package com.product.config.openapi;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
// --- Importaciones añadidas ---
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {
        
        // --- INICIO: Definición de Seguridad ---
        
        final String securitySchemeName = "bearerAuth"; // Nombre de referencia para el esquema

        // Define el esquema de seguridad (Bearer Token JWT)
        SecurityScheme securityScheme = new SecurityScheme()
            .name(securitySchemeName)
            .description("Token JWT de autorización")
            .type(SecurityScheme.Type.HTTP) // Tipo HTTP
            .scheme("bearer")               // Esquema Bearer
            .bearerFormat("JWT");           // Formato JWT

        // Define el requisito de seguridad (qué esquema se va a usar)
        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList(securitySchemeName);

        // --- FIN: Definición de Seguridad ---


        // Construye el objeto OpenAPI
        return new OpenAPI()
            .info(new Info()
                .title("DWB - API Customer")
                .version("0.0.1")
                .description("API para la gestión clientes para la tienda en línea FCiencias Store."))
            
            // --- Añade los componentes de seguridad al OpenAPI ---
            .addSecurityItem(securityRequirement) // Añade el requisito de seguridad globalmente
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, securityScheme) // Añade el esquema a los componentes
            );
    }

	@Bean
	public OpenApiCustomizer sortSchemasAlphabetically() {
	   return openApi -> {
	       Components components = openApi.getComponents();
	       if (components != null && components.getSchemas() != null) {
	           Map<String, Schema> sortedSchemas = components.getSchemas().entrySet().stream()
	                   .sorted(Map.Entry.comparingByKey())
	                   .collect(Collectors.toMap(
	                       Map.Entry::getKey,
	                       Map.Entry::getValue,
	                       (oldValue, newValue) -> oldValue,
	                       LinkedHashMap::new
	                   ));
	           components.setSchemas(sortedSchemas);
	       }
	   };
	}
}