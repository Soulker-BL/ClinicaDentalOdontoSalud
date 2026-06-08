/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Conexion;
import models.Tratamiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO {

    public List<Tratamiento> listarPorDni(String dni) {

        List<Tratamiento> lista = new ArrayList<>();

        String sql = "SELECT id, dni_paciente, tratamiento, estado, porcentaje "
                   + "FROM tratamientos WHERE dni_paciente=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Tratamiento t = new Tratamiento();

                    t.setId(rs.getInt("id"));
                    t.setDniPaciente(rs.getString("dni_paciente"));
                    t.setTratamiento(rs.getString("tratamiento"));
                    t.setEstado(rs.getString("estado"));
                    t.setPorcentaje(rs.getInt("porcentaje"));

                    lista.add(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}