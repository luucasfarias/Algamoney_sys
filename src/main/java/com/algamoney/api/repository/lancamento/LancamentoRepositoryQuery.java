package com.algamoney.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	public Page<Lancamento> filtrar(LancamentoFilter lancamentofilter, Pageable pageeble);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentofilter, Pageable pageeble);
}
