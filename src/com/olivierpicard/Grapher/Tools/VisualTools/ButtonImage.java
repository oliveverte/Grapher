package com.olivierpicard.Grapher.Tools.VisualTools;

import java.awt.*;

public class ButtonImage extends Button
{
    public ButtonImage(IButtonCallback callback, String iconPath)
    {
        super(callback, iconPath);
        size = 80;
    }


    @Override
    public void Draw(Graphics2D g, int x, int y)
    {
        isVisible = true;
        m_x = x;
        m_y = y;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        while(!g.drawImage(m_icon, x - size /4, y - size /4, size /2, size /2, null)){}

    }


}
