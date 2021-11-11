package Objetos;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    @Column(name = "id_director")
    private int id_director;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "fecha_estreno")
    private Date fecha_estreno;

    public Pelicula() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_director() {
        return id_director;
    }

    public void setId_director(int id_director) {
        this.id_director = id_director;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha_estreno() {
        return fecha_estreno;
    }

    public void setFecha_estreno(Date fecha_estreno) {
        this.fecha_estreno = fecha_estreno;
    }
}
