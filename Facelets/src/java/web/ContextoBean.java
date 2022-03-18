/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import classes.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import regras_negocio.UsuarioRN;

/**
 *
 * @author thiag
 */
@Named
@SessionScoped
public class ContextoBean implements Serializable {
    private Usuario usuarioLogado;
    
    public Usuario getUsuarioLogado(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        ExternalContext contextoExterno = contexto.getExternalContext();
        String login = contextoExterno.getRemoteUser();
        
        if(this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())){
            if(login != null && !login.isEmpty()){
                UsuarioRN rn = new UsuarioRN();
                this.usuarioLogado = rn.buscarPorLogin(login);
            }
        }
        
        return this.usuarioLogado;
    }
}
