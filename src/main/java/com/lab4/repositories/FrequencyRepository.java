package com.lab4.repositories;

import com.lab4.models.FrequencyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyRepository extends JpaRepository<FrequencyRecord, Long> {
}