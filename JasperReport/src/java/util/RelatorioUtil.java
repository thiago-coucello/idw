/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author thiag
 */
public class RelatorioUtil {
    private final HttpServletResponse resposta;
    private final FacesContext contexto;
    private ByteArrayOutputStream baos;
    private InputStream is;
    private Connection conn;
    
    public RelatorioUtil(){
        this.contexto = FacesContext.getCurrentInstance();
        this.resposta = (HttpServletResponse) this.contexto.getExternalContext().getResponse();
    }
    
    public void getRelatorioProduto() {
        ClassLoader loader = this.getClass().getClassLoader();
        is = loader.getResourceAsStream("relatorio/produtosIDW.jasper");
        Map<String, Object> params = new HashMap<>();
        baos = new ByteArrayOutputStream();
        try{
            JasperReport report = (JasperReport) JRLoader.loadObject(is);
            JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);
            this.resposta.reset();
            this.resposta.setContentType("application/pdf");
            this.resposta.setContentLength(baos.size());
            this.resposta.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
            this.resposta.getOutputStream().write(baos.toByteArray());
            this.resposta.getOutputStream().flush();
            this.resposta.getOutputStream().close();
            contexto.responseComplete();
            fecharConexao();
        }catch(JRException | IOException | ClassNotFoundException | SQLException e){
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void getRelatorioFornecedor() {
        ClassLoader loader = this.getClass().getClassLoader();
        is = loader.getResourceAsStream("relatorio/fornecedoresIDW.jasper");
        Map<String, Object> params = new HashMap<>();
        baos = new ByteArrayOutputStream();
        try{
            JasperReport report = (JasperReport) JRLoader.loadObject(is);
            JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);
            this.resposta.reset();
            this.resposta.setContentType("application/pdf");
            this.resposta.setContentLength(baos.size());
            this.resposta.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
            this.resposta.getOutputStream().write(baos.toByteArray());
            this.resposta.getOutputStream().flush();
            this.resposta.getOutputStream().close();
            contexto.responseComplete();
            fecharConexao();
        }catch(JRException | IOException | ClassNotFoundException | SQLException e){
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void fecharConexao(){
        try{
            this.conn.close();
        } catch(SQLException e){
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Connection getConexao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection("jdbc:postgresql://localhost/springsecurity", "postgres", "1234");
        return this.conn;
    }
}
