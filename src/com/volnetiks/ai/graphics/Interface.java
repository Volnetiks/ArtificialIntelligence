package com.volnetiks.ai.graphics;

import com.volnetiks.ai.utils.UpdateInfo;
import com.volnetiks.ai.utils.Updater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Date: 12/01/2019 For Artificial Intelligence By Volnetiks */
public class Interface implements ActionListener {

    JMenu users, language;
    JMenuItem login, logout, french, polish, english;
    InterfaceRender interfaceRender;

    public Interface() {
        JMenuBar menuBar = new JMenuBar();
        users = new JMenu("User");
        language = new JMenu("Language");
        menuBar.add(users);
        menuBar.add(language);

        login = new JMenuItem("Login", new ImageIcon("users.png"));
        french = new JMenuItem("French", null);
        english = new JMenuItem("English", null);
        polish = new JMenuItem("Polish", null);
        french.addActionListener(this);
        login.addActionListener(this);

        users.add(login);
        language.add(french);
        language.add(english);
        language.add(polish);
        interfaceRender = new InterfaceRender();
        JFrame frame = new JFrame("Haku - Version 2");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("haku.png")));
        frame.setPreferredSize(new Dimension(900, 625));
        frame.setMinimumSize(new Dimension(900, 625));
        frame.setMaximumSize(new Dimension(900, 625));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(interfaceRender);
        frame.addKeyListener(interfaceRender);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        try {
            if(Double.parseDouble(Updater.getLatestVersion()) > 2) {
                new UpdateInfo(Updater.getWhatsNew());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
//            JFrame frameLogin = new JFrame("Login");
//            frameLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("users.png")));
//            frameLogin.setPreferredSize(new Dimension(450, 625/2));
//            frameLogin.setMinimumSize(new Dimension(450, 625/2));
//            frameLogin.setMaximumSize(new Dimension(450, 625/2));
//            frameLogin.setResizable(false);
//            frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frameLogin.setLocationRelativeTo(null);
//            frameLogin.setContentPane(new LoginDesign());
//            frameLogin.setVisible(true);
        } else if (e.getSource() == french) {
            interfaceRender.setLanguage("Fr");
            interfaceRender.initArray();
        } else if (e.getSource() == english) {
            interfaceRender.setLanguage("En");
            interfaceRender.initArray();
        } else if (e.getSource() == polish) {
            interfaceRender.setLanguage("Pl");
            interfaceRender.initArray();
        }
    }

}
