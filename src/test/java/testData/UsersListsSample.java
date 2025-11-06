package testData;

import ru.qa.day4.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static testData.UserSamples.*;

public class UsersListsSample {
    //test data -  users lists
    public static final List<User> USER_LIST = Collections.unmodifiableList(Arrays.asList(VALID_USER, USER_WITH_EMPTY_STRINGS, NULL_USER_SAMPLE, NULL_VALUE_IN_STRING, TEENAGE_USER));

    //test data - empty list
    public static final List<User> EMPTY_LIST = Collections.emptyList();

    //test data - null list
    public static final List<User> NULL_USER_LIST = null;
}
