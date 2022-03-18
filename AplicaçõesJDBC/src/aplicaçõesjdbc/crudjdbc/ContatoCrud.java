/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicaçõesjdbc.crudjdbc;

import aplicaçõesjdbc.Contato;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiag
 */
public class ContatoCrud {
    public void salvar(Contato contato){
        Connection conexao = this.geraConexao();
        
        if(conexao == null){
            System.out.println("Erro de conexao");
            return;
        }
        
        PreparedStatement insereSt = null;
            
        try {
            String sql = "insert into contato(nome, telefone, email, dt_cad, obs, cpf) values (?, ?, ?, ?, ?, ?);";
            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setString(1, contato.getNome());
            insereSt.setString(2, contato.getTelefone());
            insereSt.setString(3, contato.getEmail());
            insereSt.setDate(4, contato.getDataCadastro());
            insereSt.setString(5, contato.getObservacao());
            insereSt.setString(6, contato.getCpf());
            insereSt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao incluir o contato. Erro: " + ex.getMessage());
        }
        finally {
            try {
                insereSt.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexao. Erro: " + ex.getMessage());
            }
        }
    }
    
    public void atualizar(Contato contato){
        Connection conexao = this.geraConexao();
        PreparedStatement atualizaSt = null;
        
        if(conexao == null){
            System.out.println("Erro de conexao!");
            return;
        }
        
        String sql = "update contato set nome=?, telefone=?, email=?, obs=?, cpf=? where codigo=?;";
        
        try {
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, contato.getNome());
            atualizaSt.setString(2, contato.getTelefone());
            atualizaSt.setString(3, contato.getEmail());
            atualizaSt.setString(4, contato.getObservacao());
            atualizaSt.setString(5, contato.getCpf());
            atualizaSt.setInt(6, contato.getCodigo());
            atualizaSt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro de SQL. Erro: " + ex.getMessage());
        }
        finally {
            try {
                atualizaSt.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexao. Erro: " + ex.getMessage() );
            }
        }
    } 

    public void excluir(Contato contato){
        Connection conexao = this.geraConexao();
        PreparedStatement excluiSt = null;
        
        if(conexao == null){
            System.out.println("Erro de conexao!");
            return;
        }
        
        String sql = "delete from contato where codigo=?;";
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.setInt(1, contato.getCodigo());
            excluiSt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro de SQL. Erro: " + ex.getMessage());
        }
        finally {
            try {
                excluiSt.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexao. Erro: " + ex.getMessage() );
            }
        }
    }
    
    public List<Contato> listar(){
        Connection conexao = this.geraConexao();
        List<Contato> listaContatos = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        Contato contato;
        
        String sql = "SELECT * FROM contato;";
        
        if(conexao == null){
            System.out.println("Erro de conexao!");
            return listaContatos;
        }
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                contato = new Contato();
                contato.setCodigo(resultado.getInt("codigo"));
                contato.setNome(resultado.getString("nome"));
                contato.setEmail(resultado.getString("email"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setObservacao(resultado.getString("obs"));
                contato.setDataCadastro(resultado.getDate("dt_cad"));
                listaContatos.add(contato);
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL. Erro: " + ex.getMessage());
        }
        finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexao. Erro: " + ex.getMessage() );
            }
        }
        
        return listaContatos;
    }
    
    public Contato listar(int codigo){
        Connection conexao = this.geraConexao();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Contato contato = null;
        
        String sql = "SELECT * FROM contato where codigo = ?;";
        
        if(conexao == null){
            System.out.println("Erro de conexao!");
            return contato;
        }
        
        try {
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, codigo);
            resultado = consulta.executeQuery();
            
            if(resultado.next()){
                contato = new Contato();
                contato.setCodigo(resultado.getInt("codigo"));
                contato.setNome(resultado.getString("nome"));
                contato.setEmail(resultado.getString("email"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setObservacao(resultado.getString("obs"));
                contato.setDataCadastro(resultado.getDate("dt_cad"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro de SQL. Erro: " + ex.getMessage());
        }
        finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexao. Erro: " + ex.getMessage() );
            }
        }
        
        return contato;
    }
    
    public Contato recuperar(int codigo){
        Connection conexao = this.geraConexao();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Contato contato = null;
        String sql = "SELECT * FROM contato where codigo = ?";
        
        try{
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, codigo);
            resultado = consulta.executeQuery();
            
            if(resultado.next()){
                contato = new Contato();
                contato.setCodigo(resultado.getInt("codigo"));
                contato.setNome(resultado.getString("nome"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setEmail(resultado.getString("email"));
                contato.setCpf(resultado.getString("cpf"));
                contato.setDataCadastro(resultado.getDate("dt_cad"));
                contato.setObservacao(resultado.getString("obs"));
            }
        }catch(SQLException e){
            System.out.println("Erro ao recuperar contato: " + codigo + ". Erro: " + e);
        } finally {
            try{
                if(consulta != null) consulta.close();
                if(resultado != null) resultado.close();
                conexao.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexões. Erro: " + e);
            }
        }
        return contato;
    }
    
    public Connection geraConexao(){
        Connection conexao = null;
        
        try{
            String url = "jdbc:postgresql://localhost/testejdbc";
            String usuario = "postgres";
            String senha = "1234";
            
            conexao = DriverManager.getConnection(url, usuario, senha);
        }catch(SQLException e){
            System.out.println("Ocorreu um erro de SQL. Erro: " + e.getMessage());
        }
        
        return conexao;
    }
}
