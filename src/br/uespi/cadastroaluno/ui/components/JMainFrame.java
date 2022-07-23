package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.util.EventObject;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import br.uespi.cadastroaluno.interfaces.OnItemMenuClickListener;
import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.ui.TelaListaCadastrado;
import br.uespi.cadastroaluno.utils.FormUtil;

public class JMainFrame extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;

	public static final int MENU_ITEM_NOVO_ESTUDANTE = 0;
	public static final int MENU_ITEM_REMOVER_UTLIMO_ESTUDANTE = 1;
	public static final int MENU_ITEM_OBTER_MATRICULA = 2;
	public static final int MENU_ITEM_OBTER_TERCEIRO_ALUNO = 3;
	public static final int MENU_ITEM_SALVAR_TUDO = 4;
	public static final int MENU_ITEM_CARREGAR_ARQUIVO = 5;

	private List<Aluno> alunoList;
	private JMenuBar menuBar;
	private JMenu menuOptions;
	private OnItemMenuClickListener menuItemClickListener;

	public JMainFrame() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		UIManager.put("MenuItem.background", Color.white);
		UIManager.put("MenuItem.selectionBackground", new Color(253, 239, 211));
		UIManager.put("MenuItem.selectionForeground", new Color(250, 112, 3));
		UIManager.put("OptionPane.messageFont", FormUtil.getFontNormal(12));
		UIManager.put("MenuItem.opaque", true);
		UIManager.put("MenuItem.font", FormUtil.getFontNormal(12));
		UIManager.put("MenuItem.borderPainted", false);
		UIManager.put("PopupMenu.opaque", true);
		UIManager.put("PopupMenu.border", new LineBorder(new Color(255, 255, 255), 10, true));
		UIManager.put("PopupMenu.background", new Color(255, 255, 255, 100));
		UIManager.put("MenuBar.opaque", true);

		menuBar = new StyledMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		menuBar.setOpaque(true);

		setJMenuBar(menuBar);

		menuOptions = new JMenu("Opções");
		menuOptions.getPopupMenu().setPreferredSize(new Dimension(300, 200));
		menuOptions.setFont(FormUtil.getFontBold(12));
		menuOptions.setBorderPainted(false);
		menuOptions.setBorder(new RoundedBorder(Color.black));
		menuBar.add(menuOptions);

		JMenuItem itemCadastrarAluno = new JMenuItem("Cadastrar novo aluno",
				FormUtil.getScaledImageIcon(this, "img/ic_menu_new_student.png", 18, 18));
		JMenuItem itemRemoverUltimo = new JMenuItem("Remover último aluno",
				FormUtil.getScaledImageIcon(this, "img/ic_menu_delete_student.png", 18, 18));
		JMenuItem itemObterMatricula = new JMenuItem("Obter matrícula",
				FormUtil.getScaledImageIcon(this, "img/ic_menu_get_mat.png", 18, 18));
		JMenuItem itemObterTerceiro = new JMenuItem("Obter terceiro aluno",
				FormUtil.getScaledImageIcon(this, "img/ic_menu_get_3.png", 18, 18));
		JMenuItem itemSalvarTudo = new JMenuItem("Salvar tudo",
				FormUtil.getScaledImageIcon(this, "img/ic_menu_save_all.png", 18, 18));
		JMenuItem itemCarregarCSV = new JMenuItem("Carregar arquivo",
				FormUtil.getScaledImageIcon(this, "img/ic_menu_open_file.png", 18, 18));

		itemCadastrarAluno.addMouseListener(this);
		itemRemoverUltimo.addMouseListener(this);
		itemObterMatricula.addMouseListener(this);
		itemObterTerceiro.addMouseListener(this);
		itemSalvarTudo.addMouseListener(this);
		itemCarregarCSV.addMouseListener(this);

		itemCadastrarAluno.addActionListener(this);
		itemRemoverUltimo.addActionListener(this);
		itemObterMatricula.addActionListener(this);
		itemObterTerceiro.addActionListener(this);
		itemSalvarTudo.addActionListener(this);
		itemCarregarCSV.addActionListener(this);

		menuOptions.add(itemCadastrarAluno);
		menuOptions.add(itemRemoverUltimo);
		menuOptions.add(itemObterMatricula);
		menuOptions.add(itemObterTerceiro);
		menuOptions.add(itemSalvarTudo);
		menuOptions.add(itemCarregarCSV);

		alunoList = new ArrayList<Aluno>();

		updateOlderNewer();
		updateMenuItem();
	}

	private void updateMenuItem() {
		menuOptions.getItem(3).setEnabled(alunoList.size() > 2);
	}

	@Override
	public void setContentPane(Container contentPane) {
		super.setContentPane(contentPane);
		updateMenuVisibility();
	}

	public void goToScreen(Container contentPane) {
		getContentPane().setVisible(false);
		setContentPane(contentPane);
		contentPane.setVisible(true);
		updateMenuVisibility();
	}

	public void updateMenuVisibility() {
		menuBar.setVisible(getContentPane() instanceof TelaListaCadastrado && !alunoList.isEmpty());
	}

	public List<Aluno> getAlunoList() {
		return alunoList;
	}

	public void addNewAluno(Aluno aluno, boolean terPosition) {
		if (terPosition) {
			this.alunoList.add(2, aluno);
		} else {
			this.alunoList.add(aluno);
		}
		updateMenuItem();
		updateOlderNewer();
	}

	public void deleteAluno(Aluno alunoSelecionado) {
		this.alunoList.remove(alunoSelecionado);
		updateOlderNewer();
		updateMenuItem();
	}

	private void updateOlderNewer() {
		if (alunoList.size() > 1) {
			int max = maiorIdade();
			int min = menorIdade();
			alunoList.forEach((aluno) -> {
				aluno.setMaisVelho(false);
				aluno.setMaisNovo(false);
				int idade = aluno.getIdade();
				if (idade == max) {
					aluno.setMaisVelho(true);
				}
				if (idade == min) {
					aluno.setMaisNovo(true);
				}
			});
		} else {
			alunoList.forEach((aluno) -> {
				aluno.setMaisVelho(false);
				aluno.setMaisNovo(false);
			});
		}

	}

	private int maiorIdade() {
		int max = alunoList.get(0).getIdade();
		for (int i = 1; i < alunoList.size(); i++) {
			if (alunoList.get(i).getIdade() > max) {
				max = alunoList.get(i).getIdade();
			}
		}

		return max;
	}

	private int menorIdade() {
		int min = alunoList.get(0).getIdade();
		for (int i = 1; i < alunoList.size(); i++) {
			if (alunoList.get(i).getIdade() < min) {
				min = alunoList.get(i).getIdade();
			}
		}

		return min;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		updateMenuItemIcon(e, true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		updateMenuItemIcon(e, false);
	}

	private void updateMenuItemIcon(EventObject e, boolean isSelected) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) e.getSource();
			if (e.getSource() == menuOptions.getItem(0)) {
				configMenuItemIcon(menuItem, "ic_menu_new_student", isSelected);
			} else if (e.getSource() == menuOptions.getItem(1)) {
				configMenuItemIcon(menuItem, "ic_menu_delete_student", isSelected);
			} else if (e.getSource() == menuOptions.getItem(2)) {
				configMenuItemIcon(menuItem, "ic_menu_get_mat", isSelected);
			} else if (e.getSource() == menuOptions.getItem(3)) {
				configMenuItemIcon(menuItem, "ic_menu_get_3", isSelected);
			} else if (e.getSource() == menuOptions.getItem(4)) {
				configMenuItemIcon(menuItem, "ic_menu_save_all", isSelected);
			} else if (e.getSource() == menuOptions.getItem(5)) {
				configMenuItemIcon(menuItem, "ic_menu_open_file", isSelected);
			}

		}
	}

	private void configMenuItemIcon(JMenuItem item, String iconName, boolean isSelected) {
		String path = isSelected ? "_selected.png" : ".png";
		item.setIcon(FormUtil.getScaledImageIcon(JMainFrame.this, "img/" + iconName + path, 18, 18));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateMenuItemIcon(e, false);
		if (menuItemClickListener != null) {
			if (e.getSource() == menuOptions.getItem(0)) {
				// CADATRAR NOVO ALUNO
				menuItemClickListener.onItemClick(MENU_ITEM_NOVO_ESTUDANTE);
			} else if (e.getSource() == menuOptions.getItem(1)) {
				// REMOVER ULTIMO ALUNO
				menuItemClickListener.onItemClick(MENU_ITEM_REMOVER_UTLIMO_ESTUDANTE);
			} else if (e.getSource() == menuOptions.getItem(2)) {
				// OBTER MATRICULA
				menuItemClickListener.onItemClick(MENU_ITEM_OBTER_MATRICULA);
			} else if (e.getSource() == menuOptions.getItem(3)) {
				// OBTER TERCEIRO ALUNO
				menuItemClickListener.onItemClick(MENU_ITEM_OBTER_TERCEIRO_ALUNO);
			} else if (e.getSource() == menuOptions.getItem(4)) {
				// CARRAGAR ARQUIVO]
				menuItemClickListener.onItemClick(MENU_ITEM_SALVAR_TUDO);
			} else if (e.getSource() == menuOptions.getItem(5)) {
				menuItemClickListener.onItemClick(MENU_ITEM_CARREGAR_ARQUIVO);
			} else {
				menuItemClickListener.onItemClick(-1);
			}
		}

	}

	public void setMenuItemClickListener(OnItemMenuClickListener menuItemClickListener) {
		this.menuItemClickListener = menuItemClickListener;
	}

}
