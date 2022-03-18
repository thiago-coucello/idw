/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Fornecedor;
import java.util.List;

/**
 *
 * @author thiag
 */
public interface FornecedorDAO {
    List<Fornecedor> listar();
    
    void atualizar(Fornecedor fornecedor);
    
    void salvar(Fornecedor fornecedor);
    
    void excluir(Fornecedor fornecedor);
}
