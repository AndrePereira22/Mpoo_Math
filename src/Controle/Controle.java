package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import Modelo.Audio;
import Modelo.Fase;
import Modelo.Sprite;
import Visao.Ajuda;
import Visao.Camera;
import Visao.Game;
import Visao.Janela;
import Visao.Menu;

public class Controle implements Runnable, KeyListener, ActionListener {

	Janela janela;
	Fase fase;
	Ajuda ajuda;
	Game game;
	Menu menu;
	Sprite bomber1;
	Audio audio;
	MovimentoP1 eventos1;
	Camera camera;
	static HashMap<Integer, Boolean> keyPool;
	boolean ativo;


	public Controle(Janela janela, Fase fase, Menu menu, Ajuda ajuda, Game game) {

		this.janela = janela;
		this.fase = fase;
		this.menu = menu;
		this.ajuda = ajuda;
		this.game = game;

		bomber1 = fase.getBomber();

		eventos1 = new MovimentoP1(bomber1);

		camera = fase.getCamera();

		keyPool = new HashMap<Integer, Boolean>();

		audio = new Audio();

		janela.add(fase);
		janela.add(menu);
		janela.add(ajuda);
		janela.add(game);
		
		

		controleEventos();

		janela.setVisible(true);
	}

	public void inicializar() {

		janela.setSize(780, 558);
		 fase.setVisible(true);
		menu.setVisible(false);
		
		game.setVisible(true);

	}

	public void controleEventos() {

		fase.addKeyListener(eventos1);
		fase.addKeyListener(this);
		menu.getBtnJogar().addActionListener(this);
		menu.getBtnSair().addActionListener(this);
		menu.getBtnAjuda().addActionListener(this);
		ajuda.getVoltar().addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menu.getBtnJogar()) {

			inicializar();
		}
		if (e.getSource() == menu.getBtnSair()) {
			System.exit(0);
		}
		if (e.getSource() == menu.getBtnAjuda()) {
			menu.setVisible(false);
			ajuda.setVisible(true);
		}
		if (e.getSource() == ajuda.getVoltar()) {
			menu.setVisible(true);
			ajuda.setVisible(false);
		}
	}

	public void run() {
		ativo = true;
		while (ativo) {
			Atualizar();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void Atualizar() {

		runControleDoJogo();

	}

	private void runControleDoJogo() {

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

}
