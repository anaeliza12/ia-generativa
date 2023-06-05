package br.fiap.bancoAlimento;

import java.util.ArrayList;

import br.fiap.alimento.Alimento;
import br.fiap.conta.Conta;
import br.fiap.empresa.Empresa;
import br.fiap.empresa.EmpresaFornecedora;
import br.fiap.empresa.EmpresaSolicitante;

public class BancoAlimento {



	private ArrayList<Conta> conta;
	private ArrayList<Alimento> catalogo;

	public BancoAlimento() {
		conta = new ArrayList<>();
		catalogo = new ArrayList<>();

	}

	public boolean addCarrinho(int opcao, Empresa es) {
		boolean aux = false;
		int i = 1;
		for (Alimento a : catalogo) {
			if (opcao == i) {
				es.getCarrinho().add(a);

				aux = true;
				break;
			}
			i++;
		}

		return aux;
	}

	public boolean alterarAlimento(Empresa es, int inserir, int substituir) {
		boolean verifica = true;
		Alimento aux = null;

		int i = 1;
		for (Alimento a : catalogo) {
			if (inserir == i) {
				aux = a;
				break;
			}
			i++;

		}
		int j = 1;

		for (Alimento a : es.getCarrinho()) {
			if (substituir == j) {
				if (aux != a) {
					es.getCarrinho().remove(substituir - 1);
					es.getCarrinho().add(substituir - 1, aux);
					break;
				} else
					verifica = false;
			}

			j++;
		}

		return verifica;

	}

	public String verCarrinho(double CNPJ) {
		String aux = null;
		int i = 1;
		for (Conta c : conta) {
			if (CNPJ == c.getEmpresa().getCnpj() && c.getEmpresa() instanceof EmpresaSolicitante) {
				for (Alimento a : c.getEmpresa().getCarrinho()) {
					if (i == 1) {
						aux = "Carrinho: " + "\n";

					}
					aux += (i) + ". " + a.getNome() + "\n";
					i++;
				}
			}
		}
		return aux;

	}

	public Empresa verificaSolicitante(double CNPJ) {
		Empresa aux = null;
		for (Conta c : conta) {
			if (CNPJ == c.getEmpresa().getCnpj() && c.getEmpresa() instanceof EmpresaSolicitante)
				aux = c.getEmpresa();

		}
		return aux;
	}

	public boolean verificarContas(double cnpj) {
		boolean verifica = true;
		for (Conta c : conta) {
			if (cnpj == c.getCnpj())
				verifica = false;
			break;
		}
		return verifica;
	}

	public boolean verificarFornecedora(double CNPJ) {
		boolean verifica = false;
		for (Conta c : conta) {
			if (CNPJ == c.getEmpresa().getCnpj() && c.getEmpresa() instanceof EmpresaFornecedora)
				verifica = true;

		}
		return verifica;
	}

	public boolean cadastrarConta(Conta conta) {
		if (verificarContas(conta.getCnpj())) {
			this.conta.add(conta);
			return true;
		} else
			return false;

	}

	public String pesquisar(double CNPJ) {
		String c = null;
		for (Conta i : conta) {
			if (i.getCnpj() == CNPJ) {
				c = i.toString();

				break;
			}
		}
		return c;
	}

	public boolean remover(double CNPJ) {
		boolean aux = false;
		for (Conta i : conta) {
			if (i.getCnpj() == CNPJ) {
				conta.remove(i);
				aux = true;
				break;
			}

		}
		return aux;
	}

	public void cadastrarAlimento(Alimento alimento) {
		catalogo.add(alimento);
	}

	public String verCatalogo() {
		String aux = null;
		int i = 1;
		for (Alimento c : catalogo) {
			if (i == 1) {
				aux = "Alimentos disponiveis: " + "\n";
			}
			aux += (i) + ". " + c.getNome() + "\n";

			i++;
		}

		return aux;

	}

	public String infoAlimento(int opcao) {
		String aux = "";
		int i = 1;
		for (Alimento c : catalogo) {
			if (opcao == i) {
				aux += c.toString();
				break;
			}

		}

		return aux;

	}

	@Override
	public String toString() {
		String aux = "";
		for (Conta c : conta) {
			aux += c + "\n";
		}
		return aux;
	}

}
