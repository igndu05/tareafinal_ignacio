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
    private Integer cantidad;

    @JoinColumn(name = "codVenta", referencedColumnName = "codVenta")
    @ManyToOne(optional = false)
    private Venta venta;

    @JoinColumn(name = "codDisco", referencedColumnName = "codDisco")
    @ManyToOne(optional = false)
    private Disco disco;

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
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codDetalleVenta);
        hash = 37 * hash + this.cantidad;
        hash = 37 * hash + Objects.hashCode(this.venta);
        hash = 37 * hash + Objects.hashCode(this.disco);
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
        if (!Objects.equals(this.venta, other.venta)) {
            return false;
        }
        return Objects.equals(this.disco, other.disco);
    }

    @Override
    public String toString() {
        return "DetalleVenta{"
                + "codDetalleVenta=" + codDetalleVenta
                + ", cantidad=" + cantidad
                + ", ventaId=" + (venta != null ? venta.getCodVenta() : "null")
                + ", discoId=" + (disco != null ? disco.getCodDisco() : "null")
                + '}';
    }

}
