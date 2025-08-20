package in.fortytwo42.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
  public ResponseEntity<String> handleCustomException (CustomException e){
    return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handelAllExceptions(Exception e){
        e.printStackTrace();
        return  new ResponseEntity<>("Something went wrong.",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
