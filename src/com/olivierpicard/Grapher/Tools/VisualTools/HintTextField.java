package com.olivierpicard.Grapher.Tools.VisualTools;

import com.olivierpicard.Grapher.ViewController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HintTextField extends JTextField implements FocusListener, DocumentListener, PropertyChangeListener, ThemeRefreshable {
    private boolean m_isEmpty;
    private Color m_hintColor;
    private Color m_textColor;
    private final String m_hintText;

    public HintTextField(String hintText) {
        super();

        m_hintText = hintText;
        m_hintColor = ViewController.theme.get_lightForgroundColor();

        addFocusListener(this);
        registerListeners();
        updateState();

        if (!hasFocus()) focusLost(null);
    }

    public void delete() {
        unregisterListeners();
        removeFocusListener(this);
    }

    private void registerListeners() {
        getDocument().addDocumentListener(this);
        addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners() {
        getDocument().removeDocumentListener(this);
        removePropertyChangeListener("foreground", this);
    }

    public Color get_hintColor() {
        return m_hintColor;
    }

    public void set_hintColor(Color m_hintColor) {
        this.m_hintColor = m_hintColor;
    }

    private void updateState() {
        m_isEmpty = (getText().length() == 0);
        m_textColor = getForeground();
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (m_isEmpty) {
            unregisterListeners();
            try {
                setText("");
                setForeground(m_textColor);
            } finally {
                registerListeners();
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (m_isEmpty) {
            unregisterListeners();
            try {
                setText(m_hintText);
                setForeground(m_hintColor);
            } finally {
                registerListeners();
            }
        }
    }


    public void RefreshTheme()
    {
        m_textColor = ViewController.theme.get_foregroundColor();
        m_hintColor = ViewController.theme.get_lightForgroundColor();
    }


    public void setForeground(Color fg)
    {
        super.setForeground(fg);
        m_textColor = fg;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateState();
    }

}
