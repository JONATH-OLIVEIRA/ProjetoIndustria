package com.br.ProjetoIndustria.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Industria {

	public static void main(String[] args) {

		Funcionario funcionarioMaisVelho = null;

		BigDecimal totalSalarios = BigDecimal.ZERO;

		BigDecimal salarioMinimo = new BigDecimal("1212.00");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

		Map<Integer, Funcionario> funcionarios = new HashMap<>();
		funcionarios.put(1,
				new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.put(2, new Funcionario("João", LocalDate.of(1990, 12, 05), new BigDecimal("2284.38"), "Operador"));
		funcionarios.put(3,
				new Funcionario("Caio", LocalDate.of(1961, 5, 02), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.put(4,
				new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.put(5,
				new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.put(6,
				new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.put(7,
				new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.put(8, new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.put(9,
				new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.put(10,
				new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));

		// Remove o funcionario passando o nome por parametro.

		System.out.println("\n");

		String remover = "João";
		List<Integer> keysToRemove = new ArrayList<>();
		for (Map.Entry<Integer, Funcionario> entry : funcionarios.entrySet()) {
			if (entry.getValue().getNome().equals(remover)) {
				keysToRemove.add(entry.getKey());
			}
		}
		if (keysToRemove.isEmpty()) {
			System.out.println("Funcionário com o nome " + remover + " não encontrado.");
		} else {
			for (Integer key : keysToRemove) {
				funcionarios.remove(key);
				System.out.println("Funcionario com o nome " + remover + " Removido. ");
			}

			System.out.println("\n");

			// Aumentando salários em 10%
			for (Funcionario funcionario : funcionarios.values()) {
				BigDecimal salario = funcionario.getSalario();
				BigDecimal aumento = salario.multiply(new BigDecimal("0.1"));
				salario = salario.add(aumento);
				funcionario.setSalario(salario);
			}

			// Lista os funcionarios com o Salario Atualizado e com o remocao feita.
			System.out.println("Lista de funcionários:");
			for (Map.Entry<Integer, Funcionario> entry : funcionarios.entrySet()) {
				Funcionario funcionario = entry.getValue();

				BigDecimal salario = funcionario.getSalario();
				salario = salario.setScale(2, RoundingMode.HALF_UP);

				totalSalarios = totalSalarios.add(entry.getValue().getSalario());

				System.out.println("*******************************");
				System.out.println("ID: " + entry.getKey());
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Data de nascimento: " + funcionario.getDataNascimento().format(formatter));
				System.out.println("Salário: " + nf.format(salario));
				System.out.println("Função: " + funcionario.getFuncao());
				System.out.println("*******************************");

			}

			BigDecimal totalSalariosFormatado = totalSalarios.setScale(2, RoundingMode.HALF_UP);
			System.out.println("\n");
			System.out.println("Total de salários: " + nf.format(totalSalariosFormatado));
			System.out.println("\n");

		}

		// Agrupa os funcionarios por funcao
		Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
		for (Funcionario funcionario : funcionarios.values()) {
			String funcao = funcionario.getFuncao();
			List<Funcionario> listaFuncionarios = funcionariosPorFuncao.get(funcao);
			if (listaFuncionarios == null) {
				listaFuncionarios = new ArrayList<>();
				funcionariosPorFuncao.put(funcao, listaFuncionarios);
			}
			listaFuncionarios.add(funcionario);
		}

		for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
			String funcao = entry.getKey();
			List<Funcionario> listaFuncionarios = entry.getValue();
			System.out.println("Função: " + funcao);
			for (Funcionario funcionario : listaFuncionarios) {

				BigDecimal salario = funcionario.getSalario();
				salario = salario.setScale(2, RoundingMode.HALF_UP);

				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
				System.out.println("Salário: " + nf.format(salario));
				System.out.println();

			}
		}
		// busca os aniversariantes dos meses informados e percorre a lista trazendo
		// tambem o funcionario mais velha exibindo seus dados.
		System.out.println("Aniversariantes");
		System.out.println("\n");
		for (Funcionario funcionario : funcionarios.values()) {

			BigDecimal salario = funcionario.getSalario();
			salario = salario.setScale(2, RoundingMode.HALF_UP);

			int mesAniversario = funcionario.getDataNascimento().getMonthValue();

			if (mesAniversario == 10 || mesAniversario == 12) {

				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
				System.out.println("Salário: " + nf.format(salario));
				System.out.println("Função: " + funcionario.getFuncao());
				System.out.println();
			}

			if (funcionarioMaisVelho == null
					|| funcionario.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
				funcionarioMaisVelho = funcionario;
			}
		}

		if (funcionarioMaisVelho != null) {
			LocalDate hoje = LocalDate.now();
			int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), hoje).getYears();
			System.out.println("Funcionário com a maior idade:");
			System.out.println("Nome: " + funcionarioMaisVelho.getNome());
			System.out.println("Idade: " + idade);
			System.out.println("\n");
		} else {
			System.out.println("Não há funcionários registrados.");
		}

		// Lista os funcionarios em Ordem Alfabetica.
		System.out.println("Funcionarios Em Ordem Alfabetica");
		System.out.println("\n");

		List<Funcionario> listaFuncionarios = new ArrayList<>(funcionarios.values());
		listaFuncionarios.sort(Comparator.comparing(Funcionario::getNome));

		for (Funcionario funcionario : listaFuncionarios) {
			BigDecimal salario = funcionario.getSalario();
			salario = salario.setScale(2, RoundingMode.HALF_UP);
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
			System.out.println("Salário: " + nf.format(salario));
			System.out.println("Função: " + funcionario.getFuncao());
			System.out.println();
		}

		// Percorre a Lista e mostra os salarios e a quantidade dos mesmo ganho pelos
		// funcionarios.
		for (Funcionario funcionario : funcionarios.values()) {
			BigDecimal salario = funcionario.getSalario();
			salario = salario.setScale(2, RoundingMode.HALF_UP);
			String salarioFormatado = nf.format(salario);
			System.out.println(funcionario.getNome() + ": R$ " + salarioFormatado);

			BigDecimal qtdSalariosMinimos = salario.divide(salarioMinimo,

					RoundingMode.HALF_UP);
			String qtdSalariosMinimosFormatado = nf.format(qtdSalariosMinimos);
			System.out.println(funcionario.getNome() + " ganha " + qtdSalariosMinimosFormatado + " salários mínimos.");
			System.out.println("\n");
		}
	}
}
