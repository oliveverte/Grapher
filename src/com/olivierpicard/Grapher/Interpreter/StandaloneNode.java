package com.olivierpicard.Grapher.Interpreter;

public class StandaloneNode extends ANode
{
    protected float _value;


    public StandaloneNode(float value)
    {
        _value = value;
    }
    protected StandaloneNode(){_value = 0f;}


    @Override
    public float Compute(float argument) {
        return _value;
    }


    @Override
    public ANode InsertNode(ANode node)
    {
        if(node instanceof OperatorNode)
        {
            if(_parent instanceof OperatorNode) return _parent.InsertNode(node);
            else {
                OperatorNode operator = (OperatorNode) node;
                _parent.InsertNode(node);
                operator._leftNode = this;
                _parent = operator;
            }
        }
        else if(node instanceof StandaloneNode || node instanceof WrapNode)
        {
            this.InsertNode(new OperatorNode("*"));
            _parent.InsertNode(node);
        }
        else throw new IllegalArgumentException();

        return node;
    }


    @Override
    public String toString() {
        return Compute(0) + " ";
    }


}


