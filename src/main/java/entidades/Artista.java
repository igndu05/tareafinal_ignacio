package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @Column(name = "fechaNacimientoArtista")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoArtista;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
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
    
    public String getFechaFormateada() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return getFechaLocalDateTime().format(formatter);
    }

    public Collection<Disco> getDiscoCollection() {
        return discoCollection;
    }

    public void setDiscoCollection(Collection<Disco> discoCollection) {
        this.discoCollection = discoCollection;
        for (Disco d : discoCollection) {
            d.setArtista(this);
        }
    }
    
    public void addDisco(Disco disco){
        this.discoCollection.add(disco);
        disco.setArtista(this);
    }
    
    public void removeDisco(Disco disco) {
        this.discoCollection.remove(disco);
        disco.setArtista(null);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codArtista);
        hash = 83 * hash + Objects.hashCode(this.nomArtista);
        hash = 83 * hash + Objects.hashCode(this.fechaNacimientoArtista);
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
        return Objects.equals(this.fechaNacimientoArtista, other.fechaNacimientoArtista);
    }

    @Override
    public String toString() {
        return "Artista{"
                + "codArtista=" + codArtista
                + ", nomArtista='" + nomArtista + '\''
                + ", fechaNacimientoArtista=" + fechaNacimientoArtista
                + ", discosCount=" + (discoCollection != null ? discoCollection.size() : 0)
                + '}';
    }
}
