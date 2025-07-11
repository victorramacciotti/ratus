package com.academia.entity.Employee.Administrator;

import com.academia.entity.Employee.Employee;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "funcionario_id")
@EqualsAndHashCode(callSuper = true)
public class Administrator extends Employee {}

