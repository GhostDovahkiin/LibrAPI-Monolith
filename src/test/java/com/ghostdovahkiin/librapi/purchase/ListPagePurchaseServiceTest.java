package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.ListPagePurchaseServiceImpl;
import com.ghostdovahkiin.librapi.user.Sex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static com.ghostdovahkiin.librapi.purchase.builder.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Execution tests to List Purchase with pagination")
class ListPagePurchaseServiceTest {
    @Mock
    private ListPagePurchaseServiceImpl listPagePurchaseService;
    @Mock
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    void SetUp(){ this.listPagePurchaseService = new ListPagePurchaseServiceImpl(purchaseRepository);}

    @Test
    @DisplayName("Should return all purchases with pagination")
    void shouldReturnUsersAsAPage() {
        when(listPagePurchaseService.listPurchases(PageRequest.of(0, 2)))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createPurchase().build())));

        Page<Purchase> purchasePage = listPagePurchaseService.listPurchases(PageRequest.of(0,2));

        assertAll("Purchase",
                () -> assertThat(purchasePage.getNumber(), is(0)),
                () -> assertThat(purchasePage.getSize(), is(2)),
                () -> assertThat(purchasePage.getContent().get(0).getUser().getSex(), is(Sex.MALE)),
                () -> assertThat(purchasePage.getContent().get(0).getAmountToPay(), is(229.90)),
                () -> assertThat(purchasePage.getContent().get(0).getPurchasedBooks().size(), is(2)),
                () -> assertThat(purchasePage.getContent().get(0).getStatus(), is(Status.COMPLETED))
        );
    }
}
