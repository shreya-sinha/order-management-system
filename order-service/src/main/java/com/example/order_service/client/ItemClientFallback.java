package com.example.order_service.client;

import com.example.order_service.entity.Item;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ItemClientFallback implements FallbackFactory<ItemClient> {

    @Override
    public ItemClient create(Throwable throwable) {
        return new ItemClient() {
            final Logger logger = LoggerFactory.getLogger(ItemClient.class);
            @Override
            public ResponseEntity<Collection<Item>> get(List<Integer> productCodes) {
                logger.error("Error communicating with item service. Request to get Product Codes {}",
                        productCodes == null ? "All" : productCodes.toString());
                return new ResponseEntity(throwable.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
            }
        };
    }
}
