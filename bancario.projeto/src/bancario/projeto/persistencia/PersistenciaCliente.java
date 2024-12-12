package bancario.projeto.persistencia;

import java.util.ArrayList; // Importa a classe ArrayList para armazenar os clientes
import bancario.projeto.model.Cliente; // Importa a classe Cliente
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersistenciaCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Cliente> clientes;

	public PersistenciaCliente() {
		clientes = new ArrayList<Cliente>();
	}
	
	 // Método para adicionar um cliente
	public void addCliente(Cliente c) {
		if (clientes.contains(c)) {
			System.out.println("Já Cadastrado!");
		} else {
			clientes.add(c);
			System.out.println("Cliente Cadastrado com Sucesso!");
		}
	}


	// Método para remover um cliente
	public void rempoverCliente(Cliente c) {
		if (clientes.contains(c)) {
			clientes.remove(c);
			System.out.println("cliente removido com sucesso");
		} else
			System.out.println("cliente nao localizado");
	}

	// Método para localizar um cliente pelo CPF
	public Cliente localizarClientePorCpf(String cpf) {
		Cliente temp = new Cliente();
		temp.setCpf(cpf);
		if (clientes.contains(temp)) {
			int index = clientes.indexOf(temp);
			temp = clientes.get(index);
			return temp;
		} else
			return null;
	}

	// Método para atualizar os dados de um cliente
	public void atualizarCliente(Cliente c) {
		if (clientes.contains(c)) {
			int index = clientes.indexOf(c);
			clientes.set(index, c);
			System.out.println("cliente autalizado com sucesso!");
		} else
			System.out.println("cliente n�o encontrado");
	}
	
	 // Método para listar todos os clientes cadastrados
    public void listarClientes() {
        if (clientes.isEmpty()) { // Verifica se a lista de clientes está vazia
            System.out.println("Nenhum cliente cadastrado."); // Exibe mensagem se não houver clientes
        } else {
            System.out.println("Clientes cadastrados:");
            for (Cliente cliente : clientes) { // Percorre a lista de clientes e exibe cada um
                System.out.println(cliente); // Exibe os dados do cliente usando o método toString()
            }
        }
    }
	
    public void salvarDadosEmArquivo() {
        try (FileOutputStream fos = new FileOutputStream("clientes.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	 @SuppressWarnings("unchecked")
	public void carregarDadosDeArquivo() {
        try (FileInputStream fis = new FileInputStream("clientes.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            clientes = (ArrayList<Cliente>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado. Criando novo arquivo.");
            clientes = new ArrayList<Cliente>(); // Inicializa a lista vazia
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
