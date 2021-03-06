/*
 * LootDialog.java
 * 
 * Copyright (c) 2009 Jay Lawson <jaylawson39 at yahoo.com>. All rights reserved.
 * 
 * This file is part of MekHQ.
 * 
 * MekHQ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MekHQ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MekHQ.  If not, see <http://www.gnu.org/licenses/>.
 */

package mekhq.gui.dialog;

import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import megamek.common.Entity;
import mekhq.campaign.Campaign;
import mekhq.campaign.mission.Loot;
import mekhq.campaign.parts.Part;

/**
 *
 * @author  Taharqa
 */
public class LootDialog extends javax.swing.JDialog {
    private static final long serialVersionUID = -8038099101234445018L;
    private Frame frame;
    private Loot loot;
    private boolean cancelled;
    private ArrayList<Entity> units;
    private ArrayList<Part> parts;
    private Campaign campaign;
       
    private JButton btnOK;
    private JButton btnCancel;
    private JTextField txtName;
    private JSpinner spnCash;
    private JButton btnAddUnit;
    private JButton btnRemoveUnit;
    private JButton btnAddPart;
    private JButton btnRemovePart;
    private JList<String> listUnits;
    private JList<String> listParts;
    private JScrollPane scrUnits;
    private JScrollPane scrParts;

    /** Creates new form NewTeamDialog */
    public LootDialog(java.awt.Frame parent, boolean modal, Loot l, Campaign c) {
        super(parent, modal);
        this.frame = parent;
        this.loot = l;
        this.campaign = c;
        cancelled = true;
        units = new ArrayList<Entity>();
        parts = new ArrayList<Part>();
        for(Entity e : l.getUnits()) {
            units.add(e);
        }
        for(Part p : l.getParts()) {
            parts.add(p);
        }
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
         java.awt.GridBagConstraints gridBagConstraints;

        txtName = new JTextField();
        btnOK = new JButton("Done");
        btnCancel = new JButton("Cancel");
        btnAddUnit = new JButton("Add");
        btnRemoveUnit = new JButton("Remove");
        btnAddPart = new JButton("Add");
        btnRemovePart = new JButton("Remove");
        listUnits = new JList<String>(new DefaultListModel<String>());
        listParts = new JList<String>(new DefaultListModel<String>());

        //ResourceBundle resourceMap = ResourceBundle.getBundle("mekhq.resources.KillDialog");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Potential Rewards");
        getContentPane().setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(new JLabel("Name:"), gridBagConstraints);
        
        txtName.setText(loot.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtName, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(new JLabel("Cash"), gridBagConstraints);
        
        spnCash = new JSpinner(new SpinnerNumberModel(loot.getCash(), 0, 300000000, 10000));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(spnCash, gridBagConstraints);
 
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(new JLabel("Units"), gridBagConstraints);
        
        btnAddUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUnit();
            }
        });
 
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(btnAddUnit, gridBagConstraints);
        
        btnRemoveUnit.setEnabled(false);
        btnRemoveUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               removeUnit();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(btnRemoveUnit, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        scrUnits = new JScrollPane(listUnits);
        listUnits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listUnits.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(
                            javax.swing.event.ListSelectionEvent evt) {
                        listUnitsValueChanged();
                    }
                });
        refreshUnitList();
        getContentPane().add(scrUnits, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(new JLabel("Parts"), gridBagConstraints);
        
        btnAddPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPart();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(btnAddPart, gridBagConstraints);
        
        btnRemovePart.setEnabled(false);
        btnRemovePart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               removePart();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(btnRemovePart, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        scrParts = new JScrollPane(listParts);
        listParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listParts.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(
                            javax.swing.event.ListSelectionEvent evt) {
                        listPartsValueChanged();
                    }
                });
        refreshPartList();
        getContentPane().add(scrParts, gridBagConstraints);
        
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                done();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnOK, gridBagConstraints);
        
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible(false);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnCancel, gridBagConstraints);

        pack();
    }
    
    public Loot getLoot() {
        if(cancelled) {
            return null;
        }
        return loot;
    }
    
    private void addUnit() {
        UnitSelectorDialog usd = new UnitSelectorDialog(frame, null, campaign, false);
        usd.setVisible(true);
        Entity e = usd.getEntity();
        if(null != e) {
            units.add(e);
        }
        refreshUnitList();
    }
    
    private void removeUnit() {
        int row = listUnits.getSelectedIndex();
        if(-1 != row) {
            units.remove(row);
        }
        refreshUnitList();
    }
    
    private void addPart() {
        PartsStoreDialog psd = new PartsStoreDialog(frame, true, null, campaign, false);
        psd.setVisible(true);
        Part p = psd.getPart();
        if(null != p) {
            parts.add(p);
        }
        refreshPartList();
    }
    
    private void removePart() {
        int row = listParts.getSelectedIndex();
        if(-1 != row) {
            parts.remove(row);
        }
        refreshPartList();
    }
 
    private void done() {
        loot.setName(txtName.getText());
        loot.setCash((int)Math.ceil((Double)spnCash.getModel().getValue()));
        cancelled = false;
        loot.clearUnits();
        loot.clearParts();
        for(Entity e : units) {
            loot.addUnit(e);
        }
        for(Part p : parts) {
            loot.addPart(p);
        }
        this.setVisible(false);
    }
 
    private void refreshUnitList() {
        int selectedRow = listUnits.getSelectedIndex();
        DefaultListModel<String> model = (DefaultListModel<String>)listUnits.getModel();
        model.removeAllElements();
        //listUnits.removeAll();
        for(Entity e : units) {
            model.addElement(e.getShortName());
            //listUnits.add(e.getDisplayName());
        }
        scrUnits.setViewportView(listUnits);
        if(selectedRow != -1) {
            if(((DefaultListModel<String>)listUnits.getModel()).getSize() > 0) {
                if(((DefaultListModel<String>)listUnits.getModel()).getSize() == selectedRow) {
                    listUnits.setSelectedIndex(selectedRow-1);
                } else {
                    listUnits.setSelectedIndex(selectedRow);
                }
            }
        }
    }
 
    private void refreshPartList() {
        int selectedRow = listParts.getSelectedIndex();
        DefaultListModel<String> model = (DefaultListModel<String>)listParts.getModel();
        model.removeAllElements();
        //listUnits.removeAll();
        for(Part p : parts) {
            model.addElement(p.getName());
            //listParts.add(e.getDisplayName());
        }
        scrParts.setViewportView(listParts);
        if(selectedRow != -1) {
            if(((DefaultListModel<String>)listParts.getModel()).getSize() > 0) {
                if(((DefaultListModel<String>)listParts.getModel()).getSize() == selectedRow) {
                    listParts.setSelectedIndex(selectedRow-1);
                } else {
                    listParts.setSelectedIndex(selectedRow);
                }
            }
        }
    }
    
    private void listUnitsValueChanged() {
        int row = listUnits.getSelectedIndex();
        btnRemoveUnit.setEnabled(row != -1);
    }
    
    private void listPartsValueChanged() {
        int row = listParts.getSelectedIndex();
        btnRemovePart.setEnabled(row != -1);
    }

}
