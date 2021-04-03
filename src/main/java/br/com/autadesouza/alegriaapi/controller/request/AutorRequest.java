package br.com.autadesouza.alegriaapi.controller.request;

import br.com.autadesouza.alegriaapi.repository.model.Autor;
import br.com.autadesouza.alegriaapi.validation.annotation.Mandatory;
import br.com.autadesouza.alegriaapi.validation.annotation.Values;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class AutorRequest {

    private Long id;

    @NotNull(message = "{mandatory}", groups = Mandatory.class)
    private String nome;

    private String email;

    private String telefone;

    @Size(message = "{invalid_size_max}", max = 3, groups = Values.class)
    private String pais;

    private String estado;

    private String cidade;

    public Autor toDomain() {
        return Autor.builder()
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .pais(this.pais)
                .estado(this.estado)
                .cidade(this.cidade)
                .build();
    }

    public Autor toDomainWithId() {
        return Autor.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .pais(this.pais)
                .estado(this.estado)
                .cidade(this.cidade)
                .build();
    }
}
