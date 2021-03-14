package br.com.autadesouza.alegriaapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Musica {
    private long id;
    private String titulo;
    private String tonalidade;
    private Collection<AutorMusica> autorMusicasById;
    private Collection<GeneroMusica> generoMusicasById;
    private Collection<Letra> letrasById;
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
    @Column(name = "titulo", nullable = true, length = -1)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "tonalidade", nullable = true, length = 7)
    public String getTonalidade() {
        return tonalidade;
    }

    public void setTonalidade(String tonalidade) {
        this.tonalidade = tonalidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return id == musica.id &&
                Objects.equals(titulo, musica.titulo) &&
                Objects.equals(tonalidade, musica.tonalidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, tonalidade);
    }

    @OneToMany(mappedBy = "musicaByMusicaId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<AutorMusica> getAutorMusicasById() {
        return autorMusicasById;
    }

    public void setAutorMusicasById(Collection<AutorMusica> autorMusicasById) {
        this.autorMusicasById = autorMusicasById;
    }

    @OneToMany(mappedBy = "musicaByMusicaId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<GeneroMusica> getGeneroMusicasById() {
        return generoMusicasById;
    }

    public void setGeneroMusicasById(Collection<GeneroMusica> generoMusicasById) {
        this.generoMusicasById = generoMusicasById;
    }

    @OneToMany(mappedBy = "musicaByMusicaId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Letra> getLetrasById() {
        return letrasById;
    }

    public void setLetrasById(Collection<Letra> letrasById) {
        this.letrasById = letrasById;
    }

    @OneToMany(mappedBy = "musicaByMusicaId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<MusicaConteudo> getMusicaConteudosById() {
        return musicaConteudosById;
    }

    public void setMusicaConteudosById(Collection<MusicaConteudo> musicaConteudosById) {
        this.musicaConteudosById = musicaConteudosById;
    }
}
