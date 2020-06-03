
package Modelo;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import Visao.Camera;

public class Fase extends Jogo {

	private static final long serialVersionUID = -2316510197558395731L;

	private Mapa mapa1, mapa2, mapaColisao;
	private static ArrayList<Rectangle> retangulosColisao;
	private Camera camera;
	private Sprite aluno;
	private static ArrayList<Obstaculo> obstaculos;
	private ArrayList<Point> pontos;
	private String[] funcoes = new String[] {"15-(3-2)+(7-4))", "2 x (5-8) + (7+2)"};
	private int[] respostas = new int[] {17,3};


	public Fase(String url) {
		super();
		setFocusable(true);
		setDoubleBuffered(true);

		obstaculos = new ArrayList<Obstaculo>();

		mapa1 = new Mapa("tileset.png", "cenario.txt");
		mapa2 = new Mapa("tileset.png", "cenario2.txt");
		mapaColisao = new Mapa("tileset.png", "cenario2.txt");

		mapa1.montarMapa();
		mapa2.montarMapa();
		retangulosColisao = mapaColisao.montarColi();

		try {
			aluno = new Sprite(url, 1, 4, 4, 50, 39);
		} catch (IOException e) {

			e.printStackTrace();
		}

		pontos = new ArrayList<Point>();

		iniciarPontos();

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


	public Sprite getAluno() {
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

	public void iniciarPontos() {

		pontos.add(new Point(46, 315));
		pontos.add(new Point(414, 426));
		pontos.add(new Point(410, 44));
		pontos.add(new Point(722, 235));
		pontos.add(new Point(858, 334));
		pontos.add(new Point(874, 45));
		
		

		for (int i = 0; i < 6; i++) {

			obstaculos.add(new Obstaculo(pontos.get(i).x, pontos.get(i).y, i));

		}
	}

	public String[] getFuncoes() {
		return funcoes;
	}

	public int[] getRespostas() {
		return respostas;
	}

}
