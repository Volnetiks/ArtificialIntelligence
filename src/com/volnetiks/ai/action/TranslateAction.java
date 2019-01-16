package com.volnetiks.ai.action;

import com.gtranslate.Language;
import com.volnetiks.ai.graphics.InterfaceRender;
import com.volnetiks.ai.utils.Translator;

import java.awt.event.KeyEvent;
import java.io.IOException;

/* Date: 12/01/2019 For Artificial Intelligence By Volnetiks */
public class TranslateAction {

    public TranslateAction() {

    }

    public TranslateAction(InterfaceRender interfaceRender) {
        interfaceRender.setAction(true);
        interfaceRender.setQuestionAction("Can you translate a text?");
        interfaceRender.getMessages().add("Give me a text.");
        interfaceRender.getBooleans().put("Give me a text.", false);
    }

    public void calledKey(KeyEvent e, InterfaceRender interfaceRender, String message) {
        interfaceRender.getMessageField().setText("");
        try {
            String traduction = "Traduction: " + Translator.translate("fr", message);
            interfaceRender.getMessages().add(traduction);
            interfaceRender.getBooleans().put(traduction, false);
            interfaceRender.setAction(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}
