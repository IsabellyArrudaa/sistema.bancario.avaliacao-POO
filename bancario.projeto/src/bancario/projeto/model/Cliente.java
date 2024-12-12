package bancario.projeto.model; //Define o pacote onde a classe se encontra, organizando a estrutura

import java.util.ArrayList; //Importa a classe ArrayList para armazenar a lista de contas do cliente
import java.util.Objects;
import java.io.Serializable; 

public class Cliente implements Serializable {
	// Atributos da classe Cliente
	 private static final long serialVersionUID = 1L; // Controle de versão para serialização
	 
	private String cpf;  // Armazena o número de CPF do cliente
	private String nome;// Armazena o nome completo do cliente
	
	private ArrayList<ContaBancaria> contas; // Lista de contas bancárias associadas ao cliente
	
	public Cliente() {//construtor vazio 
		
	}
	
	public Cliente(String cpf, String nome) { // Construtor com parâmetros: cria um cliente com CPF e nome
		this.cpf = cpf;
		this.nome = nome;
		contas = new ArrayList<>();
	}

	// Método para adicionar uma conta ao cliente
	public void addConta(ContaBancaria c){
		if(contas.contains(c)) { // Verifica se a conta já está associada ao cliente
			System.out.println("Conta já cadastrada.");
		}else {
			contas.add(c); //adiciona a conta
			System.out.println("Conta cadastrada com sucesso!");
		}
		
	}
	
	// Método para remover uma conta do cliente
	public void rempoverConta(ContaBancaria c) {
		if(contas.contains(c)){// Verifica se a conta já está associada ao cliente
			contas.remove(c); // remove a conta
			System.out.println("conta removida com sucesso");
		}else
			System.out.println("conta nao localizada");
	}
	
	// Método para localizar uma conta pelo número
	public ContaBancaria localizarContaPorNumero(Integer numero) {
		ContaBancaria temp = new ContaBancaria(numero); //criada uma nova instância
		if(contas.contains(temp)) { //verifica se a lista de contas contém a conta temporária criada
			int index = contas.indexOf(temp); // o método indexOf retorna o índice da conta na lista.
			temp = contas.get(index);//A conta encontrada é obtida da lista utilizando o índice obtido no passo anterior e armazenada novamente na variável temp
			return temp; //A conta encontrada é retornada pelo método
		}else
		return null;// se não retorna nulo
	}
	
	public void atualizarConta(ContaBancaria c) {
		if(contas.contains(c)) {//verifica se a lista de contas contém c
			int index = contas.indexOf(c); // o método indexOf retorna o índice da conta na lista.
			contas.set(index, c);// conta é atualizada
			System.out.println("conta autalizada com sucesso!");
		}else
			System.out.println("conta não encontrada");
	}
	
	 //calcular o saldo total de todas as contas bancárias associadas a um cliente
	 public double balancoEntreContas() {
			double ValorSaldo = 0.0; //Uma variável do tipo double é criada e inicializada com o valor 0. 
			for (int i = 0; i < contas.size(); i++) { // é utilizado para percorrer cada conta da lista c
				ContaBancaria c = contas.get(i); //recebe a conta atual da lista.
				ValorSaldo += c.getSaldo();//O saldo da conta atual é adicionado ao valor total acumulado em ValorSaldo.
			}
			System.out.print("Balanco entre contas: RS" + ValorSaldo );
			return ValorSaldo;
	    }
	 
	// Método para listar as contas associadas ao cliente
	    public void listarContas() {
	        if (contas.isEmpty()) {
	            System.out.println("Nenhuma conta cadastrada.");
	        } else {
	            System.out.println("Contas cadastradas:");
	            contas.forEach(System.out::println);
	        }
	    }
	 
	// Getters e Setters para os atributos serve para encapsular os dados e garantir a integridade dos objetos.
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<ContaBancaria> getContas() {
		return contas;
	}
	public void setContas(ArrayList<ContaBancaria> contas) {
		this.contas = contas;
	}
	
	
	 // Implementação de hashCode() e equals() para comparação de objetos
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	// Implementação de toString() para representação textual do objeto
	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", contas=" + contas + "]";
	}
	
}
