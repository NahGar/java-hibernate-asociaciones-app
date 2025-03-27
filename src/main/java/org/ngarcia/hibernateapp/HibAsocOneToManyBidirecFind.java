package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.entity.Factura;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocOneToManyBidirecFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class,3L);

            Factura f1 = new Factura("compras super",5000L);
            Factura f2 = new Factura("cadetería",500L);
            Factura f3 = new Factura("alimentos",100L);

            cliente.addFactura(f1).addFactura(f2).addFactura(f3);
;
            //no es necesario porque cliente al ser obtenido desde BD queda attached
            //em.merge(cliente);

            em.getTransaction().commit();

            //eliminar factura
            //nueva trn
            em.getTransaction().begin();

            Factura f4 = em.find(Factura.class, cliente.getFacturas().get(0).getId());
            cliente.getFacturas().remove(f4);
            //no le veo sentido
            //f3.setCliente(null);
            em.getTransaction().commit();

            //Otra forma de eliminar que requiere implementar equals en la clase
            //hay que crear una clase igual (según equals)
            em.getTransaction().begin();
            Factura f5 = new Factura("alimentos",100L);
            f5.setId(3L);
            cliente.getFacturas().remove(f5);

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
