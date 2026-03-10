package one.digitalinnovation.gof.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.dto.FreteRequestDto;
import one.digitalinnovation.gof.model.dto.FreteResponseDto;
import one.digitalinnovation.gof.service.ViaCepService;
import one.digitalinnovation.gof.service.strategy.FreteMesmaCidadeStrategy;
import one.digitalinnovation.gof.service.strategy.FreteMesmoEstadoStrategy;
import one.digitalinnovation.gof.service.strategy.FreteOutroEstadoStrategy;

@ExtendWith(MockitoExtension.class)
class FreteServiceImplTest {

	@Mock
	private ViaCepService viaCepService;

	private FreteServiceImpl freteService;

	@BeforeEach
	void setUp() {
		freteService = new FreteServiceImpl(
				viaCepService,
				List.of(
						new FreteMesmaCidadeStrategy(),
						new FreteMesmoEstadoStrategy(),
						new FreteOutroEstadoStrategy()));
	}

	@Test
	void deveCalcularFreteParaMesmaCidade() {
		FreteRequestDto request = new FreteRequestDto();
		request.setCepOrigem("01001-000");
		request.setCepDestino("01002-000");

		Endereco origem = criarEndereco("01001-000", "Sao Paulo", "SP");
		Endereco destino = criarEndereco("01002-000", "Sao Paulo", "SP");

		when(viaCepService.consultarCep("01001-000")).thenReturn(origem);
		when(viaCepService.consultarCep("01002-000")).thenReturn(destino);

		FreteResponseDto response = freteService.calcularFrete(request);

		assertEquals("MESMA_CIDADE", response.getTipoFrete());
		assertEquals(10.0, response.getValorFrete());
	}

	@Test
	void deveCalcularFreteParaMesmoEstado() {
		FreteRequestDto request = new FreteRequestDto();
		request.setCepOrigem("01001-000");
		request.setCepDestino("13010-000");

		Endereco origem = criarEndereco("01001-000", "Sao Paulo", "SP");
		Endereco destino = criarEndereco("13010-000", "Campinas", "SP");

		when(viaCepService.consultarCep("01001-000")).thenReturn(origem);
		when(viaCepService.consultarCep("13010-000")).thenReturn(destino);

		FreteResponseDto response = freteService.calcularFrete(request);

		assertEquals("MESMO_ESTADO", response.getTipoFrete());
		assertEquals(20.0, response.getValorFrete());
	}

	@Test
	void deveCalcularFreteParaOutroEstado() {
		FreteRequestDto request = new FreteRequestDto();
		request.setCepOrigem("01001-000");
		request.setCepDestino("20040-020");

		Endereco origem = criarEndereco("01001-000", "Sao Paulo", "SP");
		Endereco destino = criarEndereco("20040-020", "Rio de Janeiro", "RJ");

		when(viaCepService.consultarCep("01001-000")).thenReturn(origem);
		when(viaCepService.consultarCep("20040-020")).thenReturn(destino);

		FreteResponseDto response = freteService.calcularFrete(request);

		assertEquals("OUTRO_ESTADO", response.getTipoFrete());
		assertEquals(40.0, response.getValorFrete());
	}

	private Endereco criarEndereco(String cep, String cidade, String uf) {
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setLocalidade(cidade);
		endereco.setUf(uf);
		return endereco;
	}
}
