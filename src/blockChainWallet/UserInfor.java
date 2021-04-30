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

public class UserInfor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(65, 40, 322, 68);
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
		btnTrV.setBounds(118, 220, 182, 31);
		contentPane.add(btnTrV);
	}
	
	public void btnBack() {
		this.setVisible(false);
		Home home = new Home();
		home.setVisible(true);
	}

}
