package org.example;
import javax.swing.*;
import  javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.math.BigDecimal;

public class guiBuilder {
    //GUI Implementation - starts here
    static JFrame currencyConverterWindow;
    static JComboBox currencyContainerOne, currencyContainerTwo;
    static JButton convertButton;
    static JPanel objectHolder;
    static JPanel textFieldHolder;
    static JTextField intakeNumber;
    static JLabel convertedPrice;
    static BigDecimal calculatedPrice;
    String stringValueOfCalculatedPrice;

    static final String fromCurrencies[] = {
            "From",
            "FJD", "MXN", "SCR", "TVD", "CDF", "BBD", "GTQ", "CLP", "HNL", "UGX", "ZAR", "TND", "STN", "SLE", "BSD", "SLL",
            "SDG", "IQD", "CUP", "GMD", "TWD", "RSD", "DOP", "KMF", "MYR", "FKP", "XOF", "GEL", "UYU", "MAD", "CVE", "TOP",
            "AZN", "OMR", "PGK", "KES", "SEK", "BTN", "UAH", "GNF", "ERN", "MZN", "ARS", "QAR", "IRR", "CNY", "THB", "UZS",
            "XPF", "MRU", "BDT", "LYD", "BMD", "KWD", "PHP", "RUB", "PYG", "ISK", "JMD", "COP", "USD", "MKD", "DZD", "PAB",
            "GGP", "SGD", "ETB", "JEP", "KGS", "SOS", "VUV", "LAK", "BND", "XAF", "LRD", "CHF", "HRK", "ALL", "DJF", "VES",
            "ZMW", "TZS", "VND", "AUD", "ILS", "GHS", "GYD", "BOB", "KHR", "MDL", "IDR", "KYD", "AMD", "BWP", "SHP", "TRY",
            "LBP", "TJS", "JOD", "AED", "HKD", "RWF", "EUR", "FOK", "LSL", "DKK", "CAD", "KID", "BGN", "MMK", "MUR", "NOK",
            "SYP", "IMP", "ZWL", "GIP", "RON", "LKR", "NGN", "CRC", "CZK", "PKR", "XCD", "ANG", "HTG", "BHD", "KZT", "SRD",
            "SZL", "SAR", "TTD", "YER", "MVR", "AFN", "INR", "AWG", "KRW", "NPR", "JPY", "MNT", "AOA", "PLN", "GBP", "SBD",
            "BYN", "HUF", "BIF", "MWK", "MGA", "XDR", "BZD", "BAM", "EGP", "MOP", "NAD", "SSP", "NIO", "PEN", "NZD", "WST",
            "TMT", "BRL",
    };
    static final String toCurrencies[] = {
            "To",
            "FJD", "MXN", "SCR", "TVD", "CDF", "BBD", "GTQ", "CLP", "HNL", "UGX", "ZAR", "TND", "STN", "SLE", "BSD", "SLL",
            "SDG", "IQD", "CUP", "GMD", "TWD", "RSD", "DOP", "KMF", "MYR", "FKP", "XOF", "GEL", "UYU", "MAD", "CVE", "TOP",
            "AZN", "OMR", "PGK", "KES", "SEK", "BTN", "UAH", "GNF", "ERN", "MZN", "ARS", "QAR", "IRR", "CNY", "THB", "UZS",
            "XPF", "MRU", "BDT", "LYD", "BMD", "KWD", "PHP", "RUB", "PYG", "ISK", "JMD", "COP", "USD", "MKD", "DZD", "PAB",
            "GGP", "SGD", "ETB", "JEP", "KGS", "SOS", "VUV", "LAK", "BND", "XAF", "LRD", "CHF", "HRK", "ALL", "DJF", "VES",
            "ZMW", "TZS", "VND", "AUD", "ILS", "GHS", "GYD", "BOB", "KHR", "MDL", "IDR", "KYD", "AMD", "BWP", "SHP", "TRY",
            "LBP", "TJS", "JOD", "AED", "HKD", "RWF", "EUR", "FOK", "LSL", "DKK", "CAD", "KID", "BGN", "MMK", "MUR", "NOK",
            "SYP", "IMP", "ZWL", "GIP", "RON", "LKR", "NGN", "CRC", "CZK", "PKR", "XCD", "ANG", "HTG", "BHD", "KZT", "SRD",
            "SZL", "SAR", "TTD", "YER", "MVR", "AFN", "INR", "AWG", "KRW", "NPR", "JPY", "MNT", "AOA", "PLN", "GBP", "SBD",
            "BYN", "HUF", "BIF", "MWK", "MGA", "XDR", "BZD", "BAM", "EGP", "MOP", "NAD", "SSP", "NIO", "PEN", "NZD", "WST",
            "TMT", "BRL",
    };


    /* Creating the Event Listener, and registering the convert button to it */
    public void buttonListeners() {

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entry = intakeNumber.getText();
                BigDecimal amount = new BigDecimal(entry);
                calculatedPrice = conversionLogic(currencyContainerOne.getSelectedItem(),currencyContainerTwo.getSelectedItem(),amount);
                stringValueOfCalculatedPrice = calculatedPrice.toString();
                convertedPrice.setText("Converted Amount: " + stringValueOfCalculatedPrice);
            }
        };

        convertButton.addActionListener(buttonListener);
    }
    /* The logic that converts the currencies */
    /* this method takes three arguments, and returns one value to be displayed in the GUI */
    public BigDecimal conversionLogic(Object fromCurrency, Object toCurrency, BigDecimal amount) {
        String urlString = "https://v6.exchangerate-api.com/v6/576ce06e42daefae6506b029/latest/" + fromCurrency;
        String to = (String) toCurrency;
        BigDecimal qty = new BigDecimal(String.valueOf(amount));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String stringResponse = response.body().string();
            JSONObject jsonObject = new JSONObject(stringResponse);
            JSONObject ratesObject = jsonObject.getJSONObject("conversion_rates");
            BigDecimal rate = ratesObject.getBigDecimal(to.toUpperCase());
            System.out.println(rate.multiply(qty));
            return rate.multiply(qty);

        } catch (IOException ex) {
            throw new RuntimeException("Failed to fetch conversion rates.");
        }
    }

    public guiBuilder() {
        /* UI elements are being initialized, and containerized into the JPanel called objectHolder */

        currencyConverterWindow = new JFrame();
        intakeNumber = new JTextField(10);
        convertButton = new JButton("Convert");
        currencyContainerOne = new JComboBox(fromCurrencies);
        currencyContainerTwo = new JComboBox(toCurrencies);
        /* Keeping the dropdowns at index 0 */
        currencyContainerOne.setSelectedIndex(0);
        currencyContainerTwo.setSelectedIndex(0);
        /* to hold all the GUI components */
        objectHolder = new JPanel();
        objectHolder.add(currencyContainerOne);
        objectHolder.add(currencyContainerTwo);
        objectHolder.add(convertButton);
        /* keeping the textfield that takes in the amount in a different container for formatting purposes */
        textFieldHolder = new JPanel();
        textFieldHolder.add(intakeNumber);
        objectHolder.add(textFieldHolder);
        objectHolder.add(intakeNumber);
        convertedPrice = new JLabel("Converted Amount: ");
        objectHolder.add(convertedPrice);
        /* adding everything to the JFrame component & prepping it for viewing */
        currencyConverterWindow.add(objectHolder);
        currencyConverterWindow.setSize(400,100);
        currencyConverterWindow.setTitle("Converter");
        currencyConverterWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currencyConverterWindow.setVisible(true);

    }

}

