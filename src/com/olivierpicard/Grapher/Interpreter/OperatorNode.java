package com.olivierpicard.Grapher.Interpreter;


public class OperatorNode extends ANode
{
    private Operators _operator;
    protected String _operatorSign;
    protected ANode _leftNode, _rightNode;
    protected float m_lastResult;

    public Operators get_operator() { return _operator; }


    public OperatorNode(String operatorSign, ANode LeftNode, ANode RightNode)
    {
        _operator = Operators.GuessOperationType_FromSign(operatorSign);
        _operatorSign = operatorSign;
        _leftNode = LeftNode;
        _rightNode = RightNode;
    }


    public OperatorNode(String operatorSign, ANode RightNode)
    {
        _operator = Operators.GuessOperationType_FromSign(operatorSign);
        _operatorSign = operatorSign;
        _rightNode = RightNode;
    }


    public OperatorNode(String operatorSign)
    {
        _operator = Operators.GuessOperationType_FromSign(operatorSign);
        _operatorSign = operatorSign;
    }


    private void AddOnLeftNode(ANode node)
    {
        node._parent = this;
        _leftNode = node;
    }


    private void AddOnRightNode(ANode node)
    {
        node._parent = this;
        _rightNode = node;
    }


    /**
     * Si on ajoute un opérateur à une constante la constante est sur le fils gauche de l'opérateur
     * Si on ajoute une constante à un opérateur la constante est à droite (place de libre)
     * Si on ajoute un opérateur1 plus prioritaire à un autre oéprateur2
     *      Le fils gauche de op1 prend la valeur fils droit op2
     *      et op1 devient le fils droit de op2
     * Si on ajoute un opérateur1 avec une priorité inférieur ou égale à l'opérateur2
     *      On parcours les parents
     *          Si on trouve un opérateur parent (op2) avec une priorité moindre
     *              Le fils gauche de op1 prend la valeur du fils gauche de op2
     *              et le fils gauche de op2 prend la valeur op1
     *          Sinon s'il n'y a plus de parent
     *              Le fils gauche de op1 prend la valeur du root
     */
    @Override
    public ANode InsertNode(ANode node)
    {
        if(node instanceof WrapNode || node instanceof StandaloneNode) {
            AddOnRightNode(node);
        }
        else if(node instanceof OperatorNode) {
            OperatorNode opNode = (OperatorNode)node;
            if(opNode._operator.GetPriority() > _operator.GetPriority()) {
                opNode.AddOnLeftNode(_rightNode);
                AddOnRightNode(opNode);
            }
            else {
                ANode currentParent = _parent;
                while (!(currentParent instanceof WrapNode)
                        && ((OperatorNode)currentParent)._operator.GetPriority() > opNode._operator.GetPriority())
                    currentParent = currentParent._parent;

                if(currentParent instanceof WrapNode)  {
                    WrapNode anchor = (WrapNode)currentParent;
                    opNode.AddOnLeftNode(anchor.getChild());
                    anchor.InsertNode(opNode);
                }
                else {
                    opNode.AddOnLeftNode(((OperatorNode)currentParent)._leftNode);
                    ((OperatorNode)currentParent).AddOnLeftNode(opNode);
                }
            }
        }
        return node;
    }


    @Override
    public float Compute(float argument)
    {
        float result = 0f;
        switch (_operator) {
            case ADDITION:
                result = _leftNode.Compute(argument) + _rightNode.Compute(argument);
                break;
            case SUBSTRACTION:
                result = _leftNode.Compute(argument) - _rightNode.Compute(argument);
                break;
            case MULTIPLICATION:
                result = _leftNode.Compute(argument) * _rightNode.Compute(argument);
                break;
            case DIVISION:
                result = _leftNode.Compute(argument) / _rightNode.Compute(argument);
                break;
            case SQUARE:
                result = (float)Math.pow(_leftNode.Compute(argument), _rightNode.Compute(argument));
                break;
        }

        return result;
    }


    @Override
    public String toString()
    {
        return ("(" +_leftNode + _operatorSign + " " + _rightNode + ") = " + Compute(0));
    }




}
