package io.vertx.up.plugin.mongo;

interface Info {

    String MERGE_INFO = "[ ZERO ] Merged {0} and {1}, sourceKey = {2}, targetKey = {3}";

    String FILTER_INFO = "[ ZERO ] Mongo collection = {0}, filter: {1}";
}