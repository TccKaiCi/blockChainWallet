package blockChainWallet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class Block {
	
	public String hash;
	public String previousHash; 
	
	public String merkleRoot;
	public long timeStamp;
	public int nonce;
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Block(String previousHash ) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); 
	}
	
	// Tính toán hàm hash mới dựa trên nội dung block
	public String calculateHash() {
		// Ap dung thuat toan ham bam
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				merkleRoot
				);
		return calculatedhash;
	}
	
	// Tăng giá trị nonce cho đến khi đạt được mục tiêu hash .
	public void mineBlock(int difficulty) {
		merkleRoot = StringUtil.getMerkleRoot(transactions);
		
		//Create a string with difficulty * "0" 
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
	}
	
	/**
	 * xử lý giao dịch và kiểm tra xem có hợp lệ không, trừ khi block là block gốc thì bo qua.
	 * @return
	 */
	public boolean addTransaction(Transaction transaction) {
		if(transaction == null) return false;		
		if((previousHash != "0")) {
			if((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}
	
	public void writeFile(BufferedWriter out) throws IOException{
        try {
        	out.write("hash: " + hash + "|");
        	out.write("preHash: " + previousHash + "|");
        	out.write("Merkle: " + merkleRoot + "|");
        	out.write("TimeStamp: " + timeStamp + "|");
        	out.write("Nonce: " + nonce + "|");
			
        	for (Transaction a : transactions) {
				a.writeFile(out);
			}
        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }
}