package com.olivierpicard.Grapher;

import com.olivierpicard.Grapher.Tools.ScenePoint;

import java.awt.*;

public class FunctionDrawer extends SceneDrawer
{
    public static final float FUNCTION_THICKNESS = 1.8f;

    public FunctionDrawer(Graphics graphics)
    {
        super(graphics);
        SetThickness(1.8f);
    }


    public void Add(ScenePoint point)
    {
        point = point.ChangeSpace(Scene.Space.SCREEN);

        if(m_lastPoint == null) { m_lastPoint = point; return; }

        m_graphics.drawLine(
                (int)m_lastPoint.get_x(),
                (int)m_lastPoint.get_y(),
                (int)point.get_x(),
                (int)point.get_y()
        );

        m_lastPoint = point;
    }
}
