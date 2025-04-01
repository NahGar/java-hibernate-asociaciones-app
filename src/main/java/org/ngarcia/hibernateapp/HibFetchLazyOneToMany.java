package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibFetchLazyOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        //como tiene Fetch Lazy por defecto (@OneToMany), no carga las direcciones al cargar el client
        Cliente cliente = em.find(Cliente.class, 1L);

        //recién aquí obtiene las direcciones en la base de datos
        //luego de cambiar en Cliente @OneToMany(fetch = FetchType.EAGER... carga las dircciones en el find previo
        System.out.println(cliente.getDirecciones());

        em.close();
    }
}
