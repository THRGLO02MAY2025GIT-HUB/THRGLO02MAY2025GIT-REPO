# Lab: Exploring Compact Number Formatting 
30 Minutes

In this lab, you will explore the Compact Number Formatting API introduced in Java 12 and enhanced in Java 17. You will learn how to format numbers in a human-readable way, customize formatting for financial applications, control precision, and apply internationalization.

## Objective

- Understand the concept of compact number formatting.
- Format numbers using both SHORT and LONG styles.
- Control the precision of formatted numbers.
- Apply locale-specific formatting for internationalization.
- Compare formatting outputs across different locales.

---

## Code Overview

The `CompactNumberFormattingDemo` class demonstrates how to use the `NumberFormat.getCompactNumberInstance()` method to format numbers in a compact, human-friendly way. The class includes methods for basic formatting, transaction formatting, precision control, and internationalization.

### Key Features

1. **Basic Compact Formatting**:
    - Format numbers using the SHORT style (e.g., 1K, 1M, 1B).
    - Understand the default rounding behavior.

2. **Transaction Formatting**:
    - Compare SHORT and LONG styles.
    - Observe how numbers are represented differently (e.g., "1.2M" vs "1.2 million").

3. **Precision Control**:
    - Set the maximum number of fraction digits.
    - Format numbers with increased precision (e.g., "1.23K" instead of "1.2K").

4. **Internationalization**:
    - Format numbers for different locales (US, Germany, Japan).
    - Observe differences in notation, decimal separators, and scale words.

5. **Indian Numbering System**:
    - Format numbers using the Indian numbering system (e.g., Lakhs, Crores).
    - Observe how the formatting differs from the Western system.
---

## Lab Instructions

### Step 1: Basic Compact Formatting

1. Implement the `demoBasicFormatting()` method.
2. Format and print 1000, 1,000,000, and 1,000,000,000 using the SHORT style.
3. Observe the output and note the abbreviations used.

---

### Step 2: Transaction Formatting

1. Implement the `demoTransactionFormatting()` method.
2. Format the value `1234567.89` using both SHORT and LONG styles.
3. Print and compare the outputs.

---

### Step 3: Precision Formatting

1. Implement the `demoPrecisionFormatting()` method.
2. Set the formatter to use 2 decimal places.
3. Format and print `1234` and `1234567`.
4. Observe the difference in precision.

---

### Step 4: International Formatting

1. Implement the `demoInternationalFormatting()` method.
2. Format `1234567.89` for US, Germany, and Japan locales.
3. Print the results and compare the formatting conventions.

### Step 5: Indian Numbering System Formatting
1. Implement the `demoIndianNumberingFormatting()` method.
2. Format numbers using the Indian numbering system (e.g., Lakhs, Crores).
3. Observe how the formatting differs from the Western system.
---

### Step 6: Experiment and Extend

1. Try formatting other numbers (e.g., 5000, 10000000) and observe the output.
2. Experiment with other locales (e.g., France, India).
3. Document your observations.

---

## Deliverables

1. A completed `CompactNumberFormattingDemo` class with all formatting methods implemented.
2. Console output showing formatted numbers for each step.
3. A brief summary of differences observed between locales and styles.

---

## Code Template to Start With

```java
package com.demo.java17.m11compactnumberformatting;

import java.text.NumberFormat;
import java.util.Locale;

public class CompactNumberFormattingDemo {
    public static void main(String[] args) {
        System.out.println("Basic Formatting");
        demoBasicFormatting();
        System.out.println("Transaction Formatting");
        demoTransactionFormatting();
        System.out.println("Precision Formatting");
        demoPrecisionFormatting();
        System.out.println("Indian Numbering System Formatting");
        demoIndianNumberingFormatting();
    }

    private static void demoBasicFormatting() {
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(
                Locale.US, NumberFormat.Style.SHORT);
        // K represents thousands (Kilo) - 1000 becomes 1K
        System.out.println(fmt.format(1000));        // 1K
        // M represents millions (Mega) - 1000000 becomes 1M
        System.out.println(fmt.format(1000000));     // 1M
        // B represents billions (Billion) - 1000000000 becomes 1B
        System.out.println(fmt.format(1000000000));  // 1B
    }


    private static void demoTransactionFormatting() {
        // TODO: Implement transaction formatting with SHORT and LONG styles
    }

    private static void demoPrecisionFormatting() {
        // TODO: Implement precision control for compact formatting
    }

    private static void demoInternationalFormatting() {
        // TODO: Implement international formatting for US, Germany, and Japan
    }

    private static void demoIndianNumberingFormatting() {
        // Create formatters with different configurations

        // Configure precision and grouping maximum 3 and minimum 1 fraction digit


        // Test values in Indian numbering system
        double[] amounts = {
                12345.6789,      // Thousands
                1234567.89,      // Lakhs
                12345678.901,    // Crores
                1234567890.123   // Arabs
        };

        // Code to geneate and print output.

        // For additioanl reference (not needed to be a part of solution)
        //Structure: value, name, international format 
        Object[][] numberScales = {
            {1_000L,                    "Thousand"},
            {10_000L,                   "Ten Thousand"},
            {1_00_000L,                 "Lakh"},
            {10_00_000L,                "Ten Lakh"},
            {1_00_00_000L,              "Crore"},
            {10_00_00_000L,             "Ten Crore"},
            {1_00_00_00_000L,           "Arab"},
            {10_00_00_00_000L,          "Ten Arab"},
            {1_00_00_00_00_000L,        "Kharab"},
            {10_00_00_00_00_000L,       "Ten Kharab"},
            {1_00_00_00_00_00_000L,     "Neel"},
            {10_00_00_00_00_00_000L,    "Ten Neel"},
            {1_00_00_00_00_00_00_000L,  "Padma"}
        };
    }
}
```

---

### Expected Output

```
Basic Formatting
Transaction Formatting
Precision Formatting
International Formatting 
US: 1M
DE: 1 Mio.
JP: 123万
Indian Numbering System Formatting
-------------------------------------------
Amount: 12.346T (Currency: ₹12,345.68)
Percent of billion: 0.00%

Amount: 12.346L (Currency: ₹1,234,567.89)
Percent of billion: 0.12%

Amount: 1.235Cr (Currency: ₹12,345,678.90)
Percent of billion: 1.23%

Amount: 123.457Cr (Currency: ₹1,234,567,890.12)
Percent of billion: 123.46%
```

---
