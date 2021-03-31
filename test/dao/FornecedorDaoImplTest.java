/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.utilitario.UtilGerador;
import entidade.Fornecedor;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author David
 */
public class FornecedorDaoImplTest {
        
    private Session sessao;
    private FornecedorDao fornecedorDao;
    private Fornecedor fornecedor;
  //  private List<Fornecedor> fornecedores;
    
    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }

  
    @Test
    public void testSalvar() {
        System.out.println("Salvar Fornecedor");
        fornecedor = new Fornecedor(null, UtilGerador.gerarNome(), new Date(),"Blablabla");
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        sessao.close();
        
        assertNotNull(fornecedor.getId());
        
    }
    
    //@Test
    public void testAlterar() {
        System.out.println("\n========== Alterar ==========");
        buscarFornecedorBd();
        System.out.println("\n---------------------------------");
        System.out.println("\nBusca do 1° Fornecedor antes: "
                            + fornecedor.getNome()); //joao
        
        fornecedor.setNome(UtilGerador.gerarNome()); //juliana
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        
        Fornecedor fornecedorAlt = fornecedorDao.pesquisarPorId(fornecedor.getId(), sessao);
        sessao.close();
        System.out.println("\nBusca do 1° Fornecedor depois: "
                            + fornecedorAlt.getNome()); //juliana
        assertEquals(fornecedor.getNome(),fornecedorAlt.getNome());
    }
    
    //@Test
    public void testExcluir() {
        System.out.println("\n========== Excluir ==========");
        buscarFornecedorBd(); //buscar algum id no bd
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.excluir(fornecedor, sessao);
        Fornecedor fornecedorExc = fornecedorDao.pesquisarPorId(fornecedor.getId(), sessao);
        sessao.close();
        assertNull(fornecedorExc);
    }
    
    // @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        
    }    

   
    
    public Fornecedor buscarFornecedorBd(){
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Fornecedor"); //HQL 
        List<Fornecedor> fornecedores = consulta.list();
        sessao.close();
        
        if(fornecedores.isEmpty()){
            testSalvar();
        } else {
            fornecedor = fornecedores.get(0);
        }
       
                      
        return fornecedor;
    }
    
}
