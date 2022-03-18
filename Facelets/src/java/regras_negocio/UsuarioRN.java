/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regras_negocio;

import classes.Usuario;
import interfaces.UsuarioDAO;
import java.util.List;
import util.DAOFactory;

/**
 *
 * @author thiag
 */

public class UsuarioRN {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioRN(){
        this.usuarioDAO = DAOFactory.criarUsuarioDAO();
    }
    
    public Usuario carregar(int codigo){
        return this.usuarioDAO.carregar(codigo);
    }
    
    public Usuario buscarPorLogin(String login){
        return this.usuarioDAO.buscarPorLogin(login);
    }
    
    public void salvar(Usuario usuario){
        if(usuario.getCodigo() == 0){
            this.usuarioDAO.salvar(usuario);
        } else{
            this.usuarioDAO.atualizar(usuario);
        }
    }
    
    public void excluir(Usuario usuario){
        this.usuarioDAO.excluir(usuario);
    }
    
    public List<Usuario> listar(){
        return this.usuarioDAO.listar();
    }
}
