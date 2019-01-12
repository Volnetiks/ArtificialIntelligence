package com.volnetiks.ai.action;

import org.json.JSONArray;
import org.json.JSONObject;
import secrets.SecretValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/* Date: 11/01/2019 For Artificial Intelligence By Volnetiks */
public class DefinitionAction {

    public DefinitionAction() {
        System.out.println("Give me a word");
        Scanner sc = new Scanner(System.in);
        String word = sc.next().toLowerCase();
        String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/" + word;
        String json = callApi(url);
        String definition = parseJSONDefinition(json);
        System.out.println("Definition: " + definition);
    }

    private String parseJSONDefinition(String json) {
        JSONObject js = new JSONObject(json);
        JSONArray results = js.getJSONArray("results");
        JSONObject lEntries = results.getJSONObject(0);
        JSONArray lArray = lEntries.getJSONArray("lexicalEntries");
        JSONObject entries = lArray.getJSONObject(0);
        JSONArray eArray = entries.getJSONArray("entries");
        JSONObject sObject = eArray.getJSONObject(0);
        JSONArray sArray = sObject.getJSONArray("senses");
        JSONObject d = sArray.getJSONObject(0);
        JSONArray de = d.getJSONArray("definitions");
        return de.getString(0);
    }

    private String callApi(String urlW) {
        try {
            SecretValue secretValue = new SecretValue();
            URL url = new URL(urlW);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", secretValue.getOxfordAppId());
            urlConnection.setRequestProperty("app_key", secretValue.getOxfordAppKey());

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            return "Word cant be found.";
        }
    }
}
