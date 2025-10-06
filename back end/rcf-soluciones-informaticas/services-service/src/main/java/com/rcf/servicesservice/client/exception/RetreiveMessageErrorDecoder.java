package com.rcf.servicesservice.client.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcf.servicesservice.exception.ErrorResponse;
import com.rcf.servicesservice.exception.ResourceNotFound;
import com.rcf.servicesservice.exception.ServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultDecoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        String body = null;
        try (InputStream bodyIs = response.body() != null ? response.body().asInputStream() : null) {
            if (bodyIs != null) {
                byte[] bytes = bodyIs.readAllBytes();
                body = bytes.length > 0 ? new String(bytes) : null;
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el body de la respuesta: " + e.getMessage());
        }

        if (body != null) {
            try {
                ErrorResponse err = mapper.readValue(body, ErrorResponse.class);
                String mensaje = err.getMessage() != null ? err.getMessage() : "Recurso no encontrado";
                if (status == 404) {
                    return new ResourceNotFound(mensaje);
                }
                if(status == 503){
                    return new ServiceUnavailableException(mensaje);
                }
                return new RuntimeException(String.format("Feign error %d: %s", status, mensaje));
            } catch (IOException e) {
                return defaultDecoder.decode(methodKey, response);
            }
        }
        return defaultDecoder.decode(methodKey, response);
    }
}