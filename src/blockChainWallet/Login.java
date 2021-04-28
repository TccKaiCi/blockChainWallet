package blockChainWallet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txfUserName;
	private JTextField txfPass;
	private JButton btnngK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txfUserName = new JTextField();
		txfUserName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txfUserName.setBounds(70, 32, 300, 50);
		contentPane.add(txfUserName);
		txfUserName.setColumns(10);
		
		txfPass = new JTextField();
		txfPass.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txfPass.setColumns(10);
		txfPass.setBounds(70, 106, 300, 50);
		contentPane.add(txfPass);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng Nh\u1EADp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangNhap();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(126, 167, 182, 31);
		contentPane.add(btnNewButton);
		
		btnngK = new JButton("\u0110\u0103ng K\u00FD");
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangKy();
			}
		});
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnngK.setBounds(126, 221, 182, 31);
		contentPane.add(btnngK);
	}
	
	public void dangNhap() {
		String name = txfUserName.getText();
		String pass = txfPass.getText();
		
		// dang nhap
		Account acc = new Account();
		acc.setUserName(name);
		acc.setPassWord(pass);
		
		if  (Main.accList.signIn(acc)) {
			Main.userAccount = Main.accList.getAccount(acc);
			Home home = new Home();
			this.setVisible(false);
			home.setVisible(true);
		}
		else {
			JOptionPane.showConfirmDialog(this, "Đăng nhập thất bại", "NULL", JOptionPane.OK_OPTION);
		}
		
	}
	
	public void dangKy() {
		this.setVisible(false);
		Register reg = new Register();
		reg.setVisible(true);
	}
}
