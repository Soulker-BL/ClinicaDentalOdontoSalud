/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Conexion;
import models.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteDAO {

    public Paciente buscarPorDni(String dni) {

    Paciente paciente = null;

    String sql = "SELECT * FROM pacientes WHERE dni = ?";

    try {

        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, dni.trim());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            paciente = new Paciente();

            paciente.setDni(rs.getString("dni"));
paciente.setNombre(rs.getString("nombre"));
paciente.setApellido(rs.getString("apellido"));
paciente.setFechaNacimiento(
        rs.getString("fecha_nacimiento")
);
paciente.setTelefono(rs.getString("telefono"));
paciente.setEmail(rs.getString("email"));
paciente.setDireccion(
        rs.getString("direccion")
);
paciente.setEstadoTratamiento(
        rs.getString("estado_tratamiento")
);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return paciente;
}
    
    public boolean guardar(Paciente paciente) {
        
        

    String sql =
    "INSERT INTO pacientes(dni,nombre,apellido,fecha_nacimiento,telefono,email,direccion) VALUES(?,?,?,?,?,?,?)";

    try {

        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, paciente.getDni());
        ps.setString(2, paciente.getNombre());
        ps.setString(3, paciente.getApellido());
        ps.setString(4, paciente.getFechaNacimiento());
        ps.setString(5, paciente.getTelefono());
        ps.setString(6, paciente.getEmail());
        ps.setString(7, paciente.getDireccion());
      

        return ps.executeUpdate() > 0;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return false;
}
}