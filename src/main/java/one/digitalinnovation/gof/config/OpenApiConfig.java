package one.digitalinnovation.gof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI freightOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Simulador de Frete por CEP")
						.description("API para calcular frete a partir de CEPs de origem e destino.")
						.version("v1")
						.contact(new Contact()
								.name("Thiago")
								.url("https://www.dio.me/")));
	}
}
