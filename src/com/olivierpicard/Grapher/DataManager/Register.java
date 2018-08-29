package com.olivierpicard.Grapher.DataManager;

import com.olivierpicard.Grapher.DataManager.RegisterActions.RegisterAction;
import java.util.ArrayList;
import java.util.List;


public class Register {
    private static List<DataDrawable> m_datas = new ArrayList<>();


    public static void Add(DataDrawable data)
    {
        m_datas.add(data);
    }


    public static void Remove(DataDrawable data)
    {
        m_datas.remove(data);
    }


    public static void Edit(DataDrawable data)
    {
        for(int i = 0; i < m_datas.size(); i++) {
            if(m_datas.get(i).get_id() != data.get_id()) continue;
            m_datas.set(i, data);
        }
    }

    public static void Clear()
    {
        m_datas.clear();
    }


    public static DataDrawable[] Read()
    {
        return m_datas.toArray(new DataDrawable[m_datas.size()]);
    }

}
