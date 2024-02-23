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
    private JButton buscarButton;
    // Declarar la colección como un campo de clase
    private MongoCollection<Document> collection;


    public Modificacion() {
        // Conexión a la base de datos
        connectToDatabase();


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
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarcedula();
            }
        });
    }

    private void connectToDatabase(){
        // Realizar la conexión a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("mydb2");
        MongoCollection<Document> collection = database.getCollection("mycollection");

    }


    private void actualizarNota() {
        // Obtener el ID (Cédula) ingresado por el usuario
        String cedulaInput = JOptionPane.showInputDialog(this.ventanota, "Ingrese la Cédula del empleado que desea actualizar:", "Actualizar por Cédula", JOptionPane.QUESTION_MESSAGE);

        try {
            // Convertir la cédula a entero
            int cedula = Integer.parseInt(cedulaInput);

            // Obtener las notas ingresadas
            int nota1 = Integer.parseInt(txtNota1.getText());
            int nota2 = Integer.parseInt(txtNota2.getText());

            // Crear el filtro para encontrar el documento con la cédula específica
            Bson filtro = Filters.eq("Cedula:", cedula);

            // Crear la operación de actualización para ambos campos de notas
            Bson actualizacion = Updates.combine(
                    Updates.set("Nota 1:", nota1),  // Cambiado de "Nota 1" a "Nota1"
                    Updates.set("Nota 2:", nota2)   // Nuevo campo "Nota2"
            );

            // Realizar la actualización en la base de datos
            Document document = collection.findOneAndUpdate(filtro, actualizacion);

            JOptionPane.showMessageDialog(this.ventanota, "Notas actualizadas correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.ventanota, "La cédula debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarcedula() {
        try {
            // Asegúrate de que collection no sea null antes de usarlo
            if (collection != null) {
                // Solicitar al usuario que ingrese la cédula
                String cedulaInput = JOptionPane.showInputDialog(ventanota, "Ingrese la Cédula del empleado que desea buscar:", "Buscar por Cédula", JOptionPane.QUESTION_MESSAGE);

                // Convertir la cédula a entero
                int cedula = Integer.parseInt(cedulaInput);

                // Crear el filtro para encontrar el documento con la cédula específica
                Bson filtro = Filters.eq("Cedula", cedula);

                // Realizar la consulta en la base de datos
                Document document = collection.find(filtro).first();

                // Verificar si se encontró un documento
                if (document != null) {
                    // Obtener los datos del documento y mostrarlos en algún componente (por ejemplo, JOptionPane)
                    String mensaje = "Nombre: " + document.getString("Nombre") + "\n" +
                            "Nota 1: " + document.getInteger("Nota1") + "\n" +
                            "Nota 2: " + document.getInteger("Nota2");
                    JOptionPane.showMessageDialog(ventanota, mensaje, "Datos del Registro", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ventanota, "No se encontró ningún registro con la cédula proporcionada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(ventanota, "Error de conexión a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventanota, "La cédula debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }





}

