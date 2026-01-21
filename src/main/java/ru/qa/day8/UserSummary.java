package ru.qa.day8;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class UserSummary {
    private final long count;
    private final int minAge;
    private final int maxAge;
    private final double averageAge;

    public UserSummary(long count, int minAge, int maxAge, double averageAge) {
        if (count<0){
            throw new IllegalArgumentException("Count must be >=0");
        }
        if (minAge<0 || maxAge<0){
            throw new IllegalArgumentException("Age must be >=0");
        }
        if (minAge > maxAge){
            throw new IllegalArgumentException("minAge can't be greater than maxAge");
        }
        if (averageAge<0 || averageAge>100){
            throw new IllegalArgumentException("Average age should be realistic");
        }

        this.count = count;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.averageAge = averageAge;
    }
    
}

