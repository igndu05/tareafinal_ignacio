/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ignacio
 */
@Entity
@Table(name = "venta")
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findById", query = "SELECT v FROM Venta v WHERE v.codVenta = :codVenta"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fechaVenta = :fechaVenta")})
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codVenta")
    private Integer codVenta;
    
    @Column(name = "fechaVenta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;

    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codVenta")
    private Collection<DetalleVenta> detalleVentaCollection;

    public Venta() {
    }

    public Venta(Integer codVenta) {
        this.codVenta = codVenta;
    }

    public Venta(Date fechaVenta, Usuario usuario) {
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
        this.detalleVentaCollection = new ArrayList<>();
    }

    public Integer getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(Integer codVenta) {
        this.codVenta = codVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public LocalDateTime getFechaLocalDateTime() {
        return fechaVenta.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        // Convertir LocalDateTime a Date
        this.fechaVenta = Date.from(fechaVenta.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Usuario getCodUsuario() {
        return usuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.usuario = codUsuario;
    }

    public Collection<DetalleVenta> getDetalleVentaCollection() {
        return detalleVentaCollection;
    }

    public void setDetalleVentaCollection(Collection<DetalleVenta> detalleVentaCollection) {
        this.detalleVentaCollection = detalleVentaCollection;
        // A cada detalleVenta de la lista le indico que su venta es esta
        // Para que haya una sincronización bidireccional
        for (DetalleVenta d : detalleVentaCollection) {
            d.setVenta(this);
        }
    }
    
    public void addDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVentaCollection.add(detalleVenta);
        // Sincronización bidireccional con detalleVenta
        detalleVenta.setVenta(this);
    }

    public void removeDetalleVenta(DetalleVenta detalleVenta) {
        // Se borra el detalleVenta de esta venta
        this.detalleVentaCollection.remove(detalleVenta);
        // Se sincroniza la venta rompiendo la relación bidireccional
        detalleVenta.setVenta(null);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.codVenta);
        hash = 31 * hash + Objects.hashCode(this.fechaVenta);
        hash = 31 * hash + Objects.hashCode(this.usuario);
        hash = 31 * hash + Objects.hashCode(this.detalleVentaCollection);
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
        final Venta other = (Venta) obj;
        if (!Objects.equals(this.codVenta, other.codVenta)) {
            return false;
        }
        if (!Objects.equals(this.fechaVenta, other.fechaVenta)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return Objects.equals(this.detalleVentaCollection, other.detalleVentaCollection);
    }

     @Override
    public String toString() {
        String tmp = "";
        for (DetalleVenta detalle : detalleVentaCollection) {
            tmp += detalle + "\n";
        }
        return "Venta{" + "id=" + codVenta + ", fecha=" + fechaVenta + ", idUsuario=" + usuario.getCodUsuario()
                + ", detalleventaCollection=\n" + tmp + '}';
    }
    
    
    
}
