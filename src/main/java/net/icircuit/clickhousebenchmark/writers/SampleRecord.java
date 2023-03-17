package net.icircuit.clickhousebenchmark.writers;

import java.time.Instant;

public class SampleRecord {
    Long id;
    String text1;
    String text2;
    String text3;

    String[] textArray1;
    String[] textArray2;
    String[] textArray3;
    String[] textArray4;

    Double[] decimalArray1;
    Double[] decimalArray2;
    Long[] numberArray1;
    Long[] numberArray2;
    Instant[] dateTimeFields1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String[] getTextArray1() {
        return textArray1;
    }

    public void setTextArray1(String[] textArray1) {
        this.textArray1 = textArray1;
    }

    public String[] getTextArray2() {
        return textArray2;
    }

    public void setTextArray2(String[] textArray2) {
        this.textArray2 = textArray2;
    }

    public String[] getTextArray3() {
        return textArray3;
    }

    public void setTextArray3(String[] textArray3) {
        this.textArray3 = textArray3;
    }

    public String[] getTextArray4() {
        return textArray4;
    }

    public void setTextArray4(String[] textArray4) {
        this.textArray4 = textArray4;
    }

    public Double[] getDecimalArray1() {
        return decimalArray1;
    }

    public void setDecimalArray1(Double[] decimalArray1) {
        this.decimalArray1 = decimalArray1;
    }

    public Double[] getDecimalArray2() {
        return decimalArray2;
    }

    public void setDecimalArray2(Double[] decimalArray2) {
        this.decimalArray2 = decimalArray2;
    }

    public Long[] getNumberArray1() {
        return numberArray1;
    }

    public void setNumberArray1(Long[] numberArray1) {
        this.numberArray1 = numberArray1;
    }

    public Long[] getNumberArray2() {
        return numberArray2;
    }

    public void setNumberArray2(Long[] numberArray2) {
        this.numberArray2 = numberArray2;
    }

    public Instant[] getDateTimeFields1() {
        return dateTimeFields1;
    }

    public void setDateTimeFields1(Instant[] dateTimeFields1) {
        this.dateTimeFields1 = dateTimeFields1;
    }
}
