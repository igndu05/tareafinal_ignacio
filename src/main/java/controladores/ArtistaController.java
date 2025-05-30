package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Artista;

public class ArtistaController {
    private final EntityManagerFactory emf;

    public ArtistaController() {
        this.emf = Persistence.createEntityManagerFactory("discos");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artista artista) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(artista);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear artista", e);
        } finally {
            em.close();
        }
    }

    public Artista findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artista.class, id);
        } finally {
            em.close();
        }
    }

    public List<Artista> findAll() {
        EntityManager em = getEntityManager();
        try {
            // Usa la named query definida en la entidad Cliente
            return em.createNamedQuery("Artista.findAll", Artista.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Artista artista) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(artista);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el cliente", ex);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Artista a = em.find(Artista.class, id);
            if (a != null) {
                em.remove(a);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el cliente", ex);
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
            em.createNativeQuery("delete from artista").executeUpdate();
            em.createNativeQuery("alter table compra_discos.artista AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los artistas", ex);
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
