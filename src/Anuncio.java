import java.util.ArrayList;
import java.util.List;

//Classe Anuncio tem seus atributos e os m�todos getters e setters de cada atributo
public class Anuncio{
	private Locacao locacao;
	private String titulo;
	private double preco;
	private String comentariosAdicionais;
	private int codigo;
	private String data;
	private List<String> listaDatas = new ArrayList<String>();
	

	public Anuncio(Locacao locacao, String titulo, double preco, String comentariosAdicionais, int codigo, List<String> listaDatas) {
		this.setLocacao(locacao);
		this.setTitulo(titulo);
		this.setPreco(preco);
		this.setComentariosAdicionais(comentariosAdicionais);
		this.setCodigo(codigo);
		this.setListaDatas(listaDatas);
	}

	public Anuncio() {
		this.setLocacao(null);
		this.setTitulo("");
		this.setPreco(0);
		this.setComentariosAdicionais("");
		this.setCodigo(0);
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String getComentariosAdicionais() {
		return comentariosAdicionais;
	}

	public void setComentariosAdicionais(String comentariosAdicionais) {
		this.comentariosAdicionais = comentariosAdicionais;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<String> getListaDatas() {
		return listaDatas;
	}

	public void setListaDatas(List<String> listaDatas) {
		this.listaDatas = listaDatas;
	}
	
	public void setData(String data) {
		this.listaDatas.add(data);
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public String toString() {
		if(getLocacao() instanceof Barco) {
			Barco barco = (Barco) getLocacao();
			return "\n Tipo da loca��o: "+ getLocacao().getTipoDeLocacao() + "\n " + "Cidade da loca��o: " + getLocacao().getCidadeDaLocacao() + "\n " + "Comodidades: " + getLocacao().getComodidades() + "\n " + "Quantidade de h�spedes permitidos: " + getLocacao().getQuantidadeHospedes()+ " h�spedes \n " + "Valor do passeio: " + barco.getValorPasseio()+ "\n T�tulo: "+ getTitulo()+ "\n Pre�o: "+ getPreco() + "\n Coment�rios adicionais: "+ getComentariosAdicionais() + "\n C�digo: "+ getCodigo();
		}
		
		if(getLocacao() instanceof Apartamento) {
			Apartamento apartamento = (Apartamento) getLocacao();
			return "\n Tipo da loca��o: "+ getLocacao().getTipoDeLocacao() + "\n " + "Cidade da loca��o: " + getLocacao().getCidadeDaLocacao() + "\n " + "Comodidades: " + getLocacao().getComodidades() + "\n " + "Quantidade de h�spedes permitidos: " + getLocacao().getQuantidadeHospedes()+ " h�spedes \n " + "Quarto: " + apartamento.getQuartos()+ " quartos\n" + " Banheiros: " + apartamento.getBanheiros() + " banheiros \n" + " Colch�es: "+ apartamento.getColchoes() + " colch�es" + "\n T�tulo: "+ getTitulo()+ "\n Pre�o: "+ getPreco() + "\n Coment�rios adicionais: "+ getComentariosAdicionais() + "\n C�digo: "+ getCodigo();
		}
		
		if(getLocacao() instanceof Quarto) {
			Quarto quarto = (Quarto) getLocacao();
			return "\n Tipo da loca��o: "+ getLocacao().getTipoDeLocacao() + "\n " + "Cidade da loca��o: " + getLocacao().getCidadeDaLocacao() + "\n " + "Comodidades: " + getLocacao().getComodidades() + "\n " + "Quantidade de h�spedes permitidos: " + getLocacao().getQuantidadeHospedes()+ " h�spedes \n " + "Colch�es: " + quarto.getColchoes() + " colch�es" + "\n T�tulo: "+ getTitulo()+ "\n Pre�o: "+ getPreco() + "\n Coment�rios adicionais: "+ getComentariosAdicionais() + "\n C�digo: "+ getCodigo();
		}
		return null;
	}

	public void exibirDatasDisponiveis() {
		System.out.println("\n--------------Datas Dispon�veis--------------");
		for( int x=0; x<listaDatas.size(); x++) {
			System.out.printf(" [%d] Data: [ %s ]\n", x+1, listaDatas.get(x));
		}
	}
	
}
