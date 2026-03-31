package com.ueg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id de identificação no banco de dados

    @Column(unique = true, nullable = false)
    private String login; // nome de acesso definido pelo usuário
    private String senha; // senha do usuário

    @Column(unique = true)
    private String email; // email de login
    private int idade; // verifica a idade do usuário garantindo conformidade com a lei
    private boolean contaAtiva; // verifica se é uma conta ativa
    private LocalDate dataCadastro; // guarda a data de cadastro do usuário
    
    // -= Comentário Importante =- 
    // O papel de Mestre ou Jogador não está aqui pois será aquele que criou a campanha dentro do site.
}