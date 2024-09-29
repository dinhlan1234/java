package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Model.NhanVien;
import Model.SanPham;
import View.View;

public class Controller implements ActionListener {
	private View v;

	public Controller(View v) {
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String src = e.getActionCommand();
		if (src.equals("Bắt Đầu")) {

			this.v.setContentPane(this.v.panel);
			this.v.validate();
		} else if (src.equals("Product")) {
			this.v.setContentPane(this.v.panel_cacloaidouong);
			this.v.validate();
			this.v.khongchinhsua3();
			try {
				this.v.write("productStaff");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// this.v.updatetable_qlsp();
			// this.v.capnhatdulieu_sanpham();

		} else if (src.equals("Exit")) {
			System.exit(0);
		} else if (src.equals("Quay Trở Lại")) {

			this.v.setContentPane(this.v.panel_admin);
			this.v.validate();
			Action action = new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					v.setContentPane(v.panel_admin);
					v.validate();
				}
			};
			KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
			this.v.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "ESCAPE");
			this.v.getRootPane().getActionMap().put("ESCAPE", action);
		} else if (src.equals("Quay lại")) {

			Action action = new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					v.setContentPane(v.panel_admin);
					v.validate();
				}
			};
			KeyStroke keyStroke = KeyStroke.getKeyStroke("ESCAPE");
			this.v.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "ESCAPE");
			this.v.getRootPane().getActionMap().put("ESCAPE", action);
			this.v.setContentPane(this.v.panel_admin);
			this.v.validate();
		} else if (src.equals("Coffee Staff")) {
			this.v.setContentPane(this.v.nhanvien);
			// this.v.capnhatdulieu();
			this.v.validate();
			this.v.khongchinhsua4();
			try {
				this.v.write("staff");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// this.v.capnhatbang_qlnv();
		} else if (src.equals("Payments and Invoices")) {
			this.v.setContentPane(this.v.thanhtoan);
			this.v.validate();
			this.v.sanpham_congthanhtoan();
		} else if (src.equals("Add Product")) {
			this.v.deleteform_qlsp();
		} else if (src.equals("Save Product")) {

			try {
				String tenSP = this.v.textField_tenSP.getText();
				int giaSP = Integer.valueOf(this.v.textField_giaSP.getText());
				int maSP = Integer.valueOf(this.v.textField_maSP.getText());
				String ghichu = this.v.textField_ghichuSP.getText();
				boolean trangthai = true;
				if (this.v.rdn_hd.isSelected()) {
					trangthai = true;
				} else if (this.v.rdn_khonghd.isSelected()) {
					trangthai = false;
				}
				String image = this.v.txt_pathFile.getText();
				SanPham sp = new SanPham(maSP, tenSP, giaSP, trangthai, ghichu, image);
				this.v.insertandupdate_qlsp(sp);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (src.equals("Update Product")) {
			this.v.product_repair_qlsp();

		} else if (src.equals("Delete Product")) {
			this.v.delete_qlsp();
			this.v.updatetable_qlsp();
		} else if (src.equals("Thêm NV")) {
			this.v.xoaform_qlnv();
		} else if (src.equals("Lưu NV")) {
			try {
				String tenNV = this.v.textField_tenNV.getText();
				int tuoiNV = Integer.valueOf(this.v.textField_tuoiNV.getText() + "");
				String ngaysinh = this.v.textField_ngaysinhNV.getText();
				SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
				format.setLenient(false);
				Date date = null;
				try {
					date = format.parse(ngaysinh);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(this.v, "Định Dạng Ngày Sinh Không Đúng");
				}
				String cccd = this.v.textField_cccdNV.getText();
				String sdt = this.v.textField_sdtNV.getText();
				String ngaylam = this.v.textField_ngaylamNV.getText();
				SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
				Date date2 = null;
				try {
					date2 = form.parse(ngaylam);
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(this.v, "Định Dạng Ngày Làm Không Đúng");
				}
				NhanVien nv = new NhanVien(tenNV, date, tuoiNV, cccd, sdt, date2);
				this.v.themhoaccapnhatNhanVien(nv);
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(this.v, "Có Lỗi Trong Lúc Định Dạng");
			}
		} else if (src.equals("Sửa NV")) {
			this.v.suaNV();
		} else if (src.equals("Xoá NV")) {
			this.v.xoaNV();
		} else if (src.equals("Chọn")) {
			this.v.chon_douong();
		} else if (src.equals("Reset")) {
			this.v.reset_thanhtoan();
		} else if (src.equals("Tạo Hóa Đơn")) {
			this.v.taohoadon();
		} else if (src.equals("Thanh Toán")) {
			this.v.tinhtientralai();
			Action action = new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					v.tinhtientralai();
				}
			};
			KeyStroke keyStroke = KeyStroke.getKeyStroke("ENTER");
			this.v.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "ENTER");
			this.v.getRootPane().getActionMap().put("ENTER", action);
		} else if (src.equals("Statistical")) {
			this.v.setContentPane(this.v.thongke);
			this.v.validate();
			try {
				this.v.write("thongke");
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				this.v.write("chienluoc");
			} catch (Exception e2) {
				// TODO: handle exception
			}
//			this.v.hiencacchienluoc();
//   		this.v.bangthongke();
		} else if (src.equals("Thêm Chiến Lược")) {
			this.v.themchienluoc();
		} else if (src.equals("Lưu Chiến Lược")) {
			this.v.luuchienluoc();
		} else if (src.equals("Đăng Ký")) {
			this.v.setContentPane(this.v.dangki);
			this.v.validate();
		} else if (src.equals("Trở Về Màn Hình Đăng Nhập")) {
			this.v.setContentPane(this.v.batdau);
			this.v.validate();
		} else if (src.equals("Forgot Password")) {
			this.v.setContentPane(this.v.quenmatkhau);
			this.v.validate();
		} else if (src.equals("Xác Nhận")) {
			this.v.dangkytaikhoan();
		} else if (src.equals("dĐăng Nhập")) {
//			//this.v.dangnhap();
//			this.v.kiemtradangnhap();

		} else if (src.equals("Images")) {
			this.v.images();
		} else if (src.equals("Feedback")) {
			this.v.setContentPane(this.v.rcm_admin);
			this.v.validate();
			this.v.themsp_jlist();
		} else if (src.equals("Register")) {
			this.v.setContentPane(this.v.taotaikhoanNV);
			this.v.validate();
			this.v.setBounds(100, 100, 331, 252);
			this.v.setLocationRelativeTo(null);

		} else if (src.equals("Quay Về")) {
			this.v.setContentPane(this.v.panel_admin);
			this.v.validate();
			this.v.setBounds(100, 100, 724, 518);
			this.v.setLocationRelativeTo(null);
		} else if (src.equals("Tạo")) {
			this.v.taotkNV();
		} else if (src.equals("Đọc Truyện")) {
			this.v.setContentPane(this.v.doctruyen);
			this.v.validate();
			this.v.setBounds(100, 100, 904, 518);
			this.v.setLocationRelativeTo(null);
		} else if (src.equals("Quay về trang recommend")) {
			this.v.setContentPane(this.v.recommend_khachhang);
			this.v.validate();
			this.v.setBounds(100, 100, 724, 518);
			this.v.setLocationRelativeTo(null);
		} else if (src.equals("Xuất")) {
			this.v.xuatXML();
		}
		if (e.getSource() instanceof JComboBox) {
			JComboBox comboBox = (JComboBox) e.getSource();
			String selectedItem = (String) comboBox.getSelectedItem();
			if (selectedItem.equals("Tập 1:Sao sáng xứ Thanh")) {
				System.out.println("tap1");
				this.v.tap1();
			} else if (selectedItem.equals("Tập 2:Đất nứt con bọ hung")) {
				this.v.tap2();
			} else if (selectedItem.equals("Tập 3:Cúng Thành Hoàng")) {
				this.v.tap3();
			} else if (selectedItem.equals("Tập 4:Miệng kẻ sang")) {
				this.v.tap4();
			} else if (selectedItem.equals("Tập 5:Ghẹo cô hàng nước")) {
				this.v.tap5();
			} else if (selectedItem.equals("Tập 6:Đệ nhất danh họa")) {
				this.v.tap6();
			}
		}

	}

}
