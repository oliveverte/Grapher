package com.olivierpicard.Grapher;

import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.History;
import com.olivierpicard.Grapher.DataManager.Register;
import com.olivierpicard.Grapher.DataManager.RegisterActions.RegisterAddAction;
import com.olivierpicard.Grapher.Interpreter.Parser;
import com.olivierpicard.Grapher.Tools.VisualTools.Theme;
import com.olivierpicard.Grapher.Tools.VisualTools.ThemeRefreshable;

import javax.swing.*;
import java.awt.*;

public class ViewController extends JFrame implements ThemeRefreshable
{
    public static Theme theme = Theme.LIGHT;

    private SidePanel m_sidePanel;
    private Scene m_scene;
    private JSplitPane m_splitPane;


    public ViewController()
    {
        super("Grapher");
        setLayout(new BorderLayout());

        m_scene = new Scene(this);
        m_sidePanel = new SidePanel(this);


        m_splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        m_splitPane.setBackground(theme.get_backgroundColor());
        m_splitPane.setRightComponent(m_scene);
        m_splitPane.setLeftComponent(m_sidePanel);
        m_splitPane.setResizeWeight(0.15);
        add(m_splitPane, BorderLayout.CENTER);

        setSize(800, 700);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        requestFocusInWindow();

        ExecFunctionInterpreter("f(x)=sin(x)");
        ExecFunctionInterpreter("g(x)=x^2");
        ExecFunctionInterpreter("h(x)=2*x");

        RefreshTheme();
    }


    public void OnSwitchTheme()
    {
        if(theme == Theme.LIGHT) theme = Theme.DARK;
        else theme = Theme.LIGHT;
        RefreshTheme();
    }


    public void RefreshTheme()
    {
        getContentPane().setBackground(theme.get_backgroundColor());
        m_splitPane.setBackground(theme.get_separatorColor());
        m_sidePanel.RefreshTheme();
        m_scene.RefreshTheme();
    }


    public void Refresh()
    {
        m_sidePanel.Refresh();
        m_scene.Refresh();
    }


    public void ExecFunctionInterpreter(String function)
    {
        // TODO : Faire la capture des erreur et empêcher d'afficher
        final DataDrawable data = new Parser(function).Interpret();
        History.Write(new RegisterAddAction(data));
        Refresh();
    }
}
