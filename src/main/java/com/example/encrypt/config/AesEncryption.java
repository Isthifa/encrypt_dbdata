package com.example.encrypt.config;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

@Configuration
public class AesEncryption implements AttributeConverter<Object,String> {

    @Value("${aes.secret}")
    private String encryptionKey;

    private final String encryptionCipher = "AES";

    private Key key;
    private Cipher cipher;

    private Key getKey() {
        if (key == null) {
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher); // this if key is null then it will create a new key with the encryption key bytes and encryption cipher
        }
        return key;
    }

    private Cipher getCipher() {
        if (cipher == null) {
            try {
                cipher = Cipher.getInstance(encryptionCipher); // this if cipher is null then it will create a new cipher with the encryption cipher
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cipher;
    }

    public void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode, getKey());
    }

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object o) {
        if (o == null) {
            return null;
        }

        initCipher(Cipher.ENCRYPT_MODE);
        byte[] encryptedValue = SerializationUtils.serialize(o);
        return Base64.getEncoder().encodeToString(getCipher().doFinal(encryptedValue));
    }

    @Override
    @SneakyThrows
    public Object convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        initCipher(Cipher.DECRYPT_MODE);
        byte[] decryptedValue = getCipher().doFinal(Base64.getDecoder().decode(s));
        return SerializationUtils.deserialize(decryptedValue);
    }
}
