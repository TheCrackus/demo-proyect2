/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erick Montes
 */
public class Conexion {
    
    private Connection con;
    
    public Connection obtenerConexion() {
        String user = "bdec0426f50e96";
        String pwd = "62afed48";
        String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_656377383698b2d";
        String mySqlDriver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(mySqlDriver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return con;
    }
    
}
