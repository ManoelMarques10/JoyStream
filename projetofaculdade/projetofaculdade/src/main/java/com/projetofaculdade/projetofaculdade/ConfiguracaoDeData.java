package com.projetofaculdade.projetofaculdade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Classe de configuração da fonte de dados e do provedor JPA.
 * Define como a aplicação se conecta ao banco de dados MySQL e como o JPA deve se comportar.
 */
@Configuration
public class ConfiguracaoDeData {

    /**
     * Bean responsável por configurar a conexão com o banco de dados MySQL.
     * Aqui são definidos o driver JDBC, a URL do banco, o nome de usuário e a senha.
     *
     * @return DataSource configurado com os parâmetros de conexão
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Driver JDBC para MySQL 8+
        dataSource.setUrl("jdbc:mysql://localhost:3306/login?useTimezone=true&serverTimezone=UTC"); // URL de conexão com o banco
        dataSource.setUsername("root"); // Usuário do banco
        dataSource.setPassword("colocarsenhadomysql"); // Senha do banco (⚠️ Evite deixar isso exposto em produção)
        return dataSource;
    }

    /**
     * Bean responsável por configurar o provedor JPA (Hibernate).
     * Define o tipo de banco, se o SQL será exibido no console, se as tabelas devem ser geradas automaticamente, etc.
     *
     * @return JpaVendorAdapter configurado para uso com o Hibernate e MySQL
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL); // Define o tipo de banco de dados
        adapter.setShowSql(true); // Mostra as queries SQL geradas no console
        adapter.setGenerateDdl(true); // Gera automaticamente as estruturas (DDL) no banco
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect"); // Dialeto específico do MySQL 8
        adapter.setPrepareConnection(true); // Prepara a conexão automaticamente
        return adapter;
    }
}