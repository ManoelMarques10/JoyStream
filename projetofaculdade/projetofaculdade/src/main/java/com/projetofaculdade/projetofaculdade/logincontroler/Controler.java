package com.projetofaculdade.projetofaculdade.logincontroler;

import com.projetofaculdade.projetofaculdade.model.Usuario;
import com.projetofaculdade.projetofaculdade.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Classe responsável por controlar as rotas relacionadas ao login, logout e cadastro de usuários.
 */
@Controller
public class Controler {

    /** Injeção do repositório de usuários para acesso ao banco de dados. */
    @Autowired
    private UsuarioRepository ur;

    /**
     * Exibe a página de login.
     *
     * @return nome da view "login"
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Exibe a página inicial (dashboard) após login.
     *
     * @return nome da view "index"
     */
    @GetMapping("/")
    public String dashboard() {
        return "index";
    }

    /**
     * Processa o login do usuário.
     *
     * @param usuario objeto com email e senha preenchidos
     * @param model objeto para enviar mensagens à view
     * @param session sessão atual do usuário
     * @return redirecionamento para "/" se sucesso, ou retorna à página de login com erro
     */
    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, HttpSession session) {
        // Verifica se o usuário existe no banco com email e senha
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());

        if (usuarioLogado != null) {
            // Armazena usuário na sessão e redireciona para a página inicial
            session.setAttribute("usuario", usuarioLogado);
            return "redirect:/";
        }

        // Caso não encontre o usuário, exibe mensagem de erro
        model.addAttribute("erro", "Usuário Inválido!");
        return "login";
    }

    /**
     * Realiza o logout do usuário.
     *
     * @param session sessão atual a ser invalidada
     * @return redireciona para a página de login
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão do usuário
        return "redirect:/login";
    }

    /**
     * Exibe o formulário de cadastro de usuário.
     *
     * @return nome da view "cadastro"
     */
    @GetMapping("/cadastroUsuario")
    public String cadastro() {
        return "cadastro";
    }

    /**
     * Processa o cadastro de um novo usuário.
     *
     * @param usuario objeto com os dados do usuário
     * @param result resultado da validação do formulário
     * @param model objeto para envio de mensagens à view
     * @param session sessão HTTP (não utilizada aqui, mas pode ser útil)
     * @param confirmarSenha senha repetida para validação
     * @return redireciona para o login se sucesso, ou retorna à tela de cadastro com erro
     */
    @PostMapping("/cadastroUsuario")
    public String cadastroUsuario(
            @Valid Usuario usuario,
            BindingResult result,
            Model model,
            HttpSession session,
            String confirmarSenha) {

        // Verifica se há erros na validação dos campos
        if (result.hasErrors()) {
            model.addAttribute("erro", "Preencha todos os campos corretamente.");
            return "cadastro";
        }

        // Verifica se as senhas coincidem
        if (!usuario.getSenha().equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            return "cadastro";
        }

        // Verifica se já existe um usuário com o mesmo e-mail
        if (ur.findByEmail(usuario.getEmail()) != null) {
            model.addAttribute("erro", "Este e-mail já está cadastrado.");
            return "cadastro";
        }

        // Salva o usuário no banco
        ur.save(usuario);
        return "redirect:/login";
    }
}