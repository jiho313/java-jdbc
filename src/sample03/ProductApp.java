package sample03;

import java.sql.SQLException;
import java.util.List;

import util.KeyboardReader;

public class ProductApp {
	
	ProductDao dao = new ProductDao();
	KeyboardReader reader = new KeyboardReader();
	
	public void menu() {
		
		System.out.println("### 상품관리 프로그램 ###");
		System.out.println("----------------------------------------");
		System.out.println("1.전체조회 2.조회 3.상품등록 0.종료");
		System.out.println("----------------------------------------");
		System.out.println();
		
		System.out.print("### 메뉴선택: ");
		int menuNo = reader.readInt();
		System.out.println();
		
		try {
			if (menuNo == 1) {
				전체조회();
			} else if (menuNo == 2) {
				조회();
			} else if (menuNo == 3) {
				저장();
			} else if (menuNo == 0) {
				종료();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		menu();	// 재귀함수
		
	}
	
	public void 전체조회() throws SQLException {
		System.out.println("<< 상품 전체 조회 >>");
		System.out.println("상품 전체를 조회합니다.");
		List<Product> products = dao.getAllProduct();
		
		System.out.println("--------------------------------------------------------------");
		System.out.print("상품번호\t이름\t메이커\t가격\t할인율\t재고\t날짜");
		System.out.println();
		
		for(Product product : products) {
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountRate() + "\t");
			System.out.print(product.getStock() + "\t");
			System.out.println(product.getCreateDate());
		}
		System.out.println("--------------------------------------------------------------");
	}
	public void 조회() throws SQLException {
		System.out.println("<< 상품 조회 >>");
		System.out.println("### 조회할 상품 번호를 입력하세요.");
		
		System.out.print("상품번호: ");
		int no = reader.readInt();
		
		Product product = dao.getProductByProductNo(no);
		if(product == null) {
			System.out.println("### 등록된 상품정보가 존재하지 않습니다.");
		} else {
			System.out.println("--------------------------------------------------------------");
			System.out.println("["+product.getNo()+"]의 상품정보");
			System.out.println("--------------------------------------------------------------");
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountRate() + "\t");
			System.out.print(product.getStock() + "\t");
			System.out.println(product.getCreateDate());
			System.out.println("--------------------------------------------------------------");
			
		}
		
		
	}
	public void 저장() throws SQLException {
		System.out.println("<< 상품 등록>>");
		System.out.println("### 이름, 메이커, 가격, 할인율, 재고를 입력해서 상품을 등록하세요");
		
		System.out.print("이름 입력> ");
		String name = reader.readString();
		System.out.print("메이커 입력> ");
		String maker = reader.readString();
		System.out.print("가격 입력> ");
		int price = reader.readInt();
		System.out.print("할인율 입력> ");
		double discountRate = reader.readDouble();
		System.out.print("재고 입력> ");
		int stock = reader.readInt();
		
		Product product = new Product();
		product.setName(name);
		product.setMaker(maker);
		product.setPrice(price);
		product.setDiscountRate(discountRate);
		product.setStock(stock);
		
		dao.insertProduct(product);
		
		System.out.println("### 신규 상품 등록이 완료되었습니다.");
	}
	
	public void 종료() throws SQLException {
		
		System.exit(0);
	}
	
	public static void main(String[] args) {
		
		new ProductApp().menu();
		
	}
	

}
