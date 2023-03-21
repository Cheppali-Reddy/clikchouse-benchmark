## create table

CREATE TABLE sample_records (
id Int64,
text1 String,
text2 String,
text3 String,
textArray1 Array(String),
textArray2 Array(String),
textArray3 Array(String),
textArray4 Array(String),
decimalArray1 Array(Float64),
decimalArray2 Array(Float64),
numberArray1 Array(Int64),
numberArray2 Array(Int64),
dateTimeFields1 Array(DateTime)
) ENGINE = ReplicatedMergeTree('/clickhouse/clusters/{cluster}/tables/{shard}/sample_records', '{replica}') PARTITION BY id % 25
ORDER BY (id) SETTINGS index_granularity = 4096;


CREATE TABLE sample_records (
id Int64,
text1 String,
text2 String,
text3 String,
textArray1 Array(String),
textArray2 Array(String),
textArray3 Array(String),
textArray4 Array(String),
decimalArray1 Array(Float64),
decimalArray2 Array(Float64),
numberArray1 Array(Int64),
numberArray2 Array(Int64),
dateTimeFields1 Array(DateTime)
) ENGINE = MergeTree() PARTITION BY id % 25
ORDER BY (id) SETTINGS index_granularity = 4096;


CREATE TABLE user_events (
tenant_id UInt64,
event_id UInt64,
external_event_id String,
name String,
type String,
sub_type String,
category String,
event_timestamp DateTime64(3),
ingested_at DateTime64(3),
source String,
event_prop_keys Array(String),
event_prop_values Array(String),
actor_prop_keys Array(String),
actor_prop_values Array(String),
context_prop_keys Array(String),
context_prop_values Array(String),
raw_payload String
) ENGINE = ReplicatedMergeTree('/clickhouse/clusters/{cluster}/tables/{shard}/sample_records', '{replica}')
PARTITION BY tenant_id % 25
ORDER BY (tenant_id, event_id) SETTINGS index_granularity = 4096;

CREATE TABLE user_events (
tenant_id UInt64,
event_id UInt64,
external_event_id String,
name String,
type String,
sub_type String,
category String,
event_timestamp DateTime64(3),
ingested_at DateTime64(3),
source String,
event_prop_keys Array(String),
event_prop_values Array(String),
actor_prop_keys Array(String),
actor_prop_values Array(String),
context_prop_keys Array(String),
context_prop_values Array(String),
raw_payload String
) ENGINE = MergeTree()
PARTITION BY tenant_id % 25
ORDER BY (tenant_id, event_id) SETTINGS index_granularity = 4096;

INSERT INTO user_events (tenant_id, event_id, external_event_id, name, type, sub_type, category, event_timestamp, ingested_at, source, event_prop_keys, event_prop_values, actor_prop_keys, actor_prop_values, context_prop_keys, context_prop_values, raw_payload) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
