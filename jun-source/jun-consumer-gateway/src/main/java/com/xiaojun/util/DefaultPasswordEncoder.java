package com.xiaojun.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

public final class DefaultPasswordEncoder {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private final String encodingAlgorithm;
	private String characterEncoding;	
	public DefaultPasswordEncoder(String encodingAlgorithm) {
		this.encodingAlgorithm = encodingAlgorithm;
	}

	public String encode(String password) {
		if (password == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(this.encodingAlgorithm);

			if (StringUtils.hasText(this.characterEncoding))
				messageDigest.update(password.getBytes(this.characterEncoding));
			else {
				messageDigest.update(password.getBytes());
			}

			byte[] digest = messageDigest.digest();

			return getFormattedText(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new SecurityException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private String getFormattedText(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);

		for (int j = 0; j < bytes.length; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4 & 0xF)]);
			buf.append(HEX_DIGITS[(bytes[j] & 0xF)]);
		}
		return buf.toString();
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
}