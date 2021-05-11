package blockChainWallet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SendMoney extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMoney frame = new SendMoney();
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
	public SendMoney() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTrV = new JButton("Trở về");
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack();
			}
		});
		btnTrV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTrV.setBounds(362, 220, 182, 31);
		contentPane.add(btnTrV);
		
		JLabel lblMNgiNhn = new JLabel("M\u00E3 ng\u01B0\u1EDDi nh\u1EADn");
		lblMNgiNhn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMNgiNhn.setBounds(10, 22, 185, 31);
		contentPane.add(lblMNgiNhn);
		
		JLabel lblSTin = new JLabel("S\u1ED1 ti\u1EC1n");
		lblSTin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSTin.setBounds(10, 92, 185, 31);
		contentPane.add(lblSTin);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField_1.setColumns(10);
		textField_1.setBounds(205, 83, 339, 50);
		contentPane.add(textField_1);
		
		JButton btnGi = new JButton("Gửi");
		btnGi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiTien();
			}
		});
		btnGi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnGi.setBounds(160, 172, 182, 31);
		contentPane.add(btnGi);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setColumns(10);
		textField.setBounds(205, 13, 339, 50);
		contentPane.add(textField);
	}
	
	public void btnBack() {
		this.setVisible(false);
		Home home = new Home();
		home.setVisible(true);
	}
	
	public void guiTien() {
		String temp = textField.getText();
		if ( Main.accList.getAccountByPublicKey(temp) == null ) {
			System.out.println("Khong tim thay PublicKey nguoi nhan");
		}
		else {
			System.out.println("Nhap so tien muon gui: ");
			int amount = Integer.parseInt( textField_1.getText() );
			Account acc = Main.accList.getAccountByPublicKey(temp);
			
			Block block = new Block(Main.blockchain.get(Main.blockchain.size() - 1).hash);
			
			Boolean flag = block.addTransaction(
					Main.userAccount.getWallet().sendFunds(
							acc.getWallet().getPublicKey() , (float) amount));
			
			if ( flag ) {
				Main.addBlock(block);
				JOptionPane.showConfirmDialog(this, "Gửi tiền thành công", "NULL", JOptionPane.OK_OPTION);
			}
			else
				JOptionPane.showConfirmDialog(this, "Gửi tiền thất bại", "NULL", JOptionPane.OK_OPTION);
		}
	}
}
