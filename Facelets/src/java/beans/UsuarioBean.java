/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.DigestUtils;
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
    private String senhaMD5;
    private String permissao;
    
    
    public String novo(){
        this.destinoSalvar = "usuarioSucesso";
        this.destinoRetorno = "login.jsf";
        this.usuario = new Usuario();
        this.usuario.setAtivo(true);
        return "usuario";
    }
    
    public String salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        String senha = this.usuario.getSenha();
        if(senha != null && !senha.isEmpty()){
            if(!senha.equals(this.confirmarSenha)){
                FacesMessage message = new FacesMessage("As senhas não conferem");
                context.addMessage(null, message);
                return null;
            }
            String senhaHash = DigestUtils.md5DigestAsHex(senha.getBytes());
            this.usuario.setSenha(senhaHash);
        } else {
            String senhaAleatoria = DigestUtils.md5DigestAsHex(senha.getBytes());
            this.usuario.setSenha(senhaAleatoria);
        }
        
        this.usuario.getPermissao().add(this.permissao);
        
        UsuarioRN rn = new UsuarioRN();
        rn.salvar(this.usuario);
        
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
        this.senhaMD5 = this.usuario.getSenha();
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
    
    public String atribuiPermissao(Usuario usuario, String permissao){
        this.usuario = usuario;
        Set<String> permissoes = this.usuario.getPermissao();
        
        if(permissoes.contains(permissao)){
            permissoes.remove(permissao);
        }else{
            permissoes.add(permissao);
        }
        
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.salvar(this.usuario);
        return null;
    }
}
