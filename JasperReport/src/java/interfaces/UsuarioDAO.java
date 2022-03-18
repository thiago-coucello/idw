/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Usuario;
import java.util.List;

/**
 *
 * @author thiag
 */
public interface UsuarioDAO {
    public void salvar(Usuario usuario);
    
    public void atualizar(Usuario usuario);
    
    public void excluir(Usuario usuario);
    
    public Usuario carregar(int codigo);
    
    public Usuario buscarPorLogin(String login);
    
    public List<Usuario> listar();
}
