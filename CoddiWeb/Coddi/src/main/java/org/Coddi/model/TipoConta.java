package org.Coddi.model;

public enum TipoConta {
	CARTEIRA(1, "Carteira"), //
	POUPANCA(2, "Poupança"), //
	CORRENTE(3, "Conta-corrente"), //
	CARTAO_CREDITO(4, "Cartão de crédito"), //
	CARTAO_DEBITO(5, "Cartão de débito");

	private Integer id;
	private String descricao;

	TipoConta(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return id + " - " + descricao;
	}

}
