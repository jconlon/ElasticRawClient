package com.silverforge.elasticsearchrawclient.queryDSL.queries.innerQueries;

import com.silverforge.elasticsearchrawclient.BuildConfig;
import com.silverforge.elasticsearchrawclient.exceptions.MandatoryParametersAreMissingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class IdsQueryTest {

    // region Happy path

    @Test
    public void when_minimal_required_params_added_then_query_is_generated_well() throws MandatoryParametersAreMissingException {
        IdsQuery query = IdsQuery
            .builder()
            .values(1,2)
            .build();

        String queryString = query.getQueryString();

        assertThat(queryString, notNullValue());
        assertThat(queryString, not(""));

        assertThat(queryString.indexOf("\"values\":[1, 2]"), greaterThan(0));
    }

    @Test
    public void when_all_params_added_then_query_is_generated_well() throws MandatoryParametersAreMissingException {
        IdsQuery query = IdsQuery
            .builder()
            .type("value")
            .values(1, 2)
            .build();

        String queryString = query.getQueryString();

        assertThat(queryString, notNullValue());
        assertThat(queryString, not(""));

        assertThat(queryString.indexOf("\"type\":\"value\""), greaterThan(0));
        assertThat(queryString.indexOf("\"values\":[1, 2]"), greaterThan(0));
    }

    // endregion Happy path

    // region Sad path

    @Test
    public void when_no_params_added_then_exception_is_thrown() {
        boolean thrown = false;

        try {
            IdsQuery query = IdsQuery
                .builder()
                .build();
        } catch (MandatoryParametersAreMissingException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void when_no_values_added_then_exception_is_thrown() {
        boolean thrown = false;

        try {
            IdsQuery query = IdsQuery
                .builder()
                .type("value")
                .build();
        } catch (MandatoryParametersAreMissingException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    // endregion Sad path

}
