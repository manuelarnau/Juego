package Aad.manu;

import Aad.manu.datos.AccesoADatos;
import Aad.manu.gui.Ventana;
import Aad.manu.gui.VentanaController;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

        // Instanciar Ventana
        Ventana ventana = new Ventana();
        AccesoADatos datos = new AccesoADatos();
        VentanaController controlador = new VentanaController(ventana,datos);

    }
}
