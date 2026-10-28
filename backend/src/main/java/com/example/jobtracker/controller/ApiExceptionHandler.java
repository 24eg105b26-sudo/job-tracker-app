package com.example.jobtracker.controller;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import java.util.stream.Collectors;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException e){
  String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining("; "));
  return ResponseEntity.badRequest().body(Map.of("message",m.isBlank()?"Invalid request":m));
 }
 @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
 public ResponseEntity<Map<String,String>> unreadable(Exception e){return ResponseEntity.badRequest().body(Map.of("message","Invalid request body or status. Allowed statuses: Applied, Interview, Selected, Rejected."));}
 @ExceptionHandler(ResponseStatusException.class) public ResponseEntity<Map<String,String>> status(ResponseStatusException e){
  return ResponseEntity.status(e.getStatusCode()).body(Map.of("message",e.getReason()==null?"Request failed":e.getReason()));
 }
}
