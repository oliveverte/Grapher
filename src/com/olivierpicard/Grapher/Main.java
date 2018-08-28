package com.olivierpicard.Grapher;
import com.olivierpicard.Grapher.Interpreter.Parser;
import com.olivierpicard.Grapher.Interpreter.StandaloneNode;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> init());
    }

    public static void init()
    {
        ViewController view = new ViewController();

//        String s = "3";
//        System.out.println(s.substring(0, 1).matches("^[a-zA-Z]+.?$"));



//        float OldRange= 20, OldMax = 0, OldMin= -10, NewRange=1000, NewMax=0, NewMin=-0, NewValue=0, OldValue=11;

//        NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin;
//        System.out.println(NewValue);
//        Parser p = new Parser("2^(2*(x+1))", "x");
//        System.out.println(p.Interpret(4));

//        ANode wrapNode = new WrapNode();
//        wrapNode = wrapNode.InsertNode(new VariableStandaloneNode());
//        wrapNode = wrapNode.InsertNode(new OperatorNode("+"));
//        wrapNode = wrapNode.InsertNode(new VariableStandaloneNode());
//        System.out.println(wrapNode.getRoot().Compu-$e(10));
//        ANode wrapNode2;

//        TODO : Error : 2*(3*(1+1*sin(2))+7)
//        wrapNode = wrapNode.InsertNode(new StandaloneNode(2));
//        wrapNode = wrapNode.InsertNode(new OperatorNode(Operators.ADDITION));
//        wrapNode = wrapNode.InsertNode(new StandaloneNode(2));

//        wrapNode = wrapNode.InsertNode(new OperatorNode("*"));
//        wrapNode = wrapNode.InsertNode(new StandaloneNode(3));
//
//        wrapNode = wrapNode.InsertNode(new OperatorNode("/"));
//        wrapNode = wrapNode.InsertNode(new StandaloneNode(4));
//
//        wrapNode = wrapNode.InsertNode(new OperatorNode("-"));
//        wrapNode = wrapNode.InsertNode(new OppositeStandaloneNode());
//        wrapNode = wrapNode.InsertNode(new StandaloneNode(8));

//
//        wrapNode2 = wrapNode.InsertNode(new WrapNode());
//        wrapNode2 = wrapNode2.InsertNode(new StandaloneNode(2));
//        wrapNode2 = wrapNode2.InsertNode(new OperatorNode("+"));
//        wrapNode2 = wrapNode2.InsertNode(new StandaloneNode(2));
//
//        wrapNode = wrapNode.InsertNode(new OperatorNode("*"));
//        wrapNode = wrapNode.InsertNode(new OppositeStandaloneNode());
//        wrapNode = wrapNode.InsertNode(new StandaloneNode(3));



//        System.out.println(wrapNode.getRoot());
    }
}
