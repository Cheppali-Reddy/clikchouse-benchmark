package net.icircuit.clickhousebenchmark.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Component
public class SqlBatchInsert implements ChBatchWriter {
    private String INSERT_QUERY =
            "INSERT INTO user_events (tenant_id, event_id, external_event_id, name, type, sub_type, category, event_timestamp, ingested_at, source, event_prop_keys, event_prop_values, actor_prop_keys, actor_prop_values, context_prop_keys, context_prop_values, raw_payload) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private DataSource dataSource;

    @Autowired
    public SqlBatchInsert(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String name() {
        return "sql-batch-insert";
    }

    @Override
    public void insertBatch(List<UserEvent> records) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            for (UserEvent record : records) {
                addRecordToStatement(preparedStatement, record);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addRecordToStatement(PreparedStatement insertStmt, UserEvent event) throws SQLException {
        insertStmt.setLong(1, event.getTenantId());
        insertStmt.setLong(2, event.getEventId());
        insertStmt.setString(3, event.getExternalEventId());
        insertStmt.setString(4, event.getName());
        insertStmt.setString(5, event.getType());
        insertStmt.setString(6, event.getSubType());
        insertStmt.setString(7, event.getCategory());
        insertStmt.setLong(8, event.getEventTimestamp().toEpochMilli());
        insertStmt.setLong(9, event.getIngestedAt().toEpochMilli());
        insertStmt.setString(10, event.getSource());
        insertStmt.setObject(11, event.getEventPropKeys());
        insertStmt.setObject(12, event.getEventPropValues());
        insertStmt.setObject(13, event.getActorPropKeys());
        insertStmt.setObject(14, event.getActorPropValues());
        insertStmt.setObject(15, event.getContextPropKeys());
        insertStmt.setObject(16, event.getContextPropValues());
        insertStmt.setString(17, event.getRawPayload());
    }
}
