package org.talend.dataprep.mn.common;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("datapreparation")
public class DataPreparationConfiguration {

    private String databaseName = "kickoff-2019";

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
