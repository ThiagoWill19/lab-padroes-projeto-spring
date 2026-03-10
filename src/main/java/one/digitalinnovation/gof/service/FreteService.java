package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.dto.FreteRequestDto;
import one.digitalinnovation.gof.model.dto.FreteResponseDto;

public interface FreteService {

	FreteResponseDto calcularFrete(FreteRequestDto request);
}
