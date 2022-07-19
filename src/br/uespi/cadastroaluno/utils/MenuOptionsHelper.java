package br.uespi.cadastroaluno.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.uespi.cadastroaluno.interfaces.OnClickListener;
import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.model.PrimeiraUltimaMatricula;
import br.uespi.cadastroaluno.ui.TelaListaCadastrado;
import br.uespi.cadastroaluno.ui.components.JMainFrame;

public class MenuOptionsHelper {

	private TelaListaCadastrado jpanel;
	private JMainFrame mainJFrame;

	public MenuOptionsHelper(TelaListaCadastrado jpanel) {
		this.jpanel = jpanel;
		mainJFrame = jpanel.getMainFrame();

	}

	public void goToRegistrationScreen(OnClickListener listener) {
		if (listener != null) {
			listener.onClick(jpanel);
		}
	}

	public void deleteLastStudent(JPanel panel) {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		DefaultListModel<Aluno> model = (DefaultListModel<Aluno>) jpanel.getStudentsList().getModel();
		Aluno lastStudent = mainJFrame.getAlunoList().get(studentList.size() - 1);
		FormUtil.deleteWithMessageDialog(panel, lastStudent, aluno -> {
			model.removeElement(aluno);
			mainJFrame.deleteAluno(aluno);
			boolean isEmpty = studentList.isEmpty();
			jpanel.setInfoVisibity(!isEmpty);
			jpanel.updateScreen(isEmpty);			
		});

	}

	public PrimeiraUltimaMatricula getRegistrationFirstLast() {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		Aluno firstStudent = mainJFrame.getAlunoList().get(0);
		Aluno lastStudent = mainJFrame.getAlunoList().get(studentList.size() - 1);
		return new PrimeiraUltimaMatricula()
				.setPrimeiro(firstStudent.getMatricula())
				.setUltimo(lastStudent.getMatricula());
	}

	public Aluno getThirdStudent() {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		// PERGUNTAR PARA O PROFESSOR SE É O TERCEIRO ELEMENTO DA LISTA OU O ELEMENTO DE
		// INDICE 3
		return studentList.get(2);
	}

	public void saveStudent(Aluno aluno, String fileName) {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		List<Aluno> oneStudent = new ArrayList<Aluno>();
		oneStudent.add(studentList.get(studentList.indexOf(aluno)));
		saveAllStudents(oneStudent, fileName);
	}

	public void saveAllStudents(String fileName) {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		saveAllStudents(studentList, fileName);
	}

	public void saveAllStudents(List<Aluno> studentList, String fileName) {
		String path = fileName.concat(".csv");
		File studentFolder = new File("Alunos Salvos/");

		String msgError;
		String msgSuccess;
		if (studentList.size() == 1) {
			msgError = "Infelizmente não foi possível salvar esse(a) aluno(a) em um arquivo!";
			msgSuccess = "<html><body>Aluno(a) foi salvo(a) com sucesso.<br/>Salvo em: <b>%s</b></html></body>";
		} else {
			msgError = "Infelizmente não foi possível salvar essa lista de alunos em um arquivo!";
			msgSuccess = "<html><body>Os alunos foram salvos com sucesso.<br/>Salvo em: <b>%s</b></html></body>";
		}
		
		if(studentList.isEmpty()) {
			FormUtil.showErrorMessage(jpanel, "Ocorreu um erro!", "A lista de alunos não pode está vazia!");
			return;
		}

		if (!studentFolder.exists()) {
			boolean saved = studentFolder.mkdirs();
			if (!saved) {
				FormUtil.showErrorMessage(jpanel, "Ocorreu um erro!", msgError);
				return;
			}
		}

		File file = new File(studentFolder, path);
		FileWriter fw = null;
		boolean fileSaved = false;
		try {
			fw = new FileWriter(file, true);
			for (Aluno aluno : studentList) {
				String currentLine = formatLineCSV(aluno);
				fw.write(currentLine);
			}
			fileSaved = true;

			fw.close();
		} catch (IOException e) {
			fileSaved = false;
			e.printStackTrace();
		} finally {
			if (fileSaved) {
				FormUtil.showSuccessMessage(jpanel, "Arquivo salvo com sucesso!", String.format(msgSuccess, file.getAbsolutePath()));
			} else {
				FormUtil.showErrorMessage(jpanel, "Ocorreu um erro!", msgError);
			}
		}
	}

	private String formatLineCSV(Aluno aluno) {
		return new StringBuilder().append(aluno.getMatricula()).append(",").append(aluno.getNome()).append(",")
				.append(aluno.getIdade()).append(",")
				.append(FormUtil.dateToString(aluno.getDataNascimento(), FormUtil.PATTERN_FORMAT_DATE_CSV)).append(",")
				.append(aluno.getTelefone()).append(",").append(aluno.getCPF()).append("\n").toString();
	}	

}
