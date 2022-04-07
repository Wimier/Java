package com;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Counter extends JFrame {
    Controller controller=new Controller();
    byte bytes[] = new byte[512];
    JPanel jPanel1, jPanel2;
    JTextField jTextField1, jTextField2, jTextField3, jTextField4;
    JTextArea jTextArea;
    JScrollPane jScrollPane;
    JPanel buttonPanel, buttonPanel1;
    final GridLayout gridLayout;
    final String[][] names = {{"1", "2", "3", "/", "C"}, {"4", "5", "6", "*", "退格"}, {"7", "8", "9", "-", "sqrt"}, {"0", "+/-", ".", "+", "="}};
    final String[] names2 = {"保存", "查看", "清除"};
    String b = "";
    String a="";
    String c="";
    //构造方法
    public Counter() {
        super();
        setTitle("wmy的计算器");
        setResizable(false);
        setBounds(100, 100, 600, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel1.setBackground(new Color(190,244,255));
        jPanel2.setBackground(new Color(190,250,200));
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField1.setColumns(13);
        jTextField2.setColumns(6);
        jTextField3.setColumns(13);
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jTextField3);
        jTextField1.setForeground(Color.BLACK);
        jTextField1.setBackground(new Color(0,200,255));
        jTextField2.setBackground(new Color(203,185,237));
        jTextField3.setBackground(new Color(152,240,180));

        buttonPanel = new JPanel();//按钮面板
        gridLayout = new GridLayout(4, 5);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        buttonPanel.setLayout(gridLayout);
        JButton[][] button1 = new JButton[4][5];
        buttonPanel.setOpaque(false);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                button1[row][col] = new JButton(names[row][col]);
                buttonPanel.add(button1[row][col]);
                button1[row][col].addActionListener(controller);
                button1[row][col].addKeyListener(controller);
                button1[row][col].setContentAreaFilled(false);
            }
        }
        jPanel1.add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(jPanel1);

        //右面板
        jScrollPane = new JScrollPane();
        jTextField4 = new JTextField();
        jTextField4.setColumns(17);
        jTextField4.setEditable(false);//设置值不可修改
        jTextArea = new JTextArea(6, 15);
        jTextArea.setEditable(false);
        jScrollPane.setViewportView(jTextArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jTextField4.setBackground(new Color(255,200,122));

        jScrollPane.getViewport().getView().setBackground(Color.pink);
        jScrollPane.getViewport().getView().setForeground(Color.BLACK);

        buttonPanel1 = new JPanel();
        buttonPanel1.setOpaque(false);
        JButton[] button2 = new JButton[3];
        for (int i = 0; i < names2.length; i++) {
            button2[i] = new JButton(names2[i]);
            buttonPanel1.add(button2[i]);
            button2[i].addActionListener(controller);
            button2[i].setContentAreaFilled(false);
            //button2[i].setBorder(BorderFactory.createLoweredBevelBorder());'
            //button2[i].setBackground(Color.green);

        }
        jPanel2.add(jTextField4);
        jPanel2.add(jScrollPane);
        jPanel2.add(buttonPanel1);
        getContentPane().add(jPanel2);

        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.add(jPanel1, JSplitPane.LEFT);
        jSplitPane.add(jPanel2, JSplitPane.RIGHT);
        getContentPane().add(jSplitPane);
        controller.setWin(this);
    }

}
