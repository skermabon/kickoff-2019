package org.talend.kickoff.qk.common;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ComicbookRepository {

    public String greeting(String name) {
        return "hello " + name + "\n";
    }
}
