package br.com.autadesouza.alegriaapi.controller.response;

import br.com.autadesouza.alegriaapi.repository.model.Estados;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class EstadosResponse {

    private ArrayList<String> estados;

}
