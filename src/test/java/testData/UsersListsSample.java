package testData;

import ru.qa.day4.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static testData.UserSamples.*;

public class UsersListsSample {
    //test data -  users lists
    public static final List<User> USER_LIST = Collections.unmodifiableList(Arrays.asList(VALID_USER, USER_WITH_EMPTY_STRINGS, NULL_USER_SAMPLE, NULL_VALUE_IN_STRING, TEENAGE_USER, SIMILAR_PREFIX_IN_NAME, SIMILAR_PREFIX_IN_NAME_2));

    //test data - empty list
    public static final List<User> EMPTY_LIST = Collections.emptyList();

    //test data - null list
    public static final List<User> NULL_USER_LIST = null;

    //list with user under 18
    public static final List<User> TEENAGE_USER_LIST = Collections.unmodifiableList((Arrays.asList(TEENAGE_USER, USER_WITH_EMPTY_STRINGS)));

    public static final List<User> TEENAGE_USER_SAME_AGE = Collections.unmodifiableList((Arrays.asList(TEENAGE_USER, TEENAGE_USER_TWO)));

    //list with the only one user exactly 18 years old
    public static final List<User> ONE_USER_18YO = Collections.unmodifiableList(Arrays.asList(USER_WITH_EMPTY_STRINGS,TEENAGE_USER, NULL_VALUE_IN_STRING));
}
