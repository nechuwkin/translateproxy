package ru.mytranslate.controller;

import org.springframework.web.bind.annotation.*;
import ru.mytranslate.domain.SystemLog;
import ru.mytranslate.domain.dto.MyTranslateRequestDto;
import ru.mytranslate.domain.dto.MyTranslateResponseDto;
import ru.mytranslate.repository.SystemLogRepository;
import ru.mytranslate.service.MyTranslateService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {
    private final SystemLogRepository systemLogRepository;
    private final MyTranslateService service;

    public MainController(SystemLogRepository systemLogRepository, MyTranslateService service) {
        this.systemLogRepository = systemLogRepository;
        this.service = service;
    }

    @GetMapping(value = "/translate")
    public MyTranslateResponseDto translate(@RequestParam("text") String text,
                                          @RequestParam("from") ValidLanguage from,
                                          @RequestParam("to") ValidLanguage to,
                                          HttpServletRequest request) throws Exception {
        return new MyTranslateResponseDto(service.translate(from, to, text, request.getRemoteAddr()));
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
