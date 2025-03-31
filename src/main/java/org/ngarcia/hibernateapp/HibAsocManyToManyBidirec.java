package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Alumno;
import org.ngarcia.hibernateapp.entity.Curso;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibAsocManyToManyBidirec {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Evan", "Garcia");
            Alumno alumno2 = new Alumno("Gael", "Garcia");

            Curso curso1 = new Curso("Curso Java", "Andrés");
            Curso curso2 = new Curso("Curso Php", "Marcos");

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso2);

            em.persist(alumno1);
            em.persist(alumno2);

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
