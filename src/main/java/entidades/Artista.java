package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "artista")

@NamedQueries({
    @NamedQuery(name = "Artista.findAll", query = "SELECT a FROM Artista a"),
    @NamedQuery(name = "Artista.findById", query = "SELECT a FROM Artista a WHERE a.codArtista = :codArtista"),
    @NamedQuery(name = "Artista.findByNombre", query = "SELECT a FROM Artista a WHERE a.nomArtista = :nomArtista")})
public class Artista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codArtista")
    private Integer codArtista;
    @Column(name = "nomArtista")
    private String nomArtista;
    @Basic(optional = false)
    @Column(name = "fechaNacimientoArtista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimientoArtista;
    

    @OneToMany(mappedBy = "artista", cascade = CascadeType.PERSIST)
    private Collection<Disco> discoCollection;

    public Artista() {
    }

    public Artista(Integer codArtista) {
        this.codArtista = codArtista;
    }

    public Artista(Integer codArtista, String nomArtista, Date fechaNacimientoArtista) {
        this.codArtista = codArtista;
        this.nomArtista = nomArtista;
        this.fechaNacimientoArtista = fechaNacimientoArtista;
    }
    
    public Artista(Integer codArtista, String nomArtista, Date fechaNacimientoArtista, Collection<Disco> discoCollection) {
        this.codArtista = codArtista;
        this.nomArtista = nomArtista;
        this.fechaNacimientoArtista = fechaNacimientoArtista;
        this.discoCollection = new ArrayList<>();
    }

    public Integer getCodArtista() {
        return codArtista;
    }

    public void setCodArtista(Integer codArtista) {
        this.codArtista = codArtista;
    }

    public String getNomArtista() {
        return nomArtista;
    }

    public void setNomArtista(String nomArtista) {
        this.nomArtista = nomArtista;
    }

    public Date getFechaNacimientoArtista() {
        return fechaNacimientoArtista;
    }
    
    public LocalDateTime getFechaLocalDateTime() {
        return fechaNacimientoArtista.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setFechaNacimientoArtista(Date fechaNacimientoArtista) {
        this.fechaNacimientoArtista = fechaNacimientoArtista;
    }
    
    public void setFechaNacimientoArtista(LocalDateTime fechaNacimientoArtista) {
        // Convertir LocalDateTime a Date
        this.fechaNacimientoArtista = Date.from(fechaNacimientoArtista.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Collection<Disco> getDiscoCollection() {
        return discoCollection;
    }

    public void setDiscoCollection(Collection<Disco> discoCollection) {
        this.discoCollection = discoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codArtista);
        hash = 59 * hash + Objects.hashCode(this.nomArtista);
        hash = 59 * hash + Objects.hashCode(this.fechaNacimientoArtista);
        hash = 59 * hash + Objects.hashCode(this.discoCollection);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artista other = (Artista) obj;
        if (!Objects.equals(this.nomArtista, other.nomArtista)) {
            return false;
        }
        if (!Objects.equals(this.codArtista, other.codArtista)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimientoArtista, other.fechaNacimientoArtista)) {
            return false;
        }
        return Objects.equals(this.discoCollection, other.discoCollection);
    }
    
    public void setArtistaForDisco(Artista artista) {
        if (discoCollection != null) {
            for (Disco disco : discoCollection) {
                disco.setCodArtista(artista);
            }
        }
    }

    @Override
    public String toString() {
        String tmp = "";
        for (Disco disco : discoCollection) {
            tmp+=disco+"\n";
        }
        return "Artista{" + "codArtista=" + codArtista + ", nomArtista=" + nomArtista + ", fechaNacimientoArtista=" + fechaNacimientoArtista + ", discoCollection=" + discoCollection + '}';
    }

    
    
    
    
}
