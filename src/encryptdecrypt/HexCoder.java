package encryptdecrypt;

public class HexCoder {

	public static void main(String[] args) {
		
	}

	public static byte[] decode(byte[] array) {
		try {
			char[] data;
			data = new String(array, "UTF-8").toCharArray();
			int len = data.length;
	        if ((len & 0x01) != 0) {
	            throw new RuntimeException("Odd number of characters.");
	        }

	        byte[] out = new byte[len >> 1];

	        for (int i = 0, j = 0; j < len; i++) {
	            int f = toDigit(data[j], j) << 4;
	            j++;
	            f = f | toDigit(data[j], j);
	            j++;
	            out[i] = (byte) (f & 0xFF);
	        }
	        	
	        return out;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String encodeHexString(byte[] data) {

		char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return new String(out);
	}

	
    protected static int toDigit(char ch, int index)  {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal charcter " + ch + " at index " + index);
        }
        return digit;
    }

    
}
