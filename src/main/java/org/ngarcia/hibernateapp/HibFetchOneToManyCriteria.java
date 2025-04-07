package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.ngarcia.hibernateapp.entity.*;
import org.ngarcia.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibFetchOneToManyCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);

        cliente.fetch("direcciones", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);
        query.select(cliente).distinct(true);
        List<Cliente> clientes = em.createQuery(query).getResultList();

        clientes.forEach( c -> System.out.println(c.getNombre() +
                ", direcciones: " + c.getDirecciones() +
                ", detalle: " + c.getDetalle()
        ));
        em.close();
    }
}
