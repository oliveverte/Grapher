package com.olivierpicard.Grapher;
import com.olivierpicard.Grapher.Interpreter.Parser;
import com.olivierpicard.Grapher.Interpreter.StandaloneNode;

import javax.swing.*;
import java.awt.*;

public class Main
{
    /*************************************************
     *
     * Lire le README.rtf à la racine du projet
     *
     *************************************************/

    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl", "true");
        SwingUtilities.invokeLater(() -> init());
    }

    public static void init()
    {
        ViewController view = new ViewController();
    }
}
