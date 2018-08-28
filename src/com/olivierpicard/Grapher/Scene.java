package com.olivierpicard.Grapher;
import com.olivierpicard.Grapher.DataManager.DataDrawable;
import com.olivierpicard.Grapher.DataManager.Register;
import com.olivierpicard.Grapher.Tools.Interval2D;
import com.olivierpicard.Grapher.Tools.ScenePoint;
import com.olivierpicard.Grapher.Tools.VisualTools.Button;
import com.olivierpicard.Grapher.Tools.VisualTools.ThemeRefreshable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Function;

public class Scene extends JPanel implements ThemeRefreshable, MouseListener
{
    public enum Space { SCREEN, SCENE }

    public static class Constraint
    {
        public static int gridUnitPixelSize = 64;
        public static float gridUnitValue = 1f;
        public final static float gridUnitResolution = 0.2f; // Alias step
        public static Dimension screenSize;
        public static Interval2D sceneInterval;
        public static ScenePoint originPixelPosition;
    }



    private final int BUTTON_MARGIN = 15;
    private ViewController m_viewController;
    private Axis m_axis;
    private ScenePoint m_deltaOriginePosition;
    private Point m_lastMousePosition;
    private Button[] m_buttons;


    public Scene(ViewController viewController)
    {
        m_viewController = viewController;
        m_deltaOriginePosition = new ScenePoint(0f, 0f);
        m_axis = new Axis();
        Refresh();

        m_buttons = new Button[4];
        m_buttons[0] = new Button(this::OnZoomOutButton, "img/minus.png");
        m_buttons[1] = new Button(this::OnZoomInButton, "img/plus.png");
        m_buttons[2] = new Button(this::OnCenterButton, "img/target.png");
        m_buttons[3] = new Button(this::OnThemeButton, "img/theme.png");

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Refresh();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Scene.this.mouseDragged(e);
            }
        });
        addMouseListener(this);
    }


    public void mouseEntered (MouseEvent mouseEvent) {}
    public void mouseExited (MouseEvent mouseEvent) {}
    public void mouseReleased (MouseEvent mouseEvent) {}
    public void mouseClicked (MouseEvent mouseEvent) {}


    public void RefreshTheme()
    {
        setBackground(ViewController.theme.get_backgroundColor());
        for(Button button : m_buttons) button.RefreshTheme();
        Refresh();
    }



    public void Refresh()
    {
        Constraint.screenSize = getSize();
        Constraint.originPixelPosition = new ScenePoint(getWidth()/2f, getHeight()/2f).Add(m_deltaOriginePosition);
        Constraint.sceneInterval = new Interval2D(
                -Constraint.originPixelPosition.get_x() / ((float)Constraint.gridUnitPixelSize / Constraint.gridUnitValue),
                (getSize().width - Constraint.originPixelPosition.get_x()) / ((float)Constraint.gridUnitPixelSize / Constraint.gridUnitValue),
                -(getSize().height - Constraint.originPixelPosition.get_y()) / ((float)Constraint.gridUnitPixelSize / Constraint.gridUnitValue),
                Constraint.originPixelPosition.get_y() / ((float)Constraint.gridUnitPixelSize / Constraint.gridUnitValue)
        );
        repaint();
    }


    public static boolean IsVisible(ScenePoint point)
    {
        return true;
    }


    public void mousePressed (MouseEvent mouseEvent)
    {
        for (Button button : m_buttons)
            button.mouseClicked(mouseEvent);
        m_lastMousePosition = mouseEvent.getPoint();
    }


    private void mouseDragged(MouseEvent e) {
        int dx = e.getX() - m_lastMousePosition.x;
        int dy = e.getY() - m_lastMousePosition.y;
        m_deltaOriginePosition.Add(new ScenePoint(dx, dy));
        m_lastMousePosition = e.getPoint();
        Refresh();
    }


    public void OnZoomOutButton()
    {
        Constraint.gridUnitValue /= 0.5f;
        Refresh();
    }


    public void OnZoomInButton()
    {
        Constraint.gridUnitValue *= 0.5f;
        Refresh();
    }


    public void OnCenterButton()
    {
        m_deltaOriginePosition = new ScenePoint(0f, 0f);
        Constraint.gridUnitValue = 1f;
    }


    public void OnThemeButton()
    {
        m_viewController.OnSwitchTheme();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        m_axis.Draw(g2);
        for(DataDrawable data : Register.Read())
            data.Draw(new FunctionDrawer(g2));

        for(int i = 0; i < m_buttons.length; i++)
            m_buttons[i].Draw(g2,
                    getWidth() - Button.DIAMETER - BUTTON_MARGIN,
                    getHeight() - (Button.DIAMETER - BUTTON_MARGIN)*2*(i+1));
    }
}
