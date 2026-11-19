import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


// các con không có tính đa kế thừa

public class Test
{
    static Scanner scanner = new Scanner(System.in);

    // Mỗi loại sách có một danh sách riêng
    static ArrayList<SachGiaoKhoa> danhSachGiaoKhoa = new ArrayList<>();
    static ArrayList<SachThamKhao> danhSachThamKhao = new ArrayList<>();

    // File lưu dữ liệu (nằm trong thư mục đang chạy chương trình)
    static final String FILE_GIAO_KHOA = "sachgiaokhoa_tuan9.txt";
    static final String FILE_THAM_KHAO = "sachthamkhao_tuan9.txt";

    // Lưu và đọc file

    // Ghi các dòng chữ ra file
    static void ghiCacDong(String tenFile, ArrayList<String> cacDong)
    {
        try
        {
            Files.write(Paths.get(tenFile), cacDong, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi lưu file " + tenFile + ": " + e.getMessage());
        }
    }

    // Đọc các dòng chữ từ file (file chưa có thì trả về danh sách rỗng)
    static ArrayList<String> docCacDong(String tenFile)
    {
        ArrayList<String> cacDong = new ArrayList<>();
        Path path = Paths.get(tenFile);
        if (!Files.exists(path))
        {
            return cacDong;
        }
        try
        {
            for (String dong : Files.readAllLines(path, StandardCharsets.UTF_8))
            {
                if (!dong.trim().isEmpty())
                {
                    cacDong.add(dong);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi đọc file " + tenFile + ": " + e.getMessage());
        }
        return cacDong;
    }

    static void luuGiaoKhoa()
    {
        ArrayList<String> cacDong = new ArrayList<>();
        for (int i = 0; i < danhSachGiaoKhoa.size(); i++)
        {
            cacDong.add(danhSachGiaoKhoa.get(i).toChuoi());
        }
        ghiCacDong(FILE_GIAO_KHOA, cacDong);
    }

    static void luuThamKhao()
    {
        ArrayList<String> cacDong = new ArrayList<>();
        for (int i = 0; i < danhSachThamKhao.size(); i++)
        {
            cacDong.add(danhSachThamKhao.get(i).toChuoi());
        }
        ghiCacDong(FILE_THAM_KHAO, cacDong);
    }

    // Nạp dữ liệu từ hai file vào hai danh sách
    static void napDuLieu()
    {
        for (String dong : docCacDong(FILE_GIAO_KHOA))
        {
            SachGiaoKhoa s = SachGiaoKhoa.tuChuoi(dong);
            if (s != null)
            {
                danhSachGiaoKhoa.add(s);
            }
            else
            {
                System.out.println("Bỏ qua dòng bị lỗi trong " + FILE_GIAO_KHOA + ": " + dong);
            }
        }
        for (String dong : docCacDong(FILE_THAM_KHAO))
        {
            SachThamKhao s = SachThamKhao.tuChuoi(dong);
            if (s != null)
            {
                danhSachThamKhao.add(s);
            }
            else
            {
                System.out.println("Bỏ qua dòng bị lỗi trong " + FILE_THAM_KHAO + ": " + dong);
            }
        }
    }

    // Tạo dữ liệu mẫu ở lần chạy đầu tiên (khi chưa có file) để khỏi phải nhập tay
    static void taoDuLieuMau()
    {
        if (!Files.exists(Paths.get(FILE_GIAO_KHOA)))
        {
            danhSachGiaoKhoa.add(new SachGiaoKhoa("GK01", 5, 9, 2026, 85000, 10, "Giáo dục", "Mới"));
            danhSachGiaoKhoa.add(new SachGiaoKhoa("GK02", 12, 8, 2025, 60000, 20, "Kim Đồng", "Cũ"));
            danhSachGiaoKhoa.add(new SachGiaoKhoa("GK03", 20, 10, 2026, 95000, 15, "Giáo dục", "Mới"));
            danhSachGiaoKhoa.add(new SachGiaoKhoa("GK04", 3, 1, 2026, 70000, 8, "Kim Đồng", "Cũ"));
            danhSachGiaoKhoa.add(new SachGiaoKhoa("GK05", 2, 3, 2026, 110000, 5, "Giáo dục", "Cũ"));
            luuGiaoKhoa();
        }
        if (!Files.exists(Paths.get(FILE_THAM_KHAO)))
        {
            danhSachThamKhao.add(new SachThamKhao("TK01", 15, 2, 2026, 150000, 6, "Trẻ", 30000));
            danhSachThamKhao.add(new SachThamKhao("TK02", 8, 4, 2026, 220000, 4, "Giáo dục", 50000));
            danhSachThamKhao.add(new SachThamKhao("TK03", 25, 6, 2026, 99000, 12, "Kim Đồng", 15000));
            luuThamKhao();
        }
    }

    // Các hàm nhập an toàn

    static String docChuoi(String thongBao)
    {
        String s;
        do
        {
            System.out.print(thongBao);
            // Đổi dấu | thành / vì | được dùng để ngăn cách thông tin khi lưu file
            s = scanner.nextLine().trim().replace("|", "/");
            if (s.isEmpty())
            {
                System.out.println("  -> Không được để trống, nhập lại!");
            }
        }
        while (s.isEmpty());
        return s;
    }

    static int docSoNguyen(String thongBao)
    {
        while (true)
        {
            System.out.print(thongBao);
            try
            {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.println("  -> Vui lòng nhập một số nguyên!");
            }
        }
    }

    static double docSoThuc(String thongBao)
    {
        while (true)
        {
            System.out.print(thongBao);
            try
            {
                return Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.println("  -> Vui lòng nhập một số (dùng dấu chấm, ví dụ 85000.5)!");
            }
        }
    }

    // Nhập ngày nhập (ngày, tháng, năm), lặp lại đến khi là ngày hợp lệ
    // Trả về mảng 3 phần tử: [0] là ngày, [1] là tháng, [2] là năm
    static int[] docNgayNhap()
    {
        int ngay;
        int thang;
        int nam;
        do
        {
            ngay = docSoNguyen("nhập - ngày: ");
            thang = docSoNguyen("nhập - tháng: ");
            nam = docSoNguyen("nhập - năm: ");
            if (!Sach.ngayHopLe(ngay, thang, nam))
            {
                System.out.println("  -> Ngày nhập không hợp lệ, nhập lại cả ngày, tháng, năm!");
            }
        }
        while (!Sach.ngayHopLe(ngay, thang, nam));
        return new int[] {ngay, thang, nam};
    }

    static double docDonGia()
    {
        double donGia;
        do
        {
            donGia = docSoThuc("Nhập đơn giá (> 0): ");
            if (!Sach.donGiaHopLe(donGia))
            {
                System.out.println("  -> Đơn giá phải lớn hơn 0!");
            }
        }
        while (!Sach.donGiaHopLe(donGia));
        return donGia;
    }

    static int docSoLuong()
    {
        int soLuong;
        do
        {
            soLuong = docSoNguyen("Nhập số lượng (>= 0): ");
            if (!Sach.soLuongHopLe(soLuong))
            {
                System.out.println("  -> Số lượng không được âm!");
            }
        }
        while (!Sach.soLuongHopLe(soLuong));
        return soLuong;
    }

    // Kiểm tra mã sách đã có trong một trong hai danh sách chưa
    static boolean maDaTonTai(String ma)
    {
        for (int i = 0; i < danhSachGiaoKhoa.size(); i++)
        {
            if (danhSachGiaoKhoa.get(i).getMaSach().equalsIgnoreCase(ma))
            {
                return true;
            }
        }
        for (int i = 0; i < danhSachThamKhao.size(); i++)
        {
            if (danhSachThamKhao.get(i).getMaSach().equalsIgnoreCase(ma))
            {
                return true;
            }
        }
        return false;
    }

    // Nhập mã mới, không được trùng với bất kỳ sách nào
    static String nhapMaMoi()
    {
        String ma;
        do
        {
            ma = docChuoi("Nhập mã sách: ");
            if (maDaTonTai(ma))
            {
                System.out.println("  -> Mã sách đã tồn tại, nhập mã khác!");
            }
        }
        while (maDaTonTai(ma));
        return ma;
    }

    // Số lượng sách muốn nhập (n phải lớn hơn 0)
    static int docSoCuonCanNhap(String tenLoai)
    {
        int n;
        do
        {
            n = docSoNguyen("Bạn muốn nhập bao nhiêu cuốn " + tenLoai + "? ");
            if (n <= 0)
            {
                System.out.println("  -> Số cuốn phải lớn hơn 0!");
            }
        }
        while (n <= 0);
        return n;
    }

    // Nhập danh sách

    // 1. Nhập danh sách sách giáo khoa
    static void nhapSachGiaoKhoa()
    {
        int n = docSoCuonCanNhap("sách giáo khoa");
        for (int i = 1; i <= n; i++)
        {
            System.out.println("Nhập sách giáo khoa thứ " + i + "/" + n);
            String ma = nhapMaMoi();
            int[] ngayNhap = docNgayNhap();
            double donGia = docDonGia();
            int soLuong = docSoLuong();
            String nhaXuatBan = docChuoi("Nhập nhà xuất bản: ");

            int chonTinhTrang;
            do
            {
                chonTinhTrang = docSoNguyen("Nhập tình trạng (1: Mới, 2: Cũ): ");
                if (chonTinhTrang != 1 && chonTinhTrang != 2)
                {
                    System.out.println("  -> Chỉ nhập 1 hoặc 2!");
                }
            }
            while (chonTinhTrang != 1 && chonTinhTrang != 2);

            String tinhTrang;
            if (chonTinhTrang == 1)
            {
                tinhTrang = SachGiaoKhoa.MOI;
            }
            else
            {
                tinhTrang = SachGiaoKhoa.CU;
            }

            danhSachGiaoKhoa.add(new SachGiaoKhoa(ma, ngayNhap[0], ngayNhap[1], ngayNhap[2],
                    donGia, soLuong, nhaXuatBan, tinhTrang));
        }
        luuGiaoKhoa();
        System.out.println("Đã thêm " + n + " sách giáo khoa. Hiện có " + danhSachGiaoKhoa.size() + " cuốn.");
    }

    // 2. Nhập danh sách sách tham khảo
    static void nhapSachThamKhao()
    {
        int n = docSoCuonCanNhap("sách tham khảo");
        for (int i = 1; i <= n; i++)
        {
            System.out.println("Nhập sách tham khảo thứ " + i + "/" + n);
            String ma = nhapMaMoi();
            int[] ngayNhap = docNgayNhap();
            double donGia = docDonGia();
            int soLuong = docSoLuong();
            String nhaXuatBan = docChuoi("Nhập nhà xuất bản: ");

            double thue;
            do
            {
                thue = docSoThuc("Nhập thuế (số tiền, >= 0): ");
                if (!SachThamKhao.thueHopLe(thue))
                {
                    System.out.println("  -> Thuế không được âm!");
                }
            }
            while (!SachThamKhao.thueHopLe(thue));

            danhSachThamKhao.add(new SachThamKhao(ma, ngayNhap[0], ngayNhap[1], ngayNhap[2],
                    donGia, soLuong, nhaXuatBan, thue));
        }
        luuThamKhao();
        System.out.println("Đã thêm " + n + " sách tham khảo. Hiện có " + danhSachThamKhao.size() + " cuốn.");
    }

    // Xuất danh sách

    // 3. Xuất danh sách sách giáo khoa
    static void xuatSachGiaoKhoa()
    {
        if (danhSachGiaoKhoa.isEmpty())
        {
            System.out.println("Chưa có sách giáo khoa nào.");
            return;
        }
        for (int i = 0; i < danhSachGiaoKhoa.size(); i++)
        {
            System.out.println("Sách giáo khoa " + (i + 1));
            danhSachGiaoKhoa.get(i).hienThiThongTin();
            System.out.println();
        }
        System.out.println("Tổng cộng: " + danhSachGiaoKhoa.size() + " cuốn sách giáo khoa.");
    }

    // 4. Xuất danh sách sách tham khảo
    static void xuatSachThamKhao()
    {
        if (danhSachThamKhao.isEmpty())
        {
            System.out.println("Chưa có sách tham khảo nào.");
            return;
        }
        for (int i = 0; i < danhSachThamKhao.size(); i++)
        {
            System.out.println("Sách tham khảo " + (i + 1));
            danhSachThamKhao.get(i).hienThiThongTin();
            System.out.println();
        }
        System.out.println("Tổng cộng: " + danhSachThamKhao.size() + " cuốn sách tham khảo.");
    }

    // 5. Tính tổng thành tiền cho từng loại
    static void tinhTongThanhTien()
    {
        double tongGiaoKhoa = 0;
        for (int i = 0; i < danhSachGiaoKhoa.size(); i++)
        {
            tongGiaoKhoa += danhSachGiaoKhoa.get(i).thanhTien();
        }

        double tongThamKhao = 0;
        for (int i = 0; i < danhSachThamKhao.size(); i++)
        {
            tongThamKhao += danhSachThamKhao.get(i).thanhTien();
        }

        System.out.println("Tổng thành tiền sách giáo khoa (" + danhSachGiaoKhoa.size() + " cuốn): "
                + Sach.dinhDangTien(tongGiaoKhoa));
        System.out.println("Tổng thành tiền sách tham khảo (" + danhSachThamKhao.size() + " cuốn): "
                + Sach.dinhDangTien(tongThamKhao));
        System.out.println("Tổng thành tiền cả hai loại: " + Sach.dinhDangTien(tongGiaoKhoa + tongThamKhao));
    }

    // 6. Tính trung bình cộng đơn giá của các sách tham khảo
    static void tinhTrungBinhDonGiaThamKhao()
    {
        if (danhSachThamKhao.isEmpty())
        {
            System.out.println("Chưa có sách tham khảo nào để tính trung bình.");
            return;
        }
        double tong = 0;
        for (int i = 0; i < danhSachThamKhao.size(); i++)
        {
            tong += danhSachThamKhao.get(i).getDonGia();
        }
        double trungBinh = tong / danhSachThamKhao.size();
        System.out.println("Trung bình cộng đơn giá của " + danhSachThamKhao.size()
                + " sách tham khảo: " + Sach.dinhDangTien(trungBinh));
    }

    // 7. Xuất ra các sách giáo khoa của nhà xuất bản X
    static void xuatGiaoKhoaTheoNhaXuatBan()
    {
        if (danhSachGiaoKhoa.isEmpty())
        {
            System.out.println("Chưa có sách giáo khoa nào.");
            return;
        }
        String x = docChuoi("Nhập tên nhà xuất bản X: ");
        int dem = 0;
        for (int i = 0; i < danhSachGiaoKhoa.size(); i++)
        {
            SachGiaoKhoa s = danhSachGiaoKhoa.get(i);
            if (s.getNhaXuatBan().equalsIgnoreCase(x))
            {
                dem++;
                System.out.println("Kết quả " + dem);
                s.hienThiThongTin();
                System.out.println();
            }
        }
        if (dem == 0)
        {
            System.out.println("Không có sách giáo khoa nào của nhà xuất bản \"" + x + "\".");
        }
        else
        {
            System.out.println("Có " + dem + " sách giáo khoa của nhà xuất bản \"" + x + "\".");
        }
    }

    public static void main(String[] args)
    {
        // Nạp dữ liệu đã lưu từ lần chạy trước, nếu chưa có file thì tạo dữ liệu mẫu
        napDuLieu();
        taoDuLieuMau();
        System.out.println("Đã nạp " + danhSachGiaoKhoa.size() + " sách giáo khoa và "
                + danhSachThamKhao.size() + " sách tham khảo.");

        int chon;
        do
        {
            System.out.println();
            System.out.println("BÀI THỰC HÀNH TUẦN 9: TÍNH KẾ THỪA");
            System.out.println("1. Nhập danh sách sách giáo khoa");
            System.out.println("2. Nhập danh sách sách tham khảo");
            System.out.println("3. Xuất danh sách sách giáo khoa");
            System.out.println("4. Xuất danh sách sách tham khảo");
            System.out.println("5. Tính tổng thành tiền cho từng loại");
            System.out.println("6. Tính trung bình cộng đơn giá sách tham khảo");
            System.out.println("7. Xuất sách giáo khoa của nhà xuất bản X");
            System.out.println("0. Thoát");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                    nhapSachGiaoKhoa();
                    break;
                case 2:
                    nhapSachThamKhao();
                    break;
                case 3:
                    xuatSachGiaoKhoa();
                    break;
                case 4:
                    xuatSachThamKhao();
                    break;
                case 5:
                    tinhTongThanhTien();
                    break;
                case 6:
                    tinhTrungBinhDonGiaThamKhao();
                    break;
                case 7:
                    xuatGiaoKhoaTheoNhaXuatBan();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        }
        while (chon != 0);

        scanner.close();
    }
}
