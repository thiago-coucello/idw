/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comerciohibernate;

import dao.*;
import classes.*;
import java.util.HashSet;
import java.util.List;
/**
 *
 * @author thiag
 */
public class ComercioHibernate {

    /**
     * @param args the command line arguments
     */
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static CategoriaDAO categoriaDAO = new CategoriaDAO();
    
    public static void main(String[] args) {
        cadastrarProdutos();
        cadastrarCategorias();
        System.out.println("Cadastros gerados com sucesso!");
    }
    
    private static void cadastrarProdutos(){
        String[] descricao = {"Bicicleta", "Televisão", "DVD"};
        Double[] preco = {356.83, 19.99, 195.60};
        Produto produto;
        
        for(int i = 0; i < descricao.length; i++){
            produto = new Produto();
            produto.setDescricao(descricao[i]);
            produto.setPreco(preco[i]);
            produtoDAO.salvar(produto);
        }
    }
    
    private static void cadastrarCategorias(){
        String[] descricao = {"Utilidades", "Geral"};
        Categoria categoria;
        HashSet<Produto> produtos = new HashSet<>();
        List<Produto> listaProdutos = produtoDAO.listar();
        
        listaProdutos.forEach(p -> {
            produtos.add(p);
        });
        
        for (String d : descricao) {
            categoria = new Categoria();
            categoria.setDescricao(d);
            categoria.setProdutos(produtos);
            categoriaDAO.salvar(categoria);
        }
    }
}
