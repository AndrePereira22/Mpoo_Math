package Visao;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Game extends JPanel {

	private JLabel operador, superior, inferior, plano;
	private JButton passar;
	private JMenuBar menuBar;
	private JMenuItem VoltarMenu, sair;
	private JMenu mnMenu;
	private JButton botoes[] = new JButton[4];
	private int posicao[] = { 130, 275, 420, 565 };
	private JProgressBar barra;
	private JLabel jogador, placar, pontos;

	public Game(int Largura, int Altura) {

		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);
		
		barra = new JProgressBar(JProgressBar.HORIZONTAL, 0, 20);
		barra.setBounds(710, 125,50, 380);
		barra.setValue(20);
		add(barra);

		operador = new JLabel("+");
		operador.setFont(new Font("Tahoma", Font.PLAIN, 28));
		operador.setBounds(200, 120, 46, 28);
		add(operador);

		superior = new JLabel("100");
		superior.setFont(new Font("Tahoma", Font.PLAIN, 28));
		superior.setBounds(240, 65, 77, 26);
		add(superior);

		inferior = new JLabel("500");
		inferior.setFont(new Font("Tahoma", Font.PLAIN, 28));
		inferior.setBounds(240, 120, 76, 26);
		add(inferior);

		for (int i = 0; i < 4; i++) {

			this.botoes[i] = new JButton();
			botoes[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
			botoes[i].setBounds(posicao[i], 329, 80, 36);
			botoes[i].setContentAreaFilled(false);
			botoes[i].setBackground(Color.BLUE);

			add(botoes[i]);
		}
		
	
		jogador = new JLabel("JOGADOR");
		jogador.setBounds(533, 37, 121, 60);
		jogador.setIcon(new ImageIcon(getClass().getClassLoader().getResource("menina.png")));
		add(jogador);
		
		placar = new JLabel("0");
		placar .setBounds(637, 50, 81, 30);
		add(placar );
		
		pontos = new JLabel("PONTOS!");
		pontos.setBounds(653, 50, 81, 30);
		add(pontos);

		passar = new JButton();
		passar.setBounds(583, 197, 81, 30);
		passar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("next.png")));
		passar.setContentAreaFilled(false);
		passar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("next.png")));
		add(passar);


		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		add(menuBar);

		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		VoltarMenu = new JMenuItem("Menu Principal");
		mnMenu.add(VoltarMenu);

		sair = new JMenuItem("Sair");
		mnMenu.add(sair);

		plano = new JLabel("");
		plano.setBounds(0, 0, Largura, Altura);
		plano.setIcon(new ImageIcon(getClass().getClassLoader().getResource("ff.png")));
		add(plano);

		setVisible(false);

	}

	public JLabel getOperador() {
		return operador;
	}

	public JLabel getSuperior() {
		return superior;
	}

	public JLabel getInferior() {
		return inferior;
	}

	public JButton getPassar() {
		return passar;
	}


	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public JMenuItem getVoltarMenu() {
		return VoltarMenu;
	}

	public JMenuItem getSair() {
		return sair;
	}

	public JMenu getMnMenu() {
		return mnMenu;
	}

	public JButton[] getBotoes() {
		return botoes;
	}

	public JProgressBar getBarra() {
		return barra;
	}

	public JLabel getJogador() {
		return jogador;
	}

	public JLabel getPlacar() {
		return placar;
	}
	

}
