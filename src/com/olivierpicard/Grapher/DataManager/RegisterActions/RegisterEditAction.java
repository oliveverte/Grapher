package com.olivierpicard.Grapher.DataManager.RegisterActions;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.Register;

public class RegisterEditAction extends RegisterAction
{
    public RegisterEditAction(DataDrawable data) {
        super(data);
    }


    @Override
    public void DoAction() {
        Register.Edit(m_data);
    }
}
