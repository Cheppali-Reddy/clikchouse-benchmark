package net.icircuit.clickhousebenchmark.writers;

import java.util.List;

public interface ChBatchWriter {
    String name();

    void insertBatch(List<SampleRecord> records);
}
