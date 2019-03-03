package wmyskxz.blog.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import wmyskxz.blog.module.entity.User;

/**
 * 密码工具类
 *
 * @auth:wmyskxz
 * @date:2019/03/03 - 09:33
 */
public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getSalt()),
                                            hashIterations).toHex();
        user.setPassword(newPassword);
    }

    public static String getPassword(User user) {
        String encryptPassword = new SimpleHash(algorithmName, user.getPassword(),
                                                ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
        return encryptPassword;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        encryptPassword(user);
        System.out.println("加密后的密码:" + user.getPassword());
        System.out.println("随机设置的salt值" + user.getSalt());
        user.setPassword("123");
        System.out.println("getPassword:" + getPassword(user));
    }
}
