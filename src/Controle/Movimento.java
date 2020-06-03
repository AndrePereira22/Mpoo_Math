
package Controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import Modelo.Fase;
import Modelo.Sprite;

public class Movimento extends Thread implements KeyListener {

	Sprite bomber;
	int up, down, left, right;
	static HashMap<Integer, Boolean> keyPool;
	private int pulo = 4;
	private boolean ativo = true;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Movimento(Sprite player1) {
		this.bomber = player1;
		keyPool = new HashMap<Integer, Boolean>();
		start();

	}

	public void keyPressed(KeyEvent e) {

		keyPool.put(e.getKeyCode(), true);

	}

	public void keyReleased(KeyEvent e) {

		keyPool.remove(e.getKeyCode());

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			bomber.aparencia = 3;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			bomber.aparencia = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			bomber.aparencia = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			bomber.aparencia = 2;
		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void run() {
		ativo = true;
		while (ativo) {
			mover1();

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mover1() {

		if (keyPool.get(KeyEvent.VK_RIGHT) != null) {

			bomber.setX(bomber.getX() + pulo);

			switch (right) {
			case 0:
				bomber.aparencia = 2;
				break;
			case 1:
				bomber.aparencia = 6;
				break;
			case 2:
				bomber.aparencia = 10;
				break;
			case 3:
				bomber.aparencia = 14;
				break;

			}
			if (right == 4)
				right = 0;
			else
				right++;

		}
		if (keyPool.get(KeyEvent.VK_UP) != null) {

			bomber.setY(bomber.getY() - pulo);

			switch (up) {
			case 0:
				bomber.aparencia = 3;
				break;
			case 1:
				bomber.aparencia = 7;
				break;
			case 2:
				bomber.aparencia = 11;
				break;
			case 3:
				bomber.aparencia = 15;
				break;

			}
			if (up == 4)
				up = 0;
			else
				up++;

		}
		if (keyPool.get(KeyEvent.VK_DOWN) != null) {

			bomber.setY(bomber.getY() + pulo);

			switch (down) {
			case 0:
				bomber.aparencia = 0;
				break;
			case 1:
				bomber.aparencia = 4;
				break;
			case 2:
				bomber.aparencia = 8;
				break;
			case 3:
				bomber.aparencia = 12;
				break;

			}

			if (down == 4)
				down = 0;
			else
				down++;
		}

		if (keyPool.get(KeyEvent.VK_LEFT) != null) {

			bomber.setX(bomber.getX() - pulo);

			switch (left) {
			case 0:
				bomber.aparencia = 1;
				break;
			case 1:
				bomber.aparencia = 5;
				break;
			case 2:
				bomber.aparencia = 9;
				break;
			case 3:
				bomber.aparencia = 13;
				break;

			}

			if (left == 4)
				left = 0;
			else
				left++;
		}
	}

}
