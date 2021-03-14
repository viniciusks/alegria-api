package br.com.autadesouza.alegriaapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Categoria {
    private long id;
    private String nome;
    private Collection<CategoriaConteudo> categoriaConteudosById;

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
        Categoria categoria = (Categoria) o;
        return id == categoria.id &&
                Objects.equals(nome, categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @OneToMany(mappedBy = "categoriaByCategoriaId")
    public Collection<CategoriaConteudo> getCategoriaConteudosById() {
        return categoriaConteudosById;
    }

    public void setCategoriaConteudosById(Collection<CategoriaConteudo> categoriaConteudosById) {
        this.categoriaConteudosById = categoriaConteudosById;
    }
}
