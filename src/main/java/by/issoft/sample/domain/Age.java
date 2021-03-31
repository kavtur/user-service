package by.issoft.sample.domain;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * represents human age. Must be in range [0, 125]
 */
public class Age {

    public static Map<Byte, Age> agesPool = new HashMap<>();
    private final byte val;

    private Age(byte val) {
        checkArgument(val >= 0,  "by.issoft.sample.domain.Age must be positive");
        checkArgument(val <= 125,  "by.issoft.sample.domain.Age must be <= 125");
        this.val = val;
    }

    public int intValue() {
        return val;
    }

    public static Age of(int age) {
        byte byteAge = (byte) age;
        final Age fromPool = agesPool.get(byteAge);
        if (fromPool != null) {
            return fromPool;
        }
        final Age newAge = new Age(byteAge);
        agesPool.put(byteAge, newAge);

        return newAge;
    }


}
