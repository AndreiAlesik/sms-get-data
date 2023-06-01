package org.example;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashSet;
import java.util.Set;

public class Main {
    static String otoDomUrll = "https://www.olx.pl/nieruchomosci/mieszkania/?page=1&search%5Bprivate_business%5D=business";

    public static void loadAnnouncements() {
        String otoDomUrl = otoDomUrll;
        if (otoDomUrl.contains("page=1")) {
            for (int i = 1; i <= 26; i++) {
                System.out.println("STRONA NUMER: " + i);
                String page = "page=" + i;
                otoDomUrl = otoDomUrl.replace("page=1", page);
                String html=getPageData(otoDomUrl);
                Set<String> offers = getPropertyAnnouncementUrl(html);
                for (String links : offers
                ) {

                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\olesi\\OneDrive\\Documents\\GitHub\\property-sms-app\\src\\main\\resources\\driver\\chromedriver.exe");

                    // Inicjalizacja obiektu WebDriver
                    WebDriver driver = new ChromeDriver();
                    // Otwarcie strony
                    driver.get(links);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Znalezienie elementu zawierającego adres e-mail
                    String pageSource = driver.getPageSource();
                    String email = extractEmailFromPageSource(pageSource);
                    System.out.println("Nazwa firmy: " + extractCompanyName(pageSource));
                    System.out.println("Adres e-mail: " + email);
                    System.out.println("Numer telefonu: " + extractPhoneNumber(pageSource));


//                driver.quit();
                }
            }
        }
    }

    private static Set<String> getPropertyAnnouncementUrl(String page) {
        String pattern = "\"slug\":\"";
        Set<String> result = new HashSet<>();
        if (page != null && page.length() > 0) {
            for (int i = 0; i < page.length() - 100; i++) {
                String search = page.substring(i, i + 8);
                String flat = "";
                if (search.equals(pattern)) {
                    int j = i + 8;
                    while (page.charAt(j) != '"') {
                        flat += page.charAt(j);
                        j++;
                    }
                    result.add("https://www.otodom.pl/pl/oferta/" + flat);
                }
            }
            return result;
        }
        return new HashSet<>();
    }

    public static void main(String[] args) {

//        // Ustawienie ścieżki do sterownika przeglądarki Chrome
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\olesi\\OneDrive\\Documents\\GitHub\\property-sms-app\\src\\main\\resources\\driver\\chromedriver.exe");
//
//        // Inicjalizacja obiektu WebDriver
//        WebDriver driver = new ChromeDriver();
//        // Otwarcie strony
//        driver.get(temp);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // Znalezienie elementu zawierającego adres e-mail
//        String pageSource = driver.getPageSource();
//        String email = extractEmailFromPageSource(pageSource);
//        System.out.println("Nazwa firmy: " + extractCompanyName(pageSource));
//        System.out.println("Adres e-mail: " + email);
//        System.out.println("Numer telefonu: " + extractPhoneNumber(pageSource));
//
//
//        driver.quit();
        loadAnnouncements();
    }



    private static String extractEmailFromPageSource(String pageSource) {
        String email = "";

        // Znajdź indeks początku sekwencji "E-mail:"
        int startIndex = pageSource.indexOf("E-mail:");
        if (startIndex != -1) {
            // Znajdź indeks początku adresu e-mail po sekwencji "E-mail:"
            int emailStartIndex = startIndex + "E-mail:".length();

            // Znajdź indeks końca znacznika </span>
            int spanEndIndex = pageSource.indexOf("</span>", emailStartIndex);

            if (spanEndIndex != -1) {
                // Znajdź indeks początku adresu e-mail po znaczniku </span>
                int emailTextStartIndex = spanEndIndex + "</span>".length();

                // Znajdź indeks końca znacznika </p>
                int emailEndIndex = pageSource.indexOf("</p>", emailTextStartIndex);

                if (emailEndIndex != -1) {
                    // Wyciągnij adres e-mail na podstawie indeksów
                    email = pageSource.substring(emailTextStartIndex, emailEndIndex).trim();
                }
            }
        }

        return email;
    }

    private static String extractCompanyName(String pageSource) {
        String companyName = "";

        // Znajdź indeks początku sekwencji "Nazwa firmy:"
        int startIndex = pageSource.indexOf("Nazwa firmy:");
        if (startIndex != -1) {
            // Znajdź indeks początku nazwy firmy po sekwencji "Nazwa firmy:"
            int companyNameStartIndex = startIndex + "Nazwa firmy:".length();

            // Znajdź indeks końca znacznika </span>
            int spanEndIndex = pageSource.indexOf("</span>", companyNameStartIndex);

            if (spanEndIndex != -1) {
                // Znajdź indeks początku nazwy firmy po znaczniku </span>
                int companyNameTextStartIndex = spanEndIndex + "</span>".length();

                // Znajdź indeks końca znacznika </p>
                int companyNameEndIndex = pageSource.indexOf("</p>", companyNameTextStartIndex);

                if (companyNameEndIndex != -1) {
                    // Wyciągnij nazwę firmy na podstawie indeksów
                    companyName = pageSource.substring(companyNameTextStartIndex, companyNameEndIndex).trim();
                }
            }
        }

        return companyName;
    }

    private static String extractPhoneNumber(String pageSource) {
        String phoneNumber = "";

        // Znajdź indeks początku sekwencji "Numer telefonu:"
        int startIndex = pageSource.indexOf("Numer telefonu:");
        if (startIndex != -1) {
            // Znajdź indeks początku numeru telefonu po sekwencji "Numer telefonu:"
            int phoneNumberStartIndex = startIndex + "Numer telefonu:".length();

            // Znajdź indeks końca znacznika </span>
            int spanEndIndex = pageSource.indexOf("</span>", phoneNumberStartIndex);

            if (spanEndIndex != -1) {
                // Znajdź indeks początku numeru telefonu po znaczniku </span>
                int phoneNumberTextStartIndex = spanEndIndex + "</span>".length();

                // Znajdź indeks końca znacznika </p>
                int phoneNumberEndIndex = pageSource.indexOf("</p>", phoneNumberTextStartIndex);

                if (phoneNumberEndIndex != -1) {
                    // Wyciągnij numer telefonu na podstawie indeksów
                    phoneNumber = pageSource.substring(phoneNumberTextStartIndex, phoneNumberEndIndex).trim();

                    // Usuń spacje z numeru telefonu
                    phoneNumber = phoneNumber.replaceAll("\\s+", "");

                    phoneNumber = phoneNumber.replace("+48", "");
                }
            }
        }

        return phoneNumber;
    }


    public static String getPageData(String url) {
        Document document = getDocument(url);
        if(document == null){
            return null;
        }

        return document.outerHtml();
    }

    public static Document getDocument(String url) {
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (Exception e) {
            System.out.println("SITE IS PROTECTED");
            return null;
        }
        return document;
    }
}

