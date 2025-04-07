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
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name="forma_pago")
    private String formaPago;

    @Embedded
    private Auditoria auditoria = new Auditoria();

    //cascade all al crear cliente crea a las direcciones
    //orphanRemoval elimina las direcciones que queden sin vinculación con algún cliente
    //FetchType.EAGER carga todas las tablas dependientes al cargar el Cliente, puede no ser lo
    //ideal ya que puede enlentecer mucho
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //sin JoinColumn crea una tabla intermedia clientes_direcciones con los ids de ambas tablas
    //con JoinColumn crea un atributo id_cliente que es la FK a clientes en direcciones
    //@JoinColumn(name="id_cliente")
    @JoinTable(name="clientes_direcciones",
            joinColumns = @JoinColumn(name="id_cliente"),
            inverseJoinColumns = @JoinColumn(name="id_direccion"),
            //la dirección es única en esta tabla
            uniqueConstraints = @UniqueConstraint(columnNames={"id_direccion"}))
    private List<Direccion> direcciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;

    //@OneToOne
    //@JoinColumn(name = "id_cliente_detalle")
    //private ClienteDetalle detalle;
    //Se modifica para onetoone bidireccional
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private ClienteDetalle detalle;


    //JPA requiere un constructor vacío si existe un constructor con parámetros
    public Cliente() {
        facturas = new ArrayList<>();
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

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public ClienteDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ClienteDetalle detalle) {
        this.detalle = detalle;
    }

    public void addDetalle(ClienteDetalle detalle) {
        this.detalle = detalle;
        detalle.setCliente(this);
    }

    public void removeDetalle() {
        detalle.setCliente(null);
        this.detalle = null;
    }

    public Cliente addFactura(Factura factura) {
        factura.setCliente(this);
        this.facturas.add(factura);
        return this;
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
                ", direcciones='" + direcciones + '\'' +
                ", facturas='" + facturas + '\'' +
                ", detalle='" + detalle + '\'';
    }
}
