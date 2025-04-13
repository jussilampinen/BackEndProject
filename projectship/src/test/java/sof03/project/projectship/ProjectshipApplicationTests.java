package sof03.project.projectship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import sof03.project.projectship.domain.Ship;
import sof03.project.projectship.domain.ShipRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectshipApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@Autowired
	private ShipRepository shipRepository;

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

	@Test
    void loginPageIsAvailable() throws Exception {
        mockMvc.perform(get("/login"))
               .andExpect(status().isOk())
               .andExpect(view().name("login")); // make sure the login view name is correct
    }

	@Test
    void accessShiplistWithoutLoginRedirects() throws Exception {
        mockMvc.perform(get("/shiplist"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrlPattern("**/login"));
    }

	@Test
    @WithMockUser(username = "user", roles = { "USER" })
    void accessShiplistWithUserRole() throws Exception {
        mockMvc.perform(get("/shiplist"))
               .andExpect(status().isOk());
    }

	@Test
    @WithMockUser(username = "admin", roles = { "USER" })
    void accessAdminPageWithWrongRoleShouldFail() throws Exception {
        mockMvc.perform(get("/admin"))
               .andExpect(status().isForbidden());
    }

	@Test
    public void whenFindByShipName_thenShipShouldBeFound() {
        
        Ship titanic = new Ship("Titanic", 52310, 269.1, 28.2, 1912, null, null, null, null, null);
        shipRepository.save(titanic);

        Ship found = shipRepository.findByShipName("Titanic").get(0);

        assertThat(found).isNotNull();
        assertThat(found.getShipName()).isEqualTo("Titanic");
    }


	@Test
    void titanicIsPresentWithCorrectFate() {
        Ship titanic = shipRepository.findByShipName("Titanic").get(0);
        assertThat(titanic.getFate().getDescription()).contains("Sank during maiden voyage");
    }
}
