package blockChainWallet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNpTin = new JButton("N\u1EA1p ti\u1EC1n");
		btnNpTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				napTien();
			}
		});
		btnNpTin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNpTin.setBounds(10, 89, 182, 31);
		contentPane.add(btnNpTin);
		
		JButton btnGiTin = new JButton("G\u1EEDi Ti\u1EC1n");
		btnGiTin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnGiTin.setBounds(10, 136, 182, 31);
		contentPane.add(btnGiTin);
		
		JButton btnLchSGiao = new JButton("L\u1ECBch S\u1EED Giao d\u1ECBch");
		btnLchSGiao.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLchSGiao.setBounds(51, 178, 329, 31);
		contentPane.add(btnLchSGiao);
		
		JButton btnngXut = new JButton("\u0110\u0103ng xu\u1EA5t");
		btnngXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangXuat();
			}
		});
		btnngXut.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnngXut.setBounds(242, 220, 182, 31);
		contentPane.add(btnngXut);
		
		JLabel lblNewLabel = new JLabel("Xin ch\u00E0o," + Main.userAccount.getHuman().getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(77, 11, 300, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnXemHS = new JButton("Xem h\u1ED3 s\u01A1");
		btnXemHS.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnXemHS.setBounds(220, 113, 182, 31);
		contentPane.add(btnXemHS);
		
	}
	
	public void napTien() {
		this.setVisible(false);
		NapTien napTien = new NapTien();
		napTien.setVisible(true);
	}
	
	public void dangXuat() {
		this.setVisible(false);
		Login login = new Login();
		login.setVisible(true);
	}
}
