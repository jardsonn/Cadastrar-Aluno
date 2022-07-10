package br.uespi.cadastroaluno.ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.uespi.cadastroaluno.model.Aluno;

public class JMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private List<Aluno> alunoList;

	public JMainFrame() {
		setLayout(null);
		getContentPane().setFont(new Font("Open Sans", Font.PLAIN, 12));

		setSize(600, 550);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		alunoList = new ArrayList<>();
	}

	public List<Aluno> getAlunoList() {
		return alunoList;
	}

	public void setAlunoList(List<Aluno> alunoList) {
		this.alunoList = alunoList;
	}
	
	
}
