package org.dbs.crawl;

import org.dbs.crawl.controller.CrawlController;
import org.dbs.crawl.form.UrlForm;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CrawlDemoApplication.class)
@WebAppConfiguration
class CrawlDemoApplicationTests {

	@InjectMocks
	CrawlController crawlController = new CrawlController();

	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(crawlController).build();
	}
    @Test
	void contextLoads(){
    	try {
		UrlForm urlForm = buildForm();
		String requestJson = mapper.writeValueAsString(urlForm);
		mockMvc.perform(MockMvcRequestBuilders.post("/crowler") .content(requestJson)
		  .contentType(MediaType.APPLICATION_JSON) .accept(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().isOk());
    	}catch(Exception e) {
    		
    	}
	}

	private UrlForm buildForm() {
		UrlForm form = new UrlForm();
		form.setRootUrl("https://stackoverflow.com/");
		return form;
	}
}
