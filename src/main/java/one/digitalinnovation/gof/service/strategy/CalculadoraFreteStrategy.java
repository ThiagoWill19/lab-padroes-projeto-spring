package one.digitalinnovation.gof.service.strategy;

import one.digitalinnovation.gof.model.Endereco;

public interface CalculadoraFreteStrategy {

	boolean aceitar(Endereco origem, Endereco destino);

	String getTipoFrete();

	double calcularValor();
}
