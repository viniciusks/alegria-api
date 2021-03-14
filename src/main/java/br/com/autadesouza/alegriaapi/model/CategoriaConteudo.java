package br.com.autadesouza.alegriaapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoria_conteudo", schema = "public", catalog = "alegria-api")
public class CategoriaConteudo {
    private long id;
    private Long conteudoId;
    private Long categoriaId;
    private Conteudo conteudoByConteudoId;
    private Categoria categoriaByCategoriaId;

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
    @Column(name = "conteudo_id", nullable = true)
    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }

    @Basic
    @Column(name = "categoria_id", nullable = true)
    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaConteudo that = (CategoriaConteudo) o;
        return id == that.id &&
                Objects.equals(conteudoId, that.conteudoId) &&
                Objects.equals(categoriaId, that.categoriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conteudoId, categoriaId);
    }

    @ManyToOne
    @JoinColumn(name = "conteudo_id", referencedColumnName = "id")
    public Conteudo getConteudoByConteudoId() {
        return conteudoByConteudoId;
    }

    public void setConteudoByConteudoId(Conteudo conteudoByConteudoId) {
        this.conteudoByConteudoId = conteudoByConteudoId;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    public Categoria getCategoriaByCategoriaId() {
        return categoriaByCategoriaId;
    }

    public void setCategoriaByCategoriaId(Categoria categoriaByCategoriaId) {
        this.categoriaByCategoriaId = categoriaByCategoriaId;
    }
}
