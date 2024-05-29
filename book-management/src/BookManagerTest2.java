import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookManagerTest2 {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        // 테스트 시작 전에 BookManager 인스턴스 생성 및 초기화
        bookManager = new BookManager();
    }

    @Test
    void testAddBook() {
    	System.out.println("AddBook test start");
    	bookManager.addBook(1, "자바의 정석", "남궁성", 2021);
        bookManager.addBook(2, "효과적인 자바", "Joshua Bloch", 2018);
    	IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->bookManager.addBook(1, "자바의 정석", "남궁성", 2021), "해당 ID(1)는 이미 존재합니다.");
		bookManager.searchBook(1);
    	System.out.println("AddBook test end : " + e.getMessage());
    }

    @Test
	void testSearchBookID() {
    	System.out.println("SearchBookID test start");
    	bookManager.addBook(1, "자바의 정석", "남궁성", 2021);
        bookManager.addBook(2, "효과적인 자바", "Joshua Bloch", 2018);
        bookManager.addBook(3, "분산 컴퓨팅", "Yoon", 2024);
        System.out.println();
        for(int i =1; i<4; i++) {
        	bookManager.searchBook(i);
        }
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->bookManager.searchBook(4), "해당 ID(4)의 도서를 찾을 수 없습니다.\n검색된 도서가 없습니다.");
        System.out.println("SearchBookID test end : " + e.getMessage());
	}

	@Test
	void testSearchBookTitle() {
    	System.out.println("SearchBookTitle test start");
    	bookManager.addBook(1, "자바의 정석", "남궁성", 2021);
        bookManager.addBook(2, "효과적인 자바", "Joshua Bloch", 2018);
        bookManager.searchBook("자바의 정석");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->bookManager.searchBook("자바"), "검색된 도서가 없습니다.");	
        System.out.println("SearchBookTitle test end : " + e.getMessage());
	}

	@Test
	void testRemoveBookID() {
    	System.out.println("removeBookID test start");
    	bookManager.addBook(1, "자바의 정석", "남궁성", 2021);
        bookManager.addBook(2, "효과적인 자바", "Joshua Bloch", 2018);	
        bookManager.removeBook(1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->bookManager.removeBook(1), "해당 ID의 도서를 찾을 수 없어 삭제하지 못했습니다.");	
        System.out.println("SearchBookTitle test end : " + e.getMessage());
	}

	@Test
	void testRemoveBookTitle() {
    	System.out.println("removeBookTitle test start");
    	bookManager.addBook(1, "자바의 정석", "남궁성", 2021);
        bookManager.addBook(2, "효과적인 자바", "Joshua Bloch", 2018);	
        bookManager.removeBook("자바의 정석");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() ->bookManager.removeBook("자바의 정석"), "해당 제목의 도서를 찾을 수 없어 삭제하지 못했습니다.");	
        System.out.println("SearchBookTitle test end : " + e.getMessage());	}
}
