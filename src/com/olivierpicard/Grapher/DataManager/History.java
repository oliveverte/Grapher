package com.olivierpicard.Grapher.DataManager;

import com.olivierpicard.Grapher.DataManager.RegisterActions.RegisterAction;

import java.util.ArrayList;
import java.util.List;

public class History {
    private static List<RegisterAction> m_historyData = new ArrayList<>();
    private static int m_readingSize;


    public static RegisterAction[] Write(RegisterAction action)
    {
        if(m_readingSize < m_historyData.size())
            for(int i = m_readingSize; i < m_historyData.size(); i++)
                m_historyData.remove(i);

        m_historyData.add(action);
        action.DoAction();


        return m_historyData.toArray(
                new RegisterAction[m_historyData.size()]
        );
    }


    public static void Undo()
    {
        if(--m_readingSize < 0) m_readingSize = 0;
        Register.Refresh();
    }


    public static void Redo()
    {
        if(++m_readingSize > m_historyData.size())
            m_readingSize = m_historyData.size() - 1;
        Register.Refresh();
    }


    public static RegisterAction[] Read()
    {
        final RegisterAction[] actions = new RegisterAction[m_readingSize +1];
        for(int i = 0; i < m_readingSize; i++)
            actions[i] = m_historyData.get(i);
        return actions;
    }


}
