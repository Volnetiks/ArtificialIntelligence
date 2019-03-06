package com.volnetiks.ai.language;

import com.volnetiks.ai.action.DefinitionAction;
import com.volnetiks.ai.action.GameAction;
import com.volnetiks.ai.action.TranslateAction;

import java.util.ArrayList;
import java.util.HashMap;

public class FrLanguage {

    public HashMap<String, String> message;
    public HashMap<String, Class<?>> actions;
    public HashMap<String, String> questions;

    public FrLanguage() {
        message = new HashMap<>();
        actions = new HashMap<>();
        questions = new HashMap<>();
        message.put("Give me a definition", "Donne moi une définition");
        message.put("Traduction: ", "Traduction: ");
        message.put("I only know how to play morpion, let's play", "Je sais uniquement jouer au morpion");
        questions.put("Give me a definition", "Donne moi une définition");
        questions.put("Can you translate a text?", "Peux tu me traduire un texte?");
        actions.put("Donne moi une définition", DefinitionAction.class);
        actions.put("Peux tu me traduire un texte?", TranslateAction.class);
        questions.put("Can we play a game?", "On peux jouer a un jeu?");
        actions.put("On peux jouer a un jeu?", GameAction.class);
        message.put("Give me a word", "Donne moi un mot");
    }

    public HashMap<String, Class<?>> getActions() {
        return actions;
    }

    public HashMap<String, String> getMessage() {
        return message;
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }
}
