/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.utilitario.UtilGerador;
import entidade.Cartao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class CartaoDaoImplTest {
    
    private Session sessao;
    private CartaoDao cartaoDao;
    private Cartao cartao;
    
    public CartaoDaoImplTest() {
        cartaoDao = new CartaoDaoImpl();
    }

    
    @Test
    public void TestSalvarCartao() {
        System.out.println("salvar cartao");
        cartao = new Cartao(null,
                UtilGerador.gerarNumero(4)+"-"+
                UtilGerador.gerarNumero(4)+"-"+
                UtilGerador.gerarNumero(4)+"-"+
                UtilGerador.gerarNumero(4),   
                "visa",
                "08/25");
       
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();
        
        assertNotNull(cartao.getId());
    }
    
    //@Test
    public void TestAlterarCartao() {
        System.out.println("========== Alterar ==========");
        buscaCartaoBd();
    }
    //@Test
    public void testListarTodosCartoes() {
        System.out.println("listarTodos");
    }

    //@Test
    public void testPesquisarCartaoPorId() {
        System.out.println("pesquisarPorId");
    }

    
    public Cartao buscaCartaoBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Cartao"); //hql
        List<Cartao> cartoes = consulta.list();
        sessao.close();
        cartao = cartoes.get(0);
        System.out.println("================================================");
        System.out.println("Resultado da busca:" + cartoes.get(0).getNumero());
        return cartao;
    }
    
    
}
