package com.test.form;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EmpForm  extends JFrame {

	private JPanel contentPane;
	
	    private JLabel lblEmpId;
        private JLabel lblEmpDob;
        private JLabel lblEmpSal;
        private JLabel lblEmpName;
        
        private JTextField txtEmpId;
        private JTextField txtEmpDob;
        private JTextField txtEmpSal;
        private JTextField txtEmpName;
    	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpForm frame = new EmpForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
        
        public EmpForm(){
        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			JLabel lblHeader = new JLabel("Emp Form");
			GridBagConstraints gbc_lblHeader = new GridBagConstraints();
			gbc_lblHeader.gridwidth = 2;
			gbc_lblHeader.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeader.gridx = 4;
			gbc_lblHeader.gridy = 0;
			panel.add(lblHeader, gbc_lblHeader);
			
						
					    		lblEmpId = new JLabel("Emp Id");
    		    		
    		GridBagConstraints gbcLblEmpId = new GridBagConstraints();
    		gbcLblEmpId.insets = new Insets(0, 0, 5, 5);
			gbcLblEmpId.fill = GridBagConstraints.HORIZONTAL;
			gbcLblEmpId.gridx = 4;
			gbcLblEmpId.gridy = 1;
						panel.add(lblEmpId, gbcLblEmpId);
   			    		lblEmpDob = new JLabel("Emp Id");
    		    		
    		GridBagConstraints gbcLblEmpDob = new GridBagConstraints();
    		gbcLblEmpDob.insets = new Insets(0, 0, 5, 5);
			gbcLblEmpDob.fill = GridBagConstraints.HORIZONTAL;
			gbcLblEmpDob.gridx = 4;
			gbcLblEmpDob.gridy = 2;
						panel.add(lblEmpDob, gbcLblEmpDob);
   			    		lblEmpSal = new JLabel("Emp Id");
    		    		
    		GridBagConstraints gbcLblEmpSal = new GridBagConstraints();
    		gbcLblEmpSal.insets = new Insets(0, 0, 5, 5);
			gbcLblEmpSal.fill = GridBagConstraints.HORIZONTAL;
			gbcLblEmpSal.gridx = 4;
			gbcLblEmpSal.gridy = 3;
						panel.add(lblEmpSal, gbcLblEmpSal);
   			    		lblEmpName = new JLabel("Emp Id");
    		    		
    		GridBagConstraints gbcLblEmpName = new GridBagConstraints();
    		gbcLblEmpName.insets = new Insets(0, 0, 5, 5);
			gbcLblEmpName.fill = GridBagConstraints.HORIZONTAL;
			gbcLblEmpName.gridx = 4;
			gbcLblEmpName.gridy = 4;
						panel.add(lblEmpName, gbcLblEmpName);
   			   			
			
					    		txtEmpId = new JTextField();
    		    		
    		GridBagConstraints gbcTxtEmpId = new GridBagConstraints();
    		gbcTxtEmpId.insets = new Insets(0, 0, 5, 0);
			gbcTxtEmpId.fill = GridBagConstraints.HORIZONTAL;
			gbcTxtEmpId.gridx = 6;
			gbcTxtEmpId.gridy = 1;
						panel.add(txtEmpId, gbcTxtEmpId);
			txtEmpId.setColumns(10);
   			    		txtEmpDob = new JTextField();
    		    		
    		GridBagConstraints gbcTxtEmpDob = new GridBagConstraints();
    		gbcTxtEmpDob.insets = new Insets(0, 0, 5, 0);
			gbcTxtEmpDob.fill = GridBagConstraints.HORIZONTAL;
			gbcTxtEmpDob.gridx = 6;
			gbcTxtEmpDob.gridy = 2;
						panel.add(txtEmpDob, gbcTxtEmpDob);
			txtEmpDob.setColumns(10);
   			    		txtEmpSal = new JTextField();
    		    		
    		GridBagConstraints gbcTxtEmpSal = new GridBagConstraints();
    		gbcTxtEmpSal.insets = new Insets(0, 0, 5, 0);
			gbcTxtEmpSal.fill = GridBagConstraints.HORIZONTAL;
			gbcTxtEmpSal.gridx = 6;
			gbcTxtEmpSal.gridy = 3;
						panel.add(txtEmpSal, gbcTxtEmpSal);
			txtEmpSal.setColumns(10);
   			    		txtEmpName = new JTextField();
    		    		
    		GridBagConstraints gbcTxtEmpName = new GridBagConstraints();
    		gbcTxtEmpName.insets = new Insets(0, 0, 5, 0);
			gbcTxtEmpName.fill = GridBagConstraints.HORIZONTAL;
			gbcTxtEmpName.gridx = 6;
			gbcTxtEmpName.gridy = 4;
						panel.add(txtEmpName, gbcTxtEmpName);
			txtEmpName.setColumns(10);
   						
			
			JButton btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			GridBagConstraints gbc_btnSave = new GridBagConstraints();
			gbc_btnSave.gridx = 6;
			gbc_btnSave.gridy = 5;
			panel.add(btnSave, gbc_btnSave);
        }
   
}

