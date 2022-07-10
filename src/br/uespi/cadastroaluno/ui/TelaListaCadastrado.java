package br.uespi.cadastroaluno.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.uespi.cadastroaluno.utils.FormUtil;

import javax.swing.JLabel;

public class TelaListaCadastrado extends JPanel {

	private static final long serialVersionUID = -4139369237312199337L;

	public TelaListaCadastrado(JMainFrame frame) {
		initialize(frame);
	}

	private void initialize(JMainFrame frame) {
		setLayout(null);
		JToolBar toolbar = new JToolBar();
		toolbar.setBounds(0, 0, 587, 60);
		toolbar.setFloatable(false);
		toolbar.setBackground(new Color(255, 0, 51));
		toolbar.setBorder(FormUtil.getBorder(toolbar));

		
		JLabel textTitle = new JLabel("ALUNOS CADASTRADOS");
		textTitle.setForeground(new Color(255, 255, 255));
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setFont(new Font("Open Sans", Font.BOLD, 16));
		toolbar.add(textTitle);
		add(toolbar);
	}
}
