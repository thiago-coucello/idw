/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Fornecedor;
import classes.Produto;
import interfaces.FornecedorDAO;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

/**
 *
 * @author thiag
 */
@AllArgsConstructor
public class FornecedorDAOHibernate implements FornecedorDAO{
    private Session session;
    
    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public List<Fornecedor> listar() {
        return this.session.createCriteria(Fornecedor.class).list();
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        this.session.merge(fornecedor);
    }

    @Override
    public void salvar(Fornecedor fornecedor) {
        this.session.save(fornecedor);
    }

    @Override
    public void excluir(Fornecedor fornecedor) {
        this.session.delete(fornecedor);
    }
}
