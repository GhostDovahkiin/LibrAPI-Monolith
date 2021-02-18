package com.ghostdovahkiin.librapi.purchase;

import com.ghostdovahkiin.librapi.purchase.services.ListPagePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Execution tests to List Purchase with pagination")
class ListPagePurchaseServiceTest {
    @Mock
    private ListPagePurchaseServiceImpl listPagePurchaseService;
    @Mock
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    void SetUp(){ this.listPagePurchaseService = new ListPagePurchaseServiceImpl(purchaseRepository);}
}
