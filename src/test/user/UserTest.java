package test.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.service.BoardService;
import com.spring.user.service.CustomDeamon;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:WEB-INF/spring/root-context.xml")
public class UserTest {
	@Autowired
	BoardService bsv;

	@Autowired
	UserService usv;

	public void userInsert() {
		// TODO Auto-generated method stub

		String user_id = null;
		String user_pw = null;
		String user_name = null;
		String user_phone1 = "010";
		String user_phone2 = "1234";
		String user_company = "etrees";
		String user_addr2 = "gudi";
		String user_addr1 = "123-456";
		String user_phone3 = "5678";

		for (int i = 0; i < 20; i++) {
			String prefix = "abc_";
			user_id = prefix + i;
			user_pw = "00" + i;
			user_name = "lee_" + i;

			UserVo userVo = new UserVo(user_id, user_pw, user_name, user_phone1, user_phone2, user_phone3, user_addr1,
					user_addr2, user_company);
			int result = usv.registerUser(userVo);

		}
	}

	public void checkDupeId() {
		String id = "abc_8";
		System.out.println(usv.checkDuplicatedId(id));
	}
	
	/**
	 * 
	 */
}
