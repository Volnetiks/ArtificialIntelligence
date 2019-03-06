package com.volnetiks.ai.utils;

import java.io.InputStream;
import java.net.URL;

/* Date: 30/01/2019 For Artificial Intelligence By Volnetiks */
public class Updater {

    private final static String versionURL = "file:///D:/Developpement/Code/Projet Perso - Non Team/Web/Haku - WebSite/version/version.html";
    private final static String historyURL = "file:///D:/Developpement/Code/Projet Perso - Non Team/Web/Haku - WebSite/version/history.html";

    public static String getLatestVersion() throws Exception {
        String data = getData(versionURL);

        return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));
    }

    public static String getWhatsNew() throws Exception
    {
        String data = getData(historyURL);
        return data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }

    private static String getData(String address)throws Exception
    {
        URL url = new URL(address);

        InputStream html;

        html = url.openStream();

        int c = 0;
        StringBuffer buffer = new StringBuffer();

        while(c != -1) {
            c = html.read();

            buffer.append((char)c);
        }
        return buffer.toString();
    }

}
