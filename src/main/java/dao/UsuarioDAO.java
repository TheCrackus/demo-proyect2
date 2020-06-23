package dao;

import clases.Configuracion;
import clases.Usuario;
import dto.AdministradorDTO;
import dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.HibernateUtil;

public class UsuarioDAO {

    public UsuarioDTO TraeUsuario(UsuarioDTO dto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            dto.setUsuario(session.get(dto.getUsuario().getClass(), dto.getUsuario().getIdUsuario()));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return dto;
    }

    public Usuario iniciarSesion(Usuario user) {

        List lista = TraeUsuariosConfiguracion();
        for (int i = 0; i < lista.size(); i++) {
            UsuarioDTO dtor = (UsuarioDTO) lista.get(i);
            if (dtor.getUsuario().getUsuario().equalsIgnoreCase(user.getUsuario())) {
                if (dtor.getUsuario().getContrasena().equalsIgnoreCase(user.getContrasena())) {
                    user.setIdUsuario(dtor.getUsuario().getIdUsuario());
                    user.setUsuarioNombre(dtor.getUsuario().getUsuarioNombre());
                    user.setUsuarioPaterno(dtor.getUsuario().getUsuarioPaterno());
                    user.setUsuarioMaterno(dtor.getUsuario().getUsuarioMaterno());
                    user.setUsuarioCorreo(dtor.getUsuario().getUsuarioCorreo());
                    user.setUsuarioImagen(dtor.getUsuario().getUsuarioImagen());
                }
            }
        }

        return user;
    }

    public List TraeUsaurios() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List<UsuarioDTO> lista = null;
        try {
            transaction.begin();
            Query q = session.createQuery("from Usuario");
            lista = q.list();
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }

    public boolean validaRegistro(UsuarioDTO dto) {
        AdministradorDAO dao = new AdministradorDAO();
        boolean result = true;
        List lista = TraeUsuariosConfiguracion();
        for (int i = 0; i < lista.size(); i++) {
            UsuarioDTO dtor = (UsuarioDTO) lista.get(i);
            if (dtor.getUsuario().getUsuario().equals(dto.getUsuario().getUsuario())) {
                result = false;
                System.out.println("No registrado \n Un usr tiene ya estos datos");

            } else if (dtor.getUsuario().getUsuarioCorreo().equals(dto.getUsuario().getUsuarioCorreo())) {
                result = false;
                System.out.println("No registrado \n Un usr tiene ya estos datos");
            }
        }

        List lista2 = dao.MuestraAdmin();
        for (int i = 0; i < lista2.size(); i++) {
            AdministradorDTO dtor = (AdministradorDTO) lista2.get(i);
            if (dtor.getAdministrador().getAdminUsuario().equalsIgnoreCase(dto.getUsuario().getUsuario())) {
                result = false;
                System.out.println("No registrado \n Un admin tiene ya estos datos");
            } else if (dtor.getAdministrador().getAdminCorreo().equals(dto.getUsuario().getUsuarioCorreo())) {
                result = false;
                System.out.println("No registrado \n Un admin tiene ya estos datos");
            }
        }

        return result;
    }

    public boolean RegistraUsuario(UsuarioDTO dto) {//este registra un usuario y una configuracion por defecto para su juego e inicio de session 
        boolean condicionUsrMail = validaRegistro(dto);;

        if (condicionUsrMail) {
            boolean condicion = true;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();

                session.save(dto.getUsuario());
                Query query = session.createSQLQuery("call spCreaUsuarioConfig(" + dto.getUsuario().getIdUsuario() + ")");
                query.executeUpdate();

                transaction.commit();
            } catch (HibernateException e) {
                condicion = false;
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
            return condicion;
        } else {
            return false;//ya existen usr con ese correo y contra
        }
    }

    public boolean ModificaUsuario(UsuarioDTO dto) {
        boolean condicion = true;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(dto.getUsuario());
            transaction.commit();
        } catch (HibernateException e) {
            condicion = false;
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return condicion;
    }

    public boolean EliminaUsuario(UsuarioDTO dto) {
        boolean condicion = true;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query query = session.createSQLQuery("call spBorraUsuario(" + dto.getUsuario().getIdUsuario() + ")");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            condicion = false;
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return condicion;
    }

    public UsuarioDTO TraeUsuarioConfiguracion(UsuarioDTO dto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List listaUsuariosConfiguracion = new ArrayList();

        try {
            transaction.begin();

            Query query = session.createSQLQuery("call spTraeUsuario(" + dto.getUsuario().getIdUsuario() + ")")
                    .addScalar("idUsuario").addScalar("UsuarioNombre").addScalar("UsuarioPaterno")
                    .addScalar("UsuarioMaterno").addScalar("UsuarioCorreo").addScalar("Usuario")
                    .addScalar("Contrasena").addScalar("UsuarioImagen").addScalar("idConfiguracion")
                    .addScalar("volumen").addScalar("resolucion").addScalar("idUsuarioC").addEntity(Configuracion.class).addEntity(Usuario.class);
            List<Object[]> resultados = query.list();
            for (Object[] obj : resultados) {

                UsuarioDTO dtor = new UsuarioDTO();

                dtor.getUsuario().setIdUsuario((int) obj[0]);
                dtor.getUsuario().setUsuarioNombre((String) obj[1]);
                dtor.getUsuario().setUsuarioPaterno((String) obj[2]);
                dtor.getUsuario().setUsuarioMaterno((String) obj[3]);
                dtor.getUsuario().setUsuarioCorreo((String) obj[4]);
                dtor.getUsuario().setUsuario((String) obj[5]);
                dtor.getUsuario().setContrasena((String) obj[6]);
                dtor.getUsuario().setUsuarioImagen((byte[]) obj[7]);

                dtor.getConfiguracion().setIdConfiguracion((int) obj[8]);
                dtor.getConfiguracion().setVolumen((float) obj[9]);
                dtor.getConfiguracion().setResolucion((String) obj[10]);
                dtor.getConfiguracion().setIdUsuarioC((int) obj[11]);

                listaUsuariosConfiguracion.add(dtor);
            }

            dto = (UsuarioDTO) listaUsuariosConfiguracion.get(0);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return dto;
    }

    public List TraeUsuariosConfiguracion() { //trae a los usuarios y sus configuraciones
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List listaUsuariosConfiguracion = new ArrayList();

        try {
            transaction.begin();

            Query query = session.createSQLQuery("call spTraeUsuarios()")
                    .addScalar("idUsuario").addScalar("UsuarioNombre").addScalar("UsuarioPaterno")
                    .addScalar("UsuarioMaterno").addScalar("UsuarioCorreo").addScalar("Usuario")
                    .addScalar("Contrasena").addScalar("UsuarioImagen").addScalar("idConfiguracion")
                    .addScalar("volumen").addScalar("resolucion").addScalar("idUsuarioC").addEntity(Configuracion.class).addEntity(Usuario.class);
            List<Object[]> resultados = query.list();
            for (Object[] obj : resultados) {

                UsuarioDTO dtor = new UsuarioDTO();

                dtor.getUsuario().setIdUsuario((int) obj[0]);
                dtor.getUsuario().setUsuarioNombre((String) obj[1]);
                dtor.getUsuario().setUsuarioPaterno((String) obj[2]);
                dtor.getUsuario().setUsuarioMaterno((String) obj[3]);
                dtor.getUsuario().setUsuarioCorreo((String) obj[4]);
                dtor.getUsuario().setUsuario((String) obj[5]);
                dtor.getUsuario().setContrasena((String) obj[6]);
                dtor.getUsuario().setUsuarioImagen((byte[]) obj[7]);

                dtor.getConfiguracion().setIdConfiguracion((int) obj[8]);
                dtor.getConfiguracion().setVolumen((float) obj[9]);
                dtor.getConfiguracion().setResolucion((String) obj[10]);
                dtor.getConfiguracion().setIdUsuarioC((int) obj[11]);

                listaUsuariosConfiguracion.add(dtor);
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return listaUsuariosConfiguracion;
    }

}
