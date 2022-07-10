package br.uespi.cadastroaluno.ui;

import java.awt.EventQueue;

public class TelaPrincipal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMainFrame mainFrame = new JMainFrame();
					
					TelaListaCadastrado telaListaCadastrado = new TelaListaCadastrado(mainFrame);
					TelaDeCadastro telaCadastro = new TelaDeCadastro(mainFrame);

					mainFrame.setContentPane(telaCadastro);
					//mainFrame.setContentPane(telaListaCadastrado);
					
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
