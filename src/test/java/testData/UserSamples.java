package testData;

import ru.qa.day4.User;

public class UserSamples {

    //valid user with all correct data
    public static final User VALID_USER = new User(1, "Alice", "alice@test.com", 23);
    //empty strings
    public static final User USER_WITH_EMPTY_STRINGS = new User(2, "", "", 1);
    //nulls in strings fields
    public static final User NULL_VALUE_IN_STRING = new User(3, null, null, 18);
    //teenager for age limits test
    public static final User TEENAGE_USER = new User(4, "Bob", "bob@test.com", 17);

    //similar prefix name 1
    public static final User SIMILAR_PREFIX_IN_NAME = new User(5, "Donald", "don@ald.com", 56);

    //similar prefix name 2
    public static final User SIMILAR_PREFIX_IN_NAME_2 = new User(5, "Donaldinio", "", 6);

    //null link(not an object)
    public static final User NULL_USER_SAMPLE = null;


}
