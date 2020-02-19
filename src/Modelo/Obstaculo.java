package Modelo;


import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Obstaculo {

	 private Image imagem;
	private ImageIcon referencia;
		private int x, y;
	private boolean isVisivel;
	
	public Obstaculo(int x,int y) {
		this.x = x;
		this.y = y;
		
		referencia = new ImageIcon(getClass().getClassLoader().getResource("barra.png"));

		imagem = referencia.getImage();

	
		isVisivel = true;
		
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisivel() {
		return isVisivel;
	}



}
