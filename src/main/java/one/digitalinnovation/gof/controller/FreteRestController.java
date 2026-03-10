package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import one.digitalinnovation.gof.model.dto.FreteRequestDto;
import one.digitalinnovation.gof.model.dto.FreteResponseDto;
import one.digitalinnovation.gof.service.FreteService;

@RestController
@RequestMapping("fretes")
@Tag(name = "Fretes", description = "Endpoints para simulacao de frete por CEP")
public class FreteRestController {

	@Autowired
	private FreteService freteService;

	@PostMapping("/calcular")
	@Operation(
			summary = "Calcula o valor do frete",
			description = "Consulta os CEPs de origem e destino no ViaCEP e aplica a regra de frete adequada.")
	public ResponseEntity<FreteResponseDto> calcular(@RequestBody FreteRequestDto request) {
		return ResponseEntity.ok(freteService.calcularFrete(request));
	}
}
