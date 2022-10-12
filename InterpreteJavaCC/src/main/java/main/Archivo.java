/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author oscar
 */
public class Archivo {

    private File ficheroGlobal = null;

    public Archivo() {
    }

    public void obtenerArchivo(JTextArea areaTexto) {
        File fichero;
        JFileChooser seleccionar = new JFileChooser();

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo CCC", "ccc");
        seleccionar.setAcceptAllFileFilterUsed(false);
        seleccionar.addChoosableFileFilter(filtro);
        seleccionar.showOpenDialog(null);
        fichero = seleccionar.getSelectedFile();

        try {
            FileReader Lector = new FileReader(fichero);
            BufferedReader bufer = new BufferedReader(Lector);
            String linea = "";
            areaTexto.setText("");
            while ((linea = bufer.readLine()) != null) {
                areaTexto.append(linea + "\n");
            }
            ficheroGlobal = fichero;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
        }
    }

    public void limpiarFichero() {
        ficheroGlobal = null;
    }

    public void guardar(String texto) {
        if (ficheroGlobal != null) {
            int decision = JOptionPane.showConfirmDialog(null, "¿Estas seguro de guardar el archivo?");
            if (decision == JOptionPane.YES_OPTION) {
                try {
                    FileWriter escribir;
                    escribir = new FileWriter(ficheroGlobal, false);
                    escribir.write(texto);
                    escribir.close();
                    JOptionPane.showMessageDialog(null, "Se guardo el archivo");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error " + e);
                }
            }
        } else {
            guardarComo(texto);
        }
    }

    public void guardarComo(String texto) {
        try {
            File fichero;
            FileWriter escribir;
            JFileChooser seleccionar = new JFileChooser();

            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo CCC", "ccc");
            seleccionar.setAcceptAllFileFilterUsed(false);
            seleccionar.addChoosableFileFilter(filtro);
            int guardar = seleccionar.showDialog(null, "Guardar");

            if (guardar == JFileChooser.APPROVE_OPTION) {
                fichero = seleccionar.getSelectedFile();
                if (fichero.getName().endsWith(".ccc")) {
                    if (fichero.exists()) {
                        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas Sobreescribir?");
                        if (opcion == JOptionPane.YES_OPTION) {
                            fichero.createNewFile();
                            escribir = new FileWriter(fichero, false);
                            escribir.write(texto);
                            escribir.close();
                            JOptionPane.showMessageDialog(null, "Archivo Guardado");
                            ficheroGlobal = fichero;
                        }
                    } else {
                        fichero.createNewFile();
                        escribir = new FileWriter(fichero, false);
                        escribir.write(texto);
                        escribir.close();
                        JOptionPane.showMessageDialog(null, "Archivo Guardado");
                        ficheroGlobal = fichero;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No es un archivo .ccc");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
        }

    }

}
