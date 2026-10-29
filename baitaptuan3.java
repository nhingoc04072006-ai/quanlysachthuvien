import java.util.Scanner;

public class baitaptuan3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bài 1: Nhập thông tin sách
        System.out.println("=== BÀI 1: NHẬP THÔNG TIN SÁCH ===");
        System.out.print("Nhập mã sách: ");
        String maSach = scanner.nextLine();

        System.out.print("Nhập tên sách: ");
        String tenSach = scanner.nextLine();

        System.out.print("Nhập tác giả: ");
        String tacGia = scanner.nextLine();

        System.out.print("Nhập năm xuất bản: ");
        int namXuatBan = scanner.nextInt();

        System.out.print("Nhập đơn giá: ");
        double donGia = scanner.nextDouble();

        System.out.print("Nhập số lượng: ");
        int soLuong = scanner.nextInt();

        // Bài 2: Tính thành tiền = đơn giá × số lượng
        double thanhTien = donGia * soLuong;

        // Bài 3: Phí vận chuyển = 5% giá trị sách (thành tiền)
        double phiVanChuyen = thanhTien * 0.05;

        // Bài 4: Tổng tiền nhập = Thành tiền + Phí vận chuyển
        double tongTienNhap = thanhTien + phiVanChuyen;

        // Hiển thị toàn bộ kết quả
        System.out.println("\n========================================");
        System.out.println("     KẾT QUẢ BÀI THỰC HÀNH TUẦN 3         ");
        System.out.println("========================================");
        System.out.println("Mã sách         : " + maSach);
        System.out.println("Tên sách        : " + tenSach);
        System.out.println("Tác giả         : " + tacGia);
        System.out.println("Năm xuất bản    : " + namXuatBan);
        System.out.println("Đơn giá         : " + donGia);
        System.out.println("Số lượng        : " + soLuong);
        System.out.println("----------------------------------------");
        System.out.println("Bài 2 - Thành tiền     : " + thanhTien);
        System.out.println("Bài 3 - Phí vận chuyển : " + phiVanChuyen);
        System.out.println("Bài 4 - Tổng tiền nhập : " + tongTienNhap);
        System.out.println("========================================");

        scanner.close();
    }
}