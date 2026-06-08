/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.UsuarioDAO;
import models.Usuario;

public class LoginController {

    UsuarioDAO dao = new UsuarioDAO();

    public boolean login(String usuario, String password) {

        Usuario user = new Usuario(usuario, password);

        return dao.validarUsuario(user);
    }
}
