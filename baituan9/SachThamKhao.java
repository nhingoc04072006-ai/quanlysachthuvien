// Lớp con: kế thừa lớp Sach bằng từ khóa extends
public class SachThamKhao extends Sach
{
    // Thuộc tính riêng của sách tham khảo: thuế (số tiền)
    private double thue;

    // Constructor: gọi constructor của lớp cha bằng super(...) rồi gán thuộc tính riêng
    public SachThamKhao(String maSach, int ngay, int thang, int nam, double donGia, int soLuong,
            String nhaXuatBan, double thue)
    {
        super(maSach, ngay, thang, nam, donGia, soLuong, nhaXuatBan);
        setThue(thue);
    }

    public static boolean thueHopLe(double thue)
    {
        return thue >= 0;
    }

    public double getThue()
    {
        return thue;
    }

    public void setThue(double thue)
    {
        if (thueHopLe(thue))
        {
            this.thue = thue;
        }
        else
        {
            System.out.println("  -> Thuế " + thue + " không hợp lệ (phải >= 0), giữ nguyên giá trị cũ.");
        }
    }

    // Ghi đè: thành tiền = số lượng * đơn giá + thuế
    @Override
    public double thanhTien()
    {
        return getSoLuong() * getDonGia() + thue;
    }

    // Ghi đè: in thông tin chung của lớp cha rồi in thêm thông tin riêng
    @Override
    public void hienThiThongTin()
    {
        System.out.println("Loại sách     : Sách tham khảo");
        super.hienThiThongTin();
        System.out.println("Thuế          : " + dinhDangTien(thue));
        System.out.println("Thành tiền    : " + dinhDangTien(thanhTien()));
    }

    // Ghi đè: thêm thuế vào cuối dòng của lớp cha
    @Override
    public String toChuoi()
    {
        return super.toChuoi() + "|" + thue;
    }

    // Đọc một dòng chữ từ file và tạo lại đối tượng, trả về null nếu dòng sai
    public static SachThamKhao tuChuoi(String chuoi)
    {
        String[] p = chuoi.split("\\|");
        if (p.length != 8)
        {
            return null;
        }
        try
        {
            int ngay = Integer.parseInt(p[1]);
            int thang = Integer.parseInt(p[2]);
            int nam = Integer.parseInt(p[3]);
            double donGia = Double.parseDouble(p[4]);
            int soLuong = Integer.parseInt(p[5]);
            double thue = Double.parseDouble(p[7]);
            if (!ngayHopLe(ngay, thang, nam) || !donGiaHopLe(donGia) || !soLuongHopLe(soLuong)
                    || !thueHopLe(thue))
            {
                return null;
            }
            return new SachThamKhao(p[0], ngay, thang, nam, donGia, soLuong, p[6], thue);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
