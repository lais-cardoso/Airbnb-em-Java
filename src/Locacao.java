public abstract class Locacao {
	private Usuario locador;
	private String tipoDeLocacao;
	private String cidadeDaLocacao;
	private String comodidades;
	private int quantidadeHospedes;
	
	
	public Locacao(Usuario locador, String tipoDeLocacao, String cidadeDaLocacao, String comodidades,int quantidadeHospedes) {
		this.locador = locador;
		this.tipoDeLocacao = tipoDeLocacao;
		this.cidadeDaLocacao = cidadeDaLocacao;
		this.comodidades = comodidades;
		this.setQuantidadeHospedes(quantidadeHospedes);
	}

	public Locacao() {
		
	}

	public Usuario getLocador() {
		return locador;
	}
	public void setLocador(Usuario locador) {
		this.locador = locador;
	}
	public String getTipoDeLocacao() {
		return tipoDeLocacao;
	}
	public void setTipoDeLocacao(String tipoDeLocacao) {
		this.tipoDeLocacao = tipoDeLocacao;
	}
	public String getCidadeDaLocacao() {
		return cidadeDaLocacao;
	}
	public void setCidadeDaLocacao(String cidadeDaLocacao) {
		this.cidadeDaLocacao = cidadeDaLocacao;
	}
	public String getComodidades() {
		return comodidades;
	}
	public void setComodidades(String comodidades) {
		this.comodidades = comodidades;
	}

	public int getQuantidadeHospedes() {
		return quantidadeHospedes;
	}

	public void setQuantidadeHospedes(int quantidadeHospedes) {
		this.quantidadeHospedes = quantidadeHospedes;
	}

}
