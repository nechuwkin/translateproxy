/*
 Пример ответа
 HTTP/1.1 200 OK
 Server: nginx
 Content-Type: application/json; charset=utf-8
 Content-Length: 68
 Connection: keep-alive
 Keep-Alive: timeout=120
 X-Content-Type-Options: nosniff
 Date: Thu, 31 Mar 2016 10:50:20 GMT
 {
    "code": 200,
    "lang": "en-ru",
    "text": [
        "Здравствуй, Мир!"
    ]
 }
*/
package ru.mytranslate.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexApiResponseDto {
    private List<String> text;
    private int code;

    public YandexApiResponseDto() {
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return text.get(0);
    }
}
