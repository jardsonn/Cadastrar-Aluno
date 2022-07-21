package br.uespi.cadastroaluno.model;

import java.util.Date;

public class Aluno {
	
	private String matricula;
	private String nome;
	private int idade;	
	private Date dataNascimento;
	private String telefone;
	private String cpf;	
	
	private boolean maisVelho;
	private boolean maisNovo;
	
	public Aluno() {
		
	}
	
	public Aluno(String matricula, String nome, int idade, Date dataNascimento, String telefone, String cpf) {
		this.matricula = matricula;
		this.nome = nome;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cpf = cpf;
	}


	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCPF() {
		return cpf;
	}
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	public boolean isMaisVelho() {
		return maisVelho;
	}

	public void setMaisVelho(boolean maisVelho) {
		this.maisVelho = maisVelho;
	}

	public boolean isMaisNovo() {
		return maisNovo;
	}

	public void setMaisNovo(boolean maisNovo) {
		this.maisNovo = maisNovo;
	}

	// Será mostrado na lista
	@Override
	public String toString() {
		String info = "";
		if(maisVelho) {
			info = " (mais velho)";
		}else if(maisNovo) {
			info = " (mais novo)";
		}
		
		return "    "+nome+info;
		
	}
	
}