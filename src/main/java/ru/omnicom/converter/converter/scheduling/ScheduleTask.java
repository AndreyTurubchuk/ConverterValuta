//запись в БД курса валюты с периодичностью 1 час

package ru.omnicom.converter.converter.scheduling;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.omnicom.converter.converter.model.ExchangeRate;
import ru.omnicom.converter.converter.service.CursService;

@Component
public class ScheduleTask {

    private static final String URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    @Autowired
    private CursService cursService;

    @Scheduled(fixedDelay = 36000000)
    public void fixedDelaySchedule() {
        Double d = cursService.getCurs(URL);
        ExchangeRate cursVal = new ExchangeRate(d);
        cursService.saveCurs(cursVal);
    }
}
