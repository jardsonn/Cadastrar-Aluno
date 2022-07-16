package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import br.uespi.cadastroaluno.interfaces.SearchStudentsListener;
import br.uespi.cadastroaluno.model.Aluno;

public class JPlaceholderTextField extends JTextField implements DocumentListener {

	private String placeholder;

	private List<Aluno> studentsFound;
	private List<Aluno> studentsList;

	private SearchStudentsListener studentListener;

	public JPlaceholderTextField() {
		getDocument().addDocumentListener(this);
	}

	public JPlaceholderTextField(final String placeholder) {
		this.placeholder = placeholder;
		getDocument().addDocumentListener(this);
	}

	public JPlaceholderTextField(List<Aluno> studentsList) {
		this.studentsList = studentsList;
		getDocument().addDocumentListener(this);
	}

	public JPlaceholderTextField(final String placeholder, List<Aluno> studentsList) {
		this.studentsList = studentsList;
		this.placeholder = placeholder;
		getDocument().addDocumentListener(this);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(180, 180, 180));
		g.drawString(placeholder, getInsets().left, (this.getHeight() / 2) + pG.getFontMetrics().getMaxAscent() / 2);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (studentsList != null) {
			String text = getText().trim();
			List<Aluno> studentsFoundList = new ArrayList();
			for (Aluno aluno : studentsList) {
				if (aluno.getNome().contains(text) || aluno.getMatricula() == text) {
					studentsFoundList.add(aluno);
				}
			}
			if (studentListener != null) {
				studentListener.onStudentsFound(studentsFoundList);
			}
		}

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (studentsList != null) {
			String text = getText().trim();
			List<Aluno> studentsFoundList = new ArrayList();
			for (Aluno aluno : studentsList) {
				if (aluno.getNome().contains(text) || aluno.getMatricula() == text) {
					studentsFoundList.add(aluno);
				}
			}
			if (studentListener != null) {
				studentListener.onStudentsFound(studentsFoundList);
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	//	System.out.println("changedUpdate " + getText());

	}

	public List<Aluno> getStudentsFound() {
		return studentsFound;
	}

	public void setStudentsFound(List<Aluno> studentsFound) {
		this.studentsFound = studentsFound;
	}

	public List<Aluno> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Aluno> studentsList) {
		this.studentsList = studentsList;
	}

	public void setSearchStudentsListener(SearchStudentsListener studentListener) {
		this.studentListener = studentListener;
	}

}
