package hu.innocenter.bigdata.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Ákos Kiszely on 2015.11.16..
 * akos.kiszely@gmail.com
 */

@Entity
@Table(name = "laboratoriumi_meresi_eredmenyek")
public class MeasurementResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


}
