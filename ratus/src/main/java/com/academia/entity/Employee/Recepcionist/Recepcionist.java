package com.academia.entity.Employee.Recepcionist;

import com.academia.entity.Employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recepcionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "funcionario_id")
@EqualsAndHashCode(callSuper = true)
public class Recepcionist extends Employee {}
