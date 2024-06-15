import java.util.List;
import java.util.ArrayList;

class Book{
   int id;
   String title;
   String author;
   int year;
   
   @Override
    public String toString() {
        return "Book{id: '" + id + "', 제목: '" + title + "', 저자: '" + author + "', 출판년도: " + year + "}";
    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }
}

public class BookManager {
   List<Book> books ;
   
   public BookManager() {
      this.books = new ArrayList<>();
   }
   public void addBook(int id, String title, String author, int year) {
      for (Book book : books) {
            if (book.id == id) {
                throw new IllegalArgumentException("해당 ID(" + id + ")는 이미 존재합니다.");
            }
        }
      Book book =new Book(id, title, author,year);
      this.books.add(book);
      System.out.println(book+"도서가 추가되었습니다.");
      
    }
   
   public void searchBook(int id) {
      System.out.println("검색결과:");
      boolean found = false;
      for (Book book : books) {
            if (book.id == id) {
               System.out.println(book);
               found = true;
            }
        }
      if (!found) {
         throw new IllegalArgumentException("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.\n검색된 도서가 없습니다.");
      }
   }
   
   public void searchBook(String title) {
        System.out.println("검색결과:");
        boolean found = false;
        for (Book book : books) {
            if (book.title.equals(title)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
           throw new IllegalArgumentException("검색된 도서가 없습니다.");
        }
    }
    
    public void removeBook(int id) {
        boolean result = books.removeIf(book -> book.id == id);
        if (result) {
            System.out.println("ID(" + id + ")의 도서가 삭제되었습니다.");
        } else {
           throw new IllegalArgumentException("해당 ID의 도서를 찾을 수 없어 삭제하지 못했습니다.");
        }
    }
    
    public void removeBook(String title) {
        boolean result = books.removeIf(book -> book.title.equals(title));
        if (result) {
            System.out.println("제목(" + title + ")의 도서가 삭제되었습니다.");
        } else {
           throw new IllegalArgumentException("해당 제목의 도서를 찾을 수 없어 삭제하지 못했습니다.");
        }
    }
}