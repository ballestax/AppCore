/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.gui;

import com.core.Aplication;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ballestax
 */
public class GuiPanelMenu extends JPanel {

    private Aplication app;

    public GuiPanelMenu(Aplication app) {
        this.app = app;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(10, 1));
        GridLayout layout = (GridLayout)getLayout();
        layout.setHgap(10);
        layout.setVgap(10);
    }

    public void addMenu(String title) {
        addMenu(title, 0, null);
    }

    public void addMenu(String title, int i, Icon icon) {
        JButton btn = new JButton();
        btn.setText(title);
        if (icon != null) {
            btn.setIcon(icon);
        }
        btn.setPreferredSize(new Dimension(150,20));
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        
        add(btn);
    }

}
