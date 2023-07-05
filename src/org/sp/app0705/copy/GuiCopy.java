package org.sp.app0705.copy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GuiCopy extends JFrame implements ActionListener{
	JLabel la_ori, la_dest;
	JTextField t_ori, t_dest;
	JButton bt;
	
	public GuiCopy() {
		la_ori = new JLabel("원본 위치"); 
		la_dest = new JLabel("복사 위치");
		t_ori = new JTextField();
		t_dest = new JTextField();
		bt = new JButton("복사 실행");
		
		//라벨의 크기 설정
		la_ori.setPreferredSize(new Dimension(100, 40));
		la_dest.setPreferredSize(new Dimension(100, 40));
		
		//텍스트 필드의 크기 지정
		t_ori.setPreferredSize(new Dimension(365, 30));
		t_dest.setPreferredSize(new Dimension(365, 30));
		
		setLayout(new FlowLayout());
		
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		setSize(500, 170);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //윈도우를 화면 가운데 오게
		
		bt.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new GuiCopy();
	}
	
	//파일 복사
	public void copy() {
		FileInputStream fis=null;
		FileOutputStream fos=null;
		
		// 지정한 경로의 원본 파일에 입력스트림 생성하기
		try {
			fis = new FileInputStream(t_ori.getText());
			fos = new FileOutputStream(t_dest.getText());
			
			//생성된 입력 스트림으로부터 데이터 읽기
			int data=-1;
			
			while(true) {
				data = fis.read(); //1byte 읽기
				if(data == -1) break;
				fos.write(data); //출력스트림에 데이터 출력
				System.out.print((char)data);
			}
			JOptionPane.showMessageDialog(this, "복사 완료");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "파일이 존재하지 않습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) { //인스턴스가 존재할 때만
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fis!=null) { //인스턴스가 존재할 때만
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}			
	}

	public void actionPerformed(ActionEvent e) {
		copy();
	}
}
