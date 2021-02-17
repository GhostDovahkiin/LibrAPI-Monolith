package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListPurchaseByPurchaseStatusServiceImpl implements ListPurchaseByPurchaseStatusService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> listByPurchaseStatus(String purchaseStatus) {
        return purchaseRepository.findPurchaseByPurchaseStatus(purchaseStatus);
    }
}
