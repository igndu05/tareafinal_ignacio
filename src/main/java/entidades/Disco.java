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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "disco")

@NamedQueries({
    @NamedQuery(name = "Disco.findAll", query = "SELECT d FROM Disco d"),
    @NamedQuery(name = "Disco.findById", query = "SELECT d FROM Disco d WHERE d.codDisco = :codDisco"),
    @NamedQuery(name = "Disco.findByNombre", query = "SELECT d FROM Disco d WHERE d.nomDisco = :nomDisco")})
public class Disco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codDisco")
    private Integer codDisco;

    @Column(name = "nomDisco")
    private String nomDisco;

    @Column(name = "fechaLanzamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;

    @ManyToOne
    @JoinColumn(name = "codArtista")
    private Artista artista;

    @OneToMany(mappedBy = "disco", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<DetalleVenta> detalleVentaCollection;

    public Disco() {
    }

    public Disco(Integer codDisco) {
        this.codDisco = codDisco;
    }

    public Disco(String nomDisco, Date fechaLanzamiento, Artista artista) {
        this.nomDisco = nomDisco;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artista = artista;
        this.detalleVentaCollection = new ArrayList<>();
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

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public LocalDateTime getFechaLanzamientoLocalDateTime() {
        return fechaLanzamiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getFechaLanzamientoLocalDateTime().format(formatter);
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) {
        this.fechaLanzamiento = Date.from(fechaLanzamiento.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Collection<DetalleVenta> getDetalleVentaCollection() {
        return detalleVentaCollection;
    }

    public void setDetalleVentaCollection(Collection<DetalleVenta> detalleVentaCollection) {
        this.detalleVentaCollection = detalleVentaCollection;
        // A cada detalleVenta de la lista le indico que su venta es esta
        // Para que haya una sincronización bidireccional
        for (DetalleVenta detalleventa : detalleVentaCollection) {
            detalleventa.setDisco(this);
        }
    }

    public void addDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVentaCollection.add(detalleVenta);
        // Sincronización bidireccional con detalleVenta
        detalleVenta.setDisco(this);
    }

    public void removeDetalleVenta(DetalleVenta detalleVenta) {
        // Se borra el detalleVenta de esta venta
        this.detalleVentaCollection.remove(detalleVenta);
        // Se sincroniza la venta rompiendo la relación bidireccional
        detalleVenta.setDisco(null);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codDisco);
        hash = 59 * hash + Objects.hashCode(this.nomDisco);
        hash = 59 * hash + Objects.hashCode(this.fechaLanzamiento);
        hash = 59 * hash + Objects.hashCode(this.artista);
        hash = 59 * hash + Objects.hashCode(this.detalleVentaCollection);
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
        final Disco other = (Disco) obj;
        if (!Objects.equals(this.nomDisco, other.nomDisco)) {
            return false;
        }
        if (!Objects.equals(this.codDisco, other.codDisco)) {
            return false;
        }
        if (!Objects.equals(this.fechaLanzamiento, other.fechaLanzamiento)) {
            return false;
        }
        if (!Objects.equals(this.artista, other.artista)) {
            return false;
        }
        return Objects.equals(this.detalleVentaCollection, other.detalleVentaCollection);
    }

    @Override
    public String toString() {
        return "Disco{"
                + "codDisco=" + codDisco
                + ", nomDisco='" + nomDisco + '\''
                + ", fechaLanzamiento=" + fechaLanzamiento
                + ", artistaId=" + (artista != null ? artista.getCodArtista() : "null")
                + ", detalleVentaCount=" + (detalleVentaCollection != null ? detalleVentaCollection.size() : 0)
                + '}';
    }

}
