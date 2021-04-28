package blockChainWallet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField passWord;
	private JTextField hoVaTen;
	private JTextField Tuoi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userName = new JTextField();
		userName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		userName.setColumns(10);
		userName.setBounds(10, 46, 300, 50);
		contentPane.add(userName);
		
		passWord = new JTextField();
		passWord.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passWord.setColumns(10);
		passWord.setBounds(374, 46, 300, 50);
		contentPane.add(passWord);
		
		JButton btnngK = new JButton("Đăng Ký");
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangKy();
			}
		});
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnngK.setBounds(246, 246, 182, 31);
		contentPane.add(btnngK);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 11, 300, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMtKhu.setBounds(374, 11, 300, 31);
		contentPane.add(lblMtKhu);
		
		JLabel lblHVTn = new JLabel("Họ và tên");
		lblHVTn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHVTn.setBounds(10, 122, 300, 31);
		contentPane.add(lblHVTn);
		
		JLabel lblTui = new JLabel("Tuổi");
		lblTui.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTui.setBounds(374, 122, 300, 31);
		contentPane.add(lblTui);
		
		hoVaTen = new JTextField();
		hoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 24));
		hoVaTen.setColumns(10);
		hoVaTen.setBounds(10, 164, 300, 50);
		contentPane.add(hoVaTen);
		
		Tuoi = new JTextField();
		Tuoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Tuoi.setColumns(10);
		Tuoi.setBounds(374, 164, 300, 50);
		contentPane.add(Tuoi);
	}
	
	public void dangKy() {
		
	}
}
