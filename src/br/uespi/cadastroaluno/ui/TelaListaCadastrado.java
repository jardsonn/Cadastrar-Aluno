package br.uespi.cadastroaluno.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ListUI;

import br.uespi.cadastroaluno.interfaces.OnClickListener;
import br.uespi.cadastroaluno.interfaces.OnItemMenuClickListener;
import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.ui.components.JMainFrame;
import br.uespi.cadastroaluno.ui.components.JPanelCard;
import br.uespi.cadastroaluno.ui.components.JSearchTextField;
import br.uespi.cadastroaluno.ui.components.JScrollPanelCard;
import br.uespi.cadastroaluno.ui.components.RoundedBorder;
import br.uespi.cadastroaluno.ui.components.StyledButton;
import br.uespi.cadastroaluno.utils.FormUtil;
import br.uespi.cadastroaluno.utils.MenuOptionsHelper;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.JTextPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

public class TelaListaCadastrado extends JPanel implements OnItemMenuClickListener {

	private static final long serialVersionUID = -4139369237312199337L;

	private final List<Aluno> alunoList;
	private final JMainFrame frame;

	private OnClickListener cadastrarClickListener;

	private JButton btnCadastrar;

	private JLabel lblAlunoTotal;
	private JLabel textMatriculaSelecionado;
	private JLabel textNomeSelecionado;
	private JLabel textDataSelecionado;
	private JLabel textTelefoneSelecionado;
	private JLabel textCpfSelecionado;
	private StyledButton btnSalvar;
	private StyledButton btnDelete;
	private JPanel infoPanel;
	private JPanelCard notFoundPanel;

	private JList<Aluno> list;
	private DefaultListModel<Aluno> listModel;

	private Dimension screenSize;
	private JSearchTextField editFindStudent;

	private MenuOptionsHelper menuHelper;

	public TelaListaCadastrado(JMainFrame frame) {
		this.frame = frame;
		alunoList = frame.getAlunoList();
		JFrame jfarme = new JFrame();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jfarme.setBounds(0, 0, screenSize.width, screenSize.height);
		jfarme.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jfarme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfarme.getContentPane().add(this);

		frame.setMenuItemClickListener(this);

		menuHelper = new MenuOptionsHelper(this);

		initialize();
	}

	public JMainFrame getMainFrame() {
		return frame;
	}

	@Override
	public void setVisible(boolean b) {
		removeAll();
		frame.revalidate();
		frame.repaint();
		super.setVisible(b);
		initialize();
	}

	public void updateScreen(boolean isEmpty) {
		removeAll();
		if (isEmpty) {
			add(panelEmptyList());
		} else {
			add(panelList());
		}
		frame.revalidate();
		frame.repaint();

		// add(panelList());
//		add(panelEmptyList());
	}

	private void initialize() {
		setLayout(null);
		updateScreen(alunoList.isEmpty());
	}

	private JPanel panelList() {
		JPanel mainPanelList = new JPanel();

		mainPanelList.setBounds(0, 0, screenSize.width, screenSize.height);
		mainPanelList.setBackground(new Color(240, 240, 240));

		listModel = new DefaultListModel<Aluno>();
		mainPanelList.setLayout(null);
		list = new JList<Aluno>(listModel);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		list.setSelectionBackground(new Color(248, 201, 107, 30));
		list.setSelectionForeground(new Color(250, 112, 3));
		list.setBorder(new LineBorder(Color.white, 10, true));

		list.setFont(FormUtil.getFontNormal(12));
		list.setFixedCellHeight(45);
		list.setFixedCellWidth(100);
		list.setBorder(FormUtil.getBorder(list, 20, 0, 0, 0));

		int marginRight = 20;
		int marginBottom = 50;
		int width = 418;
		int height = 580;
		int xPosition = ((screenSize.width / 2) - width) + marginRight;
		int yPosition = (screenSize.height / 2) - (height / 2) - marginBottom;

		JPanelCard panelList = new JPanelCard();
		panelList.setBounds(xPosition, yPosition, width, height);
		mainPanelList.add(panelList);
		panelList.setLayout(null);

		JLabel labelNameList = new JLabel("ALUNOS");
		labelNameList.setBounds(21, 22, 115, 14);
		labelNameList.setFont(FormUtil.getFontBold(14));
		panelList.add(labelNameList);

		JScrollPanelCard scrollPane = new JScrollPanelCard();
		scrollPane.setBounds(0, 79, 418, 501);
		scrollPane.setViewportView(list);
		scrollPane.setBorder(new LineBorder(Color.white, 10, true));
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
		list.setCellRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				if (isSelected) {
					listCellRendererComponent.setBorder(new RoundedBorder(new Color(250, 112, 50, 20)));
					listCellRendererComponent.setFont(FormUtil.getFontBold(12));
				} else
					listCellRendererComponent.setBorder(new RoundedBorder(new Color(255, 255, 255, 0)));
				return listCellRendererComponent;
			}

		});
		panelList.add(scrollPane);

		editFindStudent = new JSearchTextField();
		editFindStudent.setLocation(21, 46);
		editFindStudent.setSize(366, 30);
		editFindStudent.setHorizontalAlignment(SwingConstants.LEFT);
		editFindStudent.setPlaceholder("Buscar aluno");
		editFindStudent.setFont(FormUtil.getFontNormal(12));
		editFindStudent.setBorder(new RoundedBorder());
		editFindStudent.setColumns(10);
		panelList.add(editFindStudent);

		setupPanelNotFound(mainPanelList);
		setupPanelInfo(mainPanelList);

		for (Aluno aluno : alunoList) {
			adicionarNaLista(aluno);
		}

		setInfoVisibity(false);

		list.getSelectionModel().addListSelectionListener(e -> {
			setInfoVisibity(true);
			Aluno aluno = list.getSelectedValue();
			if (aluno != null) {
				textMatriculaSelecionado.setText(aluno.getMatricula());
				textNomeSelecionado.setText(aluno.getNome());
				textDataSelecionado.setText(FormUtil.dateToString(aluno.getDataNascimento())
						.concat(String.format(" (%d anos de idade)", aluno.getIdade())));
				textTelefoneSelecionado.setText(aluno.getTelefone());
				textCpfSelecionado.setText(aluno.getCPF());
			}

		});

		setupButtonsListeners(list);
		list.setSelectedIndex(0);

		lblAlunoTotal = new JLabel("Total:");
		lblAlunoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlunoTotal.setForeground(new Color(180, 180, 180));
		lblAlunoTotal.setBounds(312, 24, 75, 14);
		lblAlunoTotal.setFont(FormUtil.getFontBold(10));
		panelList.add(lblAlunoTotal);
		setupBusca();
		updateInfoPanel(!alunoList.isEmpty());

//		updateAlunoTotal();

		return mainPanelList;
	}

	private void setupPanelInfo(JPanel mainPanelList) {
		int marginLeft = 20;
		int marginBottom = 50;
		int width = 418;
		int height = 580;
		int xPosition = (screenSize.width) - (width + width / 2) - marginLeft;
		int yPosition = (screenSize.height / 2) - (height / 2) - marginBottom;

		infoPanel = new JPanelCard();
		infoPanel.setBounds(xPosition, yPosition, width, height);

		infoPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(FormUtil.getFontBold(12));
		lblNome.setBounds(20, 55, 43, 16);
		infoPanel.add(lblNome);

		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(FormUtil.getFontBold(12));
		lblMatricula.setBounds(20, 100, 66, 16);
		infoPanel.add(lblMatricula);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setFont(FormUtil.getFontBold(12));
		lblDataDeNascimento.setBounds(20, 150, 135, 16);
		infoPanel.add(lblDataDeNascimento);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(FormUtil.getFontBold(12));
		lblTelefone.setBounds(20, 250, 62, 16);
		infoPanel.add(lblTelefone);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(FormUtil.getFontBold(12));
		lblCpf.setBounds(20, 200, 31, 16);
		infoPanel.add(lblCpf);

		textMatriculaSelecionado = new JLabel("0123456");
		textMatriculaSelecionado.setFont(FormUtil.getFontNormal(12));
		textMatriculaSelecionado.setBounds(20, 120, 289, 16);
		infoPanel.add(textMatriculaSelecionado);

		textNomeSelecionado = new JLabel("Individuo");
		textNomeSelecionado.setFont(FormUtil.getFontNormal(12));
		textNomeSelecionado.setBounds(20, 70, 289, 16);
		infoPanel.add(textNomeSelecionado);

		textDataSelecionado = new JLabel("04/01/2012 (14 anos de idade)");
		textDataSelecionado.setFont(FormUtil.getFontNormal(12));
		textDataSelecionado.setBounds(20, 170, 289, 16);
		infoPanel.add(textDataSelecionado);

		textTelefoneSelecionado = new JLabel("0000000000");
		textTelefoneSelecionado.setFont(FormUtil.getFontNormal(12));
		textTelefoneSelecionado.setBounds(20, 270, 289, 16);
		infoPanel.add(textTelefoneSelecionado);

		textCpfSelecionado = new JLabel("000000000-00");
		textCpfSelecionado.setFont(FormUtil.getFontNormal(12));
		textCpfSelecionado.setBounds(20, 220, 289, 16);
		infoPanel.add(textCpfSelecionado);

		btnSalvar = new StyledButton("Salvar", true);
		btnSalvar.setForeground(new Color(0, 189, 121));
		btnSalvar.setBounds(20, 525, 100, 40);
		btnSalvar.setBorderColor(new Color(0, 189, 121));
		btnSalvar.setButtonColor(Color.white);
		btnSalvar.setFocusPainted(false);
		btnSalvar.setFont(FormUtil.getFontBold(12));
		infoPanel.add(btnSalvar);

		btnDelete = new StyledButton("Excluir", true);
		btnDelete.setBorderColor(new Color(255, 0, 46));
		btnDelete.setButtonColor(new Color(255, 0, 46), new Color(200, 0, 46));
		btnDelete.setForeground(Color.white);
		btnDelete.setBounds(296, 525, 100, 40);
		btnDelete.setFocusPainted(false);
		btnDelete.setFont(FormUtil.getFontBold(12));
		infoPanel.add(btnDelete);

		mainPanelList.add(infoPanel);
	}

	private void updateInfoPanel(boolean isSelected) {
		infoPanel.setVisible(isSelected);
		notFoundPanel.setVisible(!isSelected);
		updateAlunoTotal();
	}

	private void setupPanelNotFound(JPanel mainPanelList) {
		int marginLeft = 20;
		int marginBottom = 50;
		int width = 418;
		int height = 580;
		int xPosition = (screenSize.width) - (width + width / 2) - marginLeft;
		int yPosition = (screenSize.height / 2) - (height / 2) - marginBottom;

		notFoundPanel = new JPanelCard();
		notFoundPanel.setBounds(xPosition, yPosition, width, height);
		notFoundPanel.setLayout(null);

		JLabel imgNoNotfound = new JLabel();
		imgNoNotfound.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon imgIcon = new ImageIcon(getClass().getResource("img/illustration_not_found.png"));
		Image image = FormUtil.getScaledImage(imgIcon.getImage(), 406, 390);

		imgNoNotfound.setIcon(new ImageIcon(image));
		imgNoNotfound.setBounds(6, 11, 406, 390);
		notFoundPanel.add(imgNoNotfound);

		JLabel labelNoData = new JLabel("Sem resultado!");
		labelNoData.setHorizontalAlignment(SwingConstants.CENTER);
		labelNoData.setBounds(0, 424, 418, 32);
		labelNoData.setFont(FormUtil.getFontBold(16));
		labelNoData.setForeground(new Color(130, 130, 130));
		notFoundPanel.add(labelNoData);

		JLabel noDataMessage = new JLabel(
				"<html><body><p style='text-align: center;'>Nenhum aluno encontrado. Por favor, tente novamente com outro <b>nome</b> ou <b>matrícula</b>.</p></body></html>");

		noDataMessage.setHorizontalAlignment(SwingConstants.CENTER);
		noDataMessage.setFont(FormUtil.getFontNormal(14));
		noDataMessage.setBounds(0, 460, 418, 55);
		noDataMessage.setForeground(new Color(130, 130, 130));
		notFoundPanel.add(noDataMessage);

		mainPanelList.add(notFoundPanel);
	}

	private void setupBusca() {
		editFindStudent.setStudentsList(alunoList);
		editFindStudent.setSearchStudentsListener(alunosEncontados -> {
			listModel.clear();
			for (Aluno aluno : alunosEncontados) {
				listModel.addElement(aluno);
			}
			updateInfoPanel(!alunosEncontados.isEmpty());
			list.setSelectedIndex(0);
		});
	}

	private void adicionarNaLista(Aluno aluno) {

		listModel.addElement(aluno);
	}

	private void setupButtonsListeners(JList<Aluno> list) {
		btnSalvar.addActionListener(e -> {
			Aluno alunoSelecionado = list.getSelectedValue();
			menuHelper.saveStudent(alunoSelecionado, alunoSelecionado.getNome());
		});

		btnDelete.addActionListener(e -> {
			Aluno alunoSelecionado = list.getSelectedValue();
			DefaultListModel<Aluno> model = (DefaultListModel<Aluno>) list.getModel();
			FormUtil.deleteWithMessageDialog(this, alunoSelecionado, aluno -> {
				model.removeElement(alunoSelecionado);
				frame.deleteAluno(alunoSelecionado);

				boolean isEmpty = frame.getAlunoList().isEmpty();
				setInfoVisibity(!isEmpty);
				updateScreen(isEmpty);
			});

		});
	}

	public JList<Aluno> getStudentsList() {
		return list;
	}

	private JPanel panelEmptyList() {
		JPanel panelEmptyList = new JPanel();
		panelEmptyList.setBackground(new Color(250, 250, 250));
		panelEmptyList.setBounds(0, 0, screenSize.width, screenSize.height);

		JLabel imgNoData = new JLabel();
		imgNoData.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon imgIcon = new ImageIcon(getClass().getResource("img/illustration_no_register.png"));
		Image image = FormUtil.getScaledImage(imgIcon.getImage(), 600, 400);
		panelEmptyList.setLayout(null);
		imgNoData.setIcon(new ImageIcon(image));
		imgNoData.setBounds(383, 28, 600, 400);
		panelEmptyList.add(imgNoData);

		JLabel labelNoStudent = new JLabel("Sem Aluno!");
		labelNoStudent.setHorizontalAlignment(SwingConstants.CENTER);
		labelNoStudent.setBounds(383, 424, 600, 32);
		labelNoStudent.setFont(FormUtil.getFontBold(32));
		labelNoStudent.setForeground(new Color(180, 180, 180));
		panelEmptyList.add(labelNoStudent);

		JLabel emptyListMessage = new JLabel(
				"<html><body><p style='text-align: center;'> Nenhum aluno foi cadastrado, clique no botão abaixo<br>para matricular</p></body></html>");

		emptyListMessage.setHorizontalAlignment(SwingConstants.CENTER);
		emptyListMessage.setFont(FormUtil.getFontBold(14));
		emptyListMessage.setBounds(383, 460, 600, 77);
		emptyListMessage.setForeground(new Color(180, 180, 180));
		panelEmptyList.add(emptyListMessage);

		btnCadastrar = new StyledButton("Cadastrar aluno");
		btnCadastrar.setBounds(558, 591, 250, 40);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setFont(FormUtil.getFontBold(12));
		panelEmptyList.add(btnCadastrar);

		btnCadastrar.addActionListener(e -> {
			if (cadastrarClickListener != null) {
				cadastrarClickListener.onClick(this);
			}
		});

		frame.updateMenuVisibility();
		return panelEmptyList;
	}

	public void setNewStudent(OnClickListener cadastrarClickListener) {
		this.cadastrarClickListener = cadastrarClickListener;
	}

	public void setInfoVisibity(boolean isVisible) {
		infoPanel.setVisible(isVisible);
	}

	@Override
	public void onItemClick(int item) {
		switch (item) {
		case JMainFrame.MENU_ITEM_NOVO_ESTUDANTE:
			if (cadastrarClickListener != null) {
				cadastrarClickListener.onClick(this);
			}
			break;

		case JMainFrame.MENU_ITEM_REMOVER_UTLIMO_ESTUDANTE:
			menuHelper.deleteLastStudent(this);
			break;

		case JMainFrame.MENU_ITEM_OBTER_MATRICULA:

			break;

		case JMainFrame.MENU_ITEM_OBTER_TERCEIRO_ALUNO:
			Aluno terceiroAluno = menuHelper.getThirdStudent();
			int index = alunoList.indexOf(terceiroAluno);
			list.setSelectedIndex(index);
//			list.setSelectedIndex(2);

			break;

		case JMainFrame.MENU_ITEM_SALVAR_TUDO:
			menuHelper.saveAllStudents("listagem_alunos");
			break;

		case JMainFrame.MENU_ITEM_CARREGAR_ARQUIVO:

			break;

		}

	}

	private void updateAlunoTotal() {
		lblAlunoTotal.setText(String.format("Total: %d", frame.getAlunoList().size()));
	}
}
