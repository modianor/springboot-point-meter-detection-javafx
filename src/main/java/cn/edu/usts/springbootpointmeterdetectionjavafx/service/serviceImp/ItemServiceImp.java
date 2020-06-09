package cn.edu.usts.springbootpointmeterdetectionjavafx.service.serviceImp;

import cn.edu.usts.springbootpointmeterdetectionjavafx.dao.ItemDao;
import cn.edu.usts.springbootpointmeterdetectionjavafx.pojo.Item;
import cn.edu.usts.springbootpointmeterdetectionjavafx.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    private ItemDao itemDao;


    @Override
    public void save(Item item) {
        List<Item> items = findItem(item);
        if (items.size() == 0) {
            this.itemDao.save(item);
        }
    }

    @Override
    public List<Item> findItem(Item item) {
        Example<Item> example = Example.of(item);
        List<Item> items = this.itemDao.findAll(example);
        return items;
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return this.itemDao.findAll(pageable);
    }

}
