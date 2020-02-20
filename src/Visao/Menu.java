package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblBomber, imagem;
	private JButton btnJogar, Pontuacao, btnAjuda, btnSair;

	public Menu(int Largura, int Altura) {
		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);

		lblBomber = new JLabel("");
		lblBomber.setForeground(Color.BLUE);
		lblBomber.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
		lblBomber.setBounds(206, 0, 254, 60);
		add(lblBomber);

		btnJogar = new JButton("JOGAR");
		btnJogar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnJogar.setBounds(47, 420, 159, 70);
		btnJogar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("calc1.png")));
		btnJogar.setContentAreaFilled(false);
		btnJogar.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("calc.png")));
		add(btnJogar);

		Pontuacao = new JButton("Ranking");
		Pontuacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Pontuacao.setIcon(new ImageIcon(getClass().getClassLoader().getResource("rank.png")));
		Pontuacao.setContentAreaFilled(false);
		Pontuacao.setBounds(225, 420, 203, 70);
		Pontuacao.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("rank1.png")));
		add(Pontuacao);

		btnAjuda = new JButton("Ajuda");
		btnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAjuda.setIcon(new ImageIcon(getClass().getClassLoader().getResource("repair.png")));
		btnAjuda.setBounds(442, 420, 150, 70);
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("repair1.png")));
		add(btnAjuda);

		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSair.setIcon(new ImageIcon(getClass().getClassLoader().getResource("warning.png")));
		btnSair.setBounds(612, 420, 140, 70);
		btnSair.setContentAreaFilled(false);
		// btnAjuda.setRolloverIcon(new
		// ImageIcon(getClass().getClassLoader().getResource("calc1.png")));
		add(btnSair);

		imagem = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fundo.png")));
		imagem.setBounds(-10, -20, 780, 558);
		add(imagem);

	}

	public JButton getBtnJogar() {
		return btnJogar;
	}

	public JButton getBtnMultiPlayer() {
		return Pontuacao;
	}

	public JButton getBtnAjuda() {
		return btnAjuda;
	}

	public JButton getBtnSair() {
		return btnSair;
	}

}
