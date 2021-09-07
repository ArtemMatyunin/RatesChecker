package ru.alfa.rateschecker.controllers;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/***
 * @author Артём Матюнин
 * Перехватчик исключений по всему приложению
 * Перехватывает исключения, логирует, предоставяет ответ клиенту.
 */
@ControllerAdvice(basePackages = "ru.alfa.rateschecker")
@Slf4j
public class ExceptionController {

    /***
     * Обрабатывает и логирует исключения,, возникающие при некорректных входных параметрах методов
     * Предоставляет ответ клиенту
     * @param ex исключение, возникающее при некорреткных входных параметрах
     * @return ответ клиенту с коротким собщением
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Invalid Input Exception: message: {}\n " +
                "caused by: {}", ex.getMessage(), ex.getCause());
        return new ResponseEntity<>("Некорректные входящие параметры", HttpStatus.BAD_REQUEST);
    }

    /***
     * Обрабатывает и логирует исключения, возникающие при некорректных ответах от Feign-клиентов
     * Предоставляет ответ клиенту
     * @param ex исключение, возникающеепри некорректных ответах от Feign-клиентов
     * @return ответ клиенту с коротким собщением
     */
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        log.error("Invalid Response Exception: message {}\n" +
                "caused by: {}", ex.getMessage(), ex.getCause());
        return new ResponseEntity<>("Не удалось получить ответ от сервера", HttpStatus.BAD_REQUEST);
    }

    /***
     * Обрабатывает и логирует остальные исключения
     * Предоставляет ответ клиенту
     * @param ex исключение, возникающее в Feign-клиентах
     * @return ответ клиенту с коротким собщением
     */
    @ExceptionHandler(value = {FeignException.FeignClientException.class})
    public ResponseEntity<Object> handleFeignException(Exception ex) {
        log.error("Invalid Response Exception: message {}\n" +
                "caused by: {}", ex.getMessage(), ex.getCause());
        return new ResponseEntity<>("Не удалось получить ответ от сервера", HttpStatus.BAD_REQUEST);
    }

}
