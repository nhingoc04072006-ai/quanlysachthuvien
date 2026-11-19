import java.time.Year;

public class Sach
{
    // Yêu cầu 1: tất cả thuộc tính đều khai báo private
    // Bên ngoài lớp Sach không thể truy cập trực tiếp, chỉ dùng được qua getter và setter
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private int namXuatBan;
    private int soLuong;

    // Yêu cầu 4: constructor có tham số đầy đủ
    // Dùng setter cho năm xuất bản và số lượng để dữ liệu sai không lọt vào đối tượng
    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong)
    {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        setNamXuatBan(namXuatBan);
        setSoLuong(soLuong);
    }

    // Các hàm kiểm tra dùng chung cho setter và cho phần nhập dữ liệu
    // Năm xuất bản hợp lệ: lớn hơn 0 và không lớn hơn năm hiện tại
    public static boolean namXuatBanHopLe(int namXuatBan)
    {
        int namHienTai = Year.now().getValue();
        return namXuatBan > 0 && namXuatBan <= namHienTai;
    }

    // Số lượng hợp lệ: không âm
    public static boolean soLuongHopLe(int soLuong)
    {
        return soLuong >= 0;
    }

    // Yêu cầu 2: getter (lấy giá trị thuộc tính)
    public String getMaSach()
    {
        return maSach;
    }

    public String getTieuDe()
    {
        return tieuDe;
    }

    public String getTacGia()
    {
        return tacGia;
    }

    public int getNamXuatBan()
    {
        return namXuatBan;
    }

    public int getSoLuong()
    {
        return soLuong;
    }

    // Yêu cầu 2: setter (thay đổi giá trị thuộc tính)
    public void setMaSach(String maSach)
    {
        this.maSach = maSach;
    }

    public void setTieuDe(String tieuDe)
    {
        this.tieuDe = tieuDe;
    }

    public void setTacGia(String tacGia)
    {
        this.tacGia = tacGia;
    }

    // Yêu cầu 3: đảm bảo năm xuất bản > 0 và <= năm hiện tại
    public void setNamXuatBan(int namXuatBan)
    {
        if (namXuatBanHopLe(namXuatBan))
        {
            this.namXuatBan = namXuatBan;
        }
        else
        {
            System.out.println("  -> Năm xuất bản " + namXuatBan + " không hợp lệ, giữ nguyên giá trị cũ ("
                    + this.namXuatBan + ").");
        }
    }

    // Yêu cầu 3: đảm bảo số lượng không âm
    public void setSoLuong(int soLuong)
    {
        if (soLuongHopLe(soLuong))
        {
            this.soLuong = soLuong;
        }
        else
        {
            System.out.println("  -> Số lượng " + soLuong + " không hợp lệ (phải >= 0), giữ nguyên giá trị cũ ("
                    + this.soLuong + ").");
        }
    }

    // In ra tất cả thông tin chi tiết của một cuốn sách
    public void hienThiThongTin()
    {
        System.out.println("Mã sách      : " + maSach);
        System.out.println("Tiêu đề      : " + tieuDe);
        System.out.println("Tác giả      : " + tacGia);
        System.out.println("Năm xuất bản : " + namXuatBan);
        System.out.println("Số lượng     : " + soLuong);
    }

    // Đổi đối tượng thành một dòng chữ để lưu vào file
    public String toChuoi()
    {
        return maSach + "|" + tieuDe + "|" + tacGia + "|" + namXuatBan + "|" + soLuong;
    }

    // Đọc một dòng chữ từ file và tạo lại đối tượng Sach
    // Trả về null nếu dòng sai định dạng hoặc dữ liệu không hợp lệ
    public static Sach tuChuoi(String chuoi)
    {
        String[] p = chuoi.split("\\|");
        if (p.length != 5)
        {
            return null;
        }
        try
        {
            int nam = Integer.parseInt(p[3]);
            int sl = Integer.parseInt(p[4]);
            if (!namXuatBanHopLe(nam) || !soLuongHopLe(sl))
            {
                return null;
            }
            return new Sach(p[0], p[1], p[2], nam, sl);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}