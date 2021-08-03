package br.com.autadesouza.alegriaapi.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document
public class Role implements GrantedAuthority {

    @Id
    private String id;

    private String nomeRole;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
