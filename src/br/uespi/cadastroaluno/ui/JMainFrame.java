package br.uespi.cadastroaluno.ui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.utils.FormUtil;

public class JMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<Aluno> alunoList;
	private JMenuBar menuBar;

	public JMainFrame() {
		setLayout(null);
		getContentPane().setFont(FormUtil.getFontNormal(12));

		setSize(740, 540);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuOptions = new JMenu("Opções");
		menuBar.add(menuOptions);

		JMenuItem itemCadastrarAluno = new JMenuItem("Cadastrar Aluno");
		JMenuItem itemRemoverUltimo = new JMenuItem("Remover Último Aluno");
		JMenuItem itemObterMatricula = new JMenuItem("Obter Matrícula");
		JMenuItem itemObterTerceiro = new JMenuItem("Obter Terceiro Aluno");
		JMenuItem itemCarregarCSV = new JMenuItem("Carregar Arquivo");

		menuOptions.add(itemCadastrarAluno);
		menuOptions.add(itemRemoverUltimo);
		menuOptions.add(itemObterMatricula);
		menuOptions.add(itemObterTerceiro);
		menuOptions.add(itemCarregarCSV);

		alunoList = new ArrayList<>();

		menuBar.setVisible(getContentPane() instanceof TelaListaCadastrado && !alunoList.isEmpty());
	}

	public void goToScreen(Container contentPane) {
		getContentPane().setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
		menuBar.setVisible(getContentPane() instanceof TelaListaCadastrado && !alunoList.isEmpty());
	}

	public List<Aluno> getAlunoList() {
		System.out.println("Aluno lista principal size " + alunoList.size());
		return alunoList;
	}

	public void addNewAluno(Aluno aluno, boolean terPosition) {
		System.out.println("Aluno lista atualizada " + alunoList.size());
		if (terPosition) {
			this.alunoList.add(2, aluno);
		} else {
			this.alunoList.add(aluno);
		}
		
	}

	public void deleteAluno(Aluno alunoSelecionado) {
		this.alunoList.remove(alunoSelecionado);
	}

}
