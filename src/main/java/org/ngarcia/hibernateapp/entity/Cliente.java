package org.ngarcia.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(name="forma_pago")
    private String formaPago;

    @Embedded
    private Auditoria auditoria = new Auditoria();

    //cascade all al crear cliente crea a las direcciones
    //orphanRemoval elimina las direcciones que queden sin vinculación con algún cliente
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //sin JoinColumn crea una tabla intermedia clientes_direcciones con los ids de ambas tablas
    @JoinColumn(name="id_cliente")
    private List<Direccion> direcciones;

    //JPA requiere un constructor vacío si existe un constructor con parámetros
    public Cliente() {
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        LocalDateTime creadoEn = this.auditoria != null ? this.auditoria.getCreadoEn(): null;
        LocalDateTime editadoEn = this.auditoria != null ? this.auditoria.getEditadoEn(): null;
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creado en='" + creadoEn + '\'' +
                ", editado en='" + editadoEn + '\'' +
                ", direcciones='" + direcciones + '\'';
    }
}
