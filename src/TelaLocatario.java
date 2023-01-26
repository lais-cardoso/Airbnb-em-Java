import java.util.InputMismatchException;
import java.util.Scanner;

//Classe onde o h�spede ir� acessar o menu do h�spede e vai escolher as op��es dispon�veis
public class TelaLocatario implements Tela {

	private Scanner sc = new Scanner(System.in);
	private TelaUsuario telaUsuario;

	// construtor TelaLocatario
	public TelaLocatario(TelaUsuario telaUsuario) {
		this.telaUsuario = telaUsuario;
	}

	// M�todo que exibe o menu do H�spede
	@Override
	public void exibirTela() {
		int opcao = -1;

		do {
			System.out.println("\n\nOl�, Bem-vindo ao Menu do Locat�rio! \n\nDigite a op��o que voc� deseja: \n");
			System.out.println("[1]Visualizar Loca��es Dispon�veis");
			System.out.println("[2]Alugar Loca��o");
			System.out.println("[3]Listar loca��es alugadas por voc�");
			System.out.println("[4]Exibir detalhes de seus alugueis");
			System.out.println("[5]Exibir soma total das taxas sobre os alugu�is em Reais(R$)");
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
					// Visualizar detalhes de um aluguel em espec�fico
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
					System.out.println("Op��o inexistente. Tente novamente!");
				}

			} catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um n�mero!");
				sc.nextLine();
			}

		} while (opcao != -1);
	}

	// m�todo que visualiza a lista de anuncios existentes
	private void visualizarTodosAnuncios() {
		int contador = 0;
		String resposta = "";
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); i++) {
				System.out.println("\n An�ncio " + (i + 1) + ": "
						+ telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).toString());
				telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).exibirDatasDisponiveis();
				contador++;
				if (contador % 10 == 0) {
					System.out.println("Continuar visualizando os an�ncios? (S/N)");
					sc.nextLine();
					resposta = sc.nextLine();
				}
			}
			break;
		} while (resposta.equals("S"));
	}

	// m�todo que aluga uma loca��o
	public void alugar() {
		int codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.printf("\nQual das loca��es voc� deseja alugar? Digite o c�digo da loca��o(4 n�meros)");

				codigo = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		int verificarDados = 0;
		int verificarLista = 0;

		// compara o c�digo digitado e o c�digo de uma das loca��es
		for (int i = 0; i < telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().size(); i++) {

			if (telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getCodigo() == codigo) {
				verificarDados = 1;
				System.out.println("Datas dispon�veis: ");
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
					
					System.out.println("\nDigite as datas que voc� deseja alugar no Formato DD/MM/YYYY - DD/MM/YYYY: ");
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

							System.out.println("\nLoca��o alugada! \n");

							telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i).getLocacao()
									.getLocador().getListaMinhasVendas()
									.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));
							telaUsuario.getTelaLogin().getBancoDeDados().getListaVendas()
									.add(telaUsuario.getTelaLogin().getBancoDeDados().getListaAnuncios().get(i));
							verificarLista = 1;
						}

					}
					if (verificarLista == 0) {
						System.out.println("Loca��o indispon�vel \n");
					}
				}
			}

		}
		if (verificarDados == 0) {
			System.out.println("An�ncio n�o existente! \n");
		}
	}

	// M�todo que exibe todos os alugueis de um usu�rio
	public void visualizarAlugueis() {
		int contador = 0;
		String resposta = "";
		do {
			for (int i = contador; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis()
					.size(); i++) {
				System.out.println("Loca��o: " + (i + 1) + " "
						+ telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i));
				contador++;
				if (contador % 10 == 0) {
					System.out.println("Continuar visualizando os an�ncios? (S/N)");
					resposta = sc.nextLine();
				}
			}
		} while (resposta.equals("S"));
	}

	// M�todo que exibe detalhes de um aluguel espec�fico
	public void exibirDetalhes() {
		int verificarDados = 0;

		int codigo = 0;
		boolean continuar = true;
		do {
			try {
				System.out.printf("Digite o c�digo da Loca��o que deseja ver detalhes? (4 d�gitos): ");

				codigo = sc.nextInt();
				continuar = false;
			}

			catch (InputMismatchException e) {
				System.out.println("Voc� deve digitar um valor!");
				sc.nextLine();
			}

		} while (continuar == true);

		for (int i = 0; i < telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().size(); i++) {
			if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).getCodigo() == codigo) {
				for (int x = 0; x < telaUsuario.getTelaLogin().getBancoDeDados().getListaUsuarios().size(); x++) {
					if (telaUsuario.getTelaLogin().getUsuarioAtual().getListaMeusAlugueis().get(i).getLocacao()
							.getLocador()
							.equals(telaUsuario.getTelaLogin().getBancoDeDados().getListaUsuarios().get(x))) {
						System.out.println("Detalhes sobre a loca��o: \n " + "\n Informa��es sobre o anfitri�o: \n "
								+ telaUsuario.getTelaLogin().getBancoDeDados().getListaUsuarios().get(x)
								+ "\n Informa��es sobre a loca��o: \n " + telaUsuario.getTelaLogin().getUsuarioAtual()
										.getListaMeusAlugueis().get(i).toString());
					}
					verificarDados = 1;
				}
			}
		}
		if (verificarDados == 0) {
			System.out.println("Voc� n�o tem alugu�is com esse c�digo! \n");
		}
	}

}