package br.com.autadesouza.alegriaapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "musica_conteudo", schema = "public", catalog = "alegria-api")
public class MusicaConteudo {
    private long id;
    private Long conteudoId;
    private Long musicaId;
    private Conteudo conteudoByConteudoId;
    private Musica musicaByMusicaId;

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
    @Column(name = "musica_id", nullable = true)
    public Long getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Long musicaId) {
        this.musicaId = musicaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicaConteudo that = (MusicaConteudo) o;
        return id == that.id &&
                Objects.equals(conteudoId, that.conteudoId) &&
                Objects.equals(musicaId, that.musicaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conteudoId, musicaId);
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
    @JoinColumn(name = "musica_id", referencedColumnName = "id")
    public Musica getMusicaByMusicaId() {
        return musicaByMusicaId;
    }

    public void setMusicaByMusicaId(Musica musicaByMusicaId) {
        this.musicaByMusicaId = musicaByMusicaId;
    }
}
