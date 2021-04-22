package blockChainWallet;

public class TransactionInput {
	public String transactionOutputId; //Tham chiếu đến TransactionOutputs -> transactionId
	public TransactionOutput UTXO;// Chứa kết quả giao dịch Chưa gửi
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
}