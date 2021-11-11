package gui;


import Datos.HibernateUtil;
import Objetos.Actor;
import Objetos.Director;
import Objetos.Pelicula;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class VentanaController implements ActionListener {
    //Variables para la ventana y manejador de datos
    private final Ventana ventana;
    private final HibernateUtil datos;
    //Método para instanciar ventana y datos desde el main
    private int tab;

    public VentanaController(Ventana ventana, HibernateUtil datos) {
        this.datos = datos;
        this.ventana = ventana;
        HibernateUtil.buildSessionFactory();

        //Muestra los objetos en el campo de texto lateral
        buscarTodos(ventana.tabbedPanel.getSelectedIndex());

        //Añadir todos los actionListeners(Código abajo del todo)
        addEventListeners(this);
        ventana.tabbedPanel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tab = ventana.tabbedPanel.getSelectedIndex();
                System.out.println(tab);
                buscarTodos(tab);
                ventana.textFieldBuscar.setText("");
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        System.out.println(accion);
        System.out.println(ventana.tabbedPanel.getSelectedIndex());
        Object o;
//        0: peliculas // 1: actores // 2: directores

        switch (accion) {
            case "Nuevo":
                //Pone todos los campos en blanco
                resetInput();
                break;
            case "Guardar":
                //Asigna valores a un nuevo objeto

                if (compobarCampos(tab)) {
                    switch (tab) {
                        case 0:
                            Pelicula pelicula = new Pelicula();
                            pelicula = (Pelicula) asignarAtributos(pelicula, tab);
                            datos.crear_mod(pelicula);
                            break;
                        case 1:
                            Actor actor = new Actor();
                            actor = (Actor) asignarAtributos(actor, tab);
                            datos.crear_mod(actor);
                            break;
                        case 2:
                            Director director = new Director();
                            director = (Director) asignarAtributos(director, tab);
                            datos.crear_mod(director);
                            break;

                    }

                    showMessageDialog(null, "Se ha guardado el objeto");
                    buscarTodos(tab);
                }
                break;


            case "Eliminar":

                //elimina el objeto con campo especificado
                if (ventana.textField_idPeliculas.getText().isEmpty() && ventana.textField_IdActores.getText().isEmpty() && ventana.textField_Id_Directores.getText().isEmpty()) {
                    showMessageDialog(null, "Escriba el id que quiera eliminar en el campo id*");

                } else {
                    switch (tab) {
                        case 0:
                            datos.borrar(Integer.parseInt(ventana.textField_idPeliculas.getText()), tab);
                            break;
                        case 1:
                            datos.borrar(Integer.parseInt(ventana.textField_IdActores.getText()), tab);
                            break;
                        case 2:
                            datos.borrar(Integer.parseInt(ventana.textField_Id_Directores.getText()), tab);
                            break;
                    }
                    showMessageDialog(null, "Se ha eliminado el objeto");

                    buscarTodos(tab);
                    resetInput();
                }
                break;

            case "Buscar":
                o = null;
                // Busca personaje en la base de datos segun el id
                if (!ventana.textFieldBuscar.getText().isEmpty()) {
                    o = datos.obtener((Integer.parseInt(ventana.textFieldBuscar.getText())),tab);
                    if (o != null) {
                        cargar(o,tab);
                    } else {
                        showMessageDialog(null, "No se ha encontrado ningún objeto con este id");
                    }
                } else {
                    showMessageDialog(null, "Escriba el id del objeto que quiera buscar en el campo de arriba");

                }

                break;
        }
    }


    private void cargar(Object o, int tab) {

      switch (tab){
          case 0:
              Pelicula pelicula = (Pelicula)o;
              ventana.textField_idPeliculas.setText(Integer.toString(pelicula.getId()));
              ventana.textField_DirectorPeliculas.setText(Integer.toString(pelicula.getId_director()));
              ventana.textField_TituloPeliculas.setText(pelicula.getTitulo());
              ventana.textField_FechaEstreno.setText(new SimpleDateFormat("dd-MM-yyyy").format(pelicula.getFecha_estreno()));
              break;
          case 1:
              Actor actor = (Actor)o;
              ventana.textField_IdActores.setText(Integer.toString(actor.getId()));
              ventana.textField_NombreActores.setText(actor.getNombre());
              ventana.textField_Fecha_Actores.setText(new SimpleDateFormat("dd-MM-yyyy").format(actor.getFecha_nacimiento()));
              break;
          case 2:
              Director director = (Director)o;
              ventana.textField_Id_Directores.setText(Integer.toString(director.getId()));
              ventana.textField_NombreDirectores.setText(director.getNombre());
              ventana.textField_FechaDirectores.setText(new SimpleDateFormat("dd-MM-yyyy").format(director.getFecha_nacimiento()));
              break;

      }
    }

    private Object asignarAtributos(Object object, int tab) {
    Object objetoADevolver = null;
        switch (tab) {
            case 0:
                Pelicula pelicula = (Pelicula)object;
                System.out.println(Integer.parseInt(ventana.textField_idPeliculas.getText()));
                pelicula.setId(Integer.parseInt(ventana.textField_idPeliculas.getText()));
                pelicula.setId_director(Integer.parseInt(ventana.textField_DirectorPeliculas.getText()));
                pelicula.setTitulo(ventana.textField_TituloPeliculas.getText());
                try {
                    pelicula.setFecha_estreno(new Date(new SimpleDateFormat("dd-MM-yyyy").parse(ventana.textField_FechaEstreno.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                objetoADevolver = pelicula;
            break;
            case 1:
                Actor actor = (Actor) object;
                actor.setId(Integer.parseInt(ventana.textField_IdActores.getText()));
                actor.setNombre(ventana.textField_NombreActores.getText());
                try {
                    actor.setFecha_nacimiento(new Date(new SimpleDateFormat("dd-MM-yyyy").parse(ventana.textField_Fecha_Actores.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                objetoADevolver = actor;
            break;
            case 2:
                Director director = (Director) object;
                director.setId(Integer.parseInt(ventana.textField_Id_Directores.getText()));
                director.setNombre(ventana.textField_NombreDirectores.getText());
                try {
                    director.setFecha_nacimiento(new Date(new SimpleDateFormat("dd-MM-yyyy").parse(ventana.textField_FechaDirectores.getText()).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                objetoADevolver = director;
            break;
        }
        return objetoADevolver;
    }

    private void resetInput() {
        ventana.textField_idPeliculas.setText("");
        ventana.textField_DirectorPeliculas.setText("");
        ventana.textField_TituloPeliculas.setText("");
        ventana.textField_FechaEstreno.setText("");
        ventana.textField_IdActores.setText("");
        ventana.textField_NombreActores.setText("");
        ventana.textField_Fecha_Actores.setText("");
        ventana.textField_Id_Directores.setText("");
        ventana.textField_NombreDirectores.setText("");
        ventana.textField_FechaDirectores.setText("");

    }

    private void addEventListeners(ActionListener listener) {

        ventana.nuevoButton.addActionListener(listener);
        ventana.guardarButton.addActionListener(listener);
        ventana.eliminarButton.addActionListener(listener);
        ventana.buscarButton.addActionListener(listener);

    }

    private void buscarTodos(int tab) {
        ArrayList<Object> objetos = datos.obtenerTodos(tab);

        switch (tab) {
            case 0:
                ventana.textAreaPeliculas.setText(null);
                ArrayList<Pelicula> peliculas = (ArrayList<Pelicula>)(ArrayList<?>) objetos;
                for (Pelicula pelicula:
                     peliculas) {
                    if (ventana.textAreaPeliculas.getText().isEmpty()) {
                        ventana.textAreaPeliculas.setText(pelicula.getId() + " - " + pelicula.getTitulo());
                    } else {
                        ventana.textAreaPeliculas.setText(ventana.textAreaPeliculas.getText() + "\n" + pelicula.getId() + " - " + pelicula.getTitulo());

                    }

                }
                break;
            case 1:
                ventana.textAreaActores.setText(null);
                ArrayList<Actor> actores = (ArrayList<Actor>)(ArrayList<?>) objetos;
                for (Actor actor:
                        actores) {
                    if (ventana.textAreaActores.getText().isEmpty()) {
                        ventana.textAreaActores.setText(actor.getId() + " - " + actor.getNombre());
                    } else {
                        ventana.textAreaActores.setText(ventana.textAreaActores.getText() + "\n" + actor.getId() + " - " + actor.getNombre());
                    }

                }
                break;
            case 2:
                ventana.textAreaDirectores.setText(null);
                ArrayList<Director> directores = (ArrayList<Director>)(ArrayList<?>) objetos;
                for (Director director:
                        directores) {
                    if (ventana.textAreaDirectores.getText().isEmpty()) {
                        ventana.textAreaDirectores.setText(director.getId() + " - " + director.getNombre());
                    } else {
                        ventana.textAreaDirectores.setText(ventana.textAreaDirectores.getText() + "\n" + director.getId() + " - " + director.getNombre());

                    }

                }
                break;
        }

    }

    private boolean compobarCampos(int tab) {
        boolean procesoOk = true;

        switch (tab) {
            case 0:
                if (ventana.textField_idPeliculas.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El id de pelicula no puede estar vacío");

                } else if (ventana.textField_DirectorPeliculas.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El id de director no puede estar vacío");

                } else if (ventana.textField_TituloPeliculas.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El titulo de pelicula no puede estar vacío");

                } else if (ventana.textField_FechaEstreno.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "La fecha de estreno no puede estar vacía");
                }
                break;

            case 1:
                if (ventana.textField_IdActores.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El id de actor no puede estar vacío");

                } else if (ventana.textField_NombreActores.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El nombre de actor no puede estar vacío");

                } else if (ventana.textField_Fecha_Actores.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "La fecha de nacimiento del actor no puede estar vavía");
                }
                break;
            case 2:
                if (ventana.textField_Id_Directores.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El id de director no puede estar vacío");

                } else if (ventana.textField_NombreDirectores.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "El nombre de director no puede estar vacío");

                } else if (ventana.textField_FechaDirectores.getText().isEmpty()) {
                    procesoOk = false;
                    showMessageDialog(null, "La fecha de nacimiento del director no puede estar vavía");
                }
                break;
        }

        System.out.println(procesoOk);
        return procesoOk;
    }
}
