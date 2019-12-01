package com.marom.restfulwebserivces.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.marom.restfulwebserivces.shop.Product;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDynamicFilteringController {

    @GetMapping("/getSellingPrice")
    public MappingJacksonValue retrieveProductSellingPrice() {

        String filterExcept = "sellingPrice";
        MappingJacksonValue value = createAndApplyFilter(filterExcept);
        return value;
    }

    @GetMapping("/getBuyingPrice")
    public MappingJacksonValue retrieveProductBuyingPrice() {

        MappingJacksonValue value = createAndApplyFilter("buyingPrice");
        return value;
    }

    private MappingJacksonValue createAndApplyFilter(String filterExcept) {
        Product product = new Product("Shoes", 23.5f, 22.1f);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterExcept);
        FilterProvider filters = new SimpleFilterProvider().addFilter("ProductFilter", filter);
        MappingJacksonValue value = new MappingJacksonValue(product);
        value.setFilters(filters);
        return value;
    }
}
