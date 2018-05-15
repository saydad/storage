package self.start.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * create by liuyong4 2018/5/14 下午2:20
 **/
public class CommonUtils {
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    public static byte[] md5(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("md5 | Failed to md5 text: {}", text, e);
            throw e;
        }
        return messageDigest.digest();
    }

    public static void main(String[] args) {


        String content = "Shanghai";

        String s = DigestUtils.md5Hex(content);
        System.out.println(s);

    }
}
