module com.speedment.runtime.typemapper {
    exports com.speedment.runtime.typemapper.string;
    exports com.speedment.runtime.typemapper.integer;
    exports com.speedment.runtime.typemapper.internal;
    exports com.speedment.runtime.typemapper;
    requires java.sql.rowset;
    requires java.sql;
    requires com.speedment.runtime.config;
}
