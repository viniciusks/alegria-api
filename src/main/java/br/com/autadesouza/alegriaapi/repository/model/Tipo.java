package br.com.autadesouza.alegriaapi.repository.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tipo {
    private long id;
    private String nome;
    private Collection<Conteudo> conteudosById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nome", nullable = true, length = -1)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipo tipo = (Tipo) o;
        return id == tipo.id &&
                Objects.equals(nome, tipo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @OneToMany(mappedBy = "tipoByTipoId")
    public Collection<Conteudo> getConteudosById() {
        return conteudosById;
    }

    public void setConteudosById(Collection<Conteudo> conteudosById) {
        this.conteudosById = conteudosById;
    }
}
