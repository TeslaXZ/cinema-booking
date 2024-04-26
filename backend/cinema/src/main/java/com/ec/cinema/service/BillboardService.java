package com.ec.cinema.service;

import com.ec.cinema.domain.entity.BillboardEntity;

public interface BillboardService extends CrudGeneratorService<BillboardEntity,Long>{
    void cancelBillboardAndBookings(Long billboardId);
    void cancelBillboardValidation(Long billboardId);
}
