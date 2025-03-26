package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.*;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Roberto","Musso");
            cliente.setFormaPago("mercado pago");

            Direccion d1 = new Direccion("canelones",1349);
            Direccion d2 = new Direccion("ejido",1312);
            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            em.persist(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);


            //borrar direccion
            em.getTransaction().begin();

            cliente = em.find(Cliente.class,cliente.getId());
            cliente.getDirecciones().remove(d1);
            //no necesita hacer merge porque el objeto cliente ya está persistido
            //de esa forma al hacer commit quedan reflejados los datos
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
