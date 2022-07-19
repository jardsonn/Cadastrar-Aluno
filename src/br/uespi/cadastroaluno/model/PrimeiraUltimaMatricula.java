package br.uespi.cadastroaluno.model;


public class PrimeiraUltimaMatricula {
	private String primeiro;
	private String ultimo;

	public String getPrimeiro() {
		return primeiro;
	}

	public PrimeiraUltimaMatricula setPrimeiro(String primeiro) {
		this.primeiro = primeiro;
		return this;
	}

	public String getUltimo() {
		return ultimo;
	}

	public PrimeiraUltimaMatricula setUltimo(String ultimo) {
		this.ultimo = ultimo;
		return this;
	}

}
