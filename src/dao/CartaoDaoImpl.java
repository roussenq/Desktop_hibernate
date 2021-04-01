/*
 * 3° passo criar essa classe impl
 * 4° passo criar o teste, com botao direito nesta classe, ferramentas, criar teste
 */
package dao;

import entidade.Cartao;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long>
                                           implements CartaoDao, Serializable {

    @Override
    public Cartao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Cartao)sessao.get(Cartao.class, id);
    }
    
}
