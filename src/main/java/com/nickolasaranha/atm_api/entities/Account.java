package com.nickolasaranha.atm_api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;


    @Getter @Setter
    @NotNull(message = "A agência não pode ser nula")
    @Min(value = 1, message = "A agência deve ser maior que zero")
    @Max(value = 9999, message = "A agência deve ter no máximo 4 dígitos")
    private Integer agency;

    @Getter @Setter
    @Column(unique=true)
    private String numberAccount;

    @Getter @Setter
    @NotNull(message = "A senha é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Getter @Setter
    @NotNull(message = "O saldo inicial é obrigatório")
    @Column(precision = 19, scale = 2)
    private BigDecimal balance;

    @Getter
    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();
}
