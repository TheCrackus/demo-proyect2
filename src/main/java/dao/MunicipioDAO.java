/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AdministradorDTO;
import dto.MunicipioDTO;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilerias.HibernateUtil;

/**
 *
 * @author Erick Montes
 */
public class MunicipioDAO {
    
    public List<MunicipioDTO> MuestraMunicipio(int idEstado){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List<MunicipioDTO> lista = null;
        try{
            transaction.begin();
            Query q = session.createQuery("from Municipio m where m.idEstado=" + idEstado + "order by m.idMunicipio");
            lista = q.list();
            transaction.commit();
        }catch(HibernateException he){
            if(transaction!=null && transaction.isActive()){
                transaction.rollback();
            }
        }
        return lista;
    }
    
//    public static void main(String[] args) {
//        MunicipioDAO dao = new MunicipioDAO();
//        System.out.println(dao.MuestraMunicipio(20));
//    }
}
