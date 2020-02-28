package App;

import Controle.Controle;
import Visao.Ajuda;
import Visao.Game;
import Visao.Janela;
import Visao.Menu;
import Visao.Rank;
import Visao.TelaJogador;

public class App {

	private final static int largura = 780, altura = 558;

	public static void main(String[] args) {

		Janela janela = new Janela(largura, altura);
		Ajuda ajuda = new Ajuda(largura, altura);
		Game game = new Game(largura, altura);
		TelaJogador telaJogaador = new TelaJogador(largura, altura);
		Menu menu = new Menu(largura, altura);
		Rank rank = new Rank(largura, altura);
		new Controle(janela, menu, ajuda, game, telaJogaador, rank);

	}

}
