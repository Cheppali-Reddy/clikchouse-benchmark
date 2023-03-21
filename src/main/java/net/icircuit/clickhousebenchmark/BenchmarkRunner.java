package net.icircuit.clickhousebenchmark;

import net.icircuit.clickhousebenchmark.writers.ChBatchWriter;
import net.icircuit.clickhousebenchmark.writers.SampleRecord;
import net.icircuit.clickhousebenchmark.writers.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BenchmarkRunner {
    Util util = new Util();
    List<ChBatchWriter> batchWriters;
    @Value("${recordsPerBatch}")
    int numberOfRecords;
    @Value("${numberOfIterations}")
    int numberOfIterations;

    @Autowired
    public BenchmarkRunner(List<ChBatchWriter> batchWriters) {
        this.batchWriters = batchWriters;
    }

    public void run() {
        final List<UserEvent> records = IntStream.range(0, numberOfRecords)
                                                 .mapToObj(i -> util.createRandomEvent(i))
                                                 .collect(Collectors.toList());
        for (ChBatchWriter batchWriter : batchWriters) {
            System.out.printf("%s,", batchWriter.name());
        }
        System.out.println();
        for (int i = 0; i < numberOfIterations; i++) {
            for (ChBatchWriter batchWriter : batchWriters) {
                Long start = Instant.now().toEpochMilli();
                batchWriter.insertBatch(records);
                Long end = Instant.now().toEpochMilli();
                Long duration = end - start;
                System.out.printf("%d,", duration);
            }
            System.out.println();
        }
    }
}
