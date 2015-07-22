package test4solanteq.utils;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"MagicNumber", "ReturnOfNull"})
public class Utils {
    private static final String HEXES = "0123456789ABCDEF";

    public static String rawToHex(final byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(raw.length * 2);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt(b & 0x0F));
        }
        return hex.toString();
    }
}