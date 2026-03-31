package com.ueg.repository; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ueg.model.Personagem; 

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> 
{
    
}