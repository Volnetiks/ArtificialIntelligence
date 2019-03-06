package com.volnetiks.ai.language;

import com.volnetiks.ai.action.DefinitionAction;
import com.volnetiks.ai.action.GameAction;
import com.volnetiks.ai.action.TranslateAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnLanguage {

    public HashMap<String, String> message;
    public HashMap<String, Class<?>> actions;
    public HashMap<String, String> questions;

    public EnLanguage() {
        message = new HashMap<>();
        actions = new HashMap<>();
        questions = new HashMap<>();
        message.put("Give me a definition", "Give me a definition");
        message.put("Traduction: ", "Traduction: ");
        message.put("I only know how to play morpion, let's play", "I only know how to play morpion, let's play");
        questions.put("Give me a definition", "Give me a definition");
        questions.put("Can you translate a text?", "Can you translate a text?");
        actions.put("Give me a definition", DefinitionAction.class);
        actions.put("Can you translate a text?", TranslateAction.class);
        questions.put("Can we play a game?", "Can we play a game?");
        actions.put("Can we play a game?", GameAction.class);
        message.put("Give me a word", "Give me a word");
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }

    public HashMap<String, Class<?>> getActions() {
        return actions;
    }

    public HashMap<String, String> getMessage() {
        return message;
    }
}
