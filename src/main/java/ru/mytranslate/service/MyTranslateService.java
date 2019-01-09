package ru.mytranslate.service;

import org.springframework.stereotype.Service;
import ru.mytranslate.controller.ValidLanguage;
import ru.mytranslate.domain.SystemLog;
import ru.mytranslate.domain.dto.YandexApiResponseDto;
import ru.mytranslate.repository.SystemLogRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class MyTranslateService {
    private final YandexApiService yandexApi;
    private final SystemLogRepository systemLogRepository;

    public MyTranslateService(YandexApiService yandexApi, SystemLogRepository systemLogRepository) {
        this.yandexApi = yandexApi;
        this.systemLogRepository = systemLogRepository;
    }

    public String translate(ValidLanguage from, ValidLanguage to, String text, String remoteIp) throws Exception {
        String[] words = text.split(" ");

        CompletableFuture[] allFutures = new CompletableFuture[words.length];
        for (int i = 0; i < words.length; i++) {
            CompletableFuture<YandexApiResponseDto> future = yandexApi.translateAsync(from, to, words[i]);
            allFutures[i] = future;
        }
        CompletableFuture.allOf(allFutures).join();

        StringBuilder translatedString = new StringBuilder();
        for (CompletableFuture future : allFutures) {
            if (translatedString.length() != 0) {
                translatedString.append(" ");
            }
            translatedString.append(future.get());
        }

        systemLogRepository.save(new SystemLog(remoteIp, from, to, text));
        return translatedString.toString();
    }
}
