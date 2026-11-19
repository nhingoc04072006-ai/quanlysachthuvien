// Lớp con: kế thừa lớp Sach bằng từ khóa extends
public class SachGiaoKhoa extends Sach
{
    // Hai giá trị cho phép của tình trạng
    public static final String MOI = "Mới";
    public static final String CU = "Cũ";

    // Thuộc tính riêng của sách giáo khoa
    private String tinhTrang = MOI;

    // Constructor: gọi constructor của lớp cha bằng super(...) rồi gán thuộc tính riêng
    public SachGiaoKhoa(String maSach, int ngay, int thang, int nam, double donGia, int soLuong,
            String nhaXuatBan, String tinhTrang)
    {
        super(maSach, ngay, thang, nam, donGia, soLuong, nhaXuatBan);
        setTinhTrang(tinhTrang);
    }

    public static boolean tinhTrangHopLe(String tinhTrang)
    {
        return tinhTrang.equalsIgnoreCase(MOI) || tinhTrang.equalsIgnoreCase(CU);
    }

    public String getTinhTrang()
    {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang)
    {
        if (tinhTrangHopLe(tinhTrang))
        {
            if (tinhTrang.equalsIgnoreCase(MOI))
            {
                this.tinhTrang = MOI;
            }
            else
            {
                this.tinhTrang = CU;
            }
        }
        else
        {
            System.out.println("  -> Tình trạng \"" + tinhTrang + "\" không hợp lệ (chỉ Mới hoặc Cũ), giữ nguyên giá trị cũ.");
        }
    }

    // Ghi đè: sách mới thì số lượng * đơn giá, sách cũ thì số lượng * đơn giá * 50%
    // Lớp con không truy cập trực tiếp thuộc tính private của lớp cha nên dùng getter
    @Override
    public double thanhTien()
    {
        double tien = getSoLuong() * getDonGia();
        if (tinhTrang.equals(MOI))
        {
            return tien;
        }
        else
        {
            return tien * 0.5;
        }
    }

    // Ghi đè: gọi super.hienThiThongTin() để in thông tin chung, rồi in thêm thông tin riêng
    @Override
    public void hienThiThongTin()
    {
        System.out.println("Loại sách     : Sách giáo khoa");
        super.hienThiThongTin();
        System.out.println("Tình trạng    : " + tinhTrang);
        System.out.println("Thành tiền    : " + dinhDangTien(thanhTien()));
    }

    // Ghi đè: thêm tình trạng vào cuối dòng của lớp cha
    @Override
    public String toChuoi()
    {
        return super.toChuoi() + "|" + tinhTrang;
    }

    // Đọc một dòng chữ từ file và tạo lại đối tượng, trả về null nếu dòng sai
    public static SachGiaoKhoa tuChuoi(String chuoi)
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
            if (!ngayHopLe(ngay, thang, nam) || !donGiaHopLe(donGia) || !soLuongHopLe(soLuong)
                    || !tinhTrangHopLe(p[7]))
            {
                return null;
            }
            return new SachGiaoKhoa(p[0], ngay, thang, nam, donGia, soLuong, p[6], p[7]);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
