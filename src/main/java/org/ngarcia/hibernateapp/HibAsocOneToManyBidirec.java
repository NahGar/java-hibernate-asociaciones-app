package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.*;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocOneToManyBidirec {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Leo","Lagos");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("compras super",5000L);
            Factura f2 = new Factura("cadetería",500L);

            //se establece la relación en ambos lados
            //cliente.getFacturas().add(f1);
            //cliente.getFacturas().add(f2);
            //f1.setCliente(cliente);
            //f2.setCliente(cliente);
            cliente.addFactura(f1).addFactura(f2);
;
            em.persist(cliente);

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
