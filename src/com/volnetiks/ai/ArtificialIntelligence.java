package com.volnetiks.ai;

import com.volnetiks.ai.questions.QuestionsManager;

import java.util.ArrayList;
import java.util.Scanner;

/* Date: 11/01/2019 For Artificial Intelligence By Volnetiks */
public class ArtificialIntelligence {

    public static void main(String[] args) {
        ArrayList<String> questions = new ArrayList<>();
        questions.add("Give me a definition");
        questions.add("How are you?");
        questions.add("What's your name?");
        System.out.println("Please, ask me something.");
        Scanner sc = new Scanner(System.in);
        String question = sc.next();
        QuestionsManager questionsManager = new QuestionsManager(questions);
        int i = 0;
        while(i < questionsManager.getQuestions().size()) {
            if (questionsManager.fetchQuestions(question, questionsManager.getQuestions().get(i))) {
                System.out.println("Found.");
                break;
            }
            i++;
        }
    }

}
