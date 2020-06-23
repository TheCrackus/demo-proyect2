
package dao;

import dto.ConfiguracionDTO;
import dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilerias.HibernateUtil;

public class ConfiguracionDAO {
    
    
    public Boolean ModificaConfiguracion(ConfiguracionDTO dto){
        boolean result = true;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            session.update(dto.getConfiguracion());
            transaction.commit();
        }catch(HibernateException e){
            result = false;
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
        return result;
    }
    
    public ConfiguracionDTO TraeConfiguracion(ConfiguracionDTO dto){ //trae la configuracion de un usuario
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List listaConfiguracion = new ArrayList();
        
        try{
            transaction.begin();
            
            Query query = session.createSQLQuery("call spTraeConfiguracion("+dto.getConfiguracion().getIdConfiguracion()+")")
                    .addScalar("idConfiguracion").addScalar("volumen").addScalar("resolucion")
                    .addScalar("idUsuarioC");
            
            List<Object[]> resultados = query.list();
            for(Object[] obj: resultados){
                
                ConfiguracionDTO dtor = new ConfiguracionDTO();
                
                dtor.getConfiguracion().setIdConfiguracion((int)obj[0]);
                dtor.getConfiguracion().setVolumen((float)obj[1]);
                dtor.getConfiguracion().setResolucion((String)obj[2]);
                dtor.getConfiguracion().setIdUsuarioC((int)obj[3]);
               
                listaConfiguracion.add(dtor);
            }
            
            dto = (ConfiguracionDTO)listaConfiguracion.get(0);
            
            transaction.commit();
        }catch(HibernateException e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
        
        return dto;
    }
    
}
