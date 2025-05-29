/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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

    // En este caso Venta es la propietaria de la relación con Cliente @JoinColumn
    // Esta tabla tiene la clave foránea a Cliente
    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
}
