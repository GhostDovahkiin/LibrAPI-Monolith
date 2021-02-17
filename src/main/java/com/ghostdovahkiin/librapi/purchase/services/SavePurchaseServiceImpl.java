package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchase.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService{
    private final PurchaseRepository pucharseRepository;

    @Override
    public void save(Purchase purchase) {
        pucharseRepository.save(purchase);
    }
}
