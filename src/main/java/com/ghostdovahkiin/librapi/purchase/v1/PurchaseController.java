package com.ghostdovahkiin.librapi.purchase.v1;

import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.PurchaseDTO;
import com.ghostdovahkiin.librapi.purchase.Status;
import com.ghostdovahkiin.librapi.purchase.services.DeletePurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.GetPurchaseByStatusService;
import com.ghostdovahkiin.librapi.purchase.services.GetPurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.ListPagePurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.ListPurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.SavePurchaseService;
import com.ghostdovahkiin.librapi.purchase.services.UpdatePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/api/purchases")
public class PurchaseController {
    private final ListPurchaseService listPurchaseService;
    private final ListPagePurchaseService listPagePurchaseService;
    private final GetPurchaseService getPurchaseService;
    private final GetPurchaseByStatusService getPurchaseByStatusService;
    private final SavePurchaseService savePurchaseService;
    private final UpdatePurchaseService updatePurchaseService;
    private final DeletePurchaseService deletePurchaseService;

    @GetMapping(path = "/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDTO> findByStatus(@PathVariable Status status) { return PurchaseDTO.fromAll(getPurchaseByStatusService.listByStatus(status)); }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseDTO findById(@PathVariable Long id) { return PurchaseDTO.from(getPurchaseService.findById(id)); }

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public Page<PurchaseDTO> listPurchases(Pageable pageable) {
        return PurchaseDTO.fromPage(listPagePurchaseService.listPurchases(pageable));
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseDTO> findAll() { return PurchaseDTO.fromAll(listPurchaseService.findAll());}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PurchaseDTO purchaseDTO) { savePurchaseService.save(Purchase.to(purchaseDTO));}

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody PurchaseDTO purchaseDTO, @PathVariable Long id) {
        updatePurchaseService.update(purchaseDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { deletePurchaseService.delete(id); }


}
