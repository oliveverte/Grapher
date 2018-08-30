package com.olivierpicard.Grapher.Tools;

import com.olivierpicard.Grapher.Scene;

import java.awt.*;

/**
 * La raison de ne pas utilisé la classe Point de java c'est
 * parce qu'elle travail en type double et qu'ici l'on souhaite du float
 * ça évitera les cast
 */
public class ScenePoint
{
    private float _x;
    private float _y;


    public ScenePoint(float x, float y)
    {
        _x = x;
        _y = y;
    }


    public ScenePoint(Point point)
    {
        _x = point.x;
        _y = point.y;
    }


    public ScenePoint ChangeSpace(Scene.Space space)
    {
        /** NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin; */
        ScenePoint point = null;

        switch (space)
        {
            case SCREEN:
                point  = new ScenePoint(
                        (((_x - Scene.Constraint.sceneInterval.get_minX()) * Scene.Constraint.screenSize.width) / Scene.Constraint.sceneInterval.get_rangeX()),
                        (float)Scene.Constraint.screenSize.height - (((_y - Scene.Constraint.sceneInterval.get_minY()) * Scene.Constraint.screenSize.height) / Scene.Constraint.sceneInterval.get_rangeY()));
                break;
            case SCENE:
                point = new ScenePoint(
                        ((_x * Scene.Constraint.sceneInterval.get_rangeX()) / Scene.Constraint.screenSize.width) + Scene.Constraint.sceneInterval.get_minX(),
                        ((_y * Scene.Constraint.sceneInterval.get_rangeY()) / Scene.Constraint.screenSize.height) + Scene.Constraint.sceneInterval.get_minY());
                break;
        }
        return point;
    }



    public void Translate(float x, float y)
    {
        _x += x;
        _y += y;
    }


    public ScenePoint Add(ScenePoint point)
    {
        _x += point._x;
        _y += point._y;
        return this;
    }


    public float get_x() {
        return _x;
    }

    public void set_x(float _x) {
        this._x = _x;
    }

    public float get_y() {
        return _y;
    }

    public void set_y(float _y) {
        this._y = _y;
    }

    public String toString()
    {
        return "(" + _x + " , " + _y + ")";
    }
}
