package com.olivierpicard.Grapher;

import com.olivierpicard.Grapher.Tools.Interval1D;
import com.olivierpicard.Grapher.Tools.Interval2D;
import com.olivierpicard.Grapher.Tools.ScenePoint;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Axis
{
    public static final float UNIT_DIVISION = 4f;


    public void Draw(Graphics2D g)
    {
        Draw(g, Scene.Constraint.gridUnitPixelSize / UNIT_DIVISION, ViewController.theme.get_thinGridColor());
        Draw(g, Scene.Constraint.gridUnitPixelSize, ViewController.theme.get_gridColor());

        g.setColor(ViewController.theme.get_axisColor());
        g.setStroke(new BasicStroke(1.7f));
        g.drawLine(0, (int)Scene.Constraint.originPixelPosition.get_y(), Scene.Constraint.screenSize.width, (int)Scene.Constraint.originPixelPosition.get_y());
        g.drawLine((int)Scene.Constraint.originPixelPosition.get_x(), 0, (int)Scene.Constraint.originPixelPosition.get_x(), Scene.Constraint.screenSize.height);

        DrawCoordinate(g);
    }


    private void Draw(Graphics2D g, float step, Color c)
    {
        g.setColor(c);
        for(int i = (int)Scene.Constraint.originPixelPosition.get_x(); i >= 0; i -= step)
            g.drawLine(i, Scene.Constraint.screenSize.height, i, 0);

        for(int i = (int)Scene.Constraint.originPixelPosition.get_x(); i <= Scene.Constraint.screenSize.width; i += step)
            g.drawLine(i, Scene.Constraint.screenSize.height, i, 0);

        // Hrizontal
        for(int i = (int)Scene.Constraint.originPixelPosition.get_y(); i <= Scene.Constraint.screenSize.height; i += step)
            g.drawLine(0, i, Scene.Constraint.screenSize.width, i);

        for(int i = (int)Scene.Constraint.originPixelPosition.get_y(); i >= 0; i -= step)
            g.drawLine(0, i, Scene.Constraint.screenSize.width, i);
    }


    public void DrawCoordinate(Graphics2D g)
    {
        final DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        final int step = Scene.Constraint.gridUnitPixelSize;
        final int characterPixelSize = 4;
        float value;
        value = 0;
        final ScenePoint baseCoord = new ScenePoint(Scene.Constraint.originPixelPosition.get_x(), Scene.Constraint.originPixelPosition.get_y());

        if((int)Scene.Constraint.originPixelPosition.get_y() > Scene.Constraint.screenSize.height - 25)
            baseCoord.set_y(Scene.Constraint.screenSize.height - 25);
        else if((int)Scene.Constraint.originPixelPosition.get_y() < 3)
            baseCoord.set_y(3f);
        if((int)Scene.Constraint.originPixelPosition.get_x() > Scene.Constraint.screenSize.width)
            baseCoord.set_x(Scene.Constraint.screenSize.width);
        else if((int)Scene.Constraint.originPixelPosition.get_x() < 0)
            baseCoord.set_x(0);


        for (int i = (int) Scene.Constraint.originPixelPosition.get_x(); i >= 0; i -= step, value -= Scene.Constraint.gridUnitValue) {
            final String strValue = df.format(value);
            g.drawString(strValue, i - characterPixelSize * strValue.length(), baseCoord.get_y() + 15);
        }

        value = 0;
        for (int i = (int) Scene.Constraint.originPixelPosition.get_x(); i <= Scene.Constraint.screenSize.width; i += step, value += Scene.Constraint.gridUnitValue) {
            final String strValue = df.format(value);
            g.drawString(strValue, i - characterPixelSize * strValue.length(), baseCoord.get_y() + 15);
        }

        // Horizontal

        value = -Scene.Constraint.gridUnitValue;
        for (int i = (int) Scene.Constraint.originPixelPosition.get_y() + step; i <= Scene.Constraint.screenSize.height; i += step, value -= Scene.Constraint.gridUnitValue) {
            final String strValue = df.format(value);
            final int sapce = characterPixelSize * strValue.length();
            final int alignement =  (Scene.Constraint.originPixelPosition.get_x() > 0) ? -sapce - 25 : sapce - 5;
            g.drawString(strValue, baseCoord.get_x() + alignement ,  i + 5);
        }


        value = Scene.Constraint.gridUnitValue;
        for (int i = (int) Scene.Constraint.originPixelPosition.get_y() - step; i > 0; i -= step, value += Scene.Constraint.gridUnitValue) {
            final String strValue = df.format(value);
            final int sapce = characterPixelSize * strValue.length();
            final int alignement =  (Scene.Constraint.originPixelPosition.get_x() > 0) ? -sapce - 25 : sapce + 5;
            g.drawString(strValue, baseCoord.get_x() + alignement ,  i + 5);
        }

    }
}
