package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String tenNV;
	private Date ngaySinh;
	private int tuoi;
	private String cccd;
	private String sdt;
	private Date ngayLam;
	
	
	public NhanVien(String tenNV, Date ngaySinh, int tuoi, String cccd, String sdt, Date ngayLam) {
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.tuoi = tuoi;
		this.cccd = cccd;
		this.sdt = sdt;
		this.ngayLam = ngayLam;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getNgaySinh() {
		SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
		String format = form.format(ngaySinh);
		return format;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public String getCccd() {
		return cccd;
	}
	public void setCccd(String cccd) {
		this.cccd = cccd;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getNgayLam() {
		SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
		String format = form.format(ngayLam);
		return format;
	}
	public void setNgayLam(Date ngayLam) {
		this.ngayLam = ngayLam;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cccd, ngayLam, ngaySinh, sdt, tenNV, tuoi);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return cccd == other.cccd && Objects.equals(ngayLam, other.ngayLam) && Objects.equals(ngaySinh, other.ngaySinh)
				&& sdt == other.sdt && Objects.equals(tenNV, other.tenNV) && tuoi == other.tuoi;
	}
}
