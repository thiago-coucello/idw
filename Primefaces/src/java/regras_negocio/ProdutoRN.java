/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regras_negocio;

import classes.Produto;
import interfaces.ProdutoDAO;
import java.util.List;
import util.DAOFactory;

/**
 *
 * @author thiag
 */
public class ProdutoRN {
    private ProdutoDAO produtoDAO;
    
    public ProdutoRN(){
        this.produtoDAO = DAOFactory.criarProdutoDAO();
    }
    
    public List<Produto> listar(){
        return this.produtoDAO.listar();
    }
    
    public void salvar(Produto produto){
        if(produto.getCod_produto() == 0){
            this.produtoDAO.salvar(produto);
        }
        else{
            this.produtoDAO.atualizar(produto);
        }
    }
    
    public void excluir(Produto produto){
        this.produtoDAO.excluir(produto);
    }
}
