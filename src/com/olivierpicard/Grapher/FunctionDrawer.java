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
        ScenePoint _point = point.ChangeSpace(Scene.Space.SCREEN);

        if(m_lastPoint == null) { m_lastPoint = point; return; }
        if(Math.abs(point.get_y() - m_lastPoint.get_y()) * Scene.Constraint.gridUnitValue > 3
                && Math.min(point.get_y(), m_lastPoint.get_y()) < 0
                && Math.max(point.get_y(), m_lastPoint.get_y()) > 0) {Clear(); return;}

        final ScenePoint lastPoint = m_lastPoint.ChangeSpace(Scene.Space.SCREEN);

        m_graphics.drawLine(
                (int)lastPoint.get_x(),
                (int)lastPoint.get_y(),
                (int)_point.get_x(),
                (int)_point.get_y()
        );

        m_lastPoint = point;
    }
}
