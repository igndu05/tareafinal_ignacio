package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")

@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u LEFT JOIN FETCH u.ventaCollection"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.codUsuario = :codUsuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codUsuario")
    private Integer codUsuario;

    @Column(name = "nomUsuario")
    private String nombreUsuario;

    @Basic(optional = false)
    @Column(name = "dniUsuario")
    private String dniUsuario;

    @Basic(optional = false)
    @Column(name = "localidadUsuario")
    private String localidadUsuario;

    @Basic(optional = false)
    @Column(name = "telefUsuario")
    private String telefUsuario;

    @OneToMany(mappedBy = "usuario")
    private Collection<Venta> ventaCollection;

    // Constructores
    public Usuario() {
    }

    public Usuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario(Integer codUsuario, String dniUsuario, String localidadUsuario, String telefUsuario) {
        this.codUsuario = codUsuario;
        this.dniUsuario = dniUsuario;
        this.localidadUsuario = localidadUsuario;
        this.telefUsuario = telefUsuario;
        this.ventaCollection = new ArrayList<>();
    }

    // Getters y Setters
    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getLocalidadUsuario() {
        return localidadUsuario;
    }

    public void setLocalidadUsuario(String localidadUsuario) {
        this.localidadUsuario = localidadUsuario;
    }

    public String getTelefUsuario() {
        return telefUsuario;
    }

    public void setTelefUsuario(String telefUsuario) {
        this.telefUsuario = telefUsuario;
    }

    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codUsuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return Objects.equals(this.codUsuario, other.codUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "codUsuario=" + codUsuario
                + ", nombreUsuario='" + nombreUsuario + '\''
                + ", dniUsuario='" + dniUsuario + '\''
                + ", localidadUsuario='" + localidadUsuario + '\''
                + ", telefUsuario='" + telefUsuario + '\''
                + '}';
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
        for (Venta venta : ventaCollection) {
            venta.setUsuario(this);
        }
    }

    public void addVenta(Venta venta) {
        this.ventaCollection.add(venta);
        venta.setUsuario(this);
    }

    public void removeVenta(Venta venta) {
        this.ventaCollection.remove(venta);
        venta.setUsuario(null);
    }
}
