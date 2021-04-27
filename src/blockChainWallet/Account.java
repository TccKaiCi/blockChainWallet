package blockChainWallet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class Account {
	
	private String userName;
	private String passWord;
	private Wallet wallet;
	private Human human;
	
	public Account() {
		
	}
	
	public Account(String userName, String passWord, Wallet wallet, Human human) {
		this.userName = userName;
		this.passWord = passWord;
		this.wallet = wallet;
		this.human = human;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public Wallet getWallet() {
		return this.wallet;
	}
	
	public Human getHuman() {
		return this.human;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public void setWallet(PrivateKey PrivateKey) {
		this.wallet.setPrivateKey(PrivateKey);
	}
	
	public boolean signIn(String passWord) {
		if (this.passWord.equals(passWord) ) {
			return true;
		}
		return false;
	}
	
	public void writeFile(BufferedWriter out) throws IOException{
        try {
        	out.write(this.getUserName() + "|");
        	out.write(this.getWallet().getPrivateKey().getEncoded() + "|");
//            out.write(this.getId() + "|");
//            out.write(this.getPosition()+ "|");
//            out.write(this.getUser() + "|");
//            out.write(this.getPassword() + "|");
            out.newLine();
        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }
    
    public boolean readFile(String s){ 
        if (s!=null){
            String[] inp = s.split("\\|");
            this.setUserName(inp[0]);
            System.out.println(inp[1]);
            
            
//            KeyFactory keyFactory = null;
//            byte[] publicKeyBytes = ;
//    		try {
//    			keyFactory = KeyFactory.getInstance("ECDSA", "BC");
//    		} catch (NoSuchAlgorithmException e) {
//    			e.printStackTrace();
//    		} catch (NoSuchProviderException e) {
//    			e.printStackTrace();
//    		}
//    		
//    	    EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
//    	    PrivateKey publicKey2 =null;
//    	    try {
//    			publicKey2 = keyFactory.generatePrivate(publicKeySpec);
//    		} catch (InvalidKeySpecException e) {
//    			e.printStackTrace();
//    		}
//    	    
//    	    System.out.println(publicKey2);
    	    
            return true;
        }
        return false;
    }
}
