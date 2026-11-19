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

    // Danh sách các đối tượng Sach quản lý bằng ArrayList
    static ArrayList<Sach> danhSach = new ArrayList<>();

    // File lưu dữ liệu (nằm trong thư mục đang chạy chương trình)
    static final String FILE_SACH = "sach_tuan8.txt";

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

    // Nhập năm xuất bản, lặp lại cho đến khi hợp lệ (dùng hàm kiểm tra của lớp Sach)
    static int docNamXuatBan()
    {
        int nam;
        do
        {
            nam = docSoNguyen("Nhập năm xuất bản: ");
            if (!Sach.namXuatBanHopLe(nam))
            {
                System.out.println("  -> Năm xuất bản phải lớn hơn 0 và không lớn hơn năm hiện tại!");
            }
        }
        while (!Sach.namXuatBanHopLe(nam));
        return nam;
    }

    // Nhập số lượng, lặp lại cho đến khi hợp lệ
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

    // Demo tính đóng gói (Yêu cầu 1, 2, 3, 4)

    static void demoDongGoi()
    {
        System.out.println("DEMO TÍNH ĐÓNG GÓI");

        // Yêu cầu 4: tạo đối tượng bằng constructor có đầy đủ tham số
        System.out.println("1. Tạo sách bằng constructor đầy đủ tham số:");
        Sach sach = new Sach("B01", "Lập trình Java cơ bản", "Nguyễn Văn A", 2022, 15);
        sach.hienThiThongTin();
        System.out.println();

        // Yêu cầu 1: thuộc tính private nên không thể viết sach.soLuong = 100
        // Nếu bỏ dấu // ở dòng dưới, chương trình sẽ báo lỗi biên dịch:
        // sach.soLuong = 100;

        // Yêu cầu 2: dùng getter để lấy giá trị
        System.out.println("2. Dùng getter để lấy giá trị:");
        System.out.println("Tiêu đề  : " + sach.getTieuDe());
        System.out.println("Số lượng : " + sach.getSoLuong());
        System.out.println();

        // Yêu cầu 2 và 3: dùng setter, giá trị hợp lệ thì được thay đổi
        System.out.println("3. Dùng setter với giá trị hợp lệ:");
        sach.setSoLuong(20);
        sach.setNamXuatBan(2023);
        System.out.println("Số lượng mới      : " + sach.getSoLuong());
        System.out.println("Năm xuất bản mới  : " + sach.getNamXuatBan());
        System.out.println();

        // Yêu cầu 3: giá trị sai bị từ chối
        System.out.println("4. Dùng setter với giá trị sai:");
        sach.setSoLuong(-5);
        sach.setNamXuatBan(3000);
        sach.setNamXuatBan(0);
        System.out.println("Số lượng vẫn là      : " + sach.getSoLuong());
        System.out.println("Năm xuất bản vẫn là  : " + sach.getNamXuatBan());
        System.out.println();

        // Yêu cầu 3 kết hợp yêu cầu 4: constructor cũng kiểm tra dữ liệu
        System.out.println("5. Tạo sách với dữ liệu sai (năm 3000, số lượng -1):");
        Sach sachLoi = new Sach("B02", "Sách thử lỗi", "Không rõ", 3000, -1);
        sachLoi.hienThiThongTin();
    }

    // Quản lý danh sách Sach bằng ArrayList (dùng getter và setter)

    // Tìm vị trí cuốn sách theo mã, trả về -1 nếu không có
    static int timViTri(String ma)
    {
        for (int i = 0; i < danhSach.size(); i++)
        {
            if (danhSach.get(i).getMaSach().equalsIgnoreCase(ma))
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

    // 1. Thêm sách
    static void themSach()
    {
        String ma = nhapMaMoi();
        String tieuDe = docChuoi("Nhập tiêu đề: ");
        String tacGia = docChuoi("Nhập tác giả: ");
        int namXuatBan = docNamXuatBan();
        int soLuong = docSoLuong();

        danhSach.add(new Sach(ma, tieuDe, tacGia, namXuatBan, soLuong));
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
            if (s.getMaSach().toLowerCase().contains(tuKhoa) || s.getTieuDe().toLowerCase().contains(tuKhoa))
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

    // 5. Sửa sách: dùng các setter để thay đổi thông tin (giữ nguyên mã sách)
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
        Sach s = danhSach.get(viTri);
        System.out.println("Thông tin hiện tại:");
        s.hienThiThongTin();
        System.out.println("Nhập thông tin mới (giữ nguyên mã sách):");
        s.setTieuDe(docChuoi("Nhập tiêu đề: "));
        s.setTacGia(docChuoi("Nhập tác giả: "));
        s.setNamXuatBan(docNamXuatBan());
        s.setSoLuong(docSoLuong());
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
            tong += danhSach.get(i).getSoLuong();
        }
        System.out.println("Số đầu sách        : " + danhSach.size());
        System.out.println("Tổng số lượng sách : " + tong);
    }

    static void quanLyDanhSach()
    {
        int chon;
        do
        {
            System.out.println();
            System.out.println("QUẢN LÝ DANH SÁCH SÁCH (ArrayList<Sach>)");
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
            System.out.println("BÀI THỰC HÀNH TUẦN 8: TÍNH ĐÓNG GÓI");
            System.out.println("1. Demo tính đóng gói (private, getter, setter, constructor)");
            System.out.println("2. Quản lý danh sách sách bằng ArrayList");
            System.out.println("0. Thoát chương trình");
            chon = docSoNguyen("Mời bạn chọn: ");

            switch (chon)
            {
                case 1:
                    demoDongGoi();
                    break;
                case 2:
                    quanLyDanhSach();
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