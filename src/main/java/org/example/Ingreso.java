package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingreso {
    JPanel ventana;
    private JTextField txtcedula;
    private JTextField txtnombre;
    private JTextField txtnota1;
    private JTextField txtnota2;
    private JButton borrarButton;
    private JButton agregarButton;
    private JButton vntModificarNotaButton;
    private JButton actualizarButton;

    public Ingreso() {

        //Boton de agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
                MongoDatabase database = mongoClient.getDatabase("mydb2");
                MongoCollection<Document> collection = database.getCollection("mycollection");

                // Obtener los valores ingresados por el usuario
                String nombre = txtnombre.getText();
                //double salario = Double.parseDouble(txtcedula.getText());
                int cedula = Integer.parseInt(txtcedula.getText());
                int nota1 = Integer.parseInt(txtnota1.getText());
                int nota2 = Integer.parseInt(txtnota2.getText());


                Document document = new Document("Cedula: ", cedula)
                        .append("Nombre: ", nombre)
                        .append("Nota 1: ", nota1)
                        .append("Nota 2: ", nota2);
                collection.insertOne(document);
                System.out.println("Documento insertado");

                // Limpiar los campos de entrada después de la inserción
                txtcedula.setText("");
                txtnombre.setText("");
                txtnota1.setText("");
                txtnota2.setText("");

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Datos agregados correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);

                collection.insertOne(document);
                System.out.println("Documento insertado");

            }
        });

        // Boton de Borrar
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos de entrada después de la inserción
                txtcedula.setText("");
                txtnombre.setText("");
                txtnota1.setText("");
                txtnota2.setText("");
            }
        });

        // Boton de Modificar la Nota
        vntModificarNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame modinota = new JFrame("Opcion Agregar");
                modinota.setContentPane(new Modificacion().ventanota);
                modinota.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                modinota.pack();
                modinota.setSize(750,550);
                modinota.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(vntModificarNotaButton)).dispose();
            }
        });
    }

}

