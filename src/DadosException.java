public class DadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DadosException (String msg) {
		super(msg);
	}
	
	public static void compararCPF(BancoDeDados bancoDeDados, String cPF) {
		for(int i=0; i<bancoDeDados.getListaUsuarios().size();  i++) {
			if(cPF.equals(bancoDeDados.getListaUsuarios().get(i).getCPF())) {
				throw new DadosException("Este CPF já está cadastrado!");
			}
		}
	}
	
	public static void compararEmail(BancoDeDados bancoDeDados, String email) {
		for(int i=0; i<bancoDeDados.getListaUsuarios().size();  i++) {
			if(email.equals(bancoDeDados.getListaUsuarios().get(i).getEmail())) {
				throw new DadosException("Este email já está cadastrado!");
			}
		}
	}
	
	public static void compararCodigo(BancoDeDados bancoDeDados,int codigo) {
		for(int i=0; i<bancoDeDados.getListaAnuncios().size();  i++) {
			if(codigo == bancoDeDados.getListaAnuncios().get(i).getCodigo()) {
				throw new DadosException("Este código já está cadastrado!");
			}
		}
	}
}
