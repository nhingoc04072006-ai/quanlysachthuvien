import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class baitaptuan3
{
    static Scanner scanner = new Scanner(System.in);

    // Bài 2: ArrayList<String> lưu mã sách
    static ArrayList<String> danhSachMa = new ArrayList<>();

    // Bài 3, 4: ArrayList<String>, mỗi phần tử là thông tin của một cuốn sách
    // Định dạng một phần tử: maSach|tenSach|tacGia|namXuatBan|donGia|soLuong|loaiSach
    static ArrayList<String> danhSachSach = new ArrayList<>();

    // Lưu và đọc file
    // File nằm trong thư mục đang chạy chương trình (thư mục project)
    static final String FILE_SACH = "sach.txt";
    static final String FILE_MA = "masach.txt";

    // Ghi cả danh sách ra file, mỗi phần tử một dòng
    static void luuFile(String tenFile, ArrayList<String> ds)
    {
        try
        {
            Files.write(Paths.get(tenFile), ds, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi lưu file " + tenFile + ": " + e.getMessage());
        }
    }

    // Đọc file nạp vào danh sách mã sách
    static void docFileMa()
    {
        Path path = Paths.get(FILE_MA);
        if (!Files.exists(path))
        {
            return;
        }
        try
        {
            for (String dong : Files.readAllLines(path, StandardCharsets.UTF_8))
            {
                if (!dong.trim().isEmpty())
                {
                    danhSachMa.add(dong.trim());
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi đọc file " + FILE_MA + ": " + e.getMessage());
        }
    }

    // Đọc file nạp vào danh sách sách (bỏ qua dòng bị hỏng)
    static void docFileSach()
    {
        Path path = Paths.get(FILE_SACH);
        if (!Files.exists(path))
        {
            return;
        }
        try
        {
            for (String dong : Files.readAllLines(path, StandardCharsets.UTF_8))
            {
                if (dong.trim().isEmpty())
                {
                    continue;
                }
                if (chuoiHopLe(dong))
                {
                    danhSachSach.add(dong);
                }
                else
                {
                    System.out.println("Bỏ qua dòng bị lỗi trong " + FILE_SACH + ": " + dong);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi đọc file " + FILE_SACH + ": " + e.getMessage());
        }
    }

    // Kiểm tra một chuỗi sách có đủ 7 phần và các phần số có đúng định dạng không
    static boolean chuoiHopLe(String chuoi)
    {
        String[] p = chuoi.split("\\|");
        if (p.length != 7)
        {
            return false;
        }
        try
        {
            Integer.parseInt(p[3]);
            Double.parseDouble(p[4]);
            Integer.parseInt(p[5]);
            Integer.parseInt(p[6]);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    // Các hàm nhập an toàn
    static String docChuoi(String thongBao)
    {
        String s;
        do
        {
            System.out.print(thongBao);
            // Đổi dấu | thành / vì | được dùng để ngăn cách các thông tin trong một chuỗi sách
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

    // Các hàm lấy từng thông tin từ một chuỗi sách
    static String layMa(String chuoi)
    {
        return chuoi.split("\\|")[0];
    }

    static String layTen(String chuoi)
    {
        return chuoi.split("\\|")[1];
    }

    static String layTacGia(String chuoi)
    {
        return chuoi.split("\\|")[2];
    }

    static int layNamXuatBan(String chuoi)
    {
        return Integer.parseInt(chuoi.split("\\|")[3]);
    }

    static double layDonGia(String chuoi)
    {
        return Double.parseDouble(chuoi.split("\\|")[4]);
    }

    static int laySoLuong(String chuoi)
    {
        return Integer.parseInt(chuoi.split("\\|")[5]);
    }

    static int layLoaiSach(String chuoi)
    {
        return Integer.parseInt(chuoi.split("\\|")[6]);
    }

    // Ghép các thông tin thành một chuỗi sách
    static String taoChuoi(String ma, String ten, String tacGia, int nam, double donGia, int soLuong, int loai)
    {
        return ma + "|" + ten + "|" + tacGia + "|" + nam + "|" + donGia + "|" + soLuong + "|" + loai;
    }

    // Các hàm tính toán (tuần 3 và tuần 4)
    static double tinhThanhTien(double donGia, int soLuong)
    {
        return donGia * soLuong;
    }

    static double tinhPhiVanChuyen(double thanhTien)
    {
        return thanhTien * 0.05;
    }

    static String phanLoaiGia(double donGia)
    {
        if (donGia < 50000)
        {
            return "Sách giá thấp";
        }
        else if (donGia <= 100000)
        {
            return "Sách giá trung bình";
        }
        else if (donGia <= 200000)
        {
            return "Sách giá cao";
        }
        else
        {
            return "Sách giá đặc biệt";
        }
    }

    static String phanLoaiTonKho(int soLuong)
    {
        if (soLuong == 0)
        {
            return "Hết sách";
        }
        else if (soLuong <= 5)
        {
            return "Sắp hết";
        }
        else if (soLuong <= 20)
        {
            return "Còn ít";
        }
        else
        {
            return "Còn nhiều";
        }
    }

    static String tenLoaiSach(int loaiSach)
    {
        switch (loaiSach)
        {
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

    static String dinhDangTien(double soTien)
    {
        return String.format(Locale.forLanguageTag("vi-VN"), "%,.0f VND", soTien);
    }

    // Hiển thị đầy đủ thông tin của một cuốn sách (nhận vào một chuỗi sách)
    static void hienThiMotSach(String chuoi)
    {
        double donGia = layDonGia(chuoi);
        int soLuong = laySoLuong(chuoi);
        double thanhTien = tinhThanhTien(donGia, soLuong);
        double phiVanChuyen = tinhPhiVanChuyen(thanhTien);
        double tongTienNhap = thanhTien + phiVanChuyen;

        System.out.println("Mã sách         : " + layMa(chuoi));
        System.out.println("Tên sách        : " + layTen(chuoi));
        System.out.println("Tác giả         : " + layTacGia(chuoi));
        System.out.println("Năm xuất bản    : " + layNamXuatBan(chuoi));
        System.out.println("Loại sách       : " + tenLoaiSach(layLoaiSach(chuoi)));
        System.out.println("Đơn giá         : " + dinhDangTien(donGia));
        System.out.println("Số lượng        : " + soLuong);
        System.out.println("Thành tiền      : " + dinhDangTien(thanhTien));
        System.out.println("Phí vận chuyển  : " + dinhDangTien(phiVanChuyen));
        System.out.println("Tổng tiền nhập  : " + dinhDangTien(tongTienNhap));
        System.out.println("Phân loại giá   : " + phanLoaiGia(donGia));
        System.out.println("Tồn kho         : " + phanLoaiTonKho(soLuong));
    }

    // Bài tuần 6

    // Bài 1: Mảng int[] soLuong, nhập 5 cuốn, hiển thị, tính tổng, tìm lớn nhất
    static void bai1()
    {
        System.out.println("BÀI 1: MẢNG SỐ LƯỢNG 5 CUỐN SÁCH");
        int[] soLuong = new int[5];

        for (int i = 0; i < soLuong.length; i++)
        {
            do
            {
                soLuong[i] = docSoNguyen("Nhập số lượng cuốn sách thứ " + (i + 1) + ": ");
                if (soLuong[i] < 0)
                {
                    System.out.println("  -> Số lượng không được âm!");
                }
            }
            while (soLuong[i] < 0);
        }

        System.out.println("Số lượng của 5 cuốn sách:");
        for (int i = 0; i < soLuong.length; i++)
        {
            System.out.println("  Sách " + (i + 1) + ": " + soLuong[i]);
        }

        int tong = 0;
        int max = soLuong[0];
        int viTriMax = 0;
        for (int i = 0; i < soLuong.length; i++)
        {
            tong += soLuong[i];
            if (soLuong[i] > max)
            {
                max = soLuong[i];
                viTriMax = i;
            }
        }
        System.out.println("Tổng số lượng      : " + tong);
        System.out.println("Số lượng lớn nhất  : " + max + " (cuốn sách thứ " + (viTriMax + 1) + ")");
    }

    // Bài 2: ArrayList<String> lưu mã sách, thêm, hiển thị, tìm, xóa
    static void bai2()
    {
        int chon;
        do
        {
            System.out.println();
            System.out.println("BÀI 2: QUẢN LÝ MÃ SÁCH (ArrayList<String>)");
            System.out.println("1. Thêm mã sách");
            System.out.println("2. Hiển thị mã sách");
            System.out.println("3. Tìm mã sách");
            System.out.println("4. Xóa mã sách");
            System.out.println("0. Quay lại");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                {
                    String ma = docChuoi("Nhập mã sách cần thêm: ");
                    if (danhSachMa.contains(ma))
                    {
                        System.out.println("Mã sách đã tồn tại!");
                    }
                    else
                    {
                        danhSachMa.add(ma);
                        luuFile(FILE_MA, danhSachMa);
                        System.out.println("Đã thêm. Hiện có " + danhSachMa.size() + " mã.");
                    }
                    break;
                }
                case 2:
                {
                    if (danhSachMa.isEmpty())
                    {
                        System.out.println("Danh sách mã sách đang trống.");
                    }
                    else
                    {
                        for (int i = 0; i < danhSachMa.size(); i++)
                        {
                            System.out.println((i + 1) + ". " + danhSachMa.get(i));
                        }
                        System.out.println("Tổng cộng: " + danhSachMa.size() + " mã.");
                    }
                    break;
                }
                case 3:
                {
                    String ma = docChuoi("Nhập mã sách cần tìm: ");
                    int viTri = danhSachMa.indexOf(ma);
                    if (viTri == -1)
                    {
                        System.out.println("Không tìm thấy mã \"" + ma + "\".");
                    }
                    else
                    {
                        System.out.println("Tìm thấy mã \"" + ma + "\" ở vị trí số " + (viTri + 1) + ".");
                    }
                    break;
                }
                case 4:
                {
                    String ma = docChuoi("Nhập mã sách cần xóa: ");
                    if (danhSachMa.remove(ma))
                    {
                        luuFile(FILE_MA, danhSachMa);
                        System.out.println("Đã xóa mã \"" + ma + "\".");
                    }
                    else
                    {
                        System.out.println("Không có mã \"" + ma + "\" để xóa.");
                    }
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        }
        while (chon != 0);
    }

    // Bài 3 và Bài 4: ArrayList<String> danhSachSach và menu hoàn chỉnh

    // Tìm vị trí cuốn sách theo mã trong danhSachSach, trả về -1 nếu không có
    static int timViTri(String ma)
    {
        for (int i = 0; i < danhSachSach.size(); i++)
        {
            if (layMa(danhSachSach.get(i)).equalsIgnoreCase(ma))
            {
                return i;
            }
        }
        return -1;
    }

    // Nhập mã mới, không được trùng
    static String nhapMaMoi()
    {
        String ma;
        do
        {
            ma = docChuoi("Nhập mã sách: ");
            if (timViTri(ma) != -1)
            {
                System.out.println("  -> Mã sách đã tồn tại, nhập mã khác!");
            }
        }
        while (timViTri(ma) != -1);
        return ma;
    }

    // Nhập thông tin một cuốn sách (mã đã có sẵn), lặp đến khi hợp lệ, trả về chuỗi sách
    static String nhapThongTinSach(String ma)
    {
        String ten = docChuoi("Nhập tên sách: ");
        String tacGia = docChuoi("Nhập tác giả: ");

        int namXuatBan;
        do
        {
            namXuatBan = docSoNguyen("Nhập năm xuất bản (1901 - 2026): ");
            if (namXuatBan <= 1900 || namXuatBan > 2026)
            {
                System.out.println("  -> Năm xuất bản không hợp lệ!");
            }
        }
        while (namXuatBan <= 1900 || namXuatBan > 2026);

        double donGia;
        do
        {
            donGia = docSoThuc("Nhập đơn giá (> 0): ");
            if (donGia <= 0)
            {
                System.out.println("  -> Đơn giá phải lớn hơn 0!");
            }
        }
        while (donGia <= 0);

        int soLuong;
        do
        {
            soLuong = docSoNguyen("Nhập số lượng (>= 0): ");
            if (soLuong < 0)
            {
                System.out.println("  -> Số lượng không được âm!");
            }
        }
        while (soLuong < 0);

        int loaiSach;
        do
        {
            loaiSach = docSoNguyen("Nhập loại sách (1: SGK, 2: Tham khảo, 3: Chuyên ngành, 4: Khác): ");
            if (loaiSach < 1 || loaiSach > 4)
            {
                System.out.println("  -> Loại sách chỉ từ 1 đến 4!");
            }
        }
        while (loaiSach < 1 || loaiSach > 4);

        return taoChuoi(ma, ten, tacGia, namXuatBan, donGia, soLuong, loaiSach);
    }

    // 1. Thêm sách
    static void themSach()
    {
        String ma = nhapMaMoi();
        danhSachSach.add(nhapThongTinSach(ma));
        luuFile(FILE_SACH, danhSachSach);
        System.out.println("Đã thêm sách. Hiện có " + danhSachSach.size() + " cuốn.");
    }

    // 2. Hiển thị sách
    static void hienThiSach()
    {
        if (danhSachSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        for (int i = 0; i < danhSachSach.size(); i++)
        {
            System.out.println("Sách " + (i + 1));
            hienThiMotSach(danhSachSach.get(i));
            System.out.println();
        }
        System.out.println("Tổng cộng: " + danhSachSach.size() + " cuốn.");
    }

    // 3. Tìm sách (theo mã hoặc một phần tên sách)
    static void timSach()
    {
        if (danhSachSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        String tuKhoa = docChuoi("Nhập mã hoặc tên sách cần tìm: ").toLowerCase();
        int dem = 0;
        for (int i = 0; i < danhSachSach.size(); i++)
        {
            String chuoi = danhSachSach.get(i);
            if (layMa(chuoi).toLowerCase().contains(tuKhoa) || layTen(chuoi).toLowerCase().contains(tuKhoa))
            {
                dem++;
                System.out.println("Kết quả " + dem + " (vị trí " + (i + 1) + ")");
                hienThiMotSach(chuoi);
                System.out.println();
            }
        }
        if (dem == 0)
        {
            System.out.println("Không tìm thấy sách nào phù hợp.");
        }
    }

    // 4. Xóa sách
    static void xoaSach()
    {
        if (danhSachSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        String ma = docChuoi("Nhập mã sách cần xóa: ");
        int viTri = timViTri(ma);
        if (viTri == -1)
        {
            System.out.println("Không tìm thấy sách có mã \"" + ma + "\".");
            return;
        }
        hienThiMotSach(danhSachSach.get(viTri));
        String xacNhan = docChuoi("Bạn chắc chắn muốn xóa? (c: có, k: không): ");
        if (xacNhan.equalsIgnoreCase("c"))
        {
            danhSachSach.remove(viTri);
            luuFile(FILE_SACH, danhSachSach);
            System.out.println("Đã xóa sách.");
        }
        else
        {
            System.out.println("Đã hủy xóa.");
        }
    }

    // 5. Sửa sách (giữ nguyên mã, nhập lại các thông tin còn lại)
    static void suaSach()
    {
        if (danhSachSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        String ma = docChuoi("Nhập mã sách cần sửa: ");
        int viTri = timViTri(ma);
        if (viTri == -1)
        {
            System.out.println("Không tìm thấy sách có mã \"" + ma + "\".");
            return;
        }
        String chuoiCu = danhSachSach.get(viTri);
        System.out.println("Thông tin hiện tại:");
        hienThiMotSach(chuoiCu);
        System.out.println("Nhập thông tin mới (giữ nguyên mã sách):");
        danhSachSach.set(viTri, nhapThongTinSach(layMa(chuoiCu)));
        luuFile(FILE_SACH, danhSachSach);
        System.out.println("Đã cập nhật sách.");
    }

    // 6. Tính tổng số lượng
    static void tinhTongSoLuong()
    {
        if (danhSachSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        int tong = 0;
        for (int i = 0; i < danhSachSach.size(); i++)
        {
            tong += laySoLuong(danhSachSach.get(i));
        }
        System.out.println("Số đầu sách        : " + danhSachSach.size());
        System.out.println("Tổng số lượng sách : " + tong);
    }

    // 7. Tính tổng giá trị
    static void tinhTongGiaTri()
    {
        if (danhSachSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        double tongGiaTri = 0;
        double tongNhap = 0;
        for (int i = 0; i < danhSachSach.size(); i++)
        {
            String chuoi = danhSachSach.get(i);
            double thanhTien = tinhThanhTien(layDonGia(chuoi), laySoLuong(chuoi));
            tongGiaTri += thanhTien;
            tongNhap += thanhTien + tinhPhiVanChuyen(thanhTien);
        }
        System.out.println("Tổng giá trị sách       : " + dinhDangTien(tongGiaTri));
        System.out.println("Tổng tiền nhập (có phí) : " + dinhDangTien(tongNhap));
    }

    // Bài 4: menu hoàn chỉnh
    static void quanLySach()
    {
        int chon;
        do
        {
            System.out.println();
            System.out.println("QUẢN LÝ SÁCH THƯ VIỆN");
            System.out.println("1. Thêm sách");
            System.out.println("2. Hiển thị sách");
            System.out.println("3. Tìm sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Sửa sách");
            System.out.println("6. Tính tổng số lượng");
            System.out.println("7. Tính tổng giá trị");
            System.out.println("8. Thoát");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                    themSach();
                    break;
                case 2:
                    hienThiSach();
                    break;
                case 3:
                    timSach();
                    break;
                case 4:
                    xoaSach();
                    break;
                case 5:
                    suaSach();
                    break;
                case 6:
                    tinhTongSoLuong();
                    break;
                case 7:
                    tinhTongGiaTri();
                    break;
                case 8:
                    System.out.println("Thoát quản lý sách.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        }
        while (chon != 8);
    }

    // Menu tổng để chạy từng bài của tuần 6
    public static void main(String[] args)
    {
        // Nạp dữ liệu đã lưu từ lần chạy trước
        docFileMa();
        docFileSach();
        System.out.println("Đã nạp " + danhSachSach.size() + " cuốn sách và "
                + danhSachMa.size() + " mã sách từ file.");

        int chon;
        do
        {
            System.out.println();
            System.out.println("BÀI THỰC HÀNH TUẦN 6");
            System.out.println("1. Bài 1: Mảng số lượng 5 cuốn sách");
            System.out.println("2. Bài 2: ArrayList<String> mã sách");
            System.out.println("3. Bài 3 + 4: Quản lý sách (menu hoàn chỉnh)");
            System.out.println("0. Thoát chương trình");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                    bai1();
                    break;
                case 2:
                    bai2();
                    break;
                case 3:
                    quanLySach();
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