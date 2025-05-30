package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Usuario;

public class UsuarioController {
    private final EntityManagerFactory emf;

    public UsuarioController() {
        this.emf = Persistence.createEntityManagerFactory("discos");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear usuario", e);
        } finally {
            em.close();
        }
    }

    public Usuario findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public List<Usuario> findAll() {
        EntityManager em = getEntityManager();
        try {
            // Usa la named query definida en la entidad Cliente
            return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Usuario usuario) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(usuario);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el usuario", ex);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario a = em.find(Usuario.class, id);
            if (a != null) {
                em.remove(a);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el usuario", ex);
        } finally {
            em.close();
        }
    }

    public void deleteAll() {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Ejemplo de uso de una consulta nativa para eliminar todos los registros
            // de la tabla cliente y reiniciar el contador de autoincremento
            // Una native query es una consulta SQL que se ejecuta directamente en la base de datos
            // sin pasar por el mapeo de entidades de JPA
            em.createNativeQuery("delete from usuario").executeUpdate();
            em.createNativeQuery("alter table compra_discos.usuario AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los usuarios", ex);
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
