package com.marom.restfulwebserivces.filtering;

import com.marom.restfulwebserivces.shop.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrderFilteringController {

    @GetMapping("/orderFiltering")
    public Order retrieveOrder() {
        return new Order("Bread", 2, new Date());
    }


}
