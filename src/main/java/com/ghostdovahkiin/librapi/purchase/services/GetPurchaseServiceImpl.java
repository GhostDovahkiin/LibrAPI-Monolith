package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
    }
}
