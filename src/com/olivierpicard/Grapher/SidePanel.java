package com.olivierpicard.Grapher;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.Register;
import com.olivierpicard.Grapher.Tools.VisualTools.CellDataRenderer;
import com.olivierpicard.Grapher.Tools.VisualTools.HintTextField;
import com.olivierpicard.Grapher.Tools.VisualTools.ThemeRefreshable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SidePanel extends JPanel implements ThemeRefreshable
{
    private final int CELL_HEIGHT = 50;

    private JList<DataDrawable> m_listOfDrawableDatas;
    private JScrollPane m_scrollablePanel;
    private HintTextField m_interpreter_textField;
    private ViewController m_viewController;


    public SidePanel(ViewController viewController)
    {
        setLayout(new BorderLayout());

        m_viewController = viewController;

        m_listOfDrawableDatas = new JList<>();
        m_listOfDrawableDatas.setCellRenderer(new CellDataRenderer());
        m_listOfDrawableDatas.setSelectionBackground(Color.PINK);
        m_listOfDrawableDatas.setFixedCellHeight(CELL_HEIGHT);

        m_scrollablePanel = new JScrollPane();
        m_scrollablePanel.add(m_listOfDrawableDatas);

        m_interpreter_textField = new HintTextField("Saisie...  f(x)=sin(2x)");
        m_interpreter_textField.setPreferredSize(new Dimension(50, 50));
        m_interpreter_textField.setMinimumSize(new Dimension(50, 50));
        m_interpreter_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        m_interpreter_textField.addActionListener(e -> OnTextField_Enter(e));

        add(m_interpreter_textField, BorderLayout.NORTH);
        add(new JScrollPane(m_listOfDrawableDatas), BorderLayout.CENTER);

        m_scrollablePanel.requestFocus();
    }


    public void Refresh()
    {
        final DefaultListModel<DataDrawable> model = new DefaultListModel<>();
        final DataDrawable[] data = Register.Read();
        for(int i = 0; i < data.length; i++)
            model.add(i, data[i]);
        m_listOfDrawableDatas.setModel(model);
    }


    private void OnTextField_Enter(ActionEvent e)
    {
        m_viewController.ExecFunctionInterpreter(m_interpreter_textField.getText());
        m_interpreter_textField.setText("");
    }


    public void RefreshTheme()
    {
        setBackground(ViewController.theme.get_backgroundColor());
        m_scrollablePanel.setBackground(ViewController.theme.get_backgroundColor());
        m_interpreter_textField.setBackground(ViewController.theme.get_backgroundColor());
        m_listOfDrawableDatas.setBackground(ViewController.theme.get_backgroundColor());
        m_listOfDrawableDatas.setSelectionBackground(ViewController.theme.get_hightlightBackgroundColor());
        m_listOfDrawableDatas.setForeground(ViewController.theme.get_foregroundColor());
        m_listOfDrawableDatas.setSelectionForeground(ViewController.theme.get_inversedForgroundColor());
    }
}
