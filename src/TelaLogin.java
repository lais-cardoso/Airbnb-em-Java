import java.util.InputMismatchException;
import java.util.Scanner;

//Tela Login é o primeiro acesso da "main" chama as duas listas que foram previamente criadas na Principal.java
public class TelaLogin implements Tela {

	private Scanner entrada = new Scanner(System.in);
	private Usuario usuarioAtual;
	private BancoDeDados bancoDeDados;
	
	// construtor da classe Tela Login
	public TelaLogin(BancoDeDados bancoDeDados) {
		this.bancoDeDados= bancoDeDados;
	}

	// método compara as strings do login(email e senha)
	public Usuario solicitarLogin() {
		String emailDigitado ="";
		boolean continuar = true;
		
		int i=0;
		if(i!=10) {
			System.out.println("--------Tela de LOGIN-------- \n");
			System.out.println("[0] Voltar \n");
			do {
			try {
			entrada.nextLine();
			System.out.println("Digite o seu email: ");
			emailDigitado = entrada.nextLine();
			
			if(emailDigitado.equals("0")) {
				i=10;
				System.out.println("Obrigado pela visita!");
				continuar = false;
			}
			
			SymbolException.verificarEmail(emailDigitado);
			continuar = false;
			}catch(SymbolException e) {
				System.out.println(e.getMessage());
			}
			
			}while(continuar == true);
			
			
			for (Usuario usuario : bancoDeDados.getListaUsuarios()) {
				if (usuario.getEmail().equals(emailDigitado)) {
					System.out.println("Usuário encontrado!\n");

					System.out.println("Digite sua senha: ");

					String senhaDigitada = entrada.nextLine();
					
					if(senhaDigitada.equals(usuario.getSenha())) {
						System.out.println("Login efetuado!");
						usuarioAtual = usuario;
						i=10;
					}else {
						for(int j=0; j<3; j++) {
							System.out.println("Senha incorreta. Tente novamente!");
							senhaDigitada = entrada.nextLine();
							
							if(senhaDigitada.equals(usuario.getSenha())) {
								System.out.println("Login Efetuado!");
								usuarioAtual = usuario;
								i=10;
							}
						}
					}
				}
			}
			if(i!=10) {
				System.out.println("Dados incorretos!");
				System.out.println("Você deseja ir para a Tela de Cadastro?(S/N)");
				String resposta = entrada.nextLine();
				if(resposta.equals("S")) {
					cadastrar();
					i=10;
				}
				else {
					System.out.println("\n\n Obrigado pela visita!\n\n");
					i=10;
				}
			}
		}
		return usuarioAtual;
	}

	// método sobrescrito exibir tela onde o usuario escolher o fazer login ou fazer
	// o cadastro
	@Override
	public void exibirTela() {
		int opcao = -1;

		do {
			TelaUsuario telaUsuario;
			
			System.out.println("Bem vindo(a) ao Airbnb! \n");
			System.out.println("[1] Realizar Login.");
			System.out.println("[2] Realizar Cadastro.");
			
			try{

			opcao = entrada.nextInt();

			switch (opcao) {
			case 1:
				// Login
				Usuario usuario = solicitarLogin();
				if (usuario != null) {
					telaUsuario = new TelaUsuario(this);
					telaUsuario.exibirTela();
				}
				break;

			case 2:
				// Cadastro do usuário
				cadastrar();
				telaUsuario = new TelaUsuario(this);
				telaUsuario.exibirTela();
				break;
				
			default:
				System.out.println("Opção inexistente, tente novamente");
			}
			
			}catch(InputMismatchException e) {
				System.out.println("Você deve digitar uma das opções (um número!)");
				entrada.nextLine();
			}
		} while (opcao != -1);
	}

	// método que faz o cadastro
	private void cadastrar() {
		usuarioAtual = new Usuario();
		entrada.nextLine();
		System.out.println("Digite seu nome: ");
		usuarioAtual.setNome(entrada.nextLine());
		
		System.out.println("Digite seu sobrenome: ");
		usuarioAtual.setSobrenome(entrada.nextLine());
		
		boolean continuar = true;
		do {
		try {
		
		System.out.println("Digite seu email: ");
		usuarioAtual.setEmail(entrada.nextLine());
		
		SymbolException.verificarEmail(usuarioAtual.getEmail());
		DadosException.compararEmail(bancoDeDados, usuarioAtual.getEmail());
		continuar=false;
		}catch(SymbolException e) {
			System.out.println(e.getMessage());
			
		}
		catch(DadosException a){
			System.out.println(a.getMessage());
		}
		
		}while(continuar==true);
		
		boolean seguir = true;
		do {
		try {
			System.out.println("Digite seu CPF: ");
			usuarioAtual.setCPF(entrada.nextLine());
			DadosException.compararCPF(bancoDeDados, usuarioAtual.getCPF());
			seguir=false;
		}
		
		
		catch(DadosException a){
			System.out.println(a.getMessage());
		}
		
		}while(seguir==true);
		
		System.out.println("Digite seu cidade: ");
		usuarioAtual.setCidade(entrada.nextLine());
		System.out.println("Digite seu senha: ");
		usuarioAtual.setSenha(entrada.nextLine());
		bancoDeDados.getListaUsuarios().add(usuarioAtual);

		System.out.println("usuarioAtual Cadastrado! Verifique seus dados");

		usuarioAtual.mostrarCadastro();

	}

	// métodos getters e setters
	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public BancoDeDados getBancoDeDados() {
		return bancoDeDados;
	}

	public void setBancoDeDados(BancoDeDados bancoDeDados) {
		this.bancoDeDados = bancoDeDados;
	}
	
	

}
