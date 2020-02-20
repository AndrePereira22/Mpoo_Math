package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class Ajuda extends JPanel {

	private JLabel fundo, lblInstrues;
	private JTextArea txtrCliqueEmbegin_1;
	private JButton voltar;

	public Ajuda(int Largura, int Altura) {
		
		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);

		lblInstrues = new JLabel("INSTRU\u00C7\u00D5ES");
		lblInstrues.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrues.setForeground(Color.BLUE);
		lblInstrues.setBounds(20, 383, 99, 14);
		add(lblInstrues);
		
		txtrCliqueEmbegin_1 = new JTextArea();
		txtrCliqueEmbegin_1.setToolTipText("INSTRU\u00C7\u00D5ES");
		txtrCliqueEmbegin_1.setText(
				" Clique em \"Iniciar Game\" para iniciar o jogo. Adicione ou subtraia\r\n os n\u00FAmeros no quadro. Em seguida, clique na resposta correta. \r\n Se voc\u00EA acertar, ganha tempo extra, caso contr\u00E1rio perde tempo. \r\n Voc\u00EA pode pular para outro problema clicando no bot\u00E3o \"-\" ou \"+\" \r\n \u00E0 direita do professor sem penalidade. Obtenha o m\u00E1ximo");
		txtrCliqueEmbegin_1.setBounds(20, 409, 531, 95);
		add(txtrCliqueEmbegin_1);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(600, 449, 89, 30);
		add(voltar);
		
		fundo = new JLabel("");
		fundo.setBounds(0, 0, Largura,Altura);
		fundo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("im.png")));
		add(fundo);


		

		setVisible(false);

	}

	public JButton getVoltar() {
		return voltar;
	}

}
