package Visao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Janela extends JFrame{
	
	private static final long serialVersionUID = 9139033148377613572L;

	public Janela(int largura,int altura) {
		
		setPreferredSize(new Dimension(largura, altura));
		setLayout(new FlowLayout());
		
		setSize(largura, altura);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	

}
