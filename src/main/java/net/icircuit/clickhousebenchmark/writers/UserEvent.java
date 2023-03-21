package net.icircuit.clickhousebenchmark.writers;

import java.time.Instant;

public class UserEvent {
    private Long tenantId;
    private Long eventId;
    private String externalEventId;

    private String name;

    private String type;

    private String subType;

    private String category;

    private Instant eventTimestamp;

    private Instant ingestedAt; // number ms from EPOCH

    private String source;

    private String[] eventPropKeys;

    private String[] eventPropValues;

    private String[] actorPropKeys;

    private String[] actorPropValues;

    private String[] contextPropKeys;

    private String[] contextPropValues;

    private String rawPayload;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getExternalEventId() {
        return externalEventId;
    }

    public void setExternalEventId(String externalEventId) {
        this.externalEventId = externalEventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Instant getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Instant eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Instant getIngestedAt() {
        return ingestedAt;
    }

    public void setIngestedAt(Instant ingestedAt) {
        this.ingestedAt = ingestedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String[] getEventPropKeys() {
        return eventPropKeys;
    }

    public void setEventPropKeys(String[] eventPropKeys) {
        this.eventPropKeys = eventPropKeys;
    }

    public String[] getEventPropValues() {
        return eventPropValues;
    }

    public void setEventPropValues(String[] eventPropValues) {
        this.eventPropValues = eventPropValues;
    }

    public String[] getActorPropKeys() {
        return actorPropKeys;
    }

    public void setActorPropKeys(String[] actorPropKeys) {
        this.actorPropKeys = actorPropKeys;
    }

    public String[] getActorPropValues() {
        return actorPropValues;
    }

    public void setActorPropValues(String[] actorPropValues) {
        this.actorPropValues = actorPropValues;
    }

    public String[] getContextPropKeys() {
        return contextPropKeys;
    }

    public void setContextPropKeys(String[] contextPropKeys) {
        this.contextPropKeys = contextPropKeys;
    }

    public String[] getContextPropValues() {
        return contextPropValues;
    }

    public void setContextPropValues(String[] contextPropValues) {
        this.contextPropValues = contextPropValues;
    }

    public String getRawPayload() {
        return rawPayload;
    }

    public void setRawPayload(String rawPayload) {
        this.rawPayload = rawPayload;
    }
}
