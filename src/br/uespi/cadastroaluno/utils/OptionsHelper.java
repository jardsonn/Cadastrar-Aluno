package br.uespi.cadastroaluno.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

import br.uespi.cadastroaluno.model.Aluno;
import br.uespi.cadastroaluno.model.PrimeiraUltimaMatricula;
import br.uespi.cadastroaluno.ui.TelaListaCadastrado;
import br.uespi.cadastroaluno.ui.components.JMainFrame;

/**
 *Essa classe contém as funções do menu opções na TelaListaCadastro.java
 * 
 * @author Jardson Costa
 * @author João Pedro
 * 
 */

public class OptionsHelper {

	private static final String SAVED_FILES = "Alunos Salvos/";

	private TelaListaCadastrado jpanel;
	private JMainFrame mainJFrame;

	/**Construtor para inicialização dos atributos
	 * 
	 * @param jpanel TelaListaCadastro - JPanel com métodos importantes
	 */
	public OptionsHelper(TelaListaCadastrado jpanel) {
		this.jpanel = jpanel;
		mainJFrame = jpanel.getMainFrame();

	}
	/**
	 * Método para excluir o último aluno
	 * 
	 * @param obj Object - Pega referência da classe para obter o caminho do ícone e excluir 
	 */
	public void deleteLastStudent(Object obj) {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		DefaultListModel<Aluno> model = (DefaultListModel<Aluno>) jpanel.getStudentsList().getModel();
		Aluno lastStudent = mainJFrame.getAlunoList().get(studentList.size() - 1);
		FormUtil.deleteWithMessageDialog(obj, lastStudent, aluno -> {
			model.removeElement(aluno);
			mainJFrame.deleteAluno(aluno);
			boolean isEmpty = studentList.isEmpty();
			jpanel.setInfoVisibity(!isEmpty);
			jpanel.updateScreen(isEmpty);
		});

	}
	
	/**
	 * Método para obter a primeira e a última matrícula
	 */ 	
	public PrimeiraUltimaMatricula getRegistrationFirstLast() {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		Aluno firstStudent = mainJFrame.getAlunoList().get(0);
		Aluno lastStudent = mainJFrame.getAlunoList().get(studentList.size() - 1);
		return new PrimeiraUltimaMatricula().setPrimeiro(firstStudent.getMatricula())
				.setUltimo(lastStudent.getMatricula());
	}

	/**
	 * Método para obter o terceiro aluno da lista
	 */
	public Aluno getThirdStudent() {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		return studentList.get(2);
	}

	/**
	 * Método para salvar um aluno em arquivo .csv
	 * 
	 * @param aluno Aluno - aluno para ser salvo
	 * @param fileName String - nome do arquivo
	 */
	
	public void saveStudent(Aluno aluno, String fileName) {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		List<Aluno> oneStudent = new ArrayList<Aluno>();
		oneStudent.add(studentList.get(studentList.indexOf(aluno)));
		saveAllStudents(oneStudent, fileName);
	}

	/**
	 * Método para salvar uma lista de alunos em arquivo .csv
	 * 
	 * @param fileName String - nome do arquivo
	 */
	public void saveAllStudents(String fileName) {
		List<Aluno> studentList = mainJFrame.getAlunoList();
		saveAllStudents(studentList, fileName);
	}

	/**
	 * Método para salvar uma lista de alunos em arquivo .csv
	 * 
	 * @param studentList List<Aluno> - Lista de alunos a ser salvo
	 * @param studentList List<Aluno> - Lista de alunos a ser salvo
	 * @param fileName String - nome do arquivo
	 */
	public void saveAllStudents(List<Aluno> studentList, String fileName) {
		String path = fileName.concat(".csv");
		File studentFolder = new File(SAVED_FILES);

		String msgError;
		String msgSuccess;
		if (studentList.size() == 1) {
			msgError = "Infelizmente não foi possível salvar esse(a) aluno(a) em um arquivo!";
			msgSuccess = "<html><body>Aluno(a) foi salvo(a) com sucesso.<br/>Salvo em: <b>%s</b></body></html>";
		} else {
			msgError = "Infelizmente não foi possível salvar essa lista de alunos em um arquivo!";
			msgSuccess = "<html><body>Os alunos foram salvos com sucesso.<br/>Salvo em: <b>%s</b></body></html>";
		}

		if (studentList.isEmpty()) {
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
			fw = new FileWriter(file, false);
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
				FormUtil.showSuccessMessage(jpanel, "Arquivo salvo com sucesso!",
						String.format(msgSuccess, file.getAbsolutePath()));
			} else {
				FormUtil.showErrorMessage(jpanel, "Ocorreu um erro!", msgError);
			}
		}
	}

	/**
	 * Método para obter alunos que estão no arquivo .csv
	 * 
	 * @return List<Aluno> Lista de alunos
	 * @throws IOException Se a operação de ler arquivo for interrompida
	 * 
	 */
	public List<Aluno> getStudentsFromFile() {
		List<Aluno> list = new ArrayList<Aluno>();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir").concat("/" + SAVED_FILES)));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile.getName().endsWith(".csv")) {
				InputStream is = null;
				try {
					is = new FileInputStream(selectedFile);
					list = streamToStudent(is);
				} catch (FileNotFoundException e) {
					FormUtil.showErrorMessage(jpanel, "Ocorreu um erro!", e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					FormUtil.showErrorMessage(jpanel, "Ocorreu um erro!", e.getMessage());
					e.printStackTrace();
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {

			}
		}

		return list;

	}

	/**	 
	 * Metódo para transformar um Aluno em uma linha válida/formatada do .csv
	 * 
	 * @param aluno Aluno - aluno a ser formatado
	 * @return Linha do .csv formatada
	 */
	private String formatLineCSV(Aluno aluno) {
		return new StringBuilder().append(aluno.getMatricula()).append(",").append(aluno.getNome()).append(",")
				.append(aluno.getIdade()).append(",")
				.append(FormUtil.dateToString(aluno.getDataNascimento(), FormUtil.PATTERN_FORMAT_DATE_CSV)).append(",")
				.append(aluno.getTelefone()).append(",").append(aluno.getCPF()).append(",").append("\n").toString();
	}
	
	
	/**
	 * Método para transformar inputStream em uma lista de alunos
	 * @param inputStream InputStream - Objeto de entrada
	 * @return List<Aluno> - Lista de alunos
	 * @throws IOException - Se o arquivo do objeto de entrada conter atributos inválidos
	 */
	private List<Aluno> streamToStudent(InputStream inputStream) throws IOException {
		List<Aluno> list = new ArrayList<Aluno>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] attrs = line.split(",");
				if (attrs.length != 6) {
					throw new IOException("Esse arquivo não é compatível com esse programa!");
				}
				String matricula = attrs[0];
				String nome = attrs[1];
				String idade = attrs[2];
				String data = attrs[3];
				String telefone = attrs[4];
				String cpf = attrs[5];
				int invalidIndex = validData(attrs);
				if (invalidIndex != -1) {
					throw new IOException(String.format("Esse arquivo contém dados formatados incorretamente! (%s)",
							attrs[invalidIndex]));
				}

				Aluno aluno = new Aluno(matricula, nome, Integer.parseInt(idade), FormUtil.stringToDate(data), telefone,
						cpf);
				list.add(aluno);
			}
		}
		return list;
	}

	/**
	 * Método para validar os atributos do arquivo lido
	 * 
	 * @param attrs String[] - Atributos do arquivo .csv
	 * @return int - Índice do atributo inválido
	 */
	private int validData(String[] attrs) {
		int lenMat = attrs[0].trim().length();
		int lenDate = attrs[3].trim().length();
		int lenPhone = attrs[4].trim().length();
		int lenCpf = attrs[5].trim().length();
		if (lenMat != 7)
			return 0;
		if (lenDate != 10)
			return 3;
		if (lenPhone != 16)
			return 4;
		if (lenCpf != 14)
			return 5;
		return -1;

	}

}
