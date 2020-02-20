package Modelo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Sprite extends Thread {

	private int x, y;
	private int altura, largura;
	int rows, columns;
	public int aparencia;
	public BufferedImage[] sprites;
	BufferedImage spriteSheet; 
	
	public Sprite(String url, int aparencia, int columns, int rows, int posX, int posY) throws IOException {
		spriteSheet = ImageIO.read(getClass().getClassLoader().getResource(url));
		this.aparencia=aparencia;
		
		this.largura = spriteSheet.getWidth()/columns;
		this.altura = spriteSheet.getHeight()/rows;

		this.rows = columns;
		this.columns = rows;
		this.x=posX;
		this.y=posY;

		sprites = new BufferedImage[columns * rows];
			for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * largura, j * altura, largura, altura);
			}
		}
	}
	public boolean colisao(ArrayList<Rectangle> tmp, int x,int y) {
		Rectangle personagem=new Rectangle(getX()+x, getY()+y, getLargura(), getAltura());
		for (Rectangle rectangle : tmp) {
			if(rectangle.intersects(personagem)){
				System.out.println("colidiu");
				return true;
			}		
		}
		return false;

	}
	public boolean colisaoBloco(ArrayList<Obstaculo> tmp, int x,int y) {
		Rectangle personagem=new Rectangle(getX()+x, getY()+y, getLargura(), getAltura());
		for (Obstaculo rectangle : tmp) {
			
			Rectangle formaBloco =rectangle.getBounds();
			
			if(formaBloco.intersects(personagem) && rectangle.isVisivel()){
				return true;
			}		
		}
		return false;
	}
	
	
	public void setX(int posX) {
		
		
		if(!colisao(Fase.getRetangulosColisao(),posX-this.x,0) && 
				!colisaoBloco(Fase.getObstaculos(),posX-this.x,0)) {
			this.x=posX;
		}
		

	}

	public void setY(int posY) {
		
		if(!colisao(Fase.getRetangulosColisao(),posY-this.y,0) && 
				!colisaoBloco(Fase.getObstaculos(),posY-this.y,0)) {
			this.y=posY;
		}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {	
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}
		
}
