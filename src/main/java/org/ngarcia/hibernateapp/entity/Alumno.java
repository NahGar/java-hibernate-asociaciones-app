package org.ngarcia.hibernateapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    //Alumno es clase principal y Curso es la secundaria
    //no utiliza CascadeType ALL porque al eliminarse un alumno eliminaría los cursos
    //y esos cursos podrían estar en otros alumnos
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //pesonalizado
    @JoinTable(name = "alumnos_cursos", joinColumns = @JoinColumn(name="id_alumno"),
    inverseJoinColumns = @JoinColumn(name = "id_curso"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"id_alumno","id_curso"}))
    private List<Curso> cursos;

    public Alumno() {
        this.cursos = new ArrayList<>();
    }

    public Alumno(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.getAlumnos().add(this);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}
