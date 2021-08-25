package cau1;
import java.awt.*;
import java.awt.event.*;

public class Cau1 extends Frame implements ActionListener {
	Label nhapChuoi, thucHien, ketQua;
	Choice comboBoxThucHien;
	TextField textInput;
	TextArea resultOutput;
	Button view, reset, exit;
	Panel containerPanel, UIPanel, buttonPanel;
	
	public static void main(String[] args) {
		new Cau1("Cau 1");
	}
	public void GUI() {
		nhapChuoi = new Label("Nhập chuỗi");
		thucHien = new Label("Thực hiện");
		ketQua = new Label("Kết quả");
		
		textInput = new TextField();
		
		resultOutput = new TextArea();
		resultOutput.setEditable(false);
		
		comboBoxThucHien = new Choice();
		comboBoxThucHien.add("Đếm từ");
		comboBoxThucHien.add("Đếm từ trùng lặp");
		comboBoxThucHien.add("Đảo chuỗi");
	
		view = new Button("View");
		view.addActionListener(this);
		
		reset = new Button("Reset");
		reset.addActionListener(this);
		
		exit = new Button("Exit");
		exit.addActionListener(this);
		
		containerPanel = new Panel(new GridLayout(2, 1));
		UIPanel = new Panel(new GridLayout(3, 2));
		buttonPanel = new Panel(new GridLayout(1, 3));
		
		UIPanel.add(nhapChuoi);
		UIPanel.add(textInput);
		UIPanel.add(thucHien);
		UIPanel.add(comboBoxThucHien);
		UIPanel.add(ketQua);
		UIPanel.add(resultOutput);
		
		buttonPanel.add(view);
		buttonPanel.add(reset);
		buttonPanel.add(exit);
		
		containerPanel.add(UIPanel);
		containerPanel.add(buttonPanel);
		add(containerPanel);
		
		setSize(400, 400);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view) {
			String selection = comboBoxThucHien.getSelectedItem();
			String text = textInput.getText();
			
			if(text == null || text.isBlank()) {
				resultOutput.setText("Xâu kí tự trống. Hãy nhập lại");
			}
			else {
				if(selection == "Đếm từ") {
					
					//using split() with regex \\s+ as one or more whitespace
					String[] words = text.split("\\s+");
					
					resultOutput.setText("Có " + Integer.toString(words.length) + " từ");
				}
				
				if(selection == "Đếm từ trùng lặp") {
					String[] words = text.split("\\s+");
					
					int wordCount = 1;
					
					for(int i = 0; i < words.length; i++) {
						for(int j = i+1; j < words.length; j++) {
							if(words[i].equals(words[j])) {
								wordCount += 1;
								words[j]="0"; //repeated words is replaced with zero
							}
						}
						if(words[i] != "0")
							resultOutput.setText(resultOutput.getText() + words[i] + ": " + wordCount + " lần\n");
						wordCount = 1;
					}
				}
				if(selection == "Đảo chuỗi") {
					StringBuilder result = new StringBuilder();
					result.append(text);
					result.reverse();
					resultOutput.setText("Xâu đảo ngược là:\n" + result);
				}
			}
		}
		if(e.getSource() == reset) {
			textInput.setText("");
			resultOutput.setText("");
		}
		if(e.getSource() == exit) {
			System.exit(0);
		}
	}
	public Cau1(String string) {
		super(string);
		GUI();
	}
}
