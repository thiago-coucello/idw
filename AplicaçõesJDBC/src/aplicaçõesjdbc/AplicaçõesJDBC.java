/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicaçõesjdbc;

import aplicaçõesjdbc.crudjdbc.ContatoCrud;
import java.sql.Date;

/**
 *
 * @author thiag
 */
public class AplicaçõesJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ContatoCrud crud = new ContatoCrud();
        Contato thiago = new Contato(3, "Thiago Fernandes Coucello da Fonseca", "69992239999", "thiago.coucello@gmail.com", "novo cliente", "03947905297", new Date(System.currentTimeMillis()));
        
        //criarContatosAula(crud);
        //excluirContatos(crud);
        //criarMeuContato(crud, thiago);
        atualizarMeuContato(crud, thiago);
    }
    
    private static void criarContatosAula(ContatoCrud crud){
        Contato beltrano = new Contato();
        beltrano.setCodigo(1);
        beltrano.setNome("Pablo Nunes Vargas");
        beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
        beltrano.setEmail("eng.pnv@gmail.com");
        beltrano.setTelefone("(69)99999-9999");
        beltrano.setObservacao("Novo cliente");
        
        crud.salvar(beltrano);
        
        Contato fulano = new Contato();
        fulano.setCodigo(2);
        fulano.setNome("João da Silva");
        fulano.setDataCadastro(new Date(System.currentTimeMillis()));
        fulano.setEmail("joao@gmail.com");
        fulano.setTelefone("(69) 99999-9999");
        fulano.setObservacao("cliente antigo");
        
        crud.salvar(fulano);
        
        System.out.println("Foram cadastrados: " + crud.listar().size() + " contatos");
    }
    
    private static void excluirContatos(ContatoCrud crud){
        crud.listar().forEach(contato -> {
            crud.excluir(contato);
        });
        System.out.println("Todos os contatos foram excluídos!");
    }
    
    private static void criarMeuContato(ContatoCrud crud, Contato thiago){
        crud.salvar(thiago);
        System.out.println("Foram cadastrados: " + crud.listar().size() + " contatos");
    }
    
    private static void atualizarMeuContato(ContatoCrud crud, Contato thiago){
        thiago.setObservacao("cliente antigo");
        crud.atualizar(thiago);
        System.out.println("Contato atualizado com sucesso!");
    }
}
