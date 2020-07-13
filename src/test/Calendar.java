package test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.spring.board.service.BoardService;
import com.spring.board.service.impl.BoardServiceImpl;

public class Calendar {
	
	BoardService bs = new BoardServiceImpl();
	
	@Test
	public void test() throws InvalidFormatException, IOException {
		bs.calendarDownload();
	}
}
