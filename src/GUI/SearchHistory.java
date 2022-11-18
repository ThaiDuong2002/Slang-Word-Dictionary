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
 * Date 11/16/2022 - 3:36 PM
 * Description: ...
 */
public class SearchHistory extends JFrame implements ActionListener {
	SlangDictionary slangDictionary;
	JButton backBtn, cancelBtn;
	
	public SearchHistory(SlangDictionary dictionary) {
		this.slangDictionary = dictionary;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Search History");
		this.setResizable(false);
		
		JLabel label = new JLabel();
		label.setBounds(100, 50, 300, 50);
		label.setText("Search History");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setForeground(Color.BLACK);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
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
		
		DefaultListModel<String> model = new DefaultListModel<>();
		model.addAll(this.slangDictionary.getHistorySearch());
		JList<String> list = list = new JList<>(model);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(100, 100, 300, 250);
		
		this.add(label);
		this.add(scrollPane);
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
		}
	}
}
