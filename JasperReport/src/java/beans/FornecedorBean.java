/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Fornecedor;
import classes.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import lombok.EqualsAndHashCode;
import regras_negocio.FornecedorRN;
import util.RelatorioUtil;

/**
 *
 * @author thiag
 */
@Named
@RequestScoped
@EqualsAndHashCode
public class FornecedorBean {
    private Fornecedor fornecedor;
    private List<Fornecedor> lista;

    public FornecedorBean() {
        this.fornecedor = new Fornecedor();
        this.lista = new ArrayList<>();
    }
    
    
    
    public void setFornecedor(Fornecedor fornecedor){
        this.fornecedor = fornecedor;
    }
    
    public Fornecedor getFornecedor(){
        return this.fornecedor;
    }
    
    public List<Fornecedor> getLista(){
        if(this.lista.isEmpty()){
            FornecedorRN rn = new FornecedorRN();
            this.lista = rn.listar();
        }
        
        return this.lista;
    }
    
    public void setLista(List<Fornecedor> lista){
        this.lista = lista;
    }
    
    public void salvar(){
        try{
            int codigo = this.fornecedor.getCod_forn();
            FornecedorRN rn = new FornecedorRN();
            rn.salvar(this.fornecedor);
            this.fornecedor = new Fornecedor();
            this.lista.clear();
            if(codigo == 0){
                addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Fornecedor salvo com sucesso!");
            }
            else{
                addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Fornecedor editado com sucesso!");
            }
        }catch(Exception e){
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
        }
    }
    
    public void excluir(){
        try{
            FornecedorRN rn = new FornecedorRN();
            rn.excluir(this.fornecedor);
            this.fornecedor = new Fornecedor();
            this.lista.clear();
            addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Fornecedor excluído com sucesso!");
        }catch(Exception e){
            addMensagem(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
        }
    }
    
    public String voltar(){
        return "/Logado/principal.jsf";
    }
    
    public void addMensagem(Severity severidade, String titulo, String aviso){
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(severidade, titulo, aviso);
        contexto.addMessage(null, mensagem);
    }
    
    public void gerarRelatorio(ActionEvent event){
        System.out.println("Gerar relatório");
        RelatorioUtil relatorio = new RelatorioUtil();
        relatorio.getRelatorioFornecedor();
    }
}
