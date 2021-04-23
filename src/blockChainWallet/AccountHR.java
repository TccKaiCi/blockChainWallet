package blockChainWallet;

import java.util.*;
import java.io.*;

public class AccountHR {
	private List<Account> accList;
	
	private String File_Users  ="./src/Qly_NhanSu/Account/acc.txt";
    
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
    
    public void writeFile() throws IOException{   
        BufferedWriter xuat = new BufferedWriter(new FileWriter(File_Users));
        for ( Account a : accList ) 
            a.writeFile(xuat);
        xuat.close();
    }
    
    public void readFile() throws IOException{    
        BufferedReader nhap = null;
        try {
            nhap = new BufferedReader(new FileReader(File_Users));
            String s = null;
            do {
                s = nhap.readLine();
                Account u = new Account();
                if ( u.readFile(s) )
                	accList.add(u);
            } while ( s!=null);
            
            nhap.close();
        } catch (Exception e) {
            System.out.println("Something wrong when reading Account file");
        }
    }
    
}
