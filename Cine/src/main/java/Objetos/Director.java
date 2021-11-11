package Objetos;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    //JOIN
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "director_id")
//    private List<Pelicula> peliculas;
//
//    public List<Pelicula> getPeliculas() {
//        return peliculas;
//    }
//
//    public void setPeliculas(List<Pelicula> peliculas) {
//        this.peliculas = peliculas;
//    }
//    //END JOIN

    public Director() {

    }

    public Director(int id, String nombre, Date fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
