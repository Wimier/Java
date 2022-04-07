package com;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
public class Controller implements ActionListener, KeyListener {
    Calculator calculator=new Calculator();
    Counter win;
    String aa="";
    String cc="";
    public void setWin(Counter temp){
        win=temp;
    }
    //键盘事件
    public void keyPressed(KeyEvent j){
        int i=j.getKeyCode();
        char c1=j.getKeyChar();
        if(i==KeyEvent.VK_C)
        {
            win.b = "";
            aa="";
            cc="";
            win.jTextField1.setText(null);
            win.jTextField2.setText(null);
            win.jTextField3.setText(null);
            win.jTextField4.setText("0");
        }
        else if(i==KeyEvent.VK_BACK_SPACE)
        {
            if(!win.jTextField1.getText().isEmpty()&&win.jTextField2.getText().isEmpty()&&win.jTextField3.getText().isEmpty())
            {
                int l=win.b.length();
                String s=win.b.substring(0,l-1);
                win.jTextField1.setText(s);
                win.b=s;
                cc=win.b;
            }
            else  if(!win.jTextField1.getText().isEmpty()&&!win.jTextField2.getText().isEmpty()&&win.jTextField3.getText().isEmpty())
            {
                String yuanshuju=win.b.substring(0,cc.length());
                int l=win.b.length();
                int l1=cc.length();////c是jTextField1里的数据
                String s=win.b.substring(l1,l-1);
                win.jTextField2.setText(s);
                win.b=yuanshuju+s;
            }
            else if(!win.jTextField1.getText().isEmpty()&&!win.jTextField2.getText().isEmpty()&&!win.jTextField3.getText().isEmpty())
            {
                String yuanshuju=win.b.substring(0,cc.length()+1);
                int l=win.b.length();
                int l1=cc.length()+1;
                String s=win.b.substring(l1,l-1);
                win.jTextField3.setText(s);
                aa=s;//a是jTextField3里的数据
                win.b=yuanshuju+s;
            }
        }
        else if(i==KeyEvent.VK_ENTER)
        {
            String []s=calculator.HouZHui(win.b);
            String result= calculator.Result(s);
            win.b=result+"";
            win.jTextField4.setText(win.b);
            //jTextArea.setText(jTextArea.getText()==+"="+jTextField4.getText()+"\n");
            win.jTextArea.setText(win.jTextArea.getText()+win.jTextField1.getText()+win.jTextField2.getText()+win.jTextField3.getText()+"="+win.jTextField4.getText()+"\n");
        }
        else
        {
            win.b+=c1;
            if(i==KeyEvent.VK_ADD||i==KeyEvent.VK_MINUS||i==KeyEvent.VK_MULTIPLY||i==KeyEvent.VK_DIVIDE||i==KeyEvent.VK_SUBTRACT)
            {
                win.jTextField2.setText(""+c1);
            }
            else if(win.jTextField2.getText().length()!=0&&win.jTextField1.getText().length()!=0)
            {

                aa+=c1;
                win.jTextField3.setText(aa);
            }
            else if(i!=KeyEvent.VK_ADD&&i!=KeyEvent.VK_MINUS&&i!=KeyEvent.VK_MULTIPLY&&i!=KeyEvent.VK_DIVIDE&&i!=KeyEvent.VK_SUBTRACT)
            {
                cc+=c1;
                win.jTextField1.setText(cc);
//                 if(!buttonName.equals("+/-"))
//                 {
//                     c+=buttonName;
//                     jTextField1.setText(c);
//                 }
//                 else if(buttonName.equals("+/-"))
//                 {
//                     String s = xiangFan(c);
//                     jTextField1.setText(null);
//                     jTextField1.setText(s);
//                 }
            }
            win.jTextField4.setText(win.b);
        }
    }
    public void keyReleased(KeyEvent e) {
        // TODO 自动生成方法存根
    }
    public void keyTyped(KeyEvent e) {
        // TODO 自动生成方法存根
    }
    //动作事件
    public void actionPerformed(ActionEvent e) {
        String buttonName = e.getActionCommand();
        if (buttonName.equals("C"))//清空按钮
        {
            win.b = "";
            aa="";
            cc="";
            win.jTextField1.setText(null);
            win.jTextField2.setText(null);
            win.jTextField3.setText(null);
            win.jTextField4.setText("0");
        }
        else if (buttonName.equals("sqrt"))
        {
            String s = calculator.sqrtYs(win.b);
            win.jTextField2.setText("sqrt");
            win.jTextField4.setText("sqrt" + "(" + win.b + ")" + "=" + s);
            win.jTextArea.setText(win.jTextArea.getText()+win.jTextField4.getText()+"\n");
            win.b = s;
        }
        else if(buttonName.equals("="))
        {
            String []s=calculator.HouZHui(win.b);
            String result=calculator.Result(s);
            win.b=result+"";
            win.jTextField4.setText(win.b);
            //jTextArea.setText(jTextArea.getText()+"="+jTextField4.getText()+"\n");
            win.jTextArea.setText(win.jTextArea.getText()+win.jTextField1.getText()+win.jTextField2.getText()+win.jTextField3.getText()+"="+win.jTextField4.getText()+"\n");
        }
        else if(buttonName.equals("+/-"))
        {
            String s = calculator.xiangFan(win.b);
            win.jTextField2.setText("+/-");
            win.jTextField4.setText("相反数为：" + "(" + win.b + ")" + "=" + s);
            win.jTextArea.setText(win.jTextArea.getText()+win.jTextField4.getText()+"\n");
        }
        else if(buttonName.equals("保存"))
        {
            JFileChooser chooser = new JFileChooser();//构造一个指向用户默认目录
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "txt","csv", "jpg", "gif");
            //FileNameExtensionFilter是FileFilter的一个实现，它使用指定的扩展名集合进行过滤。
            // 文件的扩展名是指文件名最后一个“.”后面的部分。名称不包含“.”的文件没有文件扩展名。
            //设置文件类型
            chooser.setFileFilter(filter);
            //打开选择器面板
            int returnVal = chooser.showSaveDialog(new JPanel());//showSaveDialog弹出一个”save file“文件选择器对话框，将选择的文件返回returnVal
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {//APPROVE_OPTION选择确认（yes、ok）后返回该值。这是官方的API文档，它只介绍了这么点
                System.out.println("你打开的文件夹是: " +
                        chooser.getSelectedFile().getPath());//getSelectedFile()返回选中的文件
                String path = chooser.getSelectedFile().getPath();
                try
                {
                    File f = new File(path);
                    System.out.println(f.getAbsolutePath());
                    FileOutputStream fileOutputStream=new FileOutputStream(f,true);
                    byte []data=win.jTextArea.getText().getBytes();
                    fileOutputStream.write(data);
                }
                catch (IOException tt)
                {
                    tt.printStackTrace();
                }
            }
        }
        else if(buttonName.equals("查看"))
        {
            win.jTextArea.setText(null);

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "txt", "csv", "jpg", "gif");
            //设置文件类型
            chooser.setFileFilter(filter);
           // int returnVal = chooser.showSaveDialog(new JPanel());
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("你打开的文件夹是: " +
                        chooser.getSelectedFile().getPath());
                String path = chooser.getSelectedFile().getPath();

                try {
                    File f = new File(path);
                    System.out.println(f.getAbsolutePath());
                    FileInputStream fileInputStream = new FileInputStream(f);
                    int rs = 0;
                    while ((rs = fileInputStream.read(win.bytes, 0, 512)) > 0) {
                        String s = new String(win.bytes, 0, rs);
                        win.jTextArea.setText(s);
                    }
                } catch (IOException yy) {
                    yy.printStackTrace();
                }

            }
        }
        else if(buttonName.equals("清除"))
        {
            win.jTextArea.setText(null);
        }
        else if(buttonName.equals("退格"))
        {

            if(!win.jTextField1.getText().isEmpty()&&win.jTextField2.getText().isEmpty()&&win.jTextField3.getText().isEmpty())
            {
                int l=win.b.length();
                String s=win.b.substring(0,l-1);
                win.jTextField1.setText(s);
                win.b=s;
                cc=win.b;
            }
            else  if(!win.jTextField1.getText().isEmpty()&&!win.jTextField2.getText().isEmpty()&&win.jTextField3.getText().isEmpty())
            {
                String yuanshuju=win.b.substring(0,cc.length());
                int l=win.b.length();
                int l1=cc.length();////c是jTextField1里的数据
                String s=win.b.substring(l1,l-1);
                win.jTextField2.setText(s);
                win.b=yuanshuju+s;
            }
            else if(!win.jTextField1.getText().isEmpty()&&!win.jTextField2.getText().isEmpty()&&!win.jTextField3.getText().isEmpty())
            {
                String yuanshuju=win.b.substring(0,cc.length()+1);
                int l=win.b.length();
                int l1=cc.length()+1;
                String s=win.b.substring(l1,l-1);
                win.jTextField3.setText(s);
                aa=s;//a是jTextField3里的数据
                win.b=yuanshuju+s;
            }
        }
        else
        {
            win.b=win.b+buttonName;
            if(buttonName.equals("+")||buttonName.equals("/")||buttonName.equals("*")||buttonName.equals("-"))
            {
                win.jTextField2.setText(buttonName);
            }
            else if(win.jTextField2.getText().length()!=0&&win.jTextField1.getText().length()!=0)
            {

                aa+=buttonName;
                win.jTextField3.setText(aa);
            }
            else if(!buttonName.equals("+")&&!buttonName.equals("-")&&!buttonName.equals("*")&&!buttonName.equals("/"))
            {
                cc+=buttonName;
                win.jTextField1.setText(cc);

            }
            win.jTextField4.setText(win.b);

        }

    }




}
