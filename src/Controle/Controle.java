package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Audio;
import Modelo.Fase;
import Modelo.SalvarDadosXml;
import Modelo.Sprite;
import Modelo.Usuario;
import Visao.Ajuda;
import Visao.Camera;
import Visao.Game;
import Visao.Janela;
import Visao.Menu;
import Visao.Rank;
import Visao.TelaJogador;

public class Controle extends Thread implements KeyListener, ActionListener {

	private Janela janela;
	private Fase fase;
	private Ajuda ajuda;
	private Game game;
	private Rank rank;
	private TelaJogador telaJogador;
	private Menu menu;
	private Sprite personagem;
	private Audio audio;
	private Movimento eventos1;
	private Camera camera;
	static HashMap<Integer, Boolean> keyPool;
	boolean ativo;
	boolean respondendo = false, tempo = false;
	private Random sorteio;
	private int superior, inferior, resposta, aux;
	private int contador = 0, pontuacao = 0;
	private Usuario usuario;

	public Controle(Janela janela, Menu menu, Ajuda ajuda, Game game, TelaJogador telaJogaador, Rank rank) {

		this.janela = janela;
		this.menu = menu;
		this.ajuda = ajuda;
		this.game = game;
		this.telaJogador = telaJogaador;
		this.rank = rank;

		keyPool = new HashMap<Integer, Boolean>();

		audio = new Audio();

		sorteio = new Random();

		usuario = new Usuario("Vanessa","0");

		janela.add(menu);
		janela.add(ajuda);
		janela.add(game);
		janela.add(telaJogaador);
		janela.add(rank);

		controleEventos();

		janela.setVisible(true);
	}

	public void inicializar() {

		if (telaJogador.getRdbtnAndre().isSelected()) {
			this.fase = new Fase("sprite.png");
		} else {
			this.fase = new Fase("sprite1.png");
		}

		janela.add(fase);
		personagem = fase.getBomber();
		eventos1 = new Movimento(personagem);
		camera = fase.getCamera();
		telaJogador.setVisible(false);
		fase.setVisible(true);
		fase.requestFocus();
		fase.addKeyListener(eventos1);
		fase.addKeyListener(this);

		start();

	}

	public void controleEventos() {

		menu.getBtnJogar().addActionListener(this);
		menu.getBtnSair().addActionListener(this);
		menu.getBtnAjuda().addActionListener(this);
		menu.getPontuacao().addActionListener(this);
		rank.getBtnVoltar().addActionListener(this);
		ajuda.getVoltar().addActionListener(this);
		telaJogador.getBtnOk().addActionListener(this);
		telaJogador.getBtnVoltar().addActionListener(this);
		game.getPassar().addActionListener(this);

		for (int i = 0; i < 4; i++) {
			game.getBotoes()[i].addActionListener(this);
		}

	}

	public void actionPerformed(ActionEvent e) {

		for (int contador = 0; contador < 4; contador++) {
			if (e.getSource() == game.getBotoes()[contador]) {

				int escolha = Integer.parseInt(game.getBotoes()[contador].getText());

				if (escolha == resposta) {

					if (game.getBarra().getValue() <= 17) {
						game.getBarra().setValue(game.getBarra().getValue() + 3);
						pontuacao = pontuacao + 1;
					} else {
						game.getBarra().setValue(20);
					}

					audio.getAcerto().play();
				} else {
					audio.getErro().play();
				}

				try {
					Thread.sleep(1000);
					sortearOperação();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		}

		if (e.getSource() == menu.getBtnJogar()) {
			trocarTelas(menu, telaJogador);

		}
		if (e.getSource() == menu.getBtnSair()) {
			System.exit(0);
		}
		if (e.getSource() == menu.getBtnAjuda()) {

			trocarTelas(menu, ajuda);
		}
		if (e.getSource() == ajuda.getVoltar()) {

			trocarTelas(ajuda, menu);
		}
		if (e.getSource() == telaJogador.getBtnVoltar()) {
			trocarTelas(telaJogador, menu);
		}
		if (e.getSource() == rank.getBtnVoltar()) {
			trocarTelas(rank, menu);
		}
		if (e.getSource() == menu.getPontuacao()) {

			if (SalvarDadosXml.listar() != null) {
				ArrayList<Usuario> u = SalvarDadosXml.listar();
				rank.listar(u);
			}

			trocarTelas(menu, rank);
		}
		if (e.getSource() == telaJogador.getBtnOk()) {

			inicializar();

		}
		if (e.getSource() == game.getPassar()) {
			sortearOperação();

		}
	}

	public void run() {
		ativo = true;
		while (ativo) {

			try {
				if (fase.isVisible()) {
					runControleDoJogo();
				}
				if (game.isVisible() && respondendo) {

					diminuirBarra();
				}
				if (game.getBarra().getValue() == 0 && respondendo) {
					respondendo = false;

					salvarXML();
					JOptionPane.showMessageDialog(null, "game over");

				}

				if (pontuacao == 2 && respondendo) {
					game.setVisible(false);
					respondendo = false;
					Fase.getObstaculos().get(contador).setVisivel(false);
					fase.setVisible(true);
					fase.requestFocus();
					contador++;
					game.getBarra().setValue(20);
					pontuacao = 0;
				}

				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void runControleDoJogo() {

		if (personagem.colisaoBloco(Fase.getObstaculos(), 0, 0) && respondendo == false) {
			fase.setVisible(false);
			fase.setLocation(1000, 0);
			game.setVisible(true);
			game.requestFocus();

			sortearOperação();

			respondendo = true;
		}

	}

	public void keyPressed(KeyEvent e) {

		keyPool.put(e.getKeyCode(), true);

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
			menu();
		}
	}

	public void keyReleased(KeyEvent e) {
		keyPool.remove(e.getKeyCode());

	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void menu() {

		fase.setVisible(false);
		fase.setLocation(1000, 0);
		menu.setVisible(true);
		menu.requestFocus();

	}

	public void trocarTelas(JPanel a, JPanel b) {

		a.setVisible(false);
		b.setVisible(true);

	}

	public void sortearOperação() {

		if (personagem.getOperacao() == 0) {
			superior = sorteio.nextInt(100);
			inferior = sorteio.nextInt(100);
			resposta = superior + inferior;

		} else if (personagem.getOperacao() == 1) {
			superior = sorteio.nextInt(100);
			inferior = sorteio.nextInt(100);
			resposta = superior - inferior;
			game.getOperador().setText("-");

		} else if (personagem.getOperacao() == 2) {
			superior = sorteio.nextInt(100);
			inferior = sorteio.nextInt(100);

			try {
				while (superior % inferior != 0) {
					superior = sorteio.nextInt(100);
					inferior = sorteio.nextInt(100);
				}
				resposta = superior / inferior;
				game.getOperador().setText("%");
			} catch (java.lang.ArithmeticException e) {
			}

		} else if (personagem.getOperacao() == 3) {
			superior = sorteio.nextInt(10);
			inferior = sorteio.nextInt(10);
			resposta = superior * inferior;
			game.getOperador().setText("X");
		}

		game.getSuperior().setText(superior + "");
		game.getInferior().setText(inferior + "");

		for (int i = 0; i < 4; i++) {

			aux = sorteio.nextInt((resposta + 10) - (resposta - 10) + 1) + resposta - 10;

			if (resposta < 0) {
				aux = aux * (-1);
			}

			game.getBotoes()[i].setText(aux + "");

		}

		aux = sorteio.nextInt(3);

		game.getBotoes()[aux].setText(resposta + "");

	}

	public void diminuirBarra() {
		try {
			sleep(1000);
			game.getBarra().setValue(game.getBarra().getValue() - 1);

		} catch (Exception e) {
		}
	}

	public void separarRanking() {
		try {
			ArrayList<Usuario> jogadores = SalvarDadosXml.listar();

			Collections.sort(jogadores);

		} catch (Exception ex) {
		}

	}

	public void salvarXML() {
		if (SalvarDadosXml.listar() != null) {
			ArrayList<Usuario> u = SalvarDadosXml.listar();
			usuario.setPontuacao("" + pontuacao);
			u.add(usuario);
			SalvarDadosXml.gravarXML(u);
		} else {
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			usuario.setPontuacao("" + pontuacao);
			users.add(usuario);
			SalvarDadosXml.gravarXML(users);
		}
	}

}
