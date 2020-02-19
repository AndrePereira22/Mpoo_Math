package Visao;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Modelo.Fase;
import Modelo.Mapa;
import Modelo.Obstaculo;
import Modelo.Sprite;

public class Camera {
	private Sprite personagem;
	private Mapa mapa1, mapa2;

	static int x = 0;
	static int y = 0;
	private BufferedImage tela;
	private Graphics g;
	private ArrayList<Obstaculo> obstaculos;

	public Camera(Sprite personagem, Mapa mapa1, Mapa mapa2, ArrayList<Obstaculo> obstaculos) {
		super();
		this.personagem = personagem;
		this.mapa1 = mapa1;
		this.mapa2 = mapa2;
		this.obstaculos = obstaculos;

		tela = new BufferedImage(mapa1.getMapaLargura(), mapa1.getMapaAltura(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();

		mapa1.montarMapa();
		mapa2.montarMapa();

	}

	public void renderinzar() {

		g.drawImage(mapa1.getMapa(), 0, 0, null);
		g.drawImage(mapa2.getMapa(), 0, 0, null);

		g.drawImage(personagem.sprites[personagem.aparencia], personagem.getX(), personagem.getY(), null);

		for (int i = 0; i < obstaculos.size(); i++) {

			Obstaculo o = obstaculos.get(i);

			if (o.isVisivel()) {
				g.drawImage(o.getImagem(), o.getX(), o.getY(), null);
			}
		}

	}

	public void draw(Graphics g) {
		if (personagem.getX() > Fase.LARGURA / 2)
			if (personagem.getX() < (mapa1.getMapaLargura() - Fase.LARGURA / 2))
				x = personagem.getX() - (Fase.LARGURA / 2);
		if (personagem.getY() > Fase.ALTURA / 2)
			if (personagem.getY() < (mapa1.getMapaAltura() - Fase.ALTURA / 2))
				y = personagem.getY() - Fase.ALTURA / 2;

		g.drawImage(tela, -x, -y, null);
	}

}
