package blockChainWallet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NapTien extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NapTien frame = new NapTien();
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
	public NapTien() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnk = new JButton("50");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				napTien(btn.getText());
			}
		});
		btnk.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnk.setBounds(10, 57, 182, 31);
		contentPane.add(btnk);
		
		JLabel lblNewLabel = new JLabel("Ch\u1ECDn m\u1EC7nh gi\u00E1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(114, 11, 236, 35);
		contentPane.add(lblNewLabel);
		
		JButton btnk_1 = new JButton("100");
		btnk_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				napTien(btn.getText());
			}
		});
		btnk_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnk_1.setBounds(224, 57, 182, 31);
		contentPane.add(btnk_1);
		
		JButton btnk_2 = new JButton("200");
		btnk_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				napTien(btn.getText());
			}
		});
		btnk_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnk_2.setBounds(10, 116, 182, 31);
		contentPane.add(btnk_2);
		
		JButton btnk_3 = new JButton("500");
		btnk_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				napTien(btn.getText());
			}
		});
		btnk_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnk_3.setBounds(224, 116, 182, 31);
		contentPane.add(btnk_3);
		
		JButton btnTrV = new JButton("Tr\u1EDF v\u1EC1");
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				troVe();
			}
		});
		btnTrV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTrV.setBounds(108, 190, 182, 31);
		contentPane.add(btnTrV);
	}
	
	public void troVe() {
		this.setVisible(false);
		Home home = new Home();
		home.setVisible(true);
	}
	
	public void napTien(String menhGia) {
		Main.napTien(Main.userAccount, Float.valueOf(menhGia) );
		JOptionPane.showConfirmDialog(this, "Nạp tiền thành công", "NULL", JOptionPane.OK_OPTION);
	}
}
