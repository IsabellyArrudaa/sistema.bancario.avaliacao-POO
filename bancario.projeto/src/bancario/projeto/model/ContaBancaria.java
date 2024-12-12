package bancario.projeto.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.io.Serializable;

/**
 * Classe que representa uma conta bancária em um sistema bancário.
 * Implementa Serializable para permitir a persistência dos dados em arquivo.
 */
public class ContaBancaria implements Serializable {

	 private static final long serialVersionUID = 1L; // Controle de versão para serialização
	 
	private Integer numeroConta; // Número da conta bancária
	private float saldo; // Saldo da conta
	private LocalDateTime dataAbertura; // Data de abertura da conta
	private boolean status; // Status da conta (ativa/inativa)

	// Construtor vazio
	public ContaBancaria() {
		
	}
	
	// Construtor com número da conta
	public ContaBancaria(Integer numero) {
		this.numeroConta = numero;
		this.saldo = 0f;
		this.dataAbertura = LocalDateTime.now();
		this.status = true;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(numeroConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBancaria other = (ContaBancaria) obj;
		return Objects.equals(numeroConta, other.numeroConta);
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return "ContaBancaria [numeroConta=" + numeroConta + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura
				+ ", status=" + status + "]";
	}
	

	//Método para realizar um depósito em uma conta bancária.
	public void depositar(float quantia) {
		if (status) { // Verifica se a conta está ativa
			if (quantia > 0) { //o calor do saque deve ser positivo
				this.saldo += quantia; // Atualiza o saldo
				System.out.println("Deposito realizado com sucesso.");
			} else {
				System.err.println("Valor invalido para deposito.");
			}
		} else {
			System.err.println("Operação não permitida. Conta desativada.");
		}
	}

	// Método para realizar um saque em uma conta bancária.
	public void sacar(float quantia) {
		if (status) { // Verifica se a conta está ativa
			if (quantia > 0) {  // O valor do saque deve ser positivo
				if (this.saldo >= quantia) {// Verifica se há saldo suficiente
					this.saldo -= quantia;  // Atualiza o saldo
					System.out.println("Saque realizado com sucesso!");
				} else {
					System.err.println("Saldo insuficiente.");
				}
			} else {
				System.err.println("Valor invalido para saque.");
			}
		} else {
			System.err.println("Operação não permitida. Conta desativada.");
		}

	}
	
    //Método para transferir um valor de uma conta para outra.
	public void transferir(ContaBancaria c, float quantia) {
		if (status && c.isStatus()) { // Verifica se ambas as contas estão ativas
			if (quantia < 0) {  // O valor da transferência deve ser positivo
				System.err.println("Valor invalido para transferencia.");
			} else if (quantia <= saldo) {// Verifica se há saldo suficiente
				this.saldo -= quantia;  // Deduz o valor do saldo da conta atual
				c.saldo += quantia; // Adiciona o valor ao saldo da conta de destino
			} else
				System.err.println("Saldo insuficiente para realizar a transferencia.");
		} else {
			System.err.println("Operacao nao pode ser realizada entre contas desativadas.");
		}

	}
	
	// exibe o saldo atual da conta bancária.
	 public void exibirSaldo() {
	        System.out.println("Seu saldo atual é: R$ " + saldo );
	    }
	
}
