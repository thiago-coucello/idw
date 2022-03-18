/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author thiag
 */
public class Conexao {
 
    public static Session criarSessao(){
        Session sessao = null;
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
        }catch(HibernateException e){
            System.out.println("Não foi possível criar a sessão. Erro: " + e);
            sessao = null;
        }
        return sessao;
    }
}
