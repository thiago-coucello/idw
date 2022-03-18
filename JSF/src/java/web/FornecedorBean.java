/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
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
public class FornecedorBean implements Serializable{
   private String cnpj;
   private String nome;
   private String cidade;
   
   public String novo(){
       return "cadastroFornecedor";
   }
   
   public String salvar() {
       FacesContext context = FacesContext.getCurrentInstance();
       if(this.nome.isEmpty() || this.cnpj.isEmpty() || this.cidade.isEmpty()){
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha todos os campos", ""));
           return "cadastroFornecedor";
       }
       
       return "mostraFornecedor";
   }
}
