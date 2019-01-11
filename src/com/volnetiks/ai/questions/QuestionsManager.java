package com.volnetiks.ai.questions;

import java.util.ArrayList;
import java.util.List;

/* Date: 11/01/2019 For Artificial Intelligence By Volnetiks */
public class QuestionsManager {

    List<String> questions = new ArrayList<>();

    public QuestionsManager(ArrayList<String> questions) {
        this.questions = questions;
    }

    public boolean fetchQuestions(String question, String questionA) {
        String[] tab1 = question.split(" ");
        String[] tab2 = questionA.split(" ");
        int finded = 0;
        if(tab1.length - tab2.length >= 4) return false;
        for(int i = 0; i < tab1.length; i++) {
            if(!tab2[i].equalsIgnoreCase(tab1[i])){
                finded++;
            }
        }
        if(finded >= 2) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getQuestions() {
        return questions;
    }
}
