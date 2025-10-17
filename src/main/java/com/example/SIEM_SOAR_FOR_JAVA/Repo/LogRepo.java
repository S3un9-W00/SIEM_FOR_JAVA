package com.example.SIEM_SOAR_FOR_JAVA.Repo;

import com.example.SIEM_SOAR_FOR_JAVA.Model.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends JpaRepository<LogEntity, Long> {
}