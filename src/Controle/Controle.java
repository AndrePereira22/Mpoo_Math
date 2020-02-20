package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

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
	private  ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
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
		// game.setVisible(true);

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

	}

	public void actionPerformed(ActionEvent e) {

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

			
			//rank.editarCampos(jogadores);

			trocarTelas(menu, rank);
		}
		if (e.getSource() == telaJogador.getBtnOk()) {
			inicializar();

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

}
