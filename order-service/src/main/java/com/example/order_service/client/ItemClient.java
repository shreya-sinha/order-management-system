package com.example.order_service.client;

import com.example.order_service.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@FeignClient(value = "item", url = "${item.service.url}", fallback=ItemClientFallback.class)
public interface ItemClient {

    @GetMapping()
    ResponseEntity<Collection<Item>> get(@RequestParam(value = "productCode") List<Integer> productCodes);


}
