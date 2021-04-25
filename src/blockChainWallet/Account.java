package blockChainWallet;

import java.io.*;

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
	
	public void setWallet(String PrivateKey, String PublicKey) {
//		this.wallet.se
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
            out.write(this.getPassWord()+ "|");
            out.write( 
            		this.getWallet().getPublicKey() + "|");
            out.write(
            		this.getWallet().getPrivateKey() + "|");
            out.newLine();
        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }
    
    public boolean readFile(String s){ 
        if (s!=null){
//            String[] inp = s.split("\\|");
//            this.setId(inp[0]);
//            this.setPosition(inp[1]);
//            this.setUser(inp[2]);
//            this.setPassword(inp[3]);
            return true;
        }
        return false;
    }
}
