package com.hcdisat.weekone.models;

/**
 * If this is not used lets remove it
 */
public interface IPersistenceService {

    void setValue(String key, String value);

    String getValue(String key);
}
