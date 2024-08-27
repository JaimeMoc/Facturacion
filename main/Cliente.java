// Paquete principal de Factura.
package main;

// Importación de las líbrerias.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Creación de la clase Cliente. 
public class Cliente {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

    public Cliente(int id, String nombre, String direccion, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

// Creación de los Getters y Setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = email;
    }
    
    // Método para guardar un nuevo cliente en la base de datos.
    public boolean guardarCliente(Connection conexion) {
    	String query = "INSERT INTO clientes (nombre, direccion, telefono, email) Values (?, ?, ?, ?)";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setString(1, nombre);
    		stmt.setString(2, direccion);
    		stmt.setString(3, telefono);
    		stmt.setString(4, email);
    		int rowsInserted =stmt.executeUpdate();
    		stmt.close();
    		return rowsInserted > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    // Método para actualizar un cliente existente en la base de datos. 
    public boolean actualizarCliente(Connection conexion) {
    	String query = "Update clientes SET nombre = ?, direccion = ?, telefono = ? , email= ? WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setString(1, nombre);
    		stmt.setString(2, direccion);
    		stmt.setString(3, telefono);
    		stmt.setString(4, email);
    		stmt.setInt(5, id);
    		int rowsUpdated = stmt.executeUpdate();
    		stmt.close();
    		return rowsUpdated > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    // Método para buscar un cliente en la base de datos por ID.
    public static Cliente buscarClientePorID(Connection conexion, int id){
    	String query = "SELECT * FROM clientes WHERE id = ? ";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setInt(1, id);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
    			Cliente cliente = new Cliente(
    					rs.getInt("id"),
    					rs.getString("nombre"),
    					rs.getString("direccion"),
    					rs.getString("telefono"),
    					rs.getString("email")
    					);
    			rs.close();
    			stmt.close();
    			return cliente;
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
    
    //Método para eliminar un cliente de la base de datos.
    public boolean eliminarCliente(Connection conexion){
    	String query = "DELETE FROM clientes WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setInt(1, id);
    		int rowsDeleted = stmt.executeUpdate();
    		stmt.close();
    		return rowsDeleted >0;
    	} catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}