package tw.jacky.servlet;

import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;





public class AESUtil {
	private static final String CHARSET_NAME = "UTF-8";
	private static final String AES_NAME = "AES";
	// 加密模式
	public static final String ALGORITHM = "AES/CBC/PKCS7Padding";
	// 密钥
	public static final String KEY = "XBERn1YOvpM9nfZc";
	// 偏移量
	public static final String IV = "h1ONHk4P4yqbl5LK";
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 加密
	 *
	 * @param content
	 * @param key
	 * @return
	 */
	public String encrypt( String content) {
		byte[] result = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
			result = cipher.doFinal(content.getBytes(CHARSET_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.encodeBase64String(result);
	}

	/**
	 * 解密
	 *
	 * @param content
	 * @param key
	 * @return
	 */
	public String decrypt( String content) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET_NAME), AES_NAME);
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
			return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error  ";
	}

	public static void main(String[] args) {
		AESUtil aes = new AESUtil();
		String contents = "121456465";
		String encrypt = aes.encrypt(contents);
		System.out.println("加密后:" + encrypt);
		String decrypt = aes.decrypt(encrypt);
		System.out.println("解密后:" + decrypt);
	}
}
