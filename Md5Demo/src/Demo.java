import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Demo {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		//得到一个信息摘要器
		MessageDigest digest = MessageDigest.getInstance("md5");
		String password = "123456";
		byte[] result = digest.digest(password.getBytes()); 
		StringBuffer buffer = new StringBuffer();
		//把每一个byte做一个与运算0xff;
		for(byte b : result){
			//与运算
			int number = b & 0xff;//加盐
			String str = Integer.toHexString(number);
			
//			System.out.println(str);
			if(str.length() == 1){ 
				buffer.append("0");
			}
			buffer.append(str);
		}
		
		//标准的MD5加密后的结果
		System.out.println(buffer.toString());
	}
}
