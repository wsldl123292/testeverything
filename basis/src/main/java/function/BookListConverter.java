package function;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import common.Book;

import java.util.List;
import java.util.Map;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/8 15:21
 */
public class BookListConverter implements Function<List<Book>, Map<String, String>> {
    @Override
    public Map<String, String> apply(List<Book> input) {
        Map<String,String> map = Maps.newHashMap();
        Joiner joiner = Joiner.on("|");
        for (Book book : input) {
            map.put(book.getIsbn(),joiner.join(book.getTitle(),book.getPublisher(),book.getPrice()));
        }
        return map;
    }
}
