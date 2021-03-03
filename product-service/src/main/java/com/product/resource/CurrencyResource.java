package com.product.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeMath.round;

/* Fake Currency convertor since given currency conversion was paid */
@RestController
@RequestMapping("/currency")
public class CurrencyResource {

    private static DecimalFormat df = new DecimalFormat("0.00");
    private Map<String, Double> mapCurrenyCodeToUSDCurrencyValue = new HashMap<>();

    // Get All Currency Conversion as per default USD price value
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/convert/{usdPrice}/{convertTo}", method = RequestMethod.GET)
    public ResponseEntity<?> currencyConvertor(@PathVariable String convertTo, @PathVariable Double usdPrice) {
        //Create Product
        mapCurrenyCodeToUSDCurrencyValue.put("CAD", 1.28);
        mapCurrenyCodeToUSDCurrencyValue.put("EUR", 0.83);
        mapCurrenyCodeToUSDCurrencyValue.put("GBP", 0.73);

        double currentValue = mapCurrenyCodeToUSDCurrencyValue.get(convertTo);
        double finalValue = currentValue * usdPrice;
        double roundFinalValue = (double) Math.round(finalValue);
        return new ResponseEntity<>(df.format(finalValue), HttpStatus.OK);
    }
}
