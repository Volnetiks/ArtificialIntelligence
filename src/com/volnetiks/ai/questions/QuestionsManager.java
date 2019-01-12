package com.volnetiks.ai.questions;

import java.lang.reflect.Constructor;
import java.util.*;

/* Date: 11/01/2019 For Artificial Intelligence By Volnetiks */
public class QuestionsManager {

    List<String> questions;
    HashMap<String, Class<?>> actions;

    public QuestionsManager(List<String> questions, HashMap<String, Class<?>> actions) {
        this.questions = questions;
        this.actions = actions;
    }

    public HashMap fetchQuestionsAndFindOne(String question, List<String> q) {
        HashMap<String, Integer> array = new HashMap<>();
        int finded;
        for(String value : q) {
            String[] tab1 = question.split(" ");
            String[] tab2 = value.split(" ");
            finded = 0;
            int smallArray = tab1.length > tab2.length ? tab2.length : tab1.length;
            for(int i = 0; i < smallArray; i++) {
                if (!tab2[i].equalsIgnoreCase(tab1[i])) {
                    finded++;
                }
            }
            array.put(value, finded);
        }
        return array;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void getActionToDo(String question) {
        for(int t = 0; t < actions.size(); t++) {
            Class c = actions.get(question);
            try {
                Class classe = Class.forName(c.getName());
                Constructor constructor = classe.getConstructor(new Class[]{});
                constructor.newInstance();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
