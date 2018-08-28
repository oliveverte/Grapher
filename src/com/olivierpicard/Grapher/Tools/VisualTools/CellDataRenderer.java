package com.olivierpicard.Grapher.Tools.VisualTools;

import com.olivierpicard.Grapher.DataManager.DataDrawable;

import javax.swing.*;
import java.awt.*;

public class CellDataRenderer extends JPanel implements ListCellRenderer<DataDrawable>
{
    private JLabel m_colorTag, m_title;


    public CellDataRenderer()
    {
        setLayout(new BorderLayout(10,10));
        m_colorTag = new JLabel();
        m_title = new JLabel();
        add(m_colorTag, BorderLayout.WEST);
        add(m_title, BorderLayout.CENTER);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends DataDrawable> list, DataDrawable value, int index, boolean isSelected, boolean cellHasFocus)
    {
        m_colorTag.setBackground(value.get_color());
        m_colorTag.setText("              ");
        m_colorTag.setOpaque(true);
        m_title.setText(value.toString());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            m_title.setForeground(list.getSelectionForeground());
        } else { // when don't select
            setBackground(list.getBackground());
            m_title.setForeground(list.getForeground());
        }
        return this;
    }
}
