package com.ueg.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mesas")
public class Mesa 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaMesa; 
    private String sistema;    // sistema normal ou modo sobrevivendo ao horror
    private int maxJogadores;  
    private boolean permiteEspectadores;
    private String codigoConvite; // código para os jogadores entrarem

    private Long idMestre; 
}