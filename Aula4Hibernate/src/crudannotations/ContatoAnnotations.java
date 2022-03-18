/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudannotations;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author thiag
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "contato_hibernate")
public class ContatoAnnotations implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private int codigo;
    
    @Column(name = "nome", length = 50, nullable = true)
    private String nome;
    
    @Column(name = "telefone", length = 50, nullable = true)
    private String telefone;
    
    @Column(name = "email", length = 50, nullable = true)
    private String email;
    
    @Column(name = "dt_cad", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    
    @Column(name = "obs", nullable = true)
    private String observacao;
}
