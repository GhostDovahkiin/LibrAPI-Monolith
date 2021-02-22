package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public void delete(Long id) {
        if (!purchaseRepository.existsById(id)){
            throw new PurchaseNotFoundException();
        }
        purchaseRepository.deleteById(id);
    }
}
