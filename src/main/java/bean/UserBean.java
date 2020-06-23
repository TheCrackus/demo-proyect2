/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ConfiguracionDAO;
import dao.UsuarioDAO;
import dto.ConfiguracionDTO;
import dto.UsuarioDTO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.model.file.UploadedFile;
import utilerias.Conexion;
import utilerias.Utilerias;

/**
 *
 * @author Erick Montes
 */
@Named(value = "userBean")
@SessionScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
public class UserBean extends BaseBean implements Serializable{
    private UploadedFile foto;
    private UsuarioDTO dto = new UsuarioDTO();
    private UsuarioDAO dao = new UsuarioDAO();
    private ConfiguracionDAO daoC = new ConfiguracionDAO();
    private static List<UsuarioDTO> listaDeUsuarios;
    private String volumen;
    private String resolucion;

    public UsuarioDTO getDto() {
        return dto;
    }
    
     
    public void setDto(UsuarioDTO dto) {
        this.dto = dto;
    }
    
    

    public List<UsuarioDTO> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<UsuarioDTO> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }
    
    @PostConstruct
    public void init(){
        
        listaDeUsuarios = new ArrayList<>();
        listaDeUsuarios = dao.TraeUsaurios();
    }
    
    public String prepareIndex(){
        init();
        return "/admin/listaUser?faces-redirect=true";
    }
    
    public String back(){
        init();
        return "/usuario/perfilUsuario?faces-redirect=true";
    }

    public String prepareAdd(){
        setAccion(ACC_CREAR);
        return "/usuario/userForm?faces-redirect=true";
    }
    
    public String configuraciones(){
        setDtoS();
        setResolucion(getDtoS().getConfiguracion().getResolucion());
        setVolumen(String.valueOf(getDtoS().getConfiguracion().getVolumen()));
        return "/usuario/configuracionJ?faces-redirect=true";
    }
    public String borrar(){
        try{
            dao.EliminaUsuario(dto);
            return prepareIndex();
        }catch(Exception e){
            error("errorBorrarUsuario","Error al eliminar Usuario");
            return "/admin/listaUser?faces-redirect=true";
        }
    }
   
    public void seleccionarUsuario(ActionEvent event){
        String idUser = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario");
        dto = new UsuarioDTO();
        dto.getUsuario().setIdUsuario(Integer.parseInt(idUser));
        try{
            dto = dao.TraeUsuario(dto);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String crear(){
        Utilerias utilerias;
        try{
            if (foto != null) {
                dto.getUsuario().setUsuarioImagen(getBytesFromInputStream(foto.getInputStream()));
            } else {
                System.out.println(foto);
                File img = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/imagen.png"));
                FileInputStream fis = new FileInputStream(img);
                dto.getUsuario().setUsuarioImagen(getBytesFromInputStream(fis));
            }
            if(dao.RegistraUsuario(dto)){
                utilerias = new Utilerias();
                utilerias.enviarEmail(dto.getUsuario().getUsuarioCorreo(),
                                        "Registro de Cuenta",
                                        "El usuario ha sido registrado satisfactoriamente \n" +
                                        dto.getUsuario().getUsuario()+ "\n" +dto.getUsuario().getContrasena());
            }
            return "/index?faces-redirect=true";
        }catch(Exception e){
            error("errorUsuario","Error al crear Usuario");
            return "/index?faces-redirect=true";
        }
    }
    
    public byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }
    
    public String mostrarImagen(int idUser){
        return "/imagenUServlet?id="+idUser;
    }
    
    public String actualizar(){
        dto = getDtoS();
        setDtoS();
        try{
            if (foto.getFileName() != null) {
                dto.getUsuario().setUsuarioImagen(getBytesFromInputStream(foto.getInputStream()));
            }else{
                  dto.getUsuario().setUsuarioImagen(getDtoS().getUsuario().getUsuarioImagen());
//                File img = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/imagen.png"));
//                FileInputStream fis = new FileInputStream(img);
//                dto.getUsuario().setUsuarioImagen(getBytesFromInputStream(fis));
            }
            dao.ModificaUsuario(dto);
            setDtoS();
            return "/usuario/perfilUsuario?faces-redirect=true";
        }catch(Exception e){
            error("errorEditarEvento","Error al actualizar evento");
            return "/admin/listaAdmin?faces-redirect=true";
        }
    }
    
    public String actualizarC(){
        ConfiguracionDTO dtoC = new ConfiguracionDTO();
        dtoC.setConfiguracion(getDtoS().getConfiguracion());
        if(daoC.ModificaConfiguracion(dtoC)){
            System.out.println("Actualizado Con exito");
            setDtoS();
        }
        return "/usuario/configuracionJ?faces-redirect=true";        
    }
    
    public void generateReporte(ActionEvent aevent) { 
        Conexion con = new Conexion();      
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/UsuarioR.jasper"));
        try {
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, con.obtenerConexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);            
            ServletOutputStream sos = response.getOutputStream();
            sos.write(bytes,0,bytes.length);
            sos.flush();
            sos.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generateReporteI(ActionEvent event) {       
        
        String claveSel = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new UsuarioDTO();
        dto.getUsuario().setIdUsuario(Integer.parseInt(claveSel));
        try {
            Conexion con = new Conexion();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/UsuariosI.jasper"));
            Map parametro = new HashMap();
            parametro.put("idUsuario", dto.getUsuario().getIdUsuario());
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, con.obtenerConexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            sos.write(bytes,0,bytes.length);
            sos.flush();
            sos.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String partidas(){
        return "/usuario/partidas?faces-redirect=true";
    }
    
    public String objetos(){
        return "/usuario/objetos?faces-redirect=true";
    }

    
   
}
