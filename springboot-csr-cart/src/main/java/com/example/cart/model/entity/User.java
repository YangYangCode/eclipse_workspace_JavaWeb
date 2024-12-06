package com.example.cart.model.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "`user`")		// 使用 `` 密免予保留字衝突
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)	// 不允許重複，可空值不允許
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	// user 與 order 的關係是一對多關聯
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	// 建立用戶可以關注商品的多對多關係
	@ManyToMany
	@JoinTable(
			name = "user_product", 	// 關聯表名稱
			joinColumns = @JoinColumn(name = "user_id"), 	// 用戶外鍵
			inverseJoinColumns = @JoinColumn(name = "product")	// 商品外鍵
			)
	private Set<Product> favoriteProducts;

	
	/*
    * hashCode 方法中出現了遞迴循環，通常是因為 User 和 Product 實體之間的雙向關聯造成的，
    * Hibernate 無法處理這種循環依賴。所以要自行實現
    * */
   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       User user = (User) o;
       return Objects.equals(id, user.id);
   }
   @Override
   public int hashCode() {
       return Objects.hash(id);
   }
	
	
}
