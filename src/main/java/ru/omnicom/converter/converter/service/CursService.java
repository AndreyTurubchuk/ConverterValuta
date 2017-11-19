package ru.omnicom.converter.converter.service;

import ru.omnicom.converter.converter.model.ExchangeRate;

public interface CursService {
   Double getCurs(String url);
   Double converterBuy(Double sum, Double curs); //покупка йен за рубли
   Double converterSell(Double sum, Double curs); //продажа
   void saveCurs(ExchangeRate exchangeRate);
   ExchangeRate getLastCurs();
   String buy(String sum);
   String sell(String sum);

}
