/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Conexion;
import models.Historial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {

    public List<Historial> listarPorDni(
            String dni
    ) {

        List<Historial> lista =
                new ArrayList<>();

        String sql =
        "SELECT * FROM historial_paciente WHERE dni_paciente=?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, dni);
            
            System.out.println("Buscando historial para DNI: " + dni);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {
                
                System.out.println(
                "Diagnóstico encontrado: "
                + rs.getString("diagnostico")
                    );

                Historial h =
                        new Historial();

                h.setFecha(
                        rs.getString("fecha")
                );

                h.setDiagnostico(
                        rs.getString("diagnostico")
                );

                h.setProcedimiento(
                        rs.getString("procedimiento")
                );

                h.setTipoConsulta(
                        rs.getString("tipo_consulta")
                );

                lista.add(h);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}