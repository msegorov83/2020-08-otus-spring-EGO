package ru.otus.spring.chandgelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog(order = "001")
public class InitMongoDbDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

}
