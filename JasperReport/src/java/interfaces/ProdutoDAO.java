/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Produto;
import java.util.List;

/**
 *
 * @author thiag
 */
public interface ProdutoDAO {
    List<Produto> listar();
    
    void atualizar(Produto produto);
    
    void salvar(Produto produto);
    
    void excluir(Produto produto);
}
