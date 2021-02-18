package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.GetPurchaseByStatusServiceImpl;
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
@DisplayName("Tests execution for ListPurchaseByStatus")
class GetPurchaseByStatusServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private GetPurchaseByStatusServiceImpl getPurchaseByStatusService;

    @BeforeEach
    void SetUp() { this.getPurchaseByStatusService = new GetPurchaseByStatusServiceImpl(purchaseRepository); }

    @Test
    @DisplayName("Should return all purchases with Completed Status")
    void shouldFindAllCompletedPurchases() {
        when(purchaseRepository.findPurchaseByStatus(Status.COMPLETED)).thenReturn(Stream.of(createPurchase().build(),
                createPurchase().build(),
                createPurchase().build()).collect(Collectors.toList())
        );
        List<Purchase> purchasesFound = getPurchaseByStatusService.listByStatus(Status.COMPLETED);
        assertAll("Purchases",
                () -> assertThat(purchasesFound.size(), is(3)),
                () -> assertThat(purchasesFound.get(0).getStatus(), is(Status.COMPLETED)),
                () -> assertThat(purchasesFound.get(1).getStatus(), is(Status.COMPLETED)),
                () -> assertThat(purchasesFound.get(2).getStatus(), is(Status.COMPLETED))
        );
        verify(purchaseRepository, times(1)).findPurchaseByStatus(Status.COMPLETED);
    }
}
