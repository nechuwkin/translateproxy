package ru.mytranslate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mytranslate.controller.ValidLanguage;
import ru.mytranslate.domain.dto.YandexApiResponseDto;

import java.util.concurrent.CompletableFuture;

@Component
public class YandexApiService {
    private final String url;
    private final RestTemplate restTemplate;

    public YandexApiService(@Value("${translate.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Async("taskExecutor")
    public CompletableFuture<YandexApiResponseDto> translateAsync(ValidLanguage from, ValidLanguage to, String word) {
        return CompletableFuture.completedFuture(restTemplate.postForObject(String.format(url, from, to, word), null, YandexApiResponseDto.class));
    }
}
