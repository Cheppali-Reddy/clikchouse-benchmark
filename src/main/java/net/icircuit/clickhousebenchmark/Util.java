package net.icircuit.clickhousebenchmark;

import net.datafaker.Faker;
import net.icircuit.clickhousebenchmark.writers.SampleRecord;
import net.icircuit.clickhousebenchmark.writers.UserEvent;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Util {
    Faker faker = new Faker();

    public SampleRecord crateRandomRecord(long id) {
        SampleRecord sampleRecord = new SampleRecord();
        sampleRecord.setId(id);
        sampleRecord.setText1(faker.lorem().word());
        sampleRecord.setText2(faker.lorem().sentence());
        sampleRecord.setText3(faker.lorem().paragraph());

        sampleRecord.setTextArray1(randomTextArray(10));
        sampleRecord.setTextArray2(randomTextArray(10));
        sampleRecord.setTextArray3(randomTextArray(10));
        sampleRecord.setTextArray4(randomTextArray(20));

        sampleRecord.setDecimalArray1(randomDoubleArray(5));
        sampleRecord.setDecimalArray2(randomDoubleArray(6));

        sampleRecord.setNumberArray1(randomNumberArray(5));
        sampleRecord.setNumberArray2(randomNumberArray(6));

        sampleRecord.setDateTimeFields1(randomDateArray(5));
        return sampleRecord;
    }

    private String[] randomTextArray(int size) {
        String[] txtArr = new String[size];
        for (int i = 0; i < size; i++) {
            txtArr[i] = faker.lorem().sentence();
        }
        return txtArr;
    }

    private Long[] randomNumberArray(int size) {
        Long[] numberArray = new Long[size];
        for (int i = 0; i < size; i++) {
            numberArray[i] = faker.number().randomNumber();
        }
        return numberArray;
    }

    private Double[] randomDoubleArray(int size) {
        Double[] array = new Double[size];
        for (int i = 0; i < size; i++) {
            array[i] = faker.number().randomDouble(2, -100, 100);
        }
        return array;
    }

    private Instant[] randomDateArray(int size) {
        var array = new Instant[size];
        for (int i = 0; i < size; i++) {
            array[i] = faker.date().past(300, TimeUnit.DAYS).toInstant();
        }
        return array;
    }

    public UserEvent createRandomEvent(int i) {
        UserEvent userEvent = new UserEvent();
        userEvent.setTenantId(faker.number().randomNumber());
        userEvent.setEventId((long)i);
        userEvent.setExternalEventId(UUID.randomUUID().toString());
        userEvent.setName(faker.name().firstName());
        userEvent.setType(faker.lorem().word());
        userEvent.setSubType(faker.lorem().word());
        userEvent.setCategory(faker.lorem().word());
        userEvent.setEventTimestamp(Instant.now());
        userEvent.setIngestedAt(Instant.now());
        userEvent.setSource(faker.lorem().word());

        userEvent.setEventPropKeys(randomTextArray(20));
        userEvent.setEventPropValues(randomTextArray(20));

        userEvent.setActorPropKeys(randomTextArray(5));
        userEvent.setActorPropValues(randomTextArray(5));
        userEvent.setContextPropKeys(randomTextArray(5));
        userEvent.setContextPropValues(randomTextArray(5));
        userEvent.setRawPayload(faker.lorem().sentence(2000)); // 200 words for 1KB
        return userEvent;
    }
}
