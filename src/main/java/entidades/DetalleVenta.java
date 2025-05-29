/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Objects;
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

/**
 *
 * @author ignacio
 */
@Entity
@Table(name = "detalleventa")

@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d"),
    @NamedQuery(name = "DetalleVenta.findById", query = "SELECT d FROM DetalleVenta d WHERE d.codDetalleVenta = :codDetalleVenta"),
    @NamedQuery(name = "DetalleVenta.findByCantidad", query = "SELECT d FROM DetalleVenta d WHERE d.cantidad = :cantidad")})
public class DetalleVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codDetalleVenta")
    private Integer codDetalleVenta;
    @Column(name = "cantidad")
    private int cantidad;
    
    @JoinColumn(name = "codVenta", referencedColumnName = "codVenta")
    @ManyToOne
    private Venta codVenta;

    @JoinColumn(name = "codDisco", referencedColumnName = "codDisco")
    @ManyToOne
    private Disco codDisco;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer codDetalleVenta) {
        this.codDetalleVenta = codDetalleVenta;
    }

    public Integer getCodDetalleVenta() {
        return codDetalleVenta;
    }

    public void setCodDetalleVenta(Integer codDetalleVenta) {
        this.codDetalleVenta = codDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Venta getVenta() {
        return codVenta;
    }

    public void setVenta(Venta venta) {
        this.codVenta = venta;
    }

    public Disco getCodDisco() {
        return codDisco;
    }

    public void setCodDisco(Disco disco) {
        this.codDisco = disco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codDetalleVenta);
        hash = 37 * hash + this.cantidad;
        hash = 37 * hash + Objects.hashCode(this.codVenta);
        hash = 37 * hash + Objects.hashCode(this.codDisco);
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
        final DetalleVenta other = (DetalleVenta) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.codDetalleVenta, other.codDetalleVenta)) {
            return false;
        }
        if (!Objects.equals(this.codVenta, other.codVenta)) {
            return false;
        }
        return Objects.equals(this.codDisco, other.codDisco);
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "codDetalleVenta=" + codDetalleVenta + ", cantidad=" + cantidad + ", codVenta=" + codVenta + ", codDisco=" + codDisco.getCodDisco() + '}';
    }

}
