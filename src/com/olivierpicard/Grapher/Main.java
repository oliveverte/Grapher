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
    }
}
