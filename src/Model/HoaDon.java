package Model;

public class HoaDon {
	private String ten;
	private int soluong;
	private int giaTien;
	public HoaDon(String ten, int soluong, int giaTien) {
		this.ten = ten;
		this.soluong = soluong;
		this.giaTien = giaTien;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public int getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}
}
