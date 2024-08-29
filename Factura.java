//Jaime Alberto Suarez Moctezuma.
// Clase principal del sistema de facturación.

// Importacion de librerias.
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase Factura
public class Factura{
	
	// Datos generales de la base de datos.
	private static final String URL = "jdbc:sqlite:C:/Users/suare/Documents/Java Scripts/Factura/db/facturacion.db";
	private static final String USER = "";
	private static final String PASSWORD = "";

	// Clase principal. 
    public static void main(String[] args){

    	// Creación de la interfaz gráfica.
        JFrame frame = new JFrame("Sistema de Facturación.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); //Definimos espacios generales al abrir la app.
        frame.setLayout(new GridLayout(4, 2));

        // Asignación de un color de fondo.
        frame.getContentPane().setBackground(new Color(97, 92, 108));

        JButton empresaButton = new JButton("Empresa");
        JButton productoButton = new JButton("Producto");
        JButton clienteButton = new JButton("Cliente");

        frame.add(empresaButton);
        frame.add(productoButton);
        frame.add(clienteButton);

        empresaButton.addActionListener(e -> new EmpresaForm().setVisible(true));
        productoButton.addActionListener(e -> new ProductoForm().setVisible(true));
        clienteButton.addActionListener(e -> new ClienteForm().setVisible(true));

        frame.setVisible(true);
    }
}