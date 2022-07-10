package br.uespi.cadastroaluno.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;

import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.utils.FormUtil;

import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDeCadastro extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2L;

	private static final String NAME_NOME = "nome";
	private static final String NAME_SOBRENOME = "sobrenome";
	private static final String NAME_MATRICULA = "matrícula";
	private static final String NAME_DATA_NASCIMENTO = "data de nascimento";
	private static final String NAME_TELEFONE = "telefone";
	private static final String NAME_CPF = "CPF";

	private JFormattedTextField editMatricula;
	private JFormattedTextField editCPF;
	private JFormattedTextField editTelefone;
	private JFormattedTextField editData;
	private JTextField editNome;
	private JTextField editSobrenome;
	private JButton btnVoltar;
	private JButton btnCadastrar;

	private final JMainFrame frame;

	private List<Aluno> alunoList;

	public TelaDeCadastro(JMainFrame frame) {
		this.frame = frame;
		alunoList = new ArrayList<>();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		JToolBar toolbar = new JToolBar();
		toolbar.setBounds(0, 0, 587, 60);
		toolbar.setFloatable(false);
		toolbar.setBackground(new Color(255, 0, 51));
		toolbar.setBorder(FormUtil.getBorder(toolbar));

		JLabel textTitle = new JLabel("CADASTRAR ALUNO");
		textTitle.setForeground(new Color(255, 255, 255));
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setFont(new Font("Open Sans", Font.BOLD, 16));
		toolbar.add(textTitle);
		add(toolbar);

		JLabel labelMatricula = new JLabel("Matrícula:");
		labelMatricula.setBounds(10, 113, 126, 17);
		labelMatricula.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(labelMatricula);

		editMatricula = new JFormattedTextField();
		editMatricula.setName(NAME_MATRICULA);
		editMatricula.setBounds(10, 133, 126, 33);
		FormUtil.registrationNumberFormat(editMatricula);
		editMatricula.setFont(new Font("Open Sans", Font.PLAIN, 14));
		labelMatricula.setLabelFor(editMatricula);
		editMatricula.setColumns(10);
		editMatricula.setBorder(FormUtil.getBorder(editMatricula));
		add(editMatricula);

		JLabel labelNome = new JLabel("Nome:");
		labelNome.setBounds(10, 183, 280, 17);
		labelNome.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(labelNome);

		editNome = new JTextField();
		FormUtil.formatName(editNome);
		editNome.setName(NAME_NOME);
		editNome.setBounds(10, 203, 280, 30);
		editNome.setFont(new Font("Open Sans", Font.PLAIN, 14));
		editNome.setColumns(10);
		editNome.setBorder(FormUtil.getBorder(editNome));
		add(editNome);

		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setBounds(297, 183, 280, 17);
		lblNewLabel_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(lblNewLabel_1);

		editSobrenome = new JTextField();
		FormUtil.formatName(editSobrenome);
		editSobrenome.setName(NAME_SOBRENOME);
		editSobrenome.setBounds(297, 203, 280, 30);
		editSobrenome.setFont(new Font("Open Sans", Font.PLAIN, 14));
		editSobrenome.setColumns(10);
		editSobrenome.setBorder(FormUtil.getBorder(editSobrenome));
		add(editSobrenome);

		JLabel lblNewLabel_1_1 = new JLabel("Telefone:");
		lblNewLabel_1_1.setBounds(10, 253, 126, 17);
		lblNewLabel_1_1.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(lblNewLabel_1_1);

		editTelefone = new JFormattedTextField(FormUtil.phoneNumberFormat());
		editTelefone.setName(NAME_TELEFONE);
		editTelefone.setBounds(10, 273, 150, 33);
		editTelefone.setFont(new Font("Open Sans", Font.PLAIN, 14));
		editTelefone.setColumns(10);
		editTelefone.setBorder(FormUtil.getBorder(editTelefone));
		add(editTelefone);

		JLabel lblNewLabel = new JLabel("Data de nascimento:");
		lblNewLabel.setBounds(220, 253, 145, 17);
		lblNewLabel.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(lblNewLabel);

		editData = new JFormattedTextField(FormUtil.dateNumberFormat());
		editData.setName(NAME_DATA_NASCIMENTO);
		editData.setHorizontalAlignment(SwingConstants.CENTER);
		editData.setBounds(220, 273, 150, 33);
		editData.setFont(new Font("Open Sans", Font.PLAIN, 14));
		editData.setColumns(10);
		editData.setBorder(FormUtil.getBorder(editData));
		add(editData);

		JLabel lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setBounds(427, 253, 126, 17);
		lblNewLabel_2.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(lblNewLabel_2);

		editCPF = new JFormattedTextField(FormUtil.cpfFormat());
		editCPF.setName(NAME_CPF);
		editCPF.setBounds(427, 273, 150, 33);
		editCPF.setFont(new Font("Open Sans", Font.PLAIN, 14));
		editCPF.setBorder(FormUtil.getBorder(editCPF));
		editCPF.setColumns(10);
		add(editCPF);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(236, 423, 150, 30);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setBackground(new Color(255, 0, 51));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(new Font("Open Sans", Font.BOLD, 14));
		add(btnCadastrar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(236, 463, 150, 30);
		btnVoltar.setForeground(new Color(51, 51, 51));
		btnVoltar.setFont(new Font("Open Sans", Font.BOLD, 14));
		btnVoltar.setFocusPainted(false);
		btnVoltar.setBackground(new Color(255, 255, 255));
		add(btnVoltar);

		btnVoltar.addActionListener(this);
		btnCadastrar.addActionListener(this);
	}

	private void clearText() {
		Component[] components = getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				((JTextField) component).setText(null);
			}
			
			if (component instanceof JFormattedTextField) {
				((JFormattedTextField) component).setValue(null);
			}
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCadastrar) {
			if (isDataValid()) {
				cadastrarAluno();
			}
		} else if (e.getSource() == btnVoltar) {
			setVisible(false);
		}
	}

	private void cadastrarAluno() {
		int matricula = Integer.parseInt(editMatricula.getText());
		String nome = editNome.getText();
		String sobrenome = editSobrenome.getText();
		String telefone = editTelefone.getText();
		String cpf = editCPF.getText();
		String dataNascimento = editData.getText();

		Aluno aluno = new Aluno(matricula, nome.concat(" ").concat(sobrenome), (int) getIdade(dataNascimento),
				dateParse(dataNascimento), telefone, cpf);

		addAluno(aluno);
		System.out.println(frame.getAlunoList().size());
		clearText();
	}

	private void addAluno(Aluno aluno) {
		alunoList.add(aluno);
		frame.setAlunoList(alunoList);
	}

	private Date dateParse(String date) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private long getIdade(String dataNascimento) {
		String[] dataIdade = dataNascimento.split("/");

		int diaIdade = Integer.parseInt(dataIdade[0]);
		int mesIdade = Integer.parseInt(dataIdade[1]);
		int anoIdade = Integer.parseInt(dataIdade[2]);

		LocalDate start = LocalDate.of(anoIdade, mesIdade, diaIdade);
		LocalDate end = LocalDate.now();

		long anos = ChronoUnit.YEARS.between(start, end);

		return anos;
	}

	private boolean matriculaExistente(String matricula) {
		// Percorre a lista de alunos e retorna uma lista de matriculas
		List<Integer> matriculas = alunoList.stream().map((aluno) -> aluno.getMatricula()).collect(Collectors.toList());
		for (int m : matriculas) {
			if (m == Integer.parseInt(matricula)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean cpfExistente(String cpf) {
		// Percorre a lista de alunos e retorna uma lista de cpf
		List<String> matriculas = alunoList.stream().map((aluno) -> aluno.getCPF()).collect(Collectors.toList());
		for (String c : matriculas) {
			if (c.equals(cpf)) {
				return true;
			}
		}
		return false;
	}

	private boolean isDataValid() {
		boolean isValid = true;
		boolean isEmpty = true;
		Component[] components = getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				JTextField field = ((JTextField) component);
				String text = field.getText().trim();
				switch (field.getName()) {
				case NAME_NOME:
					isEmpty = text.isEmpty();
					break;
				case NAME_SOBRENOME:
					isEmpty = text.isEmpty();
					break;
				case NAME_MATRICULA:
					boolean isMatriculaValida = text.length() == 7;
					isEmpty = text.isEmpty();
					if (!isMatriculaValida && !isEmpty) {
						isValid = false;
						showErrorMessage("Matrícula inválida", "A matrícula deve ter 7 (sete) dígitos.");
						break;
					}
					if(matriculaExistente(text)) {
						isValid = false;
						showErrorMessage("Matrícula já cadastrada", "Essa matricula já está cadastrada. Por favor, escolha outra.");
						break;
					}
					break;
				case NAME_DATA_NASCIMENTO:
					if (editData.getValue() == null) {
						isValid = false;
						showErrorMessage("Data de nascimento inválida",
								"A data de nascimento é inválida. Por favor, use o formato dd/mm/yyyy.");
						break;
					} else {
						String[] dataIdade = editData.getText().split("/");

						int dia = Integer.parseInt(dataIdade[0]);
						int mes = Integer.parseInt(dataIdade[1]);
						int ano = Integer.parseInt(dataIdade[2]);

						try {
							LocalDate.of(ano, mes, dia);
							isValid = true;
						} catch (DateTimeException e) {
							isValid = false;
							if (e.getMessage().contains("Invalid value for MonthOfYear")) {
								showErrorMessage("Data de nascimento inválida",
										"O mês digitado é inválido. Por favor, digite um mês válido.");
							} else if (e.getMessage().contains("Invalid value for Year")) {
								showErrorMessage("Data de nascimento inválida",
										"O ano digitado é inválido. Por favor, digite um ano válido.");
							} else if (e.getMessage().contains("Invalid value for DayOfMonth")) {
								showErrorMessage("Data de nascimento inválida",
										"O dia digitado é inválido. Por favor, digite um dia válido.");
							}
						}
					}
					break;
				case NAME_TELEFONE:
					if (editTelefone.getValue() == null) {
						isValid = false;
						showErrorMessage("Número de telefone inválido",
								"Este número de telefone é inválido. Por favor, digite novamente");
						break;
					}
					break;
				case NAME_CPF:
					if (editCPF.getValue() == null) {
						isValid = false;
						showErrorMessage("CPF inválido", "Este CPF é inválido. Por favor, digite novamente");
						break;
					}
					if(cpfExistente(text)) {
						isValid = false;
						showErrorMessage("CPF já cadastrada", "Esse CPF já está cadastrado. É possível que esse aluno já esteja matriculado nesse sistema.");
						break;
					}
					break;
				default:
					isValid = true;
					break;
				}

				if (!isValid) {
					break;
				}

				if (isEmpty) {
					isValid = false;
					showErrorEmptyFieldMessage("O campo " + field.getName() + " é obrigatório");
					break;
				}
			}
		}

		return isValid;
	}

	private void showErrorEmptyFieldMessage(String msg) {
		showErrorMessage("Campo Obrigatório", msg);
	}

	private void showErrorMessage(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}

}
