package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
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
	private Movimento movimento;
	private Camera camera;
	static HashMap<Integer, Boolean> keyPool;
	boolean ativo;
	boolean respondendo = false, tempo = false;
	private Random sorteio;
	private int superior, inferior, resposta, aux;
	private int contador = 0, pontuacao = 0, conquista = 5, placar = 0;
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

		usuario = new Usuario("default", "0");

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
			game.getJogador().setIcon(new ImageIcon(getClass().getClassLoader().getResource("menino.png")));
		} else {
			this.fase = new Fase("sprite1.png");
			game.getJogador().setIcon(new ImageIcon(getClass().getClassLoader().getResource("menina.png")));

		}

		janela.add(fase);
		personagem = fase.getAluno();
		movimento = new Movimento(personagem);
		camera = fase.getCamera();
		telaJogador.setVisible(false);
		fase.setVisible(true);
		fase.requestFocus();
		fase.addKeyListener(movimento);
		fase.addKeyListener(this);
		usuario.setNome(telaJogador.getTextField().getText());
		game.getJogador().setText(usuario.getNome());
		audio.getMusica().loop();

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
		game.getVoltarMenu().addActionListener(this);
		game.getSair().addActionListener(this);

		for (int i = 0; i < 4; i++) {
			game.getBotoes()[i].addActionListener(this);
		}

	}

	public void actionPerformed(ActionEvent e) {
		int escolha = 0;

		for (int contador = 0; contador < 4; contador++) {
			if (e.getSource() == game.getBotoes()[contador]) {

				if (personagem.getOperacao() != 4) {

					escolha = Integer.parseInt(game.getBotoes()[contador].getText());

					if (escolha == resposta) {

						pontuacao = pontuacao + 1;
						placar = placar + 1;
						game.getPlacar().setText(placar + "");

						if (game.getBarra().getValue() <= 17) {
							game.getBarra().setValue(game.getBarra().getValue() + 3);

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
		if (e.getSource() == game.getVoltarMenu()) {
			menu();
			trocarTelas(game, menu);

		}
		if (e.getSource() == game.getSair()) {
			System.exit(0);

		}
	}

	public void run() {
		ativo = true;
		while (ativo) {

			try {
				if (fase.isVisible()) { // se a fase tiver visivel controla o jogo
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

				if (pontuacao == conquista && respondendo) {

					voltarAoLab();

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
		contador = 0;
		respondendo = false;
		pontuacao = 0;
		game.getPlacar().setText("0");
		game.getBarra().setValue(20);

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
		} else if (personagem.getOperacao() == 4) {
			System.out.println("funcionaou");
			aux = sorteio.nextInt(1);
			resposta = fase.getRespostas()[aux];
			game.getSuperior().setText("");
			game.getInferior().setText(fase.getFuncoes()[aux]);
			game.getOperador().setText("");

		} else if (personagem.getOperacao() == 5) {
		}

		game.getSuperior().setText(superior + "");
		game.getInferior().setText(inferior + "");

		for (int i = 0; i < 4; i++) {

			aux = sorteio.nextInt((resposta + 10) - (resposta - 10) + 1) + resposta - 10;

			game.getBotoes()[i].setText(aux + "");

		}
		for (int i = 0; i < 4; i++) {
			int res = Integer.parseInt(game.getBotoes()[i].getText());

			if (res == resposta) {
				aux = sorteio.nextInt((resposta + 10) - (resposta - 10) + 1) + resposta - 10;
				game.getBotoes()[i].setText(aux + "");

			}

		}

		aux = sorteio.nextInt(3);

		game.getBotoes()[aux].setText(resposta + "");

	}

	public void diminuirBarra() {
		try {
			sleep(1000);
			game.getBarra().setValue(game.getBarra().getValue() - 1);
			game.getTempo().setText("" + game.getBarra().getValue());

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

	public void voltarAoLab() {
		game.setVisible(false);
		respondendo = false;
		Fase.getObstaculos().get(contador).setVisivel(false);

		fase.setVisible(true);

		fase.requestFocus();
		personagem.setX(personagem.getX());

		contador++;
		game.getBarra().setValue(20);
		pontuacao = 0;
	}

	public void salvarXML() {
		if (SalvarDadosXml.listar() != null) {
			ArrayList<Usuario> u = SalvarDadosXml.listar();
			usuario.setPontuacao("" + placar);
			u.add(usuario);
			SalvarDadosXml.gravarXML(u);
		} else {
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			usuario.setPontuacao("" + placar);
			users.add(usuario);
			SalvarDadosXml.gravarXML(users);
		}
	}

}
