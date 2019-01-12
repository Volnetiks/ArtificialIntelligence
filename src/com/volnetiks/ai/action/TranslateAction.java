package com.volnetiks.ai.action;

import com.volnetiks.ai.utils.Translator;

import java.io.IOException;
import java.util.Scanner;

/* Date: 12/01/2019 For Artificial Intelligence By Volnetiks */
public class TranslateAction {

    public TranslateAction() {
        System.out.println("Give me a text.");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        try {
            System.out.println(Translator.translate("fr", text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
