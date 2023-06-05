package br.fiap.empresa;

import java.util.ArrayList;

import br.fiap.alimento.Alimento;

public class Empresa {
	private String nome;
	private double cnpj;
	private String cidade;
	private String categoria;
	private ArrayList<Alimento> carrinho;

	public Empresa(String nome, double cnpj, String cidade, String categoria) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.categoria = categoria;
		carrinho = new ArrayList<>();
	}

	public static String categorizar(int opcao) {
		String categoria = null;

		if (opcao == 1) {
			categoria = "Fornecedora";
		}
		if (opcao == 2) {
			categoria = "Solicitante";
		}

		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public double getCnpj() {
		return cnpj;
	}

	public String getCidade() {
		return cidade;
	}

	@Override
	public String toString() {

		String aux = "";
		aux += "Empresa: " + nome + "\n";
		aux += "CNPJ: " + cnpj + "\n";
		aux += "Cidade: " + cidade;
		return aux;

	}

	public ArrayList<Alimento> getCarrinho() {
		return carrinho;
	}

	public String getCategoria() {
		return categoria;
	}


}
