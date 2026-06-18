/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Conexion;
import models.Receta;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RecetaDAO {

    public boolean guardar(Receta receta) {

        String sql =
        "INSERT INTO recetas(dni_paciente,nombre_paciente,medicamento,dosis,frecuencia,indicaciones,fecha) VALUES(?,?,?,?,?,?,?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, receta.getDniPaciente());
            ps.setString(2, receta.getNombrePaciente());
            ps.setString(3, receta.getMedicamento());
            ps.setString(4, receta.getDosis());
            ps.setString(5, receta.getFrecuencia());
            ps.setString(6, receta.getIndicaciones());
            ps.setString(7, receta.getFecha());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}
