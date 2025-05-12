package com.projetofaculdade.projetofaculdade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

/**
 * Classe que representa a entidade Usuario no banco de dados.
 * Utiliza anotações JPA para mapeamento e validações para garantir que os campos não estejam vazios.
 */
@Entity
public class Usuario {

    // Identificador único do usuário, gerado automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Nome do usuário. O campo é obrigatório.
    @NotEmpty
    private String nome;

    // E-mail do usuário. O campo é obrigatório.
    @NotEmpty
    private String email;

    // Senha do usuário. O campo é obrigatório.
    @NotEmpty
    private String senha;

    // Getter para o ID (não tem setter pois o ID é gerado automaticamente)
    public long getId() {
        return id;
    }

    // Getter e Setter para o nome do usuário
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para o e-mail do usuário
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para a senha do usuário
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}