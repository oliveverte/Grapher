package com.olivierpicard.Grapher.Interpreter;

public class OppositeStandaloneNode extends StandaloneNode
{
    public OppositeStandaloneNode() {super();}
    public OppositeStandaloneNode(StandaloneNode value) {
        super(value._value);
    }


    @Override
    public ANode InsertNode(ANode node)
    {
        if(node instanceof StandaloneNode) {
            _value = ((StandaloneNode) node)._value;
            return this;
        }
        else return super.InsertNode(node);

    }


    @Override
    public float Compute(float argument) {
        return _value * -1;
    }
}
