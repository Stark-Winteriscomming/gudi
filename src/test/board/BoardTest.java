package test.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:WEB-INF/spring/root-context.xml")
public class BoardTest {
	@Autowired
	BoardService bsv;
	
	@Test
	public void dummyInsert() throws Exception {
		for (int i = 0; i < 200; i++) {
			int d = (int)((Math.random()*10))%6;
			if(d != 0) {
				BoardVo vo = new BoardVo(); 
				vo.setBoardComment("abc");
				vo.setBoardTitle("tttt");
				String boardType = "a0" + d;
				vo.setBoardType(boardType);
				bsv.boardInsert(vo); 
				
			}
		}
	}
	
}
