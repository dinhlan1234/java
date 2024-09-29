package Model;

import java.util.Objects;

public class SanPham {
	private int maSP;
	private String tenSP;
	private int giaSP;
	private boolean trangThai;
	private String ghichu;
	private String image;
	public SanPham() {
		
	}
	public SanPham(int maSP, String tenSP, int giaSP, boolean trangThai, String ghichu, String image) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaSP = giaSP;
		this.trangThai = trangThai;
		this.ghichu = ghichu;
		this.image = image;
	}
	public int getMaSP() {
		return maSP;
	}
	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getGiaSP() {
		return giaSP;
	}
	public void setGiaSP(int giaSP) {
		this.giaSP = giaSP;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", giaSP=" + giaSP + ", trangThai=" + trangThai
				+ ", ghichu=" + ghichu + ", image=" + image + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(ghichu, giaSP, image, maSP, tenSP, trangThai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(ghichu, other.ghichu) && giaSP == other.giaSP && Objects.equals(image, other.image)
				&& maSP == other.maSP && Objects.equals(tenSP, other.tenSP) && trangThai == other.trangThai;
	}
	
}
