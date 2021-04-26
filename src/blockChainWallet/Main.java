package blockChainWallet;

import java.security.PublicKey;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String,TransactionOutput> UTXOs = new HashMap<>();
	
	public static int difficulty = 2;

	public static Wallet walletSys;
	
	public static AccountHR accList;
	public static Account userAccount;
	
	public static Transaction genesisTransaction;
	public static Scanner inp = new Scanner(System.in);
	public static Scanner nextInt = new Scanner(System.in);
	public static boolean flagSys = true;

	public static void main(String[] args) {	
		// thêm các blocks vào chuỗi khối ArrayList:
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		//Setup Bouncey castle as a Security Provider
		
		walletSys= new Wallet();
		createData();
		
		menu();
		System.out.printf("Dang nhap vao he thong thanh cong");
		
		// tao he thong tien phai dung truoc tao block dau tien
		tienHeThong(walletSys);
		
		// tao block dau tien
		Block genesis = new Block("0");
		genesis.addTransaction(genesisTransaction);
		addBlock(genesis);
		

		do {
			menuUser();
			if ( !isChainValid() ) {
				System.out.printf("The System is shutting down");
				flagSys = false;
			}
			System.out.println();
		} while (flagSys);
		
	}
	
	public static void menuUser() {
		boolean flag = true;
		String temp = null;
		do {
			System.out.println("Vui long chon:");
			System.out.println("1. Nap them 100 dong");
			System.out.println("2. Xem toan bo tien");
			System.out.println("3. Tim publickey");
			System.out.println("4. Gui tien");
			System.out.println("5. Lich su giao dich");
			System.out.println("6. Thoat");
			
			
			int x = nextInt.nextInt();
			switch (x) {
				case 1: 
					// nap tien
					napTien(userAccount);
					flag = !flag;
					break;
				case 2:
					// xem toan bo tien
					getInforMoney();
					flag = !flag;
					break;
				case 3:
					// tim publickey
					System.out.println("Vui long nhap userName: ");
					String userName =inp.nextLine();
					temp = StringUtil.getStringFromKey( accList.getPublicKey(userName) );
					System.out.println( 
							StringUtil.applySha256(temp)
							);
					flag = !flag;
					break;
				case 4:
					// Gui tien
					System.out.println("PublicKey nguoi nhan: ");
					temp = inp.nextLine();
					if ( accList.getAccountByPublicKey(temp) == null ) {
						System.out.println("Khong tim thay PublicKey nguoi nhan");
					}
					else {
						System.out.println("Nhap so tien muon gui: ");
						int amount = nextInt.nextInt();
						Account acc = accList.getAccountByPublicKey(temp);
						guiTien(acc.getWallet().getPublicKey(), userAccount, amount);
					}
					flag = !flag;
					break;
				case 5:
					// lich su
					lichSuGiaoDich();
					flag = !flag;
					break;
				case 6:
					// thoat
					flagSys = !flag;
					flag = !flag;
					break;
			}
		} while (flag);
	}
	
	public static void menu() {
		boolean flag = true;
		do {
			System.out.println("Vui long chon:");
			System.out.println("1. Dang Nhap");
			System.out.println("2. Tao tai khoan");
			
			int x = nextInt.nextInt();
			switch (x) {
				case 1: 
					logIn();
					flag = !flag;
					break;
				case 2:
					resigter();
					flag = !flag;
					break;
			}
		} while (flag);
	}
	
	public static void logIn() {
		// dang nhap
		Account acc = new Account();
		String name, pass;
		
		do {
			System.out.printf("Ten dang nhap: ");
			name = inp.nextLine();
			acc.setUserName(name);
			System.out.printf("Nhap vao mat khau: ");
			pass = inp.nextLine();
			acc.setPassWord(pass);
		} while (accList.signIn(acc) == false);
		
		userAccount = accList.getAccount(acc);
	}
	
	public static void lichSuGiaoDich() {
		for (Block block : blockchain) {
			
			System.out.println(block.hash+ "\n" + block.previousHash);
			for (Transaction trans : block.transactions) {
				String sender = StringUtil.getStringFromKey(trans.reciepient);
				sender = StringUtil.applySha256(sender);
				
				String sender1 = StringUtil.getStringFromKey(trans.sender);
				sender1 = StringUtil.applySha256(sender1);
				
				String nguoiGui;
				if (accList.getAccountByPublicKey(sender1) == null) 
					nguoiGui = "HeThong";
				else
				nguoiGui = accList.getAccountByPublicKey(sender1).getUserName();
				
				String nguoiNhan;
				if (accList.getAccountByPublicKey(sender) == null)
					nguoiNhan = "HeThong";
				else
				nguoiNhan = accList.getAccountByPublicKey(sender).getUserName();
				
				System.out.println(
						"Nguoi Gui: " + nguoiGui
						+ "\nNguoi Nhan: " + nguoiNhan
						+ "\nGia tri: " + trans.value);
			}
			System.out.println("end 1 block");
		}
	}
	
	public static void resigter() {
		System.out.println("Ho va Ten: ");
		String user = inp.nextLine();
		System.out.println("Tuoi: ");
		String age = inp.nextLine();
		Human human1 = new Human(user, age);
		

		System.out.println("Ten dang nhap:");
		user = inp.nextLine();
		System.out.println("Mat khau");
		String pass = inp.nextLine();
		Account A = new Account(user, pass, new Wallet(), human1);

		accList.add(A);
	}
	
	public static void createData() {
		Human human1 = new Human("Nguyen Tuan Anh", "18");
		Human human2 = new Human("Tang Chi Chung", "18");
		Human human3 = new Human("Nguyen Van Thanh", "18");
		
		Account A = new Account("NTA", "123", new Wallet(), human1);
		Account B = new Account("TCC", "321", new Wallet(), human2);
		Account C = new Account("NVT", "123", new Wallet(), human3);

		accList = new AccountHR();
		accList.add(A);
		accList.add(B);
		accList.add(C);
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
	
	public static void getInforMoney() {
		System.out.println("\nVí tien của He thong: " + walletSys.getBalance());
		accList.getInforAll();
	}
	
	public static void napTien(Account acc) {
		isChainValid();
		napTien(blockchain.get(blockchain.size() - 1), acc);
	}
	
	public static void guiTien(PublicKey key, Account acc, int amount) {
		Block block = new Block(blockchain.get(blockchain.size() - 1).hash);
		
		block.addTransaction(userAccount.getWallet().sendFunds(key , (float) amount));
		addBlock(block);
	}
	
	public static void napTien(Block pre, Account acc) {
		System.out.println("\n"+ acc.getUserName() +" nap them 100 dong vao tai khoan");
		Block block = new Block(pre.hash);
		block.addTransaction(walletSys.sendFunds(acc.getWallet().publicKey, 100));
		addBlock(block);
	}
	
	// tao tien he thong
	public static void tienHeThong(Wallet wallet) {		
		Wallet coinbase = new Wallet();
		
		// tạo giao dịch goc gửi thêm 100 giả đến walletA:
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