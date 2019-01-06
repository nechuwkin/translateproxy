package ru.mytranslate.domain.dto;

public class MyTranslateResponseDto {
    private String text;

    public MyTranslateResponseDto() {
    }

    public MyTranslateResponseDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
