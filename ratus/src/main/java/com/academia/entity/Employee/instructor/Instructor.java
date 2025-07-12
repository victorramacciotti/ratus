package com.academia.entity.Employee.instructor;

import com.academia.entity.Employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instrutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_funcionario") // Nome da coluna da chave estrangeira
@EqualsAndHashCode(callSuper = true)
public class Instructor extends Employee {

    @Column(name = "especialidade_instrutor") // Nome da coluna
    private String especialidade;
}