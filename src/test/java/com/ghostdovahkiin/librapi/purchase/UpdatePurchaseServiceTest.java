package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.UpdatePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.purchase.builder.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Update Purchase Service")
class UpdatePurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private UpdatePurchaseServiceImpl updatePurchaseService;

    @BeforeEach
    void setUp() { this.updatePurchaseService = new UpdatePurchaseServiceImpl(purchaseRepository); }

    @Test
    @DisplayName("Should update a Purchase")
    void shouldUpdatePurchase() {

        Purchase purchaseToUpdate = createPurchase().status(Status.FINISHED).build();

        Optional<Purchase> purchaseOptional  = Optional.of(createPurchase().build());
        when(purchaseRepository.findById(anyLong())).thenReturn(purchaseOptional);

        updatePurchaseService.update(PurchaseDTO.from(purchaseToUpdate), 123L);

        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepository).save(purchaseArgumentCaptor.capture());
        Purchase result = purchaseArgumentCaptor.getValue();

        assertAll("Purchase",
                () -> assertThat(result.getStatus(), is(Status.FINISHED))
        );
    }
}
