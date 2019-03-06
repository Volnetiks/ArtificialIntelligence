package com.volnetiks.ai.utils;

import com.volnetiks.ai.graphics.InterfaceRender;

public class Utils {

    public static void addMessage(InterfaceRender interfaceRender, boolean user, String... messages) {
        for (int i = 0; i < messages.length; i++) {
            interfaceRender.getMessages().add(messages[i]);
            interfaceRender.getBooleans().put(messages[i], user);
        }
    }

}
