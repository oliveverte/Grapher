package com.olivierpicard.Grapher;

import com.olivierpicard.Grapher.Tools.ScenePoint;

import java.awt.*;

public abstract class SceneDrawer
{
    protected Graphics2D m_graphics;
    protected ScenePoint m_lastPoint;


    public abstract void Add(ScenePoint point);


    public SceneDrawer(Graphics graphics)
    {
        m_graphics = (Graphics2D)graphics;
    }


    public void SetColor(Color color)
    {
        m_graphics.setColor(color);
    }


    public void SetThickness(float thickness) {m_graphics.setStroke(new BasicStroke(thickness));}


    public void Clear() {m_lastPoint = null;}

}
