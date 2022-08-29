package com.future.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class EncryptUtils {

    private EncryptUtils() {
    }

    public static @NotNull String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static @NotNull String encodeBase64(@NotNull String data) {
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] decodeBase64(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static byte[] decodeBase64(byte[] data) {
        return Base64.getDecoder().decode(data);
    }

    public static @NotNull String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static @NotNull String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    public static byte @NotNull [] md5(String data) {
        return DigestUtils.md5(data);
    }

    public static byte[] md5(byte[] data) {
        return DigestUtils.md5(data);
    }

    public static @NotNull String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }

    public static @NotNull String hex(@NotNull String data) {
        return Hex.encodeHexString(data.getBytes(StandardCharsets.UTF_8));
    }

    public static @NotNull String encodePassword(@NotNull String originalPassword, @Nullable String salt) {
        if (salt == null) {
            return encodePassword(originalPassword);
        }
        return md5Hex(md5Hex(originalPassword) + salt);
    }

    public static @NotNull String encodePassword(String originalPassword) {
        return md5Hex(md5Hex(originalPassword));
    }

}
