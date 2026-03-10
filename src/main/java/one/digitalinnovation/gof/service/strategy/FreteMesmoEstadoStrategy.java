package one.digitalinnovation.gof.service.strategy;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Endereco;

@Component
public class FreteMesmoEstadoStrategy implements CalculadoraFreteStrategy {

	private static final double VALOR_MESMO_ESTADO = 20.0;

	@Override
	public boolean aceitar(Endereco origem, Endereco destino) {
		return origem.getUf().equalsIgnoreCase(destino.getUf())
				&& !origem.getLocalidade().equalsIgnoreCase(destino.getLocalidade());
	}

	@Override
	public String getTipoFrete() {
		return "MESMO_ESTADO";
	}

	@Override
	public double calcularValor() {
		return VALOR_MESMO_ESTADO;
	}
}
