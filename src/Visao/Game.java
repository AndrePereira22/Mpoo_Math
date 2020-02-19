package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Game extends JPanel {

	private JLabel operador, superior, inferior, plano;
	private JButton resposta1, resposta2, resposta3, resposta4, passar, voltar;
	JMenuBar menuBar;
	JMenuItem VoltarMenu, sair;
	JMenu mnMenu;

	public Game(int Largura, int Altura) {

		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);

		operador = new JLabel("+");
		operador.setFont(new Font("Tahoma", Font.PLAIN, 28));
		operador.setBounds(200, 120, 46, 14);
		add(operador);

		superior = new JLabel("100");
		superior.setFont(new Font("Tahoma", Font.PLAIN, 28));
		superior.setBounds(240, 65, 77, 26);
		add(superior);

		inferior = new JLabel("500");
		inferior.setFont(new Font("Tahoma", Font.PLAIN, 28));
		inferior.setBounds(240, 120, 76, 26);
		add(inferior);

		resposta1 = new JButton("200");
		resposta1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resposta1.setBounds(140, 329, 65, 36);
		add(resposta1);

		resposta2 = new JButton("200");
		resposta2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resposta2.setBounds(285, 329, 65, 36);
		add(resposta2);

		resposta3 = new JButton("200");
		resposta3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resposta3.setBounds(430, 329, 65, 36);
		add(resposta3);

		resposta4 = new JButton("200");
		resposta4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resposta4.setBounds(575, 329, 65, 36);
		add(resposta4);

		passar = new JButton("+");
		passar.setBounds(573, 195, 41, 36);
		add(passar);

		voltar = new JButton("-");
		voltar.setBounds(634, 195, 41, 36);
		add(voltar);

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

	public JButton getResposta1() {
		return resposta1;
	}

	public JButton getResposta2() {
		return resposta2;
	}

	public JButton getResposta3() {
		return resposta3;
	}

	public JButton getResposta4() {
		return resposta4;
	}

	public JButton getPassar() {
		return passar;
	}

	public JButton getVoltar() {
		return voltar;
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

}
