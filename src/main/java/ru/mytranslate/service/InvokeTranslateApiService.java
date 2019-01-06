package ru.mytranslate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mytranslate.controller.ValidLanguage;
import ru.mytranslate.domain.dto.YandexApiResponseDto;

import java.util.concurrent.CompletableFuture;

@Service
public class InvokeTranslateApiService {
    @Value("${translate.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<YandexApiResponseDto> translate(ValidLanguage from, ValidLanguage to, String word) {
        return CompletableFuture.completedFuture(restTemplate.postForObject(String.format(url, from, to, word), null, YandexApiResponseDto.class));
    }
}
