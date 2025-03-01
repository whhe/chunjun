/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.chunjun.connector.greenplum.dialect;

import com.dtstack.chunjun.connector.greenplum.converter.GreenplumRawTypeConverter;
import com.dtstack.chunjun.connector.postgresql.dialect.PostgresqlDialect;
import com.dtstack.chunjun.converter.RawTypeConverter;

import java.util.Optional;

/**
 * company www.dtstack.com
 *
 * @author jier
 */
public class GreenplumDialect extends PostgresqlDialect {

    private static final String DIALECT_NAME = "Greenplum";
    private static final String DRIVER = "com.pivotal.jdbc.GreenplumDriver";
    public static final String URL_START = "jdbc:pivotal:greenplum:";
    public static final String DATABASE_NAME = ";DatabaseName=";

    @Override
    public String dialectName() {
        return DIALECT_NAME;
    }

    @Override
    public boolean canHandle(String url) {
        return url.startsWith(URL_START);
    }

    @Override
    public RawTypeConverter getRawTypeConverter() {
        return GreenplumRawTypeConverter::apply;
    }

    @Override
    public Optional<String> defaultDriverName() {
        return Optional.of(DRIVER);
    }

    @Override
    public Optional<String> getUpsertStatement(
            String schema,
            String tableName,
            String[] fieldNames,
            String[] uniqueKeyFields,
            boolean allReplace) {
        throw new RuntimeException("Greenplum does not support upsert sql");
    }

    @Override
    public Optional<String> getReplaceStatement(
            String schema, String tableName, String[] fieldNames) {
        throw new RuntimeException("Greenplum does not support replace sql");
    }
}
