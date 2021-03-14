package br.com.autadesouza.alegriaapi.repository.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link_conteudo", schema = "public", catalog = "alegria-api")
public class LinkConteudo {
    private long id;
    private Long conteudoId;
    private String link;
    private Conteudo conteudoByConteudoId;

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
    @Column(name = "conteudo_id", nullable = true, insertable = false, updatable = false)
    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }

    @Basic
    @Column(name = "link", nullable = true, length = -1)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkConteudo that = (LinkConteudo) o;
        return id == that.id &&
                Objects.equals(conteudoId, that.conteudoId) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conteudoId, link);
    }

    @ManyToOne
    @JoinColumn(name = "conteudo_id", referencedColumnName = "id")
    public Conteudo getConteudoByConteudoId() {
        return conteudoByConteudoId;
    }

    public void setConteudoByConteudoId(Conteudo conteudoByConteudoId) {
        this.conteudoByConteudoId = conteudoByConteudoId;
    }
}
