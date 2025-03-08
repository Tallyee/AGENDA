package view;

import controller.ContatoController;
import exception.ParametroInvalidoException;
import util.TecladoUtil;

import java.time.LocalDate;

public class Sistema {
    private static final ContatoController agenda = new ContatoController();
    private static boolean sair = false;


    public static void main(String[] args) {
        while (!sair) {
            mostrarMenu();
            int opcao = TecladoUtil.lerInteiro("Informe uma Opcao:");
            executaAcao(opcao);
        }

    }

    private static void executaAcao(int opcao) {
        switch (opcao) {
            case 1:
adicionarContato();
                break;
            case 2:
listarContatos();
                break;
            case 3:
removerContato();
                break;
            case 4:
editarContato();
                break;
            case 5:
pesquisarPorLetra();
                break;
            case 6:
listarAniversariantes();
                break;
            case 7:
                sair = true;
                break;
            default:
                System.out.println("Opção invalida!");
                break;
        }

    }

    public static void mostrarMenu(){
        System.out.println("--- Insira uma opção ---");
        System.out.println("1. Adicionar contato:");
        System.out.println("2. Listar contatos:");
        System.out.println("3. Remover contato:");
        System.out.println("4. Alterar/editar contato:");
        System.out.println("5. Pesquisar contato por letra:");
        System.out.println("6. Listar aniversariantes do mês:");
        System.out.println("7. Sair");
    }
    private static void adicionarContato() {
        try {
            String nome = TecladoUtil.lerString("Nome: ");
            String telefone = TecladoUtil.lerString("Telefone: ");
            String email = TecladoUtil.lerString("E-mail: ");
            LocalDate dataNascimento = TecladoUtil.lerData("Data de Nascimento");

            agenda.adicionarContato(nome, telefone, email, dataNascimento);
            System.out.println("Contato adicionado com sucesso!");
        } catch (ParametroInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarContatos() {
        System.out.println("\n===== LISTA DE CONTATOS =====");
        agenda.listarContatos();
    }

    private static void removerContato() {
        int idRemover = TecladoUtil.lerInteiro("Digite o ID do contato para remover: ");
        if (agenda.removerContato(idRemover)) {
            System.out.println("Contato removido com sucesso.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void editarContato() {
        int idEditar = TecladoUtil.lerInteiro("Digite o ID do contato para editar: ");
        String novoNome = TecladoUtil.lerString("Novo Nome: ");
        String novoTelefone = TecladoUtil.lerString("Novo Telefone: ");
        String novoEmail = TecladoUtil.lerString("Novo E-mail: ");
        LocalDate novaDataNascimento = TecladoUtil.lerData("Nova Data de Nascimento");
        agenda.editarContato(idEditar, novoNome, novoTelefone, novoEmail, novaDataNascimento);
    }

    private static void pesquisarPorLetra() {
        String letra = TecladoUtil.lerString("Digite a letra inicial: ");
        agenda.pesquisarPorLetra(letra);
    }

    private static void listarAniversariantes() {
        int mes = TecladoUtil.lerInteiro("Digite o número do mês: ");
        agenda.listarAniversariantesDoMes(mes);
    }
}
