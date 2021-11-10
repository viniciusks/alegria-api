package br.com.autadesouza.alegriaapi.service.impl;

import br.com.autadesouza.alegriaapi.config.ConfigUserDetailsService;
import br.com.autadesouza.alegriaapi.controller.request.LoginRequest;
import br.com.autadesouza.alegriaapi.controller.response.LoginResponse;
import br.com.autadesouza.alegriaapi.repository.UserRepository;
import br.com.autadesouza.alegriaapi.repository.model.Usuario;
import br.com.autadesouza.alegriaapi.service.AuthService;
import br.com.autadesouza.alegriaapi.service.client.IbgeClient;
import br.com.autadesouza.alegriaapi.service.client.response.IbgeResponse;
import br.com.autadesouza.alegriaapi.validation.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Log4j2
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Integer ACCESS_TOKEN_VALIDITY_IN_SECONDS = 900;
    private static final Integer REFRESH_TOKEN_VALIDITY_IN_SECONDS = 900;

    private UserRepository userRepository;

    private ConfigUserDetailsService configUserDetailsService;

    private AuthenticationManager authenticationManager;

    private IbgeClient ibgeClient;

    @Override
    public Usuario register(Usuario usuario) {
        Usuario verifyUsuario = userRepository.findByEmail(usuario.getEmail());
        if(verifyUsuario != null) {
            throw new UserAlreadyExistsException("User already exists, please try another email.");
        }

        String encodePass = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(encodePass);
        return userRepository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        UserDetails userDetails = configUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, loginRequest.getPassword(), userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        OAuth2Request oAuth2Request = createOAuth2Request();
        OAuth2AccessToken token = createOAuth2Token(usernamePasswordAuthenticationToken, oAuth2Request);

        return LoginResponse.builder()
                .username(userDetails.getUsername())
                .accessToken(token.getValue())
                .expiresIn(token.getExpiresIn())
                .refreshToken(token.getRefreshToken().getValue())
                .tokenType(token.getTokenType())
                .build();
    }

    private OAuth2AccessToken createOAuth2Token(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, OAuth2Request oAuth2Request) {
        InMemoryTokenStore inMemoryTokenStore = new InMemoryTokenStore();
        DefaultTokenServices service = new DefaultTokenServices();
        service.setSupportRefreshToken(true);
        service.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_IN_SECONDS);
        service.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_IN_SECONDS);

        service.setTokenStore(inMemoryTokenStore);

        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, usernamePasswordAuthenticationToken);
        OAuth2AccessToken token = service.createAccessToken(auth);
        return token;
    }

    private OAuth2Request createOAuth2Request() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Map<String, String> requestParameters = new HashMap<>();
        String clientId = "client-id";
        Set<String> scope = new HashSet<>();
        scope.add("read");
        scope.add("write");
        scope.add("trust");
        Set<String> resourceIds = new HashSet<>();
        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");
        Map<String, Serializable> extensionProperties = new HashMap<>();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId,
                authorities, true, scope,
                resourceIds, null, responseTypes, extensionProperties);
        return oAuth2Request;
    }

    @Override
    public List<String> getMunicipiosByUf(String uf) throws IOException {
        var response = ibgeClient.getMunicipiosByUf(uf);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        var ibgeResponseArray = mapper.readValue(response, IbgeResponse[].class);
        List<String> municipios = new ArrayList<>();

        for(IbgeResponse distrito : ibgeResponseArray) {
            municipios.add(distrito.getNome());
        }

        Collections.sort(municipios);

        return municipios;
    }

//    @Override
//    public Autor getAutorById(String id) {
//        Optional<Autor> optAutor = autoresRepository.findById(id);
//        return optAutor.orElseThrow(() -> {
//            log.info("m=getAutores msg=Autor not found, autorId={}", id);
//            throw new AutorNotFoundException("Autor not found.");
//        });
//    }
}
