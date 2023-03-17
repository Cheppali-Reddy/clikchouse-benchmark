package net.icircuit.clickhousebenchmark.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class SqlInsertAsSelectBatchInsert extends SqlBatchInsert {
    private String INSERT_QUERY =
            "INSERT INTO sample_records SELECT id,text1,text2,text3,textArray1,textArray2,textArray3,textArray4,"
                    + "decimalArray1,decimalArray2,numberArray1,numberArray2,dateTimeFields1 FROM  "
                    + "input('id Int64, text1 String, text2 String, text3 String, textArray1 Array(String), "
                    + "textArray2 Array(String), textArray3 Array(String), textArray4 Array(String), decimalArray1 "
                    + "Array(Float64), decimalArray2 Array(Float64), numberArray1 Array(Int64), numberArray2 Array"
                    + "(Int64), dateTimeFields1 Array(DateTime)')";

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
