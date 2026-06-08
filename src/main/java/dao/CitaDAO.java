/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Conexion;
import models.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public boolean guardar(Cita cita) {

        String sql =
"INSERT INTO citas(dni_paciente,nombre_paciente,doctor,fecha,hora,motivo) VALUES(?,?,?,?,?,?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

           ps.setString(1, cita.getDniPaciente());
            ps.setString(2, cita.getNombrePaciente());
            ps.setString(3, cita.getDoctor());
            ps.setString(4, cita.getFecha());
            ps.setString(5, cita.getHora());
            ps.setString(6, cita.getMotivo());
            
            return ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public List<Cita> listar() {

        List<Cita> lista =
                new ArrayList<>();

        String sql =
"SELECT * FROM citas ORDER BY fecha ASC, hora ASC";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Cita cita =
                        new Cita();

                cita.setDniPaciente(
                        rs.getString("dni_paciente")
                );

                cita.setNombrePaciente(
                        rs.getString("nombre_paciente")
                );

                cita.setFecha(
                        rs.getString("fecha")
                );

                cita.setHora(
                        rs.getString("hora")
                );

                cita.setMotivo(
                        rs.getString("motivo")
                );
                
                cita.setDoctor(
                        rs.getString("doctor")
                );

                lista.add(cita);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}