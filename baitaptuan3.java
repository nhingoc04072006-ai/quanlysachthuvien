import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Lớp Sach: gom các thông tin và phép tính của tuần 3, 4 vào một chỗ
class Sach {
    String maSach;
    String tenSach;
    String tacGia;
    int namXuatBan;
    double donGia;
    int soLuong;
    int loaiSach;

    // Tuần 3: Bài 2, 3, 4
    double getThanhTien() {
        return donGia * soLuong;
    }

    double getPhiVanChuyen() {
        return getThanhTien() * 0.05;
    }

    double getTongTienNhap() {
        return getThanhTien() + getPhiVanChuyen();
    }

    // Tuần 4: Bài 2 - Phân loại giá
    String phanLoaiGia() {
        if (donGia < 50000) {
            return "Sách giá thấp";
        } else if (donGia <= 100000) {
            return "Sách giá trung bình";
        } else if (donGia <= 200000) {
            return "Sách giá cao";
        } else {
            return "Sách giá đặc biệt";
        }
    }

    // Tuần 4: Bài 3 - Phân loại tồn kho
    String phanLoaiTonKho() {
        if (soLuong == 0) {
            return "Hết sách";
        } else if (soLuong <= 5) {
            return "Sắp hết";
        } else if (soLuong <= 20) {
            return "Còn ít";
        } else {
            return "Còn nhiều";
        }
    }

    // Tuần 4: Bài 4 - Loại sách bằng switch
    String tenLoaiSach() {
        switch (loaiSach) {
            case 1:
                return "Sách giáo khoa";
            case 2:
                return "Sách tham khảo";
            case 3:
                return "Sách chuyên ngành";
            case 4:
                return "Sách khác";
            default:
                return "Không xác định";
        }
    }

    void hienThi() {
        System.out.println("Mã sách         : " + maSach);
        System.out.println("Tên sách        : " + tenSach);
        System.out.println("Tác giả         : " + tacGia);
        System.out.println("Năm xuất bản    : " + namXuatBan);
        System.out.println("Loại sách       : " + tenLoaiSach());
        System.out.println("Đơn giá         : " + dinhDangTien(donGia));
        System.out.println("Số lượng        : " + soLuong);
        System.out.println("Thành tiền      : " + dinhDangTien(getThanhTien()));
        System.out.println("Phí vận chuyển  : " + dinhDangTien(getPhiVanChuyen()));
        System.out.println("Tổng tiền nhập  : " + dinhDangTien(getTongTienNhap()));
        System.out.println("Phân loại giá   : " + phanLoaiGia());
        System.out.println("Tồn kho         : " + phanLoaiTonKho());
    }

    static String dinhDangTien(double soTien) {
        return String.format(Locale.forLanguageTag("vi-VN"), "%,.0f VND", soTien);
    }
}

public class baitaptuan3 {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Sach> danhSach = new ArrayList<>();

    // Báo lỗi khi nhập sai kiểu dữ liệu
    static String docChuoi(String thongBao) {
        String s;
        do {
            System.out.print(thongBao);
            s = scanner.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("  -> Không được để trống, nhập lại!");
            }
        } while (s.isEmpty());
        return s;
    }

    static int docSoNguyen(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  -> Vui lòng nhập một số nguyên!");
            }
        }
    }

    static double docSoThuc(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  -> Vui lòng nhập một số (dùng dấu chấm, ví dụ 8.5)!");
            }
        }
    }

    // Tìm vị trí sách theo mã, trả về -1 nếu không có
    static int timViTri(String ma) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).maSach.equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    //Nhập 1 cuốn sách, lặp lại cho đến khi dữ liệu hợp lệ
    static Sach nhapMotSach() {
        Sach s = new Sach();

        // Mã sách không được trùng
        String ma;
        do {
            ma = docChuoi("Nhập mã sách: ");
            if (timViTri(ma) != -1) {
                System.out.println("  -> Mã sách đã tồn tại, nhập mã khác!");
            }
        } while (timViTri(ma) != -1);
        s.maSach = ma;

        s.tenSach = docChuoi("Nhập tên sách: ");
        s.tacGia = docChuoi("Nhập tác giả: ");

        do {
            s.namXuatBan = docSoNguyen("Nhập năm xuất bản (1901 - 2026): ");
            if (s.namXuatBan <= 1900 || s.namXuatBan > 2026) {
                System.out.println("  -> Năm xuất bản không hợp lệ!");
            }
        } while (s.namXuatBan <= 1900 || s.namXuatBan > 2026);

        do {
            s.donGia = docSoThuc("Nhập đơn giá (> 0): ");
            if (s.donGia <= 0) {
                System.out.println("  -> Đơn giá phải lớn hơn 0!");
            }
        } while (s.donGia <= 0);

        do {
            s.soLuong = docSoNguyen("Nhập số lượng (>= 0): ");
            if (s.soLuong < 0) {
                System.out.println("  -> Số lượng không được âm!");
            }
        } while (s.soLuong < 0);

        do {
            s.loaiSach = docSoNguyen("Nhập loại sách (1: SGK, 2: Tham khảo, 3: Chuyên ngành, 4: Khác): ");
            if (s.loaiSach < 1 || s.loaiSach > 4) {
                System.out.println("  -> Loại sách chỉ từ 1 đến 4!");
            }
        } while (s.loaiSach < 1 || s.loaiSach > 4);

        return s;
    }

    //Bài 1: Nhập n sách bằng vòng lặp for
    static void nhapNSach() {
        int n;
        do {
            n = docSoNguyen("Bạn muốn nhập bao nhiêu cuốn sách? ");
            if (n <= 0) {
                System.out.println("  -> n phải lớn hơn 0!");
            }
        } while (n <= 0);

        for (int i = 1; i <= n; i++) {
            System.out.println("--- Nhập sách thứ " + i + "/" + n + " ---");
            danhSach.add(nhapMotSach());
        }
        System.out.println("Đã thêm " + n + " cuốn sách.");
    }

    // Hiển thị toàn bộ danh sách (for-each)
    static void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        int stt = 1;
        for (Sach s : danhSach) {
            System.out.println("=== Sách " + stt + " ===");
            s.hienThi();
            stt++;
        }
    }

    //Bài 2: Tính tổng số lượng và tổng giá trị sách 
    static void thongKe() {
        if (danhSach.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        int tongSoLuong = 0;
        double tongGiaTri = 0;
        double tongNhap = 0;

        int i = 0;
        while (i < danhSach.size()) { // dùng while
            Sach s = danhSach.get(i);
            tongSoLuong += s.soLuong;
            tongGiaTri += s.getThanhTien();
            tongNhap += s.getTongTienNhap();
            i++;
        }

        System.out.println("Số đầu sách            : " + danhSach.size());
        System.out.println("Tổng số lượng sách     : " + tongSoLuong);
        System.out.println("Tổng giá trị sách      : " + Sach.dinhDangTien(tongGiaTri));
        System.out.println("Tổng tiền nhập (có phí): " + Sach.dinhDangTien(tongNhap));
    }

    //Bài 4: Tìm sách theo mã 
    static void timSachTheoMa() {
        if (danhSach.isEmpty()) {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        String ma = docChuoi("Nhập mã sách cần tìm: ");
        int viTri = timViTri(ma);
        if (viTri == -1) {
            System.out.println("Không tìm thấy sách có mã \"" + ma + "\".");
        } else {
            System.out.println("Tìm thấy sách:");
            danhSach.get(viTri).hienThi();
        }
    }

    //Bài 3: Menu lặp đến khi chọn Thoát (do-while) 
    public static void main(String[] args) {
        int chon;
        do {
            System.out.println();
            System.out.println("QUẢN LÝ THƯ VIỆN");
            System.out.println("1. Nhập n cuốn sách");
            System.out.println("2. Hiển thị danh sách sách");
            System.out.println("3. Tổng số lượng và tổng giá trị sách");
            System.out.println("4. Tìm sách theo mã");
            System.out.println("0. Thoát");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon) {
                case 1:
                    nhapNSach();
                    break;
                case 2:
                    hienThiDanhSach();
                    break;
                case 3:
                    thongKe();
                    break;
                case 4:
                    timSachTheoMa();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        } while (chon != 0);

        scanner.close();
    }
}