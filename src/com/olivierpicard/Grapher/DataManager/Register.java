package com.olivierpicard.Grapher.DataManager;

import java.util.ArrayList;
import java.util.List;


public class Register {
    private static List<DataDrawable> datas = new ArrayList<>();


    public static void Add(DataDrawable data)
    {
        datas.add(data);
    }


    public static void Remove(DataDrawable data)
    {
        datas.remove(data);
    }


    public static void Edit(DataDrawable data)
    {
        for(int i = 0; i < datas.size(); i++) {
            if(datas.get(i).get_id() != data.get_id()) continue;
            datas.set(i, data);
        }
    }


    public static void Clear()
    {
        datas.clear();
    }


    public static DataDrawable[] Read()
    {
        return datas.toArray(new DataDrawable[datas.size()]);
    }


    public static void Selected(int index)
    {
        UnselectAll();
        if(index > -1)
            datas.get(index).set_isSelected(true);

    }


    public static void UnselectAll()
    {
        for(int i = 0; i < datas.size(); i++)
            datas.get(i).set_isSelected(false);
    }

}
