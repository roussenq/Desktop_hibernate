/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Chamado;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ChamadoDaoImpl extends BaseDaoImpl<Chamado, Long> implements ChamadoDao, Serializable {

    @Override
    public List<Chamado> pesquisarPorEquipamento(String equipamento, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Chamado where equipamento like :equipamento");
        consulta.setParameter("equipamento", "%" + equipamento + "%");
        return consulta.list();
    }

    @Override
    public Chamado pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Chamado) sessao.get(Chamado.class, id);
    }

    @Override
    public List<Chamado> pesquisarChamadoAberto(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Chamado where ativo = true");
        return consulta.list();
    }

}
