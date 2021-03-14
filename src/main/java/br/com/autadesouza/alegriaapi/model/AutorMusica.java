package br.com.autadesouza.alegriaapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "autor_musica", schema = "public", catalog = "alegria-api")
public class AutorMusica {
    private long id;
    private Long musicaId;
    private Long autorId;
    private Musica musicaByMusicaId;
    private Autor autorByAutorId;

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
    @Column(name = "musica_id", nullable = true)
    public Long getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Long musicaId) {
        this.musicaId = musicaId;
    }

    @Basic
    @Column(name = "autor_id", nullable = true)
    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorMusica that = (AutorMusica) o;
        return id == that.id &&
                Objects.equals(musicaId, that.musicaId) &&
                Objects.equals(autorId, that.autorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musicaId, autorId);
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
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    public Autor getAutorByAutorId() {
        return autorByAutorId;
    }

    public void setAutorByAutorId(Autor autorByAutorId) {
        this.autorByAutorId = autorByAutorId;
    }
}
