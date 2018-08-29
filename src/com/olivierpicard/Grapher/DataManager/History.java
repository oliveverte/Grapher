package com.olivierpicard.Grapher.DataManager;

import com.olivierpicard.Grapher.DataManager.RegisterActions.RegisterAction;

import java.util.ArrayList;
import java.util.List;

public class History {
    private static List<RegisterAction> m_historyData = new ArrayList<>();
    private static int m_readingSize = -1;


    public static void Write(RegisterAction action)
    {
        if(m_readingSize < m_historyData.size() - 1) {
            for(int i = m_historyData.size() - 1; m_readingSize < i; i--)
                m_historyData.remove(i);
        }

        m_historyData.add(action);
        m_readingSize++;
        action.DoAction();
    }


    public static void Undo()
    {
        if(--m_readingSize < -1) m_readingSize = -1;
        RefreshRegister();
    }


    public static void Redo()
    {
        if(++m_readingSize > m_historyData.size()-1)
            m_readingSize = m_historyData.size()-1;
        RefreshRegister();
    }


    public static boolean isUndoAvailable()
    {
        return m_readingSize > -1;
    }


    public static boolean isRedoAvailable()
    {
        return m_readingSize < m_historyData.size() - 1;
    }


    private static void RefreshRegister()
    {
        Register.Clear();
        for(int i = 0; i <= m_readingSize; i++)
            m_historyData.get(i).DoAction();
    }

}
