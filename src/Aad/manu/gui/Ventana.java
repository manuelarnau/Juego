package Aad.manu.gui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ventana {

    //Elementos de la ventana(swing)

    private JPanel panel1;


    private JLabel textId;
    private JLabel textNombre;
    private JLabel textNivel;
    private JLabel textEnergia;
    private JLabel textPuntos;

    public JTextField textFieldId;
    public JTextField textFieldNombre;
    public JTextField textFieldNivel;
    public JTextField textFieldEnergia;
    public JTextField textFieldPuntos;
    public JTextField textFieldBuscar;

    public JButton nuevoButton;
    public JButton guardarButton;
    public JButton modificarButton;
    public JButton eliminarButton;
    public JButton eliminarTodosButton;
    public JButton buscarButton;

    public JTextPane buscarTextPane;


    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textFieldId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        textFieldNivel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        textFieldEnergia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        textFieldPuntos.addKeyListener(new KeyAdapter() {
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
