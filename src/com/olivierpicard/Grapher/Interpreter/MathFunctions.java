package com.olivierpicard.Grapher.Interpreter;

public enum MathFunctions
{
    SIN,
    COS,
    TAN,
    SQRT,
    EXP;


    public static MathFunctions GuessMathFunction_FromString(String operationSign)
    {
        if(operationSign == null) return null;
        operationSign = operationSign.toLowerCase();
        MathFunctions function = null;

        switch (operationSign) {
            case "sin" : function = MathFunctions.SIN; break;
            case "cos" : function = MathFunctions.COS; break;
            case "tan" : function = MathFunctions.TAN; break;
            case "sqrt" : function = MathFunctions.SQRT; break;
            case "exp" : function = MathFunctions.EXP; break;
            default: return null;
        }

        return function;
    }


    public String toString()
    {
        MathFunctions opSelected = valueOf(name());
        String result = "";
        switch (opSelected)
        {
            case SIN: result = "sin"; break;
            case COS: result = "cos"; break;
            case TAN: result = "tan"; break;
            case SQRT: result = "sqrt"; break;
            case EXP: result = "exp"; break;
        }
        return result;
    }
}
