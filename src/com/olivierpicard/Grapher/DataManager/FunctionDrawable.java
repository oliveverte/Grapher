package com.olivierpicard.Grapher.DataManager;

import com.olivierpicard.Grapher.Interpreter.ANode;
import com.olivierpicard.Grapher.Scene;
import com.olivierpicard.Grapher.FunctionDrawer;
import com.olivierpicard.Grapher.Tools.ScenePoint;
import com.olivierpicard.Grapher.Tools.VisualTools.Theme;
import com.olivierpicard.Grapher.ViewController;
import jdk.nashorn.internal.objects.annotations.Function;

import java.awt.*;


public class FunctionDrawable extends DataDrawable
{
    protected String m_variableName;
    protected ANode m_tree;


    public FunctionDrawable(String functionName, String variableName, String expression, ANode tree)
    {
        super(null, functionName, expression);
        m_variableName = variableName;
        m_tree = tree;
    }


    public void Draw(FunctionDrawer drawer)
    {
        final float lowerBound = Scene.Constraint.sceneInterval.get_minX();
        final float upperBound = Scene.Constraint.sceneInterval.get_maxX();
        final float step = Math.min(Scene.Constraint.gridUnitResolution * Scene.Constraint.gridUnitValue, 0.2f);

        if(m_isSelected) {
            drawer.SetThickness(FunctionDrawer.FUNCTION_THICKNESS + 2f);
            drawer.SetColor((ViewController.theme == Theme.LIGHT ? m_color.darker() : m_color.brighter()));
            for(float i = lowerBound; i <= upperBound+1; i += step)
                try { drawer.Add(new ScenePoint(i, m_tree.Compute(i))); } catch (ArithmeticException e) { drawer.Clear(); }
        }

        drawer.Clear();
        drawer.SetThickness(FunctionDrawer.FUNCTION_THICKNESS);
        drawer.SetColor(m_color);

        for(float i = lowerBound; i <= upperBound+1; i += step)
            try { drawer.Add(new ScenePoint(i, m_tree.Compute(i))); } catch (ArithmeticException e) { drawer.Clear(); }
    }

    @Override
    public float Compute(float arg)
    {
        return m_tree.Compute(arg);
    }


    public String get_expression() {
        return m_expression;
    }


    public void set_expression(String m_expression) {
        this.m_expression = m_expression;
    }


    public String get_variableName() {
        return m_variableName;
    }


    public void set_variableName(String m_variableName) {
        this.m_variableName = m_variableName;
    }


    public ANode get_tree() {
        return m_tree;
    }


    @Override
    public String toString() {
        return m_name + "(" + m_variableName + ")=" + m_expression;
    }


}
