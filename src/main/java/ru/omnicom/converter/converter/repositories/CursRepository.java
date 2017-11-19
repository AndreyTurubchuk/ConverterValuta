package ru.omnicom.converter.converter.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.omnicom.converter.converter.model.ExchangeRate;

public interface CursRepository extends JpaRepository<ExchangeRate, Long> {
}
