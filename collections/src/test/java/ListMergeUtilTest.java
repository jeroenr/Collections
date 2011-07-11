import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class ListMergeUtilTest {
    @Test
    public void shouldMerge() throws Exception {
        List<String> list1 = Arrays.asList("A","B","D");
        List<String> list2 = Arrays.asList("B","D","E");

        final List<String> resultList = new ArrayList<String>();
        ListMergeUtil.merge(resultList, list1, list2, new ListMergeUtil.FoundMatchCallback<List<String>, String>() {
            @Override
            public List<String> handleFoundMatch(List<String> result, String current, String mutation) {
                result.add(current + mutation);
                return result;
            }
        });

        assertThat(resultList.size(), is(equalTo(4)));
        assertThat(resultList.contains("A"), is(true));
        assertThat(resultList.contains("BB"), is(true));
        assertThat(resultList.contains("DD"), is(true));
        assertThat(resultList.contains("E"), is(true));
    }
}
