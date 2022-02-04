package tech.dock.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.dock.desafio.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{

}