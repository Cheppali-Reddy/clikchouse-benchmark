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
            "INSERT INTO sample_records (id,text1,text2,text3,textArray1,textArray2,textArray3,textArray4,"
                    + "decimalArray1,decimalArray2,numberArray1,numberArray2,dateTimeFields1) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
    public void insertBatch(List<SampleRecord> records) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            for (SampleRecord record : records) {
                addRecordToStatement(preparedStatement, record);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addRecordToStatement(PreparedStatement stmt, SampleRecord record) throws SQLException {
        stmt.setLong(1, record.getId());
        stmt.setString(2, record.getText1());
        stmt.setString(3, record.getText2());
        stmt.setString(4, record.getText3());
        stmt.setObject(5, record.getTextArray1());
        stmt.setObject(6, record.getTextArray2());
        stmt.setObject(7, record.getTextArray3());
        stmt.setObject(8, record.getTextArray4());
        stmt.setObject(9, record.getDecimalArray1());
        stmt.setObject(10, record.getDecimalArray2());
        stmt.setObject(11, record.getNumberArray1());
        stmt.setObject(12, record.getNumberArray2());
        stmt.setObject(13, Arrays.stream(record.getDateTimeFields1())
                                 .map(Timestamp::from).toArray(Timestamp[]::new));
    }
}
