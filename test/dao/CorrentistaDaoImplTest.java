/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import br.com.utilitario.UtilGerador;
import entidade.Cartao;
import entidade.Correntista;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



/**
 *
 * @author David
 */
public class CorrentistaDaoImplTest {
    
    private Correntista correntista;
    private CorrentistaDao correntistaDao;
    private Session sessao;
   
    
    public CorrentistaDaoImplTest() {
        correntistaDao = new CorrentistaDaoImpl();
    }

    //@Test
    public void testSalvarCorrentistaComCartao() {
        System.out.println("Salvar Correntista");
        CartaoDaoImplTest cartaoTeste = new CartaoDaoImplTest();
        
        correntista = new Correntista(
                null,
                UtilGerador.gerarNome(),
                new Date(),
                UtilGerador.gerarNumero(3)+"."+UtilGerador.gerarNumero(3),//+"."+
                //UtilGerador.gerarNumero(3)+"-"+UtilGerador.gerarNumero(2),
                UtilGerador.gerarEmail(),
                BigDecimal.TEN,
                "Blablabla"
        );
        
        Cartao cartao = cartaoTeste.buscaCartaoBd();
        correntista.setCartao(cartao);
        
        sessao = HibernateUtil.abrirConexao();
        correntistaDao.salvarOuAlterar(correntista, sessao);
        sessao.close();
        
        assertNotNull(correntista.getId());
    }
    
    //@Test
    public void testExcluirCorrentista() {
        System.out.println("Excluir Correntista");
        
        buscaCorrentistaBd();
        
        sessao = HibernateUtil.abrirConexao();
        correntistaDao.excluir(correntista, sessao);
        
        Correntista correntistaExcluido = correntistaDao.pesquisarPorId(correntista.getId(), sessao);
        
        sessao.close();
        assertNull(correntistaExcluido);
    }
    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
       
    }
    @Test
    public void testPesquisarCorrentistaPorNome() {
        System.out.println("Correntista pesquisar por Nome");
        buscaCorrentistaBd();
        
        sessao = HibernateUtil.abrirConexao();
        int tamanho = correntista.getNome().length();
        String parteDoNome = correntista.getNome().substring(tamanho-5);
        List<Correntista> correntistas = correntistaDao.pesquisarPorNome(parteDoNome, sessao);
        
        sessao.close();
        assertTrue(!correntistas.isEmpty());
    }
    
    @Test
    public void testAlterarCorrentista() {
        System.out.println("Alterar correntista");
        
        buscaCorrentistaBd();
        
        correntista.setEmail("david@gmail.com");
        
        sessao = HibernateUtil.abrirConexao();
        correntistaDao.salvarOuAlterar(correntista, sessao);
        
        Correntista correntistaNovo = correntistaDao.pesquisarPorId(correntista.getId(), sessao);
        
        sessao.close();
        assertEquals(correntista.getNome(), correntistaNovo.getNome());
    }

    public Correntista buscaCorrentistaBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Correntista");
        List<Correntista> correntistas = consulta.list();
        sessao.close();
        if (correntistas.isEmpty()) {
            testSalvarCorrentistaComCartao();
        } else {
            correntista = correntistas.get(0);
            System.out.println("================================================");
            System.out.println("Resultado da busca:" + correntistas.get(0).getNome());
        
        }
        return correntista;
        
    }
    
}
