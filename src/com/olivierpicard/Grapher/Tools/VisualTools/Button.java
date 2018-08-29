package com.olivierpicard.Grapher.Tools.VisualTools;

import com.olivierpicard.Grapher.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Button implements ThemeRefreshable
{
    public int size = 40;
    public boolean isVisible = false;

    protected int m_x, m_y;
    private IButtonCallback m_callback;
    protected Image m_icon;
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
        isVisible = true;
        m_x = x;
        m_y = y;
        g.setColor(ViewController.theme.get_ButtonBackgroundColor());
        g.fillOval(x - size /2, y - size /2, size, size);

        g.setColor(ViewController.theme.get_ButtonBorderColor());
        g.drawOval(x - size /2, y - size /2, size, size);

        if(m_icon == null) return;

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        while(!g.drawImage(m_icon, x - size /4, y - size /4, size /2, size /2, null)){}
    }


    public void mouseClicked(MouseEvent e)
    {
        if(!isVisible) return;
        if(e.getX() >= m_x - size /2 && e.getX() <= m_x + size /2
                && e.getY() >= m_y - size /2 && e.getY() <= m_y + size /2)
            m_callback.ExecuteFunction();
    }

}
