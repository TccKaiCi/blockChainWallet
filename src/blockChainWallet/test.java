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
		
		String src = 
				"hash: 000935a54e41129c3de68ad69818b24ead9185be25f43ff9b2a95f34555ec687|preHash: 000f0b7bc1d09c23746ff8752aec52d23c9580311b75f7e791771171bcc9eaf0|Merkle: 1b7f4a1b6717e647bf5d5fb6b8ac30efa21cc0399165211f97d682afff461601|TimeStamp: 1619781289843|Nonce: 1774|transactionId: 1b7f4a1b6717e647bf5d5fb6b8ac30efa21cc0399165211f97d682afff461601|SenderKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAE6VtQQgRbJsLx41SCOUH1jJtPJWpZLM0+knffItVq+17Xbn8fusdm+GxpPKtpmext|ReciverKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAEL63thgCTw9HPLuDq9uOdM0nZkx5XtJTVh0Jd/N83oMaSAIu9TY8zDXXuuKoDwVE4|Value: 180.0|Signature: MDYCGQDm7qXeUS97JImoT6TvAsq67IrgN8bYpucCGQDTKOTa3HGA342U7nAPNfDSUy+Lo2+vO2c=|transactionOutputId: 5a53a7478c7bf23ed8d12c6e9ee4f303d44367b774a22488c6eefecadf216bc7|IdOutput: 5a53a7478c7bf23ed8d12c6e9ee4f303d44367b774a22488c6eefecadf216bc7|SenderKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAE6VtQQgRbJsLx41SCOUH1jJtPJWpZLM0+knffItVq+17Xbn8fusdm+GxpPKtpmext|Value: 20.0|parentTransactionId: 44949eb8e03dbf766c0e9d7a7bcc6684b09fe177340095044db541565d98a7be|transactionOutputId: 9bd117b33af49f7d4d3215396883eff749be9004536a31d5b3c3de4992323e9f|IdOutput: 9bd117b33af49f7d4d3215396883eff749be9004536a31d5b3c3de4992323e9f|SenderKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAE6VtQQgRbJsLx41SCOUH1jJtPJWpZLM0+knffItVq+17Xbn8fusdm+GxpPKtpmext|Value: 100.0|parentTransactionId: 57a49cc3a06a0d49138fd4beadcf5eeaf782f0f7a03269a2afc5415ac3156cdc|transactionOutputId: e01f2a4b868981a579add0095cbe54a1e8126246b22a34c3515decfb6ed5ffe6|IdOutput: e01f2a4b868981a579add0095cbe54a1e8126246b22a34c3515decfb6ed5ffe6|SenderKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAE6VtQQgRbJsLx41SCOUH1jJtPJWpZLM0+knffItVq+17Xbn8fusdm+GxpPKtpmext|Value: 60.0|parentTransactionId: 1d0bd299e24415d4ffa6fea94ef8d281ba5a0c8265626f1ad713bf2e4e7ce74d|IdOutput: d18c9e35003a173b8cb61adb871c73d534d40076542ccaf0d42da727415f3bb6|SenderKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAEL63thgCTw9HPLuDq9uOdM0nZkx5XtJTVh0Jd/N83oMaSAIu9TY8zDXXuuKoDwVE4|Value: 180.0|parentTransactionId: 1b7f4a1b6717e647bf5d5fb6b8ac30efa21cc0399165211f97d682afff461601|IdOutput: a5dc974bf443a30282c9c1bb02bc363123514684e1e7491e601ee87d1c33e3d7|SenderKey: MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAE6VtQQgRbJsLx41SCOUH1jJtPJWpZLM0+knffItVq+17Xbn8fusdm+GxpPKtpmext|Value: 0.0|parentTransactionId: 1b7f4a1b6717e647bf5d5fb6b8ac30efa21cc0399165211f97d682afff461601|";
		
		
        String[] s = src.split("\\|");

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

	}

	public void testAccount() {
AccountHR accList = new AccountHR();
		
		Human human1 = new Human("Nguyen Tuan Anh");
		Human human2 = new Human("Tang Chi Chung");
		Human human3 = new Human("Nguyen Van Thanh");
		
		Account A = new Account("NTA", "123", new Wallet(), human1);
		Account B = new Account("TCC", "123", new Wallet(), human2);
		Account C = new Account("NVT", "123", new Wallet(), human3);
		
		accList.add(A);
		accList.add(B);
		accList.add(C);

		
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

		accList.display();
	}
}
