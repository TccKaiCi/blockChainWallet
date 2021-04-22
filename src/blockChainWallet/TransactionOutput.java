package blockChainWallet;

import java.security.PublicKey;

public class TransactionOutput {
	public String id;
	public PublicKey reciepient;// chủ sở hữu mới của những đồng ti�?n.
	public float value;// số lượng coin mà h�? sở hữu
	public String parentTransactionId; // id của giao dịch mà đầu ra này được tạo
	
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
	}
	
	// Kiểm tra xem đồng xu có thuộc v�? vi�? không
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}
}