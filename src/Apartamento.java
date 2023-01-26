//Classe de Apartamente que é um tipo de localização, contém três atributos diferentes das outras duas classes e seus metodos getters e setters

public class Apartamento extends Locacao{
	private int quartos;
	private int banheiros;
	private int colchoes;
	
	//construtor
	public Apartamento(Usuario locador, String tipoDeLocacao, String cidadeDaLocacao, String comodidades, int quantidadeHospedes, int quartos, int banheiros, int colchoes) {
		super(locador, tipoDeLocacao,cidadeDaLocacao, comodidades, quantidadeHospedes);
		 this.setQuartos(quartos);
		 this.setBanheiros(banheiros);
		 this.setColchoes(colchoes);
	}
	
	public Apartamento(){
		
	}

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public int getBanheiros() {
		return banheiros;
	}

	public void setBanheiros(int banheiros) {
		this.banheiros = banheiros;
	}

	public int getColchoes() {
		return colchoes;
	}

	public void setColchoes(int colchoes) {
		this.colchoes = colchoes;
	}
	
	@Override
	public String toString() {
		return "\n Tipo da locação: "+ getTipoDeLocacao() + "\n " + "Cidade da locação: " + getCidadeDaLocacao() + "\n " + "Comodidades: " + getComodidades() + "\n " + "Quantidade de hóspedes permitidos: " + getQuantidadeHospedes()+ " hóspedes \n " + "Quantidade de Quartos: " + getQuartos() + " quartos" + "\n " + "Quantidade de banheiros: " + getBanheiros () + " banheiros" +"\n " + "Quantidade de colchoes: " + getColchoes() + " colchões \n ";
	}
}
