package com.olivierpicard.Grapher.Interpreter;

/**
 * This enum is ordered by operative Priority
 * Operator at the bottom of the list is those who have the most GetPriority
 */

public enum Operators
{
    ADDITION,
    SUBSTRACTION,
    MULTIPLICATION,
    DIVISION,
    SQUARE;


    public int GetPriority() {
        return ordinal();
    }


    public static Operators GuessOperationType_FromSign(String operationSign)
    {
        if(operationSign == null) return null;
        operationSign = operationSign.toLowerCase();
        Operators operator = null;

        switch (operationSign) {
            case "^" : operator = Operators.SQUARE; break;
            case "*" : operator = Operators.MULTIPLICATION; break;
            case "/" : operator = Operators.DIVISION; break;
            case "+" : operator = Operators.ADDITION; break;
            case "-" : operator = Operators.SUBSTRACTION; break;
            default: operator = null;
        }

        return operator;
    }


    public String toString()
    {
        Operators opSelected = valueOf(name());
        String result = "";
        switch (opSelected)
        {
            case ADDITION: result = "+"; break;
            case SUBSTRACTION: result = "-"; break;
            case MULTIPLICATION: result = "*"; break;
            case DIVISION: result = "/"; break;
            case SQUARE: result = "^"; break;
        }
        return result;
    }
}
