/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Usuario;
import interfaces.UsuarioDAO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thiag
 */
@Getter
@Setter
@AllArgsConstructor
public class UsuarioDAOHibernate implements UsuarioDAO {
    private Session sessao;
    
    @Override
    public void salvar(Usuario usuario) {
        this.sessao.saveOrUpdate(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {
        this.sessao.merge(usuario);
    }

    @Override
    public void excluir(Usuario usuario) {
        this.sessao.delete(usuario);
    }

    @Override
    public Usuario carregar(int codigo) {
        return (Usuario) this.sessao.get(Usuario.class, codigo);
    }

    @Override
    public Usuario buscarPorLogin(String login) {
        Criteria criterio = this.sessao.createCriteria(Usuario.class);
        criterio.add(Restrictions.eq("login", login));
        return (Usuario) criterio.uniqueResult();
    }

    @Override
    public List<Usuario> listar() {
        this.sessao.beginTransaction();
        List<Usuario> lista = this.sessao.createCriteria(Usuario.class).list();
        return lista;
    }
    
}
