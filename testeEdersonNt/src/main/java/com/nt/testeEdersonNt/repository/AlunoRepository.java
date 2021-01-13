package com.nt.testeEdersonNt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.testeEdersonNt.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Aluno findByemail(String email);
}
