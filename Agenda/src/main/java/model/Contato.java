package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private static int contador;

    public Contato(){

    }

    public Contato(String nome, String telefone, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.id = contador++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID: " + id +
                " | Nome: " + nome +
                " | Telefone: " + telefone +
                " | E-mail: " + email +
                " | Nascimento: " + dataNascimento.format(formatter);
    }
}
