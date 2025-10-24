package testData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSamplesForTests {
    //Empty list
    public static final List <String> emptyList = new ArrayList<>();
    //List consists NULL
    public static final List<String> nullContainList = Arrays.asList((String) null);
    //List consist NULL and another date
    public static final List<String> nullAndOtherDataList = Arrays.asList((String) null, "Alice");
    // NULL-link (no list)
    public static final List<String> nullList = null;
    //One element list
    public static final List<String> oneElementList = new ArrayList<String>(List.of("alfa"));
    //Ten elements list
    public static final List<String> tenElements = new ArrayList<String>(List.of("Sample", "String", "Alice", "a@test.com", " ", "Bob", "b@test.com", "refix", "Index", "Paris" ));
    // Repeat element list
    public static final List<String> repeatElements = new ArrayList<String>(List.of("Sample", "Sample", "Sample" ));
}
