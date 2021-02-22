package com.ghostdovahkiin.librapi.purchase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostdovahkiin.librapi.purchase.services.DeletePurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.GetPurchaseByStatusService;
import com.ghostdovahkiin.librapi.purchase.services.GetPurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.ListPagePurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.ListPurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.SavePurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.UpdatePurchaseService;
import com.ghostdovahkiin.librapi.purchase.v1.PurchaseController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static com.ghostdovahkiin.librapi.purchase.builder.PurchaseBuilder.createPurchase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PurchaseController.class)
@DisplayName("Tests all implemented tests from services if working on controller")
class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeletePurchaseService deletePurchaseService;
    @MockBean
    private GetPurchaseByStatusService getPurchaseByStatusService;
    @MockBean
    private GetPurchaseService getPurchaseService;
    @MockBean
    private ListPagePurchaseService listPagePurchaseService;
    @MockBean
    private ListPurchaseService listPurchaseService;
    @MockBean
    private SavePurchaseService savePurchaseService;
    @MockBean
    private UpdatePurchaseService updatePurchaseService;

    private final String URL = "/v1/api/purchases";

    @Test
    @DisplayName("Should find and return all Purchases")
    void shouldFindAllPurchases() throws Exception{
        when(listPurchaseService.findAll()).thenReturn(
                Lists.newArrayList(
                        createPurchase().id(123L).status(Status.PENDING).build(),
                        createPurchase().id(456L).status(Status.CLOSED).build(),
                        createPurchase().id(789L).status(Status.FINISHED).build()
                ));

        mockMvc.perform(get(URL + "/all").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(123)))
                .andExpect(jsonPath("$[0].status", is("PENDING")))
                .andExpect(jsonPath("$[1].id", is(456)))
                .andExpect(jsonPath("$[1].status", is("CLOSED")))
                .andExpect(jsonPath("$[2].id", is(789)))
                .andExpect(jsonPath("$[2].status", is("FINISHED"))
        );

        verify(listPurchaseService).findAll();

    }

    @Test
    @DisplayName("Should find and return one Purchase")
    void shouldFindPurchaseById() throws Exception{
        when(getPurchaseService.findById(123L))
                .thenReturn(createPurchase().id(123L).status(Status.PENDING).build());

        mockMvc.perform(get(URL + "/{id}", 123L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.status", is("PENDING"))
        );

        verify(getPurchaseService, times(1)).findById(anyLong());

    }

    @Test
    @DisplayName("Should save an Purchase")
    void shouldSavePurchase() throws Exception {
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(readJson("PurchaseDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated()
                );

        verify(savePurchaseService, times(1)).save(any(Purchase.class));
    }

    @Test
    @DisplayName("Should update an Purchase")
    void shouldUpdatePurchase() throws Exception {
        mockMvc.perform(put(URL + "/{id}", 123L)
                .content(readJson("PurchaseDTOUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updatePurchaseService).update(any(PurchaseDTO.class), eq(123L));
    }

    @Test
    @DisplayName("Should delete an Purchase")
    void shouldRemovePurchase() throws Exception {
        mockMvc.perform(delete(URL + "/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deletePurchaseService).delete(anyLong());
    }

    @Test
    @DisplayName("Should return Purchases with pagination")
    void shouldListUsersWithPagination() throws Exception {
        Page<Purchase> purchasePage = new PageImpl<>(Collections.singletonList(createPurchase().id(123L).build()));
        when(listPagePurchaseService.listPurchases(PageRequest.of(0,2))).thenReturn(purchasePage);

        mockMvc.perform(get(URL + "/?page=0&size=2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(123)))
                .andExpect(jsonPath("$.content[0].status", is("COMPLETED")))
                .andExpect(jsonPath("$.content[0].amountToPay", is(229.90))
        );
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }

}
