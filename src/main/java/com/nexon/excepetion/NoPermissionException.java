package com.nexon.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="No Permission")
public class NoPermissionException extends RuntimeException{
}
