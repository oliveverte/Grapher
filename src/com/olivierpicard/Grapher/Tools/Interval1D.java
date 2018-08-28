package com.olivierpicard.Grapher.Tools;

public class Interval1D
{
    private float m_minX, m_maxX;


    public Interval1D(float min, float max)
    {
        set_minX(min);
        set_maxX(max);
    }


    public float get_minX() {
        return m_minX;
    }


    public void set_minX(float min) {
        m_minX = min;
    }


    public float get_maxX() {
        return m_maxX;
    }


    public float get_rangeX() {return m_maxX - m_minX; }


    public void set_maxX(float max) {
        m_maxX = max;
    }


    public String toString()
    {
        return "min X : " + m_minX + " - max X : " + m_maxX;
    }


}
