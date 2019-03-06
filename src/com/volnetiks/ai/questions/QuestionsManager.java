package com.volnetiks.ai.questions;

import com.volnetiks.ai.graphics.InterfaceRender;

import java.lang.reflect.Constructor;
import java.util.*;

/* Date: 11/01/2019 For Artificial Intelligence By Volnetiks */
public class QuestionsManager {

    HashMap<String, String> questions;
    HashMap<String, Class<?>> actions;
    HashMap<String, String> messages;

    public QuestionsManager(HashMap<String, String> questions, HashMap<String, Class<?>> actions, HashMap<String, String> messages) {
        this.questions = questions;
        this.actions = actions;
        this.messages = messages;
    }

    public HashMap fetchQuestionsAndFindOne(String question) {
        HashMap<String, Integer> array = new HashMap<>();
        questions.forEach((x, value) -> {
            int finded;
            char[] valueC = value.toLowerCase().toCharArray();
            String[] questionW = question.toLowerCase().split(" ");
            String[] charW = value.toLowerCase().split(" ");
            int smallS = questionW.length > charW.length ? charW.length : questionW.length;

            finded = 0;
            for (int i = 0; i < smallS; i++) {
                char[] wordC = questionW[i].toCharArray();
                char[] charC = charW[i].toCharArray();
                int smallArray = wordC.length > charC.length ? charC.length : wordC.length;
                for(int j = 0; j < smallArray; j++) {
                    if(charC[j] != wordC[j]) {
                        finded++;
                    }
                }
            }
            int pourcent = Math.round(valueC.length * 80 / 100);
            if(finded <= (valueC.length - pourcent)) {
                array.put(value, finded);
            }
        });
//        for(String value : questions) {
//            char[] valueC = value.toLowerCase().toCharArray();
//            String[] questionW = question.toLowerCase().split(" ");
//            String[] charW = value.toLowerCase().split(" ");
//            int smallS = questionW.length > charW.length ? charW.length : questionW.length;
//
//            finded = 0;
//            for (int i = 0; i < smallS; i++) {
//                char[] wordC = questionW[i].toCharArray();
//                char[] charC = charW[i].toCharArray();
//                int smallArray = wordC.length > charC.length ? charC.length : wordC.length;
//                for(int j = 0; j < smallArray; j++) {
//                    if(charC[j] != wordC[j]) {
//                        finded++;
//                    }
//                }
//            }
//            int pourcent = Math.round(valueC.length * 80 / 100);
//            if(finded <= (valueC.length - pourcent)) {
//                array.put(value, finded);
//            }
//        }
        return array;
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }

    public void getActionToDo(String question, InterfaceRender interfaceRender) {
        Class c = actions.get(question);
        try {
            Class classe = Class.forName(c.getName());
            Constructor constructor = classe.getConstructor(new Class[]{ InterfaceRender.class });
            constructor.newInstance(interfaceRender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }
}
