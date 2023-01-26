//Classe de Barco que � um tipo de localiza��o, cont�m um atributo diferente das outras duas classes e seu metodo get e set.
public class Quarto extends Locacao{
	private int colchoes;
	
	//construtor de Quarto
	public Quarto(Usuario locador, String tipoDeLocacao, String cidadeDaLocacao, String comodidades, int quantidadeHospedes, int colchoes) {
		super(locador, tipoDeLocacao,cidadeDaLocacao, comodidades, quantidadeHospedes);
		this.setColchoes(colchoes);
	}
	
	public Quarto() {
		
	}
	
	public int getColchoes() {
		return colchoes;
	}

	public void setColchoes(int colchoes) {
		this.colchoes = colchoes;
	}
	
	public String toString() {
		return "\n Tipo da loca��o: "+ getTipoDeLocacao() + "\n " + "Cidade da loca��o: " + getCidadeDaLocacao() + "\n " + "Comodidades: " + getComodidades() + "\n " + "Quantidade de h�spedes permitidos: " + getQuantidadeHospedes()+ " h�spedes \n " + "Quantidade de Quartos: " +"\n " + "Quantidade de colchoes: " + getColchoes() + " colch�es \n ";
	}
}
