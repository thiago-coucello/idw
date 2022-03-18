/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "locacao")
public class Locacao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locacao_seq")
    @SequenceGenerator(name = "locacao_seq", sequenceName = "locacao_seq", allocationSize = 2)
    @Column(name = "cod_locacao")
    private int locacao;
    
    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "cod_midia")
    private Midia midia;
    
    @Column(name = "data_emprestimo", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmprestimo;
    
    private Time horaEmprestimo;
    
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    
    @Column(name = "obs")
    private String observacao;
}
