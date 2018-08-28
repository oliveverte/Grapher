package com.olivierpicard.Grapher.Interpreter;

public class WrapNode extends ANode
{
    protected ANode _child;


    public ANode getChild() {
        return _child;
    }


    @Override
    public float Compute(float argument) {
        return _child.Compute(argument);
    }


    @Override
    public ANode InsertNode(ANode node) {
        if(node instanceof OperatorNode)
            ((OperatorNode)node)._leftNode = _child;
        _child = node;
        node._parent = this;
        return node;
    }


    @Override
    public String toString() {
        return _child.toString();
    }
}
