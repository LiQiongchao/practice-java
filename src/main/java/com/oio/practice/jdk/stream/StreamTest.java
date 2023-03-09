package com.oio.practice.jdk.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.awt.print.Book;
import java.util.*;

/**
 * @Author: Liqc
 * @Date: 2022/2/8 14:51
 */
public class StreamTest {

    @Test
    public void flatMapTest() {
        List<List<Integer>> list = new ArrayList();
        list.add(Arrays.asList(1, 2, 3, 4));
        list.add(Arrays.asList(4,5,6,7));
        list.stream().flatMap(Collection::stream).forEach(System.out::println);


    }

    @Test
    public void flatMapTest2() {
        List<Book> books = Arrays.asList(new Book(10, "AAA"), new Book(20, "BBB"));
        Writer w1 = new Writer("Mohan", books);

        books = Arrays.asList(new Book(30, "XXX"), new Book(15, "ZZZ"));
        Writer w2 = new Writer("Sohan", books);

        List<Writer> writers = Arrays.asList(w1, w2);

        Book book = writers.stream().flatMap(writer -> writer.getBooks().stream())
                .max(new BookComparator()).get();
        System.out.println("Name:"+book.getName()+", Price:"+ book.getPrice() );
    }


    @Data
    @AllArgsConstructor
    class Writer {

        private String name;

        private List<Book> books;

    }


    @Data
    @AllArgsConstructor
    class Book {

        private Integer price;

        private String name;

    }

    class BookComparator implements Comparator<Book> {
        @Override
        public int compare(Book b1, Book b2) {
            if (b1.getPrice() > b2.getPrice()) {
                return 1;
            } else if (Objects.equals(b1.getPrice(), b2.getPrice())) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
