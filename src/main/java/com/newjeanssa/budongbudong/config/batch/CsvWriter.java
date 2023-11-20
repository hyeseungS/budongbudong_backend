package com.newjeanssa.budongbudong.config.batch;

import com.newjeanssa.budongbudong.model.dao.StoreInfoDao;
import com.newjeanssa.budongbudong.model.dto.store.StoreInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CsvWriter implements ItemWriter<StoreInfoDto> {

    private final StoreInfoDao storeInfoDao;

    @Override
    public void write(List<? extends StoreInfoDto> list) throws Exception {
        storeInfoDao.insertAll(new ArrayList<>(list));
    }
}
