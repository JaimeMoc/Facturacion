// Paquete principal de facturación.
package main;

// Importación de las líbrerias.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

// Creación de la clase Empresa.
public class EmpresaForm extends JFrame {
    private JTextField nombreField, direccionField, telefonoField, emailField, rfcField, logoPathField;
    private Empresa empresa;

    public EmpresaForm(){
        setTitle("Formulario de Empresa:");
        setSize(300,300);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        add(direccionField);

        add(new JLabel("Telefono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add (new JLabel("RFC:"));
        rfcField = new JTextField();
        add(rfcField);

        add (new JLabel("Logo Path:"));
        logoPathField = new JTextField();
        add(logoPathField);

        JButton guardarButton = new JButton("Guardar");
        add(guardarButton);

        guardarButton.addActionListener(e ->{
            empresa = new Empresa(0, "", "", "", "", "", "");

            empresa.setNombre(nombreField.getText());
            empresa.setDireccion(direccionField.getText());
            empresa.setTelefono(telefonoField.getText());
            empresa.setEmail(emailField.getText());
            empresa.setRfc(rfcField.getText());
            empresa.setLogoPath(logoPathField.getText());

            try (Connection conexion = Factura.conectar()){
                if (empresa.guardarEmpresa(conexion)){
                    JOptionPane.showMessageDialog(this, "Empresa guardada correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardarlo.");
                }
            } catch (SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error de conexion a la base de datos.");
            }
        });
    }
}