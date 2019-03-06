package com.volnetiks.ai.games;

public class MorpionGame {

    public MorpionGame() {
        String[] run = {"java","-jar","games/morpion.jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
