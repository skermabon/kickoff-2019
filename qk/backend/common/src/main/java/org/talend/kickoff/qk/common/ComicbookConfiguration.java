package org.talend.kickoff.qk.common;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;

@Singleton
public class ComicbookConfiguration {

    @ConfigProperty(name = "database.name")
    private String databaseName = "kickoff-2019";

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

}
