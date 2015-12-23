package com.kpi.ip41m.pceshop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PcEshopApplication.class)
@WebAppConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class})
public abstract class PcEshopApplicationTests {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void before() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	protected MockMvc getMockMvc() {
		return this.mockMvc;
	}

	/******************************** http requests ********************************/
	protected ResultActions get(String collectionResourceUrl) throws Exception {
		return getMockMvc().perform(MockMvcRequestBuilders.get(collectionResourceUrl));
	}

	protected <T> ResultActions get(String collectionResourceUrl, T itemResourceId) throws Exception {
		return get(collectionResourceUrl + "/" + itemResourceId);
	}

	protected ResultActions post(String collectionresourceUrl, Object object) throws Exception {
		return getMockMvc().perform(MockMvcRequestBuilders.post(collectionresourceUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(object)));
	}

	/******************************** helpers ********************************/
	protected void printResponse(ResultActions resultActions) throws UnsupportedEncodingException {
		System.out.println(resultActions.andReturn().getResponse().getContentAsString());
	}

	protected static byte[] asJsonString(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}
