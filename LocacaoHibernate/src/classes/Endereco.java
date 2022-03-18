/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "endereco")
public class Endereco implements Serializable{
    @Id
    @GeneratedValue(generator = "fk_endereco_cod_cliente")
    @GenericGenerator(name = "fk_endereco_cod_cliente", strategy = "foreign", parameters = @Parameter(name = "property", value="cliente"))
    @Column(name = "cod_cliente")
    private int endereco;
    
    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;
    
    private String rua;
    
    private int numero;
    
    private String bairro;
    
    private String cidade;
    
    private String uf;
    
    private String cep;
    
    private String complemento;
}
