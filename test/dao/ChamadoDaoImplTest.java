/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import br.com.utilitario.UtilGerador;
import entidade.Chamado;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class ChamadoDaoImplTest {

    private Chamado chamado;
    private ChamadoDao chamadoDao;
    private Session sessao;

    public ChamadoDaoImplTest() {
        chamadoDao = new ChamadoDaoImpl();
    }

    //@Test
    public void testSalvarChamado() {
        System.out.println("Salvar chamado");

        chamado = new Chamado(
                null,
                new Date(),
                "PC" + UtilGerador.gerarNumero(3),
                "Instalação de KIT transmissão",
                true
        );

        sessao = HibernateUtil.abrirConexao();
        chamadoDao.salvarOuAlterar(chamado, sessao);
        sessao.close();

        assertNotNull(chamado.getId());
    }

    //@Test
    public void testAlterarChamado() {
        System.out.println("Alterar chamado");
        buscarChamadoBd();
        chamado.setAtivo(false);
        sessao=HibernateUtil.abrirConexao();
        chamadoDao.salvarOuAlterar(chamado, sessao);
        Chamado chamadoAlterado = chamadoDao.pesquisarPorId(chamado.getId(), sessao);
        sessao.close();
        assertEquals(chamado.isAtivo(),chamadoAlterado.isAtivo());
    }

    //@Test
    public void testExcluirChamado() {
        System.out.println("Excluir chamado");
        buscarChamadoBd();
        sessao = HibernateUtil.abrirConexao();
        chamadoDao.excluir(chamado, sessao);
        Chamado chamadoExc = chamadoDao.pesquisarPorId(chamado.getId(), sessao);
        sessao.close();
        assertNull(chamadoExc);
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
    }

    public Chamado buscarChamadoBd() {

        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Chamado");
        List<Chamado> chamados = consulta.list();
        sessao.close();

        if (chamados.isEmpty()) {
            testSalvarChamado();
        } else {
            chamado = chamados.get(0);
        }
        return chamado;
    }

    //@Test
    public void testPesquisarPorEquipamento() {
        System.out.println("pesquisar Por Equipamento");
   
        buscarChamadoBd();
        
        sessao=HibernateUtil.abrirConexao();
        
        sessao.close();
    }

    //@Test
    public void testPesquisarChamadoAberto() {
        System.out.println("pesquisarChamadoAberto");
        
        buscarChamadoBd();
        
        sessao=HibernateUtil.abrirConexao();
        
        sessao.close();
        
 
    }

}
