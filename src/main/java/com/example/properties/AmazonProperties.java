package com.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Separate class for Amazon properties.
 *
 * Created by dkorolev on 10/2/2017.
 */
@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {

    private String associatedId;


    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId;
    }
    public String getAssociatedId() {
        return associatedId;
    }
}
