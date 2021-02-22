package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.GetPurchaseServiceImpl;
import com.ghostdovahkiin.librapi.user.Sex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.purchase.builder.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for List One Purchase Service")
class GetPurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private GetPurchaseServiceImpl getPurchaseService;

    @BeforeEach
    void setUp() { this.getPurchaseService = new GetPurchaseServiceImpl(purchaseRepository); }

    @Test
    @DisplayName("Should return one purchase")
    void shouldFindOnePurchase(){
        Optional<Purchase> purchaseOptional = Optional.of(createPurchase().build());
        when(purchaseRepository.findById(anyLong())).thenReturn(purchaseOptional);
        Purchase purchase = getPurchaseService.findById(123L);

        assertAll("Purchase",
                () -> assertThat(purchase.getId(), is(123L)),
                () -> assertThat(purchase.getPurchasedBooks().size(), is(2)),
                () -> assertThat(purchase.getUser().getSex(), is(Sex.MALE)),
                () -> assertThat(purchase.getUser().getAge(), is(22)),
                () -> assertThat(purchase.getStatus(), is(Status.COMPLETED))
        );
        verify(purchaseRepository, times(1)).findById(123L);


    }
}
