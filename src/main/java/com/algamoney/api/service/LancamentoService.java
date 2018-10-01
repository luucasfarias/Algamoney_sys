package com.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInativaOuInexistenteException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInativaOuInexistenteException();
		}
		return lancamentoRepository.save(lancamento);
	}

	Public Lancamento atualizar(Long id, Lancamento lancamento){
		Lancamento lancamentoSalvo = buscarLancamentoExistente(id);
		if(!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())){
			validarPessoa(lancamento);
		}

		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento){
		Pessoa pessoa = null;
		if(lancamento.getPessoa().getId() != null){
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		}

		if(pessoa == null || pessoa.isInativo()){
			throw new PessoaInativaOuInexistenteException();
		}
	}

	private Lancamento buscarLancamentoExistente(Long id){
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		if(lancamentoSalvo == null){
			throw new IllegalArgumentException();
		}

		return lancamentoSalvo;
	}

}
