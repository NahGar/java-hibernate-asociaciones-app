package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.ngarcia.hibernateapp.entity.Factura;
import org.ngarcia.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibFetchManyToOneCriteria {
   public static void main(String[] args) {

      EntityManager em = JpaUtil.getEntityManager();

      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Factura> query = cb.createQuery(Factura.class);
      Root<Factura> factura = query.from(Factura.class);
      query.select(factura);
      List<Factura> facturas = em.createQuery(query).getResultList();
      facturas.forEach( f-> System.out.println(f.getDescripcion() + " " +
              f.getCliente().getNombre()));
      em.close();
   }
}
