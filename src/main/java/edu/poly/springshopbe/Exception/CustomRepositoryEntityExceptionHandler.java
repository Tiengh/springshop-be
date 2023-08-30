package edu.poly.springshopbe.Exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomRepositoryEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CategoryException.class)
    public final ResponseEntity<Object> handlerCategoryException(CategoryException ex, WebRequest request){
        CategoryExceptionRespone exceptionRespone = new CategoryExceptionRespone(ex.getMessage());
        return new ResponseEntity<>(exceptionRespone, HttpStatus.BAD_REQUEST);
    }

}
