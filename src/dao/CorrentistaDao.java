/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Correntista;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public interface CorrentistaDao extends BaseDao<Correntista, Long>{
    
    List<Correntista> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
    
    
}
