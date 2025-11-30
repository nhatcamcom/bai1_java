

import java.util.ArrayList;     // Để sử dụng danh sách động
import java.util.Scanner;       // Để nhập liệu từ bàn phím
import java.util.Comparator;    // Để sắp xếp

// CÂU 1: Lớp trừu tượng TaiKhoan
abstract class TaiKhoan {
    // Các thuộc tính
    protected String soTaiKhoan;
    protected String tenChuTK;
    protected double soDu;

    // Constructors
    public TaiKhoan() {
        // Constructor không tham số
    }

    public TaiKhoan(String soTaiKhoan, String tenChuTK, double soDu) {
        this.soTaiKhoan = soTaiKhoan;
        this.tenChuTK = tenChuTK;
        this.soDu = soDu;
    }

    // Các phương thức getter/setter (chỉ tạo getter cần thiết)
    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public double getSoDu() {
        return soDu;
    }

    // Phương thức abstract là tinhPhiDuyTri()
    public abstract double tinhPhiDuyTri();

    // Phương thức (concrete) getSoDuThucTe()
    public double getSoDuThucTe() {
        // Gọi phương thức trừu tượng (tính đa hình)
        return soDu - tinhPhiDuyTri();
    }

    // Phương thức nạp chồng toString()
    @Override
    public String toString() {
        // Định dạng số cho dễ đọc (không có .00)
        String sDu = String.format("%,.0f", soDu);
        String phi = String.format("%,.0f", tinhPhiDuyTri());
        String sDuThucTe = String.format("%,.0f", getSoDuThucTe());

        return "STK: " + soTaiKhoan +
                ", Tên: " + tenChuTK +
                ", Số dư: " + sDu + " VND" +
                ", Phí duy trì: " + phi + " VND" +
                ", Số dư thực tế: " + sDuThucTe + " VND";
    }

    // Phương thức nhập thông tin chung
    public void nhapThongTin(Scanner sc) {
        System.out.print("Nhập số tài khoản: ");
        soTaiKhoan = sc.nextLine();
        System.out.print("Nhập tên chủ tài khoản: ");
        tenChuTK = sc.nextLine();
        System.out.print("Nhập số dư: ");
        // Xử lý nhập số an toàn
        try {
            soDu = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nhập sai định dạng số, tự động gán là 0.");
            soDu = 0;
        }
    }
}

// CÂU 2: Các lớp kế thừa

// Lớp TaiKhoanThuong
class TaiKhoanThuong extends TaiKhoan {
    // Thuộc tính riêng: phiCoDinh (gán cứng = 50000)
    // Dùng final để đảm bảo là hằng số
    private final double phiCoDinh = 50000;

    // Constructor để khởi tạo dữ liệu mẫu
    public TaiKhoanThuong(String soTaiKhoan, String tenChuTK, double soDu) {
        // Gọi constructor của lớp cha
        super(soTaiKhoan, tenChuTK, soDu);
    }

    // Constructor rỗng để nhập liệu
    public TaiKhoanThuong() {
        super();
    }

    // Ghi đè phương thức tinhPhiDuyTri()
    @Override
    public double tinhPhiDuyTri() {
        return phiCoDinh;
    }

    // Ghi đè toString() để thêm nhãn (tùy chọn)
    @Override
    public String toString() {
        return "[TK Thường] " + super.toString();
    }

    // Ghi đè phương thức nhập (không có thuộc tính riêng nên chỉ cần gọi cha)
    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
    }
}

// Lớp TaiKhoanVip
class TaiKhoanVip extends TaiKhoan {
    // Thuộc tính riêng
    private final double soDuToiThieuVip = 100000000;

    // Constructor để khởi tạo dữ liệu mẫu
    public TaiKhoanVip(String soTaiKhoan, String tenChuTK, double soDu) {
        // Gọi constructor của lớp cha
        super(soTaiKhoan, tenChuTK, soDu);
    }

    // Constructor rỗng để nhập liệu
    public TaiKhoanVip() {
        super();
    }

    // Ghi đè phương thức tinhPhiDuyTri()
    @Override
    public double tinhPhiDuyTri() {
        // Kiểm tra số dư (lấy từ thuộc tính 'soDu' của lớp cha)
        if (soDu < soDuToiThieuVip) {
            return 10000;   // Phí 10.000
        } else {
            return 0;       // Miễn phí
        }
    }

    // Ghi đè toString() để thêm nhãn (tùy chọn)
    @Override
    public String toString() {
        return "[TK VIP] " + super.toString();
    }

    // Ghi đè phương thức nhập (không có thuộc tính riêng nên chỉ cần gọi cha)
    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
    }
}

// CÂU 3: Lớp chính chứa hàm main và menu chương trình
public class nhat123 {
    // Khai báo Scanner để nhập liệu
    private static Scanner sc = new Scanner(System.in);
    // Khai báo Danh sách kiểu Lớp Cha (Tính đa hình)
    private static ArrayList<TaiKhoan> danhSachTK = new ArrayList<>();
    // Khởi tạo static để thêm dữ liệu mẫu
    static {
        // Thêm 2 tài khoản thường
        danhSachTK.add(new TaiKhoanThuong("TK001", "Nguyen Van A", 5000000));
        danhSachTK.add(new TaiKhoanThuong("TK002", "Le Thi C", 1500000));
        
        // Thêm 2 tài khoản VIP
        // VIP 1: Dưới 100tr (bị tính phí 10k)
        danhSachTK.add(new TaiKhoanVip("VIP001", "Tran Van B", 80000000));
        // VIP 2: Trên 100tr (miễn phí)
        danhSachTK.add(new TaiKhoanVip("VIP002", "Pham Thi D", 200000000));
    }

    public static void main(String[] args) {
        int luaChon;
        do {
            // Hiển thị menu
            System.out.println("\n--- CHƯƠNG TRÌNH QUẢN LÝ TÀI KHOẢN ---");
            System.out.println("1. Nhập danh sách tài khoản (Thường và Vip)");
            System.out.println("2. Xuất thông tin danh sách tài khoản");
            System.out.println("3. Xuất danh sách TK Vip không bị tính phí");
            System.out.println("4. Sắp xếp TK giảm dần theo Số dư thực tế");
            System.out.println("5. Xoá tài khoản theo STK");
            System.out.println("6. Thoát chương trình");
            System.out.print("Mời bạn chọn chức năng: ");

            try {
                luaChon = Integer.parseInt(sc.nextLine()); // Đọc lựa chọn
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số.");
                luaChon = -1;   // Gán giá trị sai để lặp lại menu
            }

            // Xử lý lựa chọn
            switch (luaChon) {
                case 1:
                    nhapDanhSach();
                    break;
                case 2:
                    xuatDanhSach();
                    break;
                case 3:
                    xuatVipKhongPhi();
                    break;
                case 4:
                    sapXepGiamDan();
                    break;
                case 5:
                    xoaTaiKhoan();
                    break;
                case 6:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        } while (luaChon != 6);
    }

    // 1. Chức năng Nhập danh sách
    private static void nhapDanhSach() {
        System.out.println("--- NHẬP TÀI KHOẢN ---");
        System.out.print("Bạn muốn nhập Tài khoản Thường (1) hay Tài khoản Vip (2)? ");
        int loaiTK = Integer.parseInt(sc.nextLine());

        TaiKhoan tk; // Khai báo biến kiểu lớp cha

        if (loaiTK == 1) {
            System.out.println("Nhập thông tin TK Thường:");
            tk = new TaiKhoanThuong();  // Tạo đối tượng lớp con
        } else {
            System.out.println("Nhập thông tin TK Vip:");
            tk = new TaiKhoanVip();     // Tạo đối tượng lớp con
        }

        tk.nhapThongTin(sc);    // Gọi phương thức nhập (tính đa hình)
        danhSachTK.add(tk);     // Thêm vào danh sách (kiểu lớp cha)
        System.out.println("Thêm tài khoản thành công!");
    }

    // 2. Chức năng Xuất danh sách
    private static void xuatDanhSach() {
        System.out.println("--- DANH SÁCH TÀI KHOẢN NGÂN HÀNG ---");
        if (danhSachTK.isEmpty()) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        // Duyệt danh sách và gọi toString() (tính đa hình)
        for (TaiKhoan tk : danhSachTK) {
            System.out.println(tk.toString());
        }
    }

    // 3. Chức năng Xuất TK Vip không bị tính phí
    private static void xuatVipKhongPhi() {
        System.out.println("--- DANH SÁCH TK VIP KHÔNG TÍNH PHÍ (Số dư >= 100tr) ---");
        boolean timThay = false;    // Biến kiểm tra
        for (TaiKhoan tk : danhSachTK) {
            // Kiểm tra xem đối tượng có phải là TaiKhoanVip không
            if (tk instanceof TaiKhoanVip) {
                // Kiểm tra điều kiện phí (gọi đa hình)
                if (tk.tinhPhiDuyTri() == 0) {
                    System.out.println(tk.toString());
                    timThay = true;
                }
            }
        }
        if (!timThay) {
            System.out.println("Không tìm thấy tài khoản Vip nào thỏa mãn.");
        }
    }

    // 4. Chức năng Sắp xếp giảm dần theo Số dư thực tế
    private static void sapXepGiamDan() {
        if (danhSachTK.isEmpty()) {
            System.out.println("Danh sách rỗng, không thể sắp xếp.");
            return;
        }
        danhSachTK.sort(Comparator.comparingDouble(TaiKhoan::getSoDuThucTe).reversed());
        System.out.println("--- DANH SÁCH SAU KHI SẮP XẾP GIẢM DẦN THEO SỐ DƯ THỰC TẾ ---");
        xuatDanhSach(); // Gọi lại hàm xuất danh sách
    }

    // 5. Chức năng Xoá tài khoản theo STK
    private static void xoaTaiKhoan() {
        System.out.println("--- XOÁ TÀI KHOẢN ---");
        System.out.print("Nhập Số Tài Khoản (STK) cần xoá: ");
        String stkCanXoa = sc.nextLine();
        boolean daXoa = danhSachTK.removeIf(tk -> tk.getSoTaiKhoan().equalsIgnoreCase(stkCanXoa));
        if (daXoa) {
            System.out.println("Đã xoá tài khoản có STK: " + stkCanXoa);
        } else {
            System.out.println("Không tìm thấy tài khoản có STK: " + stkCanXoa);
        }        
    }
}