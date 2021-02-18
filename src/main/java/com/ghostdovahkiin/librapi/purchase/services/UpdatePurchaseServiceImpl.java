package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.PurchaseDTO;
import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public void update(PurchaseDTO purchase, Long id) {
        Purchase purchaseFound = purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);

        purchaseFound.setUser(purchase.getUser());
        purchaseFound.setPurchasedBooks(purchase.getPurchasedBooks());
        purchaseFound.setAmountToPay(purchase.getAmountToPay());
        purchaseFound.setStatus(purchase.getStatus());

        purchaseRepository.save(purchaseFound);
    }
}
