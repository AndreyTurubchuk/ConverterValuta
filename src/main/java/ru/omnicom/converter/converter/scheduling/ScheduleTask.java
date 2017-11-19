//запись в БД курса валюты с периодичностью 1 час

package ru.omnicom.converter.converter.scheduling;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.omnicom.converter.converter.model.ExchangeRate;
import ru.omnicom.converter.converter.service.CursService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class ScheduleTask {

  //  private static final String URL0 = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=18/11/2017&date_req2=18/11/2017&VAL_NM_RQ=R01820";
    private static final String URL1 = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=";
    private static final String URL2 = "&date_req2=";
    private static final String URL3 = "&VAL_NM_RQ=R01820";
    private static final String URL11 = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=18/11/2017";
    private static final String URL21 = "&date_req2=18/11/2017";
    private static final String URL31 = "&VAL_NM_RQ=R01820";

    @Autowired
    private CursService cursService;

    @Scheduled(fixedDelay = 36000000)
    public void fixedDelaySchedule() {
      SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
      String URL0 = URL11 + formatterDate.format(new Date()) + URL21 + formatterDate.format(new Date()) + URL31;
      ExchangeRate cursVal = new ExchangeRate(cursService.getCurs(URL0));
      cursService.saveCurs(cursVal);

    }
}
