package cn.edu.usts.springbootpointmeterdetectionjavafx.service;

import cn.edu.usts.springbootpointmeterdetectionjavafx.pojo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    public void save(Item item);

    public List<Item> findItem(Item item);

    public Page<Item> findAll(Pageable pageable);
}
