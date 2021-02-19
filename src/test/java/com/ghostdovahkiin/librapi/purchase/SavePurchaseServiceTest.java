package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.SavePurchaseServiceImpl;
import com.ghostdovahkiin.librapi.user.Sex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ghostdovahkiin.librapi.purchase.builder.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Save Purchase Service")
class SavePurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private SavePurchaseServiceImpl savePurchaseService;

    @BeforeEach
    void SetUp() { this.savePurchaseService = new SavePurchaseServiceImpl(purchaseRepository); }

    @Test
    @DisplayName("Should save a Purchase")
    void shouldSavePurchase() {
        savePurchaseService.save(createPurchase().build());

        ArgumentCaptor<Purchase> captorPurchase = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepository, times(1)).save(captorPurchase.capture());

        Purchase resultPurchase = captorPurchase.getValue();

        assertAll("Purchase",
                () -> assertThat(resultPurchase.getId(), is(123L)),
                () -> assertThat(resultPurchase.getUser().getAge(), is(22)),
                () -> assertThat(resultPurchase.getUser().getSex(), is(Sex.MALE)),
                () -> assertThat(resultPurchase.getPurchasedBooks().size(), is(2)),
                () -> assertThat(resultPurchase.getStatus(), is(Status.COMPLETED)),
                () -> assertThat(resultPurchase.getAmountToPay(), is(229.90))
        );
    }
}
