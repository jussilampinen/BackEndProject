package sof03.project.projectship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectshipApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // Basic context load test
    }

    @Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
    void shipListPageLoadsSuccessfully() throws Exception {
        mockMvc.perform(get("/shiplist"))
                .andExpect(status().isOk())
                .andExpect(view().name("shiplist"))
                .andExpect(model().attributeExists("ships"));
    }
}
