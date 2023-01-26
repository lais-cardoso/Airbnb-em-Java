import java.util.InputMismatchException;
import java.util.Scanner;

//Classe onde o hóspede irá acessar o menu do hóspede e vai escolher as opções disponíveis
public class TelaLocatario implements Tela {

	private Scanner sc = new Scanner(System.in);
	private TelaUsuario telaUsuario;

	// construtor TelaLocatario
	public TelaLocatario(TelaUsuario telaUsuario) {
		this.telaUsuario = telaUsuario;
	}

	// Método que exibe o menu do Hóspede
	@Override
	public void exibirTela() {
		int opcao = -1;

		do {
			System.out.println("\n\nOlá, Bem-vindo ao Menu do Locatário! \n\nDigite a opção que você deseja: \n");
			System.out.println("[1]Visualizar Locações Disponíveis");
			System.out.println("[2]Alugar Locação");
			System.out.println("[3]Listar locações alugadas por você");
			System.out.println("[4]Exibir detalhes de seus alugueis");
			System.out.println("[5]Exibir soma total das taxas sobre os aluguéis em Reais(R$)");
			System.out.println("[6]Voltar ao Menu Inicial");

			try {
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					// exibir todos os anuncios
					visualizarTodosAnuncios();
					break;

				case 2:
					// AlugarLocacao(comprar locacao)(carrinho)
					alugar();
					break;

				case 3:
					// VisualizarSeusAlugueis
					visualizarAlugueis();

					break;

				case 4:
					// Visualizar detalhes de um aluguel em específico
					exibirDetalhes();
					break;

				case 5:
					// Taxa total Airbnb
					telaUsuario.getTelaLogin().getBancoDeDados().exibirTaxa();
					break;

				case 6:
					// volta para o Menu Inicial
					telaUsuario.exibirTela();
					break;

				default:
					System.out.println("Opção inexistente. Tente novamente!");
				}

			} catch (InputMismatchException e) {
				System.out.println("Você deve digitar um número!");
				sc.nextLine();
			}

		} while (opcao != -1);
	}

	// método que visualiza a lista de anuncios existentes
	private void visualizarTodosAnuncios() {
		int contador = 0;
		String resposta = "";
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); i++) {
				System.out.println("\n Anúncio " + (i + 1) + ": "
						+ telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).toString());
				telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).exibirDatasDisponiveis();
				contador++;
				if (contador % 10 == 0) {
					System.out.println("Continuar visualizando os anúncios? (S/N)");
					sc.nextLine();
					resposta = sc.nextLine();
				}
			}
			break;
		} while (resposta.equals("S"));
	}

	// método que aluga uma locação
	public void alugar() {
		int codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.printf("\nQual das locações você deseja alugar? Digite o código da locação(4 números)");

				codigo = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Você deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		int verificarDados = 0;
		int verificarLista = 0;

		// compara o código digitado e o código de uma das locações
		for (int i = 0; i < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); i++) {

			if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getCodigo() == codigo) {
				verificarDados = 1;
				System.out.println("Datas disponíveis: ");
				for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i)
						.getListaDatas().size(); x++) {
					System.out.printf("[%d] - %s%n", x + 1, telaUsuario.getTelaLogin().getBancoDeDados()
							.getListaAnuncios().get(i).getListaDatas().get(x));
				}
				sc.nextLine();
				System.out.println("\nDeseja alugar em alguma dessas datas?(S/N)");
				String resposta = sc.nextLine();
				if (resposta.equals("S")) {
					String data = "";
					boolean cont = true;
					do {
					try {
					
					System.out.println("\nDigite as datas que você deseja alugar no Formato DD/MM/YYYY - DD/MM/YYYY: ");
					data = sc.nextLine();
					
					SymbolException.verificarData(data);
					cont=false;
					
					}catch(SymbolException e) {
						System.out.println(e.getMessage());
						
					}
					
					}while(cont==true);
					

					for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i)
							.getListaDatas().size(); x++) {
						if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getListaDatas()
								.get(x).equals(data) && resposta.equals("S")) {
							telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis()
									.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));

							System.out.println("\nLocação alugada! \n");

							telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getLocacao()
									.getLocador().getListaMinhasVendas()
									.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));
							telaUsuario.getTelaLogin().getBancoDeDados().getListaVendas()
									.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));
							verificarLista = 1;
						}

					}
					if (verificarLista == 0) {
						System.out.println("Locação indisponível \n");
					}
				}
			}

		}
		if (verificarDados == 0) {
			System.out.println("Anúncio não existente! \n");
		}
	}

	// Método que exibe todos os alugueis de um usuário
	public void visualizarAlugueis() {
		int contador = 0;
		String resposta = "";
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis()
					.size(); i++) {
				System.out.println("Locação: " + (i + 1) + " "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i));
				contador++;
				if (contador % 10 == 0) {
					System.out.println("Continuar visualizando os anúncios? (S/N)");
					resposta = sc.nextLine();
				}
			}
		} while (resposta.equals("S"));
	}

	// Método que exibe detalhes de um aluguel específico
	public void exibirDetalhes() {
		int verificarDados = 0;

		int codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.printf("Digite o código da Locação que deseja ver detalhes? (4 dígitos): ");

				codigo = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Você deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().size(); i++) {
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).getCodigo() == codigo) {
				for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaUsuarios().size(); x++) {
					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).getLocacao()
							.getLocador()
							.equals(telaUsuario.getTelaLogin().getBancoDeDados().getListaUsuarios().get(x))) {
						System.out.println("Detalhes sobre a locação: \n " + "\n Informações sobre o anfitrião: \n "
								+ telaUsuario.getTelaLogin().getBancoDeDados().getListaUsuarios().get(x)
								+ "\n Informações sobre a locação: \n " + telaUsuario.getTelaLogin().getUsuarioAtual()
										.getListaMeusAlugueis().get(i).toString());
					}
					verificarDados = 1;
				}
			}
		}
		if (verificarDados == 0) {
			System.out.println("Você não tem aluguéis com esse código! \n");
		}
	}

}