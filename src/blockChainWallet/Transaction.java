package blockChainWallet;

import java.security.*;
import java.util.ArrayList;

public class Transaction {
	
	public String transactionId; // đây cũng là băm của giao dịch.
	public PublicKey sender; // địa chỉ ngư�?i gửi / khóa công khai.
	public PublicKey reciepient;// �?ịa chỉ ngư�?i nhận / khóa công khai.
	public float value;
	public byte[] signature; // đi�?u này là để ngăn không cho bất kỳ ai khác chi tiêu ti�?n trong ví của người kha�?c
	
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	private static int sequence = 0; // tổng số sơ bộ có bao nhiêu giao dịch đã được tạo.
	
	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	// �?i�?u này Tính toán hash giao dịch (sẽ được sử dụng làm Id của nó)
	private String calulateHash() {
		sequence++;// tăng chuỗi để tránh 2 giao dịch giống nhau có cùng một hash 
		return StringUtil.applySha256(StringUtil.getStringFromKey(sender) 
				+ StringUtil.getStringFromKey(reciepient)
		+ Float.toString(value) + sequence);
	}
	
	// Trả ve true nếu có thể tạo giao dịch mới.
	public boolean processTransaction() {
		
		if (verifiySignature() == false) {
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}
		
		// thu thập các đầu vào giao dịch (Dảm bảo chúng chưa được sử dụng):
		for (TransactionInput i : inputs) {
			i.UTXO = Main.UTXOs.get(i.transactionOutputId);
		}
		
		// kiểm tra xem giao dịch có hợp lệ không:
		if (getInputsValue() < 0.1f) {
			System.out.println("#Transaction Inputs to small: " + getInputsValue());
			return false;
		}
		
		// tạo kết quả giao dịch:
		float leftOver = getInputsValue() - value; // lấy giá trị của đầu vào sau đó thay đổi bên trái:
		transactionId = calulateHash();
		outputs.add(new TransactionOutput(this.reciepient, value, transactionId)); // gửi giá trị cho ngư�?i nhận
		outputs.add(new TransactionOutput(this.sender, leftOver, transactionId)); // gửi lại 'thay đổi' còn lại cho ngư�?i gửi
		
		// thêm đầu ra vào danh sách Chưa gửi
		for (TransactionOutput o : outputs) {
			Main.UTXOs.put(o.id, o);
		}
		
		// xóa đầu vào giao dịch khoi danh sách UTXO như đã chi tiêu:
		for (TransactionInput i : inputs) {
			if (i.UTXO == null)
				continue; // nếu không tìm thấy Giao dịch, hãy b�? qua
			Main.UTXOs.remove(i.UTXO.id);
		}
		
		return true;
	}
	
	// trả v�? tổng giá trị đầu vào (UTXO)
	public float getInputsValue() {
		float total = 0;
		for (TransactionInput i : inputs) {
			if (i.UTXO == null)
				continue; // nếu không tìm thấy Giao dịch, hãy b�? qua
			total += i.UTXO.value;
		}
		return total;
	}
	
	// trả ve tổng kết quả đầu ra:
	public float getOutputsValue() {
		float total = 0;
		for (TransactionOutput o : outputs) {
			total += o.value;
		}
		return total;
	}
	
	// Báo hiệu tất cả dữ liệu mà chúng tôi không muốn bị giả mạo.
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient)
		+ Float.toString(value);
		signature = StringUtil.applyECDSASig(privateKey, data);
	}
	
	// Xác minh dữ liệu chúng tôi đã ký không bị giả mạo
	public boolean verifiySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient)
		+ Float.toString(value);
		return StringUtil.verifyECDSASig(sender, data, signature);
	}
}
