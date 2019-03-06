package com.volnetiks.ai.action;

import com.volnetiks.ai.graphics.InterfaceRender;
import com.volnetiks.ai.utils.Utils;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameAction {

    public GameAction() {

    }

    public GameAction(InterfaceRender interfaceRender) {
        interfaceRender.setAction(true);
        interfaceRender.setQuestionAction("Can we play a game?");
        Utils.addMessage(interfaceRender, false, "I only know how to play morpion, let's play");
    }

    public void calledKey(KeyEvent e, InterfaceRender interfaceRender, String message) {

    }

}
