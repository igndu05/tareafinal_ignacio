package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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

@Entity
@Table(name = "usuarios")

@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
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
    @Basic(optional = false)

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private Collection<Disco> discoCollection; 

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
        this.discoCollection = new ArrayList<>();
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
    public Collection<Disco> getDiscoCollection() {
        return discoCollection;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codUsuario == null) ? 0 : codUsuario.hashCode());
        result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
        result = prime * result + ((dniUsuario == null) ? 0 : dniUsuario.hashCode());
        result = prime * result + ((localidadUsuario == null) ? 0 : localidadUsuario.hashCode());
        result = prime * result + ((telefUsuario == null) ? 0 : telefUsuario.hashCode());
        result = prime * result + ((discoCollection == null) ? 0 : discoCollection.hashCode());
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
        Usuario other = (Usuario) obj;
        if (codUsuario == null) {
            if (other.codUsuario != null)
                return false;
        } else if (!codUsuario.equals(other.codUsuario))
            return false;
        if (nombreUsuario == null) {
            if (other.nombreUsuario != null)
                return false;
        } else if (!nombreUsuario.equals(other.nombreUsuario))
            return false;
        if (dniUsuario == null) {
            if (other.dniUsuario != null)
                return false;
        } else if (!dniUsuario.equals(other.dniUsuario))
            return false;
        if (localidadUsuario == null) {
            if (other.localidadUsuario != null)
                return false;
        } else if (!localidadUsuario.equals(other.localidadUsuario))
            return false;
        if (telefUsuario == null) {
            if (other.telefUsuario != null)
                return false;
        } else if (!telefUsuario.equals(other.telefUsuario))
            return false;
        if (discoCollection == null) {
            if (other.discoCollection != null)
                return false;
        } else if (!discoCollection.equals(other.discoCollection))
            return false;
        return true;
    }

    
    public void setUsuarioForDiscos(Usuario usuario) {
        if (discoCollection != null) {
            for (Disco disco : discoCollection) {
                disco.setUsuario(usuario);
            }
        }
    }

    
    @Override
    public String toString() {
        String tmp = "";
        for (Disco disco : discoCollection) {
            tmp+=disco+"\n";
        }
        return "Usuario{" + "codUsuario=" + codUsuario + ", nombreUsuario=" + nombreUsuario + ", dniUsuario=" + dniUsuario + ", localidadUsuario=" + localidadUsuario + ", telefUsuario=" + telefUsuario + ", discoCollection=" + discoCollection + '}';
    }

    
}
