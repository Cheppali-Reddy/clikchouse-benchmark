package net.icircuit.clickhousebenchmark.writers;

import com.clickhouse.data.ClickHouseOutputStream;
import com.clickhouse.data.ClickHouseWriter;
import com.clickhouse.data.format.BinaryStreamUtils;
import com.clickhouse.data.format.ClickHouseRowBinaryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
public class RowBinaryStreamInsert implements ChBatchWriter {
    private String INSERT_QUERY =
            "INSERT INTO user_events format RowBinary";
    private DataSource dataSource;

    @Autowired
    public RowBinaryStreamInsert(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String name() {
        return "row-binary-stream";
    }

    @Override
    public void insertBatch(List<UserEvent> records) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery())) {
            preparedStatement.setObject(1, new ClickHouseWriter() {
                @Override
                public void write(ClickHouseOutputStream output) throws IOException {
                    // this will be executed in a separate thread
                    for (UserEvent event : records) {
                        writeRecordToStream(output, event);
                    }
                }
            });
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeRecordToStream(ClickHouseOutputStream output, UserEvent event) throws IOException {
        BinaryStreamUtils.writeUnsignedInt64(output, event.getTenantId());
        BinaryStreamUtils.writeUnsignedInt64(output, event.getEventId());

        output.writeUnicodeString(event.getExternalEventId());
        output.writeUnicodeString(event.getName());
        output.writeUnicodeString(event.getType());
        output.writeUnicodeString(event.getSubType());
        output.writeUnicodeString(event.getCategory());

        BinaryStreamUtils.writeInt64(output, event.getEventTimestamp().toEpochMilli());
        BinaryStreamUtils.writeInt64(output, event.getIngestedAt().toEpochMilli());

        output.writeUnicodeString(event.getSource());

        writeStringArray(output,event.getEventPropKeys());
        writeStringArray(output,event.getEventPropValues());
        writeStringArray(output,event.getActorPropKeys());
        writeStringArray(output,event.getActorPropValues());
        writeStringArray(output,event.getContextPropKeys());
        writeStringArray(output,event.getContextPropValues());

        output.writeUnicodeString(event.getRawPayload());
    }

    private void writeStringArray(ClickHouseOutputStream output, String[] array) throws IOException {
        output.writeVarInt(array.length);
        for (int i = 0; i < array.length; i++) {
            output.writeUnicodeString(array[i]);
        }
    }

    private void writeNumberArray(ClickHouseOutputStream output, Long[] array) throws IOException {
        output.writeVarInt(array.length);
        for (int i = 0; i < array.length; i++) {
            BinaryStreamUtils.writeInt64(output, array[i]);
        }
    }

    private void writeDoubleArray(ClickHouseOutputStream output, Double[] array) throws IOException {
        output.writeVarInt(array.length);
        for (int i = 0; i < array.length; i++) {
            BinaryStreamUtils.writeFloat64(output, array[i]);
        }
    }

    private void writeInstantArray(ClickHouseOutputStream output, Instant[] array) throws IOException {
        output.writeVarInt(array.length);
        for (int i = 0; i < array.length; i++) {
            BinaryStreamUtils.writeUnsignedInt32(output, array[i].getEpochSecond());
        }
    }

    private String getInsertQuery() {
        return INSERT_QUERY;
    }
}
