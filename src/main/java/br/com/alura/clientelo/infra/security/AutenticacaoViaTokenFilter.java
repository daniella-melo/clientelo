package br.com.alura.clientelo.infra.security;

import br.com.alura.clientelo.dominio.model.usuario.Usuario;
import br.com.alura.clientelo.aplicacao.repository.usuario.UsuarioJpaRepository;
import br.com.alura.clientelo.aplicacao.service.token.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

   //não da para colocar autowired
     private TokenService tokenService;
    private UsuarioJpaRepository usuarioJpaRepository;
    public AutenticacaoViaTokenFilter(TokenService tokenService,
                                      UsuarioJpaRepository usuarioJpaRepository) {
        this.tokenService = tokenService;
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //pegar o token do cabeçalho e verificar se esta ok
        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if(valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token){
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioJpaRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token==null || token.isEmpty() || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
