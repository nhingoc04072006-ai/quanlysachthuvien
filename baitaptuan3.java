import java.util.Scanner;

public class baitaptuan3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bài 1: Nhập thông tin sách
        System.out.println("BÀI 1: NHẬP THÔNG TIN SÁCH");
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

        //Bổ sung thêm biến nhập loại sách
        System.out.print("Nhập loại sách (1: SGK, 2: Tham khảo, 3: Chuyên ngành, 4: Khác): ");
        int loaiSach = scanner.nextInt();

        //Bài 2: Tính thành tiền = đơn giá × số lượng
        double thanhTien = donGia * soLuong;

        //Bài 3: Phí vận chuyển = 5% giá trị sách (thành tiền)
        double phiVanChuyen = thanhTien * 0.05;

        //Bài 4: Tổng tiền nhập = Thành tiền + Phí vận chuyển
        double tongTienNhap = thanhTien + phiVanChuyen;

        // Hiển thị toàn bộ kết quả
        System.out.println("KẾT QUẢ QUẢN LÝ SÁCH");
        System.out.println("Mã sách         : " + maSach);
        System.out.println("Tên sách        : " + tenSach);
        System.out.println("Tác giả         : " + tacGia);
        System.out.println("Năm xuất bản    : " + namXuatBan);
        System.out.println("Đơn giá         : " + donGia);
        System.out.println("Số lượng        : " + soLuong);
        System.out.println("Thành tiền      : " + thanhTien);
        System.out.println("Phí vận chuyển  : " + phiVanChuyen);
        System.out.println("Tổng tiền nhập  : " + tongTienNhap);

        //Bổ sung bài tập tuần 4 
        
        //Bài 1: Kiểm tra hợp lệ
        System.out.print("Kiểm tra dữ liệu (T4-B1) : ");
        if (donGia > 0 && soLuong >= 0 && namXuatBan > 1900 && namXuatBan <= 2026) {
            System.out.println("Hợp lệ");
        } else {
            System.out.println("Không hợp lệ!");
        }

        //Bài 2: Phân loại giá
        System.out.print("Phân loại giá (T4-B2)    : ");
        if (donGia < 50000) {
            System.out.println("Sách giá thấp");
        } else if (donGia <= 100000) {
            System.out.println("Sách giá trung bình");
        } else if (donGia <= 200000) {
            System.out.println("Sách giá cao");
        } else {
            System.out.println("Sách giá đặc biệt");
        }

        //Bài 3: Phân loại tồn kho
        System.out.print("Phân loại tồn kho (T4-B3): ");
        if (soLuong == 0) {
            System.out.println("Hết sách");
        } else if (soLuong <= 5) {
            System.out.println("Sắp hết");
        } else if (soLuong <= 20) {
            System.out.println("Còn ít");
        } else {
            System.out.println("Còn nhiều");
        }

        //Bài 4: Chọn loại sách bằng switch
        System.out.print("Loại sách        : ");
        switch (loaiSach) {
            case 1:
                System.out.println("Sách giáo khoa");
                break;
            case 2:
                System.out.println("Sách tham khảo");
                break;
            case 3:
                System.out.println("Sách chuyên ngành");
                break;
            case 4:
                System.out.println("Sách khác");
                break;
            default:
                System.out.println("Không xác định");
                break;
        }
        scanner.close();
    }
}