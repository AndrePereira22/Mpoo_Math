package Modelo;

import java.util.ArrayList;

public class Jogador {

	private String nome;
	private int pontucao;

	

	public Jogador(String nome, int pontucao) {
		super();
		this.nome = nome;
		this.pontucao = pontucao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontucao() {
		return pontucao;
	}

	public void setPontucao(int pontucao) {
		this.pontucao = pontucao;
	}


}
