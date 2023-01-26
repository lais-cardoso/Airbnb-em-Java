import java.util.ArrayList;
import java.util.List;

//Classe Anuncio tem seus atributos e os métodos getters e setters de cada atributo
public class Usuario {
	private String nome;
	private String sobrenome;
	private String cPF;
	private String cidade;
	private String senha;
	private String email;
	private List<Anuncio> listaMeusAnuncios = new ArrayList<Anuncio>() ;
	private List<Anuncio> listaMeusAlugueis = new ArrayList<Anuncio>();
	private List<Anuncio> listaMinhasVendas = new ArrayList<Anuncio>();
	
	
	public Usuario( String nome, String sobrenome, String cPF, String cidade, String senha, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cPF = cPF;
		this.cidade = cidade;
		this.senha = senha;
		this.email = email;
		}
	public Usuario() {
		}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCPF() {
		return cPF;
	}

	public void setCPF(String cPF) {
		this.cPF = cPF;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Anuncio> getListaMeusAnuncios() {
		return listaMeusAnuncios;
	}
	public void setListaMeusAnuncios(List<Anuncio> listaMeusAnuncios) {
		this.listaMeusAnuncios = listaMeusAnuncios;
	}

	public List<Anuncio> getListaMeusAlugueis() {
		return listaMeusAlugueis;
	}
	public void setListaMeusAlugueis(List<Anuncio> listaMeusAlugueis) {
		this.listaMeusAlugueis = listaMeusAlugueis;
	}
	
	public List<Anuncio> getListaMinhasVendas() {
		return listaMinhasVendas;
	}
	public void setListaMinhasVendas(List<Anuncio> listaMinhasVendas) {
		this.listaMinhasVendas = listaMinhasVendas;
	}
	
	public void mostrarCadastro(){
		System.out.println("Nome: " + getNome() + "\n ");
		System.out.println("Sobrenome: " + getSobrenome() + "\n ");
		System.out.println("CPF: " + getCPF() + "\n ");
		System.out.println("Cidade: " + getCidade() + "\n ");
		System.out.println("Senha: " + getSenha() + "\n ");
		System.out.println("Email: " + getEmail() + "\n ");
	}
	
	//to String criado para mostrar para os usuários(não contém senha e CPF)
		public String toString(){
			return "Nome: " + getNome() + "\n " + "Sobrenome: " + getSobrenome() + "\n " + "Cidade: " + getCidade()  + "\n " + "Email: " + getEmail() + "\n ";
		}
}
