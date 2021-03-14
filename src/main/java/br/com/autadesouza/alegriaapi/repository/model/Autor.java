package br.com.autadesouza.alegriaapi.repository.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Autor {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String pais;
    private String estado;
    private String cidade;
    private Collection<AutorMusica> autorMusicasById;

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

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telefone", nullable = true, length = -1)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Basic
    @Column(name = "pais", nullable = true, length = 3)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Basic
    @Column(name = "estado", nullable = true, length = -1)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "cidade", nullable = true, length = -1)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id &&
                Objects.equals(nome, autor.nome) &&
                Objects.equals(email, autor.email) &&
                Objects.equals(telefone, autor.telefone) &&
                Objects.equals(pais, autor.pais) &&
                Objects.equals(estado, autor.estado) &&
                Objects.equals(cidade, autor.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, pais, estado, cidade);
    }

    @OneToMany(mappedBy = "autorByAutorId")
    public Collection<AutorMusica> getAutorMusicasById() {
        return autorMusicasById;
    }

    public void setAutorMusicasById(Collection<AutorMusica> autorMusicasById) {
        this.autorMusicasById = autorMusicasById;
    }
}
