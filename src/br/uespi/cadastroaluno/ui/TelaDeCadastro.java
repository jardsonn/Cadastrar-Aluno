package br.uespi.cadastroaluno.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.uespi.cadastroaluno.interfaces.OnClickListener;
import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.ui.components.JEditText;
import br.uespi.cadastroaluno.ui.components.JMainFrame;
import br.uespi.cadastroaluno.ui.components.JPanelCard;
import br.uespi.cadastroaluno.ui.components.RoundedBorder;
import br.uespi.cadastroaluno.ui.components.StyledButton;
import br.uespi.cadastroaluno.utils.FormUtil;

import javax.swing.JToolBar;
import javax.swing.SpinnerListModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.SpringLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.FormSpecs;
//import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JProgressBar;

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
	private StyledButton btnVoltar;
	private StyledButton btnCadastrar;
	private JCheckBox chckbxPosition;

	private final JMainFrame frame;

	private JPanelCard innerPanel;

	private OnClickListener backListener;

	private Dimension screenSize;

	public TelaDeCadastro(JMainFrame frame) {
		this.frame = frame;
		JFrame jfarme = new JFrame();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jfarme.setBounds(0, 0, screenSize.width, screenSize.height);
		jfarme.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jfarme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfarme.getContentPane().add(this);

		initialize();
	}

	private void initialize() {
		setLayout(null);
		innerPanel = new JPanelCard();
		innerPanel.setBackground(Color.WHITE);
		int marginBottom = 50;
		int width = 833;
		int height = 466;
		int xPosition = screenSize.width/2-(width/2);
		int yPosition = screenSize.height/2-(height/2)-marginBottom;

		innerPanel.setBounds(xPosition,  yPosition, width, height);
		
		add(innerPanel);
		innerPanel.setLayout(null);

		JLabel labelMatricula = new JLabel("Matrícula:");
		labelMatricula.setBounds(20, 73, 146, 17);

		labelMatricula.setFont(FormUtil.getFontBold(12));
		innerPanel.add(labelMatricula);
		editMatricula = new JEditText("0123456");
		editMatricula.setBounds(20, 94, 126, 33);
		editMatricula.setBorder(new RoundedBorder());
		editMatricula.setName(NAME_MATRICULA);
		FormUtil.registrationNumberFormat(editMatricula);
		editMatricula.setFont(FormUtil.getFontNormal(12));
		editMatricula.setColumns(10);
		editMatricula.setBorder(FormUtil.getBorder(editMatricula));
		labelMatricula.setLabelFor(editMatricula);
		innerPanel.add(editMatricula);

		JLabel labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 138, 300, 17);
		labelNome.setFont(FormUtil.getFontBold(12));
		innerPanel.add(labelNome);

		editNome = new JEditText("Ada");
		editNome.setBounds(20, 159, 360, 30);
		FormUtil.formatName(editNome);
		editNome.setBorder(new RoundedBorder());
		editNome.setName(NAME_NOME);
		editNome.setFont(FormUtil.getFontNormal(12));
		editNome.setColumns(10);
		editNome.setBorder(FormUtil.getBorder(editNome));
		innerPanel.add(editNome);

		JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
		lblNewLabel_1.setBounds(452, 138, 330, 17);
		lblNewLabel_1.setFont(FormUtil.getFontBold(12));
		innerPanel.add(lblNewLabel_1);

		editSobrenome = new JEditText("Lovelace");
		editSobrenome.setBounds(452, 159, 360, 30);
		FormUtil.formatName(editSobrenome);
		editSobrenome.setName(NAME_SOBRENOME);
		editSobrenome.setFont(FormUtil.getFontNormal(12));
		editSobrenome.setColumns(10);
		editSobrenome.setBorder(new RoundedBorder());
		editSobrenome.setBorder(FormUtil.getBorder(editSobrenome));
		innerPanel.add(editSobrenome);

		editTelefone = new JEditText(FormUtil.phoneNumberFormat());
		editTelefone.setBounds(20, 228, 220, 33);
		editTelefone.setName(NAME_TELEFONE);
		editTelefone.setFont(FormUtil.getFontNormal(12));
		editTelefone.setColumns(10);
		editTelefone.setBorder(new RoundedBorder());
		editTelefone.setBorder(FormUtil.getBorder(editTelefone));
		innerPanel.add(editTelefone);

		JLabel lblNewLabel_1_1 = new JLabel("Telefone:");
		lblNewLabel_1_1.setBounds(20, 208, 146, 17);
		lblNewLabel_1_1.setFont(FormUtil.getFontBold(12));
		innerPanel.add(lblNewLabel_1_1);

		editData = new JFormattedTextField(FormUtil.dateNumberFormat());
		editData.setBounds(303, 228, 200, 33);
		editData.setName(NAME_DATA_NASCIMENTO);
		editData.setHorizontalAlignment(SwingConstants.CENTER);
		editData.setFont(FormUtil.getFontNormal(12));
		editData.setColumns(10);
		editData.setBorder(new RoundedBorder());
		editData.setBorder(FormUtil.getBorder(editData));
		innerPanel.add(editData);

		JLabel lblNewLabel = new JLabel("Data de nascimento:");
		lblNewLabel.setBounds(303, 208, 200, 17);
		innerPanel.add(lblNewLabel);
		lblNewLabel.setFont(FormUtil.getFontBold(12));

		JLabel lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setBounds(562, 208, 250, 17);
		lblNewLabel_2.setFont(FormUtil.getFontBold(12));
		innerPanel.add(lblNewLabel_2);

		editCPF = new JFormattedTextField(FormUtil.cpfFormat());
		editCPF.setBounds(562, 228, 250, 33);
		editCPF.setName(NAME_CPF);
		editCPF.setFont(FormUtil.getFontNormal(12));
		editCPF.setColumns(10);
		editCPF.setBorder(new RoundedBorder());
		innerPanel.add(editCPF);

		chckbxPosition = new JCheckBox("Salvar na terceira posição");
		chckbxPosition.setBackground(new Color(255, 255, 255));
		chckbxPosition.setBounds(20, 281, 220, 23);
		chckbxPosition.setEnabled(false);
		chckbxPosition.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxPosition.setFont(FormUtil.getFontNormal(10));

		chckbxPosition.setIcon(FormUtil.getScaledImageIcon(this, "img/checkbox_unchecked.png", 14, 14));
		chckbxPosition.setSelectedIcon(FormUtil.getScaledImageIcon(this, "img/checkbox_checked.png", 14, 14));
		chckbxPosition
				.setDisabledIcon(FormUtil.getScaledImageIcon(this, "img/checkbox_unchecked_disabled.png", 16, 16));
		chckbxPosition.setDisabledSelectedIcon(
				FormUtil.getScaledImageIcon(this, "img/checkbox_checked_disabled.png", 16, 16));

		innerPanel.add(chckbxPosition);

		ImageIcon imgIcon = new ImageIcon(getClass().getResource("img/ic_ramdom.png"));
		Image image = FormUtil.getScaledImage(imgIcon.getImage(), 24, 24);

		JLabel textTitle = new JLabel("CADASTRAR ALUNO");
		textTitle.setBounds(20, 25, 156, 18);
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setFont(FormUtil.getFontBold());
		innerPanel.add(textTitle);

		StyledButton btnRandomMat = new StyledButton(true);
		btnRandomMat.setBounds(165, 94, 56, 33);
		btnRandomMat.setButtonColor(Color.white);
		btnRandomMat.setBackground(new Color(255, 255, 255));
		btnRandomMat.setIcon(new ImageIcon(image));
		btnRandomMat.setFocusPainted(false);
		innerPanel.add(btnRandomMat);

		btnCadastrar = new StyledButton("Cadastrar");
		btnCadastrar.setBounds(291, 362, 250, 40);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setFont(FormUtil.getFontBold(12));
		innerPanel.add(btnCadastrar);

		btnVoltar = new StyledButton("Voltar", true);
		btnVoltar.setBounds(291, 411, 250, 40);
		btnVoltar.setButtonColor(Color.white);
		btnVoltar.setFont(FormUtil.getFontBold(12));
		btnVoltar.setFocusPainted(false);
		innerPanel.add(btnVoltar);

		btnVoltar.addActionListener(this);
		btnCadastrar.addActionListener(this);

		btnRandomMat.addActionListener(e -> {
			editMatricula.setText(matriculaGerada());
		});

		updateCheckBox();
	}

	private String matriculaGerada() {
		StringBuilder sb = new StringBuilder();
		do {
			sb.append(new Random().nextInt(9 - 0 + 1));
			if (sb.length() == 7) {
				if (matriculaExistente(sb.toString())) {
					sb = new StringBuilder();
				}
			}
		} while (sb.length() < 7);
		return sb.toString();
	}

	private void clearText() {
		Component[] components = innerPanel.getComponents();
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
			if (backListener != null) {
				backListener.onClick(this);
			}
		}
	}

	private void cadastrarAluno() {
		String matricula = editMatricula.getText();
		String nome = editNome.getText();
		String sobrenome = editSobrenome.getText();
		String telefone = editTelefone.getText();
		String cpf = editCPF.getText();
		String dataNascimento = editData.getText();

		Aluno aluno = new Aluno(matricula, nome.concat(" ").concat(sobrenome), (int) getIdade(dataNascimento),
				dateParse(dataNascimento), telefone, cpf);

		addAluno(aluno);
		clearText();

		FormUtil.showSuccessMessage(this, "Cadastrado com sucesso!",
				String.format("<html><body><b>%s</b> foi cadastrado(a) com sucesso!</html></body>", aluno.getNome()));
	}

	private void addAluno(Aluno aluno) {
		frame.addNewAluno(aluno, chckbxPosition.isSelected());
		updateCheckBox();
	}

	private void updateCheckBox() {
		int size = frame.getAlunoList().size();
		if (size >= 3) {
			chckbxPosition.setEnabled(true);
		} else {
			chckbxPosition.setSelected(false);
			chckbxPosition.setEnabled(false);
		}
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
		List<String> matriculas = frame.getAlunoList().stream().map((aluno) -> aluno.getMatricula())
				.collect(Collectors.toList());
		for (String m : matriculas) {
			if (m == matricula) {
				return true;
			}
		}
		return false;
	}

	private boolean cpfExistente(String cpf) {
		// Percorre a lista de alunos e retorna uma lista de cpf
		List<String> matriculas = frame.getAlunoList().stream().map((aluno) -> aluno.getCPF())
				.collect(Collectors.toList());
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
		Component[] components = innerPanel.getComponents();
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
						FormUtil.showErrorMessage(this, "Matrícula inválida", "A matrícula deve ter 7 (sete) dígitos.");
						break;
					}
					if (matriculaExistente(text)) {
						isValid = false;
						FormUtil.showErrorMessage(this, "Matrícula já cadastrada",
								"Essa matricula já está cadastrada. Por favor, escolha outra.");
						break;
					}
					break;
				case NAME_DATA_NASCIMENTO:
					if (editData.getValue() == null) {
						isValid = false;
						FormUtil.showErrorMessage(this, "Data de nascimento inválida",
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
								FormUtil.showErrorMessage(this, "Data de nascimento inválida",
										"O mês digitado é inválido. Por favor, digite um mês válido.");
							} else if (e.getMessage().contains("Invalid value for Year")) {
								FormUtil.showErrorMessage(this, "Data de nascimento inválida",
										"O ano digitado é inválido. Por favor, digite um ano válido.");
							} else if (e.getMessage().contains("Invalid value for DayOfMonth")) {
								FormUtil.showErrorMessage(this, "Data de nascimento inválida",
										"O dia digitado é inválido. Por favor, digite um dia válido.");
							}
						}
					}
					break;
				case NAME_TELEFONE:
					if (editTelefone.getValue() == null) {
						isValid = false;
						FormUtil.showErrorMessage(this, "Número de telefone inválido",
								"Este número de telefone é inválido. Por favor, digite novamente");
						break;
					}
					break;
				case NAME_CPF:
					if (editCPF.getValue() == null) {
						isValid = false;
						FormUtil.showErrorMessage(this, "CPF inválido",
								"Este CPF é inválido. Por favor, digite novamente");
						break;
					}
					if (cpfExistente(text)) {
						isValid = false;
						FormUtil.showErrorMessage(this, "CPF já cadastrada",
								"Esse CPF já está cadastrado. É possível que esse aluno já esteja matriculado nesse sistema.");
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
					FormUtil.showErrorEmptyFieldMessage(this, "O campo " + field.getName() + " é obrigatório");
					break;
				}
			}
		}

		return isValid;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();

		Point p1 = new Point(10, 10);
		Point p2 = new Point(getWidth(), getHeight());
		final GradientPaint gp = new GradientPaint(p1, new Color(250, 175, 123), p2, new Color(248, 201, 107), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

	public void onBack(OnClickListener backListener) {
		this.backListener = backListener;

	}
}
