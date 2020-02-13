package io.medalytics.projectactions.Config;

import org.hibernate.dialect.Dialect;
import org.springframework.context.annotation.Configuration;

import java.sql.Types;

@Deprecated
@Configuration
public class SQLiteDialect extends Dialect {
    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.VARCHAR, "string");
        // other data types
    }
}
