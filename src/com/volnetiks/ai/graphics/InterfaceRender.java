package com.volnetiks.ai.graphics;

import com.volnetiks.ai.action.DefinitionAction;
import com.volnetiks.ai.action.GameAction;
import com.volnetiks.ai.action.TranslateAction;
import com.volnetiks.ai.questions.QuestionsManager;
import com.volnetiks.ai.questions.ValueComparator;
import com.volnetiks.ai.sql.SQLConnection;
import com.volnetiks.ai.utils.Utils;
import secrets.SecretValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

/* Date: 12/01/2019 For Artificial Intelligence By Volnetiks */
public class InterfaceRender extends JPanel implements KeyListener {

    private JTextField messageField = new JTextField();
    private JLabel message = new JLabel("Talk with Haku");
    private HashMap<String, Boolean> booleans = new HashMap<>();
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<JLabel> labels = new ArrayList<>();
    private HashMap<String, String> questions;
    private HashMap<String, Class<?>> actions;
    private QuestionsManager questionsManager;
    private boolean action;
    private String questionAction;
    private SecretValue secretValue;
    private String language;
    private HashMap<String, String> messagesAi;

    public InterfaceRender() {
        setLanguage("En");
        initArray();
        this.setLayout(null);
        action = false;

        secretValue = new SecretValue();
        SQLConnection sqlConnection = new SQLConnection("jdbc:mysql://", secretValue.getSqlHost(), secretValue.getSqlDatabase(), secretValue.getSqlUser(), secretValue.getSqlPass());
        sqlConnection.connection();
        sqlConnection.accesPermissionIp();

        messageField.setBounds(100, 525, 775, 25);
        messageField.setForeground(Color.BLACK);
        messageField.setFont(messageField.getFont().deriveFont(20F));
        messageField.setCaretColor(Color.WHITE);
        messageField.setOpaque(false);
        messageField.addKeyListener(this);

        message.setForeground(Color.BLACK);
        message.setBounds(0, 525, 100, 25);
        message.setFont(new Font(messageField.getFont().getName(), Font.PLAIN, 15));

        this.add(messageField);
        this.add(message);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!messageField.getText().equalsIgnoreCase("")) {
                if(!hasOnlySpace(messageField.getText())) {
                    String message = messageField.getText();
                    messageField.setText("");
                    String[] split = message.split("(?<=\\G....................)");
                    Utils.addMessage(this, true, split);

                    if(!action) {
                        HashMap array;
                        array = questionsManager.fetchQuestionsAndFindOne(message);
                        if(array.isEmpty()) {
                            Utils.addMessage(this, false, "Sorry, but i dont understand");
                            return;
                        }
                        TreeMap<String, Integer> sortedMap = sortMapByValue(array);
                        questionsManager.getActionToDo(sortedMap.firstKey(), this);
                    } else {
                        Class c = actions.get(getQuestionAction());
                        try {
                            Class classe = Class.forName(c.getName());
                            Constructor constructor = classe.getConstructor(new Class[]{});
                            Object classes = constructor.newInstance();
                            Method calledKey = classes.getClass().getDeclaredMethod("calledKey", KeyEvent.class, InterfaceRender.class, String.class);
                            calledKey.invoke(classes, e, this, message);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void renderMessages(ArrayList<String> messages, HashMap<String, Boolean> booleans, ArrayList<JLabel> labels, FontMetrics fontMetrics) {
        if(messages.size() > 10) {
            messages.remove(0);
        }
        initJlabel(messages, labels);
        int heigth = 25;
        int v = 0;
        for(String m : messages) {
            initJlabel(messages, labels);
            JLabel labelM = labels.get(v);
            boolean user = booleans.get(m);
            int width = user ? 850 : 25;
            int widthM = fontMetrics.stringWidth(m);
            if(heigth > 525) break;
            labelM.setBounds((user ? width - widthM : width), heigth, widthM, 25);
            labelM.setForeground(Color.BLACK);
            labelM.setFont(new Font(messageField.getFont().getName(), Font.PLAIN, 25));
            labelM.setText(m);
            labelM.setVisible(true);
            v++;
            heigth += 50;
        }
    }

    private void designDialogBox(Graphics graphics) {
        Image image = Toolkit.getDefaultToolkit().getImage("dialog.png");
        graphics.drawImage(image, 100, 100, this);
    }

    private boolean hasOnlySpace(String text) {
        char[] textC = text.toCharArray();
        for(char c : textC) {
            if(!(c == ' ')) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FontMetrics fontMetrics = g.getFontMetrics(new Font(messageField.getFont().getName(), Font.PLAIN, 25));
        renderMessages(messages, booleans, labels, fontMetrics);
        designDialogBox(g);
    }

    public void initArray() {
        questions = new HashMap<>();
        actions = new HashMap<>();
        try {
            Class classe = Class.forName("com.volnetiks.ai.language." + getLanguage() + "Language");
            Constructor constructor = classe.getConstructor(new Class[]{});
            Object classes = constructor.newInstance();
            Method getQuestions = classes.getClass().getDeclaredMethod("getQuestions");
            questions = (HashMap<String, String>) getQuestions.invoke(classes);
            Method getActions = classes.getClass().getDeclaredMethod("getActions");
            actions = (HashMap<String, Class<?>>) getActions.invoke(classes);
            Method getMessages = classes.getClass().getDeclaredMethod("getMessage");
            messagesAi = (HashMap<String, String>) getMessages.invoke(classes);
            questionsManager = new QuestionsManager(questions, actions, messagesAi);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        questions.add("Give me a definition");
//        questions.add("Can you translate a text?");
//        actions.put("Give me a definition", DefinitionAction.class);
//        actions.put("Can you translate a text?", TranslateAction.class);
//        questions.add("Can we play a game?");
//        actions.put("Can we play a game?", GameAction.class);
    }

    public static TreeMap<String, Integer> sortMapByValue(HashMap<String, Integer> map){
        Comparator<String> comparator = new ValueComparator(map);
        TreeMap<String, Integer> result = new TreeMap<>(comparator);
        result.putAll(map);
        return result;
    }

    public HashMap<String, Boolean> getBooleans() {
        return booleans;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void initJlabel(ArrayList<String> messages, ArrayList<JLabel> labels) {
        while(messages.size() > 10) {
            messages.remove(0);
        }
        for(int i = 0; i < (messages.size() - labels.size()); i++) {
            JLabel label = new JLabel();
            labels.add(label);
            this.add(label);
        }
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public String getQuestionAction() {
        return questionAction;
    }

    public void setQuestionAction(String questionAction) {
        this.questionAction = questionAction;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public QuestionsManager getQuestionsManager() {
        return questionsManager;
    }
}
