package blockChainWallet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UserInfor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfor frame = new UserInfor();
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
	public UserInfor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(52, 115, 495, 68);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField.setText( 
				StringUtil.applySha256( StringUtil.getStringFromKey( 
						Main.userAccount.getWallet().getPublicKey()))
				);
		
		JButton btnTrV = new JButton("Trở về");
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack();
			}
		});
		btnTrV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTrV.setBounds(199, 420, 182, 31);
		contentPane.add(btnTrV);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(52, 11, 322, 59);
		contentPane.add(lblNewLabel);
		
		lblNewLabel.setText("Xin chao, " + Main.userAccount.getHuman().getName());
		
		JLabel lblPublickey = new JLabel("PublicKey:");
		lblPublickey.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPublickey.setBounds(52, 59, 322, 59);
		contentPane.add(lblPublickey);
		
		JLabel lblPrivatekey = new JLabel("PrivateKey:");
		lblPrivatekey.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPrivatekey.setBounds(52, 195, 322, 59);
		contentPane.add(lblPrivatekey);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(52, 251, 495, 68);
		
		textField_1.setText( 
				StringUtil.applySha256( StringUtil.getStringFromKey( 
						Main.userAccount.getWallet().getPrivateKey()))
				);
		
		contentPane.add(textField_1);
		
		JLabel lblSTinCa = new JLabel("Số tiền của bạn là: " + Main.userAccount.getWallet().getBalance());
		lblSTinCa.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSTinCa.setBounds(52, 341, 322, 59);
		contentPane.add(lblSTinCa);
	}
	
	public void btnBack() {
		this.setVisible(false);
		Home home = new Home();
		home.setVisible(true);
	}
}
