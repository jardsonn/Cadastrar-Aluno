package br.uespi.cadastroaluno.ui;

import java.awt.EventQueue;

public class TelaPrincipal extends JMainFrame {
	
	public TelaPrincipal() {
		try {
			
			TelaListaCadastrado telaListaCadastrado = new TelaListaCadastrado(this);
			TelaDeCadastro telaCadastro = new TelaDeCadastro(this);
			setContentPane(telaListaCadastrado);
			setVisible(true);
			
			
			telaListaCadastrado.setNewStudent(panel -> {
				goToScreen(telaCadastro);
			});
			
			telaCadastro.onBack(panel -> {
				goToScreen(telaListaCadastrado);
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaPrincipal();
			}
		});
	}

}
