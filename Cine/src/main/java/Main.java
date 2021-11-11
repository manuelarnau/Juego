import Datos.HibernateUtil;
import gui.Ventana;
import gui.VentanaController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

        // Instanciar Ventana
        Ventana ventana = new Ventana();
        HibernateUtil datos = new HibernateUtil();
        VentanaController controlador = new VentanaController(ventana,datos);

    }
}
