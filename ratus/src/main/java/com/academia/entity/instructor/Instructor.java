package com.academia.entity.instructor;

import com.academia.entity.Funcionario;
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
public class Instructor extends Funcionario {

    private String especialidade;
}

