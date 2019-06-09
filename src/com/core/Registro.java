/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Document;
import org.bx.gui.MyDatePickerImp;

public class Registro extends Box implements Reseteable, CaretListener {

    public Registro(int axis, String stLabel, String stCampo) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, int width) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.width = width;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, Font fontLabel) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.fontLabel = fontLabel;
        fontCampo = fontCampoDefault;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, Font fontLabel, Document docLim) {
        super(axis);
        bordered = true;
        this.docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.fontLabel = fontLabel;
        fontCampo = fontCampoDefault;
        this.docLim = docLim;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, Font fontLabel, AbstractAction action, Document docLim) {
        super(axis);
        bordered = true;
        this.docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.action = action;
        this.fontLabel = fontLabel;
        fontCampo = fontCampoDefault;
        this.docLim = docLim;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, int width, Font fontLabel) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.width = width;
        this.fontLabel = fontLabel;
        fontCampo = fontCampoDefault;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, int width, Font fontLabel, Document docLim) {
        super(axis);
        bordered = true;
        this.docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.width = width;
        bordered = true;
        this.fontLabel = fontLabel;
        fontCampo = fontCampoDefault;
        this.docLim = docLim;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, int width, AbstractAction action, Font fontLabel) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.width = width;
        this.action = action;
        this.fontLabel = fontLabel;
        fontCampo = fontCampoDefault;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, String stCampo, int width, AbstractAction action) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        this.stCampo = stCampo;
        this.width = width;
        this.action = action;
        fontLabel = fontLabelDefault;
        fontCampo = fontCampoDefault;
        inicializar(null);
    }

    public Registro(int axis, String stLabel, JComponent campo) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        stCampo = "";
        fontLabel = fontLabelDefault;
        fontCampo = fontCampoDefault;
        inicializar(campo);
    }

    public Registro(int axis, String stLabel, JComponent campo, boolean bordered) {
        super(axis);
        this.bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        stCampo = "";
        this.bordered = bordered;
        fontLabel = fontLabelDefault;
        fontCampo = fontCampoDefault;
        inicializar(campo);
    }

    public Registro(int axis, String stLabel, JComponent campo, int width) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        stCampo = "";
        bordered = true;
        this.width = width;
        fontLabel = fontLabelDefault;
        fontCampo = fontCampoDefault;
        inicializar(campo);
    }

    public Registro(int axis, String stLabel, JComponent campo, int width, boolean bordered, Font fontLabel, Font fontCampo) {
        super(axis);
        this.bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        stCampo = "";
        this.bordered = bordered;
        this.width = width;
        this.fontCampo = fontCampo;
        this.fontLabel = fontLabel;
        inicializar(campo);
    }

    public Registro(int axis, String stLabel, JComponent campo, int width, Font fontLabel, Font fontCampo) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        stCampo = "";
        bordered = false;
        this.width = width;
        this.fontCampo = fontCampo;
        this.fontLabel = fontLabel;
        inicializar(campo);
    }

    public Registro(int axis, String stLabel, JComponent campo, int width, Font fontLabel) {
        super(axis);
        bordered = true;
        docLim = null;
        fontCampoDefault = new Font("Tahoma", 1, 14);
        fontLabelDefault = new Font("Arial", 0, 11);
        this.axis = axis;
        this.stLabel = stLabel;
        stCampo = "";
        bordered = bordered;
        this.width = width;
        fontCampo = fontCampoDefault;
        this.fontLabel = fontLabel;
        inicializar(campo);
    }

    public synchronized void addFocusListener(FocusListener l) {
        super.addFocusListener(l);
        if (campo != null) {
            campo.addFocusListener(l);
        }
    }

    private void inicializar(JComponent componente) {
        UIManager.put("ComboBox.disabledBackground", Color.white);
        UIManager.put("ComboBox.disabledForeground", new Color(100, 100, 100));
        bordeError = BorderFactory.createLineBorder(Color.red, 1, true);
        bordeNormal = BorderFactory.createLineBorder(Color.darkGray, 1, true);
        bordeEditing = BorderFactory.createLineBorder(Color.blue, 1, true);
        if (bordered) {
            setBorder(BorderFactory.createLineBorder(Color.darkGray, 1, true));
        }
        setAlignmentX(1.0F);
        if (componente == null) {
            campo = new JTextField(stCampo);
            campo.setBorder(null);
            if (docLim != null) {
                ((JTextField) campo).setDocument(docLim);
            }
            ((JTextField) campo).addCaretListener(this);
        } else {
            campo = componente;
            if (campo instanceof JTextField) {
                ((JTextField) campo).addCaretListener(this);
            }
        }
        campo.setFont(fontCampo);
        label = new JLabel((new StringBuilder()).append(" ").append(stLabel).append(" ").toString());
        if (axis == 0) {
            campo.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
        } else {
            campo.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
        }
        campo.setPreferredSize(new Dimension(width, 20));
        campo.setMinimumSize(new Dimension(width, 20));
        FocusListener focusListeners[] = campo.getFocusListeners();
        FocusListener arr$[] = focusListeners;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            FocusListener focusListener = arr$[i$];
            campo.addFocusListener(focusListener);
        }
        label.setFont(fontLabel);
        label.setHorizontalAlignment(2);
        add(label);
        boolean conIcono = false;
        Box boxH = null;
        if (action != null) {
            JButton icono = new JButton(action);
            icono.setPreferredSize(new Dimension(18, 18));
            boxH = new Box(0);
            boxH.add(campo);
            boxH.add(icono);
            conIcono = true;
        }
        add(((Component) (conIcono ? ((Component) (boxH)) : ((Component) (campo)))));
    }

    public void reset() {
        if (campo instanceof JTextField) {
            ((JTextField) campo).setText("");
        } else if (campo instanceof MyDatePickerImp) {
            ((MyDatePickerImp) campo).setText("");
        }
    }

    public Component getComponent() {
        return campo;
    }

    public String getText() {
        if (campo instanceof JTextField) {
            return ((JTextField) campo).getText();
        }
        if (campo instanceof JComboBox) {
            JComboBox cb = ((JComboBox) campo);
            if (cb.getSelectedItem() != null) {
                return ((JComboBox) campo).getSelectedItem().toString();
            } else {
                return "";
            }
        }
        if (campo instanceof MyDatePickerImp) {
            return ((MyDatePickerImp) campo).getText();
        } else {
            return "";
        }
    }

    public void setText(String text) {
        if (campo instanceof JTextField) {
            ((JTextField) campo).setText(text);
        } else if (campo instanceof JComboBox) {
            ((JComboBox) campo).setSelectedItem(text);
        } else if (campo instanceof MyDatePickerImp) {
            ((MyDatePickerImp) campo).setText(text);
        }
    }

    public void setText(String[] text) {
        if (campo instanceof JComboBox) {
            ((JComboBox) campo).setModel(new DefaultComboBoxModel<>(text));
        }
    }

    public int getSelected() {
        if (campo instanceof JComboBox) {
            return ((JComboBox) campo).getSelectedIndex();
        } else {
            return 0;
        }
    }

    public void setLabelFont(Font fontLabel) {
        this.fontLabel = fontLabel;
        label.setFont(fontLabel);
    }

    public void setCampoFont(Font fontCampo) {
        this.fontCampo = fontCampo;
        campo.setFont(fontCampo);
    }

    public void addCaretListener(CaretListener caretListener) {
        if (campo instanceof JTextField) {
            ((JTextField) campo).addCaretListener(caretListener);
        }
    }

    public void caretUpdate(CaretEvent e) {
        if (getBorder().equals(bordeError)) {
            setBorderToNormal();
        }
    }

    public Border getBordeNormal() {
        return bordeNormal;
    }

    public Border getBordeError() {
        return bordeError;
    }

    public void setBordeError(Border bordeError) {
        this.bordeError = bordeError;
    }

    public void setBordeNormal(Border bordeNormal) {
        this.bordeNormal = bordeNormal;
    }

    public void setBorderToError() {
        setBorder(bordeError);
    }

    public void setBorderToNormal() {
        setBorder(bordeNormal);
    }

    public void setBorderToEditing() {
        setBorder(bordeEditing);
    }

    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (campo instanceof JTextField) {
            ((JTextField) campo).setForeground(fg);
        }
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        label.setEnabled(enabled);
        campo.setEnabled(enabled);
    }

    public void setEditable(boolean editable) {
        if (editable) {
            setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        } else {
            setBorder(BorderFactory.createLineBorder(new Color(125, 142, 174), 1, true));
        }
        if (campo instanceof JTextField) {
            Color background = ((JTextField) campo).getBackground();
            ((JTextField) campo).setEditable(editable);
            ((JTextField) campo).setBackground(background);
            if (editable) {
                ((JTextField) campo).setForeground(Color.black);
            } else {
                ((JTextField) campo).setForeground(new Color(100, 100, 100));
            }
        } else if (campo instanceof JComboBox) {
            Color foreground = ((JComboBox) campo).getForeground();
            ((JComboBox) campo).setEnabled(editable);
            ((JComboBox) campo).setForeground(foreground);
        } else if (campo instanceof MyDatePickerImp) {
            ((MyDatePickerImp) campo).setEditable(editable);
        }
    }

    public void setActionCommand(String actionCommand) {
        if (campo != null) {
            if (campo instanceof JComboBox) {
                ((JComboBox) campo).setActionCommand(actionCommand);
            }
        }
    }

    public void addActionListener(ActionListener listener) {
        if (campo != null) {
            if (campo instanceof JComboBox) {
                ((JComboBox) campo).addActionListener(listener);
            }
        }
    }

    protected Border bordeError;
    protected Border bordeNormal;
    protected Border bordeEditing;
    private JLabel label;
    private JComponent campo;
    private final String stCampo;
    private final String stLabel;
    private boolean bordered;
    private int width;
    private AbstractAction action;
    private Font fontLabel;
    private Font fontCampo;
    private Document docLim;
    private final Font fontCampoDefault;
    private final Font fontLabelDefault;
    private int axis;
}
