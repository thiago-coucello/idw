/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Categoria;
import conexao.Conexao;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thiag
 */
@NoArgsConstructor
public class CategoriaDAO {
    private Session sessao;
    private Transaction transacao;
    
    public void salvar(Categoria c){
        this.sessao = Conexao.criarSessao();
        if(this.sessao != null){
            try{
                this.transacao = this.sessao.beginTransaction();
                this.transacao.begin();
                this.sessao.save(c);
                this.transacao.commit();
            }catch(HibernateException e){
                System.out.println("Não foi possível salvar a categoria. Erro: " + e);
            }finally {
                try{
                    if(this.sessao.isOpen()){
                        this.sessao.close();
                    }
                }catch (HibernateException e){
                    System.out.println("Erro ao fechar a sessao. Erro: " + e);
                }
            }
        }
    }
    
    public void atualizar(Categoria c){
        this.sessao = Conexao.criarSessao();
        if(this.sessao != null){
            try{
                this.transacao = this.sessao.beginTransaction();
                this.transacao.begin();
                this.sessao.update(c);
                this.transacao.commit();
            }catch(HibernateException e){
                System.out.println("Não foi possível atualizar a categoria. Erro: " + e);
            }finally {
                try{
                    if(this.sessao.isOpen()){
                        this.sessao.close();
                    }
                }catch (HibernateException e){
                    System.out.println("Erro ao fechar a sessao. Erro: " + e);
                }
            }
        }
    }
    
    public void deletar(Categoria c){
        this.sessao = Conexao.criarSessao();
        if(this.sessao != null){
            try{
                this.transacao = this.sessao.beginTransaction();
                this.transacao.begin();
                this.sessao.delete(c);
                this.transacao.commit();
            }catch(HibernateException e){
                System.out.println("Não foi possível deletar a categoria. Erro: " + e);
            }finally {
                try{
                    if(this.sessao.isOpen()){
                        this.sessao.close();
                    }
                }catch (HibernateException e){
                    System.out.println("Erro ao fechar a sessao. Erro: " + e);
                }
            }
        }
    }
    
    public List<Categoria> listar(){
        this.sessao = Conexao.criarSessao();
        Criteria criterio;
        List<Categoria> resultados = new ArrayList<>();
        if(this.sessao != null){
            try{
                this.transacao = this.sessao.beginTransaction();
                this.transacao.begin();
                criterio = this.sessao.createCriteria(Categoria.class);
                resultados = criterio.list();
                this.transacao.commit();
            }catch(HibernateException e){
                System.out.println("Não foi possível deletar a categoria. Erro: " + e);
            }finally {
                try{
                    if(this.sessao.isOpen()){
                        this.sessao.close();
                    }
                }catch (HibernateException e){
                    System.out.println("Erro ao fechar a sessao. Erro: " + e);
                }
            }
        }
        
        return resultados;
    }
    
    public Categoria recuperar(int codigo){
        Categoria categoria = null;
        Criteria criterio;
        this.sessao = Conexao.criarSessao();
        if(this.sessao != null){
            try{
                this.transacao = this.sessao.beginTransaction();
                this.transacao.begin();
                criterio = this.sessao.createCriteria(Categoria.class);
                criterio.add(Restrictions.eq("categoria", codigo));
                categoria = (Categoria) criterio.uniqueResult();
                this.transacao.commit();
            }catch(HibernateException e){
                if(this.transacao.isActive()){
                    this.transacao.rollback();
                }
                System.out.println("Erro ao recuperar categoria: " + codigo + " .Erro: " + e);
            } finally {
                try{
                    if(this.sessao.isOpen()){
                        this.sessao.close();
                    }
                }catch (HibernateException e){
                    System.out.println("Erro ao fechar a sessao. Erro: " + e);
                }
            }
        }
        return categoria;
    }
}
