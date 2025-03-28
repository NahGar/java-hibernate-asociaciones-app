package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.entity.ClienteDetalle;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocOneToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            //la relación está en Cliente
            Cliente cliente = em.find(Cliente.class, 2L);

            ClienteDetalle clienteDetalle = new ClienteDetalle(true,800L);
            em.persist(clienteDetalle);

            cliente.setDetalle(clienteDetalle);

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
