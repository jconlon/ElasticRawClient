package com.silverforge.elasticsearchrawclient.queryDSL.queries.innerQueries;

import com.silverforge.elasticsearchrawclient.queryDSL.generator.QueryFactory;
import com.silverforge.elasticsearchrawclient.queryDSL.operators.ZeroToOneRangeOperator;
import com.silverforge.elasticsearchrawclient.queryDSL.Constants;
import com.silverforge.elasticsearchrawclient.model.QueryTypeItem;
import com.silverforge.elasticsearchrawclient.definition.Queryable;
import com.silverforge.elasticsearchrawclient.queryDSL.queries.innerQueries.common.BoostQuery;
import com.silverforge.elasticsearchrawclient.utils.QueryTypeArrayList;

public class DisMaxQuery
        extends BoostQuery {

    private QueryTypeArrayList<QueryTypeItem> queryBag;

    protected DisMaxQuery(QueryTypeArrayList<QueryTypeItem> queryBag) {
        this.queryBag = queryBag;
    }

    public static Init<?> builder() {
        return new DisMaxQueryBuilder();
    }

    @Override
    public String getQueryString() {
        return QueryFactory
            .disMaxQueryGenerator()
            .generate(queryBag);
    }

    public static class DisMaxQueryBuilder
            extends Init<DisMaxQueryBuilder> {

        @Override
        protected DisMaxQueryBuilder self() {
            return this;
        }
    }

    public static abstract class Init<T extends Init<T>>
            extends BoostQuery.BoostInit<T> {

        public T tieBreaker(ZeroToOneRangeOperator tieBreakerOperator) {
            String value = tieBreakerOperator.toString();
            queryBag.addItem(Constants.TIE_BREAKER, value);
            return self();
        }

        public T queries(Queryable... queries) {
            queryBag.addItem(Constants.QUERIES, queries);
            return self();
        }

        public DisMaxQuery build() {
            return new DisMaxQuery(queryBag);
        }
    }
}
