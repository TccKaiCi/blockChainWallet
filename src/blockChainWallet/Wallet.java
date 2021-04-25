package blockChainWallet;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
	private PrivateKey privateKey;
	public PublicKey publicKey;

	// chỉ các UTXO (unspent transaction outputs) thuộc sở hữu của ví này.
	public HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>(); 

	public Wallet() {
		generateKeyPair();
	}

	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			// Khởi tạo trình tạo khóa generator và generate KeyPair
			keyGen.initialize(ecSpec, random); // 256 byte cung cấp mức bảo mật có thể chấp nhận được
			KeyPair keyPair = keyGen.generateKeyPair();
			
			// Dat khóa công khai và khóa riêng tư từ keyPair
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * trả v�? số dư và lưu trữ UTXO thuộc sở hữu của ví này trong UTXOs
	 * @return
	 */
	public float getBalance() {
		float total = 0;
		for (Map.Entry<String, TransactionOutput> item : Main.UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			
			// nếu đầu ra thuộc v�? vi�? (nếu ti�?n thuộc v�? vi�?)
			if (UTXO.isMine(publicKey)) { 
				// thêm nó vào danh sách các giao dịch chưa được sử dụng của vi�?.
				UTXOs.put(UTXO.id, UTXO); 
				total += UTXO.value;
			}
		}
		return total;
	}
	
	/** 
	 * Tạo và trả ve một giao dịch mới từ ví này.
	 */
	public Transaction sendFunds(PublicKey _recipient, float value) {
		// thu thập số dư và kiểm tra quỹ.
		if (getBalance() < value) { 
			System.out.println("Khong du tien de gui");
			return null;
		}
		
		// tạo mảng danh sách các đầu vào
		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();

		float total = 0;
		for (Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			total += UTXO.value;
			inputs.add(new TransactionInput(UTXO.id));
			if (total > value)
				break;
		}

		Transaction newTransaction = new Transaction(publicKey, _recipient, value, inputs);
		newTransaction.generateSignature(privateKey);

		for (TransactionInput input : inputs) {
			UTXOs.remove(input.transactionOutputId);
		}
		return newTransaction;
	}
	
	public PublicKey getPublicKey() {
		return this.publicKey;
	}
	
	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}
	
}