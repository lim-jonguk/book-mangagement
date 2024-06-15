import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class BookManager {
    private static BookManager instance = null;
    private List<Book> books;

    private BookManager() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public static BookManager getInstance() {
        if (instance == null) {
            instance = new BookManager();
        }
        return instance;
    }

    public void addBook(int id, String title, String author, int year) {
        for (Book book : books) {
            if (book.id == id) {
                throw new IllegalArgumentException("해당 ID(" + id + ")는 이미 존재합니다.");
            }
        }
        Book book = new Book(id, title, author, year);
        this.books.add(book);
        this.books.sort(Comparator.comparingInt(b -> b.id));  // 항상 정렬 상태 유지
        System.out.println(book + " 도서가 추가되었습니다.");
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

    public Book search_bs(int id) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);

            if (midBook.id == id) {
                return midBook;
            } else if (midBook.id < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        throw new IllegalArgumentException("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.");
    }

}

class Book {
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
