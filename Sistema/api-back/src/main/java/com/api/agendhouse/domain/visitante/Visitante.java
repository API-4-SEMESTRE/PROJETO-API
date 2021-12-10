package com.api.agendhouse.domain.visitante;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="VISITANTE")
@SequenceGenerator(name="VIS_SQ", sequenceName="VIS_SQ", allocationSize=1)
@Data
public class Visitante {

    @Id
    @Column(name="VIS_COD")
    @GeneratedValue(generator="EVE_SQ", strategy=GenerationType.SEQUENCE)
    private Long viscod;

    @Column(name="VIS_NOME_VISI")
    private String visnome;

    @Column(name="VIS_EMAIL_VISI")
    private String visemail;

    @Column(name="VIS_CPF_VISI")
    private String viscpf;

    @Column(name="VIS_VACINA")
    private String visvacina;
}
