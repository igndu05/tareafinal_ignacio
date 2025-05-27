package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entidades.Disco;

public class DiscoController {
    private final EntityManagerFactory emf;

    public DiscoController(){
        this.emf = Persistence.createEntityManagerFactory("discos");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Disco disco) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(disco);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al crear el disco", ex);
        } finally {
            em.close();
        }
    }

    public Disco findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disco.class, id);
        } finally {
            em.close();
        }
    }

    public List<Disco> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Discos.findAll", Disco.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Disco disco) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(disco);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al actualizar el disco", ex);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Disco a = em.find(Disco.class, id);
            if (a != null) {
                em.remove(a);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar el disco", ex);
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
            em.createNativeQuery("delete from discos").executeUpdate();
            em.createNativeQuery("alter table compra_discos.discos AUTO_INCREMENT = 1").executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al eliminar todos los discos", ex);
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
