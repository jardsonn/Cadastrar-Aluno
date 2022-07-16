package br.uespi.cadastroaluno.interfaces;

import java.util.List;

import br.uespi.cadastroaluno.model.Aluno;

public interface SearchStudentsListener {
	
	void onStudentsFound(List<Aluno> studentsFound);

}
