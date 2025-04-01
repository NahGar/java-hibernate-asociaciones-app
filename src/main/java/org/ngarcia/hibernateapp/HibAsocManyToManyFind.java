package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Alumno;
import org.ngarcia.hibernateapp.entity.Curso;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocManyToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);

            //Curso curso1 = new Curso("Curso Java", "Andrés");
            //Curso curso2 = new Curso("Curso Php", "Marcos");
            Curso curso1 = em.find(Curso.class, 1L);
            Curso curso2 = em.find(Curso.class, 2L);

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso2);

            //no require merge
            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

        }
        catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
