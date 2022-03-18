/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula4hibernate;

import crudannotations.ContatoAnnotations;
import crudannotations.ContatoCRUD;
import java.util.Date;

/**
 *
 * @author thiag
 */
public class Aula4Hibernate {

    public static void main(String[] args){
        ContatoAnnotations contato;
        ContatoCRUD crud = new ContatoCRUD();
        String[] nomes = {"Fulano", "Beltrano", "Cicrano"};
        String[] fones = {"(47) 2222-1111", "(47) 7777-5555", "(47) 9090-2525"};
        String[] emails = {"fulano@teste.com", "beltrano@teste.com", "cicrano@teste.com"};
        String[] observacoes = {"Novo cliente", "Cliente em dia", "Ligar na quinta"};
        
        for(int i = 0; i < nomes.length; i++){
            contato = new ContatoAnnotations();
            contato.setNome(nomes[i]);
            contato.setTelefone(fones[i]);
            contato.setEmail(emails[i]);
            contato.setObservacao(observacoes[i]);
            contato.setDataCadastro(new Date(System.currentTimeMillis()));
            crud.salvar(contato);
        }
        
        System.out.println("Total de registros cadastrados: " + crud.listar().size());
        System.out.println("Todos os contatos cadastrados:");
        crud.listar().forEach(c -> {
            System.out.println(c.getNome());
        });
    }
    
}
