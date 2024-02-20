package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modificacion {
    private JLabel txTitulo;
    JPanel ventanota;
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JButton actualizarNotaButton;
    private JButton regresarButton;

    public Modificacion() {
        actualizarNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarNota();
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame pantalla = new JFrame("Conexion con MongoDB");
                pantalla.setContentPane(new Ingreso().ventana);
                pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pantalla.pack();
                pantalla.setSize(650,550);
                pantalla.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(regresarButton)).dispose();
            }
        });
    }

//    private void actualizarNota() {
//
//        // Conexion a la base de datos
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//        MongoDatabase database = mongoClient.getDatabase("mydb2");
//        MongoCollection<Document> collection = database.getCollection("mycollection");
//
//        // Se obtiene la cédula ingresada
//        int cedulaInput = Integer.parseInt(JOptionPane.showInputDialog(this.ventanota, "Ingrese la cédula del estudiante que desea actualizar:", "Actualizar por Cédula", JOptionPane.QUESTION_MESSAGE));
//
//        // Se obtiene las nuevas notas ingresadas
//        int nuevaNota1 = Integer.parseInt(txtNota1.getText());
//        int nuevaNota2 = Integer.parseInt(txtNota2.getText());
//
//        // Se crea un filtro para identificar el documento a actualizar por la cédula
//        Document filtro = new Document("Cedula", cedulaInput);
//
//        // Se crea un documento con los nuevos valores de notas
//        Document nuevosValores = new Document("Nota1", nuevaNota1)
//                .append("Nota2", nuevaNota2);
//
//        // Se crea una operación de actualización
//        Document update = new Document("$set", nuevosValores);
//
//        // Actualizamos el documento que cumple con el filtro
//        collection.updateOne(filtro, update);
//
//        // Limpiamos los campos de entrada después de la inserción
//        txtNota1.setText("");
//        txtNota2.setText("");
//
//        JOptionPane.showMessageDialog(this.ventanota, "Notas actualizadas correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);
//    }


    private void actualizarNota() {
        // Obtener el ID (Cédula) ingresado por el usuario
        String cedulaInput = JOptionPane.showInputDialog(this.ventanota, "Ingrese la Cédula del empleado que desea actualizar:", "Actualizar por Cédula", JOptionPane.QUESTION_MESSAGE);

        try {
            // Convertir la cédula a entero
            int cedula = Integer.parseInt(cedulaInput);

            // Obtener las notas ingresadas
            int nota1 = Integer.parseInt(txtNota1.getText());
            int nota2 = Integer.parseInt(txtNota2.getText());

            // Realizar la conexión a MongoDB
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("mydb2");
            MongoCollection<Document> collection = database.getCollection("mycollection");


            Document document = collection.findOneAndUpdate(Filters.eq("Cedula", cedula), Updates.set("Nota 1: ", nota1));

            JOptionPane.showMessageDialog(this.ventanota, "Notas actualizadas correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.ventanota, "La cédula debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

