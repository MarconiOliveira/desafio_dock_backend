package tech.dock.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.dock.desafio.model.Terminal;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Integer>{

}