import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
    static Scanner scanner = new Scanner(System.in);

    // Yêu cầu 4: danh sách các đối tượng Sach quản lý bằng ArrayList
    static ArrayList<Sach> danhSach = new ArrayList<>();

    // File lưu dữ liệu (nằm trong thư mục đang chạy chương trình)
    static final String FILE_SACH = "sach_tuan7.txt";

    // Lưu và đọc file

    // Ghi cả danh sách ra file, mỗi cuốn sách một dòng
    static void luuFile()
    {
        ArrayList<String> cacDong = new ArrayList<>();
        for (int i = 0; i < danhSach.size(); i++)
        {
            cacDong.add(danhSach.get(i).toChuoi());
        }
        try
        {
            Files.write(Paths.get(FILE_SACH), cacDong, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    // Đọc file và tạo lại các đối tượng Sach đưa vào danh sách
    static void docFile()
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
                Sach s = Sach.tuChuoi(dong);
                if (s != null)
                {
                    danhSach.add(s);
                }
                else
                {
                    System.out.println("Bỏ qua dòng bị lỗi trong file: " + dong);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    // Tạo dữ liệu mẫu ở lần chạy đầu tiên (khi chưa có file) để khỏi phải nhập tay
    static void taoDuLieuMau()
    {
        if (!Files.exists(Paths.get(FILE_SACH)))
        {
            danhSach.add(new Sach("S01", "Toán 10", "Nguyễn Văn An", 2020, 10));
            danhSach.add(new Sach("S02", "Ngữ văn 10", "Trần Thị Bình", 2019, 3));
            danhSach.add(new Sach("S03", "Lập trình Java", "Lê Minh Cường", 2023, 25));
            danhSach.add(new Sach("S04", "Tuổi trẻ đáng giá bao nhiêu", "Rosie Nguyễn", 2018, 0));
            danhSach.add(new Sach("S05", "Luyện thi Toán nâng cao", "Phạm Hoàng Dũng", 2022, 8));
            luuFile();
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

    // Yêu cầu 3: tạo đối tượng từ lớp Sach

    static void yeuCau3()
    {
        System.out.println("YÊU CẦU 3: TẠO ĐỐI TƯỢNG TỪ LỚP SACH");

        // Tạo đối tượng bằng từ khóa new
        Sach sach1 = new Sach("B01", "Lập trình Java cơ bản", "Nguyễn Văn A", 2022, 15);
        Sach sach2 = new Sach("B02", "Cấu trúc dữ liệu", "Trần Thị B", 2021, 7);
        Sach sach3 = new Sach("B03", "Cơ sở dữ liệu", "Lê Văn C", 2020, 0);

        // Gọi phương thức hienThiThongTin() của từng đối tượng
        System.out.println("Sách thứ nhất:");
        sach1.hienThiThongTin();
        System.out.println();

        System.out.println("Sách thứ hai:");
        sach2.hienThiThongTin();
        System.out.println();

        System.out.println("Sách thứ ba:");
        sach3.hienThiThongTin();
        System.out.println();

        // Thay đổi thuộc tính của đối tượng rồi hiển thị lại
        System.out.println("Sau khi nhập thêm 10 cuốn cho sách thứ ba:");
        sach3.soLuong = sach3.soLuong + 10;
        sach3.hienThiThongTin();
    }

    // Yêu cầu 4: quản lý danh sách Sach bằng ArrayList

    // Tìm vị trí cuốn sách theo mã, trả về -1 nếu không có
    static int timViTri(String ma)
    {
        for (int i = 0; i < danhSach.size(); i++)
        {
            if (danhSach.get(i).maSach.equalsIgnoreCase(ma))
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

    // Nhập thông tin một cuốn sách (mã đã có sẵn), trả về đối tượng Sach
    static Sach nhapSach(String ma)
    {
        String tieuDe = docChuoi("Nhập tiêu đề: ");
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

        return new Sach(ma, tieuDe, tacGia, namXuatBan, soLuong);
    }

    // 1. Thêm sách
    static void themSach()
    {
        String ma = nhapMaMoi();
        danhSach.add(nhapSach(ma));
        luuFile();
        System.out.println("Đã thêm sách. Hiện có " + danhSach.size() + " cuốn.");
    }

    // 2. Hiển thị danh sách
    static void hienThiDanhSach()
    {
        if (danhSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        for (int i = 0; i < danhSach.size(); i++)
        {
            System.out.println("Sách " + (i + 1));
            danhSach.get(i).hienThiThongTin();
            System.out.println();
        }
        System.out.println("Tổng cộng: " + danhSach.size() + " cuốn.");
    }

    // 3. Tìm sách (theo mã hoặc một phần tiêu đề)
    static void timSach()
    {
        if (danhSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        String tuKhoa = docChuoi("Nhập mã hoặc tiêu đề cần tìm: ").toLowerCase();
        int dem = 0;
        for (int i = 0; i < danhSach.size(); i++)
        {
            Sach s = danhSach.get(i);
            if (s.maSach.toLowerCase().contains(tuKhoa) || s.tieuDe.toLowerCase().contains(tuKhoa))
            {
                dem++;
                System.out.println("Kết quả " + dem + " (vị trí " + (i + 1) + ")");
                s.hienThiThongTin();
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
        if (danhSach.isEmpty())
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
        danhSach.get(viTri).hienThiThongTin();
        String xacNhan = docChuoi("Bạn chắc chắn muốn xóa? (c: có, k: không): ");
        if (xacNhan.equalsIgnoreCase("c"))
        {
            danhSach.remove(viTri);
            luuFile();
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
        if (danhSach.isEmpty())
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
        Sach cu = danhSach.get(viTri);
        System.out.println("Thông tin hiện tại:");
        cu.hienThiThongTin();
        System.out.println("Nhập thông tin mới (giữ nguyên mã sách):");
        danhSach.set(viTri, nhapSach(cu.maSach));
        luuFile();
        System.out.println("Đã cập nhật sách.");
    }

    // 6. Tính tổng số lượng
    static void tinhTongSoLuong()
    {
        if (danhSach.isEmpty())
        {
            System.out.println("Thư viện chưa có sách nào.");
            return;
        }
        int tong = 0;
        for (int i = 0; i < danhSach.size(); i++)
        {
            tong += danhSach.get(i).soLuong;
        }
        System.out.println("Số đầu sách        : " + danhSach.size());
        System.out.println("Tổng số lượng sách : " + tong);
    }

    static void yeuCau4()
    {
        int chon;
        do
        {
            System.out.println();
            System.out.println("YÊU CẦU 4: QUẢN LÝ DANH SÁCH SÁCH (ArrayList<Sach>)");
            System.out.println("1. Thêm sách");
            System.out.println("2. Hiển thị sách");
            System.out.println("3. Tìm sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Sửa sách");
            System.out.println("6. Tính tổng số lượng");
            System.out.println("7. Quay lại");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                    themSach();
                    break;
                case 2:
                    hienThiDanhSach();
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
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
                    break;
            }
        }
        while (chon != 7);
    }

    public static void main(String[] args)
    {
        // Nạp dữ liệu đã lưu từ lần chạy trước, nếu chưa có file thì tạo dữ liệu mẫu
        docFile();
        taoDuLieuMau();
        System.out.println("Đã nạp " + danhSach.size() + " cuốn sách từ file.");

        int chon;
        do
        {
            System.out.println();
            System.out.println("BÀI THỰC HÀNH TUẦN 7: LỚP VÀ ĐỐI TƯỢNG");
            System.out.println("1. Yêu cầu 3: Tạo đối tượng từ lớp Sach");
            System.out.println("2. Yêu cầu 4: Quản lý danh sách Sach bằng ArrayList");
            System.out.println("0. Thoát chương trình");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                    yeuCau3();
                    break;
                case 2:
                    yeuCau4();
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
