/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Cartao;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long> implements CartaoDao, Serializable {

    @Override
    public Cartao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Cartao)sessao.get(Cartao.class, id);
    }
    
}
