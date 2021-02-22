package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchase.services.DeletePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests execution for Delete Purchase Service")
class DeletePurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private DeletePurchaseServiceImpl deletePurchaseService;

    @BeforeEach
    void SetUp() { this.deletePurchaseService = new DeletePurchaseServiceImpl(purchaseRepository); }

    @Test
    @DisplayName("Should delete a Purchase")
    void shouldDeletePurchase() {
        when(purchaseRepository.existsById(anyLong())).thenReturn(true);
        deletePurchaseService.delete(123L);
        verify(purchaseRepository).existsById(anyLong());
    }

    @Test
    @DisplayName("Should return a PurchaseNotFoundException if not encountered a Purchase with specified ID")
    void shouldThrowPurchaseNotFoundException() {
        when(purchaseRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(PurchaseNotFoundException.class, () -> deletePurchaseService.delete(1L));
        verify(purchaseRepository, times(0)).deleteById(anyLong());
    }
}
