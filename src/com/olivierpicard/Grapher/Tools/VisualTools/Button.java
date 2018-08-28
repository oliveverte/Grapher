package com.olivierpicard.Grapher.Tools.VisualTools;

import com.olivierpicard.Grapher.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

public class Button extends JComponent implements ThemeRefreshable
{
    public static final int DIAMETER = 40;

    private int m_x, m_y;
    private IButtonCallback m_callback;
    private Image m_icon;
    private String m_iconPath;


    public Button(IButtonCallback callback)
    {
        m_callback = callback;
    }


    public Button(IButtonCallback callback, String iconPath)
    {
        m_callback = callback;
        m_iconPath = iconPath;
        RefreshTheme();
    }


    public void RefreshTheme()
    {
        if(m_iconPath.isEmpty()) return;;
        String[] path = m_iconPath.split("\\.");

        if(ViewController.theme == Theme.LIGHT)
            m_icon = Toolkit.getDefaultToolkit().getImage(path[0] + "_light." + path[1]);
        else
            m_icon = Toolkit.getDefaultToolkit().getImage(path[0] + "_dark." + path[1]);

    }


    public void Draw(Graphics2D g, int x, int y)
    {
        m_x = x;
        m_y = y;
        g.setColor(ViewController.theme.get_ButtonBackgroundColor());
        g.fillOval(x - DIAMETER/2, y - DIAMETER/2, DIAMETER, DIAMETER);

        g.setColor(ViewController.theme.get_ButtonBorderColor());
        g.drawOval(x - DIAMETER/2, y - DIAMETER/2, DIAMETER, DIAMETER);

        if(m_icon == null) return;

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(m_icon, x - DIAMETER/4, y - DIAMETER/4, DIAMETER/2, DIAMETER/2, this);
    }


    public void mouseClicked(MouseEvent e)
    {
        if(e.getX() >= m_x - DIAMETER/2 && e.getX() <= m_x + DIAMETER/2
                && e.getY() >= m_y - DIAMETER/2 && e.getY() <= m_y + DIAMETER/2)
            m_callback.ExecuteFunction();
    }

}
