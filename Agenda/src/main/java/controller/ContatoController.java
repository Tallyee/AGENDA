package controller;

import exception.ParametroInvalidoException;
import model.Contato;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContatoController {

        private List<Contato> contatos = new ArrayList<>();

    public void adicionarContato(String nome, String telefone, String email, LocalDate dataNascimento)
            throws ParametroInvalidoException{
        if (nome == null || nome.trim().isEmpty()) {
            throw new ParametroInvalidoException("O campo nome é obrigatório");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new ParametroInvalidoException("O campo telefone é obrigatório");
        }
        if (dataNascimento == null) {
            throw new ParametroInvalidoException("O campo data de nascimento é obrigatória");
        }

        contatos.add(new Contato(nome, telefone, email, dataNascimento));
    }

    public List<Contato> listarContatos() {
        return contatos.stream()
                .sorted(Comparator.comparing(Contato::getNome))
                .toList();
    }

    public boolean removerContato(int id) {
        return contatos.removeIf(c -> c.getId() == id);
    }

    public Contato buscarContatoPorId(int id) {
        return contatos.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void editarContato(int id, String nome, String telefone, String email, LocalDate dataNascimento) {
        Contato contato = buscarContatoPorId(id);
        if (contato != null) {
            contato.setNome(nome);
            contato.setTelefone(telefone);
            contato.setEmail(email);
            contato.setDataNascimento(dataNascimento);
        } else {
            System.out.println("Contato não encontrado.");
        }
    }


    public List<Contato> pesquisarPorLetra(String letra) {
        return contatos.stream()
                .filter(c -> c.getNome().toLowerCase().startsWith(letra.toLowerCase()))
                .sorted(Comparator.comparing(Contato::getNome))
                .toList();
    }

    public List<Contato> listarAniversariantesDoMes(int mes) {
        return contatos.stream()
                .filter(c -> c.getDataNascimento().getMonthValue() == mes)
                .sorted(Comparator.comparing(Contato::getDataNascimento))
                .toList();
    }


    }
