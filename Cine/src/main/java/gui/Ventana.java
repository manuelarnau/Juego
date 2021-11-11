package gui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ventana {
    public JPanel panel1;
    public JTabbedPane tabbedPanel;

    public JPanel tabbedPanelPeliculas;
    public JPanel tabbedPanelActores;
    public JPanel tabbedPanelDirectores;

    public JTextArea textAreaPeliculas;
    public JTextArea textAreaActores;
    public JTextArea textAreaDirectores;

    public JButton nuevoButton;
    public JButton eliminarButton;
    public JButton buscarButton;
    public JButton guardarButton;


    public JTextField textField_idPeliculas;
    public JTextField textField_DirectorPeliculas;
    public JTextField textField_TituloPeliculas;
    public com.toedter.calendar.JTextFieldDateEditor textField_FechaEstreno;

    public JTextField textField_IdActores;
    public JTextField textField_NombreActores;
    public com.toedter.calendar.JTextFieldDateEditor textField_Fecha_Actores;

    public JTextField textField_Id_Directores;
    public JTextField textField_NombreDirectores;
    public com.toedter.calendar.JTextFieldDateEditor textField_FechaDirectores;

    public JTextField textFieldBuscar;

    public Ventana() {
        JFrame frame = new JFrame("Cine");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        textField_idPeliculas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        textField_DirectorPeliculas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });



        textField_IdActores.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });



        textField_Id_Directores.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

    }
}
