package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaJogador extends JPanel {
	private JTextField textField;
	private JButton btnOk, btnVoltar;

	public TelaJogador(int Largura, int Altura) {
		
		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);

		JLabel lblNome = new JLabel("DIGITE SEU NOME :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNome.setBounds(50, 424, 221, 57);
		add(lblNome);

		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnOk.setBounds(546, 430, 73, 40);
		add(btnOk);

		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnVoltar.setBounds(635, 432, 130, 35);
		add(btnVoltar);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(277, 435, 251, 35);
		add(textField);
		textField.setColumns(10);

		JLabel fundo = new JLabel("");
		fundo.setBounds(0, 0, Largura, Altura);
		fundo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("im.png")));
		add(fundo);

		setVisible(false);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
