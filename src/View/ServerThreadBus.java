package View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.mindrot.jbcrypt.BCrypt;

public class ServerThreadBus {
	View v;

	public ServerThreadBus(View v) {
		this.v = v;
	}
	

	private List<ServerThread> listServerThreads;

	public List<ServerThread> getListServerThreads() {
		return listServerThreads;
	}

	public ServerThreadBus() {
		listServerThreads = new ArrayList<>();
	}

	public void add(ServerThread serverThread) {
		listServerThreads.add(serverThread);
	}

	public void mutilCastSend(String message) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			try {
				serverThread.write(message);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void boardCast(int id, String message) {

		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				continue;
			} else {
				try {
					serverThread.write(message);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public int getLength() {
		return listServerThreads.size();
	}

	public void sendOnlineList() {
		String res = "";
		List<ServerThread> threadbus = Server.serverThreadBus.getListServerThreads();
		for (ServerThread serverThread : threadbus) {
			res += serverThread.getClientNumber() + "-";
		}
		Server.serverThreadBus.mutilCastSend("update-online-list" + "," + res);
	}

	public void sendMessageToPersion(int id, String message) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				try {
					serverThread.write("global-message" + "," + message);
					break;
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void remove(int id) {
		for (int i = 0; i < Server.serverThreadBus.getLength(); i++) {
			if (Server.serverThreadBus.getListServerThreads().get(i).getClientNumber() == id) {
				Server.serverThreadBus.listServerThreads.remove(i);
			}
		}
	}

//    for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
//		if (serverThread.getClientNumber() == id) {
//			try {
//				serverThread.write("done");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public void check(int id, String tk, String mk) {
		boolean check = false;
		if (check == false) {
			try {
				Connection conn = null;
				PreparedStatement stmt = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/taikhoanadmin", "root", "2010");
				String sql = "SELECT * FROM taikhoanadmin WHERE tendangnhap=? AND matkhau=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tk);
				stmt.setString(2, mk);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
						if (serverThread.getClientNumber() == id) {
							try {
								serverThread.write("done1" + "," + tk);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					check = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (check == false) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/taikhoannhanvien", "root",
						"2010");
				String sql = "SELECT matkhau FROM taikhoannhanvien WHERE tendangnhap=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tk);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String passCSDL = rs.getString("matkhau");
					boolean validate = BCrypt.checkpw(mk, passCSDL);
					if (validate == true) {
						for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
							if (serverThread.getClientNumber() == id) {
								try {
									serverThread.write("done2" + "," + tk);
									check = true;
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (check == false) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/taikhoan", "root", "2010");
				String sql = "SELECT matkhau FROM taikhoan WHERE taikhoan=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tk);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String passCSDL = rs.getString("matkhau");
					boolean validate = BCrypt.checkpw(mk, passCSDL);
					if (validate == true) {
						for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
							if (serverThread.getClientNumber() == id) {
								try {
									serverThread.write("done3");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				} else {
					for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
						if (serverThread.getClientNumber() == id) {
							serverThread.write("matkhaukhongchinhxac");
						}}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void update(int id) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sanpham", "root", "2010");
			String sql = "SELECT TenSanPham FROM sanpham";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String tenSP = rs.getString("TenSanPham");
				for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
					if (serverThread.getClientNumber() == id) {
						try {
							serverThread.write("product" + "," + tenSP);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selected(int id, String tenSP) {
		String path = "";
		String giaSP = "";
		String cmt = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sanpham", "root", "2010");
			String sql = "SELECT img FROM sanpham WHERE TenSanPham = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tenSP);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				path = rs.getString("img");

//				ImageIcon imageIcon = new ImageIcon(path);
//				lbl_anh.setIcon(imageIcon);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sanpham", "root", "2010");
			String sql = "SELECT GiaSanPham FROM sanpham WHERE TenSanPham = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tenSP);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				giaSP = rs.getInt("GiaSanPham") + "";
				// textArea_gia.setText(" " + giaSP + "vnđ");

			}
		} catch (Exception e3) {
			e3.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/comment_khachhang", "root", "2010");
			String sql = "SELECT CMT FROM comment_khachhang WHERE tenSP = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tenSP);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cmt = rs.getString("CMT");
				// textArea_CMT.setText(cmt);
			}
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				try {
					serverThread.write("selected" + "," + path + "," + giaSP + "," + cmt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void productStaff(int id) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				Connection conn = null;
				try {
					String url = "jdbc:mysql://localhost:3306/sanpham";
					conn = DriverManager.getConnection(url, "root", "2010");
					String sql = "SELECT * FROM sanpham";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						int maSP = rs.getInt("MaSanPham");
						String tenSP = rs.getString("TenSanPham");
						int giaSP = rs.getInt("GiaSanPham");
						boolean trangthai = rs.getBoolean("TrangThai");
						String ghichu = rs.getString("GhiChu");
						try {
							serverThread.write("productStaff" + "," + maSP + "," + tenSP + "," + giaSP + "," + trangthai
									+ "," + ghichu);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void tongtien(int id, int tongtien, String ngay) {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/hoadon";
			conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "INSERT INTO hoadon (TongTien, NgayTinhHoaDon) VALUES (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tongtien);
			pstmt.setString(2, ngay);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
	}

	public void thongke(int id) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				Connection conn = null;
				try {
					String url = "jdbc:mysql://localhost:3306/hoadon";
					conn = DriverManager.getConnection(url, "root", "2010");

					String query = "SELECT NgayTinhHoaDon, SUM(TongTien) as TotalMoney FROM hoadon GROUP BY NgayTinhHoaDon";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						String ngayBan = rs.getString("NgayTinhHoaDon");
						String tongTien = rs.getString("TotalMoney");
						serverThread.write("thongke" + "," + tongTien + "," + ngayBan);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void staff(int id) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				Connection conn = null;
				try {
					String url = "jdbc:mysql://localhost:3306/nhanvien";
					conn = DriverManager.getConnection(url, "root", "2010");
					String sql = "SELECT * FROM nhanvien";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						String tenNV = rs.getString("tennhanvien");
						String ngaysinh = rs.getString("ngaysinh");
						int tuoi = rs.getInt("tuoi");
						String cccd = rs.getString("cccd");
						String sdt = rs.getString("sdt");
						String ngaylam = rs.getString("ngaylam");
						// String tk = rs.getString("taikhoan");
						// String mk = rs.getString("matkhau");
						serverThread.write("staff" + "," + tenNV + "," + ngaysinh + "," + tuoi + "," + cccd + "," + sdt
								+ "," + ngaylam);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void chienluoc(int id) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				Connection conn = null;
				try {
					String url = "jdbc:mysql://localhost:3306/chienluocc";
					conn = DriverManager.getConnection(url, "root", "2010");
					String sql = "SELECT cacchienluoc FROM chienluoc WHERE id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 1);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						String chienluoc = rs.getString("cacchienluoc");
						serverThread.write("chienluoc" + "," + chienluoc);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void dangkytaikhoannhanvien(int id, String tentk, String email, String hash) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				boolean check = false;
				try {
					String url = "jdbc:mysql://localhost:3306/taikhoannhanvien";
					Connection conn = DriverManager.getConnection(url, "root", "2010");
					String sql = "INSERT INTO taikhoannhanvien (tendangnhap, matkhau, email) VALUES (?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, tentk);
					pstmt.setString(2, hash);
					pstmt.setString(3, email);
					pstmt.executeUpdate();
					check = true;
					serverThread.write("dangkytaikhoannhanvien" + "," + check);
				} catch (Exception e) {
					check = false;
					JOptionPane.showMessageDialog(this.v, "Tên Tài Khoản Đã Tồn Tại");
				}
			}
		}

	}

	public void kiemtranhanvientontaihaychua(int id, String tennv, String ngaysinh, String tuoi, String cccd,
			String sdt, String ngaylam) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				Connection conn = null;
				try {
					String url = "jdbc:mysql://localhost:3306/nhanvien";
					conn = DriverManager.getConnection(url, "root", "2010");
					String sql = "SELECT tennhanvien FROM nhanvien WHERE cccd = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cccd);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						System.out.println("sua nhan vien");
						Connection connnn = null;
						try {
							String urlll = "jdbc:mysql://localhost:3306/nhanvien";
							connnn = DriverManager.getConnection(urlll, "root", "2010");
							String sqlll = "UPDATE nhanvien SET tennhanvien = ?, ngaysinh = ?, tuoi = ?, sdt = ?, ngaylam = ? WHERE cccd = ?";
							PreparedStatement pstmttt = connnn.prepareStatement(sqlll);
							pstmttt.setString(1, tennv);
							pstmttt.setString(2, ngaysinh);
							pstmttt.setInt(3, Integer.parseInt(tuoi));
							pstmttt.setString(4, sdt);
							pstmttt.setString(5, ngaylam);
							pstmttt.setString(6, cccd);
							pstmttt.executeUpdate();

						} catch (SQLException e) {
							System.out.println(e.getMessage());
						}

					} else {
						System.out.println("them nhan vien");
						try {
							Connection connn = null;
							String urll = "jdbc:mysql://localhost:3306/nhanvien";
							connn = DriverManager.getConnection(urll, "root", "2010");
							String sqll = "INSERT INTO nhanvien (tennhanvien, ngaysinh, tuoi, cccd, sdt, ngaylam) VALUES (?,?,?,?,?,?)";
							PreparedStatement pstmtt = connn.prepareStatement(sqll);
							pstmtt.setString(1, tennv);
							pstmtt.setString(2, ngaysinh);
							pstmtt.setInt(3, Integer.parseInt(tuoi));
							pstmtt.setString(4, cccd);
							pstmtt.setString(5, sdt);
							pstmtt.setString(6, ngaylam);
							pstmtt.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this.v.nhanvien, "Nhân Viên Đã Tồn Tại");
						}
					}

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

	public void delete(int id, String cccd) {
		for (ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if (serverThread.getClientNumber() == id) {
				Connection conn = null;
				try {
					String url = "jdbc:mysql://localhost:3306/nhanvien";
					conn = DriverManager.getConnection(url, "root", "2010");
					String sql = "DELETE FROM nhanvien WHERE cccd = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cccd);
					pstmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
