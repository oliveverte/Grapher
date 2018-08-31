package com.olivierpicard.Grapher.Interpreter;

public class MathWrapNode extends WrapNode
{
    private MathFunctions _functionName;


    public MathWrapNode(MathFunctions functionName)
    {
        _functionName = functionName;
    }


    @Override
    public float Compute(float argument)
    {
        float result = _child.Compute(argument);
        switch (_functionName) {
            case SIN: result = (float)Math.sin(result); break;
            case COS: result = (float)Math.cos(result); break;
            case TAN: result = (float)Math.tan(result); break;
            case SQRT: result = (float)Math.sqrt(result); break;
            case EXP: result = (float)Math.exp(result); break;
        }
        return result;
    }


    public String toString()
    {
        return _functionName.name().toLowerCase() + "( " + super.toString() + ")";
    }


}
