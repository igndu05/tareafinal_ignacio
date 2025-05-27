package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "discos")

@NamedQueries({
    @NamedQuery(name = "Discos.findAll", query = "SELECT d FROM discos d"),
    @NamedQuery(name = "Discos.findById", query = "SELECT d FROM discos d WHERE a.codDisco = :codDisco"),
    @NamedQuery(name = "Discos.findByNombre", query = "SELECT d FROM discos d WHERE a.nomDisco = :nomDisco")})
public class Disco implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codDisco")
    private Integer codDisco;
    @Column(name = "nomDisco")
    private String nomDisco;
    @Column(name = "fechaLanzamiento")
    private LocalDate fechaLanzamiento;
    @Column(name = "cantidadDiscos")
    private int cantidadDiscos;

    @JoinColumn(name = "codArtista", referencedColumnName = "codArtista")
    @ManyToOne
    private Artista artista;

    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
    @ManyToOne
    private Usuario usuario;

    public Disco() {
    }

    public Disco(Integer codDisco) {
        this.codDisco = codDisco;
    }

    public Disco(Integer codDisco, String nomDisco, LocalDate fechaLanzamiento, int cantidadDiscos, Artista artista,
    Usuario usuario) {
        this.codDisco = codDisco;
        this.nomDisco = nomDisco;
        this.fechaLanzamiento = fechaLanzamiento;
        this.cantidadDiscos = cantidadDiscos;
        this.artista = artista;
        this.usuario = usuario;
    }

    public Integer getCodDisco() {
        return codDisco;
    }

    public void setCodDisco(Integer codDisco) {
        this.codDisco = codDisco;
    }

    public String getNomDisco() {
        return nomDisco;
    }

    public void setNomDisco(String nomDisco) {
        this.nomDisco = nomDisco;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getCantidadDiscos() {
        return cantidadDiscos;
    }

    public void setCantidadDiscos(int cantidadDiscos) {
        this.cantidadDiscos = cantidadDiscos;
    }

    public Integer getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codDisco == null) ? 0 : codDisco.hashCode());
        result = prime * result + ((nomDisco == null) ? 0 : nomDisco.hashCode());
        result = prime * result + ((fechaLanzamiento == null) ? 0 : fechaLanzamiento.hashCode());
        result = prime * result + cantidadDiscos;
        result = prime * result + ((artista == null) ? 0 : artista.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disco other = (Disco) obj;
        if (codDisco == null) {
            if (other.codDisco != null)
                return false;
        } else if (!codDisco.equals(other.codDisco))
            return false;
        if (nomDisco == null) {
            if (other.nomDisco != null)
                return false;
        } else if (!nomDisco.equals(other.nomDisco))
            return false;
        if (fechaLanzamiento == null) {
            if (other.fechaLanzamiento != null)
                return false;
        } else if (!fechaLanzamiento.equals(other.fechaLanzamiento))
            return false;
        if (cantidadDiscos != other.cantidadDiscos)
            return false;
        if (codArtista == null) {
            if (other.codArtista != null)
                return false;
        } else if (!codArtista.equals(other.codArtista))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Disco{" + "codDisco=" + codDisco + ", nomDisco=" + nomDisco + ", fechaLanzamiento=" + fechaLanzamiento + ", cantidadDiscos=" + cantidadDiscos + ", codArtista=" + codArtista + ", Usuario=" + usuario + '}';
    }
    
    
    
}
