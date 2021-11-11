package Objetos;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    //JOIN
    //Nota: no consigo eliminar entidades relacionadas, aunque no estén usadas en una tabla relacional
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "actores_peliculas",
//            joinColumns = @JoinColumn(name = "actor_id"),
//            inverseJoinColumns = @JoinColumn(name = "peliculas_id"))
//    private List<Pelicula> peliculas;
//
//    public List<Pelicula> getPeliculas() {
//        return peliculas;
//    }
//
//    public void setPeliculas(List<Pelicula> peliculas) {
//        this.peliculas = peliculas;
//    }
    //END JOIN

    public Actor (){

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