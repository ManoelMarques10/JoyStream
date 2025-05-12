package com.projetofaculdade.projetofaculdade.repository;

import com.projetofaculdade.projetofaculdade.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Interface de repositório para a entidade Usuario.
 * Estende CrudRepository, que fornece operações básicas de CRUD (Create, Read, Update, Delete).
 * Utiliza Spring Data JPA para simplificar o acesso ao banco de dados.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id ID do usuário
     * @return Objeto Usuario correspondente ao ID informado
     */
    Usuario findById(long id);

    /**
     * Realiza o login buscando um usuário por e-mail e senha.
     *
     * @param email E-mail do usuário
     * @param senha Senha do usuário
     * @return Objeto Usuario correspondente, se encontrado; caso contrário, null
     */
    @Query(value = "SELECT * FROM login.usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    Usuario login(@Param("email") String email, @Param("senha") String senha);

    /**
     * Verifica se existe um usuário com o e-mail informado.
     *
     * @param email E-mail do usuário
     * @return Objeto Usuario correspondente, se encontrado; caso contrário, null
     */
    @Query(value = "SELECT * FROM login.usuario WHERE email = :email", nativeQuery = true)
    Usuario findByEmail(@Param("email") String email);
}