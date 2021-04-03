package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.repository.model.Letra;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class LetraResponse {

    private long id;

    private Long musicaId;

    private Integer ordem;

    private String tipo;

    private String texto;

    public static LetraResponse fromDomain(Letra letra) {
        return LetraResponse.builder()
                .id(letra.getId())
                .musicaId(letra.getMusicaId())
                .ordem(letra.getOrdem())
                .tipo(letra.getTipo())
                .texto(letra.getTexto())
                .build();
    }

    public static List<LetraResponse> fromDomain(List<Letra> letras) {
        return letras.stream().map(letra -> fromDomain(letra))
                .collect(Collectors.toList());
    }
}
