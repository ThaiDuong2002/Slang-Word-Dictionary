package GUI;

import Dictionary.SlangDictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * GUI
 * Created by Thai Duong
 * Date 11/16/2022 - 3:37 PM
 * Description: ...
 */
public class AddSlang extends JFrame implements ActionListener {
	SlangDictionary slangDictionary;
	JPanel panel;
	JTextField slangField, definitionField;
	JButton backBtn, cancelBtn;
	JButton addBtn;
	JButton overwriteBtn, duplicateBtn;
	JLabel notification;
	
	public AddSlang(SlangDictionary dictionary) {
		this.slangDictionary = dictionary;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Slang Search");
		this.setResizable(false);
		
		JLabel label = new JLabel();
		label.setBounds(100, 20, 300, 50);
		label.setText("Add new slang");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setForeground(Color.BLACK);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
		panel = new JPanel();
		panel.setBounds(50, 70, 400, 310);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setOpaque(true);
		
		JLabel container1 = new JLabel();
		container1.setText("Input the new Slang");
		container1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		container1.setForeground(Color.BLACK);
		container1.setBounds(50, 0, 300, 50);
		
		slangField = new JTextField();
		slangField.setBounds(50, 40, 300, 30);
		slangField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		slangField.setForeground(Color.BLACK);
		
		JLabel container2 = new JLabel();
		container2.setText("Input the Definition");
		container2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		container2.setForeground(Color.BLACK);
		container2.setBounds(50, 60, 300, 50);
		
		definitionField = new JTextField();
		definitionField.setBounds(50, 100, 300, 30);
		definitionField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		definitionField.setForeground(Color.BLACK);
		
		addBtn = new JButton("ADD");
		addBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addBtn.setForeground(Color.BLACK);
		addBtn.setFocusable(false);
		addBtn.setBounds(150, 140, 100, 30);
		addBtn.addActionListener(this);
		
		notification = new JLabel();
		notification.setText("This Slang has already existed. Do you want to change?");
		notification.setBounds(30, 170, 400, 30);
		notification.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		notification.setVisible(false);
		
		overwriteBtn = new JButton("Overwrite");
		overwriteBtn.setFocusable(false);
		overwriteBtn.setBounds(50, 200, 100, 30);
		overwriteBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		overwriteBtn.addActionListener(this);
		overwriteBtn.setVisible(false);
		
		duplicateBtn = new JButton("Duplicate");
		duplicateBtn.setFocusable(false);
		duplicateBtn.setBounds(250, 200, 100, 30);
		duplicateBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		duplicateBtn.addActionListener(this);
		duplicateBtn.setVisible(false);
		
		panel.add(container1);
		panel.add(container2);
		panel.add(slangField);
		panel.add(definitionField);
		panel.add(addBtn);
		panel.add(notification);
		panel.add(overwriteBtn);
		panel.add(duplicateBtn);
		
		backBtn = new JButton("Back");
		backBtn.setFocusable(false);
		backBtn.setBounds(50, 400, 100, 30);
		backBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		backBtn.addActionListener(this);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFocusable(false);
		cancelBtn.setBounds(350, 400, 100, 30);
		cancelBtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cancelBtn.addActionListener(this);
		
		this.add(label);
		this.add(panel);
		this.add(backBtn);
		this.add(cancelBtn);
		this.setSize(500, 500);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new MainInterface(this.slangDictionary);
		} else if (e.getSource() == cancelBtn) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if (e.getSource() == addBtn) {
			if (this.slangDictionary.findBySlangWord(slangField.getText()) != null) {
				notification.setVisible(true);
				overwriteBtn.setVisible(true);
				duplicateBtn.setVisible(true);
			} else {
			
			}
		}
	}
}
