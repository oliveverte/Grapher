package com.olivierpicard.Grapher.Tools.VisualTools;

import com.olivierpicard.Grapher.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button {
    public static final int DIAMETER = 40;

    private int m_x, m_y;
    private IButtonCallback m_callback;


    public Button(IButtonCallback callback)
    {
        m_callback = callback;
    }


    public void Draw(Graphics2D g, int x, int y)
    {
        m_x = x;
        m_y = y;
        g.setColor(ViewController.theme.get_ButtonBackgroundColor());
        g.fillOval(x - DIAMETER/2, y - DIAMETER/2, DIAMETER, DIAMETER);

        g.setColor(ViewController.theme.get_ButtonBorderColor());
        g.drawOval(x - DIAMETER/2, y - DIAMETER/2, DIAMETER, DIAMETER);
    }


    public void mouseClicked(MouseEvent e)
    {
        if(e.getX() >= m_x - DIAMETER/2 && e.getX() <= m_x + DIAMETER/2
                && e.getY() >= m_y - DIAMETER/2 && e.getY() <= m_y + DIAMETER/2)
            m_callback.ExecuteFunction();
    }

}
