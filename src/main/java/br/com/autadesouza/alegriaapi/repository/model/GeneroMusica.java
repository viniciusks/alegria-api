package br.com.autadesouza.alegriaapi.repository.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genero_musica", schema = "public", catalog = "alegria-api")
public class GeneroMusica {
    private long id;
    private Long musicaId;
    private Long generoId;
    private Musica musicaByMusicaId;
    private Genero generoByGeneroId;

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
    @Column(name = "musica_id", nullable = true, insertable = false, updatable = false)
    public Long getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Long musicaId) {
        this.musicaId = musicaId;
    }

    @Basic
    @Column(name = "genero_id", nullable = true, insertable = false, updatable = false)
    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneroMusica that = (GeneroMusica) o;
        return id == that.id &&
                Objects.equals(musicaId, that.musicaId) &&
                Objects.equals(generoId, that.generoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musicaId, generoId);
    }

    @ManyToOne
    @JoinColumn(name = "musica_id", referencedColumnName = "id")
    public Musica getMusicaByMusicaId() {
        return musicaByMusicaId;
    }

    public void setMusicaByMusicaId(Musica musicaByMusicaId) {
        this.musicaByMusicaId = musicaByMusicaId;
    }

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    public Genero getGeneroByGeneroId() {
        return generoByGeneroId;
    }

    public void setGeneroByGeneroId(Genero generoByGeneroId) {
        this.generoByGeneroId = generoByGeneroId;
    }
}
