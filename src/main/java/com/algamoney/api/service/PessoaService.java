package com.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = encontrarPessoa(id); // metodo encontrar pessoa.
		// fazendo uma copia do objeto pessoa para pessoaSalva ignorando o ID
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		// depois de pessoaSalva ter os dados atualizados do objeto pessoa
		// salva pessoaSalva no banco de dados com os dados atuais.
		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarAtivo(Long id, Boolean ativo) {
		// Atualização parcial para propiedade ativo do modelo de dados.
		Pessoa pessoaSalva = encontrarPessoa(id); // metodo encontrar pessoa.
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	// busca a pessoa pelo seu id
	public Pessoa encontrarPessoa(Long id) {
		// buscar por pessoa que ja esta no banco para depois atualizar
		Pessoa pessoaSalva = pessoaRepository.findOne(id);

		// validando a exceção se pessoaSalva for igual a null, lançar exceção tratada.
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
