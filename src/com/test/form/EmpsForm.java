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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class EmpsForm extends JFrame {

	private JPanel contentPane;
	private JTextField textEmpId;
	private JTextField textEmpName;
	private JTextField textEmpDob;
	private JTextField textEmpSal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpsForm frame = new EmpsForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpsForm() {
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
		
		JLabel lblEmpId = new JLabel("Emp Id");
		GridBagConstraints gbc_lblEmpId = new GridBagConstraints();
		gbc_lblEmpId.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpId.gridx = 4;
		gbc_lblEmpId.gridy = 1;
		panel.add(lblEmpId, gbc_lblEmpId);
		
		textEmpId = new JTextField();
		GridBagConstraints gbc_textEmpId = new GridBagConstraints();
		gbc_textEmpId.insets = new Insets(0, 0, 5, 0);
		gbc_textEmpId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmpId.gridx = 6;
		gbc_textEmpId.gridy = 1;
		panel.add(textEmpId, gbc_textEmpId);
		textEmpId.setColumns(10);
		
		JLabel lblEmpName = new JLabel("Emp Name");
		GridBagConstraints gbc_lblEmpName = new GridBagConstraints();
		gbc_lblEmpName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpName.gridx = 4;
		gbc_lblEmpName.gridy = 2;
		panel.add(lblEmpName, gbc_lblEmpName);
		
		textEmpName = new JTextField();
		GridBagConstraints gbc_textEmpName = new GridBagConstraints();
		gbc_textEmpName.insets = new Insets(0, 0, 5, 0);
		gbc_textEmpName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmpName.gridx = 6;
		gbc_textEmpName.gridy = 2;
		panel.add(textEmpName, gbc_textEmpName);
		textEmpName.setColumns(10);
		
		JLabel lblDob = new JLabel("Dob");
		GridBagConstraints gbc_lblDob = new GridBagConstraints();
		gbc_lblDob.insets = new Insets(0, 0, 5, 5);
		gbc_lblDob.gridx = 4;
		gbc_lblDob.gridy = 3;
		panel.add(lblDob, gbc_lblDob);
		
		textEmpDob = new JTextField();
		GridBagConstraints gbc_textEmpDob = new GridBagConstraints();
		gbc_textEmpDob.insets = new Insets(0, 0, 5, 0);
		gbc_textEmpDob.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmpDob.gridx = 6;
		gbc_textEmpDob.gridy = 3;
		panel.add(textEmpDob, gbc_textEmpDob);
		textEmpDob.setColumns(10);
		
		JLabel lblEmpSal = new JLabel("Emp Salary");
		GridBagConstraints gbc_lblEmpSal = new GridBagConstraints();
		gbc_lblEmpSal.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpSal.gridx = 4;
		gbc_lblEmpSal.gridy = 4;
		panel.add(lblEmpSal, gbc_lblEmpSal);
		
		textEmpSal = new JTextField();
		GridBagConstraints gbc_textEmpSal = new GridBagConstraints();
		gbc_textEmpSal.insets = new Insets(0, 0, 5, 0);
		gbc_textEmpSal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmpSal.gridx = 6;
		gbc_textEmpSal.gridy = 4;
		panel.add(textEmpSal, gbc_textEmpSal);
		textEmpSal.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Saved...");
				
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 6;
		gbc_btnSave.gridy = 5;
		panel.add(btnSave, gbc_btnSave);
	}

}
