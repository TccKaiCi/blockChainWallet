package blockChainWallet;

import java.util.*;
import java.security.PublicKey;

public class AccountHR {
	private List<Account> accList;
    Scanner gets = new Scanner(System.in);
	
	public AccountHR() {
		accList = new ArrayList<>();
	}
    
    public void add(Account acc){
    	accList.add(acc);
    }
    
    public boolean signIn(Account acc) {
    	for (Account a : accList) {
    		if ( a.getUserName().equals(acc.getUserName()) ) {
    			return a.signIn(acc.getPassWord());
    		}
    	}
    	return false;
    }
    
    public List<Account> getListAcc() {
    	return accList;
    }
    
    public Account getAccount(Account acc) {
    	for (Account a : accList) {
    		if ( a.getUserName().equals(acc.getUserName()) && a.signIn(acc.getPassWord())) {
    			return a;
    		}
    	}
    	return null;
    }
    
    public PublicKey getPublicKey(String name) {
    	for (Account a : accList) {
    		if ( a.getUserName().equalsIgnoreCase(name) ) {
    			return a.getWallet().getPublicKey();
    		}
    	}
    	return null;
    }
    
    public Account getAccountByPublicKey(String key) {
    	for (Account a : accList) {
    		String temp = StringUtil.getStringFromKey( a.getWallet().getPublicKey() );
    		temp = StringUtil.applySha256(temp);
    		if ( temp.equals(key) ) {
    			return a;
    		}
    	}
    	return null;
    }
    
    public Account getAccount(String publicKey) {
    	for (Account a : accList) {
    		if (  StringUtil.getStringFromKey( a.getWallet().getPublicKey() ).equals(publicKey) ) {
    			return a;
    		}
    	}
    	return null;
    }
    
    public void getInforAll() {
    	for (Account a : accList) {
    		System.out.println(
    				a.getUserName() + " have: " + a.getWallet().getBalance());
    	}
    }    
}
