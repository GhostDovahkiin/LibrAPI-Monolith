package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;

@FunctionalInterface
public interface GetPurchaseService {
    Purchase findById(Long id);
}
