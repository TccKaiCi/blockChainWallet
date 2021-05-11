package blockChainWallet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Base64;

public class TransactionOutput {
	public String id;
	public PublicKey reciepient;// chủ sở hữu mới của những đồng tien.
	public float value;// số lượng coin mà sở hữu
	public String parentTransactionId; // id của giao dịch mà đầu ra này được tạo
	
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
	}
	
	// Kiểm tra xem đồng xu có thuộc ve viec không
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}
	
	public void display() {
		System.out.println(this.id);
		System.out.println(this.reciepient);
		System.out.println(this.value);
		System.out.println(this.parentTransactionId);
	}
	
	public void writeFile(BufferedWriter out) throws IOException{
        try {
        	out.write("IdOutput: " + this.id + "|");
        	
        	//converting byte to String 
        	String str_key = Base64.getEncoder().encodeToString(this.reciepient.getEncoded());
        	out.write("SenderKey: " + str_key + "|");
        	        	
        	out.write("Value: " + this.value + "|");
        	out.write("parentTransactionId: " + this.parentTransactionId + "|");
        	
        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }
}