//Classe de Barco que � um tipo de localiza��o, cont�m um atributo diferente das outras duas classes e seu metodo get e set.

public class Barco extends Locacao {
	private double valorPasseio;
	
	//construtor de Barco
	public Barco(Usuario locador, String tipoDeLocacao, String cidadeDaLocacao, String comodidades, int quantidadeHospedes, double valorPasseio) {
		super(locador, tipoDeLocacao,cidadeDaLocacao, comodidades, quantidadeHospedes);
		this.setValorPasseio(valorPasseio);
		
	}
	
	public Barco() {
		
	}

	public double getValorPasseio() {
		return valorPasseio;
	}

	public void setValorPasseio(double valorPasseio) {
		this.valorPasseio = valorPasseio;
	}
	
	public String toString() {
		return "\n Tipo da loca��o: "+ getTipoDeLocacao() + "\n " + "Cidade da loca��o: " + getCidadeDaLocacao() + "\n " + "Comodidades: " + getComodidades() + "\n " + "Quantidade de h�spedes permitidos: " + getQuantidadeHospedes()+ " h�spedes \n " + "Valor do passeio: " + getValorPasseio();
	}		
}
