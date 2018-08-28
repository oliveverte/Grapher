package com.olivierpicard.Grapher.Tools;

import java.awt.*;

public class Interval2D extends Interval1D
{
    private float m_minY, m_maxY;


    public Interval2D(float minX, float maxX, float minY, float maxY)
    {
        super(minX, maxX);
        m_minY = minY;
        m_maxY = maxY;
    }


    public Interval2D(Dimension dimension)
    {
        super(0, dimension.width);
        m_minY = 0;
        m_maxY = dimension.height;
    }


    public float get_minY() {
        return m_minY;
    }


    public void set_minY(float minY) {
        m_minY = minY;
    }


    public float get_maxY() {
        return m_maxY;
    }


    public void set_maxY(float maxY) {
        m_maxY = maxY;
    }


    public float get_rangeY() {return m_maxY - m_minY; }

    public String toString()
    {

        return super.toString() + " - min Y : " + m_minY + " - max Y : " + m_maxY;
    }
}
