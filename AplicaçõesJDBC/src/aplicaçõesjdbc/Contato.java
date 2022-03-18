package aplicaçõesjdbc;


import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiag
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    private int codigo;
    private String nome, telefone, email, observacao, cpf;
    private Date dataCadastro;
}
