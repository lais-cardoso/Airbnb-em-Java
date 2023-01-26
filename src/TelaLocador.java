import java.util.Scanner;
import java.util.InputMismatchException;

//Classe onde o anfitri�o ir� acessar o menu do anfitri�o e vai escolher as op��es dispon�veis
public class TelaLocador implements Tela {

	private Scanner sc = new Scanner(System.in);
	private TelaUsuario telaUsuario;

	// construtor TelaLocador
	public TelaLocador(TelaUsuario telaUsuario) {
		this.telaUsuario = telaUsuario;
	}

	// M�todo que exibe o menu do Anfitri�o
	public void exibirTela() {
		int opcao = -1;

		do {
			System.out.println("Ol� Bem vindo(a) ao Menu do Anfitri�o,\n Digite a op��o que voc� deseja: \n");
			System.out.println("[1]Anunciar uma loca��o");
			System.out.println("[2]Visualizar todos os seus an�ncios");
			System.out.println("[3]Excluir an�ncio");
			System.out.println("[4]Visualizar suas loca��es alugadas");
			System.out.println("[5]Editar an�ncio");
			System.out.println("[6]Voltar ao Menu Inicial");

			try {
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					int tipo = 4;
					do {
						System.out.println("Digite a loca��o desejada: \n");
						System.out.println("[1]Apartamento");
						System.out.println("[2]Barco");
						System.out.println("[3]Quarto");
						System.out.println("[4]Voltar para o Menu do anfitri�o");

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
							System.out.println("Op��o inexistente, tente novamente");
						}

					} while (tipo != 4);
					break;
				case 2:
					// m�todo para visualizar anuncios do usuario especifico
					visualizarAnuncios();
					break;
				case 3:
					// excluirMeusAnuncios();
					excluirAnuncios();
					break;

				case 4:
					// visualiza as vendas do usu�rio
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
					System.out.println("Op��o inexistente, tente novamente");
				}

			} catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar uma das op��es (um n�mero!)");
				sc.nextLine();
			}
		} while (opcao != 6);
	}

	// m�todo que onde o usu�rio anuncia a loca��o, o programa atrav�s do scanner e
	// guarda na lista de anuncios geral e a lista dos seus anuncios
	public void anunciar(Locacao locacao) {

		Anuncio anuncio = new Anuncio();
		sc.nextLine();
		anuncio.setLocacao(locacao);
		System.out.println("Espa�o inteiro ou Espa�o compartilhado: ");
		anuncio.getLocacao().setTipoDeLocacao(sc.nextLine());
		System.out.println("Cidade da Loca��o: ");
		anuncio.getLocacao().setCidadeDaLocacao(sc.nextLine());
		System.out.println("T�tulo da Loca��o: ");
		anuncio.setTitulo(sc.nextLine());
		System.out.println("Comodidades:  ");
		anuncio.getLocacao().setComodidades(sc.nextLine());
		System.out.println("Observa��es sobre a Loca��o: ");
		anuncio.setComentariosAdicionais(sc.nextLine());

		boolean seguir = true;
		do {
			try {
				System.out.println("Valor/noite (R$): ");
				anuncio.setPreco(sc.nextDouble());
				seguir = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!)");
				sc.nextLine();
			}

		} while (seguir == true);

		sc.nextLine();
		System.out.println("Quantidade m�xima de H�spedes: ");
		anuncio.getLocacao().setQuantidadeHospedes(sc.nextInt());
		
		String dataDisponivel = "";
		
			boolean continuar = true;
			do {
				try {
					System.out.println("C�digo da Loca��o(4 d�gitos): ");
					anuncio.setCodigo(sc.nextInt());
					continuar = false;
				}

				catch (InputMismatchException e) {
					System.out.println("Voc� deve digitar um valor!");
					sc.nextLine();
				}

			} while (continuar == true);

			sc.nextLine();
			System.out.println("Digite as datas dispon�veis no Formato DD/MM/YYYY - DD/MM/YYYY: ");
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
		
			System.out.println("\nDeseja continuar digitando datas dispon�veis?(S/N)");
			dataDisponivel = sc.nextLine();
			System.out.print("Data: ");
			

		} while (dataDisponivel.equals("S"));

		if (locacao instanceof Apartamento) {
			Apartamento apartamento = (Apartamento) locacao;
			System.out.println("Quantidade de Quartos: ");
			apartamento.setQuartos(sc.nextInt());
			System.out.println("Quantidade de Banheiros: ");
			apartamento.setBanheiros(sc.nextInt());
			System.out.println("Quantidade de Colch�es: ");
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
			System.out.println("Quantidade de Colch�es: ");
			quarto.setColchoes(sc.nextInt());
			locacao = quarto;
		}

		anuncio.setLocacao(locacao);
		telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().add(anuncio);
		telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().add(anuncio);

		System.out.println("An�ncio Cadastrado! \n");
	}

	// m�todo que exibe todos os anuncios cadastrados pelo usu�rio, o programa
	// percorre a ListaMeusAnuncios;
	private void visualizarAnuncios() {
		String resposta = "";
		int contador = 0;
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios()
					.size(); i++) {
				System.out.println("\n\nAn�ncio " + (i + 1) + ": "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).toString());
				contador++;
				if (i % 10 == 0) {
					System.out.println("\n\nContinuar visualizando os an�ncios? (S/N)");
					resposta = sc.nextLine();
				}
			}
		} while (resposta.equals("S"));
	}

	// m�todo que exclui os anuncios solicitados pelo anunciante que escreve o
	// t�tulo e o programa compara com a ListaMeusAnuncios;
	private void excluirAnuncios() {
		int verificarCodigo = 0;
		int excluido = 0;
		boolean continuar = true;
		do {
			try {
				System.out.println("Digite o c�digo do an�ncio (4 d�gitos): ");

				excluido = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
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
			System.out.println("An�ncio n�o existente! \n");
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
				System.out.println("Digite o c�digo da Loca��o que deseja editar (4 d�gitos): ");

				codigo = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		String edit = "S";
		int posicao = 0;
		do {
			for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().size(); i++) {
				if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).getCodigo() == codigo) {
					System.out.println("An�ncio: "
							+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(i).toString()
							+ "\n");

					posicao = i;

				}
			}

			System.out.println(
					"\nQual dos campos voc� deseja editar?\n \n[1] Tipo De Locacao: \n[2] Cidade Da Locacao:  \n[3] T�tulo:  \n[4] Comodidades: \n[5] Coment�rios adicionais: \n[6] Pre�o: \n[7] Quantidade de H�spedes: \n[8] C�digo: \n[9] Datas:  ");

			int editar = 0;
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
					.getLocacao() instanceof Apartamento) {
				Apartamento apartamento = (Apartamento) telaUsuario.getTelaLogin().getUsuarioAtual()
						.getListaMeusAnuncios().get(posicao).getLocacao();

				System.out.println(
						"[10] Quantidade de quartos: \n[11] Quantidade de banheiros:  \n[12] Quantidade de colch�es: ");
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

				System.out.println("\n[10] Quantidade de colch�es: ");
				editar = sc.nextInt();
				sc.nextLine();

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).setLocacao(quarto);
			}

			if (editar == 1) {
				System.out.print("Est� oferencendo um espa�o inteiro ou espa�o compartilhado?");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setTipoDeLocacao(sc.nextLine());
			}

			if (editar == 2) {
				System.out.print("Qual a cidade em que a loca��o se encontra? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setCidadeDaLocacao(sc.nextLine());
			}

			if (editar == 3) {
				System.out.print("T�tulo da loca��o: ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setTitulo(sc.nextLine());
			}

			if (editar == 4) {
				System.out.print("Comodidades:  ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setComodidades(sc.nextLine());

			}

			if (editar == 5) {
				System.out.print("O anfitri�o inclui caf� da manh�? wifi? televis�o? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setComentariosAdicionais(sc.nextLine());
			}

			if (editar == 6) {
				System.out.print("Valor por noite? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setPreco(sc.nextDouble());
			}

			if (editar == 7) {
				System.out.print("Qual a quantidade m�xima de hospedes que seu espa�o permite? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).getLocacao()
						.setQuantidadeHospedes(sc.nextInt());
			}

			if (editar == 8) {
				System.out.print("Digite o c�digo que deseja para sua loca��o(4 n�meros)? ");
				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao)
						.setCodigo(sc.nextInt());
			}

			if (editar == 9) {
				System.out.println("Datas dispon�veis: ");
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
					System.out.println("Colch�es: ");
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
					System.out.println("Colch�es: ");
					quarto.setColchoes(sc.nextInt());
				}

				telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).setLocacao(quarto);
			}

			System.out.println("An�ncio Editado! \n"
					+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAnuncios().get(posicao).toString());
			telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(posicao).exibirDatasDisponiveis();

			System.out.println("Deseja continuar editando? (S/N) \n");
			edit = sc.next();

		} while (edit.equals("S"));
	}
}
