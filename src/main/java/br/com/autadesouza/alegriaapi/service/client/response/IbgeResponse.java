package br.com.autadesouza.alegriaapi.service.client.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IbgeResponse {

    @JsonProperty("cidade")
    private String nome;
}
