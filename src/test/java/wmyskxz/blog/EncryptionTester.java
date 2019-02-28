package wmyskxz.blog;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密测试
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 13:24
 */
public class EncryptionTester {
    public static void main(String[] args) {
        String password = "123";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;// 加密次数
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ", password, salt, times, encodedPassword);
    }
}
