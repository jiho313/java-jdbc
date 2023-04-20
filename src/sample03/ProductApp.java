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
		System.out.println("1.전체조회 2.상세조회 3.가격대조회 4.신규등록 5.재고변경 6.상품삭제 0.종료");
		System.out.println("----------------------------------------");
		System.out.println();
		
		System.out.print("### 메뉴선택: ");
		int menuNo = reader.readInt();
		System.out.println();
		
		try {
			if (menuNo == 1) {
				전체조회();
				System.out.println("1.할인율 높은순으로 재정렬 2.할인된 가격순으로 재정렬하기 0.메인메뉴");
				System.out.print("번호 입력: ");
				int menuNo2 = reader.readInt();
				if (menuNo2 == 1) {
					할인율높은순재정렬();
				} else if (menuNo2 == 2) {
					할인가격순재정렬();
				} else if (menuNo2 == 0) {
					System.out.println("### 메인 메뉴로 돌아갑니다.");
					System.out.println();
					menu();
				}
			} else if (menuNo == 2) {
				상세조회();
			} else if (menuNo == 3) {
				가격대조회();
			} else if (menuNo == 4) {
				신규등록();
			} else if(menuNo == 5) {
				상품정보변경();
			} else if(menuNo == 6) {
				상품삭제();
			} else if(menuNo ==0) {
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
	
	private void 전체조회() throws SQLException {
		System.out.println("<< 상품 전체 조회 >>");
		System.out.println("### 상품 전체를 조회합니다.");
		List<Product> products = dao.getAllProduct();
		
		System.out.println("--------------------------------------------------------------");
		System.out.print("상품번호\t이름\t메이커\t가격\t할인율\t재고");
		System.out.println();
		
		if(products.isEmpty()) {
			System.out.println("### 등록된 상품정보가 존재하지 않습니다.");
		}
		for(Product product : products) {
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountPerCent() + "\t");
			System.out.println(product.getStock());
		}
		System.out.println("--------------------------------------------------------------");
	}

	private void 할인율높은순재정렬() throws SQLException{
		System.out.println("### 할인율이 높은 순으로 재정렵합니다.");
		List<Product> products = dao.getDiscountPerCent();
		
		System.out.println("--------------------------------------------------------------");
		System.out.print("상품번호\t이름\t메이커\t할인전가격\t할인율\t할인된가격\t재고");
		System.out.println();
		
		if(products.isEmpty()) {
			System.out.println("### 등록된 상품정보가 존재하지 않습니다.");
		}
	
		for (Product product : products) {
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountPerCent() + "\t");
			System.out.print(product.getDiscountPrice() + "\t");
			System.out.println(product.getStock());
		}
		System.out.println("--------------------------------------------------------------");
	}
	
	private void 할인가격순재정렬() throws SQLException {
		System.out.println("### 할인된 가격순으로 재정렬합니다.");
		List<Product> products = dao.getDiscountPrice();
		
		System.out.println("--------------------------------------------------------------");
		System.out.print("상품번호\t이름\t메이커\t할인전가격\t할인율\t할인된가격\t재고");
		System.out.println();
		
		if(products.isEmpty()) {
			System.out.println("### 등록된 상품정보가 존재하지 않습니다.");
		}
		for(Product product : products) {
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountPerCent() + "\t");
			System.out.print(product.getDiscountPrice() + "\t");
			System.out.println(product.getStock());
		}
		System.out.println("--------------------------------------------------------------");
	}
	
	private void 상세조회() throws SQLException {
		System.out.println("<< 상품 상세 조회 >>");
		System.out.println("### 조회할 상품 번호를 입력하세요.");
		
		System.out.print("상품번호: ");
		int no = reader.readInt();
		
		Product product = dao.getProductByProductNo(no);
		if(product == null) {
			System.out.println("### 등록된 상품정보가 존재하지 않습니다.");
		} else {
			System.out.println("--------------------------------------------------------------");
			System.out.println("["+product.getNo()+"]의 상품정보");
			System.out.println("상품번호\t이름\t메이커\t가격\t할인율\t할인된가격\t재고\t날짜");
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getName() + "\t");
			System.out.print(product.getMaker() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getDiscountPerCent() + "\t");
			System.out.print(product.getDiscountPrice() + "\t");
			System.out.print(product.getStock() + "\t");
			System.out.println(product.getCreateDate());
			System.out.println("--------------------------------------------------------------");
			
		}
		
		
	}
	
	private void 가격대조회() throws SQLException{
		System.out.println("<< 가격대별 상품 조회 >>");
		System.out.println("### 조회할 최소, 최대 가격을 입력하세요.");
		
		System.out.print("최소 가격 입력: ");
		int minPrice = reader.readInt();
		System.out.print("최대 가격 입력: ");
		int maxPrice = reader.readInt();
		
		List<Product> products = dao.getProductByMinMaxPrice(minPrice, maxPrice);
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("["+minPrice+"] ~ ["+maxPrice+"]까지의 상품정보를 조회합니다.");
		System.out.println("--------------------------------------------------------------");
		System.out.print("상품번호\t이름\t메이커\t가격\t할인율\t재고\t날짜");
		System.out.println();
		
		if (products.isEmpty()) {
			System.out.println();
			System.out.println("---------------- 해당 가격대의 상품을 찾을 수 없습니다. ----------------");
			}
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
	
	
	private void 신규등록() throws SQLException {
		System.out.println("<< 신규 상품 등록 >>");
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
	
	private void 상품정보변경() throws SQLException {
		System.out.println("<< 상품 정보 변경 >>");
		System.out.println("### 정보를 변경할 상품번호를 입력하세요.");
		Product product = new Product();
		System.out.print("정보를 변경할 상품 번호: ");
		int no = reader.readInt();
		System.out.println("변경할 가격: ");
		int price = reader.readInt();
		System.out.println("변경할 할인율: ");
		double discountRate = reader.readDouble();
		System.out.println("변경할 재고의 수량: ");
		int stock = reader.readInt();
		
		product.setNo(no);
		product.setPrice(price);
		product.setDiscountRate(discountRate);
		product.setStock(stock);
		dao.updateProduct(product);
		System.out.println("["+no+"]번 상품의 가격이 ["+product.getPrice()+"]원으로 변경되었습니다.");
		System.out.println("["+no+"]번 상품의 할인율이 ["+product.getDiscountPerCent()+"]로 변경되었습니다.");
		System.out.println("["+no+"]번 상품의 재고가 ["+product.getStock()+"]개로 변경되었습니다.");
	}
	
	private void 상품삭제() throws SQLException{
		System.out.println("<< 상품 삭제 >>");
		System.out.println("### 삭제할 상품 번호를 입력하세요.");
		
		System.out.print("상품 번호: ");
		int no = reader.readInt();
		dao.deleteProdutByNo(no);
		
		System.out.println("### 상품 정보가 삭제가 완료되었습니다.");
	}
	
	private void 종료() throws SQLException {
		System.out.println("<< 종료하기 >>");
		System.out.println("### 프로그램을 종료합니다.");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		
		new ProductApp().menu();
		
	}
	

}
