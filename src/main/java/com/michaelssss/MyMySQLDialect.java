package com.michaelssss;

import org.hibernate.dialect.MySQL57InnoDBDialect;

import java.sql.Types;

public class MyMySQLDialect extends MySQL57InnoDBDialect {
    protected void registerVarcharTypes() {
        registerColumnType(Types.VARCHAR, "longtext");
//		registerColumnType( Types.VARCHAR, 16777215, "mediumtext" );
//		registerColumnType( Types.VARCHAR, 65535, "text" );
        registerColumnType(Types.VARCHAR, 255, "varchar($l)");
        registerColumnType(Types.LONGVARCHAR, "longtext");
    }

}
