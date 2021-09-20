package com.api.name.domain.espaco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Long> {
}
