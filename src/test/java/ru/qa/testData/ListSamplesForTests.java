package testData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListSamplesForTests {
    //Empty list
    public static final List <String> EMPTIED_LIST = Collections.emptyList();
    //List consists NULL
    public static final List<String> NULL_CONTAIN_LIST =Collections.unmodifiableList(Arrays.asList((String) null));
    //List consist NULL and another date
    public static final List<String> NULL_AND_OTHER_DATA_LIST = Collections.unmodifiableList(Arrays.asList((String) null, "Alice"));
    // NULL-link (no list)
    public static final List<String> NULL_LIST = null;
    //One element list
    public static final List<String> ONE_ELEMENT_LIST = new ArrayList<String>(List.of("alfa"));
    //Ten elements list
    public static final List<String> TEN_ELEMENTS = new ArrayList<String>(List.of("Sample", "String", "Alice", "a@test.com", " ", "Bob", "b@test.com", "refix", "Index", "Paris" ));
    // Repeat element list
    public static final List<String> REPEAT_ELEMENTS = new ArrayList<String>(List.of("Sample", "Sample", "Sample" ));
}
