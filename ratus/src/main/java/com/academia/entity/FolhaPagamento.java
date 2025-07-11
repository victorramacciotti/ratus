package com.academia.entity;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.academia.entity.Employee.Employee;
import com.academia.enums.MesReferenteAPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "folha_pagamento")
public class FolhaPagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2, name = "salario_pago_mes")
    private BigDecimal salarioPagoMes; //essa coluna é diferente de salario do funcionario, aqui seria o salário que foi pago no mês com, por exemplo, horas extras. 

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "mes_referente")
    @Enumerated(EnumType.STRING)
    private MesReferenteAPagamento mesReferente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Employee funcionario;
}
