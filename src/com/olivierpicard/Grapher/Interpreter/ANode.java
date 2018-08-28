package com.olivierpicard.Grapher.Interpreter;

public abstract class ANode extends Object
{
    protected ANode _parent;
    public abstract float Compute(float argument);
    public abstract ANode InsertNode(ANode node);
    public abstract String toString();


    public ANode get_parent() {
        return _parent;
    }


    public WrapNode getAnchor()
    {
        ANode currentNode = this;
        while(!(currentNode instanceof WrapNode))
            currentNode = currentNode._parent;
        return (WrapNode)currentNode;
    }


    public WrapNode getRoot()
    {
        ANode currentNode = this;
        while(currentNode._parent != null)
            currentNode = currentNode._parent;
        return (WrapNode)currentNode;
    }

}
