package br.com.autadesouza.alegriaapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Genero {
    private long id;
    private String nome;
    private Collection<GeneroMusica> generoMusicasById;

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
        Genero genero = (Genero) o;
        return id == genero.id &&
                Objects.equals(nome, genero.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @OneToMany(mappedBy = "generoByGeneroId")
    public Collection<GeneroMusica> getGeneroMusicasById() {
        return generoMusicasById;
    }

    public void setGeneroMusicasById(Collection<GeneroMusica> generoMusicasById) {
        this.generoMusicasById = generoMusicasById;
    }
}
