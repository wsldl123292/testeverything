package collections;

import com.google.common.base.Function;
import com.google.common.collect.*;
import common.Book;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/8 17:01
 */
public class MapsTest {

    private  Book book = new Book.Builder().isbn("ISBN123").title("book1").build();
    private  Book book2 = new Book.Builder().isbn("ISBN456").title("book2").build();
    private  Book book3 = new Book.Builder().isbn("ISBN789").title("book3").build();
    private List<Book> books = Lists.newArrayList(book, book2, book3);

    @Test
    public void uniqueIndexTest(){
        Map<String,Book> bookMap = Maps.uniqueIndex(books, new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.getIsbn();
            }
        });
        assertThat(bookMap.get("ISBN123"),is(book));
        assertThat(bookMap.get("ISBN456"),is(book2));
        assertThat(bookMap.get("ISBN789"),is(book3));

        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(Lists.newArrayList("dd","fff","eeee"),
        new Function<String, Integer> () {
            public Integer apply(String string) {
                return string.length();
            }
        });
        System.out.println(stringsByIndex);
    }

    @Test
    public void asMapTest(){
        Set<Book> bookSet = Sets.newHashSet(books);
        Map<Book,String> bookToIsbn = Maps.asMap(bookSet,new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.getIsbn();
            }
        });
        System.out.println(bookToIsbn);
        assertThat(bookToIsbn.get(book),is("ISBN123"));
        assertThat(bookToIsbn.get(book2),is("ISBN456"));
        assertThat(bookToIsbn.get(book3),is("ISBN789"));
    }
    @Test
    public void transformValuesTest(){
        Map<String,Book> bookMap = Maps.uniqueIndex(books,new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.getIsbn();
            }
        });

        Map<String,String> map = Maps.transformValues(bookMap,new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.getTitle();
            }
        });

        assertThat(map.get("ISBN123"),is("book1"));
        assertThat(map.get("ISBN456"),is("book2"));
        assertThat(map.get("ISBN789"),is("book3"));
    }

    @Test
    public void transformEntriesTest(){
        Map<String,Book> bookMap = Maps.uniqueIndex(books,new Function<Book, String>() {
            @Override
            public String apply(Book book) {
                return book.getIsbn();
            }
        });

        Map<String,String> map = Maps.transformEntries(bookMap,new Maps.EntryTransformer<String,Book,String>(){
            @Override
            public String transformEntry(String key, Book value) {
                StringBuilder builder = new StringBuilder();
                return builder.append(key).append("|").append(value.getTitle()).toString();
            }
        });
        System.out.println(map);
        assertThat(map.get("ISBN123"),is("ISBN123|book1"));
        assertThat(map.get("ISBN456"),is("ISBN456|book2"));
        assertThat(map.get("ISBN789"),is("ISBN789|book3"));
    }

    @Test
    public void testDifference(){
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("e", 1, "b", 2, "c", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());

    }

}
