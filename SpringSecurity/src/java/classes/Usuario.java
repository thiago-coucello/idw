/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

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
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private int codigo;
    
    private String nome;
    
    private String email;
    
    @NaturalId
    private String login;
    
    private String senha;
    
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    
    private String celular;
    
    private boolean ativo;
    
    @ElementCollection(targetClass = String.class)
    @JoinTable(
            name = "usuario_permissao",
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario", "permissao"})
            },
            joinColumns = { @JoinColumn(name = "usuario") }
    )
    private Set<String> permissao = new HashSet<>();
}
