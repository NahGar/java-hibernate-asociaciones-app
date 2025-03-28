package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.entity.ClienteDetalle;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocOneToOneBidirec {

   public static void main(String[] args) {

      //Cliente es la clase principal y ClienteDetalle es dueña de la relación

      EntityManager em = JpaUtil.getEntityManager();

      try {
         em.getTransaction().begin();

         Cliente cliente = new Cliente("Juan","Ramirez");
         cliente.setFormaPago("oro");

         ClienteDetalle detalle = new ClienteDetalle(true, 10L);
         //cliente.setDetalle(detalle);
         //detalle.setCliente(cliente);
         cliente.addDetalle(detalle);

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
