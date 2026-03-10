package one.digitalinnovation.gof.service.strategy;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Endereco;

@Component
public class FreteOutroEstadoStrategy implements CalculadoraFreteStrategy {

	private static final double VALOR_OUTRO_ESTADO = 40.0;

	@Override
	public boolean aceitar(Endereco origem, Endereco destino) {
		return !origem.getUf().equalsIgnoreCase(destino.getUf());
	}

	@Override
	public String getTipoFrete() {
		return "OUTRO_ESTADO";
	}

	@Override
	public double calcularValor() {
		return VALOR_OUTRO_ESTADO;
	}
}
