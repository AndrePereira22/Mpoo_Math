package Modelo;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import Visao.Camera;

public class Fase extends Jogo {

	private static final long serialVersionUID = -2316510197558395731L;

	private Mapa mapa1, mapa2, mapaColisao;
	private static ArrayList<Rectangle> retangulosColisao;
	private Camera camera;
	private Sprite aluno;
	private static ArrayList<Obstaculo> obstaculos;

	public Fase() {
		super();
		setFocusable(true);
		setDoubleBuffered(true);

		Load();

	}

	public void Load() {

		obstaculos = new ArrayList<Obstaculo>();
		 
		mapa1 = new Mapa("tileset.png", "cenario.txt");
		mapa2 = new Mapa("tileset.png", "cenario2.txt");
		mapaColisao = new Mapa("tileset.png", "cenario2.txt");

		mapa1.montarMapa();
		mapa2.montarMapa();
		retangulosColisao = mapaColisao.montarColi();

		try {
			aluno = new Sprite("sprite.png", 1, 4, 4, 50, 45);
		} catch (IOException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < 4; i++) {

			obstaculos.add(new Obstaculo( 200, 200));

		}
		
		camera = new Camera(aluno, mapa1, mapa2, obstaculos);
		
		setVisible(false);
	}

	public void Update() {
		mapa1.montarMapa();
		camera.renderinzar();

	}

	public void Render() {
		camera.draw(g);
	}

	public Sprite getBomber() {
		return aluno;
	}

	public static ArrayList<Rectangle> getRetangulosColisao() {
		return retangulosColisao;
	}

	public Camera getCamera() {
		return camera;
	}

	public static ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

}
