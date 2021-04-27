package blockChainWallet;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class test {

	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		
		AccountHR accList = new AccountHR();
		
		Human human1 = new Human("Nguyen Tuan Anh", "18");
//		Human human2 = new Human("Tang Chi Chung", "18");
//		Human human3 = new Human("Nguyen Van Thanh", "18");
		
		Account A = new Account("NTA", "123", new Wallet(), human1);
		System.out.println( A.getWallet().getPrivateKey());
		System.out.println( A.getWallet().getPublicKey() );
		
//		Account B = new Account("TCC", "123", new Wallet(), human2);
//		Account C = new Account("NVT", "123", new Wallet(), human3);

		accList.add(A);
//		accList.add(B);
//		accList.add(C);

		
//	    byte[] publicKeyBytes = A.getWallet().getPrivateKey().getEncoded();
//		System.out.println( publicKeyBytes );
//		
//		//converting byte to String 
//		String str_key = Base64.getEncoder().encodeToString(publicKeyBytes);
//		// String str_key = new String(byte_pubkey,Charset.);
//		System.out.println("STRING KEY::" + str_key);
//		
//		//converting string to Bytes
//		publicKeyBytes  = Base64.getDecoder().decode(str_key);
//		System.out.println("BYTE KEY::" + publicKeyBytes);
//
//	    KeyFactory keyFactory = null;
//		try {
//			keyFactory = KeyFactory.getInstance("ECDSA", "BC");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchProviderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(publicKeyBytes);
//	    PrivateKey publicKey2 =null ;
//	    try {
//			publicKey2 = keyFactory.generatePrivate(publicKeySpec);
//		} catch (InvalidKeySpecException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//		System.out.println( publicKey2 );
		
	    

		
//		try {
//			accList.writeFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		try {
			accList.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
