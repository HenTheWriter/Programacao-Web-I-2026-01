package com.ueg.model;

import jakarta.persistence.Entity;

import lombok.Data;          // Para o @Data (Getters/Setters automáticos)
import java.time.LocalDate;  // Para o tipo de data

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
@Data

public class Personagem 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único (Long)

    private String nome; // Nome do personagem (String)
    private String classe; // Ex: Combatente, Especialista, Ocultista, Sobrevivente, Mundano (String)
    private int NEX; // Nível de Exposição Paranormal (int)
    private String SaH; // verificar se está sendo utilizado as regras do sobrevivendo ao horror

    private int agiATR; // Atributo Agilidade
    private int forATR; // Atributo Força
    private int intATR; // Atributo Intelecto
    private int preATR; // Atributo Presença
    private int vigATR; // Atributo Vigor

    private int pvAtual; // Pontos de Vida 
    private int pvMAX;
    private int sanAtual; // Pontos de Sanidade
    private int sanMAX;
    private int peAtual; // Pontos de Esforço
    private int peMAX;
    private int pdAtual; // Pontos de Determinação
    private int pdMAX;

    private boolean inserido; // Verifica se o personagem está ou não inserido em uma capanha
    private LocalDate dataCriacao; // Data que a ficha foi feita (LocalDate)

    @PrePersist
    public void preencherDataCriacao() {
        this.dataCriacao = LocalDate.now();
    }
}