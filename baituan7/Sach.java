public class Sach
{
    // Yêu cầu 1: các thuộc tính của lớp Sach
    String maSach;
    String tieuDe;
    String tacGia;
    int namXuatBan;
    int soLuong;

    // Phương thức khởi tạo: dùng để tạo đối tượng Sach bằng từ khóa new
    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong)
    {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
    }

    // Yêu cầu 2: in ra tất cả thông tin chi tiết của một cuốn sách
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
    // Trả về null nếu dòng bị sai định dạng
    public static Sach tuChuoi(String chuoi)
    {
        String[] p = chuoi.split("\\|");
        if (p.length != 5)
        {
            return null;
        }
        try
        {
            return new Sach(p[0], p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4]));
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
