package com.silverforge.elasticsearchrawclient.queryDSL.queries;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchQuery extends QueryBase {
    private final String fieldName;
    private final String value;

    MatchQuery(MatchQueryBuilder MatchQueryBuilder) {
        this.fieldName = MatchQueryBuilder.fieldName;
        this.value = MatchQueryBuilder.valueString;
    }

    public static MatchQueryBuilder builder() {
         return new MatchQueryBuilder();
    }

    public String getQueryString() {
        String queryString = "{\"match\" : {\"{{FIELDNAME}}\" : \"{{VALUE}}\"}}";
        if (TextUtils.isEmpty(fieldName))
            queryString = queryString.replace("{{FIELDNAME}}", "_all");
        else
            queryString = queryString.replace("{{FIELDNAME}}", fieldName);

        queryString = queryString.replace("{{VALUE}}", this.value);

        queryString = queryEnvelope.replace("{{BUILTQUERYSTRING}}", queryString);

        return queryString;
    }

    public static class MatchQueryBuilder {
        private String fieldName;
        private String valueString;

        MatchQueryBuilder() {}

        public MatchQueryBuilder fieldName(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }

        public MatchQueryBuilder value(String value) {
            this.valueString = value;
            return this;
        }

        // region integer numbers

        public MatchQueryBuilder value(Byte value) {
            this.valueString = value.toString();
            return this;
        }

        public MatchQueryBuilder value(Short value) {
            this.valueString = value.toString();
            return this;
        }

        public MatchQueryBuilder value(Integer value) {
            this.valueString = value.toString();
            return this;
        }

        public MatchQueryBuilder value(Long value) {
            this.valueString = value.toString();
            return this;
        }

        // endregion

        // region float numbers

        public MatchQueryBuilder value(Float value) {
            this.valueString = value.toString();
            return this;
        }

        public MatchQueryBuilder value(Double value) {
            this.valueString = value.toString();
            return this;
        }

        // endregion

        public MatchQueryBuilder value(Date value, String format) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            this.valueString = formatter.format(value);
            return this;
        }

        public MatchQueryBuilder value(Boolean value) {
            if (value)
                this.valueString = "true";
            else
                this.valueString = "false";

            return this;
        }

        public MatchQuery build() {
            return new MatchQuery(this);
        }
    }
}
