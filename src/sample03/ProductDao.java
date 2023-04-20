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
	
	// 상품 등록 기능
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
		
		pstmt.close();
		con.close();
	}
	
	// 모든 상품 정보 반환기능
	public List<Product> getAllProduct() throws SQLException{
		String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock "
				+ "from sample_product "
				+ "order by product_no desc";
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
			
			Product product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			
			productList.add(product);
		}
		pstmt.close();
		rs.close();
		con.close();
		
		return productList;
	} 
	// 상품을 할인율 높은순으로
	public List<Product> getDiscountPerCent() throws SQLException {
		String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock "
				+ "from sample_product "
				+ "order by product_discount_rate desc";
		List<Product> produtList = new ArrayList<>();
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
			
			Product product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			
			produtList.add(product);
			
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return produtList;
	}
	// 상품을 할인된 가격순으로 
	public List<Product> getDiscountPrice () throws SQLException{
		String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock, "
				+ "product_price * (1 - product_discount_rate) as discounted_price "
				+ "from sample_product "
				+ "ORDER BY discounted_price ASC";
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
			
			Product product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			
			productList.add(product);
		}
		rs.close();
		pstmt.close();
		con.close();
		
		return productList;
	} 
	// 상품번호를 전달받아 해당 상품 정보 반환 기능
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
		
		rs.close();
		pstmt.close();
		con.close();
		
		return product;
		
	}
	// 최소가격, 최대가격을 전달받아서 해당 가격범위에 포함된 상품정보 반환기능
	public List<Product> getProductByMinMaxPrice (int minPrice, int maxPrice) throws SQLException {
		String sql = "select product_no, product_name, product_maker, product_price, product_discount_rate, product_stock, product_create_date "
				+ "from sample_product "
				+ "where product_price between ? and ? "
				+ "order by product_price asc";
		List<Product> produts = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, minPrice);
		pstmt.setInt(2, maxPrice);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Product product = new Product();
			int no = rs.getInt("product_no");
			String name = rs.getString("product_name");
			String maker = rs.getString("product_maker");
			int price = rs.getInt("product_price");
			double discountRate = rs.getDouble("product_discount_rate");
			int stock = rs.getInt("product_stock");
			Date createDate = rs.getDate("product_create_date");
			
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			product.setCreateDate(createDate);
			
			produts.add(product);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return produts;
	}
	// 상품번호를 전달받아서 상품 정보를 변경하는 기능
	public void updateProduct (Product product) throws SQLException {
		String sql = "update sample_product "
				+ "set product_price =?,  product_discount_rate =?, product_stock = ? "
				+ "where product_no = ?";
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, product.getPrice());
		pstmt.setDouble(2, product.getDiscountRate());
		pstmt.setInt(3, product.getStock());
		pstmt.setInt(4, product.getNo());
		pstmt.executeUpdate();	
		
	}
//	public void updateProduct (int no, int price, double discountRate, int stock) throws SQLException {
//		String sql = "update sample_product "
//				+ "set product_price =?,  product_discount_rate =?, product_stock = ? "
//				+ "where product_no = ?";
//		Connection con = ConnUtils.getConnection();
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setInt(1, price);
//		pstmt.setDouble(2, discountRate);
//		pstmt.setInt(3, stock);
//		pstmt.setInt(4, no);
//		pstmt.executeUpdate();	
//	}
	
	// 상품번호를 전달받아서 상품정보를 삭제하는 기능
	public void deleteProdutByNo(int no) throws SQLException {
		String sql = "delete from sample_product "
				+ "where product_no = ?";
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		
	}

}
