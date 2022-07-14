package br.uespi.cadastroaluno.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ListUI;

import br.uespi.cadastroaluno.interfaces.OnClickListener;
import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.utils.FormUtil;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;

public class TelaListaCadastrado extends JPanel {

	private static final long serialVersionUID = -4139369237312199337L;

	private final List<Aluno> alunoList;
	private final JMainFrame frame;

	private OnClickListener cadastrarClickListener;

	private JButton btnCadastrar;

	JLabel textMatriculaSelecionado;
	JLabel textNomeSelecionado;
	JLabel textDataSelecionado;
	JLabel textTelefoneSelecionado;
	JLabel textCpfSelecionado;
	JButton btnSalvar;
	JButton btnDelete;
	private JPanel infoPanel;

	public TelaListaCadastrado(JMainFrame frame) {
		this.frame = frame;
		alunoList = frame.getAlunoList();
		initialize();
	}

	@Override
	public void setVisible(boolean b) {
		removeAll();
		frame.revalidate();
		frame.repaint();
		super.setVisible(b);
		initialize();
	}

	private void updateScreen(boolean isEmpty) {
		removeAll();
		if (isEmpty) {
			add(panelEmptyList());
		} else {
			add(panelList());
		}
		frame.revalidate();
		frame.repaint();
	}

	private void initialize() {
		setLayout(null);
		updateScreen(alunoList.isEmpty());
	}

	private JPanel panelList() {
		JPanel mainPanelList = new JPanel();
		mainPanelList.setBounds(0, 0, 720, 485);

		DefaultListModel<Aluno> listModel = new DefaultListModel<Aluno>();
		mainPanelList.setLayout(null);
		JList<Aluno> list = new JList<Aluno>(listModel);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		list.setSelectionBackground(new Color(204, 0, 51, 24));
		list.setSelectionForeground(new Color(204, 0, 51, 255));

		list.setFont(FormUtil.getFontNormal(12));
		// list.setBounds(20, 66, 360, 408);
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(100);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 360, 415);
		scrollPane.setViewportView(list);
		// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanelList.add(scrollPane);

		infoPanel = new JPanel();
		infoPanel.setBounds(395, 20, 300, 415);

		/*
		 * for(int i = 0; i< 50; i++) { frame.addNewAluno(new
		 * Aluno("145447"+i,"Jardson Costa"+i, 2*i, new Date(), "98 984898889",
		 * "618.054.747-5"+i), i==8); }
		 */

		for (Aluno aluno : alunoList) {
			listModel.addElement(aluno);
		}
		
		infoPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(FormUtil.getFontBold(12));
		lblNome.setBounds(0, 5, 43, 16);
		infoPanel.add(lblNome);

		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(FormUtil.getFontBold(12));
		lblMatricula.setBounds(0, 50, 66, 16);
		infoPanel.add(lblMatricula);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setFont(FormUtil.getFontBold(12));
		lblDataDeNascimento.setBounds(0, 100, 135, 16);
		infoPanel.add(lblDataDeNascimento);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(FormUtil.getFontBold(12));
		lblTelefone.setBounds(0, 200, 62, 16);
		infoPanel.add(lblTelefone);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(FormUtil.getFontBold(12));
		lblCpf.setBounds(0, 150, 31, 16);
		infoPanel.add(lblCpf);

		textMatriculaSelecionado = new JLabel("0123456");
		textMatriculaSelecionado.setFont(FormUtil.getFontNormal(12));
		textMatriculaSelecionado.setBounds(0, 70, 289, 16);
		infoPanel.add(textMatriculaSelecionado);

		textNomeSelecionado = new JLabel("Individuo");
		textNomeSelecionado.setFont(FormUtil.getFontNormal(12));
		textNomeSelecionado.setBounds(0, 20, 289, 16);
		infoPanel.add(textNomeSelecionado);

		textDataSelecionado = new JLabel("04/01/2012 (14 anos de idade)");
		textDataSelecionado.setFont(FormUtil.getFontNormal(12));
		textDataSelecionado.setBounds(0, 120, 289, 16);
		infoPanel.add(textDataSelecionado);

		textTelefoneSelecionado = new JLabel("0000000000");
		textTelefoneSelecionado.setFont(FormUtil.getFontNormal(12));
		textTelefoneSelecionado.setBounds(0, 220, 289, 16);
		infoPanel.add(textTelefoneSelecionado);

		textCpfSelecionado = new JLabel("000000000-00");
		textCpfSelecionado.setFont(FormUtil.getFontNormal(12));
		textCpfSelecionado.setBounds(0, 170, 289, 16);
		infoPanel.add(textCpfSelecionado);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setBounds(0, 388, 89, 23);
		btnSalvar.setFocusPainted(false);
		btnSalvar.setBackground(new Color(0, 191, 76));
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setFont(FormUtil.getFontBold(12));
		infoPanel.add(btnSalvar);

		btnDelete = new JButton("Excluir");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(205, 388, 89, 23);
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(new Color(255, 0, 51));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(FormUtil.getFontBold(12));
		infoPanel.add(btnDelete);

		mainPanelList.add(infoPanel);

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

		return mainPanelList;
	}

	private void setupButtonsListeners(JList<Aluno> list) {
		btnSalvar.addActionListener(e -> {
			Aluno alunoSelecionado = list.getSelectedValue();

		});

		btnDelete.addActionListener(e -> {
			Aluno alunoSelecionado = list.getSelectedValue();
			DefaultListModel<Aluno> model = (DefaultListModel<Aluno>) list.getModel();
			model.removeElement(alunoSelecionado);
			frame.deleteAluno(alunoSelecionado);
			boolean isEmpty = frame.getAlunoList().isEmpty();
			setInfoVisibity(!isEmpty);
			updateScreen(isEmpty);
		});
	}

	private JPanel panelEmptyList() {
		JPanel panelEmptyList = new JPanel();
		panelEmptyList.setBounds(0, 0, 720, 485);

		JLabel imgNoData = new JLabel();
		imgNoData.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon imgIcon = new ImageIcon(getClass().getResource("img/illustration_no_data.png"));
		Image image = FormUtil.getScaledImage(imgIcon.getImage(), 320, 250);
		panelEmptyList.setLayout(null);
		imgNoData.setIcon(new ImageIcon(image));
		imgNoData.setBounds(200, 5, 320, 250);
		panelEmptyList.add(imgNoData);

		JLabel emptyListMessage = new JLabel(
				"Nenhum aluno registrado, você pode cadastrar alunos clicando no botão abaixo.");
		emptyListMessage.setHorizontalAlignment(SwingConstants.CENTER);
		emptyListMessage.setFont(FormUtil.getFontBold(12));
		emptyListMessage.setBounds(97, 264, 526, 16);
		panelEmptyList.add(emptyListMessage);

		btnCadastrar = new JButton("Cadastrar aluno");
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.setBounds(260, 401, 200, 30);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setBackground(new Color(255, 0, 51));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setFont(FormUtil.getFontBold(12));
		panelEmptyList.add(btnCadastrar);

		btnCadastrar.addActionListener(e -> {
			if (cadastrarClickListener != null) {
				cadastrarClickListener.onClick(this);
			}
		});

		return panelEmptyList;
	}

	public void setNewStudent(OnClickListener cadastrarClickListener) {
		this.cadastrarClickListener = cadastrarClickListener;
	}

	private void setInfoVisibity(boolean isVisible) {
		infoPanel.setVisible(isVisible);
	}
}
