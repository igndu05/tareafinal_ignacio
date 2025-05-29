package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;
    @Column(name = "stock")
    private int stock;

    @JoinColumn(name = "codArtista", referencedColumnName = "codArtista")
    @ManyToOne
    private Artista artista;
    
    @OneToMany(mappedBy = "codDisco", cascade = CascadeType.PERSIST)
    private Collection<DetalleVenta> detalleVentaCollection;

    public Disco() {
    }

    public Disco(Integer codDisco) {
        this.codDisco = codDisco;
    }

    public Disco(Integer codDisco, String nomDisco, Date fechaLanzamiento, int stock) {
        this.codDisco = codDisco;
        this.nomDisco = nomDisco;
        this.fechaLanzamiento = fechaLanzamiento;
        this.stock = stock;
    }
    
    public Disco(Integer codDisco, String nomDisco, Date fechaLanzamiento, int stock, Artista artista) {
        this.codDisco = codDisco;
        this.nomDisco = nomDisco;
        this.fechaLanzamiento = fechaLanzamiento;
        this.stock = stock;
        this.artista = artista;
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

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
    
    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) {
        // Convertir LocalDateTime a Date
        this.fechaLanzamiento = Date.from(fechaLanzamiento.atZone(ZoneId.systemDefault()).toInstant());
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Artista getCodArtista() {
        return artista;
    }

    public void setCodArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.codDisco);
        hash = 47 * hash + Objects.hashCode(this.nomDisco);
        hash = 47 * hash + Objects.hashCode(this.fechaLanzamiento);
        hash = 47 * hash + this.stock;
        hash = 47 * hash + Objects.hashCode(this.artista);
        hash = 47 * hash + Objects.hashCode(this.detalleVentaCollection);
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
        if (this.stock != other.stock) {
            return false;
        }
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
        return "Disco{" + "codDisco=" + codDisco + ", nomDisco=" + nomDisco + ", fechaLanzamiento=" + fechaLanzamiento + ", stock=" + stock + ", artista=" + artista.getNomArtista() + ", detalleVentaCollection=" + detalleVentaCollection + '}';
    }

    

    
    
    
}
