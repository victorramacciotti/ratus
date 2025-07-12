package com.academia.entity.Employee.Administrator;

import com.academia.entity.Employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id_funcionario") // Nome da coluna da chave estrangeira
@EqualsAndHashCode(callSuper = true)
public class Administrator extends Employee {}