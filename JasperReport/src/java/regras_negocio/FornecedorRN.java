/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regras_negocio;

import classes.Fornecedor;
import classes.Produto;
import interfaces.FornecedorDAO;
import interfaces.ProdutoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import util.DAOFactory;

/**
 *
 * @author thiag
 */
public class FornecedorRN {
    private FornecedorDAO fornecedorDAO;
    
    public FornecedorRN(){
        this.fornecedorDAO = DAOFactory.criarFornecedorDAO();
    }
    
    public List<Fornecedor> listar(){
        return this.fornecedorDAO.listar();
    }
    
    public void salvar(Fornecedor fornecedor){
        if(fornecedor.getCod_forn()== 0){
            String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            fornecedor.setData_cad(data);
            this.fornecedorDAO.salvar(fornecedor);
        }
        else{
            this.fornecedorDAO.atualizar(fornecedor);
        }
    }
    
    public void excluir(Fornecedor fornecedor){
        this.fornecedorDAO.excluir(fornecedor);
    }
}
