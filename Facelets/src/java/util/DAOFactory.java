/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import conexao.HibernateUtil;
import controllers.UsuarioDAOHibernate;
import interfaces.UsuarioDAO;

/**
 *
 * @author thiag
 */
public class DAOFactory {
    
    public static UsuarioDAO criarUsuarioDAO(){
        UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate(HibernateUtil.getSessionFactory().getCurrentSession());
        return usuarioDAO;
    }
}
