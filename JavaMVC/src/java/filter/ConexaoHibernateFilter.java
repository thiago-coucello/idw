/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import conexao.HibernateUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 *
 * @author thiag
 */
public class ConexaoHibernateFilter implements Filter{
    private SessionFactory sf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            this.sf.getCurrentSession().beginTransaction();
            chain.doFilter(request, response);
            this.sf.getCurrentSession().getTransaction().commit();
            this.sf.getCurrentSession().close();
        }catch(HibernateException e1){
            try{
                if(this.sf.getCurrentSession().getTransaction().isActive()){
                    this.sf.getCurrentSession().getTransaction().rollback();
                }
            }catch(HibernateException e2){
                System.out.println("Erro ao desfazer alteração. Erro: " + e2);
            }
            
            System.out.println("Erro ao realizar filtro. Erro: " + e1);
            throw new ServletException(e1);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
