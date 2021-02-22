package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
