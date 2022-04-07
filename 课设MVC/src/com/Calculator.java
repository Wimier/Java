package com;

import java.text.DecimalFormat;

public class Calculator {
    public String xiangFan(String str)//求相反数
    {
        String result = "";
        double a = Double.parseDouble(str), b = 0;
        b = -1*a;
        result = String.valueOf(b);//将运算结果转换为string类型并赋给string类型的变量result
        return result;
    }
    public String sqrtYs(String str) {
        String result = "";
        double a = Double.parseDouble(str), b = 0;
        b = Math.sqrt(a);
        result = String.valueOf(b);//将运算结果转换为string类型并赋给string类型的变量result
        return result;
    }



    //中缀转化为后缀
    public String[] HouZHui(String str) {
        String s = "";
        char[] opStack = new char[100];
        String[] postQueue = new String[100];
        int top = -1, j = 0;// 静态指针top,控制变量j
        for (int i = 0; i < str.length(); i++)
        // indexOf函数，返回字串首次出现的位置；charAt函数返回index位置处的字符；
        {

            if ("0123456789.".indexOf(str.charAt(i)) >= 0) // 遇到数字字符的情况直接入队
            {
                s = "";// 作为承接字符，每次开始时都要清空
                for (; i < str.length() && "0123456789.".indexOf(str.charAt(i)) >= 0; i++) {
                    s = s + str.charAt(i);
                    //比如，中缀表达式：234+4*2，我们扫描这个字符串的时候，s的作用相当于用来存储长度为3个字符的操作数：234
                }
                i--;
                postQueue[j] = s;
                j++;
            }
//            else if ("(".indexOf(str.charAt(i)) >= 0) {
//                top++;
//                opStack[top] = str.charAt(i);// 左括号入栈
//            } else if (")".indexOf(str.charAt(i)) >= 0) {// 遇到右括号
//                for (; ; )// 栈顶元素循环出栈，直到遇到左括号为止
//                {
//                    if (opStack[top] != '(') {// 栈顶元素不是左括号
//                        postQueue[j] = opStack[top] + "";// 栈顶元素出栈
//                        j++;
//                        top--;
//                    } else { // 找到栈顶元素是左括号
//                        top--;// 删除栈顶左括号
//                        break;// 循环结束
//                    }
//                }
//
//            }
            else if ("*%/+-".indexOf(str.charAt(i)) >= 0)// 遇到运算符
            {
                if (top == -1) {// 若栈为空则直接入栈
                    top++;
                    opStack[top] = str.charAt(i);
                }
            } else if ("*%/".indexOf(opStack[top]) >= 0) {// 当栈顶元素为高优先级运算符时,让栈顶元素出栈进入后缀表达式后,当前运算符再入栈
                postQueue[j] = opStack[top] + "";
                j++;
                opStack[top] = str.charAt(i);
            } else {
                top++;
                opStack[top] = str.charAt(i);// 当前元素入栈
            }
        }

        while (top != -1) {// 遍历结束后将栈中剩余元素依次出栈进入后缀表达式
            postQueue[j] = opStack[top] + "";
            j++;
            top--;
        }
        return postQueue;
    }

    public String Result(String[] str) {
        String[] Result = new String[100];
        int Top = -1;
        for (int i = 0; str[i] != null; i++) {
            if ("+-*%/".indexOf(str[i]) < 0) {
                Top++;
                Result[Top] = str[i];
            }
            if ("+-*%/".indexOf(str[i]) >= 0)// 遇到运算符字符，将栈顶两个元素出栈计算并将结果返回栈顶
            {
                double x, y, n;
                x = Double.parseDouble(Result[Top]);// 顺序出栈两个数字字符串，并转换为double类型
                Top--;
                y = Double.parseDouble(Result[Top]);
                Top--;
                if ("*".indexOf(str[i]) >= 0) {
                    n = y * x;
                    Top++;
                    DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
                    String s1 = df.format(n);
                    Result[Top] = String.valueOf(s1);// 将运算结果重新入栈

                }
                if ("/".indexOf(str[i]) >= 0) {
                    if (x == 0)// 被除数不允许为0
                    {
                        String s = "error!";
                        return s;
                    } else {
                        n = y / x;
                        DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
                        String s1 = df.format(n);
                        Top++;
                        Result[Top] = String.valueOf(s1);// 将运算结果重新入栈
                    }
                }
                if ("%".indexOf(str[i]) >= 0) {
                    if (x == 0)// 被除数不允许为0
                    {
                        String s = "error!";
                        return s;
                    } else {
                        n = y % x;
                        Top++;
                        Result[Top] = String.valueOf(n);// 将运算结果重新入栈
                    }
                }
                if ("-".indexOf(str[i]) >= 0) {
                    n = y - x;
                    DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
                    String s1 = df.format(n);
                    Top++;
                    Result[Top] = String.valueOf(s1);// 将运算结果重新入栈
                }
                if ("+".indexOf(str[i]) >= 0) {
                    n = y + x;
                    Top++;
                    Result[Top] = String.valueOf(n);// 将运算结果重新入栈
                }
            }
        }
        return Result[Top];
    }
}
