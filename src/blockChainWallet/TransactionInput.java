package blockChainWallet;

import java.io.BufferedWriter;
import java.io.IOException;

public class TransactionInput {
	public String transactionOutputId; //Tham chiếu đến TransactionOutputs -> transactionId
	public TransactionOutput UTXO;// Chứa kết quả giao dịch Chưa gửi
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
	
	public void display() {
		System.out.println(this.transactionOutputId);
		this.UTXO.display();
	}
	
	public void writeFile(BufferedWriter out) throws IOException{
        try {
        	out.write(this.transactionOutputId + "|");
        	UTXO.writeFile(out);
        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }
}