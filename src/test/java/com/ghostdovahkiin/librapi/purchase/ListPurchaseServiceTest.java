package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.ListPurchaseServiceImpl;
import com.ghostdovahkiin.librapi.user.Sex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ghostdovahkiin.librapi.purchase.builder.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List All Purchases Service")
class ListPurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private ListPurchaseServiceImpl listPurchaseService;

    @BeforeEach
    void SetUp() { this.listPurchaseService = new ListPurchaseServiceImpl(purchaseRepository); }

    @Test
    @DisplayName("Should return all users")
    void shouldFindAllPurchases() {
        when(purchaseRepository.findAll()).thenReturn(
                Stream.of(createPurchase().id(123L).status(Status.PENDING).build(),
                        createPurchase().id(456L).status(Status.COMPLETED).build(),
                        createPurchase().id(789L).status(Status.CLOSED).build()).collect(Collectors.toList())
        );

        List<Purchase> purchaseList = listPurchaseService.findAll();

        assertAll("Purchases",
                () -> assertThat(purchaseList.size(), is(3)),
                () -> assertThat(purchaseList.get(0).getUser().getAge(), is(22)),
                () -> assertThat(purchaseList.get(0).getId(), is(123L)),
                () -> assertThat(purchaseList.get(0).getUser().getSex(), is(Sex.MALE)),
                () -> assertThat(purchaseList.get(0).getStatus(), is(Status.PENDING)),
                () -> assertThat(purchaseList.get(0).getAmountToPay(), is(229.90)),
                () -> assertThat(purchaseList.get(0).getPurchasedBooks().size(), is(2)),
                () -> assertThat(purchaseList.get(1).getUser().getAge(), is(22)),
                () -> assertThat(purchaseList.get(1).getId(), is(456L)),
                () -> assertThat(purchaseList.get(1).getUser().getSex(), is(Sex.MALE)),
                () -> assertThat(purchaseList.get(1).getStatus(), is(Status.COMPLETED)),
                () -> assertThat(purchaseList.get(1).getAmountToPay(), is(229.90)),
                () -> assertThat(purchaseList.get(1).getPurchasedBooks().size(), is(2)),
                () -> assertThat(purchaseList.get(1).getUser().getAge(), is(22)),
                () -> assertThat(purchaseList.get(2).getId(), is(789L)),
                () -> assertThat(purchaseList.get(2).getUser().getSex(), is(Sex.MALE)),
                () -> assertThat(purchaseList.get(2).getStatus(), is(Status.CLOSED)),
                () -> assertThat(purchaseList.get(2).getAmountToPay(), is(229.90)),
                () -> assertThat(purchaseList.get(2).getPurchasedBooks().size(), is(2))
        );
        verify(purchaseRepository, times(1)).findAll();
    }
}
