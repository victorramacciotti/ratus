package com.academia.entity.Employee.Recepcionist;

import com.academia.entity.Employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "recepcionista")
@Data
@EqualsAndHashCode(callSuper = true) // Importante para herança com Lombok
public class Recepcionist extends Employee { // Nome da classe corrigido para 'Recepcionist'

    public Recepcionist() {
        super(); // Chama o construtor da superclasse Employee
    }
}
