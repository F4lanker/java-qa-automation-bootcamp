package testData;

import ru.qa.day4.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserSamples {

    //valid user with all correct data
    public static final User VALID_USER = new User(1, "Alice", "alice@test.com", 23)
    //empty strings
    public static final User USER_WITH_EMPTY_STRINGS = new User(2, "", "", 1);
    //nulls in strings fields
    public static final User NULL_VALUE_IN_STRING = new User (3, null, null, 18);
    //teenager for age limits test
    public static final User TEENAGE_USER = new User(4, "Bob", "bob@test.com", 17);
    //null link(not an object)
    public static final User NULL_USER_SAMPLE = null;

    public static final List<User> USER_LIST = Collections.unmodifiableList(Arrays.asList(VALID_USER,USER_WITH_EMPTY_STRINGS, NULL_USER_SAMPLE, NULL_VALUE_IN_STRING, TEENAGE_USER);



