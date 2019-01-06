package ru.mytranslate.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.mytranslate.controller.ValidLanguage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyTranslateRequestDto {
    private ValidLanguage from;
    private ValidLanguage to;
    private String text;

    public MyTranslateRequestDto() {
    }

    public ValidLanguage getFrom() {
        return from;
    }

    public void setFrom(ValidLanguage from) {
        this.from = from;
    }

    public ValidLanguage getTo() {
        return to;
    }

    public void setTo(ValidLanguage to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
