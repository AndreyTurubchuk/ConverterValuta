package ru.omnicom.converter.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.omnicom.converter.converter.model.ConvertResult;
import ru.omnicom.converter.converter.model.ExchangeRate;
import ru.omnicom.converter.converter.repositories.CursRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class CursServiceImpl implements CursService {
    private final static String STATUS_OK = "OK";
    private final static String STATUS_ERROR = "ERROR";

    @Autowired
    private CursRepository cursRepository;

    @Autowired
    private CursService cursService;

    @Override
    public Double getCurs(String url) {
        Double curs = 0.00;
        String cursString;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(url);
            NodeList nodeList = doc.getElementsByTagName("Valute");
            int i = 0;
            while (i <= nodeList.getLength()) {
                Element element = (Element) nodeList.item(i);
                int numCode = Integer.parseInt(element.getElementsByTagName("NumCode").item(0).getTextContent());
                if (numCode == 392) {
                    cursString = element.getElementsByTagName("Value").item(0).getTextContent();
                    curs = Double.parseDouble(cursString.replace(',', '.'));
                    break;
                }
                i++;
            }
            //NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curs;
    }


    @Override
    public Double converterBuy(Double sum, Double curs) {
        return sum / curs / 100;
    }

    @Override
    public Double converterSell(Double sum, Double curs) {
        return sum * curs / 100;
    }

    @Override
    public void saveCurs(ExchangeRate exchangeRate) {
        cursRepository.saveAndFlush(exchangeRate);
    }

    @Override
    public ExchangeRate getLastCurs() {
        return cursRepository.findOne(1L);
    }

    @Override
    public String buy(String sum) {
        ConvertResult convertResult = new ConvertResult();
        Double sum2;
        try {
            sum = sum.replace(',', '.');
            sum2 = Double.parseDouble(sum);
            ExchangeRate curs = cursService.getLastCurs();
            Double d = curs.getCurrencyRate();
            double result = cursService.converterBuy(sum2, d);
            convertResult.setSum(result);
            convertResult.setStatus(STATUS_OK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            convertResult.setStatus(STATUS_ERROR);
        }
        return convertResult.toString();
    }

    @Override
    public String sell(String sum) {
        ConvertResult convertResult = new ConvertResult();
        Double sum2;
        try {
            sum = sum.replace(',', '.');
            sum2 = Double.parseDouble(sum);
            ExchangeRate curs = cursService.getLastCurs();
            Double d = curs.getCurrencyRate();
            double result = cursService.converterSell(sum2, d);
            convertResult.setSum(result);
            convertResult.setStatus(STATUS_OK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            convertResult.setStatus(STATUS_ERROR);
        }
        return convertResult.toString();
    }

}
