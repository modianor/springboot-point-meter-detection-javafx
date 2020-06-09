package cn.edu.usts.springbootpointmeterdetectionjavafx.dao;

import cn.edu.usts.springbootpointmeterdetectionjavafx.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, Integer> {

}
