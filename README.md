# Couchbase Java Session Store
Example of how to build a session store with Couchbase using spring-session-couchbase

# How to run this Project

The *spring-session-data-couchbase* dependency is not released yet, so you will need to checkout this project https://github.com/couchbaselabs/spring-session-data-couchbase and build it locally first.

Then, you will need to configure the database connection in the *application.properties* file:

---
spring.couchbase.bootstrap-hosts=localhost
spring.couchbase.bucket.name=sessionstore
spring.couchbase.bucket.password=password
---

