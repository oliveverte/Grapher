package com.olivierpicard.Grapher.DataManager.RegisterActions;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.Register;

public abstract class RegisterAction
{
    protected DataDrawable m_data;

    public abstract void DoAction();


    public RegisterAction(DataDrawable data)
    {
        m_data = data;
    }

}
