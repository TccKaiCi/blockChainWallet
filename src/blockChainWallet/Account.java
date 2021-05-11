package blockChainWallet;

import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.io.*;
import java.util.*;

public class Account {
	
	private String userName;
	private String passWord;
	private Wallet wallet;
	private Human human;
		
	public Account() {
		this.human = new Human();
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
        	out.write(this.getPassWord() + "|");
        	out.write(this.getHuman().getName() + "|");
        	
        	//converting byte to String 
        	String str_key = Base64.getEncoder().encodeToString(this.getWallet().getPublicKey().getEncoded());
        	out.write(str_key + "|");
        	
    		String skey = Base64.getEncoder().encodeToString(this.getWallet().getPrivateKey().getEncoded());
        	out.write(skey + "|");
            out.newLine();
        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }
    
    public boolean readFile(String s){ 
        if (s!=null){
            String[] inp = s.split("\\|");
            this.setUserName(inp[0]);
            this.setPassWord(inp[1]);
            this.human.setName(inp[2]);

            this.wallet = new Wallet();
            
            KeyFactory keyFactory = null;
            try {
            	keyFactory = KeyFactory.getInstance("ECDSA", "BC");
            } catch (NoSuchAlgorithmException | NoSuchProviderException e1) {
            	e1.printStackTrace();
            }

            byte[] KeyBytes  = Base64.getDecoder().decode(inp[3]);
            
            // get public key
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(KeyBytes);
            PublicKey publicKey2 =null ;
            try {
            	publicKey2 = keyFactory.generatePublic(publicKeySpec);
            } catch (InvalidKeySpecException e) {
            	e.printStackTrace();
            }
            
    		this.wallet.setPublicKey(publicKey2);
    		
    		// get Private key
    		KeyBytes  = Base64.getDecoder().decode(inp[4]);
    		
    		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(KeyBytes);
            PrivateKey privateKey =null ;
            try {
            	privateKey = keyFactory.generatePrivate(privateKeySpec);
            } catch (InvalidKeySpecException e) {
            	e.printStackTrace();
            }
            
            this.wallet.setPrivateKey(privateKey);

            return true;
        }
        return false;
    }
}
