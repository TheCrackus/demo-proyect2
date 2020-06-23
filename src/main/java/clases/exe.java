/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import dao.AdministradorDAO;
import dao.ConfiguracionDAO;
import dao.PartidaDAO;
import dao.UsuarioDAO;
import dto.AdministradorDTO;
import dto.ConfiguracionDTO;
import dto.ObjetosDTO;
import dto.PartidaDTO;
import dto.UsuarioDTO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Kilex
 */
class exe{
    public static void main(String[] args) throws SQLException{
        BufferedImage bImage = null;
        try {
            bImage = ImageIO.read(new File("C:\\Users\\edgar\\Desktop\\ipn.png"));
        } catch (IOException ex) {
            Logger.getLogger(exe.class.getName()).log(Level.SEVERE, null, ex);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "png", bos );
        } catch (IOException ex) {
            Logger.getLogger(exe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte [] data = bos.toByteArray();
        /*
        AdministradorDAO dao = new AdministradorDAO();
        System.out.println(dao.MuestraAdmin());
        
        UsuarioDAO dao = new UsuarioDAO();
        ConfiguracionDAO dao2 = new ConfiguracionDAO();
        PartidaDAO dao3 = new PartidaDAO();
       
        UsuarioDTO dto = new UsuarioDTO();
        ConfiguracionDTO dto2 = new ConfiguracionDTO();
       
        dto.getUsuario().setIdUsuario(1);
       
        dto = dao.TraeUsuarioConfiguracion(dto); //recupero los usuarios y sus configuraciones
        dto2.setConfiguracion(dto.getConfiguracion()); //asigno la configuracion del usuario con id = 1 a un dto diferente
       
        System.out.println(dto.getUsuario());//imprimo el usuario con id 1
        System.out.println(dto2.getConfiguracion());//imprimo la configuracion de ese usuario
       
       
       
        dto2.getConfiguracion().setVolumen(0.6f);//modifico un dato de la configuracion (cambien el dato pa que se note es flotante de 0 a 1)
        dto.getUsuario().setUsuarioNombre("joelito");//modifico el nombre de usuario (cambien para que se note)
       
        boolean r = dao2.ModificaConfiguracion(dto2);//modifico la config en la base de datos
        boolean r2 = dao.ModificaUsuario(dto);//modifico usuario en la base de datos
       
        if(r && r2){
            dto = dao.TraeUsuarioConfiguracion(dto); //recupero los usuarios y sus configuraciones
            dto2.setConfiguracion(dto.getConfiguracion()); //asigno la configuracion del usuario con id = 1 a un dto diferente
           
            System.out.println("\ndepues de la modificacion"+dto.getUsuario());
            System.out.println(dto2.getConfiguracion());//imprimo las modificaciones
           
        }
       
        UsuarioDTO dto3 = new UsuarioDTO();
        dto3.getUsuario().setIdUsuario(101);
        boolean r3 = dao.EliminaUsuario(dto3);//eliminamos a un usuario (este metodo elimina las partidas, configuraciones y documentos añadidos a este usuario)
       
        if(r3){
            System.out.println("Se elimino!");
        }else{
            System.out.println("No se elimino por que no existe");
        }
       
        //Aqui inserto una imagen al Usuario desde mi escritorio (modificar la ruta)
       
        UsuarioDTO dto4 = new UsuarioDTO();
        dto4.getUsuario().setUsuarioNombre("polo");
        dto4.getUsuario().setUsuarioPaterno("poloa");
        dto4.getUsuario().setUsuarioMaterno("polom");
        dto4.getUsuario().setUsuarioCorreo("polom@gmail.com");
        dto4.getUsuario().setUsuarioImagen(data);
        dto4.getUsuario().setUsuario("polo");
        dto4.getUsuario().setContrasena("polo");
       
        boolean r4 = dao.RegistraUsuario(dto4);//este registro guarda al usuario y una configuracion para su juego
       
        if(r4){
            System.out.println("\nSe registro a un usuario");
        }else{
            System.out.println("\nNo se registro el man");
        }
       
        System.out.println("\nLista de usuarios y configuraciones de estos:");
        List lista = dao.TraeUsuariosConfiguracion();
        for (int i = 0; i < lista.size(); i++) {
            UsuarioDTO p = (UsuarioDTO)lista.get(i);
            ConfiguracionDTO pc = new ConfiguracionDTO();
            pc.setConfiguracion((Configuracion)p.getConfiguracion());
            System.out.println(p.getUsuario());
            System.out.println(pc.getConfiguracion());
        }
       
        System.out.println("\nPartidas Usuario");
        
        dao3.TraePartidasUsuario(dto);
        for (int i = 0; i < dto.getPartidas().size(); i++) {
            Objetos o1 = dto.getPartidas().get(i).getObjetos();
            Partida p1 = dto.getPartidas().get(i).getPartida();
            puntoControl pc1 = dto.getPartidas().get(i).getPuntoControl();
            
            System.out.println(o1);
            System.out.println(p1);
            System.out.println(pc1);
        }
        
        System.out.println("\nLista de puntajes:");
        
        List lista2 = dao3.puntajes();
        for (int i = 0; i < lista2.size(); i++) {
            puntajes pu = (puntajes)lista2.get(i);
            System.out.println(pu);
        }*/
        
        /*AdministradorDAO dao0 = new AdministradorDAO();
        UsuarioDAO dao1 = new UsuarioDAO();

        UsuarioDTO dto5 = new UsuarioDTO();
        dto5.getUsuario().setUsuarioNombre("edd");
        dto5.getUsuario().setUsuarioPaterno("edd");
        dto5.getUsuario().setUsuarioMaterno("edd");
        dto5.getUsuario().setUsuarioCorreo("edd@gmail.com");
        dto5.getUsuario().setUsuarioImagen(data);
        dto5.getUsuario().setUsuario("edd");
        dto5.getUsuario().setContrasena("edd");
        
        boolean r1 = dao1.RegistraUsuario(dto5);
        
        
        AdministradorDTO dto6 = new AdministradorDTO();
        dto6.getAdministrador().setAdminNombre("paco");
        dto6.getAdministrador().setAdminPaterno("paco");
        dto6.getAdministrador().setAdminMaterno("paco");
        dto6.getAdministrador().setIdMunicipio(1);
        dto6.getAdministrador().setAdminCorreo("paco@gmail.com");
        dto6.getAdministrador().setAdminImagen(data);
        dto6.getAdministrador().setAdminUsuario("paco");
        dto6.getAdministrador().setAdminContrasena("paco");
        
        boolean r2 = dao0.registra(dto6);
        
        dto5.getUsuario().setUsuarioNombre("paco");
        dto5.getUsuario().setUsuarioPaterno("paco");
        dto5.getUsuario().setUsuarioMaterno("paco");
        dto5.getUsuario().setUsuarioCorreo("paco@gmail.com");
        dto5.getUsuario().setUsuarioImagen(data);
        dto5.getUsuario().setUsuario("paco");
        dto5.getUsuario().setContrasena("paco");
        
        boolean r3 = dao1.RegistraUsuario(dto5);
        
        
        dto6.getAdministrador().setAdminNombre("edd");
        dto6.getAdministrador().setAdminPaterno("edd");
        dto6.getAdministrador().setAdminMaterno("edd");
        dto6.getAdministrador().setIdMunicipio(1);
        dto6.getAdministrador().setAdminCorreo("edd@gmail.com");
        dto6.getAdministrador().setAdminImagen(data);
        dto6.getAdministrador().setAdminUsuario("edd");
        dto6.getAdministrador().setAdminContrasena("edd");
        
        boolean r4 = dao0.registra(dto6);*/
        
        /*AdministradorDAO dao0 = new AdministradorDAO();
        UsuarioDAO dao1 = new UsuarioDAO();
        
        UsuarioDTO dto1 = new UsuarioDTO();
        AdministradorDTO dto0 = new AdministradorDTO();
        
        dto1.getUsuario().setUsuario("edd");
        dto1.getUsuario().setContrasena("edd");
        
        Usuario u = dao1.iniciarSesion(dto1.getUsuario());
        System.out.println(u);
        
        dto0.getAdministrador().setAdminUsuario("root");
        dto0.getAdministrador().setAdminContrasena("root");
        
        Administrador a = dao0.iniciarSesion(dto0.getAdministrador());
        System.out.println(a);*/
    }
}
    