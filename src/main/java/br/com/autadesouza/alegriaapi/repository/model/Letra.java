package br.com.autadesouza.alegriaapi.repository.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Letra {
    private long id;
    private Long musicaId;
    private Long instrumentoId;
    private Integer ordem;
    private String tipo;
    private String texto;
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
    @Column(name = "musica_id", nullable = true, insertable = false, updatable = false)
    public Long getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(Long musicaId) {
        this.musicaId = musicaId;
    }

    @Basic
    @Column(name = "instrumento_id", nullable = true, insertable = false, updatable = false)
    public Long getInstrumentoId() {
        return instrumentoId;
    }

    public void setInstrumentoId(Long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }

    @Basic
    @Column(name = "ordem", nullable = true)
    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @Basic
    @Column(name = "tipo", nullable = true, length = -1)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "texto", nullable = true, length = -1)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letra letra = (Letra) o;
        return id == letra.id &&
                Objects.equals(musicaId, letra.musicaId) &&
                Objects.equals(instrumentoId, letra.instrumentoId) &&
                Objects.equals(ordem, letra.ordem) &&
                Objects.equals(tipo, letra.tipo) &&
                Objects.equals(texto, letra.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musicaId, instrumentoId, ordem, tipo, texto);
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
