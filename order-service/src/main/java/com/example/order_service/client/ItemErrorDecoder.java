package com.example.order_service.client;

import com.example.order_service.exception.ItemNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ItemErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            return new ItemNotFoundException(response.body().toString());
        }
        return defaultErrorDecoder.decode(s, response);
    }
}
