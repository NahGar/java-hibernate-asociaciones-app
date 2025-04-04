package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Alumno;
import org.ngarcia.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibFetchManyToMany {
   public static void main(String[] args) {

      EntityManager em = JpaUtil.getEntityManager();

      String sql = "Select distinct a from Alumno a left join fetch a.cursos";
      List<Alumno> alumnos = em.createQuery(sql,Alumno.class).getResultList();
      alumnos.forEach( a -> System.out.println(a.getNombre() + a.getCursos()));

      em.close();
   }
}
