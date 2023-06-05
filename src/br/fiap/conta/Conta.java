package br.fiap.conta;

import br.fiap.empresa.Empresa;

public class Conta {

	private double cnpj;

	private Empresa empresa;

	public Conta(Empresa empresa) {
		this.cnpj = empresa.getCnpj();
		this.empresa = empresa;
	}

	public double getCnpj() {
		return cnpj;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	@Override
	public String toString() {
		String aux = "";
		aux += "Empresa: " + empresa.getNome() + "\n";
		aux += "CNPJ: " + cnpj + "\n";
		aux += "Cidade: " + empresa.getCidade() + "\n";
		aux += "Categoria: " + empresa.getCategoria();
		return aux;
	}

}
