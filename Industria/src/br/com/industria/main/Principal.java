package br.com.industria.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.industria.entidades.Funcionario;

public class Principal {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        List<Funcionario> funcionarios = inicializarFuncionarios();

        removerFuncionarioPorNome(funcionarios, "João");

        System.out.println("Todos os funcionários:");
        imprimirFuncionarios(funcionarios);

        aumentarSalarios(funcionarios, new BigDecimal("0.10"));

        System.out.println("*******************NOVOS SALARIOS***************************");
        imprimirFuncionarios(funcionarios);

        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);

        System.out.println("Funcionários agrupados por função:");
        imprimirFuncionariosAgrupados(funcionariosPorFuncao);

        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        imprimirAniversariantes(funcionarios, 10);
        imprimirAniversariantes(funcionarios, 12);

        imprimirFuncionarioMaisVelho(funcionarios);

        System.out.println("Funcionários em ordem alfabética:");
        imprimirFuncionariosOrdenados(funcionarios);

        imprimirTotalSalarios(funcionarios);

        imprimirSalariosMinimos(funcionarios);
    }

    private static List<Funcionario> inicializarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    private static void removerFuncionarioPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(funcionario -> System.out.println(funcionario + "\n"));
    }

    private static void aumentarSalarios(List<Funcionario> funcionarios, BigDecimal percentual) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.ONE.add(percentual));
            funcionario.setSalario(novoSalario);
        }
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao + "\n");
            listaFuncionarios.forEach(funcionario -> System.out.println("    " + funcionario + "\n"));
        });
    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios, int mes) {
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .forEach(funcionario -> System.out.println(funcionario + "\n"));
    }

    private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
        if (funcionarioMaisVelho != null) {
            int idade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
            System.out.println(
                    "Funcionário com a maior idade: Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade + "\n\n");
        }
    }

    private static void imprimirFuncionariosOrdenados(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> System.out.println(funcionario + "\n"));
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: R$ " + String.format("%,.2f", totalSalarios) + "\n\n");
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.\n");
        }
    }
}
