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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField tfuserName;
	private JTextField tfpassWord;
	private JTextField tfhoVaTen;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfuserName = new JTextField();
		tfuserName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfuserName.setColumns(10);
		tfuserName.setBounds(10, 46, 300, 50);
		contentPane.add(tfuserName);
		
		tfpassWord = new JTextField();
		tfpassWord.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfpassWord.setColumns(10);
		tfpassWord.setBounds(374, 46, 300, 50);
		contentPane.add(tfpassWord);
		
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
		
		JLabel lblHVTn = new JLabel("NickName");
		lblHVTn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHVTn.setBounds(10, 122, 300, 31);
		contentPane.add(lblHVTn);
		
		tfhoVaTen = new JTextField();
		tfhoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfhoVaTen.setColumns(10);
		tfhoVaTen.setBounds(10, 164, 664, 50);
		contentPane.add(tfhoVaTen);
	}
	
	public void dangKy() {
		String userName = tfuserName.getText();
		String pass = tfpassWord.getText();
		String hvTen = tfhoVaTen.getText();
		
		Human human1 = new Human(hvTen);
		Account A = new Account(userName, pass, new Wallet(), human1);

		Main.accList.add(A);
		int n = JOptionPane.showConfirmDialog(this, "Đăng ký thành công", "NULL", JOptionPane.OK_OPTION);
		if (n < 1000) {
			this.setVisible(false);
			Login login = new Login();
			login.setVisible(true);
		}
	}
}
