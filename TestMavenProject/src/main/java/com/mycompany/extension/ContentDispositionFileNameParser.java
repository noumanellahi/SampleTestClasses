package com.mycompany.extension;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * This parser implementation is taken out from Spring framework.
 * 
 * Parses "filename" parameter of the Content-Disposition HTTP header as defined
 * in RFC 6266.
 *
 * @author Sebastien Deleuze
 * @author Juergen Hoeller
 * @author Rossen Stoyanchev
 */
final class ContentDispositionFileNameParser {

	private static final String INVALID_HEADER_FIELD_PARAMETER_FORMAT = "Invalid header field parameter format (as defined in RFC 5987)";

	/**
	 * Parse a {@literal Content-Disposition} header value as defined in RFC 2183.
	 *
	 * @param contentDisposition the {@literal Content-Disposition} header value
	 * @return Return the value of the {@literal filename} parameter (or the value
	 *         of the {@literal filename*} one decoded as defined in the RFC 5987),
	 *         or {@code null} if not defined.
	 */
	public String parse(String contentDisposition) {
		String filename = null;
		try {
			List<String> parts = tokenize(contentDisposition);
			Charset charset;
			for (int i = 1; i < parts.size(); i++) {
				String part = parts.get(i);
				int eqIndex = part.indexOf('=');
				if (eqIndex != -1) {
					String attribute = part.substring(0, eqIndex);
					String value = (part.startsWith("\"", eqIndex + 1) && part.endsWith("\"")
							? part.substring(eqIndex + 2, part.length() - 1)
							: part.substring(eqIndex + 1));
					if (attribute.equals("filename*")) {
						int idx1 = value.indexOf('\'');
						int idx2 = value.indexOf('\'', idx1 + 1);
						if (idx1 != -1 && idx2 != -1) {
							charset = Charset.forName(value.substring(0, idx1).trim());
							if (!(UTF_8.equals(charset) || ISO_8859_1.equals(charset))) {
								throw new IllegalArgumentException("Charset should be UTF-8 or ISO-8859-1");
							}
							filename = decodeFilename(value.substring(idx2 + 1), charset);
						} else {
							// US ASCII
							filename = decodeFilename(value, StandardCharsets.US_ASCII);
						}
					} else if (attribute.equals("filename") && (filename == null)) {
						filename = value;
					}
				} else {
					throw new IllegalArgumentException("Invalid content disposition format");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (StringUtils.isEmpty(filename)) {
			FileExtension fileExtension = new FileExtension();
			filename = fileExtension.getFileNameFromContentDisposition(contentDisposition);
		}
		return filename;
	}

	private List<String> tokenize(String headerValue) {
		int index = headerValue.indexOf(';');
		String type = (index >= 0 ? headerValue.substring(0, index) : headerValue).trim();
		if (type.isEmpty()) {
			throw new IllegalArgumentException("Content-Disposition header must not be empty");
		}
		List<String> parts = new ArrayList<>();
		parts.add(type);
		if (index >= 0) {
			do {
				int nextIndex = index + 1;
				boolean quoted = false;
				boolean escaped = false;
				while (nextIndex < headerValue.length()) {
					char ch = headerValue.charAt(nextIndex);
					if (ch == ';') {
						if (!quoted) {
							break;
						}
					} else if (!escaped && ch == '"') {
						quoted = !quoted;
					}
					escaped = (!escaped && ch == '\\');
					nextIndex++;
				}
				String part = headerValue.substring(index + 1, nextIndex).trim();
				if (!part.isEmpty()) {
					parts.add(part);
				}
				index = nextIndex;
			} while (index < headerValue.length());
		}
		return parts;
	}

	/**
	 * Decode the given header field param as described in RFC 5987.
	 * <p>
	 * Only the US-ASCII, UTF-8 and ISO-8859-1 charsets are supported.
	 *
	 * @param filename the filename
	 * @param charset  the charset for the filename
	 * @return the encoded header field param
	 */
	private String decodeFilename(String filename, Charset charset) {
		if (filename == null) {
			throw new IllegalArgumentException("'input' String` should not be null");
		}
		if (charset == null) {
			throw new IllegalArgumentException("'charset' should not be null");
		}
		byte[] value = filename.getBytes(charset);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int index = 0;
		while (index < value.length) {
			byte b = value[index];
			if (isRFC5987AttrChar(b)) {
				byteArrayOutputStream.write((char) b);
				index++;
			} else if (b == '%' && index < value.length - 2) {
				char[] array = new char[] { (char) value[index + 1], (char) value[index + 2] };
				try {
					byteArrayOutputStream.write(Integer.parseInt(String.valueOf(array), 16));
				} catch (NumberFormatException ex) {
					throw new IllegalArgumentException(INVALID_HEADER_FIELD_PARAMETER_FORMAT, ex);
				}
				index += 3;
			} else {
				throw new IllegalArgumentException(INVALID_HEADER_FIELD_PARAMETER_FORMAT);
			}
		}
		try {
			return byteArrayOutputStream.toString(charset.name());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Failed to copy contents of ByteArrayOutputStream into a String", e);
		}
	}

	private boolean isRFC5987AttrChar(byte c) {
		return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '!' || c == '#'
				|| c == '$' || c == '&' || c == '+' || c == '-' || c == '.' || c == '^' || c == '_' || c == '`'
				|| c == '|' || c == '~';
	}
}
