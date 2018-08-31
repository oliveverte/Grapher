package com.olivierpicard.Grapher.Interpreter;

import com.olivierpicard.Grapher.DataManager.FunctionDrawable;

/**
 *
*/
public class Parser
{
    private int m_readingHead, m_parentheseCounter;
    private String m_expression, m_variableName, m_functionName;
    private ANode m_tree;



    public Parser(String expression)
    {
        m_expression = expression;
    }


    public FunctionDrawable Interpret()
    {
        m_tree = new WrapNode();
        m_functionName = m_variableName = "";
        int startingExpression = 0;

        if(m_expression.contains("=")) {
            PreCompute();
            startingExpression = m_readingHead;
        }
        else{
            m_functionName = "f";
            m_variableName = "x";
        }

        ComputeAutomatLogic();

        return new FunctionDrawable(
                m_functionName,
                m_variableName,
                m_expression.substring(startingExpression),
                m_tree.getRoot()
        );
    }


    public void PreCompute()
    {
        int state = 0;
        boolean mustStop = false;
        String currentReading = "";

        while (!hasReachTheEnd() && !mustStop)
        {
            switch (state)
            {
                case 0 :
                    // Si le caractère courant est une lettre
                    if(LookIf_IsACharacter()) state = 1;
                    else state = 6;
                    break;
                case 1 :
                    // Si c'est du text (nom de la fonction)
                    currentReading += ReadCharacter();
                    if(LookIf_IsACharacter()) break;
                    else {
                        m_functionName = currentReading;
                        currentReading = "";
                        if(LookNextToken("(")) state = 2;
                        else state = 6;
                    }
                    break;
                case 2:
                    // Si c'est une parenthèse ouverte
                    ReadCharacter();
                    if(LookIf_IsACharacter()) state = 3;
                    else state = 6;
                    break;
                case 3:
                    // Si c'est du text entre parenthèse
                    currentReading += ReadCharacter();
                    if(LookIf_IsACharacter()) break;
                    else if(LookNextToken(")")) {
                        m_variableName = currentReading;
                        currentReading = "";
                        state = 4;
                    }
                    else state = 6;
                    break;
                case 4:
                    // SI c'est une parenthèse fermante
                    ReadCharacter();
                    if(LookNextToken("=")) state = 5;
                    else state = 6;
                    break;
                case 5:
                    // Si c'est un égal, on le lis
                    ReadCharacter();
                    mustStop = true;
                    break;
                case 6:
                    mustStop = true;
                    throw new IllegalArgumentException();

            }
        }
    }


    private void ComputeAutomatLogic()
    {
        int state = 0;
        String currentReading = "";
        if(hasReachTheEnd()) throw new IllegalArgumentException();
        while(!hasReachTheEnd()) {
//            System.out.println("index : " + m_readingHead + "\t charAt : " + m_expression.charAt(m_readingHead) + "\t state : " + state);
            switch (state) {
                case 0:
                    if(TryReadNextToken("-")) state = 1;
                    else if(LookIf_Charater_IsANumber()) state = 2;
                    else if(LoofIf_IsAMathFunction()) state = 5;
                    else if(LookNextToken(m_variableName)) state = 6;
                    else if(TryReadNextToken("(")) state = 7;
                    else state = 9;
                    break;
                case 1:
                    m_tree = m_tree.InsertNode(new OppositeStandaloneNode());
                    if(LookIf_Charater_IsANumber()) state = 2;
                    else if(LoofIf_IsAMathFunction()) state = 5;
                    else if(LookNextToken(m_variableName)) state = 6;
                    else state = 9;
                    break;
                case 2:
                    currentReading += ReadAStringNumber();
                    try{Float.parseFloat(currentReading);}catch(Exception e){throw new IllegalArgumentException(e.getMessage());}
                    if(TryReadNextToken(".") || TryReadNextToken(",")) {
                        state = 3;
                        break;
                    }
                    else {
                        m_tree = m_tree.InsertNode(new StandaloneNode(Float.parseFloat(currentReading)));
                        currentReading = "";
                    }

                    if(LookIf_IsAnOperator()) state = 4;
                    else if(LoofIf_IsAMathFunction()) state = 5;
                    else if(LookNextToken(m_variableName)) state = 6;
                    else if(TryReadNextToken("(")) state = 7;
                    else if(TryReadNextToken(")")) state = 8;
                    else state = 9;
                    break;
                case 3:
                    currentReading += ".";
                    if(LookIf_Charater_IsANumber()) state = 2;
                    else state = 9;
                    break;
                case 4:
                    m_tree = m_tree.InsertNode(new OperatorNode(ReadCharacter()));
                    if(LookIf_Charater_IsANumber()) state = 2;
                    else if(TryReadNextToken("(")) state = 7;
                    else if(LookNextToken(m_variableName)) state = 6;
                    else if(LoofIf_IsAMathFunction()) state = 5;
                    else state = 9;
                    break;
                case 5:
                    m_tree = m_tree.InsertNode(new MathWrapNode(ReadAMathFunction()));
                    if(TryReadNextToken("(")) state = 7;
                    else state = 9;
                    break;
                case 6:
                    TryReadNextToken(m_variableName);
                    m_tree = m_tree.InsertNode(new VariableStandaloneNode());
                    if(TryReadNextToken("(")) state = 7;
                    else if(TryReadNextToken(")")) state = 8;
                    else if(LookIf_IsAnOperator()) state = 4;
                    else state = 9;
                    break;
                case 7:
                    m_parentheseCounter++;
                    m_tree = m_tree.InsertNode(new WrapNode());
                    if(TryReadNextToken("(")) break;
                    else state = 0;
                    break;
                case 8:
                    if(--m_parentheseCounter < 0) { state = 9; break; }
                    m_tree = m_tree.getAnchor()._parent;
                    if(m_tree.getAnchor() instanceof MathWrapNode) m_tree = m_tree._parent;

                    if(TryReadNextToken(")")) break;
                    else if(LookNextToken(m_variableName)) state = 6;
                    else if(LoofIf_IsAMathFunction()) state = 5;
                    else if(LookIf_Charater_IsANumber()) state = 2;
                    else if(LookIf_IsAnOperator()) state = 4;
                    break;
                case 9:
                    throw new IllegalArgumentException();
            }
        }

    }


    private boolean LookIf_IsACharacter()
    {
        try {
            if (m_expression.substring(
                    m_readingHead, m_readingHead + 1).matches("^[a-zA-Z]+.?$")) {
                return true;
            }
        }catch (StringIndexOutOfBoundsException e) {return false;}
        return false;
    }


    private boolean TryReadNextToken(String tokenToLookup)
    {
        try {
            if (m_expression.substring(
                    m_readingHead, m_readingHead + tokenToLookup.length()).equals(tokenToLookup)) {
                m_readingHead += tokenToLookup.length();
                return true;
            }
        }catch (StringIndexOutOfBoundsException e) {return false;}
        return false;
    }


    private boolean LookNextToken(String tokenToLookup)
    {
        try {
            if (m_expression.substring(
                    m_readingHead, m_readingHead + tokenToLookup.length()).equals(tokenToLookup)) {
                return true;
            }
        }catch (StringIndexOutOfBoundsException e) {return false;}
        return false;
    }


    private boolean LookIf_Charater_IsANumber()
    {
        try{Integer.parseInt("" + m_expression.charAt(m_readingHead));}
        catch (Exception e){return false;}

        return true;
    }


    private String ReadCharacter()
    {
        if(m_readingHead >= m_expression.length()) return null;
        return "" + m_expression.charAt(m_readingHead++);
    }


    private String LookCharacter()
    {
        if(m_readingHead >= m_expression.length()) return null;
        return "" + m_expression.charAt(m_readingHead);
    }


    private String ReadAStringNumber()
    {
        String number = "";
        while(LookIf_Charater_IsANumber())
            number += ReadCharacter();
        return number;
    }


    private boolean hasReachTheEnd()
    {
        return m_readingHead >= m_expression.length();
    }


    private boolean LoofIf_IsAMathFunction()
    {
        for (MathFunctions functionName : MathFunctions.values())
            if(LookNextToken(functionName.toString().toLowerCase()))
                return true;
        return false;
    }


    private boolean LookIf_IsAnOperator()
    {
        return (Operators.GuessOperationType_FromSign(LookCharacter()) != null);
    }


    private MathFunctions ReadAMathFunction()
    {
        for (MathFunctions functionName : MathFunctions.values())
            if(TryReadNextToken(functionName.toString().toLowerCase()))
                return functionName;
        return null;
    }











}
