/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locacaohibernate;

import classes.*;
import conexao.HibernateUtil;
import dao.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thiag
 */
public class LocacaoHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().openSession();
        cadastrarCategorias();
        cadastrarFilmes();
        cadastrarMidias();
        
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco endereco = new Endereco();
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCelular("(47) 1111-2222");
        cliente.setEmail("solaris@javapro.com");
        cliente.setNome("Fulano Solaris");
        cliente.setTelefone("(47) 3333-7777");
        
        endereco.setBairro("Centro");
        endereco.setCep("89000-000");
        endereco.setCidade("Joinville");
        endereco.setComplemento("Casa");
        endereco.setNumero(1);
        endereco.setRua("Av. Principal");
        endereco.setUf("SC");
        
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        
        clienteDAO.salvar(cliente);
        enderecoDAO.salvar(endereco);
        
        LocacaoDAO locacaoDAO = new LocacaoDAO();
        Locacao locacao = new Locacao();
        
        locacao.setDataEmprestimo(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)); // Dia anterior
        locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
        locacao.setObservacao("Devolução do dia anterior");
        locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));
        
        locacao.setCliente(cliente);
        
        MidiaDAO midiaDAO = new MidiaDAO();
        Midia midia = midiaDAO.recuperar(2);
        locacao.setMidia(midia);
        
        locacaoDAO.salvar(locacao);
        System.out.println("Cadastros gerados com sucesso!");
    }
    
    public static void cadastrarCategorias(){
        String[] categorias = {"Aventura", "Ação", "Comédia" };
        Categoria categoria;
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        for(String c : categorias){
            categoria = new Categoria();
            categoria.setDescricao(c);
            categoriaDAO.salvar(categoria);
        }
    }
    
    public static void cadastrarFilmes(){
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        FilmeDAO filmeDAO = new FilmeDAO();
        Filme filme;
        
        String[] descricoes = {"Senhor dos Anéis", "Transformers", "Ghostbusters"};
        Date[] datas = {new Date(2001-1900, 11, 19), new Date(2007-1900, 6, 20), new Date(1985-1900, 1, 1)};
        
        for (int i = 0; i < descricoes.length; i++){
            filme = new Filme();
            filme.setDescricao(descricoes[i]);
            filme.setCategoria(categoriaDAO.recuperar(i + 2));
            filme.setAno(datas[i]);
            filmeDAO.salvar(filme);
        }
    }
    
    public static void cadastrarMidias(){
        Midia midia;
        MidiaDAO midiaDAO = new MidiaDAO();
        FilmeDAO filmeDAO = new FilmeDAO();
        List<Filme> filmes = filmeDAO.listar();
        
        for(Filme f : filmes){
            midia = new Midia();
            midia.setFilme(f);
            midia.setInutilizada('N');
            midiaDAO.salvar(midia);
        }
    }
}
