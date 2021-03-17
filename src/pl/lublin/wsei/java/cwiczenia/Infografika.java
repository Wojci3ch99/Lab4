package pl.lublin.wsei.java.cwiczenia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infografika {
    public String  tytul;
    public String adresStrony;
    public String adresGrafiki;
    public String adresMiniaturki;
    public int szerokosc;
    public int wysokosc;

    public Infografika (String tekst){
        Pattern pat = Pattern.compile("<title><!\\[CDATA\\[(.*)\\]\\]");
        Matcher m = pat.matcher(tekst);
        if (m.find())
            tytul = m.group(1);
        else
            tytul = "";

        pat = Pattern.compile("<link>(.*)</link>");
        m = pat.matcher(tekst);
        if (m.find())
            adresStrony = m.group(1);
        else
            adresStrony = "";

        pat = Pattern.compile("<media:content url=\\\"(.*?)\\\"");
        m = pat.matcher(tekst);
        if (m.find())
            adresGrafiki = m.group(1);
        else
            adresGrafiki = "";

        pat = Pattern.compile("<media:thumbnail url=\\\"(.*?)\\\"");
        m = pat.matcher(tekst);
        if (m.find())
            adresMiniaturki = m.group(1);
        else
            adresMiniaturki = "";

        pat = Pattern.compile("width=\\\"(.*?)\\\" height=\\\"(.*?)\\\"");
        m = pat.matcher(tekst);
        if (m.find())
            szerokosc = Integer.parseInt(m.group(1));
            wysokosc = Integer.parseInt(m.group(2));
    }
    public void print(){
        System.out.println("Infografika: ");
        System.out.println("    tytul: " + tytul);
        System.out.println("    adres strony: " + adresStrony);
        System.out.println("    adres grafiki: " + adresGrafiki);
        System.out.println("    adresMiniaturki: " + adresMiniaturki);
        System.out.println("    rozmiar: " + szerokosc + "x" + wysokosc);
        System.out.println("");
    }
}

