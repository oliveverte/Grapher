package com.olivierpicard.Grapher.DataManager;

import com.olivierpicard.Grapher.FunctionDrawer;

import java.awt.*;
import java.util.Random;

public abstract class DataDrawable {
    private static short m_counterDataCreation = 0;
    protected Color m_color;
    protected String m_name;
    protected String m_expression;
    protected short m_id;
    protected boolean m_isSelected;

    public abstract String toString();
    public abstract void Draw(FunctionDrawer drawer);
    public abstract float Compute(float arg);


    public DataDrawable(Color color, String name, String expression)
    {
        Random r = new Random();
        int Low = 0x707070, High = 0xAFAFAF; // Gray value in hexa

        m_color = new Color(r.nextInt(High-Low) + Low);
        m_name = name;
        m_expression = expression;
        m_id = m_counterDataCreation++;
    }


    public Color get_color() {
        return m_color;
    }


    public void set_color(Color m_color) {
        this.m_color = m_color;
    }


    public String get_name() {
        return m_name;
    }


    public void set_name(String m_name) {
        this.m_name = m_name;
    }


    public short get_id(){
        return m_id;
    }


    public boolean is_isSelected() {
        return m_isSelected;
    }


    public void set_isSelected(boolean m_isSelected) {
        this.m_isSelected = m_isSelected;
    }
}
