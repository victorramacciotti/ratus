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
@PrimaryKeyJoinColumn(name = "funcionario_id")
@EqualsAndHashCode(callSuper = true)
public class Instructor extends Employee {

    private String especialidade;
}

