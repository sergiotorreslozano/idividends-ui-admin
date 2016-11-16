package com.idividends.ui.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ org.springframework.test.context.web.ServletTestExecutionListener.class,
		org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener.class,
		org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class,
		org.springframework.test.context.support.DirtiesContextTestExecutionListener.class,
		org.springframework.test.context.transaction.TransactionalTestExecutionListener.class,
		org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener.class })
public class UiAdminApplicationTests {

	@Test
	public void contextLoads() {
	}

}
