package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.model.Letra;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class LetraRequest {

    private Long id;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private Integer ordem;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String tipo;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String texto;

    public Letra toDomain() {
        return Letra.builder()
                .ordem(this.ordem)
                .tipo(this.tipo)
                .texto(this.texto)
                .build();
    }
}
