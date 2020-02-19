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
	
	private JLabel  lblBomber,imagem ;
	private JButton btnJogar,Pontuacao,btnAjuda,btnSair;

	public Menu(int Largura, int Altura) {
		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);
		
		
		lblBomber = new JLabel("");
		lblBomber.setForeground(Color.BLUE);
		lblBomber.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
		lblBomber.setBounds(206, 0, 254, 60);
		add(lblBomber);
		
		btnJogar = new JButton("INICIAR GAME");
		btnJogar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnJogar.setBounds(47, 420, 159, 40);
		add(btnJogar);
		
		Pontuacao = new JButton("Ranking");
		Pontuacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Pontuacao.setBounds(225, 420, 143, 40);
		add(Pontuacao);
		
		btnAjuda = new JButton("Ajuda");
		btnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAjuda.setBounds(377, 420, 89, 40);
		add(btnAjuda);
		
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSair.setBounds(499, 420, 89, 40);
		add(btnSair);
		
		imagem = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fundo.png")));
		imagem.setBounds(-10, -20, 780,558);
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
