package br.com.industria.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {

	private BigDecimal salario;
	private String funcao;

	public Funcionario() {

	}

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

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = getDataNascimento().format(formatter);
		String formattedSalary = String.format("%,.2f", salario);

		return "Nome: " + getNome() + ", Data de Nascimento: " + formattedDate + ", Salário: R$ " + formattedSalary
				+ ", Função: " + funcao;
	}
}
