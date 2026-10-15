package fr.portfolio.stock;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
/** Entité persistée représentant un produit du catalogue. */
@Entity public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false, unique=true) private String sku;
 @NotBlank @Column(nullable=false) private String name;
 @NotNull @Min(0) @Column(nullable=false) private Integer quantity;
 protected Product() {}
 public Product(String sku,String name,Integer quantity){this.sku=sku;this.name=name;this.quantity=quantity;}
 public Long getId(){return id;} public String getSku(){return sku;} public String getName(){return name;} public Integer getQuantity(){return quantity;}
 public void setSku(String v){sku=v;} public void setName(String v){name=v;} public void setQuantity(Integer v){quantity=v;}
}
