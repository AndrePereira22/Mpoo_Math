package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class TelaJogador extends JPanel {
	private JTextField textField;
	private JButton btnOk, btnVoltar;
	private JRadioButton rdbtnTaina,rdbtnAndre;
	private ButtonGroup grupo;

	public TelaJogador(int Largura, int Altura) {
		
		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);
		
		grupo= new ButtonGroup();
		

		JLabel lblNome = new JLabel("DIGITE SEU NOME :");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNome.setBounds(50, 424, 221, 57);
		add(lblNome);
		
		rdbtnAndre = new JRadioButton("");
		rdbtnAndre.setBounds(345, 320, 20, 47);
		rdbtnAndre.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(rdbtnAndre);
		
		rdbtnTaina = new JRadioButton("",true);
		rdbtnTaina.setBounds(145, 320, 20, 47);
		rdbtnTaina.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(rdbtnTaina);
		
		grupo.add(rdbtnAndre);
		grupo.add(rdbtnTaina);

		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnOk.setBounds(546, 430, 73, 40);
		add(btnOk);

		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnVoltar.setBounds(635, 432, 130, 35);
	

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

	public JRadioButton getRdbtnTaina() {
		return rdbtnTaina;
	}

	public JRadioButton getRdbtnAndre() {
		return rdbtnAndre;
	}
	

}
