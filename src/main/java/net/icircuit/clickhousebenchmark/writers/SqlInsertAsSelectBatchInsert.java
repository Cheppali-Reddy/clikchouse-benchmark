package net.icircuit.clickhousebenchmark.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class SqlInsertAsSelectBatchInsert extends SqlBatchInsert {
    private String INSERT_QUERY =
            "INSERT INTO user_events SELECT tenant_id, event_id, external_event_id, name, type, sub_type, category, event_timestamp, ingested_at, source, event_prop_keys, event_prop_values, actor_prop_keys, actor_prop_values, context_prop_keys, context_prop_values, raw_payload FROM  "
                    + "input('tenant_id UInt64,event_id UInt64,external_event_id String,name String,type String,sub_type String,category String,event_timestamp DateTime64(3),ingested_at DateTime64(3),source String,event_prop_keys Array(String),event_prop_values Array(String),actor_prop_keys Array(String),actor_prop_values Array(String),context_prop_keys Array(String),context_prop_values Array(String),raw_payload String')";

    @Override
    public String name() {
        return "sql-insert-from-select";
    }

    @Autowired
    public SqlInsertAsSelectBatchInsert(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }
}
