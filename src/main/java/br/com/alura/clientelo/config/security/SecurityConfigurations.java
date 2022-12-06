package br.com.alura.clientelo.config.security;

import br.com.alura.clientelo.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    //configuracoes de autenticacoes
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //configuracoes de autorizacoes (url, perfis, etc)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/pedidos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/pedidos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/produtos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/produtos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();;
    }

    //configuracoes de recursos estaticos (js, css, imagens, etc)
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    public String getEncodedPassword(String rawSenha){
        return new BCryptPasswordEncoder().encode(rawSenha);
    }
}
