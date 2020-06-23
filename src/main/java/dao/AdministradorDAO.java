package dao;

import clases.Administrador;
import dto.AdministradorDTO;
import dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.HibernateUtil;

public class AdministradorDAO {

    public Administrador iniciarSesion(Administrador admin) {
        List lista2 = MuestraAdmin();
        for (int i = 0; i < lista2.size(); i++) {
            AdministradorDTO dtor = (AdministradorDTO) lista2.get(i);
            if (dtor.getAdministrador().getAdminUsuario().equalsIgnoreCase(admin.getAdminUsuario())) {
                if (dtor.getAdministrador().getAdminContrasena().equalsIgnoreCase(admin.getAdminContrasena())) {
                    admin.setIdAdministrador(dtor.getAdministrador().getIdAdministrador());
                    admin.setAdminNombre(dtor.getAdministrador().getAdminNombre());
                    admin.setAdminPaterno(dtor.getAdministrador().getAdminPaterno());
                    admin.setAdminMaterno(dtor.getAdministrador().getAdminMaterno());
                    admin.setAdminCorreo(dtor.getAdministrador().getAdminCorreo());
                    admin.setIdMunicipio(dtor.getAdministrador().getIdMunicipio());
                    admin.setAdminImagen(dtor.getAdministrador().getAdminImagen());
                }
            }
        }
        return admin;
    }

    public boolean validaRegistro(AdministradorDTO dto) {
        UsuarioDAO dao = new UsuarioDAO();
        boolean result = true;

        List lista = dao.TraeUsuariosConfiguracion();
        for (int i = 0; i < lista.size(); i++) {
            UsuarioDTO dtor = (UsuarioDTO) lista.get(i);
            if (dtor.getUsuario().getUsuario().equals(dto.getAdministrador().getAdminUsuario())) {
                result = false;
                System.out.println("No registrado \n Un usr tiene ya estos datos");
            } else if (dtor.getUsuario().getUsuarioCorreo().equals(dto.getAdministrador().getAdminCorreo())) {
                result = false;
                System.out.println("No registrado \n Un usr tiene ya estos datos");
            }
        }

        List lista2 = MuestraAdmin();
        for (int i = 0; i < lista2.size(); i++) {
            AdministradorDTO dtor = (AdministradorDTO) lista2.get(i);
            if (dtor.getAdministrador().getAdminUsuario().equals(dto.getAdministrador().getAdminUsuario())) {
                result = false;
                System.out.println("No registrado \n Un admin tiene ya estos datos");
            } else if (dtor.getAdministrador().getAdminCorreo().equals(dto.getAdministrador().getAdminCorreo())) {
                result = false;
                System.out.println("No registrado \n Un admin tiene ya estos datos");
            }
        }

        return result;
    }

    public boolean registra(AdministradorDTO dto) {
        boolean condicionUsrMail = validaRegistro(dto);;
        if (condicionUsrMail) {
            boolean condicion = true;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                session.save(dto.getAdministrador());
                transaction.commit();
            } catch (HibernateException he) {
                condicion = false;
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
            return condicion;
        } else {
            return false;
        }
    }

    public boolean actualiza(AdministradorDTO dto) {
        boolean condicion = true;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(dto.getAdministrador());
            transaction.commit();
        } catch (HibernateException he) {
            condicion = false;
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return condicion;
    }

    public boolean elimina(AdministradorDTO dto) {
        boolean condicion = true;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.delete(dto.getAdministrador());
            transaction.commit();
        } catch (HibernateException he) {
            condicion = false;
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return condicion;
    }

    public AdministradorDTO leerUno(AdministradorDTO dto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();

            Query query = session.createSQLQuery("call spTraeAdmin(" + dto.getAdministrador().getIdAdministrador() + ")")
                    .addScalar("idAdministrador").addScalar("adminNombre").addScalar("adminPaterno")
                    .addScalar("adminMaterno").addScalar("idMunicipio").addScalar("adminCorreo")
                    .addScalar("adminUsuario").addScalar("adminContrasena").addScalar("adminImagen")
                    .addEntity(Administrador.class);

            List<Object[]> resultados = query.list();
            for (Object[] obj : resultados) {

                AdministradorDTO dtor = new AdministradorDTO();

                dtor.getAdministrador().setIdAdministrador((int) obj[0]);
                dtor.getAdministrador().setAdminNombre((String) obj[1]);
                dtor.getAdministrador().setAdminPaterno((String) obj[2]);
                dtor.getAdministrador().setAdminMaterno((String) obj[3]);
                dtor.getAdministrador().setIdMunicipio((int) obj[4]);
                dtor.getAdministrador().setAdminCorreo((String) obj[5]);
                dtor.getAdministrador().setAdminUsuario((String) obj[6]);
                dtor.getAdministrador().setAdminContrasena((String) obj[7]);
                dtor.getAdministrador().setAdminImagen((byte[]) obj[8]);

                lista.add(dtor);
            }
            dto = (AdministradorDTO) lista.get(0);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return dto;
    }

    public List MuestraAdmin() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();

            Query query = session.createSQLQuery("call spTraeAdmins()")
                    .addScalar("idAdministrador").addScalar("adminNombre").addScalar("adminPaterno")
                    .addScalar("adminMaterno").addScalar("idMunicipio").addScalar("adminCorreo")
                    .addScalar("adminUsuario").addScalar("adminContrasena").addScalar("adminImagen")
                    .addEntity(Administrador.class);

            List<Object[]> resultados = query.list();
            for (Object[] obj : resultados) {

                AdministradorDTO dtor = new AdministradorDTO();

                dtor.getAdministrador().setIdAdministrador((int) obj[0]);
                dtor.getAdministrador().setAdminNombre((String) obj[1]);
                dtor.getAdministrador().setAdminPaterno((String) obj[2]);
                dtor.getAdministrador().setAdminMaterno((String) obj[3]);
                dtor.getAdministrador().setIdMunicipio((int) obj[4]);
                dtor.getAdministrador().setAdminCorreo((String) obj[5]);
                dtor.getAdministrador().setAdminUsuario((String) obj[6]);
                dtor.getAdministrador().setAdminContrasena((String) obj[7]);
                dtor.getAdministrador().setAdminImagen((byte[]) obj[8]);

                lista.add(dtor);
            }

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }
}
