package com.olivierpicard.Grapher.Interpreter;

public class VariableStandaloneNode extends StandaloneNode
{
    public VariableStandaloneNode() { super(); }

    @Override
    public float Compute(float argument)
    {
        return argument;
    }

    @Override
    public String toString()
    {
        return "x0 ";
    }

}
