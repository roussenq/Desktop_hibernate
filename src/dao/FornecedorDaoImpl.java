/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class FornecedorDaoImpl extends BaseDaoImpl<Fornecedor, Long> implements FornecedorDao, Serializable {

    @Override
    public Fornecedor pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Fornecedor) sessao.get(Fornecedor.class, id);
    }

    public Fornecedor pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Fornecedor where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return (Fornecedor) consulta.list();
    }

    @Override
    public List<Fornecedor> listarTodos(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Fornecedor");
        List<Fornecedor> fornecedores = consulta.list();
        return fornecedores;
    }

}
