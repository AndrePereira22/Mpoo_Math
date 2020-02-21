package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Modelo.Audio;
import Modelo.Fase;
import Modelo.Jogador;
import Modelo.Sprite;
import Visao.Ajuda;
import Visao.Camera;
import Visao.Game;
import Visao.Janela;
import Visao.Menu;
import Visao.Rank;
import Visao.TelaJogador;

public class Controle implements Runnable, KeyListener, ActionListener {

	Janela janela;
	Fase fase;
	Ajuda ajuda;
	Game game;
	Rank rank;
	TelaJogador telaJogador;
	Menu menu;
	Sprite personagem;
	Audio audio;
	MovimentoP1 eventos1;
	Camera camera;
	static HashMap<Integer, Boolean> keyPool;
	boolean ativo;
	boolean respondendo = false;
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	private Random sorteio;
	private int superior, inferior, resposta, aux;

	public Controle(Janela janela, Fase fase, Menu menu, Ajuda ajuda, Game game, TelaJogador telaJogaador, Rank rank) {

		this.janela = janela;
		this.fase = fase;
		this.menu = menu;
		this.ajuda = ajuda;
		this.game = game;
		this.telaJogador = telaJogaador;
		this.rank = rank;

		personagem = fase.getBomber();

		eventos1 = new MovimentoP1(personagem);

		camera = fase.getCamera();

		keyPool = new HashMap<Integer, Boolean>();

		audio = new Audio();

		sorteio = new Random();

		janela.add(fase);
		janela.add(menu);
		janela.add(ajuda);
		janela.add(game);
		janela.add(telaJogaador);
		janela.add(rank);

		controleEventos();

		janela.setVisible(true);
	}

	public void inicializar() {

		telaJogador.setVisible(false);
		fase.setVisible(true);
		fase.requestFocus();

	}

	public void controleEventos() {

		fase.addKeyListener(eventos1);
		fase.addKeyListener(this);
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
					System.out.println("acertou");
					audio.getAcerto().play();
				} else {
					System.out.println("errou");
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

			menu.setVisible(false);
			telaJogador.setVisible(true);
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

			// rank.editarCampos(jogadores);

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
				runControleDoJogo();
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
		;

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
		superior = sorteio.nextInt(100);
		inferior = sorteio.nextInt(100);

		resposta = superior + inferior;

		game.getSuperior().setText(superior + "");
		game.getInferior().setText(inferior + "");

		for (int i = 0; i < 4; i++) {

			aux = sorteio.nextInt(100);

			game.getBotoes()[i].setText(aux + "");

		}

		aux = sorteio.nextInt(3);

		game.getBotoes()[aux].setText(resposta + "");

	}

}
