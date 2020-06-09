package cn.edu.usts.springbootpointmeterdetectionjavafx.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GeneratorType;

import static org.bytedeco.javacpp.opencv_core.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@ToString
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imgId;
    private Double angle;
    private Double value;
    private Date date;
    @Transient
    public Mat src;

    @Tolerate
    public Item () {};
}
