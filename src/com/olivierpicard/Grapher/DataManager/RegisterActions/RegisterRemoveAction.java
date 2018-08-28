package com.olivierpicard.Grapher.DataManager.RegisterActions;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.Register;

public class RegisterRemoveAction extends RegisterAction
{
    public RegisterRemoveAction(DataDrawable data) {
        super(data);
    }


    @Override
    public void DoAction() {
        Register.Remove(m_data);
    }
}
