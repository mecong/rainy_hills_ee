package rain.service;

import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.Spliterator;

public class StringListParserService {

    private static final String SEPARATOR = ",";

    public int[] parse(String list) throws NumberFormatException {
        return Strings.isNullOrEmpty(list) ?
               null :
               Arrays.stream(list.split(SEPARATOR)).mapToInt(i -> Integer.valueOf(i.trim())).toArray();
    }
}
