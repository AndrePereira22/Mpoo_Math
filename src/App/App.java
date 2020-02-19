package App;

import Controle.Controle;
import Modelo.Fase;
import Visao.Ajuda;
import Visao.Game;
import Visao.Janela;
import Visao.Menu;

public class App {

	public static void main(String[] args) {
		 
		Janela janela = new Janela(780,558 );
		Fase  fase =  new Fase();
		Ajuda ajuda = new Ajuda(780,558);
		Game game = new Game(780,558);
		Menu menu = new Menu(780,558);
		
		Controle control =  new Controle(janela, fase,menu,ajuda,game);
		control.run();
		
		
		
	}

}
