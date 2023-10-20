package br.com.serratec.ecommerce.handler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;
import br.com.serratec.ecommerce.model.error.ErrorResposta;
import br.com.serratec.ecommerce.model.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResposta> handlerResourceNotFoundException(ResourceNotFoundException ex) {

        String data = ConversorData.converterDataParaDataHora(new Date());
        ErrorResposta error = new ErrorResposta(400, "Bad Request", ex.getMessage(), data);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<ErrorResposta> handlerUnprocessableEntityException(UnprocessableEntity ex) {

        String data = ConversorData.converterDataParaDataHora(new Date());
        ErrorResposta error = new ErrorResposta(422, "Unprocessable Entity", ex.getMessage(), data);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResposta> handleResourceBadRequestException(ResourceBadRequestException ex) {
        String data = ConversorData.converterDataParaDataHora(new Date());
        ErrorResposta error = new ErrorResposta(400, "Bad Request", ex.getMessage(), data);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResposta> handleExceptions(Exception ex) {
        String data = ConversorData.converterDataParaDataHora(new Date());
        ErrorResposta error = new ErrorResposta(500, "Internal Server Error", ex.getMessage(), data);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResposta> handlerBadCredentialsException(Exception ex){

        String data =  ConversorData.converterDataParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(401, "Unauthorized", "Usuário ou senha inválidos", data);

        return new ResponseEntity<>(erro, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResposta> handlerAccessDeniedException(AccessDeniedException ex){

        String data =  ConversorData.converterDataParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(403, "Forbidden", ex.getMessage(), data);

        return new ResponseEntity<>(erro, HttpStatus.FORBIDDEN);
    }
}
