/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Usuario;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import regras_negocio.UsuarioRN;

/**
 *
 * @author thiag
 */
@Named
@RequestScoped
@Getter
@Setter
public class UsuarioBean {
    private Usuario usuario = new Usuario();
    private String confirmarSenha;
    private List<Usuario> lista;
    private String destinoSalvar;
    private String destinoRetorno;
    
    public String novo(){
        this.destinoSalvar = "usuarioSucesso";
        this.destinoRetorno = "login.jsf";
        this.usuario = new Usuario();
        this.usuario.setAtivo(true);
        return "usuario";
    }
    
    public String salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(!this.usuario.getSenha().equals(this.confirmarSenha)){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não conferem", ""));
            return "usuario";
        }
        Date nascimento = this.usuario.getNascimento();
        this.usuario.setNascimento(new Date(nascimento.getTime() + 24 * 60 * 60 * 1000));
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.salvar(this.usuario);
        return this.destinoSalvar;
    }
    
    public String voltar(){
        return this.destinoRetorno;
    }
    
    public List<Usuario> getLista(){
        if(this.lista == null){
            UsuarioRN usuarioRN = new UsuarioRN();
            this.lista = usuarioRN.listar();
        }
        
        return this.lista;
    }
    
    public String editar() {
        this.confirmarSenha = this.usuario.getSenha();
        return "/Publico/usuario";
    }
    
    public String excluir(){
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.excluir(this.usuario);
        this.lista = null;
        return null;
    }
    
    public String ativar(){
        this.usuario.setAtivo(!this.usuario.isAtivo());
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.salvar(this.usuario);
        return null;
    }
}
