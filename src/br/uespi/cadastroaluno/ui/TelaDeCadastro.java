package br.uespi.cadastroaluno.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class TelaDeCadastro {

	private JFrame frame;
	private JFormattedTextField editMatricula;
	private JTextField editNome;
	private JTextField editSobrenome;
	private JFormattedTextField editData;
	private JLabel lblNewLabel_2;
	private JFormattedTextField editCPF;
	private JFormattedTextField editTelefone;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastro window = new TelaDeCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDeCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private MaskFormatter cpfFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	private MaskFormatter phoneNumberFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(##) # ####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	private MaskFormatter registrationNumberFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("#######");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	private MaskFormatter dateNumberFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Open Sans", Font.PLAIN, 12));
		frame.setBounds(100, 100, 530, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelMatricula = new JLabel("Matrícula:");
		labelMatricula.setFont(new Font("Open Sans", Font.BOLD, 12));
		labelMatricula.setBounds(10, 79, 78, 14);
		frame.getContentPane().add(labelMatricula);

		editMatricula = new JFormattedTextField(registrationNumberFormat());
		editMatricula.setFont(new Font("Open Sans", Font.PLAIN, 12));
		labelMatricula.setLabelFor(editMatricula);
		editMatricula.setBounds(10, 96, 127, 30);
		editMatricula.setColumns(10);
		editMatricula.setBorder(BorderFactory.createCompoundBorder(editMatricula.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		frame.getContentPane().add(editMatricula);

		JLabel labelNome = new JLabel("Nome:");
		labelNome.setFont(new Font("Open Sans", Font.BOLD, 12));
		labelNome.setBounds(10, 141, 46, 14);
		frame.getContentPane().add(labelNome);

		editNome = new JTextField();
		editNome.setFont(new Font("Open Sans", Font.PLAIN, 12));
		editNome.setBounds(10, 159, 241, 30);
		editNome.setColumns(10);
		editNome.setBorder(
				BorderFactory.createCompoundBorder(editNome.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		frame.getContentPane().add(editNome);

		editSobrenome = new JTextField();
		editSobrenome.setFont(new Font("Open Sans", Font.PLAIN, 12));
		editSobrenome.setBounds(261, 159, 243, 30);
		editSobrenome.setColumns(10);
		editSobrenome.setBorder(BorderFactory.createCompoundBorder(editSobrenome.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		frame.getContentPane().add(editSobrenome);

		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel_1.setBounds(261, 141, 243, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Data de nascimento:");
		lblNewLabel.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 202, 127, 14);
		frame.getContentPane().add(lblNewLabel);

		editData = new JFormattedTextField(dateNumberFormat());
		editData.setFont(new Font("Open Sans", Font.PLAIN, 12));
		editData.setColumns(10);
		editData.setBounds(10, 219, 150, 30);
		editData.setBorder(
				BorderFactory.createCompoundBorder(editData.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		frame.getContentPane().add(editData);

		lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel_2.setBounds(300, 79, 204, 14);
		frame.getContentPane().add(lblNewLabel_2);

		editCPF = new JFormattedTextField(cpfFormat());
		editCPF.setFont(new Font("Open Sans", Font.PLAIN, 12));
		editCPF.setBounds(300, 96, 204, 30);
		editCPF.setBorder(
				BorderFactory.createCompoundBorder(editCPF.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		editCPF.setColumns(10);
		frame.getContentPane().add(editCPF);

		editTelefone = new JFormattedTextField(phoneNumberFormat());
		editTelefone.setFont(new Font("Open Sans", Font.PLAIN, 12));
		editTelefone.setColumns(10);
		editTelefone.setBounds(147, 96, 143, 30);
		editTelefone.setBorder(BorderFactory.createCompoundBorder(editTelefone.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		frame.getContentPane().add(editTelefone);

		JLabel lblNewLabel_1_1 = new JLabel("Telefone:");
		lblNewLabel_1_1.setFont(new Font("Open Sans", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(150, 79, 66, 14);
		frame.getContentPane().add(lblNewLabel_1_1);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setBackground(new Color(255, 0, 51));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Open Sans", Font.BOLD, 12));
		btnCadastrar.setBounds(171, 299, 171, 36);
		frame.getContentPane().add(btnCadastrar);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(new Color(255, 0, 51));
		toolBar.setBounds(0, 0, 514, 27);
		toolBar.setBorder(
				BorderFactory.createCompoundBorder(toolBar.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		frame.getContentPane().add(toolBar);

		JLabel lblNewLabel_3 = new JLabel("CADASTRAR ALUNO");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		toolBar.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Open Sans", Font.BOLD, 14));
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(new Color(51, 51, 51));
		btnVoltar.setFont(new Font("Open Sans", Font.BOLD, 12));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBackground(new Color(255, 255, 255));
		btnVoltar.setBounds(171, 346, 171, 36);
		frame.getContentPane().add(btnVoltar);

	}

}
