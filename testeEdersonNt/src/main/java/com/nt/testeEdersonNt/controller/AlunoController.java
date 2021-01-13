package com.nt.testeEdersonNt.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.testeEdersonNt.entity.Aluno;
import com.nt.testeEdersonNt.repository.AlunoRepository;

@RestController
@RequestMapping(value = "api")
public class AlunoController {
	
	@Autowired
	private AlunoRepository _alunoRepository;
	
	@GetMapping(value = "/alunos")
	public List<Aluno> buscarTodos() {
		return _alunoRepository.findAll();
	}
	
	@GetMapping(value = "/aluno/{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable(value = "id") long id) {
		Optional<Aluno> aluno = _alunoRepository.findById(id);
		if (aluno.isPresent()) {
			return new ResponseEntity<Aluno>(aluno.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/aluno/email/{email}")
	public Aluno buscar(@RequestParam(value = "email") String email){
		Aluno aluno = _alunoRepository.findByEmail(email);
		return aluno;
	}
	
	@PostMapping(value = "/aluno")
	public Aluno salvarAluno(@Valid @RequestBody Aluno aluno) {
		return _alunoRepository.save(aluno);
	}
	
	@PutMapping(value = "/aluno/{id}")
	public ResponseEntity<Aluno> editarAluno(@Valid @PathVariable(value = "id") long id, @RequestBody Aluno newAluno) {
		Optional<Aluno> oldAluno = _alunoRepository.findById(id);
		if (oldAluno.isPresent()) {
			Aluno aluno = oldAluno.get();
			aluno.setNome(newAluno.getNome());
			aluno.setTelefone(newAluno.getTelefone());
			aluno.setEmail(newAluno.getEmail());
			
			_alunoRepository.save(aluno);
			return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
		}else {
			return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/aluno/{id}")
	public ResponseEntity<Object> excluirAluno(@PathVariable(value = "id") long id) {
		Optional<Aluno> aluno = _alunoRepository.findById(id);
		if (aluno.isPresent()) {
			_alunoRepository.delete(aluno.get());
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

}
