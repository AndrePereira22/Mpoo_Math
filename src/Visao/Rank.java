package Visao;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Jogador;
public class Rank extends JPanel {
	private JTable tabela;
	private JScrollPane scrow;
	private JButton btnVoltar;

	public Rank(int Largura, int Altura) {

		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);

		JLabel titulo = new JLabel("RANKING DE JOGADORES");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titulo.setBounds(120, 22, 365, 29);
		add(titulo);

		btnVoltar = new JButton("VOLTAR");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnVoltar.setBounds(117, 463, 151, 39);
		add(btnVoltar);

		tabela = new JTable();

		tabela.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabela.setBorder(new LineBorder(Color.DARK_GRAY));
		tabela.setBackground(Color.LIGHT_GRAY);
		tabela.setBounds(144, 90, 281, 288);


		add(tabela);

		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("imm.png")));
		fundo.setBounds(0, 0, Largura, Altura);
		add(fundo);

	}

	public void editarCampos(ArrayList<Jogador> users) {
		try {
			tabela.setModel(
					new DefaultTableModel(new Object[][] { 
						    { "1", users.get(0).getNome(), users.get(0).getPontucao() },
							{ "2", users.get(1).getNome(), users.get(1).getPontucao() },
							{ "3", users.get(2).getNome(), users.get(2).getPontucao() },
							{ "4", users.get(3).getNome(), users.get(3).getPontucao() },
							{ "5", users.get(4).getNome(), users.get(4).getPontucao() },
						

					}, new String[] { "", "Nome", "Pontuacao" }));
			tabela.getColumnModel().getColumn(0).setPreferredWidth(3);
		} catch (NullPointerException e) {

		} catch (IndexOutOfBoundsException x) {
		}
	}

	public JTable getTabela() {
		return tabela;
	}

	public JScrollPane getScrow() {
		return scrow;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

}
