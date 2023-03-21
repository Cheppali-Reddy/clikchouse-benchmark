package net.icircuit.clickhousebenchmark.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class SqlOpenBatchInsert extends SqlBatchInsert {
    private String INSERT_QUERY =
            "INSERT INTO user_events";

    @Autowired
    public SqlOpenBatchInsert(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String name() {
        return "sql-open-batch";
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }
}
