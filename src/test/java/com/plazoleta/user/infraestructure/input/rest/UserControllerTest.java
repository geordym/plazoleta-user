package com.plazoleta.user.infraestructure.input.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.plazoleta.user.application.dto.request.CreateOwnerRequestDto;
import com.plazoleta.user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.plazoleta.user.domain.util.Constants.MINIUM_YEARS_AGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUserPersistencePort userPersistencePort;

    CreateOwnerRequestDto createUserRequestDtoValid;

    @BeforeEach
    void setup(){

        Faker faker = new Faker();

        createUserRequestDtoValid = new CreateOwnerRequestDto();

        createUserRequestDtoValid.setName(faker.name().firstName());
        createUserRequestDtoValid.setLastName(faker.name().lastName());
        createUserRequestDtoValid.setEmail(faker.internet().emailAddress());
        createUserRequestDtoValid.setPassword(faker.internet().password());
        createUserRequestDtoValid.setPhoneNumber("+" + faker.numerify("############"));
        createUserRequestDtoValid.setDateOfBirth(String.valueOf(LocalDate.now().minusYears(MINIUM_YEARS_AGE)));
        createUserRequestDtoValid.setIdentityDocument(Long.valueOf(faker.numerify("##########")));
    }


    @Test
    public void When_UserOwnerInformationIsCorrect_Expect_StatusCreated() throws Exception {
        String userJson = objectMapper.writeValueAsString(createUserRequestDtoValid);

        mockMvc.perform(post("/api/users/owner")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated());
    }


}
