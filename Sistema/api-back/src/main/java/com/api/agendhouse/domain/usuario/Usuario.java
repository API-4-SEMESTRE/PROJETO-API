package com.api.agendhouse.domain.usuario;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="USUARIO")
@SequenceGenerator(name = "USU_SQ", sequenceName = "USU_SQ", allocationSize = 1)
@Data
public class Usuario {

    @Id
    @Column(name="USU_COD")
    @GeneratedValue(generator = "USU_SQ", strategy = GenerationType.SEQUENCE)
    private Long cod;

    @Column(name="USU_NOME")
    private String nome;

    @Column(name="USU_TIPO")
    @Enumerated(EnumType.STRING)
    private UsuarioTipo tipo;

    @Column(name="USU_EMAIL")
    private String email;

    @Column(name="USU_DATE_CREATE")
    private LocalDateTime date_create;

    @Column(name="USU_ACTIVE")
    private boolean active;

    @Column(name="USU_SENHA")
    private String senha;

}
