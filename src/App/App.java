package App;

import Controle.Controle;
import Modelo.Fase;
import Modelo.Jogador;
import Visao.Ajuda;
import Visao.Game;
import Visao.Janela;
import Visao.Menu;
import Visao.Rank;
import Visao.TelaJogador;

public class App {
	
	private final static int largura=780,altura=558;

	public static void main(String[] args) {

		Janela janela = new Janela(780, 558);
		Fase fase = new Fase();
		Ajuda ajuda = new Ajuda(780, 558);
		Game game = new Game(780, 558);
		TelaJogador telaJogaador = new TelaJogador(780, 558);
		Menu menu = new Menu(780, 558);
		Rank rank = new Rank(780,558);

		Controle control = new Controle(janela, fase, menu, ajuda, game, telaJogaador,rank);
		control.run();
		
		
	
		

	}

}
