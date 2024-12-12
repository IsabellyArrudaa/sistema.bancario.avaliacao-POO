package bancario.projeto.app;
import java.util.Scanner;
import bancario.projeto.persistencia.PersistenciaCliente;
import bancario.projeto.model.*;

public class Programa {

	public static void main(String[] args) {
		
		PersistenciaCliente persistencia = new PersistenciaCliente();
		Scanner sc = new Scanner(System.in);	
		boolean sair = true;
		Integer opcao = 0;
		
		 // Carregar dados de clientes previamente salvos (caso existam)
        persistencia.carregarDadosDeArquivo();

		// Menu principal
		while(sair) {
			System.out.println("\n\n\nBem-vindo ao Sistemma Bancario! O que você gostaria de fazer?\n"
                    + "\n1 - Cadastrar Cliente\n"
                    + "2 - Listar Clientes\n"
                    + "3 - Consultar Cliente por CPF\n"
                    + "4 - Remover Cliente\n"
                    + "5 - Criar Conta\n"
                    + "6 - Listar Contas de um Cliente\n"
                    + "7 - Realizar Depósito\n"
                    + "8 - Realizar Saque\n"
                    + "9 - Transferir Entre Contas\n"
                    + "10 - Consultar Saldo\n"
                    + "11 - Consultar Balanço Total\n"
                    + "12 - Salvar e Sair\n"
                    + "\nEscolha uma opção: ");
			
			opcao = sc.nextInt();
			sc.nextLine(); 
			
			switch(opcao) {
			case 1:{
				// Cadastrar Cliente
				System.out.println("Informe o CPF do Cliente: ");
				String cpf = sc.next();
				System.out.println("Informe o Nome do Cliente: ");
				String nome = sc.next();
				persistencia.addCliente(new Cliente(cpf,nome));
				break;
			}
			case 2:{
				// Listar Clientes Cadastrados
				 persistencia.listarClientes();
				break;
			}
			case 3:{
				// Consultar Cliente por CPF
				System.out.print("Informe o CPF do Cliente: ");
                String cpfConsulta = sc.nextLine();
                Cliente clienteConsulta = persistencia.localizarClientePorCpf(cpfConsulta);
                if (clienteConsulta != null) {
                    System.out.println(clienteConsulta);
                } else {
                    System.out.println("Cliente não encontrado.");
                }
				break;
			}
			case 4:{
				// Remover Cliente
                System.out.print("Informe o CPF do Cliente a ser removido: ");
                String cpfRemover = sc.nextLine();
                Cliente clienteRemover = persistencia.localizarClientePorCpf(cpfRemover);
                if (clienteRemover != null) {
                    persistencia.rempoverCliente(clienteRemover);
                } else {
                    System.out.println("Cliente não encontrado.");
                }
				break;
			}
			case 5:{
				// Criar Conta
                System.out.print("Informe o CPF do Cliente: ");
                String cpfClienteConta = sc.nextLine();
                Cliente clienteConta = persistencia.localizarClientePorCpf(cpfClienteConta);
                if (clienteConta != null) {
                    System.out.print("Informe o número da conta: ");
                    int numeroConta = sc.nextInt();
                    ContaBancaria conta = new ContaBancaria(numeroConta);
                    clienteConta.addConta(conta);
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
			}
			
			case 6:{
				// Listar Contas de um Cliente
                System.out.print("Informe o CPF do Cliente: ");
                String cpfListarContas = sc.nextLine();
                Cliente clienteListarContas = persistencia.localizarClientePorCpf(cpfListarContas);
                if (clienteListarContas != null) {
                    clienteListarContas.listarContas();
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
			}
			
			case 7:{
				// Realizar Depósito
                System.out.print("Informe o CPF do Cliente: ");
                String cpfDeposito = sc.nextLine();
                Cliente clienteDeposito = persistencia.localizarClientePorCpf(cpfDeposito);
                if (clienteDeposito != null) {
                    System.out.print("Informe o número da conta: ");
                    int numeroContaDeposito = sc.nextInt();
                    ContaBancaria contaDeposito = clienteDeposito.localizarContaPorNumero(numeroContaDeposito);
                    if (contaDeposito != null) {
                        System.out.print("Informe o valor do depósito: ");
                        float valorDeposito = sc.nextFloat();
                        contaDeposito.depositar(valorDeposito);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
			}
			
			case 8:{
				 // Realizar Saque
                System.out.print("Informe o CPF do Cliente: ");
                String cpfSaque = sc.nextLine();
                Cliente clienteSaque = persistencia.localizarClientePorCpf(cpfSaque);
                if (clienteSaque != null) {
                    System.out.print("Informe o número da conta: ");
                    int numeroContaSaque = sc.nextInt();
                    ContaBancaria contaSaque = clienteSaque.localizarContaPorNumero(numeroContaSaque);
                    if (contaSaque != null) {
                        System.out.print("Informe o valor do saque: ");
                        float valorSaque = sc.nextFloat();
                        contaSaque.sacar(valorSaque);
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
			}
			
			case 9:{
                 // Transferir Entre Contas
                 System.out.print("Informe o CPF do Cliente: ");
                 String cpfTransferencia = sc.nextLine();
                 Cliente clienteTransferencia = persistencia.localizarClientePorCpf(cpfTransferencia);
                 if (clienteTransferencia != null) {
                     System.out.print("Informe o número da conta de origem: ");
                     int numeroContaOrigem = sc.nextInt();
                     ContaBancaria contaOrigem = clienteTransferencia.localizarContaPorNumero(numeroContaOrigem);
                     if (contaOrigem != null) {
                         System.out.print("Informe o número da conta de destino: ");
                         int numeroContaDestino = sc.nextInt();
                         ContaBancaria contaDestino = clienteTransferencia.localizarContaPorNumero(numeroContaDestino);
                         if (contaDestino != null) {
                             System.out.print("Informe o valor da transferência: ");
                             float valorTransferencia = sc.nextFloat();
                             contaOrigem.transferir(contaDestino, valorTransferencia);
                             System.out.println("Transferência realizada com sucesso!");
                         } else {
                             System.out.println("Conta de destino não encontrada.");
                         }
                     } else {
                         System.out.println("Conta de origem não encontrada.");
                     }
                 } else {
                     System.out.println("Cliente não encontrado.");
                 }
                 break;
			}
			
			case 10:{
				 // Consultar Saldo
                System.out.print("Informe o CPF do Cliente: ");
                String cpfSaldo = sc.nextLine();
                Cliente clienteSaldo = persistencia.localizarClientePorCpf(cpfSaldo);
                if (clienteSaldo != null) {
                    System.out.print("Informe o número da conta: ");
                    int numeroContaSaldo = sc.nextInt();
                    ContaBancaria contaSaldo = clienteSaldo.localizarContaPorNumero(numeroContaSaldo);
                    if (contaSaldo != null) {
                        contaSaldo.exibirSaldo();
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
			}
			
			case 11:{
				// Consultar Balanço Total
                System.out.print("Informe o CPF do Cliente: ");
                String cpfBalanco = sc.nextLine();
                Cliente clienteBalanco = persistencia.localizarClientePorCpf(cpfBalanco);
                if (clienteBalanco != null) {
                    clienteBalanco.balancoEntreContas();
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
			}
			
			case 12:{
				 // Salvar e Sair
                persistencia.salvarDadosEmArquivo();
                System.out.println("Dados salvos. Saindo...");
                System.exit(0);
                break;
			}
			
			default:
			
				throw new IllegalArgumentException("Opção invalida" +opcao);
			}
		}
	}
}
