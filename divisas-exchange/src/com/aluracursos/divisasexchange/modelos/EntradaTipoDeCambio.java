package com.aluracursos.divisasexchange.modelos;
import java.util.Map;

//Código Base --> divisaActual
//Factores de Conversion --> Lista de todos los factores de conversion
public record EntradaTipoDeCambio(String base_code, Map<String, Float> conversion_rates) {
}
