import java.util.Scanner;
import java.util.InputMismatchException;

//Classe onde o anfitrião irá acessar o menu do anfitrião e vai escolher as opções disponíveis
public class TelaLocador implements Tela {

	private Scanner sc = new Scanner(System.in);
	private TelaUsuario telaUsuario;

	// construtor TelaLocador
	public TelaLocador(TelaUsuario telaUsuario) {
		this.telaUsuario = telaUsuario;
	}

	// Método que exibe o menu do Anfitrião
	public void exibirTela() {
		int opcao = -1;

		do {
			System.out.println("Olá Bem vindo(a) ao Menu do Anfitrião,\n Digite a opção que você deseja: \n");
			System.out.println("[1]Anunciar uma locação");
			System.out.println("[2]Visualizar todos os seus anúncios");
			System.out.println("[3]Excluir anúncio");
			System.out.println("[4]Visualizar suas locações alugadas");
			System.out.println("[5]Editar anúncio");
			System.out.println("[6]Voltar ao Menu Inicial");

			try {
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					int tipo = 4;
					do {
						System.out.println("Digite a locação desejada: \n");
						System.out.println("[1]Apartamento");
						System.out.println("[2]Barco");
						System.out.println("[3]Quarto");
						System.out.println("[4]Voltar para o Menu do anfitrião");

						tipo = sc.nextInt();

						switch (tipo) {
						case 1:
							anunciar(new Apartamento());
							break;

						case 2:
							anunciar(new Barco());
							break;

						case 3:
							anunciar(new Quarto());
							break;

						case 4:
							System.out.println("Obrigado por anunciar aqui!");
							break;

						default:
							System.out.println("Opção inexistente, tente novamente");
						}

					} while (tipo != 4);
					break;
				case 2:
					// método para visualizar anuncios do usuario especifico
					visualizarAnuncios();
					break;
				case 3:
					// excluirMeusAnuncios();
					excluirAnuncios();
					break;

				case 4:
					// visualiza as vendas do usuário
					visualizarVendas();
					break;

				case 5:
					// Alterar anuncios
					alterarAnuncios();
					break;

				case 6:
					// volta para o Menu Inicial
					telaUsuario.exibirTela();
					break;

				default:
					System.out.println("Opção inexistente, tente novamente");
				}

			} catch (InputMismatchException e) {
				System.out.println("Você deve digitar uma das opções (um número!)");
				sc.nextLine();
			}
		} while (opcao != 6);
	}

	// método que onde o usuário anuncia a locação, o programa através do scanner e
	// guarda na lista de anuncios geral e a lista dos seus anuncios
	public void anunciar(Locacao locacao) {

		Anuncio anuncio = new Anuncio();
		sc.nextLine();
		anuncio.setLocacao(locacao);
		System.out.println("Espaço inteiro ou Espaço compartilhado: ");
		anuncio.getLocacao().setTipoDeLocacao(sc.nextLine());
		System.out.println("Cidade da Locação: ");
		anuncio.getLocacao().setCidadeDaLocacao(sc.nextLine());
		System.out.println("Título da Locação: ");
		anuncio.setTitulo(sc.nextLine());
		System.out.println("Comodidades:  ");
		anuncio.getLocacao().setComodidades(sc.nextLine());
		System.out.println("Observações sobre a Locação: ");
		anuncio.setComentariosAdicionais(sc.nextLine());

		boolean seguir = true;
		do {
			try {
				System.out.println("Valor/noite (R$): ");
				anuncio.setPreco(sc.nextDouble());
				seguir = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Você deve digitar um valor!)");
				sc.nextLine();
			}

		} while (seguir == true);

		sc.nextLine();
		System.out.println("Quantidade máxima de Hóspedes: ");
		anuncio.getLocacao().setQuantidadeHospedes(sc.nextInt());
		
		String dataDisponivel = "";
		
			boolean continuar = true;
			do {
				try {
					System.out.println("Código da Locação(4 dígitos): ");
					anuncio.setCodigo(sc.nextInt());
					continuar = false;
				}

				catch (InputMismatchException e) {
					System.out.println("Você deve digitar um valor!");
					sc.nextLine();
				}

			} while (continuar == true);

			sc.nextLine();
			System.out.println("Digite as datas disponíveis no Formato DD/MM/YYYY - DD/MM/YYYY: ");
			System.out.print("Data: ");
			do {
			boolean cont = true;
			do {
				try {
					anuncio.setData(sc.nextLine());

					SymbolException.verificarData(anuncio.getData());
					
					cont = false;
					
				} catch (SymbolException e) {
					System.out.println(e.getMessage());
					System.out.print("Data: ");
				}
				
			} while (cont == true);
		
			System.out.println("\nDeseja continuar digitando datas disponíveis?(S/N)");
			dataDisponivel = sc.nextLine();
			System.out.print("Data: ");
			

		} while (dataDisponivel.equals("S"));

		if (locacao instanceof Apartamento) {
			Apartamento apartamento = (Apartamento) locacao;
			System.out.println("Quantidade de Quartos: ");
			apartamento.setQuartos(sc.nextInt());
			System.out.println("Quantidade de Banheiros: ");
			apartamento.setBanheiros(sc.nextInt());
			System.out.println("Quantidade de Colchões: ");
			apartamento.setColchoes(sc.nextInt());
			locacao = apartamento;
		}

		if (locacao instanceof Barco) {
			Barco barco = (Barco) locacao;
			System.out.println("Valor do passeio: ");
			barco.setValorPasseio(sc.nextInt());
			locacao = barco;
		}

		if (locacao instanceof Quarto) {
			Quarto quarto = (Quarto) locacao;
			System.out.println("Quantidade de Colchões: ");
			quarto.setColchoes(sc.nextInt());
			locacao = quarto;
		}

		anuncio.setLocacao(locacao);
		telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().add(anuncio);
		telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().add(anuncio);

		System.out.println("Anúncio Cadastrado! \n");
	}

	// método que exibe todos os anuncios cadastrados pelo usuário, o programa
	// percorre a ListaMeusAnuncios;
	private void visualizarAnuncios() {
		String resposta = "";
		int contador = 0;
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
					.size(); i++) {
				System.out.println("\n\nAnúncio " + (i + 1) + ": "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).toString());
				contador++;
				if (i % 10 == 0) {
					System.out.println("\n\nContinuar visualizando os anúncios? (S/N)");
					resposta = sc.nextLine();
				}
			}
		} while (resposta.equals("S"));
	}

	// método que exclui os anuncios solicitados pelo anunciante que escreve o
	// título e o programa compara com a ListaMeusAnuncios;
	private void excluirAnuncios() {
		int verificarCodigo = 0;
		int excluido = 0;
		boolean continuar = true;
		do {
			try {
				System.out.println("Digite o código do anúncio (4 dígitos): ");

				excluido = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Você deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().size(); i++) {
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).getCodigo() == excluido) {
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().remove(i);
				for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); x++) {
					if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(x)
							.getCodigo() == excluido) {
						telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().remove(x);

						System.out.println("Anuncio removido!");
					}
				}
				verificarCodigo = 1;
			}
		}
		if (verificarCodigo == 0) {
			System.out.println("Anúncio não existente! \n");
		}
	}

	private void visualizarVendas() {
		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMinhasVendas().size(); i++) {
			System.out.println("Minhas vendas: "
					+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMinhasVendas().get(i).toString());
		}
	}

	private void alterarAnuncios() {
		int codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.println("Digite o código da Locação que deseja editar (4 dígitos): ");

				codigo = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Você deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		String edit = "S";
		int posicao = 0;
		do {
			for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().size(); i++) {
				if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).getCodigo() == codigo) {
					System.out.println("Anúncio: "
							+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).toString()
							+ "\n");

					posicao = i;

				}
			}

			System.out.println(
					"\nQual dos campos você deseja editar?\n \n[1] Tipo De Locacao: \n[2] Cidade Da Locacao:  \n[3] Título:  \n[4] Comodidades: \n[5] Comentários adicionais: \n[6] Preço: \n[7] Quantidade de Hóspedes: \n[8] Código: \n[9] Datas:  ");

			int editar = 0;
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Apartamento) {
				Apartamento apartamento = (Apartamento) telaUsuario.getTelaLogin().getUsuarioAtual()
						.getListaMeusAnuncios().get(posicao).getLocacao();

				System.out.println(
						"[10] Quantidade de quartos: \n[11] Quantidade de banheiros:  \n[12] Quantidade de colchões: ");
				editar = sc.nextInt();
				sc.nextLine();

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setLocacao(apartamento);

			}

			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Barco) {
				Barco barco = (Barco) telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.getLocacao();
				System.out.println("\n[10] Valor do passeio: ");
				editar = sc.nextInt();
				sc.nextLine();

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).setLocacao(barco);

			}

			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Quarto) {
				Quarto quarto = (Quarto) telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
						.get(posicao).getLocacao();

				System.out.println("\n[10] Quantidade de colchões: ");
				editar = sc.nextInt();
				sc.nextLine();

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).setLocacao(quarto);
			}

			if (editar == 1) {
				System.out.print("Está oferencendo um espaço inteiro ou espaço compartilhado?");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setTipoDeLocacao(sc.nextLine());
			}

			if (editar == 2) {
				System.out.print("Qual a cidade em que a locação se encontra? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setCidadeDaLocacao(sc.nextLine());
			}

			if (editar == 3) {
				System.out.print("Título da locação: ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setTitulo(sc.nextLine());
			}

			if (editar == 4) {
				System.out.print("Comodidades:  ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setComodidades(sc.nextLine());

			}

			if (editar == 5) {
				System.out.print("O anfitrião inclui café da manhã? wifi? televisão? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setComentariosAdicionais(sc.nextLine());
			}

			if (editar == 6) {
				System.out.print("Valor por noite? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setPreco(sc.nextDouble());
			}

			if (editar == 7) {
				System.out.print("Qual a quantidade máxima de hospedes que seu espaço permite? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setQuantidadeHospedes(sc.nextInt());
			}

			if (editar == 8) {
				System.out.print("Digite o código que deseja para sua locação(4 números)? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setCodigo(sc.nextInt());
			}

			if (editar == 9) {
				System.out.println("Datas disponíveis: ");
				telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao).exibirDatasDisponiveis();
				System.out.print("Digite a data que deseja editar:");
				String data = sc.nextLine();
				for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao)
						.getListaDatas().size(); x++) {
					if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao).getListaDatas()
							.get(x).equals(data)) {
						telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao).getListaDatas()
								.remove(x);
						System.out.println("Digite a nova data no Formato DD/MM/YYYY - DD/MM/YYYY: ");
						data = sc.nextLine();
						telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao).getListaDatas()
								.add(data);
					}
				}

			}

			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Apartamento) {
				Apartamento apartamento = (Apartamento) telaUsuario.getTelaLogin().getUsuarioAtual()
						.getListaMeusAnuncios().get(posicao).getLocacao();

				if (editar == 10) {
					System.out.println("Quartos: ");
					apartamento.setQuartos(sc.nextInt());

				}

				if (editar == 11) {

					System.out.println("Banheiros: ");
					apartamento.setBanheiros(sc.nextInt());

				}

				if (editar == 12) {
					System.out.println("Colchões: ");
					apartamento.setColchoes(sc.nextInt());
				}

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setLocacao(apartamento);

			}

			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Barco) {
				Barco barco = (Barco) telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.getLocacao();

				if (editar == 10) {
					System.out.println("Valor do passeio: ");
					barco.setValorPasseio(sc.nextDouble());
				}

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).setLocacao(barco);

			}

			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Quarto) {
				Quarto quarto = (Quarto) telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
						.get(posicao).getLocacao();

				if (editar == 10) {
					System.out.println("Colchões: ");
					quarto.setColchoes(sc.nextInt());
				}

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).setLocacao(quarto);
			}

			System.out.println("Anúncio Editado! \n"
					+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).toString());
			telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao).exibirDatasDisponiveis();

			System.out.println("Deseja continuar editando? (S/N) \n");
			edit = sc.next();

		} while (edit.equals("S"));
	}
}
