package blockChainWallet;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class test {

	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
		
		AccountHR accList = new AccountHR();
		
		Human human1 = new Human("Nguyen Tuan Anh", "18");
		Human human2 = new Human("Tang Chi Chung", "18");
		Human human3 = new Human("Nguyen Van Thanh", "18");
		
		Account A = new Account("NTA", "123", new Wallet(), human1);
		Account B = new Account("TCC", "123", new Wallet(), human2);
		Account C = new Account("NVT", "123", new Wallet(), human3);

		accList.add(A);
		accList.add(B);
		accList.add(C);

		System.out.println( A.getWallet().getPublicKey() );
		

	    byte[] publicKeyBytes = A.getWallet().getPublicKey().getEncoded();
	    
	    String s  = "";
	    for (int i =0 ; i < publicKeyBytes.length ; i++) {
	    	s += publicKeyBytes[i];
	    }
	    
		System.out.println(  );
		System.out.println( publicKeyBytes );
		

	    KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("ECDSA", "BC");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
	    PublicKey publicKey2 =null ;
	    try {
			publicKey2 = keyFactory.generatePublic(publicKeySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		System.out.println( publicKey2 );
		
	    

		
		try {
			accList.writeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			accList.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
