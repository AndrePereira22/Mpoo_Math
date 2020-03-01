package Modelo;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Obstaculo {

	private Image imagem;
	private ImageIcon referencia;
	private int x, y;
	private boolean isVisivel;
	private int largura, altura;
	private String  url[] = {"adicao.png","subtra.png","divisao.png","multiplicacao.png","formula.png","fim.png"};
	private int posicao;
	public Obstaculo(int x, int y,int i) {
		this.x = x;
		this.y = y;

		referencia = new ImageIcon(getClass().getClassLoader().getResource(url[i]));

		imagem = referencia.getImage();
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		this.posicao=i;

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

	public Rectangle getBounds() {
		return new Rectangle(x, y,largura, altura);
	}

	public int getPosicao() {
		return posicao;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

}
