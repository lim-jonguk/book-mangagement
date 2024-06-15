import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookManagerTest2 {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        // 테스트 시작 전에 BookManager 인스턴스 생성 및 초기화
        bookManager =  BookManager.getInstance();
    	bookManager.getBooks().clear();
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
    public void compareBsWithN() {
        // 10개 이상의 엔트리 추가
        for (int i = 1; i <= 10000; i++) {
        	bookManager.addBook(i, "Book " + i, "Author " + i, 2000 + i);
        }
        
        long n_delta = 0;
        long bs_delta = 0;
        
        long startTime ,endTime,duration;
	    for(int i=0 ;i<10; i++) {
	    	startTime= System.nanoTime(); // 시작 시간 측정
	        bookManager.searchBook( (int)((Math.random()*10000)) ); // 테스트할 메소드 실행
	        endTime = System.nanoTime(); // 종료 시간 측정
	        
	        duration = endTime - startTime; // 실행 시간 계산
	        n_delta += duration;
	    }
	    n_delta /= 10;
	    for(int i=0 ;i<10; i++) {
	    	startTime= System.nanoTime(); // 시작 시간 측정
	        bookManager.search_bs( (int)((Math.random()*10000)) ); // 테스트할 메소드 실행
	        endTime = System.nanoTime(); // 종료 시간 측정
	        
	        duration = endTime - startTime; // 실행 시간 계산
	        bs_delta += duration;
	    }
	    bs_delta /= 10;

        System.out.println("search_bs() Execution Time: " + bs_delta + " nanoseconds");
        System.out.println("search() Execution Time: " + n_delta + " nanoseconds");
        
        System.out.println("In average , search_bs is faster than search_normal by " + Math.abs(n_delta - bs_delta) + " nanoseconds");
        System.out.println("In average , search_bs is faster than search_normal by " + (n_delta / bs_delta)*100 + "%");
        
    }
    
    
    
    @Test
    public void testBinarySearch() {
        // 10개 이상의 엔트리 추가
        for (int i = 1; i <= 15; i++) {
        	bookManager.addBook(i, "Book " + i, "Author " + i, 2000 + i);
        }

        // 최적의 경우: 첫 번째 책을 찾을 때
        assertEquals("Book 1", bookManager.search_bs(1).title);

        // 최악의 경우: 마지막 책을 찾을 때
        assertEquals("Book 15", bookManager.search_bs(15).title);

        // 중간의 책을 찾을 때
        assertEquals("Book 8", bookManager.search_bs(8).title);

        // 못 찾을 경우: 존재하지 않는 ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	bookManager.search_bs(20);
        });
        assertTrue(exception.getMessage().contains("해당 ID(20)의 도서를 찾을 수 없습니다."));
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
