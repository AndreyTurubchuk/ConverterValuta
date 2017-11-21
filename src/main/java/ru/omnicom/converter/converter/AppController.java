package ru.omnicom.converter.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.omnicom.converter.converter.service.CursService;

@RestController
@RequestMapping(value = "/yconverter")
public class AppController {

    @Autowired
    private CursService cursService;

    @GetMapping("/buy")
    public String buy(@RequestParam(value = "sum", required = false, defaultValue = "0.00") String sum) {
        return cursService.buy(sum);
    }

    @GetMapping("/sell")
    public String sell(@RequestParam(value = "sum", required = false, defaultValue = "0.00") String sum) {
        return cursService.sell(sum);

    }

}



