/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EstadoDTO;
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
public class EstadoDAO {
    public List<EstadoDTO> MuestraEstado(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List<EstadoDTO> lista = null;
        try{
            transaction.begin();
            Query q = session.createQuery("from Estado e order by e.idEstado");
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
//        EstadoDAO dao = new EstadoDAO();
//        System.out.println(dao.MuestraEstado());
//    }
}
