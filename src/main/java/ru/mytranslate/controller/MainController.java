package ru.mytranslate.controller;

import org.springframework.web.bind.annotation.*;
import ru.mytranslate.domain.SystemLog;
import ru.mytranslate.domain.dto.MyTranslateRequestDto;
import ru.mytranslate.domain.dto.MyTranslateResponseDto;
import ru.mytranslate.domain.dto.YandexApiResponseDto;
import ru.mytranslate.repository.SystemLogRepository;
import ru.mytranslate.service.InvokeTranslateApiService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@RestController
public class MainController {

    private final SystemLogRepository systemLogRepository;
    private final InvokeTranslateApiService service;

    public MainController(SystemLogRepository systemLogRepository, InvokeTranslateApiService service) {
        this.systemLogRepository = systemLogRepository;
        this.service = service;
    }

    @GetMapping(value = "/translate")
    public MyTranslateResponseDto translate(@RequestParam("text") String text,
                                          @RequestParam("from") ValidLanguage from,
                                          @RequestParam("to") ValidLanguage to,
                                          HttpServletRequest request) throws Exception {
        String[] words = text.split(" ");

        CompletableFuture[] allFutures = new CompletableFuture[words.length];
        for (int i = 0; i < words.length; i++) {
            CompletableFuture<YandexApiResponseDto> future = service.translate(from, to, words[i]);
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

        String ip = request.getRemoteAddr();
        systemLogRepository.save(new SystemLog(ip, from, to, text));

        return new MyTranslateResponseDto(translatedString.toString());
    }

    @PostMapping("/translate")
    public MyTranslateResponseDto translatePost(@RequestBody MyTranslateRequestDto json, HttpServletRequest request) throws Exception {
        return translate(json.getText(), json.getFrom(), json.getTo(), request);
    }

    @GetMapping("/read-log")
    public Iterable<SystemLog> readLog(){
        return systemLogRepository.findAll();
    }
}
