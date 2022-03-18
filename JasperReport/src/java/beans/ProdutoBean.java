/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.EqualsAndHashCode;
import regras_negocio.ProdutoRN;
import util.RelatorioUtil;

/**
 *
 * @author thiag
 */
@Named
@RequestScoped
@EqualsAndHashCode
public class ProdutoBean {
    private Produto produto;
    private List<Produto> lista;

    public ProdutoBean() {
        this.produto = new Produto();
        this.lista = new ArrayList<>();
    }
    
    
    
    public void setProduto(Produto produto){
        this.produto = produto;
    }
    
    public Produto getProduto(){
        return this.produto;
    }
    
    public List<Produto> getLista(){
        if(this.lista.isEmpty()){
            ProdutoRN rn = new ProdutoRN();
            this.lista = rn.listar();
        }
        
        return this.lista;
    }
    
    public void setLista(List<Produto> lista){
        this.lista = lista;
    }
    
    public void salvar(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        try{
            ProdutoRN rn = new ProdutoRN();
            rn.salvar(this.produto);
            this.produto = new Produto();
            this.lista.clear();
        }catch(Exception e){
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            contexto.addMessage(null, mensagem);
        }
    }
    
    public void excluir(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        try{
            ProdutoRN rn = new ProdutoRN();
            rn.excluir(this.produto);
            this.produto = new Produto();
            this.lista.clear();
        }catch(Exception e){
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            contexto.addMessage(null, mensagem);
        }
    }
    
    public void gerarRelatorio() {
        RelatorioUtil relatorio = new RelatorioUtil();
        relatorio.getRelatorioProduto();
    }
}
