package com.ueg.repository; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ueg.model.Mesa; 

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> 
{
    
}