package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchase.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetPurchaseByStatusServiceImpl implements GetPurchaseByStatusService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> listByStatus(Status status) {
        return purchaseRepository.findPurchaseByStatus(status);
    }
}
