package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseByPurchaseStatusService {
    List<Purchase> listByPurchaseStatus(String purchaseStatus);
}
