public class MonHoc {
    private String maMonHoc;
    private String tenMonHoc;
    private int soTinChi;
    private double hocPhiMotTin;

    public MonHoc() {}

    public MonHoc(String maMonHoc, String tenMonHoc, int soTinChi, double hocPhiMotTin) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.hocPhiMotTin = hocPhiMotTin;
    }

    public String getMaMonHoc() { return maMonHoc; }
    public void setMaMonHoc(String maMonHoc) { this.maMonHoc = maMonHoc; }

    public String getTenMonHoc() { return tenMonHoc; }
    public void setTenMonHoc(String tenMonHoc) { this.tenMonHoc = tenMonHoc; }

    public int getSoTinChi() { return soTinChi; }
    public void setSoTinChi(int soTinChi) { this.soTinChi = soTinChi; }

    public double getHocPhiMotTin() { return hocPhiMotTin; }
    public void setHocPhiMotTin(double hocPhiMotTin) { this.hocPhiMotTin = hocPhiMotTin; }

    public double tinhHocPhi() {
        return soTinChi * hocPhiMotTin;
    }

    @Override
    public String toString() {
        return maMonHoc + " - " + tenMonHoc + " - " + soTinChi + " tín chỉ - Học phí: " + tinhHocPhi();
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyMonHoc {
    public static void main(String[] args) {
        ArrayList<MonHoc> ds = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("1. Nhập thông tin môn học");
            System.out.println("2. Hiển thị danh sách môn học");
            System.out.println("3. Tìm môn học");
            System.out.println("4. Sửa thông tin môn học");
            System.out.println("5. Xóa môn học");
            System.out.println("6. Thoát");
            System.out.print("Chọn: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    System.out.print("Mã môn: ");
                    String ma = sc.nextLine();
                    System.out.print("Tên môn: ");
                    String ten = sc.nextLine();
                    System.out.print("Số tín chỉ: ");
                    int tc = sc.nextInt();
                    System.out.print("Học phí 1 tín: ");
                    double hp = sc.nextDouble();
                    sc.nextLine();
                    ds.add(new MonHoc(ma, ten, tc, hp));
                    break;

                case 2:
                    for (MonHoc m : ds) System.out.println(m);
                    break;

                case 3:
                    System.out.print("Nhập mã môn cần tìm: ");
                    String tim = sc.nextLine();
                    for (MonHoc m : ds)
                        if (m.getMaMonHoc().equals(tim))
                            System.out.println(m);
                    break;

                case 4:
                    System.out.print("Nhập mã môn cần sửa: ");
                    String sua = sc.nextLine();
                    for (MonHoc m : ds)
                        if (m.getMaMonHoc().equals(sua)) {
                            System.out.print("Tên mới: ");
                            m.setTenMonHoc(sc.nextLine());
                            System.out.print("Số tín mới: ");
                            m.setSoTinChi(sc.nextInt());
                            System.out.print("Học phí mới: ");
                            m.setHocPhiMotTin(sc.nextDouble());
                            sc.nextLine();
                        }
                    break;

                case 5:
                    System.out.print("Nhập mã môn muốn xóa: ");
                    String xoa = sc.nextLine();
                    ds.removeIf(m -> m.getMaMonHoc().equals(xoa));
                    break;
            }

        } while (chon != 6);
    }
}
