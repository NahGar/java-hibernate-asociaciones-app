package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibFetchResultList {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        //String sql = "select c from Cliente c";
        String sql = "select distinct c from Cliente c " +
                "left outer join fetch c.direcciones " +
                //no permite hacer multiples consultas Many
                //"left outer join fetch c.facturas " +
                "left outer join fetch c.detalle";
        List<Cliente> clientes = em.createQuery(sql,Cliente.class).getResultList();

        clientes.forEach( c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));

        em.close();
    }
}
