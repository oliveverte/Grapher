package com.olivierpicard.Grapher;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.History;
import com.olivierpicard.Grapher.DataManager.Register;
import com.olivierpicard.Grapher.DataManager.RegisterActions.RegisterRemoveAction;
import com.olivierpicard.Grapher.Tools.VisualTools.CellDataRenderer;
import com.olivierpicard.Grapher.Tools.VisualTools.HintTextField;
import com.olivierpicard.Grapher.Tools.VisualTools.ThemeRefreshable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SidePanel extends JPanel implements ThemeRefreshable
{
    private static final int CELL_HEIGHT = 50;

    private DefaultListModel<DataDrawable> m_model;
    private JList<DataDrawable> m_listOfDrawableDatas;
    private JScrollPane m_scrollablePanel;
    private HintTextField m_interpreter_textField;
    private ViewController m_viewController;


    public SidePanel(ViewController viewController)
    {
        setLayout(new BorderLayout());

        m_viewController = viewController;

        m_model = new DefaultListModel<>();
        m_listOfDrawableDatas = new JList<>(m_model);
        m_listOfDrawableDatas.setCellRenderer(new CellDataRenderer());
        m_listOfDrawableDatas.setSelectionBackground(Color.PINK);
        m_listOfDrawableDatas.setFixedCellHeight(CELL_HEIGHT);
        m_listOfDrawableDatas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                SidePanel.this.OnListClick(e);
            }
        });
        m_listOfDrawableDatas.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                Register.UnselectAll();
                m_viewController.Refresh();
            }
        });

        m_scrollablePanel = new JScrollPane();
        m_scrollablePanel.add(m_listOfDrawableDatas);

        m_interpreter_textField = new HintTextField("Saisie...  f(x)=sin(2x)");
        m_interpreter_textField.setPreferredSize(new Dimension(50, 50));
        m_interpreter_textField.setMinimumSize(new Dimension(50, 50));
        m_interpreter_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        m_interpreter_textField.addActionListener(this::OnTextField_Enter);

        add(m_interpreter_textField, BorderLayout.NORTH);
        add(new JScrollPane(m_listOfDrawableDatas), BorderLayout.CENTER);
    }


    public void Refresh()
    {
        m_model.clear();
        final DataDrawable[] data = Register.Read();
        for(int i = 0; i < data.length; i++)
            m_model.add(i, data[i]);

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
        Refresh();
    }


    public void OnListClick(MouseEvent e)
    {

        Point p = e.getPoint();
        final int minX = getSize().width - CellDataRenderer.BUTTON_SIZE;
        final int maxX = getSize().width;

        final int index = m_listOfDrawableDatas.locationToIndex(e.getPoint());
        if(index > -1) {
            Register.Selected(index);
            if (p.x > minX && p.x < maxX)
                History.Write(new RegisterRemoveAction(m_listOfDrawableDatas.getSelectedValue()));
        }
        else Register.UnselectAll();


        m_viewController.Refresh();
    }
}
