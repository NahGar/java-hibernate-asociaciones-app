package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.entity.Factura;
import org.ngarcia.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibFetchManyToOneCriteria {
   public static void main(String[] args) {

      EntityManager em = JpaUtil.getEntityManager();

      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Factura> query = cb.createQuery(Factura.class);
      Root<Factura> factura = query.from(Factura.class);
      //si no se indica JoinType hace INNER JOIN
      Join<Factura, Cliente> cliente = (Join) factura.fetch("cliente", JoinType.LEFT);
      cliente.fetch("detalle", JoinType.LEFT);
      //query.select(factura);
      query.select(factura).where(cb.equal(cliente.get("id"), 1L));
      List<Factura> facturas = em.createQuery(query).getResultList();
      facturas.forEach( f-> System.out.println(f.getDescripcion() + " " +
              f.getCliente().getNombre()));
      em.close();
   }
}
