/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudannotations;

import aula4hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thiag
 */
@NoArgsConstructor
public class ContatoCRUD {
    public Session createSession(){
        Session sessao = null;
        
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
        }catch(HibernateException e){
            System.out.println("Não foi possível criar a sessão. Erro: " + e);
        }
        return sessao;
    }
    
    public void salvar(ContatoAnnotations contato){
        Session sessao = createSession();
        Transaction transacao;
        if(sessao != null){
            try{
                transacao = sessao.beginTransaction();
                transacao.begin();
                sessao.save(contato);
                transacao.commit();
            }catch(HibernateException e){
                System.out.println("Erro ao salvar contato. Erro: " + e);
            } finally {
                try{
                    sessao.close();
                }catch(HibernateException e){
                    System.out.println("Não foi possível fechar a sessão. Erro: " + e);
                }
            }
        }
    }
    
    public void atualizar(ContatoAnnotations contato){
        Session sessao = createSession();
        Transaction transacao;
        if(sessao != null){
            try{
                transacao = sessao.beginTransaction();
                transacao.begin();
                sessao.save(contato);
                transacao.commit();
            }catch(HibernateException e){
                System.out.println("Erro ao atualizar contato. Erro: " + e);
            } finally {
                try{
                    sessao.close();
                }catch(HibernateException e){
                    System.out.println("Não foi possível fechar a sessão. Erro: " + e);
                }
            }
        }
    }
    
    public ContatoAnnotations recuperar(int codigo){
        Session sessao = createSession();
        ContatoAnnotations contatoRecuperado = null;
        Query consulta;
        Transaction transacao;
        
        if(sessao != null){
            try{
                transacao = sessao.beginTransaction();
                transacao.begin();
                consulta = sessao.createQuery("from contato_hibernate where codigo = :parametro");
                consulta.setInteger("parametro", codigo);
                contatoRecuperado = (ContatoAnnotations) consulta.uniqueResult();
                transacao.commit();
            }catch(HibernateException e){
                System.out.println("Erro ao recuperar o contato: " + codigo + " . Erro: " + e);
            } finally {
                try{
                    sessao.close();
                }catch(HibernateException e){
                    System.out.println("Não foi possível fechar a sessão. Erro: " + e);
                }
            }
        }
        return contatoRecuperado;
    }
    
    public void excluir(ContatoAnnotations contato){
        Session sessao = createSession();
        Transaction transacao;
        
        if(sessao != null){
            try{
                transacao = sessao.beginTransaction();
                transacao.begin();
                sessao.delete(contato);
                transacao.commit();
            }catch(HibernateException e){
                System.out.println("Erro ao atualizar contato. Erro: " + e);
            } finally {
                try{
                    sessao.close();
                }catch(HibernateException e){
                    System.out.println("Não foi possível fechar a sessão. Erro: " + e);
                }
            }
        }
    }
    
    public List<ContatoAnnotations> listar(){
        Session sessao = createSession();
        List<ContatoAnnotations> resultado = new ArrayList<>();
        Transaction transacao;
        Criteria criterio;
        
        if(sessao != null){
            try{
                transacao = sessao.beginTransaction();
                transacao.begin();
                criterio = sessao.createCriteria(ContatoAnnotations.class);
                criterio.add(Restrictions.eq("codigo", 1));
                resultado = criterio.list();
                transacao.commit();
            }catch(HibernateException e){
                System.out.println("Erro ao atualizar contato. Erro: " + e);
            } finally {
                try{
                    sessao.close();
                }catch(HibernateException e){
                    System.out.println("Não foi possível fechar a sessão. Erro: " + e);
                }
            }
        }
        return resultado;
    }
}
