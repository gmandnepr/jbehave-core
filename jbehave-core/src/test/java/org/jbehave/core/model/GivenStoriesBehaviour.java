package org.jbehave.core.model;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Valery_Yatsynovich
 */
public class GivenStoriesBehaviour {

    private static final String GIVEN_STORY_PATH1 = "/path/to/given1.story";
    private static final String GIVEN_STORY_PATH2 = "/path/to/given2.story";

    @Test
    public void shouldParseSimpleGivenStories() {
        GivenStories givenStories = new GivenStories(GIVEN_STORY_PATH1 + "," + GIVEN_STORY_PATH2);

        assertThat(givenStories.getPaths(), equalTo(Arrays.asList(GIVEN_STORY_PATH1, GIVEN_STORY_PATH2)));
    }

    @Test
    public void shouldParseGivenStoriesWithTrailingComma() {
        GivenStories givenStories = new GivenStories(GIVEN_STORY_PATH1 + ",");

        assertThat(givenStories.getPaths(), equalTo(Arrays.asList(GIVEN_STORY_PATH1)));
    }

    @Test
    public void shouldParseGivenStoriesWithTrailingCommaAndSpaces() {
        GivenStories givenStories = new GivenStories(GIVEN_STORY_PATH1 + ",  ");

        assertThat(givenStories.getPaths(), equalTo(Arrays.asList(GIVEN_STORY_PATH1)));
    }

    @Test
    public void shouldParseGivenStoriesWithEmptyStoryPathInTheMiddle() {
        GivenStories givenStories = new GivenStories(GIVEN_STORY_PATH1 + ",  ," + GIVEN_STORY_PATH2);

        assertThat(givenStories.getPaths(), equalTo(Arrays.asList(GIVEN_STORY_PATH1, GIVEN_STORY_PATH2)));
    }

    @Test
    public void shouldParseGivenStoriesWithLeadingComma() {
        GivenStories givenStories = new GivenStories("," + GIVEN_STORY_PATH1);

        assertThat(givenStories.getPaths(), equalTo(Arrays.asList(GIVEN_STORY_PATH1)));
    }

    @Test
    public void shouldParseGivenStoriesWithLeadingCommaAndSpaces() {
        GivenStories givenStories = new GivenStories("  ," + GIVEN_STORY_PATH1);

        assertThat(givenStories.getPaths(), equalTo(Arrays.asList(GIVEN_STORY_PATH1)));
    }

}
