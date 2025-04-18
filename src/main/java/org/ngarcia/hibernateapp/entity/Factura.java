package org.ngarcia.hibernateapp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Long total;

    //por defecto es EAGER
    //@ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    //por default pone nombre cliente_id, pero se puede cambiar
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public Factura() {
    }

    public Factura(String descripcion, Long total) {
        this.descripcion = descripcion;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", total=" + total;
                // no imprime cliente porque el cliente imprime las facturas y loop
                //", cliente=" + cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(id, factura.id) && Objects.equals(descripcion, factura.descripcion) &&
                Objects.equals(total, factura.total);
    }

}
