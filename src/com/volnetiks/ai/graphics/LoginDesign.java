package com.volnetiks.ai.graphics;

import javax.swing.*;
import java.awt.*;

/* Date: 22/01/2019 For Artificial Intelligence By Volnetiks */
public class LoginDesign extends JPanel {

    JTextField usernameF = new JTextField();
    JLabel usernameL = new JLabel("Username");
    JLabel passwordL = new JLabel("Password");
    JTextField passwordF = new JTextField();

    public LoginDesign() {

        usernameF.setBounds(250, 250, 100, 50);
        usernameF.setForeground(Color.BLACK);
        usernameF.setFont(usernameF.getFont().deriveFont(20F));
        usernameF.setCaretColor(Color.WHITE);
        usernameF.setOpaque(false);

        passwordF.setBounds(25, 200, 100, 50);
//
//        this.add(passwordL);
        this.add(passwordF);
        this.add(usernameF);
//        this.add(usernameL);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();

        int w = 450;
        int h = 625/2;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        graphics2D.setPaint(new GradientPaint(0, 0, Color.decode("#4158d0").darker().darker().darker(), w, 0, Color.decode("#c850c0").darker().darker()));
        graphics2D.fillRect(0, 0, w, h);
        graphics2D.dispose();
    }
}
