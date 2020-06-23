/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.AdministradorDAO;
import dao.EstadoDAO;
import dao.MunicipioDAO;
import dto.AdministradorDTO;
import dto.EstadoDTO;
import dto.MunicipioDTO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.model.file.UploadedFile;
import utilerias.Conexion;
import utilerias.Sesion;
import utilerias.Utilerias;

/**
 *
 * @author Erick Montes
 */
@Named(value = "adminBean")
@SessionScoped
@ManagedBean

public class AdminBean extends BaseBean implements Serializable {

    private AdministradorDTO dto;
    private AdministradorDAO dao = new AdministradorDAO();
    private List<AdministradorDTO> listaDeAdmin = new ArrayList<>();
    private int idEstado = 1;
    private UploadedFile foto;

    public AdminBean() {
    }

    public AdministradorDTO getDto() {
        return dto;
    }

    public void setDto(AdministradorDTO dto) {
        this.dto = dto;
    }

    public List<AdministradorDTO> getListaDeAdmin() {
        return listaDeAdmin;
    }

    public void setListaDeAdmin(List<AdministradorDTO> listaDeAdmin) {
        this.listaDeAdmin = listaDeAdmin;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    @PostConstruct
    public void init() {
        setDtoS1();
        listaDeAdmin = new ArrayList<>();
        listaDeAdmin = dao.MuestraAdmin();
    }

    public String nuevo() {
        dto = new AdministradorDTO();
        setAccion(ACC_CREAR);
        return "/admin/adminForm?faces-redirect=true";
    }

    public String editar() {
        idEstado = 1;
        setAccion(ACC_ACTUALIZAR);
        return "/admin/adminForm?faces-redirect=true";
    }

    public String perfil() {
        setAccion(ACC_ACTUALIZAR);
        dto = new AdministradorDTO();
        idEstado = 1;
        return "/admin/perfilAdmin?faces-redirect=true";
    }

    public String back() {
        init();
        return "/admin/listaAdmin?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/admin/listaAdmin?faces-redirect=true";
    }

    public String listaUsuarios() {
        return "/admin/listaUser?faces-redirect=true";
    }

    public String crear() {
        Utilerias utilerias;
        try {
            if (foto.getFileName() != null) {
                dto.getAdministrador().setAdminImagen(getBytesFromInputStream(foto.getInputStream()));
            } else {
                System.out.println(foto);
                File img = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/imagen.png"));
                FileInputStream fis = new FileInputStream(img);
                dto.getAdministrador().setAdminImagen(getBytesFromInputStream(fis));
            }
            if (dao.registra(dto)) {
                utilerias = new Utilerias();
                utilerias.enviarEmail(dto.getAdministrador().getAdminCorreo(),
                        "Registro de Cuenta",
                        "El administrador ha sido registrado satisfactoriamente \n"
                        + dto.getAdministrador().getAdminUsuario() + "\n"
                        + dto.getAdministrador().getAdminContrasena());
                info();
                return prepareIndex();
            }else{
                error();
                return prepareIndex();
            }
            
        } catch (Exception e) {
            error("errorCrearAdmin", "Error al crear administrador");
            return "/admin/listaAdmin?faces-redirect=true";
        }
    }

    public String actualizar() {
        try {
            if (foto.getSize() != 0) {
                if(dto != null)
                    dto.getAdministrador().setAdminImagen(getBytesFromInputStream(foto.getInputStream()));
                else
                    getDtoS1().getAdministrador().setAdminImagen(getBytesFromInputStream(foto.getInputStream()));
                
            } 
            System.out.println("Apenas voy we");
            System.out.println(getDtoS1() + " " +dto);
            if(dto != null){
                if (dao.actualiza(dto)){
                    setDtoS1();
                    System.out.println("Aqui 1");
                    return prepareIndex();
                }
            } else {
                if(getDtoS1().getAdministrador().getIdMunicipio() == 0)
                    getDtoS1().getAdministrador().setIdMunicipio(1);
                if(dao.actualiza(getDtoS1()))
                    setDtoS1();
            } 
            return prepareIndex();
        
        } catch (Exception e) {
            error("errorEditarEvento", "Error al actualizar evento");
            return "/admin/listaAdmin?faces-redirect=true";
        }
    }
    public String actualizarP() {
        try {
            getDtoS1().getAdministrador().setAdminImagen(getBytesFromInputStream(foto.getInputStream()));
            System.out.println(getDtoS1());
                if(!dao.actualiza(getDtoS1()))
                    setDtoS1();
            return prepareIndex();       
        } catch (Exception e) {
            error("errorEditarEvento", "Error al actualizar evento");
            return "/admin/listaAdmin?faces-redirect=true";
        }
    }

    public String borrar() {
        try {

            if (dao.elimina(dto)) {
                warn();
                return prepareIndex();
            }else{
                error();
                return prepareIndex();
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("errorBorrarEvento", "Error al eliminar evento");
            return "/admin/listaAdmin?faces-redirect=true";
        }
    }

    public void seleccionarAdministrador(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new AdministradorDTO();
        dto.getAdministrador().setIdAdministrador(Integer.parseInt(claveSel));
        try {
            dto = dao.leerUno(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EstadoDTO> listaEstados() {
        EstadoDAO dao = new EstadoDAO();
        return dao.MuestraEstado();
    }

    public List<MunicipioDTO> listaMunicipios() {
        MunicipioDAO dao = new MunicipioDAO();
        return dao.MuestraMunicipio(idEstado);
    }

    public byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int len = is.read(buffer); len != -1; len = is.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }

//    public StreamedContent getImagen() throws IOException{
//        FacesContext context = FacesContext.getCurrentInstance();
//        if(context.getCurrentPhaseId()==PhaseId.RENDER_RESPONSE){
//            return new DefaultStreamedContent();
//        } else {
//            String idAdmin =  context.getExternalContext().getRequestParameterMap().get("idAdmin");
//            dto.getAdministrador().setIdAdministrador(Integer.parseInt(idAdmin));
//            dto = dao.leerUno(dto);
//            return new DefaultStreamedContent(new ByteArrayInputStream(dto.getAdministrador().getAdminImagen()));
//        }
//    }
//    
    public String mostrarImagen(int idAdmin) {
        return "/imagenServlet?id=" + idAdmin;
    }

    public String getNomUsuario() {
        return Sesion.getDatosSesion();
    }

    public void generateReporte(ActionEvent event) {
        Conexion con = new Conexion();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Admin.jasper"));
        try {
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, con.obtenerConexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream sos = response.getOutputStream();
            sos.write(bytes, 0, bytes.length);
            sos.flush();
            sos.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateReporteI(ActionEvent event) {

        String claveSel = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new AdministradorDTO();
        dto.getAdministrador().setIdAdministrador(Integer.parseInt(claveSel));
        try {
            Conexion con = new Conexion();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/AdminI.jasper"));
            Map parametro = new HashMap();
            parametro.put("idAdmin", dto.getAdministrador().getIdAdministrador());
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, con.obtenerConexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream sos = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            sos.write(bytes, 0, bytes.length);
            sos.flush();
            sos.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean NotRoot(AdministradorDTO dto) {
        if (dto != null) {
            if (dto.getAdministrador().getIdAdministrador() == 1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public String grafica() {
        return "/admin/graficas?faces-redirect=true";
    }

}
