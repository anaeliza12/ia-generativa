package br.fiap.util;

import javax.swing.JOptionPane;

import br.fiap.alimento.Alimento;
import br.fiap.bancoAlimento.BancoAlimento;
import br.fiap.conta.Conta;
import br.fiap.empresa.Empresa;
import br.fiap.empresa.EmpresaFornecedora;
import br.fiap.empresa.EmpresaSolicitante;

public class Util {
	BancoAlimento banco = new BancoAlimento();

	public void menu() {

		int opcao = 0;

		do {

			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, menuPrincipal()));

			// menuPrincipal1
			switch (opcao) {
			case 1:
				cadastrarConta();
				break;

			case 2:
				pesquisar();
				break;

			case 3:
				removerConta();
				break;

			case 4:
				doacao();
				break;

			case 5:
				catalogo();
				break;

			case 6:
				alterar();
				break;

			case 7:
				solicitar();
				break;

			case 8:
				carrinho();
			}

		} while (opcao != 9);
	}

	private void alterar() {

		double CNPJ = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		Empresa aux = banco.verificaSolicitante(CNPJ);
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "Carrinho não encontrado (verifique a categoria de sua empresa)");
		} else {
			int inserir = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Informe o alimento que deseja inserir: " + "\n" + banco.verCatalogo()));
			int substituir = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Alimento que deseja substituir do seu " + "\n" + banco.verCarrinho(CNPJ)));

			boolean aux1 = banco.alterarAlimento(aux, inserir, substituir);
			if (aux1 == false) {
				JOptionPane.showMessageDialog(null, "Voce ja possui esse item");
			} else {
				JOptionPane.showMessageDialog(null, "Alimento substituido.");

			}

		}

	}

	private void carrinho() {
		double CNPJ = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		String aux = banco.verCarrinho(CNPJ);

		if (aux == null) {
			JOptionPane.showMessageDialog(null, "Carrinho não encontrado (verifique a categoria de sua empresa)");
		} else {
			JOptionPane.showMessageDialog(null, aux);
		}

	}

	private void solicitar() {

		double CNPJ = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		Empresa aux1 = banco.verificaSolicitante(CNPJ);
		if (aux1 == null)
			JOptionPane.showMessageDialog(null, "Empresa não encontrada (verifique a categoria de sua empresa)");
		else {
			String aux3 = banco.verCatalogo();
			if (aux3 == null) {
				JOptionPane.showMessageDialog(null, "Não ha alimentos disponíveis");
			} else {
				int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux3));
				boolean aux2 = banco.addCarrinho(opcao, aux1);
				if (aux2 == false)
					JOptionPane.showMessageDialog(null, "Opção inválida");
				else
					JOptionPane.showMessageDialog(null, "Alimento adicionado ao seu carrinho.");

			}

		}

	}

	private void catalogo() {

		String aux = banco.verCatalogo();
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "Não há alimentos diponíveis");
		} else {
			int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, aux));
			JOptionPane.showMessageDialog(null, banco.infoAlimento(opcao));
		}

	}

	private void doacao() {
		double CNPJ = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		if (banco.verificarFornecedora(CNPJ) == false) {
			JOptionPane.showMessageDialog(null, "Conta não cadastrada. (verifique a categoria de sua empresa)");
		} else {

			int categoria = Integer.parseInt(JOptionPane.showInputDialog(null, "Categoria: " + menuCatalogo()));
			String nomeAlimento = JOptionPane.showInputDialog(null,
					"Nome da categoria:  " + Alimento.categorizar(categoria));
			String dataValidade = JOptionPane.showInputDialog(null, "Data de validade (dd/mm/yyyy): ");
			String marca = JOptionPane.showInputDialog(null, "Marca: ");
			double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Quantidade (Kg ou L): "));

			Alimento alimento = new Alimento(nomeAlimento, categoria, dataValidade, marca, quantidade);

			banco.cadastrarAlimento(alimento);
			JOptionPane.showMessageDialog(null, "Alimento cadastrado.");
		}

	}

	private void removerConta() {
		double cnpj = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		boolean aux = banco.remover(cnpj);

		if (aux == false) {
			JOptionPane.showMessageDialog(null, "CNPJ não encontrado");
		} else
			JOptionPane.showMessageDialog(null, "Conta removida");

	}

	private void pesquisar() {
		double CNPJ = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		String aux = banco.pesquisar(CNPJ);
		if (aux == null) {
			JOptionPane.showMessageDialog(null, "Empresa não encontrada");
		} else
			JOptionPane.showMessageDialog(null, banco.pesquisar(CNPJ));

	}

	private void cadastrarConta() {
		String nome = JOptionPane.showInputDialog(null, "Nome: ");
		double CNPJ = Double.parseDouble(JOptionPane.showInputDialog(null, "CNPJ: "));
		String cidade = JOptionPane.showInputDialog(null, "Cidade(UF): ");
		int categoria = Integer.parseInt(JOptionPane.showInputDialog(null, menuCategoria()));

		if (categoria == 1) {
			EmpresaFornecedora fornecedora = new EmpresaFornecedora(nome, CNPJ, cidade, Empresa.categorizar(categoria));
			Conta conta = new Conta(fornecedora);
			if (banco.cadastrarConta(conta) == true) {
				JOptionPane.showMessageDialog(null, "Conta criada");

			} else
				JOptionPane.showMessageDialog(null, "Conta existente");

		} else if (categoria == 2) {

			EmpresaSolicitante solicitante = new EmpresaSolicitante(nome, CNPJ, cidade, Empresa.categorizar(categoria));
			Conta conta = new Conta(solicitante);
			if (banco.cadastrarConta(conta) == true) {
				JOptionPane.showMessageDialog(null, "Conta criada");

			} else
				JOptionPane.showMessageDialog(null, "Conta existente");

		}

	}

	public String menuPrincipal() {
		String aux = "";
		aux += "1. Cadastrar conta" + "\n";
		aux += "2. Pesquisar conta" + "\n";
		aux += "3. Remover conta" + "\n";
		aux += "4. Realizar doação (Necessário ter conta!)" + "\n";
		aux += "5. Visualizar catálogo " + "\n";
		aux += "6. Alterar alimento" + "\n";
		aux += "7. Solicitar assistência (Necessário ter conta!) " + "\n";
		aux += "8. Ver carrinho" + "\n";
		aux += "9. Sair";
		return aux;
	}

	public String menuCatalogo() {
		String aux = "\n";
		aux += "1. Carnes" + "\n";
		aux += "2. Enlatados" + "\n";
		aux += "3. Bebidas" + "\n";
		aux += "4. Hortifruti" + "\n";
		aux += "5. Padaria" + "\n";
		aux += "6. Graos" + "\n";
		aux += "7. Outros" + "\n";

		return aux;
	}

	public String menuCategoria() {
		String aux = "Informe a categoria da empresa:" + "\n";
		aux += "1. Fornecedora" + "\n";
		aux += "2. Solicitante" + "\n";

		return aux;
	}


}
