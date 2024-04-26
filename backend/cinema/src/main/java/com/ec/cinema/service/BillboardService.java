package com.ec.cinema.service;

import com.ec.cinema.domain.dto.billboard.BillboardDTO;

public interface BillboardService extends CrudGeneratorService<BillboardDTO,Long>{
    void cancelBillboardAndBookings(Long billboardId);
    void cancelBillboardValidation(Long billboardId);
}
