import java.util.InputMismatchException;
import java.util.Scanner;

//Tela do usuario aparece para o usuário logo depois da Tela login
public class TelaUsuario implements Tela {
	private TelaLogin telaLogin;
	private TelaLocatario telaLocatario;
	private TelaLocador telaLocador;
	private Scanner sc = new Scanner(System.in);
	
	//construtor de Tela Usuário
	public TelaUsuario(TelaLogin telaLogin) {
		this.telaLogin = telaLogin;
 	}
	
	
	//método sobrescrito onde o usuário escolhe entre ser anfitrião ou hóspede
	public void exibirTela() {
		
		
		//Usuario usuarioAtual = telaLogin.getUsuarioAtual();
		int opcao=-1;
		
		do {
			System.out.println("\nSeja bem vindo(a) ao Menu inicial Airbnb: \n");
			System.out.println("Entrar como: ");
			System.out.println("[1]Locatário");
			System.out.println("[2]Locador");
			System.out.println("[3]Sair");
			try {
			opcao = sc.nextInt();
			
			switch(opcao) {
			case 1:
				telaLocatario = new TelaLocatario(this);
				telaLocatario.exibirTela();
				break;
			
			case 2: 
				telaLocador = new TelaLocador(this);
				telaLocador.exibirTela();
				break;
			case 3:
				System.out.println("Espero ter ajudado nas suas viagens. Volte sempre ;) \n\n\n");
				telaLogin.exibirTela();
				break;
			default:
				System.out.println("Opção não existente, tente novamente");
				break;
			}
			
			}catch(InputMismatchException e) {
				System.out.println("Você deve digitar uma das opções (um número!)");
				sc.nextLine();
			}
				
			}while(opcao!=3);
		}


		public TelaLogin getTelaLogin() {
			return telaLogin;
		}
	
	
		public void setTelaLogin(TelaLogin telaLogin) {
			this.telaLogin = telaLogin;
		}
	
}