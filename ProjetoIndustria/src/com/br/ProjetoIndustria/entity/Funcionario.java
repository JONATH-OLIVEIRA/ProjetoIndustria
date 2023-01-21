package com.br.ProjetoIndustria.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

class Funcionario extends Pessoa {
	private BigDecimal salario = BigDecimal.ZERO;
	private String funcao;

	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;

	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}