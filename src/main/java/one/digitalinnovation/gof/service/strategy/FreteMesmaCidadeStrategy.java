package one.digitalinnovation.gof.service.strategy;

import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Endereco;

@Component
public class FreteMesmaCidadeStrategy implements CalculadoraFreteStrategy {

	private static final double VALOR_MESMA_CIDADE = 10.0;

	@Override
	public boolean aceitar(Endereco origem, Endereco destino) {
		return origem.getUf().equalsIgnoreCase(destino.getUf())
				&& origem.getLocalidade().equalsIgnoreCase(destino.getLocalidade());
	}

	@Override
	public String getTipoFrete() {
		return "MESMA_CIDADE";
	}

	@Override
	public double calcularValor() {
		return VALOR_MESMA_CIDADE;
	}
}
