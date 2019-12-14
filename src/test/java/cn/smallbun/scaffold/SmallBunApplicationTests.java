package cn.smallbun.scaffold;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles(value = "dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmallBunApplicationTests {

	@Test
	public void contextLoads() {

	}

}

