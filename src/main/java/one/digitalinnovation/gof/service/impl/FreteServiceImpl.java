package one.digitalinnovation.gof.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.dto.FreteRequestDto;
import one.digitalinnovation.gof.model.dto.FreteResponseDto;
import one.digitalinnovation.gof.service.FreteService;
import one.digitalinnovation.gof.service.ViaCepService;
import one.digitalinnovation.gof.service.strategy.CalculadoraFreteStrategy;

@Service
public class FreteServiceImpl implements FreteService {

	private final ViaCepService viaCepService;
	private final List<CalculadoraFreteStrategy> calculadorasFrete;

	public FreteServiceImpl(ViaCepService viaCepService, List<CalculadoraFreteStrategy> calculadorasFrete) {
		this.viaCepService = viaCepService;
		this.calculadorasFrete = calculadorasFrete;
	}

	@Override
	public FreteResponseDto calcularFrete(FreteRequestDto request) {
		Endereco origem = viaCepService.consultarCep(request.getCepOrigem());
		Endereco destino = viaCepService.consultarCep(request.getCepDestino());

		FreteResponseDto response = new FreteResponseDto();
		response.setCepOrigem(origem.getCep());
		response.setCepDestino(destino.getCep());
		response.setCidadeOrigem(origem.getLocalidade());
		response.setCidadeDestino(destino.getLocalidade());
		response.setUfOrigem(origem.getUf());
		response.setUfDestino(destino.getUf());
		CalculadoraFreteStrategy calculadora = calcularFrete(origem, destino);
		response.setTipoFrete(calculadora.getTipoFrete());
		response.setValorFrete(calculadora.calcularValor());

		return response;
	}

	private CalculadoraFreteStrategy calcularFrete(Endereco origem, Endereco destino) {
		return calculadorasFrete.stream()
				.filter(calculadora -> calculadora.aceitar(origem, destino))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Nenhuma regra de frete encontrada"));
	}
}
