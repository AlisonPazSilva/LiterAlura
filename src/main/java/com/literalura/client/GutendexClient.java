package com.literalura.client;

import com.literalura.dto.ResultadoBusqueda;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.Normalizer;

@Component
public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResultadoBusqueda buscarLibrosPorTitulo(String tituloOriginal) {
        String tituloNormalizado = normalizarTexto(tituloOriginal);

        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("search", tituloNormalizado)
                .toUriString();

        System.out.println("⤵️ Consultando URL: " + url);

        try {
            ResultadoBusqueda resultado = restTemplate.getForObject(url, ResultadoBusqueda.class);
            if (resultado != null && resultado.results() != null) {
                System.out.println("📚 Libros encontrados: " + resultado.results().size());
            } else {
                System.out.println("⚠️ Respuesta nula o sin resultados.");
            }
            return resultado;
        } catch (RestClientException e) {
            System.out.println("❌ Error al consultar la API: " + e.getMessage());
            return null;
        }
    }

    private String normalizarTexto(String texto) {
        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return sinTildes.trim().toLowerCase();
    }
}