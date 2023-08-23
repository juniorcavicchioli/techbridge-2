package br.com.fiap.techbridge.exceptions;

public record RestError(
    int cod,
    String message
) {
    
}
