package br.uespi.cadastroaluno.model;

import java.util.Date;
//Dever ser o Date
public class Aluno {
	
	
	
	public Aluno(int matricula, String nome, int idade, Date dataNascimento, String telefone, String CPF) {
		this.matricula = matricula;
		this.nome = nome;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.CPF = CPF;
	}
	
	public Aluno() {
		
	}
	
	
	private int matricula;
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
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
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	private String nome;
	private int idade;	
	private Date dataNascimento;
	private String telefone;
	private String CPF;	
}