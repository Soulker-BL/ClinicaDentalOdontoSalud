/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Conexion;
import models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarUsuario(Usuario usuario) 
    {
        

        String sql =
                "SELECT * FROM usuarios WHERE usuario=? AND password=?";

        try {

            Connection con = Conexion.conectar();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

            return false;
        }      
}
    
public boolean registrarUsuario(
        Usuario usuario
) {

    String sql =
    "INSERT INTO usuarios(usuario,password) VALUES(?,?)";

    try {

        Connection con =
                Conexion.conectar();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(
                1,
                usuario.getUsuario()
        );

        ps.setString(
                2,
                usuario.getPassword()
        );

        return ps.executeUpdate() > 0;

    } catch(Exception e) {

        System.out.println(
                "Error: " + e.getMessage()
        );

        return false;
    }
}
}
