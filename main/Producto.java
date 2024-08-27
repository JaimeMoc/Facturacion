// Paquete principal de factura.
package main;

// Importación de las líbrerias. 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Creación de la clase producto.
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;

    public Producto(int id, String nombre, String descripcion, float precio){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Creación de los getters y setters.
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public float getPrecio(){
        return precio;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }
    
    // Método para guardar los productos en la base de datos.
    public boolean guardarProducto(Connection conexion){
    	String query = "INSERT INTO producto (nombre, descripcion, precio) VALUES (?, ?, ?)";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setString(1, nombre);
    		stmt.setString(2, descripcion);
    		stmt.setFloat(3, precio);
    		int rowsInserted = stmt.executeUpdate();
    		stmt.close();
    		return rowsInserted > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    // Método para actualizar productos.
    public boolean actualizarProducto(Connection conexion){
    	String query = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ? WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setString(1, nombre);
    		stmt.setString(2, descripcion);
    		stmt.setFloat(3, precio);
    		stmt.setInt(4, id);
    		int rowsUpdated = stmt.executeUpdate();
    		stmt.close();
    		return rowsUpdated > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    // Método para buscar un producto por ID. 
    public static Producto buscarProductoPorID(Connection conexion, int id) {
    	String query = "SELECT * FROM producto WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setInt(1, id);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()){
    			Producto producto = new Producto(
    					rs.getInt("id"),
    					rs.getString("nombre"),
    					rs.getString("descripcion"),
    					rs.getFloat("precio")
    					);
    			rs.close();
    			stmt.close();
    			return producto;
    		} else {
    			rs.close();
    			stmt.close();
    			return null;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    // Método para eliminar un producto en la bd.
    public boolean eliminarProducto(Connection conexion) {
    	String query = "DELETE FROM producto WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setInt(1, id);
    		int rowsDeleted = stmt.executeUpdate();
    		stmt.close();
    		return rowsDeleted > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}