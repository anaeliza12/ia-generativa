package br.fiap.alimento;

public class Alimento {
	private String categoria;
	private String nome;
	private String dataValidade;
	private String marca;
	private double quantidade;

	public Alimento(String nome, int categoria, String dataValidade, String marca, double quantidade) {
		this.nome = nome;
		this.categoria = categorizar(categoria);
		this.dataValidade = dataValidade;
		this.marca = marca;
		this.quantidade = quantidade;
	}

	public static String categorizar(int opcao) {
		String categoria = null;

		if (opcao == 1) {
			categoria = "Carnes";
		}
		if (opcao == 2) {
			categoria = "Enlatados";
		}
		if (opcao == 3) {
			categoria = "Bebidas";
		}
		if (opcao == 4) {
			categoria = "Hortifruti";
		}
		if (opcao == 5) {
			categoria = "Padaria";
		}
		if (opcao == 6) {
			categoria = "Grãos";
		}
		if (opcao == 7) {
			categoria = "Outros";
		}

		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public String getMarca() {
		return marca;
	}

	public double getQuantidade() {
		return quantidade;
	}

	@Override
	public String toString() {
		return "Nome:  " + nome + "\n" + "Categoria: " + categoria + "\n" + "Data de validade: " + dataValidade + "\n"
				+ "Marca: " + marca + "\n" + "Quantidade: " + quantidade;
	}

	public String getCategoria() {
		return categoria;
	}


}
