package com.olivierpicard.Grapher.Tools.VisualTools;

import java.awt.*;

public enum Theme
{
    LIGHT,
    DARK;

    public Color get_hightlightBackgroundColor()
    {
        if(ordinal() == LIGHT.ordinal()) return Color.PINK;
        return Color.PINK;
    }

    public Color get_backgroundColor()
    {
        if(ordinal() == LIGHT.ordinal()) return Color.WHITE;
        return Color.BLACK;
    }

    public Color get_foregroundColor()
    {
        if(ordinal() == LIGHT.ordinal()) return Color.BLACK;
        return Color.WHITE;
    }

    public Color get_inversedForgroundColor()
    {
        if(ordinal() == LIGHT.ordinal()) return Color.BLACK;
        return Color.BLACK;
    }

    public Color get_lightForgroundColor()
    {
        if(ordinal() == LIGHT.ordinal()) return Color.lightGray;
        return Color.lightGray;
    }

    public Color get_separatorColor()
    {
        if(ordinal() == LIGHT.ordinal()) return Color.LIGHT_GRAY;
        return Color.DARK_GRAY;
    }

    public Color get_gridColor()
    {
        if(ordinal() == LIGHT.ordinal()) return new Color(0xD3D3D3);
        return new Color(0x303030);
    }

    public Color get_thinGridColor()
    {
        if(ordinal() == LIGHT.ordinal()) return new Color(0xEDEDED);
        return new Color(0x232323);
    }

    public Color get_buttonBackgroundColor()
    {
        if(ordinal() == LIGHT.ordinal()) return  Color.LIGHT_GRAY;
        return Color.DARK_GRAY;
    }

    public Color get_buttonBorderColor()
    {
        if(ordinal() == LIGHT.ordinal()) return  Color.DARK_GRAY;
        return Color.LIGHT_GRAY;
    }

    public Color get_axisColor()
    {
        if(ordinal() == LIGHT.ordinal()) return  Color.DARK_GRAY;
        return Color.LIGHT_GRAY;
    }
}
