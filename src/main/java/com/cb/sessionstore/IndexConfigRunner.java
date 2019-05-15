package com.cb.sessionstore;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.query.dsl.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.springframework.session.data.couchbase.config.annotation.web.http.CouchbaseSessionDefaults.DEFAULT_VALUE_TYPE;

@Component
public class IndexConfigRunner implements CommandLineRunner {
    @Autowired
    private Bucket bucket;

    @Override
    public void run(String... strings) throws Exception {
        bucket.bucketManager().createN1qlPrimaryIndex(true, true);
    }
}
