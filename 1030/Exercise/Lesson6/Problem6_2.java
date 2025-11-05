package Lesson6;

class Book {
    private String author;
    private String title;
    private String buyer;

    public Book(String author, String title, String buyer) {
        this.author = author;
        this.title = title;
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return buyer + "이 구입한 도서: " + author + "의 " + title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book b = (Book) obj;
            // 저자와 도서명이 같으면 같은 책으로 판단
            return this.author.equals(b.author) && this.title.equals(b.title);
        } else {
            return false;
        }
    }
}

public class Problem6_2 {
    public static void main(String[] args) {
        Book a = new Book("황기태", "명품자바", "김하진");
        Book b = new Book("황기태", "명품자바", "허예린");
        System.out.println(a);
        System.out.println(b);

        if (a.equals(b))
            System.out.println("같은 책");
        else
            System.out.println("다른 책");
    }
}
