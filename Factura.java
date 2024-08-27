//Jaime Alberto Suarez Moctezuma.
// Clase principal del sistema de facturación. 

// Importacion de librerias.
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

        // Asignación de las propiedades especificas para Clientes.
        JLabel clienteLabel = new JLabel("Cliente: ");
        clienteLabel.setOpaque(true);
        clienteLabel.setBackground(new Color(97, 92, 108));
        frame.add(clienteLabel);

        // Creación del campo de texto para cliente.
        JTextField clienteField = new JTextField();
        frame.add(clienteField);

        // Asignación de las propiedades para productos.
        JLabel productoLabel = new JLabel("Producto: ");
        productoLabel.setOpaque(true);
        productoLabel.setBackground(new Color(97, 92, 108));
        frame.add(productoLabel);

        // Creación del campo de texto para producto.
        JTextField productoField = new JTextField();
        frame.add(productoField);

        //Asignación de las propiedades propias para empresa. 
        JLabel empresaLabel = new JLabel("Empresa: ");
        empresaLabel.setOpaque(true);
        empresaLabel.setBackground(new Color(97, 92, 108));
        frame.add(empresaLabel);

        //Creación del campo de texto para empresa. 
        JTextField empresaField = new JTextField();
        frame.add(empresaField);
        
        //Creación del boton de guardado.
        JButton guardarBoton = new JButton("Guardar");
        guardarBoton.setBackground(Color.WHITE);
        frame.add(guardarBoton);

        // Obtención de los tres campos de texto.
        guardarBoton.addActionListener(e -> {
        	String clienteNombre = clienteField.getText();
        	String productoNombre = productoField.getText();
        	String empresaNombre = empresaField.getText();
        
        // Conexión a la base de datos con sus respectivas clases.
        	try (Connection conexion = conectar()){
        		if (conexion != null) {
        			guardarEnBaseDeDatos(conexion, clienteNombre, productoNombre, empresaNombre);
        			}
        		} catch(SQLException ex){
        			ex.printStackTrace();
        		}
        	});
        
        frame.setVisible(true);
    }
    
    // Conexión de bases de datos. Imprimiendo el mensaje de confirmación de conexión a la bd. 
    public static Connection conectar(){
    	Connection conexion = null;
    	try {
    		conexion = DriverManager.getConnection(URL, USER, PASSWORD);
    		System.out.println("Conexion exitosa a la base de datos.");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return conexion;
    }
    
    // Creacion de las instancias. 
    public static void guardarEnBaseDeDatos(Connection conexion, String cliente, String Producto, String empresa) {
    	Cliente cliente = new cliente(0, clienteNombre, "", "", "");
    	Producto producto = new producto(0, productoNombre, ""); 
    	Empresa empresa = new empresa (0, empresaNombre, "");
    	
    	if (cliente.guardarCliente(conexion) && producto.guardarProducto(conexion) && empresa.guardarEmpresa(conexion)) {
    		System.out.println("Los datos fueron guardados correctamente.");
    	} else { 
    		System.out.println("Hubo un error al guardar los datos.");
    	}
    }
}