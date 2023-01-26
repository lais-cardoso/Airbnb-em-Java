//Classe de Barco que é um tipo de localização, contém um atributo diferente das outras duas classes e seu metodo get e set.

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
		return "\n Tipo da locação: "+ getTipoDeLocacao() + "\n " + "Cidade da locação: " + getCidadeDaLocacao() + "\n " + "Comodidades: " + getComodidades() + "\n " + "Quantidade de hóspedes permitidos: " + getQuantidadeHospedes()+ " hóspedes \n " + "Valor do passeio: " + getValorPasseio();
	}		
}
