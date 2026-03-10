package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.dto.FreteRequestDto;
import one.digitalinnovation.gof.model.dto.FreteResponseDto;
import one.digitalinnovation.gof.service.FreteService;

@RestController
@RequestMapping("fretes")
public class FreteRestController {

	@Autowired
	private FreteService freteService;

	@PostMapping("/calcular")
	public ResponseEntity<FreteResponseDto> calcular(@RequestBody FreteRequestDto request) {
		return ResponseEntity.ok(freteService.calcularFrete(request));
	}
}
