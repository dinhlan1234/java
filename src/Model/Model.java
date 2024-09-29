package Model;

import java.util.ArrayList;

public class Model {
	public ArrayList<NhanVien> dsnv;
	public ArrayList<SanPham> dssp;
	private ArrayList<HoaDon> dshd;
	private String luachon;
	
	public Model() {
		this.dsnv = new ArrayList<NhanVien>();
		this.dssp = new ArrayList<SanPham>();
		this.dshd = new ArrayList<HoaDon>();
	}
	
	
	public Model(ArrayList<NhanVien> dsnv, ArrayList<SanPham> dssp, ArrayList<HoaDon> dshd, String luachon) {
		this.dsnv = dsnv;
		this.dssp = dssp;
		this.dshd = dshd;
		this.luachon = luachon;
	}


	public ArrayList<NhanVien> getNv() {
		return dsnv;
	}

	public void setNv(ArrayList<NhanVien> nv) {
		this.dsnv = nv;
	}

	public ArrayList<SanPham> getSp() {
		return dssp;
	}

	public void setSp(ArrayList<SanPham> sp) {
		this.dssp = sp;
	}

	public ArrayList<HoaDon> getHd() {
		return dshd;
	}

	public void setHd(ArrayList<HoaDon> hd) {
		this.dshd = hd;
	}

	public String getLuachon() {
		return luachon;
	}

	public void setLuachon(String luachon) {
		this.luachon = luachon;
	}

	// Quản Lý Nhân Viên
	public void themNV(NhanVien nhanvien) {
		dsnv.add(nhanvien);
	}
	public void chinhSuaNV(NhanVien nhanvien) {
		dsnv.remove(nhanvien);
		dsnv.add(nhanvien);
	}
	public void xoaNV(NhanVien nhanvien) {
		dsnv.remove(nhanvien);
	}
	// Quản Lý Sản Phẩm
	
	public void themSP(SanPham sanpham) {
		dssp.add(sanpham);
	}

	public void chinhsuaSP(SanPham sanpham) {
		dssp.remove(sanpham);
		dssp.add(sanpham);
	}

	public void xoaSP(SanPham sanpham) {
		dssp.remove(sanpham);
	}
	
// kiểm tra sản phẩm tồn tại hay chưa
	
	public boolean kiemtraSPdatontaichua(SanPham sp) {
		for (SanPham sanpham : dssp) {
			if(sanpham.getMaSP() == sp.getMaSP()) {
				return false;
			}
		}
		return true;
	}
	
// kiểm tra nhân viên tồn tại hay chưa
	
	public boolean kiemtraNVdatontaihaychua(NhanVien nv) {
		for(NhanVien nhanvien : dsnv) {
			if(nhanvien.getCccd().equals(nv.getCccd()+"")) {
				return true;
			}
		}
		return false;
	}
}
