package br.fiap.empresa;

public class EmpresaFornecedora extends Empresa {

	public EmpresaFornecedora(String nome, double cnpj, String cidade, String tipo) {
		super(nome, cnpj, cidade, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmpresaFornecedora [getNome()=" + getNome() + ", getCnpj()=" + getCnpj() + ", getCidade()="
				+ getCidade() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
}
