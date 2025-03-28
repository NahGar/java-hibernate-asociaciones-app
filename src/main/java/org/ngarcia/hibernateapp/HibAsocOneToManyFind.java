package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.entity.Direccion;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocOneToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);

            Direccion d1 = new Direccion("canelones",1349);
            Direccion d2 = new Direccion("ejido",1312);
            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            em.merge(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            em.getTransaction().begin();

            //no se elimina porque d1 no está atachado al contexto
            //cliente.getDirecciones().remove(d1);

            //hay que buscar la direccion
            d1 = em.find(Direccion.class, cliente.getDirecciones().get(0).getId());
            cliente.getDirecciones().remove(d1);

            em.getTransaction().commit();

            System.out.println(cliente);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
