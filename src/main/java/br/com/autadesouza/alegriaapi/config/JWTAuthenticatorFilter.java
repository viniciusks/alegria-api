package br.com.autadesouza.alegriaapi.config;

import br.com.autadesouza.alegriaapi.repository.model.DetalheUsuario;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticatorFilter extends UsernamePasswordAuthenticationFilter {

    private static final int TOKEN_EXPIRATION_TIME = 600_000;

    //TODO Colocar isso no enviroment properties pra cada env
    public static final String TOKEN_PASSWORD = "04976686-0781-4a76-a884-993ada5bf22b";
    
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticatorFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        Usuario usuario;

        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
        } catch (AuthenticationException | IOException ex) {
            throw new RuntimeException("Falha ao processar request JSON.");
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            usuario.getEmail(),
            usuario.getPassword(),
            usuario.getRoles()
        ));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        DetalheUsuario detalheUsuario = (DetalheUsuario) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(detalheUsuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
