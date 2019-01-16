package com.volnetiks.ai.graphics;

import javax.swing.*;
import java.awt.*;

/* Date: 12/01/2019 For Artificial Intelligence By Volnetiks */
public class Interface {

    public Interface() {
        JFrame frame = new JFrame("Haku - AI");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("haku.png")));
        frame.setPreferredSize(new Dimension(900, 600));
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setMaximumSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new InterfaceRender());
        frame.addKeyListener(new InterfaceRender());
        frame.setVisible(true);
    }
}
