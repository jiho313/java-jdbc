package sample03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;

public class ProductDao {
	
	// 상품 저장 기능
	public void insertProduct(Product product) throws SQLException {
		String sql ="insert into sample_product "
				+ "(product_no, product_name, product_maker, product_price, product_discount_rate, product_stock )"
				+ "values"
				+ "(sample_product"
				+ "_seq.nextval, ?, ?, ?, ?, ?)";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, product.getName());
		pstmt.setString(2, product.getMaker());
		pstmt.setInt(3, product.getPrice());
		pstmt.setDouble(4, product.getDiscountRate());
		pstmt.setInt(5, product.getStock());
		
		pstmt.executeUpdate();
		
		con.close();
		pstmt.close();

	}
	
	// 모든 상품 정보 반환기능
	public List<Product> getAllProduct() throws SQLException{
		String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock, product_create_date "
				+ "from sample_product "
				+ "order by product_no asc";
		List<Product> productList = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int no = rs.getInt("product_no");
			String name = rs.getString("product_name");
			String maker = rs.getString("product_maker");
			int price = rs.getInt("product_price");
			double discountRate = rs.getDouble("product_discount_rate");
			int stock = rs.getInt("product_stock");
			Date createDate = rs.getDate("product_create_date");
			
			Product product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			product.setCreateDate(createDate);
			
			productList.add(product);
		}
		con.close();
		pstmt.close();
		rs.close();
		
		return productList;
	} 
	
	// 상품번호 전달 해당 상품 정보 반환 기능
	public Product getProductByProductNo(int productNo) throws SQLException {
		String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock, product_create_date "
				+ "from sample_product "
				+ "where product_no = ?";
		
		Product product = null;
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int no = rs.getInt("product_no");
			String name = rs.getString("product_name");
			String maker = rs.getString("product_maker");
			int price = rs.getInt("product_price");
			double discountRate = rs.getDouble("product_discount_rate");
			int stock = rs.getInt("product_stock");
			Date createDate = rs.getDate("product_create_date");
			
			product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			product.setCreateDate(createDate);
		}
		
		con.close();
		pstmt.close();
		rs.close();
		
		return null;
		
	}
	// 최소가격 최대가격을 전달받아서 해당 가격범위 포함된 상품정보 반환기능
	// 상품번호를 전달받아서 상품정보를 삭제하는 기능

}
