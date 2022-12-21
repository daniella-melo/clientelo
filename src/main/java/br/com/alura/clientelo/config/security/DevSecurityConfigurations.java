package br.com.alura.clientelo.config.security;

import br.com.alura.clientelo.repository.usuario.UsuarioJpaRepository;
import br.com.alura.clientelo.service.auth.AutenticacaoService;
import br.com.alura.clientelo.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().csrf().disable();
    }
}
