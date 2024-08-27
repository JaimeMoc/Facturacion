// Paquete principal de facturación.
package main;

// Importación de las líbrerias.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Creación de la clase Empresa.
public class Empresa {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String rfc;
    private String logoPath;

    public Empresa(int id, String nombre, String direccion, String telefono, String email, String rfc,
            String logoPath) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.rfc = rfc;
        this.logoPath = logoPath;
    }

    // Creación de los getters y setters. 
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logopath) {
        this.logoPath = logopath;
    }
    
    // Método para guardar un nuevo cliente en la base de datos.
    public boolean guardarEmpresa(Connection conexion) {
    	String query = "INSERT INTO empresa (nombre, direccion, telefono, email, rfc, logoPath) VALUES (? ,? ,? ,? ,? ,?)";
    try {
    	PreparedStatement stmt = conexion.prepareStatement(query);
    	stmt.setString(1, nombre);
    	stmt.setString(2, direccion);
    	stmt.setString(3, telefono);
    	stmt.setString(4, email);
    	stmt.setString(5, rfc);
    	stmt.setString(6, logoPath);
    	int rowsInserted = stmt.executeUpdate();
    	stmt.close();
    	return rowsInserted >0;
    } catch (SQLException e) {
    	e.printStackTrace();
    	return false;
    }
  }
    
    // Método para actualizar un cliente existente en la base de datos.
    public boolean actualizarEmpresa(Connection conexion) {
    	String query = "Update empresa SET nombre = ?, direccion = ?, telefono = ?, email = ?, rfc = ?, logoPath = ? WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setString(1, nombre);
    		stmt.setString(2, direccion);
    		stmt.setString(3, telefono);
    		stmt.setString(4, email);
    		stmt.setString(5, rfc);
    		stmt.setString(6, logoPath);
    		stmt.setInt(7, id);
    		int rowsUpdated = stmt.executeUpdate();
    		stmt.close();
    		return rowsUpdated > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    // Método para buscar un cliente en la base de datos por ID. 
    public static Empresa buscarEmpresaPorId(Connection conexion, int id) {
    	String query = " SELECT * FROM empresa WHRE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setInt(1, id);
    		ResultSet rs = stmt.executeQuery();
    		if (rs.next()) {
    			Empresa empresa = new Empresa(
    					rs.getInt("id"),
    					rs.getString("nombre"),
    					rs.getString("direccion"),
    					rs.getString("telefono"),
    					rs.getString("email"),
    					rs.getString("rfc"),
    					rs.getString("logoPath")
    					);
    			rs.close();
    			stmt.close();
    			return empresa;
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
    
    // Método para eliminar un cliente de la base de datos. 
    public boolean eliminarEmpresa(Connection conexion) {
    	String query = "DELETE FROM empresa WHERE id = ?";
    	try {
    		PreparedStatement stmt = conexion.prepareStatement(query);
    		stmt.setInt(1,id);
    		int rowsDeleted = stmt.executeUpdate();
    		stmt.close();
    		return rowsDeleted > 0;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}