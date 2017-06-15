package com.gangjian.util.encrypt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BASE64Decoder extends CharacterDecoder {

	public BASE64Decoder() {
		decode_buffer = new byte[4];
	}

	protected int bytesPerAtom() {
		return 4;
	}

	protected int bytesPerLine() {
		return 72;
	}

	protected void decodeAtom(InputStream inputstream, OutputStream outputstream, int i) throws IOException {
		byte byte0 = -1;
		byte byte1 = -1;
		byte byte2 = -1;
		byte byte3 = -1;
		if (i < 2)
			throw new IOException("BASE64Decoder: Not enough bytes for an atom.");
		int j;
		do {
			j = inputstream.read();
			if (j == -1)
				throw new IOException("StreamExhausted");
		} while (j == 10 || j == 13);
		decode_buffer[0] = (byte) j;
		j = readFully(inputstream, decode_buffer, 1, i - 1);
		if (j == -1)
			throw new IOException("StreamExhausted");
		if (i > 3 && decode_buffer[3] == 61)
			i = 3;
		if (i > 2 && decode_buffer[2] == 61)
			i = 2;
		switch (i) {
		case 4: // '\004'
			byte3 = pem_convert_array[decode_buffer[3] & 0xff];
			// fall through

		case 3: // '\003'
			byte2 = pem_convert_array[decode_buffer[2] & 0xff];
			// fall through

		case 2: // '\002'
			byte1 = pem_convert_array[decode_buffer[1] & 0xff];
			byte0 = pem_convert_array[decode_buffer[0] & 0xff];
			// fall through

		default:
			switch (i) {
			case 2: // '\002'
				outputstream.write((byte) (byte0 << 2 & 0xfc | byte1 >>> 4 & 3));
				break;

			case 3: // '\003'
				outputstream.write((byte) (byte0 << 2 & 0xfc | byte1 >>> 4 & 3));
				outputstream.write((byte) (byte1 << 4 & 0xf0 | byte2 >>> 2 & 0xf));
				break;

			case 4: // '\004'
				outputstream.write((byte) (byte0 << 2 & 0xfc | byte1 >>> 4 & 3));
				outputstream.write((byte) (byte1 << 4 & 0xf0 | byte2 >>> 2 & 0xf));
				outputstream.write((byte) (byte2 << 6 & 0xc0 | byte3 & 0x3f));
				break;
			}
			break;
		}
	}

	private static final char pem_array[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '+', '/' };
	private static final byte pem_convert_array[];
	byte decode_buffer[];

	static {
		pem_convert_array = new byte[256];
		for (int i = 0; i < 255; i++)
			pem_convert_array[i] = -1;

		for (int j = 0; j < pem_array.length; j++)
			pem_convert_array[pem_array[j]] = (byte) j;

	}

	// Mapping table from 6-bit nibbles to Base64 characters.
	private static char[] map1 = new char[64];
	static {
		int i = 0;
		for (char c = 'A'; c <= 'Z'; c++)
			map1[i++] = c;
		for (char c = 'a'; c <= 'z'; c++)
			map1[i++] = c;
		for (char c = '0'; c <= '9'; c++)
			map1[i++] = c;
		map1[i++] = '+';
		map1[i++] = '/';
	}

	// Mapping table from Base64 characters to 6-bit nibbles.
	private static byte[] map2 = new byte[128];
	static {
		for (int i = 0; i < map2.length; i++)
			map2[i] = -1;
		for (int i = 0; i < 64; i++)
			map2[map1[i]] = (byte) i;
	}

	/**
	 * Encodes a string into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param s
	 *            a String to be encoded.
	 * @return A String with the Base64 encoded data.
	 */
	public static String encodeString(String s) {
		return new String(encode(s.getBytes()));
	}

	/**
	 * Encodes a byte array into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param in
	 *            an array containing the data bytes to be encoded.
	 * @return A character array with the Base64 encoded data.
	 */
	public static char[] encode(byte[] in) {
		return encode(in, in.length);
	}

	public static String encode2String(byte[] in) {
		return new String(encode(in, in.length));
	}

	/**
	 * Encodes a byte array into Base64 format. No blanks or line breaks are
	 * inserted.
	 * 
	 * @param in
	 *            an array containing the data bytes to be encoded.
	 * @param iLen
	 *            number of bytes to process in <code>in</code>.
	 * @return A character array with the Base64 encoded data.
	 */
	public static char[] encode(byte[] in, int iLen) {
		int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
		int oLen = ((iLen + 2) / 3) * 4; // output length including padding
		char[] out = new char[oLen];
		int ip = 0;
		int op = 0;
		while (ip < iLen) {
			int i0 = in[ip++] & 0xff;
			int i1 = ip < iLen ? in[ip++] & 0xff : 0;
			int i2 = ip < iLen ? in[ip++] & 0xff : 0;
			int o0 = i0 >>> 2;
			int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
			int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
			int o3 = i2 & 0x3F;
			out[op++] = map1[o0];
			out[op++] = map1[o1];
			out[op] = op < oDataLen ? map1[o2] : '=';
			op++;
			out[op] = op < oDataLen ? map1[o3] : '=';
			op++;
		}
		return out;
	}

	/**
	 * Decodes a string from Base64 format.
	 * 
	 * @param s
	 *            a Base64 String to be decoded.
	 * @return A String containing the decoded data.
	 * @throws IllegalArgumentException
	 *             if the input is not valid Base64 encoded data.
	 */
	public static String decodeString(String s) {
		return new String(decode(s));
	}

	/**
	 * Decodes a byte array from Base64 format.
	 * 
	 * @param s
	 *            a Base64 String to be decoded.
	 * @return An array containing the decoded data bytes.
	 * @throws IllegalArgumentException
	 *             if the input is not valid Base64 encoded data.
	 */
	public static byte[] decode(String s) {
		return decode(s.toCharArray());
	}

	/**
	 * Decodes a byte array from Base64 format. No blanks or line breaks are
	 * allowed within the Base64 encoded data.
	 * 
	 * @param in
	 *            a character array containing the Base64 encoded data.
	 * @return An array containing the decoded data bytes.
	 * @throws IllegalArgumentException
	 *             if the input is not valid Base64 encoded data.
	 */
	public static byte[] decode(char[] in) {
		int iLen = in.length;
		if (iLen % 4 != 0)
			throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
		while (iLen > 0 && in[iLen - 1] == '=')
			iLen--;
		int oLen = (iLen * 3) / 4;
		byte[] out = new byte[oLen];
		int ip = 0;
		int op = 0;
		while (ip < iLen) {
			int i0 = in[ip++];
			int i1 = in[ip++];
			int i2 = ip < iLen ? in[ip++] : 'A';
			int i3 = ip < iLen ? in[ip++] : 'A';
			if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
				throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
			int b0 = map2[i0];
			int b1 = map2[i1];
			int b2 = map2[i2];
			int b3 = map2[i3];
			if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
				throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
			int o0 = (b0 << 2) | (b1 >>> 4);
			int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
			int o2 = ((b2 & 3) << 6) | b3;
			out[op++] = (byte) o0;
			if (op < oLen)
				out[op++] = (byte) o1;
			if (op < oLen)
				out[op++] = (byte) o2;
		}
		return out;
	}

}