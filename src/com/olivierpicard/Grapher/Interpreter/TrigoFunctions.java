package com.olivierpicard.Grapher.Interpreter;

public enum TrigoFunctions
{
    SIN,
    COS,
    TAN;


    public static TrigoFunctions GuessTrigoFunction_FromString(String operationSign)
    {
        if(operationSign == null) return null;
        operationSign = operationSign.toLowerCase();
        TrigoFunctions function = null;

        switch (operationSign) {
            case "sin" : function = TrigoFunctions.SIN; break;
            case "cos" : function = TrigoFunctions.COS; break;
            case "tan" : function = TrigoFunctions.TAN; break;
            default: function = null;
        }

        return function;
    }


    public String toString()
    {
        TrigoFunctions opSelected = valueOf(name());
        String result = "";
        switch (opSelected)
        {
            case SIN: result = "sin"; break;
            case COS: result = "cos"; break;
            case TAN: result = "tan"; break;
        }
        return result;
    }
}
