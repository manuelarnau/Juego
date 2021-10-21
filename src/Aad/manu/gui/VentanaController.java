package Aad.manu.gui;

import Aad.manu.clases.Personaje;
import Aad.manu.datos.AccesoADatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class VentanaController implements ActionListener {
    //Variables para la ventana y manejador de datos
    private final Ventana ventana;
    private final AccesoADatos datos;
    //Método para instanciar ventana y datos desde el main

    public VentanaController(Ventana ventana, AccesoADatos datos) {
        this.datos = datos;
        this.ventana = ventana;

        //Muestra los objetos en el campo de texto lateral
        buscarTodos();

        //Añadir todos los actionListeners(Código abajo del todo)
        addEventListeners(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        Personaje personaje;
        System.out.println(accion);
        switch (accion) {
            case "Nuevo":

                //Pone todos los campos en blanco
                resetInput();
                break;
            case "Guardar":
                //Asigna valores a un nuevo objeto
                personaje = new Personaje();


                if (compobarCampos()) {

                    personaje = asignarAtributos(personaje);

                    datos.addPersonaje(personaje);
                    showMessageDialog(null, "Se ha guardado el personaje");
                    buscarTodos();
                }
                break;

            case "Modificar":

                //Modifica objeto segun campo
                personaje = new Personaje();
                if (compobarCampos()) {

                    personaje = asignarAtributos(personaje);
                    datos.modificarPersonaje(personaje.getId(), personaje);
                    showMessageDialog(null, "Se ha modificado la entrada ");
                }
                break;

            case "Eliminar":

                //elimina el objeto con campo especificado
                if (ventana.textFieldId.getText().isEmpty()) {
                    showMessageDialog(null, "Escriba el id que quiera eliminar en el campo id*");

                } else {
                    datos.borrarPersonaje(Integer.parseInt(ventana.textFieldId.getText()));
                    showMessageDialog(null, "Se ha eliminado el personaje");

                    buscarTodos();
                    resetInput();
                }
                break;

            case "Eliminar Todos":
                datos.borrarTodosPersonajes();
                buscarTodos();
                resetInput();
                break;

            case "Buscar":
                personaje = null;
                // Busca personaje en la base de datos segun el id
                if (!ventana.textFieldBuscar.getText().isEmpty()) {
                    personaje = datos.obtenerPersonaje(Integer.parseInt(ventana.textFieldBuscar.getText()));
                    if (personaje != null) {
                        cargar(personaje);
                    } else {
                        showMessageDialog(null, "No se ha encontrado ningún personaje con este id");
                    }
                } else {
                    showMessageDialog(null, "Escriba el id del personaje que quiera buscar en el campo de arriba");

                }

                break;
        }
    }

    private boolean compobarCampos() {
        boolean procesoOk = true;
        if (ventana.textFieldId.getText().isEmpty()) {
            procesoOk = false;
            showMessageDialog(null, "El id de personaje no puede estar vacío");

        } else if (ventana.textFieldNombre.getText().isEmpty()) {
            procesoOk = false;
            showMessageDialog(null, "El nombre de personaje no puede estar vacío");

        } else if (ventana.textFieldNivel.getText().isEmpty()) {
            procesoOk = false;
            showMessageDialog(null, "El nivel de personaje no puede estar vacío");

        } else if (ventana.textFieldEnergia.getText().isEmpty()) {
            procesoOk = false;
            showMessageDialog(null, "La energía de personaje no puede estar vacía");

        } else if (ventana.textFieldPuntos.getText().isEmpty()) {
            procesoOk = false;
            showMessageDialog(null, "Los puntos de personaje no puede estar vacíos");

        }
        System.out.println(procesoOk);
        return procesoOk;
    }

    private void cargar(Personaje personaje) {

        ventana.textFieldId.setText(Integer.toString(personaje.getId()));
        ventana.textFieldNombre.setText(personaje.getNombre());
        ventana.textFieldNivel.setText(Integer.toString(personaje.getNivel()));
        ventana.textFieldEnergia.setText(Integer.toString(personaje.getEnergia()));
        ventana.textFieldPuntos.setText(Integer.toString(personaje.getPuntos()));
    }

    private Personaje asignarAtributos(Personaje personaje) {
        personaje.setId(Integer.parseInt(ventana.textFieldId.getText()));
        personaje.setNombre(ventana.textFieldNombre.getText());
        personaje.setNivel(Integer.parseInt(ventana.textFieldNivel.getText()));
        personaje.setEnergia(Integer.parseInt(ventana.textFieldEnergia.getText()));
        personaje.setPuntos(Integer.parseInt(ventana.textFieldPuntos.getText()));
        return personaje;
    }

    private void resetInput() {
        ventana.textFieldId.setText("");
        ventana.textFieldNombre.setText("");
        ventana.textFieldNivel.setText("");
        ventana.textFieldEnergia.setText("");
        ventana.textFieldPuntos.setText("");
        ventana.textFieldBuscar.setText("");
    }

    private void addEventListeners(ActionListener listener) {

        ventana.nuevoButton.addActionListener(listener);
        ventana.guardarButton.addActionListener(listener);
        ventana.modificarButton.addActionListener(listener);
        ventana.eliminarButton.addActionListener(listener);
        ventana.eliminarTodosButton.addActionListener(listener);
        ventana.buscarButton.addActionListener(listener);

    }

    private void buscarTodos() {
        ArrayList<Personaje> personajes = datos.listaPersonajes();
        ventana.buscarTextPane.setText(null);
        for (Personaje personaje :
                personajes) {
            if (ventana.buscarTextPane.getText().isEmpty()) {
                ventana.buscarTextPane.setText(personaje.getId() + " - " + personaje.getNombre());
            } else {
                ventana.buscarTextPane.setText(ventana.buscarTextPane.getText() + "\n" + personaje.getId() + " - " + personaje.getNombre());

            }

        }
    }
}
