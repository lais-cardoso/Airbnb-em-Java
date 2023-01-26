public class SymbolException extends RuntimeException {
	// Para tratar erros da ausencia do @ na hora de digitar

	private static final long serialVersionUID = 1L;

	public SymbolException(String msg) {
		super(msg);
	}

	public static void verificarEmail(String email) {
		String[] vet = email.split("@");
		if (vet.length != 2 && !email.equals("0")) {
			throw new SymbolException("Formato de Email incorreto!");

		}
	}

	public static void verificarData(String data) {
		String[] vet = data.split("-");
		String[] vet2 = data.split("/");

		for (int i = 0; i < vet2.length; i++) {
			
				if (vet2[i].length() != 5 || vet[i].length() != 2) {
						throw new SymbolException(
								"Formato de Data incorreto!(Utilize este formato DD/MM/YYYY - DD/MM/YYYY)");
				}
		}

	}
}
