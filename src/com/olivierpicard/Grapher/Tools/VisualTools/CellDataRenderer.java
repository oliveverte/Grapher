package com.olivierpicard.Grapher.Tools.VisualTools;

import com.olivierpicard.Grapher.DataManager.DataDrawable;

import javax.swing.*;
import java.awt.*;

public class CellDataRenderer extends JPanel implements ListCellRenderer<DataDrawable>
{
    public static final int BUTTON_SIZE = 25;
    private JLabel m_colorTag, m_title, m_delete;


    public CellDataRenderer()
    {
        setLayout(new BorderLayout(10,10));

        Image img = new ImageIcon("delete_1.png").getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE,  Image.SCALE_SMOOTH);

        m_colorTag = new JLabel();
        m_title = new JLabel();
        m_delete = new JLabel();

        m_delete.setIcon(new ImageIcon(img));
        m_delete.setHorizontalAlignment(SwingConstants.RIGHT);

        add(m_colorTag, BorderLayout.WEST);
        add(m_title, BorderLayout.CENTER);
        add(m_delete, BorderLayout.EAST);
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
        } else {
            setBackground(list.getBackground());
            m_title.setForeground(list.getForeground());
        }
        return this;
    }
}
