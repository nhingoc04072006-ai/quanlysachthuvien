import java.util.Locale;

// Lớp cha: chứa các thông tin chung của mọi loại sách
public class Sach
{
    // Các thuộc tính chung (private, giữ tính đóng gói của tuần 8)
    private String maSach;
    private int ngay;
    private int thang;
    private int nam;
    private double donGia;
    private int soLuong;
    private String nhaXuatBan;

    // Constructor đầy đủ tham số
    // Dùng setter cho ngày nhập, đơn giá, số lượng để dữ liệu sai không lọt vào đối tượng
    public Sach(String maSach, int ngay, int thang, int nam, double donGia, int soLuong, String nhaXuatBan)
    {
        this.maSach = maSach;
        this.nhaXuatBan = nhaXuatBan;
        setNgayNhap(ngay, thang, nam);
        setDonGia(donGia);
        setSoLuong(soLuong);
    }

    // Các hàm kiểm tra dùng chung (lớp con và phần nhập dữ liệu cũng dùng)

    // Số ngày của một tháng (có xét năm nhuận cho tháng 2)
    private static int soNgayTrongThang(int thang, int nam)
    {
        switch (thang)
        {
            case 2:
                if ((nam % 4 == 0 && nam % 100 != 0) || nam % 400 == 0)
                {
                    return 29;
                }
                else
                {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    public static boolean ngayHopLe(int ngay, int thang, int nam)
    {
        if (nam <= 0 || thang < 1 || thang > 12 || ngay < 1)
        {
            return false;
        }
        return ngay <= soNgayTrongThang(thang, nam);
    }

    public static boolean donGiaHopLe(double donGia)
    {
        return donGia > 0;
    }

    public static boolean soLuongHopLe(int soLuong)
    {
        return soLuong >= 0;
    }

    public static String dinhDangTien(double soTien)
    {
        return String.format(Locale.forLanguageTag("vi-VN"), "%,.0f VND", soTien);
    }

    // Getter
    public String getMaSach()
    {
        return maSach;
    }

    public int getNgay()
    {
        return ngay;
    }

    public int getThang()
    {
        return thang;
    }

    public int getNam()
    {
        return nam;
    }

    // Trả về ngày nhập dạng chữ, ví dụ 05/09/2026
    public String getNgayNhap()
    {
        return String.format("%02d/%02d/%04d", ngay, thang, nam);
    }

    public double getDonGia()
    {
        return donGia;
    }

    public int getSoLuong()
    {
        return soLuong;
    }

    public String getNhaXuatBan()
    {
        return nhaXuatBan;
    }

    // Setter
    public void setMaSach(String maSach)
    {
        this.maSach = maSach;
    }

    public void setNgayNhap(int ngay, int thang, int nam)
    {
        if (ngayHopLe(ngay, thang, nam))
        {
            this.ngay = ngay;
            this.thang = thang;
            this.nam = nam;
        }
        else
        {
            System.out.println("  -> Ngày nhập " + ngay + "/" + thang + "/" + nam
                    + " không hợp lệ, giữ nguyên giá trị cũ.");
        }
    }

    public void setDonGia(double donGia)
    {
        if (donGiaHopLe(donGia))
        {
            this.donGia = donGia;
        }
        else
        {
            System.out.println("  -> Đơn giá " + donGia + " không hợp lệ (phải > 0), giữ nguyên giá trị cũ.");
        }
    }

    public void setSoLuong(int soLuong)
    {
        if (soLuongHopLe(soLuong))
        {
            this.soLuong = soLuong;
        }
        else
        {
            System.out.println("  -> Số lượng " + soLuong + " không hợp lệ (phải >= 0), giữ nguyên giá trị cũ.");
        }
    }

    public void setNhaXuatBan(String nhaXuatBan)
    {
        this.nhaXuatBan = nhaXuatBan;
    }

    // Thành tiền cơ bản của lớp cha: số lượng * đơn giá
    // Các lớp con sẽ ghi đè (override) phương thức này theo cách tính riêng
    public double thanhTien()
    {
        return soLuong * donGia;
    }

    // In các thông tin chung, lớp con sẽ ghi đè và in thêm thông tin riêng
    public void hienThiThongTin()
    {
        System.out.println("Mã sách       : " + maSach);
        System.out.println("Ngày nhập     : " + getNgayNhap());
        System.out.println("Đơn giá       : " + dinhDangTien(donGia));
        System.out.println("Số lượng      : " + soLuong);
        System.out.println("Nhà xuất bản  : " + nhaXuatBan);
    }

    // Đổi các thông tin chung thành một dòng chữ để lưu file
    public String toChuoi()
    {
        return maSach + "|" + ngay + "|" + thang + "|" + nam + "|" + donGia + "|" + soLuong + "|" + nhaXuatBan;
    }
}
