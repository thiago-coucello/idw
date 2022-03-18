/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.text.DateFormat;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author thiag
 */
@Getter
@Setter
@Named
@SessionScoped
public class UsuarioBean implements Serializable {
    private String  nome;
    private String  email;
    private String  senha;
    private String  confirmaSenha;
    private String dataNascimento;
    
    public String novo(){
        return "cadastroUsuario";
    }
    
    public String salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(!this.senha.equalsIgnoreCase(this.confirmaSenha)){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem", ""));
            return "cadastroUsuario";
        }
        return "mostraUsuario";
    }
}
