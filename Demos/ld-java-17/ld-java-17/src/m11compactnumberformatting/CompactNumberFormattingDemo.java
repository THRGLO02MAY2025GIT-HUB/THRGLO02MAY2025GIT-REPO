package m11compactnumberformatting;

import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumberFormattingDemo {
    public static void main(String[] args) {
        // Compact Number Formatting allows format numbers in a human-readable format in common scales (K, M, B, T)
        // Use case : Display large numbers in financial apps
        System.out.println("Basic Formatting");
        demoBasicFormatting();
    }

    private static void demoBasicFormatting() {
        // Format 1000 to 1K and similarly  1M, 1B
        NumberFormat numberFormat = NumberFormat.getCompactNumberInstance(
                Locale.US, NumberFormat.Style.SHORT);
        // K represents thousands (Kilo) - 1000 become 1K
        System.out.println(numberFormat.format(1000));
        // M represents millions (Million) - 1000000 become 1M
        System.out.println(numberFormat.format(1000000));
        // B represents billions (Billion) - 1000 become 1B
        System.out.println(numberFormat.format(1000000000));
        System.out.println(numberFormat.format(1000000000000L));
        // IP
        // Default max fraction digits are 0
        System.out.println(numberFormat.getMaximumFractionDigits());
        numberFormat.setMaximumFractionDigits(1);
        System.out.println(numberFormat.format(1500));
        // Set max fraction digits to control decimal places
        // 1500 to 1.5K
        numberFormat.setMaximumFractionDigits(2);
        System.out.println(numberFormat.format(23456));
        // 23456 23.46K
        // 100000 to 100K
        // Try trillion too, put the number of zero's for trillion
        // Like the SHORT format, try LONG format too.
        numberFormat = NumberFormat.getCompactNumberInstance(
                Locale.US, NumberFormat.Style.LONG);
        numberFormat.setMaximumFractionDigits(1);
        System.out.println(numberFormat.format(1500));
    }
}
