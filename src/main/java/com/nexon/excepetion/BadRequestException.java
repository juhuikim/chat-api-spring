package com.nexon.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Include symbol or over 20 chatracters")
public class BadRequestException extends RuntimeException {
}
