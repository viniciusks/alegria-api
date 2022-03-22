package br.com.autadesouza.alegriaapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTValidatorFilter extends BasicAuthenticationFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer ";

    private ConfigUserDetailsService usuarioService;

    public JWTValidatorFilter(AuthenticationManager authenticationManager, ConfigUserDetailsService usuarioService) {
        super(authenticationManager);
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if(authorizationHeader == null) {
            chain.doFilter(request, response);
            return;
        }

        if(!authorizationHeader.startsWith(TOKEN_TYPE)) {
            chain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(TOKEN_TYPE, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String usuario = JWT.require(Algorithm.HMAC512(JWTAuthenticatorFilter.TOKEN_PASSWORD))
                .build()
                .verify(token)
                .getSubject();

        if(usuario == null)
            return null;

        UserDetails detalheUsuario = usuarioService.loadUserByUsername(usuario);

        return new UsernamePasswordAuthenticationToken(usuario, null, detalheUsuario.getAuthorities());
    }
}
