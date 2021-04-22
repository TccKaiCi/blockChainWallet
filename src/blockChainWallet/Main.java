package blockChainWallet;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String,TransactionOutput> UTXOs = new HashMap<>();
	
	public static int difficulty = 2;

	public static Wallet walletSys;
	
	public static AccountHR accList;
	public static Account playerA;
	
	public static Transaction genesisTransaction;
	public static Scanner inp = new Scanner(System.in);

	public static void main(String[] args) {	
		// thêm các blocks vào chuỗi khối ArrayList:
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		//Setup Bouncey castle as a Security Provider
		
		//Create wallets:
		walletSys = new Wallet();
		playerA = new Account();
		
		
		Human human1 = new Human("Hiam", "18");
		Human human2 = new Human("Kaido", "17");
		accList = new AccountHR();
		
		// database
		Account A = new Account("Hiam", "123", new Wallet(), human1);
		Account B = new Account("Kaido", "321", new Wallet(), human2);
		
		accList.add(A);
		accList.add(B);
		
		
		// dang nhap
		Account acc = new Account();
		acc.setUserName("Hiam");
		String pass;
		
		do {
			System.out.printf("Nhap vao mat khau: ");
			pass = inp.nextLine();
			acc.setPassWord(pass);
		} while (accList.signIn(acc) == false);
			
		playerA = accList.getAccount(acc);
		
		getInfor();

		// tao he thong tien phai dung truoc tao block dau tien
		tienHeThong(walletSys);
		
		// tao block dau tien
		System.out.println("Tạo block cơ sở ");
		Block genesis = new Block("0");
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);
		

		System.out.print("Tien ban đầu: ");
		getInforMoney();
		
		//nap tien
		do {
			isChainValid();
			System.out.printf("Nap tien?");
			pass = inp.nextLine();
			if (pass.equalsIgnoreCase("Co"))
				napTien(blockchain.get(blockchain.size() - 1), playerA.getWallet());
			else 
				break;
		} while (true);		
		
		//testing
		System.out.println("\nA gửi B: 40 đồng");
		Block block = new Block(blockchain.get(blockchain.size() - 1).hash);
		
		Account playerB = accList.getAccount(StringUtil.getStringFromKey(B.getWallet().getPublicKey()));
		block.addTransaction(playerA.getWallet().sendFunds(playerB.getWallet().publicKey , 40));
		addBlock(block);
		
		getInforMoney();
		isChainValid();
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
	
	public static void getInforMoney() {
		System.out.println("\nVí ti�?n của He thong: " + walletSys.getBalance());
		accList.getInforAll();
//		System.out.println("Ví ti�?n của A: " + A.getWallet().getBalance());
//		System.out.println("Ví ti�?n của B: " + walletB.getBalance());
	}
	
	public static void getInfor() {
//		String temp = StringUtil.getStringFromKey( A.getWallet().getPrivateKey());
//		System.out.println("\nA:\nPB:" + StringUtil.getStringFromKey( A.getWallet().getPublicKey() ) 
//		+ "\nPR:" + temp );
//		
//		System.out.println("B:\nPB:" + StringUtil.getStringFromKey( walletB.getPublicKey() ) 
//		+ "\nPR:" + StringUtil.getStringFromKey( walletB.getPrivateKey() ));
	}
	
	public static void napTien(Block pre, Wallet wallet) {
		System.out.println("\nA nap them 100 dong vao tai khoan");
		Block block = new Block(pre.hash);
		block.addTransaction(walletSys.sendFunds(wallet.publicKey, 100));
		addBlock(block);
		
		
		System.out.print("\nSau khi nạp ti�?n: ");
		getInforMoney();      
	}
	
	public static void tienHeThong(Wallet wallet) {
		System.out.println("Tao tien he thong");
		
		Wallet coinbase = new Wallet();
		
		// tạo giao dịch gô�?c, gửi thêm 100 giả đến walletA:
		genesisTransaction = new Transaction(coinbase.publicKey, wallet.publicKey, 999999999, null);
		
		// ký thủ công giao dịch genesis
		genesisTransaction.generateSignature(coinbase.getPrivateKey());	 
		
		// đặt id giao dịch theo cách thủ công
		genesisTransaction.transactionId = "0";	
		
		//thêm thủ công Transactions Output
		genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient
				, genesisTransaction.value, genesisTransaction.transactionId)); 
		
		// đi�?u quan tr�?ng là phải lưu trữ giao dịch đầu tiên của chúng trong danh sách UTXOs.
		UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); 
	}
	
	
	
	
	
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		// danh sách làm việc tạm th�?i của các giao dịch chưa được sử dụng ở trạng thái block nhất định.
		HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); 
		tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
		
		// lặp qua blockchain để kiểm tra hàm hash:
		for(int i=1; i < blockchain.size(); i++) {
			
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			// so sánh hàm hash đã đăng ký và hàm hash được tính toán:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("#Current Hashes not equal");
				return false;
			}
			// so sánh hàm hash trước đó và hàm hash trước đó đã đăng ký
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("#Previous Hashes not equal");
				return false;
			}
			// kiểm tra xem hash đã được giải quyết chưa
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("#This block hasn't been mined");
				return false;
			}
			

			// lặp qua các giao dịch blockchains:
			TransactionOutput tempOutput;
			for(int t=0; t <currentBlock.transactions.size(); t++) {
				Transaction currentTransaction = currentBlock.transactions.get(t);
				
				if(!currentTransaction.verifiySignature()) {
					System.out.println("#Signature on Transaction(" + t + ") is Invalid");
					return false; 
				}
				if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
					System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
					return false; 
				}
				
				for(TransactionInput input: currentTransaction.inputs) {	
					tempOutput = tempUTXOs.get(input.transactionOutputId);
					
					if(tempOutput == null) {
						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
						return false;
					}
					
					if(input.UTXO.value != tempOutput.value) {
						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
						return false;
					}
					
					tempUTXOs.remove(input.transactionOutputId);
				}
				
				for(TransactionOutput output: currentTransaction.outputs) {
					tempUTXOs.put(output.id, output);
				}
				
				if( currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
					return false;
				}
				if( currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
					System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
					return false;
				}
				
			}
			
		}
		System.out.println("Blockchain is valid");
		return true;
	}
	
}