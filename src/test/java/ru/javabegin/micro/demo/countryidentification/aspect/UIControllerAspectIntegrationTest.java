package ru.javabegin.micro.demo.countryidentification.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.micro.demo.countryidentification.configuration.TestConfig;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
class UIControllerAspectIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ISDCodeService isdCodeService;

    @Test
    void testAspectAfterControllerMethod() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        verify(isdCodeService).createPrefixTrie();
    }
}