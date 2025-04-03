package org.ngarcia.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ngarcia.hibernateapp.entity.Cliente;
import org.ngarcia.hibernateapp.util.JpaUtil;

public class HibFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        //cuando es una consulta personalizada las otras consultas EAGER
        //las ejecuta como sqls separadas (no hace join)
        //String sql = "Select c from Cliente c where c.id=:id";
        //con fetch carga las Direcciones en Cliente
        String sql = "Select c from Cliente c left join fetch c.direcciones left join fetch c.detalle where c.id=:id";
        Cliente cliente = em.createQuery(sql,Cliente.class)
                .setParameter("id",1L)
                .getSingleResult();

        //recién aquí obtiene las direcciones en la base de datos
        //luego de cambiar en Cliente @OneToMany(fetch = FetchType.EAGER... carga las dircciones en el find previo
        System.out.println(cliente.getDirecciones());
        System.out.println(cliente.getDetalle());

        em.close();
    }
}
