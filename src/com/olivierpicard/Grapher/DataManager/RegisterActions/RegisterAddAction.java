package com.olivierpicard.Grapher.DataManager.RegisterActions;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.Register;

public class RegisterAddAction extends RegisterAction
{
    public RegisterAddAction(DataDrawable data) {
        super(data);
    }


    @Override
    public void DoAction() {
        Register.Add(m_data);
    }
}
