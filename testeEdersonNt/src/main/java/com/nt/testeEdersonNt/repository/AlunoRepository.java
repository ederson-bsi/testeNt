package com.nt.testeEdersonNt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.testeEdersonNt.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Aluno findByEmail(String email);
}
