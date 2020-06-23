
package dao;

import clases.Objetos;
import clases.Partida;
import clases.Usuario;
import clases.puntajes;
import clases.puntoControl;
import dto.ObjetosDTO;
import dto.PartidaDTO;
import dto.UsuarioDTO;
import dto.puntoControlDTO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.HibernateUtil;

public class PartidaDAO {
    
    public UsuarioDTO TraePartidasUsuario(UsuarioDTO dto){ //trae a los usuarios y sus configuraciones
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            Query query = session.createSQLQuery("call spTraePartidas("+ dto.getUsuario().getIdUsuario() +")")
                    .addScalar("idPartida").addScalar("vida").addScalar("eliminados")
                    .addScalar("puntaje").addScalar("tiempoSegundos").addScalar("tiempoMinutos")
                    .addScalar("tiempoHoras").addScalar("idObjetos").addScalar("armaPrimaria")
                    .addScalar("armaSecundaria").addScalar("tipoMunicion1").addScalar("municiona1")
                    .addScalar("municion1").addScalar("tipoMunicion2").addScalar("municiona2")
                    .addScalar("municion2").addScalar("comida").addScalar("idPuntoControl")
                    .addScalar("x").addScalar("y")
                    .addEntity(Partida.class).addEntity(Objetos.class)
                    .addEntity(puntoControl.class);
            
            List<Object[]> resultados = query.list();
            for(Object[] obj: resultados){
                
                PartidaDTO dtor = new PartidaDTO();
                ObjetosDTO dto2r = new ObjetosDTO();
                puntoControlDTO dto3r = new puntoControlDTO();
                
                dtor.getPartida().setIdPartida((int)obj[0]);
                dtor.getPartida().setVida((int)obj[1]);
                dtor.getPartida().setEliminados((int)obj[2]);
                dtor.getPartida().setPuntaje((int)obj[3]);
                dtor.getPartida().setTiempoSegundos((int)obj[4]);
                dtor.getPartida().setTiempoMinutos((int)obj[5]);
                dtor.getPartida().setTiempoHoras((int)obj[6]);
                
                dto2r.getObjetos().setIdObjetos((int)obj[7]);
                dto2r.getObjetos().setArmaPrimaria((String)obj[8]);
                dto2r.getObjetos().setArmaSecundaria((String)obj[9]);
                dto2r.getObjetos().setTipoMunicion1((String)obj[10]);
                dto2r.getObjetos().setMuniciona1((int)obj[11]);
                dto2r.getObjetos().setMunicion1((int)obj[12]);
                dto2r.getObjetos().setTipoMunicion2((String)obj[13]);
                dto2r.getObjetos().setMuniciona2((int)obj[14]);
                dto2r.getObjetos().setMunicion2((int)obj[15]);
                dto2r.getObjetos().setComida((int)obj[16]);
                
                dto3r.getPuntoControl().setIdPuntoControl((int)obj[17]);
                dto3r.getPuntoControl().setX((int)obj[18]);
                dto3r.getPuntoControl().setY((int)obj[19]);
                
                dtor.setObjetos(dto2r.getObjetos());
                dtor.setPuntoControl(dto3r.getPuntoControl());
               
                dto.getPartidas().add(dtor);
            }
            
            transaction.commit();
        }catch(HibernateException e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
        
        return dto;
    }
    
    public List puntajes(){// devuelve una lista con los puntajes mas altos de todos los jugadores
                           // sin comprometer los datos personales
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List listaPuntajes = new ArrayList();
        try{
            transaction.begin();
            
            Query query = session.createSQLQuery("call spTraePuntajes()")
                    .addScalar("Usuario").addScalar("UsuarioCorreo").addScalar("eliminados")
                    .addScalar("puntaje").addScalar("tiempoSegundos").addScalar("tiempoMinutos")
                    .addScalar("tiempoHoras")
                    .addEntity(Partida.class).addEntity(Usuario.class);
            
            List<Object[]> resultados = query.list();
            for(Object[] obj: resultados){
                puntajes p = new puntajes();
                p.setNombre((String)obj[0]);
                p.setCorreo((String)obj[1]);
                p.setEliminados((int)obj[2]);
                p.setPuntaje((int)obj[3]);
                p.setSegundos((int)obj[4]);
                p.setMinutos((int)obj[5]);
                p.setHoras((int)obj[6]);
                
                listaPuntajes.add(p);
            }
            
            transaction.commit();
        }catch(HibernateException e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
        
        return listaPuntajes;
    }

}
