package com.pdvsystem.api.domain.moviment;

import com.pdvsystem.api.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "stock_moviments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockMoviment {

    @Id
    @GeneratedValue
    private UUID id;
    private String type;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
