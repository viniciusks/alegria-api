package br.com.autadesouza.alegriaapi.repository.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Conteudo {
    private long id;
    private Long tipoId;
    private String descricao;
    private String detalhe;
    private Collection<CategoriaConteudo> categoriaConteudosById;
    private Tipo tipoByTipoId;
    private Collection<LinkConteudo> linkConteudosById;
    private Collection<MusicaConteudo> musicaConteudosById;

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
    @Column(name = "tipo_id", nullable = true, insertable = false, updatable = false)
    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    @Basic
    @Column(name = "descricao", nullable = true, length = -1)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Basic
    @Column(name = "detalhe", nullable = true, length = -1)
    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conteudo conteudo = (Conteudo) o;
        return id == conteudo.id &&
                Objects.equals(tipoId, conteudo.tipoId) &&
                Objects.equals(descricao, conteudo.descricao) &&
                Objects.equals(detalhe, conteudo.detalhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoId, descricao, detalhe);
    }

    @OneToMany(mappedBy = "conteudoByConteudoId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<CategoriaConteudo> getCategoriaConteudosById() {
        return categoriaConteudosById;
    }

    public void setCategoriaConteudosById(Collection<CategoriaConteudo> categoriaConteudosById) {
        this.categoriaConteudosById = categoriaConteudosById;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_id", referencedColumnName = "id")
    public Tipo getTipoByTipoId() {
        return tipoByTipoId;
    }

    public void setTipoByTipoId(Tipo tipoByTipoId) {
        this.tipoByTipoId = tipoByTipoId;
    }

    @OneToMany(mappedBy = "conteudoByConteudoId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<LinkConteudo> getLinkConteudosById() {
        return linkConteudosById;
    }

    public void setLinkConteudosById(Collection<LinkConteudo> linkConteudosById) {
        this.linkConteudosById = linkConteudosById;
    }

    @OneToMany(mappedBy = "conteudoByConteudoId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<MusicaConteudo> getMusicaConteudosById() {
        return musicaConteudosById;
    }

    public void setMusicaConteudosById(Collection<MusicaConteudo> musicaConteudosById) {
        this.musicaConteudosById = musicaConteudosById;
    }
}
