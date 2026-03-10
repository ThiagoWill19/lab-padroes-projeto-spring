package one.digitalinnovation.gof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.dto.FreteRequestDto;
import one.digitalinnovation.gof.model.dto.FreteResponseDto;
import one.digitalinnovation.gof.service.FreteService;
import one.digitalinnovation.gof.service.ViaCepService;

@Service
public class FreteServiceImpl implements FreteService {

	private static final double VALOR_MESMA_CIDADE = 10.0;
	private static final double VALOR_MESMO_ESTADO = 20.0;
	private static final double VALOR_OUTRO_ESTADO = 40.0;

	@Autowired
	private ViaCepService viaCepService;

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

		if (origem.getUf().equalsIgnoreCase(destino.getUf())
				&& origem.getLocalidade().equalsIgnoreCase(destino.getLocalidade())) {
			response.setTipoFrete("MESMA_CIDADE");
			response.setValorFrete(VALOR_MESMA_CIDADE);
		} else if (origem.getUf().equalsIgnoreCase(destino.getUf())) {
			response.setTipoFrete("MESMO_ESTADO");
			response.setValorFrete(VALOR_MESMO_ESTADO);
		} else {
			response.setTipoFrete("OUTRO_ESTADO");
			response.setValorFrete(VALOR_OUTRO_ESTADO);
		}

		return response;
	}
}
