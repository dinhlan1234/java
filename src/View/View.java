package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mindrot.jbcrypt.BCrypt;

import Controller.Controller;
import Model.Model;
import Model.NhanVien;
import Model.SanPham;

public class View extends JFrame {
	private String ten;
	public static volatile ServerThreadBus serverThreadBus;
	public static Socket socketOfServer;
	private int x;
	private int id;

	public static String sos;
	private Thread thread;
	private BufferedWriter os;
	private BufferedReader is;
	private Socket socketOfClient;
	private List<String> onlineList;
	private String name;

	JList<String> list;
	JList<String> list_chat_admin;
	private Map<String, JTextArea> userTextAreas = new HashMap<>();
	public Map<String, JTextArea> userTextArea = new HashMap<>();
	static ServerSocket serverSocket_client;
	static Socket socket_client;
	static DataInputStream dis_client;
	static DataOutputStream dos_client;

	DefaultListModel<String> model_chat_admin = new DefaultListModel<>();
	DefaultListModel<String> model_admin = new DefaultListModel<>();
	DefaultListModel<String> model = new DefaultListModel<>();
	private Model m;
	public JPanel thanhtoan;
	public JPanel nhanvien;
	public JPanel dangki;
	public JPanel khungmenu;
	public JPanel batdau;
	public JPanel panel;
	public JPanel douong;
	public JTextField textField_tenSP;
	public JTextField textField_giaSP;
	public JTextField textField_maSP;
	public JTable table_cacloaidouong;
	public JPanel panel_cacloaidouong;
	public JTextField textField_ghichuSP;
	public JTextField textField_tenNV;
	public JTextField textField_tuoiNV;
	public JTextField textField_ngaysinhNV;
	public JTextField textField_cccdNV;
	public JTextField textField_sdtNV;
	public JTextField textField_ngaylamNV;
	public JTextField textField_ngaytt;
	public JTextField textField_tkdtt;
	public JTextField textField_tltt;
	public JTextArea textArea_hdtt;
	public JRadioButton rdn_hd;
	public JRadioButton rdn_khonghd;
	private JTable table_qlnv;
	public JTable table_sanpham_thanhtoan;
	public JPopupMenu jpopupmenu;
	private JTable table_thanhtoan_tinhtoan;
	public JPanel recommend_khachhang;
	public JPanel thongke;
	private JTextField textField_time_thongke;
	private JTable table_thongke;
	private JTextArea textArea_ghichienluoc;
	private JTextArea textArea_luuchienluoc;
	private JTextField textField_username;

	public JTextField textField_usernameDK;
	public JTextField textField_emailDK;
	public JTextField textField_passDK;

	public JPanel quenmatkhau;
	private JTextField textField_usernameQMK;
	private JTextField textField_emailQMK;
	private JTextField textField_passQMK;
	private JTextField textField_mãQMK;

	JPasswordField textField_password;

	public JPanel admin;
	public JPanel panel_admin;
	public JPanel thongke_nv;
	private JTextField textField_time_thongke_nv;
	private JTable table_thongke_nv;
	private JTextArea textArea_luuchienluoc_nv;
	private JTextArea textArea_ghichienluoc_nv;
	private JComponent btnExit_nv;
	public JTextArea txt_pathFile;
	public JLabel img;
	private JTextField textField_tensp;
	private JTextField textField_anh;
	private JComponent textField_gia;
	private JTextArea textArea_ten;
	private JLabel lbl_anh;
	private JTextArea textArea_gia;
	public JTextArea textArea_tentk;
	private JRadioButton rdbtn_1s;
	// private JLabel lbl_anh;
	private JRadioButton rdbtn_2s;
	private JRadioButton rdbtn_3s;
	private JRadioButton rdbtn_4s;
	private JRadioButton rdbtn_5s;
	private JTextArea textArea_vietCmt;
	private JTextArea textArea_CMT;
	public JPanel rcm_admin;
	private JTextArea textField_tensp_rcmt_admin;
	private JLabel label_anh_admin;
	private JTextArea gia_rcm_admin;
	private JTextArea textArea_CMT_rcm_admin;
	public JPanel taotaikhoanNV;
	private JTextField textField_taikhoanNV;
	private JTextField textField_emailNV;
	private JTextField textField_matkhauNV;
	public JPanel chat_admin;
	public JTextArea textArea_tenKhachHang;
	public JTextArea textArea_chat_admin;
	private JTextArea textArea_guiTN;
	private JTextArea textArea_thongtinKH;
	private JTextField textField_tenKH_dangky;
	private JTextField textField_sdtDK;
	private JPanel chatkh;
	private JTextArea textArea_chat_kh;
	private JTextArea textArea_Mess_kh;
	private JRadioButton rdbtn_staff;
	private JRadioButton rdbtn_Manager;
	private JTextArea textArea_chat_tenKH;
	private JPanel chat;
	private JTextArea jTextArea1;
	private JTextField jTextField1;
	public JTextArea textArea_chat_ten;
	private JComboBox<String> jComboBox1;
	private JLabel jLabel3;
	private Timer timerr;
	private JLabel lebel_chuchayngang;
	private JTextArea txt_thongtin_chat;
	public JPanel doctruyen;
	private JTextPane textPane;
	private JButton btn_qlrcm;

	public View() {
		this.m = new Model();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 518);
		this.setLocationRelativeTo(null);
		batdau = new JPanel();
		batdau.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionListener ac = new Controller(this);
		setContentPane(batdau);
		batdau.setLayout(null);

//p1
		JLabel label_myaccount = new JLabel("My Account");
		label_myaccount.setForeground(new Color(192, 192, 192));
		label_myaccount.setFont(new Font("Tahoma", Font.BOLD, 17));
		label_myaccount.setBounds(494, 154, 114, 33);
		batdau.add(label_myaccount);

		JLabel label_username = new JLabel("UserName");
		label_username.setForeground(new Color(192, 192, 192));
		label_username.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_username.setBounds(430, 199, 122, 25);
		batdau.add(label_username);

		JLabel label_password = new JLabel("PassWord");
		label_password.setForeground(new Color(192, 192, 192));
		label_password.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_password.setBounds(430, 246, 105, 25);
		batdau.add(label_password);

		textField_username = new JTextField();
		textField_username.setBounds(528, 202, 128, 22);
		batdau.add(textField_username);
		textField_username.setColumns(10);

		textField_password = new JPasswordField();
		textField_password.setEchoChar('•');
		textField_password.setColumns(10);
		textField_password.setBounds(528, 249, 128, 22);
		batdau.add(textField_password);

		JButton button_quenmk = new JButton("Forgot Password");
		button_quenmk.addActionListener(ac);
		button_quenmk.setFont(new Font("Tahoma", Font.PLAIN, 10));

		button_quenmk.setBackground(SystemColor.menu);
		button_quenmk.setBounds(586, 282, 114, 16);
		batdau.add(button_quenmk);

		JButton button_dangnhap = new JButton("Đăng Nhập");
		button_dangnhap.addActionListener(e -> {
			String tk = this.textField_username.getText();
			String mk = this.textField_password.getText();
			try {
				write("check" + "," + "" + tk + "," + mk);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		this.getRootPane().setDefaultButton(button_dangnhap);
		button_dangnhap.setBackground(SystemColor.menu);
		button_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_dangnhap.setBounds(441, 309, 94, 16);
		batdau.add(button_dangnhap);

		JButton button_dangki = new JButton("Đăng Ký");
		button_dangki.addActionListener(ac);
		button_dangki.setBackground(SystemColor.menu);
		button_dangki.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_dangki.setBounds(562, 309, 94, 16);
		batdau.add(button_dangki);

		JLabel lblNewLabel_anh1 = new JLabel("");
		lblNewLabel_anh1.setBounds(0, 0, 708, 483);
		lblNewLabel_anh1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(View.class.getResource("background-ly-cafe-den_090050156.jpg")))); //
		batdau.add(lblNewLabel_anh1);

		JLabel lblNewLabel_anh2 = new JLabel("");
//		lblNewLabel_anh2
//				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("menu_jpg.png"))));     //
		lblNewLabel_anh2.setBounds(381, 0, 317, 175);
		batdau.add(lblNewLabel_anh2);

		JButton button_exitgiaodien = new JButton("Exit");
		button_exitgiaodien.addActionListener(ac);
		button_exitgiaodien.setOpaque(true);
		button_exitgiaodien.setBackground(SystemColor.inactiveCaptionBorder);
//		button_exitgiaodien
//				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("exit.png"))));      //
		button_exitgiaodien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		button_exitgiaodien.setBounds(603, 440, 105, 43);
		batdau.add(button_exitgiaodien);

//p2
		khungmenu = new JPanel();
		khungmenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		khungmenu.setLayout(null);

		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 140, 479);
		khungmenu.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_banghieu = new JLabel("Bom Coffee");
//		lblNewLabel_banghieu
//				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("cafe_jpg.jpg"))));
		lblNewLabel_banghieu.setOpaque(true);
		lblNewLabel_banghieu.setBackground(SystemColor.inactiveCaption);
		lblNewLabel_banghieu.setFont(new Font("Bernard MT Condensed", Font.ITALIC, 19));
		lblNewLabel_banghieu.setBounds(0, 0, 140, 59);
		panel.add(lblNewLabel_banghieu);

		JButton btnNewButton = new JButton("Product");
		btnNewButton.addActionListener(ac);
//		btnNewButton
//				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("drink.png"))));
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnNewButton.setBounds(0, 107, 140, 59);
		panel.add(btnNewButton);

		JButton btnHan = new JButton("Payments and Invoices");
		btnHan.addActionListener(ac);
//		btnHan.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("bill.png"))));
		btnHan.setBackground(SystemColor.inactiveCaption);
		btnHan.setOpaque(true);
		btnHan.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnHan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHan.setBounds(0, 177, 140, 59);
		panel.add(btnHan);

		JButton btnNhnVin = new JButton("Coffee Staff");
		btnNhnVin.addActionListener(ac);
//		btnNhnVin.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("staff.png"))));
		btnNhnVin.setBackground(SystemColor.inactiveCaption);
		btnNhnVin.setOpaque(true);
		btnNhnVin.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnNhnVin.setBounds(0, 247, 140, 59);
		panel.add(btnNhnVin);

		JButton btnThngK = new JButton("Statistical");
		btnThngK.addActionListener(ac);
//		btnThngK.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("thongke.png"))));
		btnThngK.setBackground(SystemColor.inactiveCaption);
		btnThngK.setOpaque(true);
		btnThngK.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnThngK.setBounds(0, 317, 140, 59);
		panel.add(btnThngK);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(ac);
//		btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("exit.png"))));
		btnExit.setBackground(SystemColor.inactiveCaption);
		btnExit.setOpaque(true);
		btnExit.setFont(new Font("Verdana", Font.BOLD, 13));
		btnExit.setBounds(0, 420, 140, 59);
		panel.add(btnExit);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(0, 53, 140, 44);
		panel.add(lblNewLabel_1);

		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ActionListener updateClockAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				lblNewLabel_1.setText(form.format(date));
			}
		};
		Timer t = new Timer(1000, updateClockAction);
		t.start();

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 399, 140, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 53, 140, 2);
		panel.add(separator_1);

//p3

		douong = new JPanel();
		douong.setLayout(null);
		panel_cacloaidouong = new JPanel();
		panel_cacloaidouong.setBounds(0, 0, 698, 479);
		douong.add(panel_cacloaidouong);
		panel_cacloaidouong.setLayout(null);

		JLabel label_cacloaidouong = new JLabel("Các Loại Đồ Uống");
		label_cacloaidouong.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 27));
		label_cacloaidouong.setBounds(150, 5, 233, 43);
		panel_cacloaidouong.add(label_cacloaidouong);

		JSeparator s = new JSeparator();
		s.setBounds(0, 46, 698, 2);
		panel_cacloaidouong.add(s);

		JLabel label_tenSP = new JLabel("Product's Name");
		label_tenSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tenSP.setBounds(20, 59, 196, 32);
		panel_cacloaidouong.add(label_tenSP);

		JLabel label_giaSP = new JLabel("Product Price");
		label_giaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_giaSP.setBounds(20, 93, 113, 32);
		panel_cacloaidouong.add(label_giaSP);

		JLabel label_maSP = new JLabel("Product Code");
		label_maSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_maSP.setBounds(20, 125, 113, 32);
		panel_cacloaidouong.add(label_maSP);

		txt_pathFile = new JTextArea();
		txt_pathFile.setEditable(false);
		txt_pathFile.setBounds(385, 166, 288, 22);
		panel_cacloaidouong.add(txt_pathFile);

		textField_tenSP = new JTextField();
		textField_tenSP.setBounds(226, 67, 259, 20);
		panel_cacloaidouong.add(textField_tenSP);
		textField_tenSP.setColumns(10);

		textField_giaSP = new JTextField();
		textField_giaSP.setColumns(10);
		textField_giaSP.setBounds(226, 101, 259, 20);
		panel_cacloaidouong.add(textField_giaSP);

		textField_maSP = new JTextField();
		textField_maSP.setColumns(10);
		textField_maSP.setBounds(226, 133, 259, 20);
		panel_cacloaidouong.add(textField_maSP);

		table_cacloaidouong = new JTable();
		table_cacloaidouong.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Product Code", "Product's Name", "Product Price", "Active Status", "Note" }));
		table_cacloaidouong.setBounds(98, 309, 300, 300);

		JScrollPane scrollPane = new JScrollPane(table_cacloaidouong);
		scrollPane.setBounds(0, 199, 698, 206);
		panel_cacloaidouong.add(scrollPane);

		JButton button_xoaSP = new JButton("Delete Product");
		button_xoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_xoaSP.setFont(new Font("Tahoma", Font.BOLD, 9));
		button_xoaSP.addActionListener(ac);
		button_xoaSP.setBounds(20, 430, 95, 38);
		panel_cacloaidouong.add(button_xoaSP);

		JButton button_themSP = new JButton("Add Product");
		button_themSP.setFont(new Font("Tahoma", Font.BOLD, 9));
		button_themSP.addActionListener(ac);
		button_themSP.setBounds(133, 430, 93, 38);
		panel_cacloaidouong.add(button_themSP);

		JButton button_suaSP = new JButton("Update Product");
		button_suaSP.setFont(new Font("Tahoma", Font.BOLD, 9));
		button_suaSP.addActionListener(ac);
		button_suaSP.setBounds(248, 430, 93, 38);
		panel_cacloaidouong.add(button_suaSP);

		JButton button_luuSP = new JButton("Save Product");
		button_luuSP.setFont(new Font("Tahoma", Font.BOLD, 9));
		button_luuSP.addActionListener(ac);
		button_luuSP.registerKeyboardAction(
				button_luuSP.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
		button_luuSP.setBounds(364, 430, 93, 38);
		panel_cacloaidouong.add(button_luuSP);

		rdn_hd = new JRadioButton("Hoạt Động");
		rdn_hd.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdn_hd.setBounds(482, 412, 109, 23);
		panel_cacloaidouong.add(rdn_hd);

		rdn_khonghd = new JRadioButton("Không Hoạt Động");
		rdn_khonghd.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdn_khonghd.setBounds(482, 438, 131, 23);
		panel_cacloaidouong.add(rdn_khonghd);

		ButtonGroup btn = new ButtonGroup();
		btn.add(rdn_hd);
		btn.add(rdn_khonghd);

		JLabel label_ghichu = new JLabel("Note");
		label_ghichu.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_ghichu.setBounds(518, 64, 113, 23);
		panel_cacloaidouong.add(label_ghichu);

		textField_ghichuSP = new JTextField();
		textField_ghichuSP.setBounds(518, 93, 155, 60);
		panel_cacloaidouong.add(textField_ghichuSP);
		textField_ghichuSP.setColumns(10);

		JButton button_quaytrolai = new JButton("Quay Trở Lại");
		button_quaytrolai.setFont(new Font("Tahoma", Font.BOLD, 9));

		button_quaytrolai.addActionListener(ac);
//		button_quaytrolai.setIcon(
//			new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("quaytrolai.png"))));
		button_quaytrolai.setBounds(619, 447, 79, 32);
		panel_cacloaidouong.add(button_quaytrolai);

		JButton button_luuSP_1 = new JButton("Images");
		button_luuSP_1.addActionListener(ac);
		button_luuSP_1.setFont(new Font("Tahoma", Font.BOLD, 9));
		button_luuSP_1.setBounds(226, 164, 113, 24);
		panel_cacloaidouong.add(button_luuSP_1);

		img = new JLabel("Images");
		img.setFont(new Font("Tahoma", Font.BOLD, 15));
		img.setBounds(20, 163, 113, 32);
		panel_cacloaidouong.add(img);

//p4	- nhân viên

		nhanvien = new JPanel();
		nhanvien.setLayout(null);
		JPanel panel_qlnv = new JPanel();
		panel_qlnv.setBounds(0, 0, 708, 479);
		nhanvien.add(panel_qlnv);
		panel_qlnv.setLayout(null);

		JLabel label_quanlynhanvien = new JLabel("Quản Lý Nhân Viên");
		label_quanlynhanvien.setFont(new Font("Segoe UI", Font.BOLD, 27));
		label_quanlynhanvien.setBounds(224, 0, 250, 48);
		panel_qlnv.add(label_quanlynhanvien);

		JSeparator separator_qlnv = new JSeparator();
		separator_qlnv.setBounds(0, 46, 708, 2);
		panel_qlnv.add(separator_qlnv);

		table_qlnv = new JTable();
		table_qlnv.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00EAn NV", "Ng\u00E0y Sinh",
				"Tu\u1ED5i", "CCCD", "S\u0110T", "Ng\u00E0y L\u00E0m" }));

		table_qlnv.setBounds(267, 197, 1, 1);

		JScrollPane scrollPane_qlnv = new JScrollPane(table_qlnv);
		scrollPane_qlnv.setBounds(10, 52, 698, 179);
		panel_qlnv.add(scrollPane_qlnv);

		JLabel label_tenNV = new JLabel("Tên Nhân Viên");
		label_tenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tenNV.setBounds(10, 262, 114, 35);
		panel_qlnv.add(label_tenNV);

		JLabel label_tuoiNV = new JLabel("Tuổi Nhân Viên");
		label_tuoiNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tuoiNV.setBounds(10, 320, 114, 35);
		panel_qlnv.add(label_tuoiNV);

		JLabel label_ngaysinhNV = new JLabel("Ngày Sinh");
		label_ngaysinhNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_ngaysinhNV.setBounds(10, 384, 114, 35);
		panel_qlnv.add(label_ngaysinhNV);

		JLabel label_cccdNV = new JLabel("CCCD");
		label_cccdNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_cccdNV.setBounds(380, 262, 114, 35);
		panel_qlnv.add(label_cccdNV);

		JLabel label_sdtNV = new JLabel("SĐT");
		label_sdtNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_sdtNV.setBounds(380, 320, 114, 35);
		panel_qlnv.add(label_sdtNV);

		JLabel label_ngaylamNV = new JLabel("Ngày Làm");
		label_ngaylamNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_ngaylamNV.setBounds(380, 384, 114, 35);
		panel_qlnv.add(label_ngaylamNV);

		textField_tenNV = new JTextField();
		textField_tenNV.setBounds(134, 271, 167, 20);
		panel_qlnv.add(textField_tenNV);
		textField_tenNV.setColumns(10);

		textField_tuoiNV = new JTextField();
		textField_tuoiNV.setColumns(10);
		textField_tuoiNV.setBounds(134, 329, 167, 20);
		panel_qlnv.add(textField_tuoiNV);

		textField_ngaysinhNV = new JTextField();
		textField_ngaysinhNV.setColumns(10);
		textField_ngaysinhNV.setBounds(134, 393, 167, 20);
		panel_qlnv.add(textField_ngaysinhNV);

		textField_cccdNV = new JTextField();
		textField_cccdNV.setColumns(10);
		textField_cccdNV.setBounds(504, 271, 167, 20);
		panel_qlnv.add(textField_cccdNV);

		textField_sdtNV = new JTextField();
		textField_sdtNV.setColumns(10);
		textField_sdtNV.setBounds(504, 329, 167, 20);
		panel_qlnv.add(textField_sdtNV);

		textField_ngaylamNV = new JTextField();
		textField_ngaylamNV.setColumns(10);
		textField_ngaylamNV.setBounds(504, 393, 167, 20);
		panel_qlnv.add(textField_ngaylamNV);

		JButton btnNewButton_xoaNV = new JButton("Xoá NV");
		btnNewButton_xoaNV.addActionListener(ac);
		btnNewButton_xoaNV.setBounds(17, 445, 107, 38);
		panel_qlnv.add(btnNewButton_xoaNV);

		JButton button_themNV = new JButton("Thêm NV");
		button_themNV.addActionListener(ac);
		button_themNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_themNV.setBounds(133, 443, 107, 38);
		panel_qlnv.add(button_themNV);

		JButton button_capnhatNV = new JButton("Sửa NV");
		button_capnhatNV.addActionListener(ac);
		button_capnhatNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_capnhatNV.setBounds(250, 443, 107, 38);
		panel_qlnv.add(button_capnhatNV);

		JButton button_luuNV = new JButton("Lưu NV");
		button_luuNV.addActionListener(ac);
		// button_luuNV.registerKeyboardAction(button_luuNV.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,
		// 0)), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);

		button_luuNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_luuNV.setBounds(367, 443, 107, 38);
		panel_qlnv.add(button_luuNV);
		JButton button_quaytrolai_qlnv = new JButton("Quay Trở Lại");
//		button_quaytrolai_qlnv.setIcon(
//				new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("quaytrolai.png"))));
		button_quaytrolai_qlnv.addActionListener(ac);
		button_quaytrolai_qlnv.setBounds(619, 447, 72, 32);
		panel_qlnv.add(button_quaytrolai_qlnv);
		JButton button_xuat = new JButton("Xuất");
		button_xuat.addActionListener(ac);
		button_xuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_xuat.setBounds(481, 443, 107, 38);
		panel_qlnv.add(button_xuat);

//p5 - thanh toán

		thanhtoan = new JPanel();
		thanhtoan.setLayout(null);

		JLabel label_thanhtoan = new JLabel("Cổng Thanh Toán");
		label_thanhtoan.setFont(new Font("Segoe UI", Font.BOLD, 27));
		label_thanhtoan.setBounds(234, 0, 235, 52);
		thanhtoan.add(label_thanhtoan);

		JSeparator separator_thanhtoan = new JSeparator();
		separator_thanhtoan.setBounds(33, 50, 654, 2);
		thanhtoan.add(separator_thanhtoan);

		table_sanpham_thanhtoan = new JTable();
		table_sanpham_thanhtoan.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Sản Phẩm", "Trạng Thái", "Ghi Chú" }));
		table_sanpham_thanhtoan.setBounds(98, 319, 1, 1);

		JScrollPane scrollPane_sanphamthanhtoan = new JScrollPane(table_sanpham_thanhtoan);
		scrollPane_sanphamthanhtoan.setBounds(10, 242, 341, 237);
		thanhtoan.add(scrollPane_sanphamthanhtoan);

		table_thanhtoan_tinhtoan = new JTable();
		table_thanhtoan_tinhtoan
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Tên", "SL", "Giá" }));
		table_thanhtoan_tinhtoan.setBounds(88, 259, 1, 1);

		JScrollPane scrollPane_thanhtoan = new JScrollPane(table_thanhtoan_tinhtoan);
		scrollPane_thanhtoan.setBounds(10, 58, 341, 129);
		thanhtoan.add(scrollPane_thanhtoan);

		JLabel lblTinKhcha = new JLabel("Tiền Khách Đưa:");
		lblTinKhcha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTinKhcha.setBounds(361, 303, 125, 29);
		thanhtoan.add(lblTinKhcha);

		JLabel lblThiLi = new JLabel("Thối Lại:");
		lblThiLi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThiLi.setBounds(361, 357, 93, 29);
		thanhtoan.add(lblThiLi);

		JButton button_thanhtoan = new JButton("Thanh Toán");
		button_thanhtoan.addActionListener(ac);
		button_thanhtoan.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_thanhtoan.setBounds(443, 412, 107, 29);
		thanhtoan.add(button_thanhtoan);

		JButton button_taohoadon = new JButton("Tạo Hóa Đơn");
		button_taohoadon.addActionListener(ac);
		button_taohoadon.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_taohoadon.setBounds(564, 90, 127, 32);
		thanhtoan.add(button_taohoadon);

		JButton button_reset = new JButton("Reset");
		button_reset.addActionListener(ac);
		button_reset.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_reset.setBounds(564, 169, 121, 32);
		thanhtoan.add(button_reset);

//		JButton button_quaytrolai_thanhtoan = new JButton("Quay Trở Lại");
////		button_quaytrolai_thanhtoan.setIcon(
////				new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("quaytrolai.png"))));
//		button_quaytrolai_thanhtoan.setBounds(623, 440, 79, 32);
//		thanhtoan.add(button_quaytrolai_thanhtoan);

		JLabel label_hoadontt = new JLabel("Hóa Đơn");
		label_hoadontt.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_hoadontt.setBounds(409, 49, 93, 29);
		thanhtoan.add(label_hoadontt);

		textField_ngaytt = new JTextField();
		textField_ngaytt.setEditable(false);
		textField_ngaytt.setOpaque(true);
		textField_ngaytt.setBackground(SystemColor.menu);

		textField_ngaytt.setBounds(585, 23, 102, 23);
		thanhtoan.add(textField_ngaytt);
		textField_ngaytt.setColumns(10);

		SimpleDateFormat form_thanhtoan = new SimpleDateFormat("dd/MM/yyyy");
		ActionListener updatetime = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date_thanhtoan = new Date();
				textField_ngaytt.setText(form_thanhtoan.format(date_thanhtoan));
			}
		};
		Timer t_thanhtoan = new Timer(100, updatetime);
		t_thanhtoan.start();

		textField_tkdtt = new JTextField();
		textField_tkdtt.setColumns(10);
		textField_tkdtt.setBounds(508, 308, 125, 23);
		thanhtoan.add(textField_tkdtt);

		textField_tltt = new JTextField();
		textField_tltt.setEditable(false);
		textField_tltt.setColumns(10);
		textField_tltt.setBounds(508, 362, 125, 23);
		thanhtoan.add(textField_tltt);

		textArea_hdtt = new JTextArea();
		textArea_hdtt.setEditable(false);
		textArea_hdtt.setColumns(10);
		textArea_hdtt.setBounds(361, 90, 195, 202);
		thanhtoan.add(textArea_hdtt);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 0, 45, 22);
		thanhtoan.add(menuBar);

		JMenu jMenu = new JMenu("Menu");
		JMenuItem jMenuItem_Bill = new JMenuItem("Payment and Invoices");
		jMenuItem_Bill.addActionListener(ac);
		JMenuItem jMenuItem_TKe = new JMenuItem("Statisticall");
		jMenuItem_TKe.addActionListener(e -> {
			setContentPane(thongke_nv);
			validate();

			try {
				write("thongke");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		JMenuItem jMenuItem_Chat = new JMenuItem("Chat");
		jMenuItem_Chat.addActionListener(e -> {
			setContentPane(chat);
			validate();
			timerr.start();
			txt_thongtin_chat.setText("Hãy Nhớ Khách Hàng Là" + "\n" + "Thượng Đế Nhé");
			HienThiTinNhan();
			// setUpSocket();
		});
		JMenuItem jMenuItem_Exit = new JMenuItem("Exit");
		jMenuItem_Exit.addActionListener(e -> {
			System.exit(0);
		});
		jMenu.add(jMenuItem_Bill);
		jMenu.add(jMenuItem_TKe);
		jMenu.add(jMenuItem_Chat);
		jMenu.add(jMenuItem_Exit);

		menuBar.add(jMenu);

		// jpopupmenu

		jpopupmenu = new JPopupMenu();
		JMenuItem item_chon = new JMenuItem("Chọn");
		item_chon.addActionListener(ac);
		jpopupmenu.add(item_chon);

		table_sanpham_thanhtoan.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int r = table_sanpham_thanhtoan.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table_sanpham_thanhtoan.getRowCount()) {
					table_sanpham_thanhtoan.setRowSelectionInterval(r, r);
				} else {
					table_sanpham_thanhtoan.clearSelection();
				}

				int rowindex = table_sanpham_thanhtoan.getSelectedRow();
				if (rowindex < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					jpopupmenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

// thống kê - Admin

		thongke = new JPanel();
		thongke.setLayout(null);

		JLabel label_congthongke = new JLabel("Cổng Thống Kê");
		label_congthongke.setFont(new Font("Segoe UI", Font.BOLD, 27));
		label_congthongke.setBounds(234, 0, 205, 47);
		thongke.add(label_congthongke);

		JSeparator separator_thongke = new JSeparator();
		separator_thongke.setBounds(10, 45, 688, 2);
		thongke.add(separator_thongke);

		textField_time_thongke = new JTextField();
		textField_time_thongke.setBounds(577, 21, 121, 20);
		thongke.add(textField_time_thongke);
		textField_time_thongke.setColumns(10);

		table_thongke = new JTable();
		table_thongke.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_thongke.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Ngày Bán", "Tổng Tiền" }));
		table_thongke.setBounds(131, 186, 1, 1);

		JScrollPane scrollPane_thongke = new JScrollPane(table_thongke);
		scrollPane_thongke.setBounds(10, 58, 688, 183);
		thongke.add(scrollPane_thongke);

		textArea_luuchienluoc = new JTextArea();
		textArea_luuchienluoc.setLineWrap(true);
		textArea_luuchienluoc.setBounds(513, 252, 185, 216);
		thongke.add(textArea_luuchienluoc);
		JScrollPane scrollPane_chienluoc = new JScrollPane(textArea_luuchienluoc);
		scrollPane_chienluoc.setBounds(499, 252, 199, 216);
		thongke.add(scrollPane_chienluoc);

		JButton button_themchienluoc = new JButton("Thêm Chiến Lược");
		button_themchienluoc.addActionListener(ac);
		button_themchienluoc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button_themchienluoc.setBounds(10, 274, 109, 47);
		thongke.add(button_themchienluoc);

		JButton button_luuchienluoc = new JButton("Lưu Chiến Lược");
		button_luuchienluoc.addActionListener(ac);
		button_luuchienluoc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button_luuchienluoc.setBounds(10, 360, 109, 47);
		thongke.add(button_luuchienluoc);

		textArea_ghichienluoc = new JTextArea();
		textArea_ghichienluoc.setLineWrap(true);
		textArea_ghichienluoc.setBounds(170, 252, 165, 216);
		thongke.add(textArea_ghichienluoc);

		SimpleDateFormat form_thongke = new SimpleDateFormat("dd/MM/yyyy");

		ActionListener time_thongke = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date_thanhtoan = new Date();
				textField_time_thongke.setText(form_thongke.format(date_thanhtoan));
			}
		};
		Timer t_thongke = new Timer(100, time_thongke);
		t_thongke.start();

		btnExit.setBackground(SystemColor.inactiveCaption);
		btnExit.setOpaque(true);

		JButton button_exit_thongke = new JButton("Quay Trở Lại");
		button_exit_thongke.addActionListener(ac);
		button_exit_thongke.setBounds(0, 0, 51, 23);
		thongke.add(button_exit_thongke);
// Thống kê - nhân viên

		thongke_nv = new JPanel();
		thongke_nv.setLayout(null);

		JLabel label_congthongke_nv = new JLabel("Cổng Thống Kê");
		label_congthongke_nv.setFont(new Font("Segoe UI", Font.BOLD, 27));
		label_congthongke_nv.setBounds(234, 0, 205, 47);
		thongke_nv.add(label_congthongke_nv);

		JSeparator separator_thongke_nv = new JSeparator();
		separator_thongke_nv.setBounds(10, 45, 688, 2);
		thongke_nv.add(separator_thongke_nv);

		textField_time_thongke_nv = new JTextField();
		textField_time_thongke_nv.setBounds(577, 21, 121, 20);
		thongke_nv.add(textField_time_thongke_nv);
		textField_time_thongke_nv.setColumns(10);

		table_thongke_nv = new JTable();
		table_thongke_nv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_thongke_nv.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Ngày Bán", "Tổng Tiền" }));
		table_thongke_nv.setBounds(131, 186, 1, 1);

		JScrollPane scrollPane_thongke_nv = new JScrollPane(table_thongke_nv);
		scrollPane_thongke_nv.setBounds(10, 58, 688, 183);
		thongke_nv.add(scrollPane_thongke_nv);

//		textArea_luuchienluoc_nv = new JTextArea();
//		textArea_luuchienluoc_nv.setLineWrap(true);
//		textArea_luuchienluoc_nv.setBounds(513, 252, 185, 216);
//		thongke_nv.add(textArea_luuchienluoc_nv);
//		JScrollPane scrollPane_chienluoc_nv = new JScrollPane(textArea_luuchienluoc_nv);
//		scrollPane_chienluoc_nv.setBounds(499, 252, 199, 216);
//		thongke_nv.add(scrollPane_chienluoc_nv);

//		JButton button_themchienluoc_nv = new JButton("Thêm Chiến Lược");
//		button_themchienluoc_nv.addActionListener(ac);
//		button_themchienluoc_nv.setFont(new Font("Tahoma", Font.PLAIN, 9));
//		button_themchienluoc_nv.setBounds(10, 274, 109, 47);
//		thongke_nv.add(button_themchienluoc_nv);
//
//		JButton button_luuchienluoc_nv = new JButton("Lưu Chiến Lược");
//		button_luuchienluoc_nv.addActionListener(ac);
//		button_luuchienluoc_nv.setFont(new Font("Tahoma", Font.PLAIN, 9));
//		button_luuchienluoc_nv.setBounds(10, 360, 109, 47);
//		thongke_nv.add(button_luuchienluoc_nv);
//
//		textArea_ghichienluoc_nv = new JTextArea();
//		textArea_ghichienluoc_nv.setLineWrap(true);
//		textArea_ghichienluoc_nv.setBounds(170, 252, 165, 216);
//		thongke_nv.add(textArea_ghichienluoc_nv);

		SimpleDateFormat form_thongke_nv = new SimpleDateFormat("dd/MM/yyyy");

		ActionListener time_thongke_nv = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date_thanhtoan_nv = new Date();
				textField_time_thongke_nv.setText(form_thongke_nv.format(date_thanhtoan_nv));
			}
		};
		Timer t_thongke_nv = new Timer(100, time_thongke_nv);
		t_thongke_nv.start();

		// btnExit_nv.setBackground(SystemColor.inactiveCaption);
		// btnExit_nv.setOpaque(true);

//		JButton button_exit_thongke_nv = new JButton("Quay Trở Lại");
//		button_exit_thongke_nv.addActionListener(ac);
//		button_exit_thongke_nv.setBounds(0, 0, 51, 23);
//		thongke_nv.add(button_exit_thongke_nv);

		JMenuBar menuBar_nv = new JMenuBar();
		menuBar_nv.setBounds(10, 0, 45, 22);
		thongke_nv.add(menuBar_nv);

		JMenu jMenu_nv = new JMenu("Menu");
		JMenuItem jMenuItem_Bill_nv = new JMenuItem("Payments and Invoices");
		jMenuItem_Bill_nv.addActionListener(ac);
		jMenuItem_Bill_nv.addActionListener(ac);
		JMenuItem jMenuItem_TKe_nv = new JMenuItem("Chat");
		jMenuItem_TKe_nv.addActionListener(e -> {
			setContentPane(chat);
			validate();
			timerr.start();
			txt_thongtin_chat.setText("Hãy Nhớ Khách Hàng Là" + "\n" + "Thượng Đế Nhé");
			HienThiTinNhan();
			setUpSocket();
		});
		JMenuItem jMenuItem_Exit_nv = new JMenuItem("Exit");
		jMenuItem_Exit_nv.addActionListener(ac);

		jMenu_nv.add(jMenuItem_Bill_nv);
		jMenu_nv.add(jMenuItem_TKe_nv);
		jMenu_nv.add(jMenuItem_Exit_nv);

		menuBar_nv.add(jMenu_nv);

// giao diện đăng kí
		dangki = new JPanel();
		dangki.setLayout(null);

		JLabel lblngKTi = new JLabel("Đăng Ký Tài Khoản Khách Hàng");
		lblngKTi.setForeground(Color.GRAY);
		lblngKTi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblngKTi.setBounds(405, 11, 295, 28);
		dangki.add(lblngKTi);
		JLabel label_tenKH = new JLabel("Tên Của Bạn:");
		label_tenKH.setForeground(Color.GRAY);
		label_tenKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_tenKH.setBounds(405, 72, 102, 23);
		dangki.add(label_tenKH);
		textField_tenKH_dangky = new JTextField();
		textField_tenKH_dangky.setColumns(10);
		textField_tenKH_dangky.setBounds(544, 74, 154, 20);
		dangki.add(textField_tenKH_dangky);
		JLabel lblTnTiKhon = new JLabel("Tên Tài Khoản:");
		lblTnTiKhon.setForeground(Color.GRAY);
		lblTnTiKhon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnTiKhon.setBounds(405, 124, 102, 23);
		dangki.add(lblTnTiKhon);

		JLabel label_sdt = new JLabel("Số Điện Thoại:");
		label_sdt.setForeground(Color.GRAY);
		label_sdt.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_sdt.setBounds(405, 216, 102, 23);
		dangki.add(label_sdt);

		JLabel label_passDK = new JLabel("PassWord:");
		label_passDK.setForeground(Color.GRAY);
		label_passDK.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_passDK.setBounds(405, 170, 102, 23);
		dangki.add(label_passDK);

		textField_usernameDK = new JTextField();
		textField_usernameDK.setBounds(544, 126, 154, 20);
		dangki.add(textField_usernameDK);
		textField_usernameDK.setColumns(10);

		textField_sdtDK = new JTextField();
		textField_sdtDK.setColumns(10);
		textField_sdtDK.setBounds(546, 218, 154, 20);
		dangki.add(textField_sdtDK);

		textField_passDK = new JTextField();
		textField_passDK.setColumns(10);
		textField_passDK.setBounds(546, 172, 154, 20);
		dangki.add(textField_passDK);

		JButton buttonn_xacnhandangki = new JButton("Xác Nhận");
		buttonn_xacnhandangki.addActionListener(ac);
		buttonn_xacnhandangki.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonn_xacnhandangki.setBounds(488, 270, 112, 23);
		dangki.add(buttonn_xacnhandangki);
		JButton btnNewButton_1 = new JButton("Trở Về Màn Hình Đăng Nhập");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setForeground(Color.GRAY);

		JLabel label_anhdangki = new JLabel("New label");
		label_anhdangki.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.createImage(View.class.getResource("background-ly-cafe-den_090050156.jpg"))));
		label_anhdangki.setBounds(0, 0, 708, 504);
		dangki.add(label_anhdangki);

//giao diện quên mật khẩu
		quenmatkhau = new JPanel();
		quenmatkhau.setLayout(null);

		JSeparator separator_QMK = new JSeparator();
		separator_QMK.setBounds(10, 50, 688, 2);
		quenmatkhau.add(separator_QMK);

		JLabel lblQunMtKhu = new JLabel("Quên Mật Khẩu");
		lblQunMtKhu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQunMtKhu.setBounds(527, 63, 112, 23);
		quenmatkhau.add(lblQunMtKhu);

		JLabel label_QMK = new JLabel("Cổng Vấn Đề Về Tài Khoản");
		label_QMK.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_QMK.setBounds(231, 11, 295, 28);
		quenmatkhau.add(label_QMK);

		JLabel label_usernameQMK = new JLabel("UserName:");
		label_usernameQMK.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_usernameQMK.setBounds(424, 101, 81, 23);
		quenmatkhau.add(label_usernameQMK);

		JLabel label_emailQMK = new JLabel("Email:");
		label_emailQMK.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_emailQMK.setBounds(424, 148, 102, 23);
		quenmatkhau.add(label_emailQMK);

		JLabel label_newpass_QMK = new JLabel("New PassWord:");
		label_newpass_QMK.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_newpass_QMK.setBounds(424, 238, 102, 23);
		quenmatkhau.add(label_newpass_QMK);

		textField_usernameQMK = new JTextField();
		textField_usernameQMK.setBounds(544, 103, 154, 20);
		quenmatkhau.add(textField_usernameQMK);
		textField_usernameQMK.setColumns(10);

		textField_emailQMK = new JTextField();
		textField_emailQMK.setColumns(10);
		textField_emailQMK.setBounds(544, 150, 154, 20);
		quenmatkhau.add(textField_emailQMK);

		textField_passQMK = new JTextField();
		textField_passQMK.setColumns(10);
		textField_passQMK.setBounds(544, 240, 154, 20);
		quenmatkhau.add(textField_passQMK);

		JButton buttonn_submit_QMK = new JButton("Submit");
		buttonn_submit_QMK.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonn_submit_QMK.setBounds(511, 307, 112, 23);
		quenmatkhau.add(buttonn_submit_QMK);

		JLabel label_anhQMK = new JLabel("New label");
//		label_anhQMK.setIcon(
//				new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("anhnencf.jpg"))));
		label_anhQMK.setBounds(0, 53, 398, 426);
		quenmatkhau.add(label_anhQMK);

		JButton button_trove_QMK = new JButton("Trở Về Màn Hình Đăng Nhập");
		button_trove_QMK.addActionListener(ac);
		button_trove_QMK.setBounds(603, 440, 105, 43);
		quenmatkhau.add(button_trove_QMK);

		JLabel label_mãQMK = new JLabel("Mã Xác Nhận:");
		label_mãQMK.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_mãQMK.setBounds(424, 195, 102, 23);
		quenmatkhau.add(label_mãQMK);

		textField_mãQMK = new JTextField();
		textField_mãQMK.setColumns(10);
		textField_mãQMK.setBounds(544, 197, 154, 20);
		quenmatkhau.add(textField_mãQMK);
// -----------------------------------------------------------------------------------------------------
		panel_admin = new JPanel();
		panel_admin.setLayout(null);
		panel_admin.setBackground(SystemColor.inactiveCaption);
		JLabel lblNewLabel_banghieu_admin = new JLabel("Bom Coffee");
		lblNewLabel_banghieu_admin.setBackground(SystemColor.inactiveCaption);
		lblNewLabel_banghieu_admin

				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("logocf.png"))));
		lblNewLabel_banghieu_admin.setOpaque(true);
		// lblNewLabel_banghieu_admin.setBackground(Color.GRAY);
		lblNewLabel_banghieu_admin.setFont(new Font("Bernard MT Condensed", Font.ITALIC, 19));
		lblNewLabel_banghieu_admin.setBounds(0, 0, 140, 59);
		panel_admin.add(lblNewLabel_banghieu_admin);
		JButton btnNewButton_admin = new JButton("Product");
		btnNewButton_admin.setBackground(SystemColor.inactiveCaption);

		btnNewButton_admin.setContentAreaFilled(false);
		btnNewButton_admin.addActionListener(ac);
		btnNewButton_admin
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("coffeee.png"))));
		btnNewButton_admin.setOpaque(true);
		btnNewButton_admin.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnNewButton_admin.setBounds(0, 89, 140, 59);
		panel_admin.add(btnNewButton_admin);

		JButton btnNhnVin_admin = new JButton("Coffee Staff");
		btnNhnVin_admin.setBackground(SystemColor.inactiveCaption);

		btnNhnVin_admin.setContentAreaFilled(false);
		btnNhnVin_admin.addActionListener(ac);
		btnNhnVin_admin
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("team.png"))));
		btnNhnVin_admin.setOpaque(true);
		btnNhnVin_admin.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnNhnVin_admin.setBounds(0, 151, 140, 59);
		panel_admin.add(btnNhnVin_admin);
		JButton btnThngK_admin = new JButton("Statistical");
		btnThngK_admin.setBackground(SystemColor.inactiveCaption);

		btnThngK_admin.setContentAreaFilled(false);
		btnThngK_admin.addActionListener(ac);
		btnThngK_admin
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("thongke.png"))));
		btnThngK_admin.setOpaque(true);
		btnThngK_admin.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnThngK_admin.setBounds(0, 214, 140, 59);
		panel_admin.add(btnThngK_admin);

		JButton btnExit_admin = new JButton("Exit");
		btnExit_admin.setBackground(SystemColor.inactiveCaption);

		btnExit_admin.setContentAreaFilled(false);
		btnExit_admin.addActionListener(ac);
		btnExit_admin.setBackground(SystemColor.inactiveCaption);
		btnExit_admin.setOpaque(true);
		btnExit_admin.setFont(new Font("Verdana", Font.BOLD, 13));
		btnExit_admin.setBounds(0, 420, 140, 59);
		panel_admin.add(btnExit_admin);
		JLabel lblNewLabel_1_admin = new JLabel("");
		lblNewLabel_1_admin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_admin.setBounds(0, 53, 140, 25);
		panel_admin.add(lblNewLabel_1_admin);

		SimpleDateFormat form_admin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		ActionListener updateClockAction_admin = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				lblNewLabel_1_admin.setText(form_admin.format(date));
			}
		};
		Timer t_admin = new Timer(1000, updateClockAction_admin);
		t_admin.start();

		JButton btnrecommend_admin = new JButton("Feedback");
		btnrecommend_admin.setBackground(SystemColor.inactiveCaption);

		btnrecommend_admin.setContentAreaFilled(false);
		btnrecommend_admin.addActionListener(ac);
		btnrecommend_admin.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("feedback.png"))));
		btnrecommend_admin.setOpaque(true);
		btnrecommend_admin.setFont(new Font("Verdana", Font.ITALIC, 13));
		btnrecommend_admin.setBounds(0, 284, 140, 59);
		panel_admin.add(btnrecommend_admin);
		// chat của admin
		JButton btn_mess_admin = new JButton("Chat");
		btn_mess_admin.addActionListener(e -> {
			setContentPane(chat);
			validate();
			txt_thongtin_chat.setText("-Chào Ngày Mới" + "\n" + "-Phải Bán Được Nhiều" + "\n" + "Hơn Hôm Qua Nhé");
			timerr.start();
			HienThiTinNhan();
			// setUpSocket();
		});

		btn_mess_admin.setBackground(SystemColor.inactiveCaption);
		btn_mess_admin.setContentAreaFilled(false);
		btn_mess_admin.addActionListener(e -> {

		});
		btn_mess_admin.addActionListener(ac);
		btn_mess_admin
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("messss.png"))));
		btn_mess_admin.setOpaque(true);

		btn_mess_admin.setBounds(583, 374, 90, 59);
		panel_admin.add(btn_mess_admin);
		JButton btn_taotknhanvien_admin = new JButton("Register");
		btn_taotknhanvien_admin.setBackground(SystemColor.inactiveCaption);

		btn_taotknhanvien_admin.addActionListener(ac);
		btn_taotknhanvien_admin
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("user.png"))));
		btn_taotknhanvien_admin.setOpaque(true);
		btn_taotknhanvien_admin.setFont(new Font("Verdana", Font.ITALIC, 13));
		btn_taotknhanvien_admin.setBounds(0, 350, 140, 59);
		panel_admin.add(btn_taotknhanvien_admin);

// recommend_khachhang

		recommend_khachhang = new JPanel();
		recommend_khachhang.setBackground(SystemColor.inactiveCaption);
		recommend_khachhang.setLayout(null);

		JScrollPane scrollPane_menu = new JScrollPane();
		scrollPane_menu.setBounds(26, 12, 226, 411);
		scrollPane_menu.setPreferredSize(new Dimension(259, 361));
		recommend_khachhang.add(scrollPane_menu);

		list = new JList<>(model);
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String tenSP = list.getSelectedValue();
				if (tenSP != null) {
					textArea_ten.setText("                   " + tenSP);
					try {
						write("selected" + "," + tenSP);
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}
		});
		lbl_anh = new JLabel();
		lbl_anh.setBounds(393, 45, 252, 129);
		recommend_khachhang.add(lbl_anh);

		scrollPane_menu.setViewportView(list);

		JScrollPane scrollPane_CMT = new JScrollPane();
		scrollPane_CMT.setBounds(375, 241, 320, 108);
		recommend_khachhang.add(scrollPane_CMT);

		textArea_CMT = new JTextArea();
		scrollPane_CMT.setViewportView(textArea_CMT);
		textArea_CMT.setEditable(false);
		// scrollPane.setRowHeaderView(textArea_CMT);
		textArea_CMT.setBackground(SystemColor.inactiveCaption);
		textArea_CMT.setBounds(347, 241, 348, 108);

		JScrollPane scrollPane_vietCmt = new JScrollPane();
		scrollPane_vietCmt.setBounds(375, 360, 267, 63);
		recommend_khachhang.add(scrollPane_vietCmt);

		textArea_vietCmt = new JTextArea();
		scrollPane_vietCmt.setViewportView(textArea_vietCmt);
		textArea_vietCmt.setBackground(SystemColor.inactiveCaption);
		textArea_vietCmt.setBounds(347, 360, 267, 63);
		JButton btnSend = new JButton("Gửi");
		btnSend.addActionListener(e -> {
			String selectedProdut = list.getSelectedValue();
			if (selectedProdut != null) {
				// Lấy tên tài khoản khách hàng
				String tenTK = this.textArea_tentk.getText();
				// Lấy số sao khách hàng đánh giá
				int sao = 0;
				if (rdbtn_1s.isSelected()) {
					sao = 1;
				} else if (rdbtn_2s.isSelected()) {
					sao = 2;
				} else if (rdbtn_3s.isSelected()) {
					sao = 3;
				} else if (rdbtn_4s.isSelected()) {
					sao = 4;
				} else if (rdbtn_5s.isSelected()) {
					sao = 5;
				}
				if (sao == 0) {
					JOptionPane.showMessageDialog(this, "Vui Lòng Đánh Giá Sao");
				} else {
					String soSao = "";
					if (sao == 1) {
						soSao = rdbtn_1s.getText();
					} else if (sao == 2) {
						soSao = rdbtn_2s.getText();
					} else if (sao == 3) {
						soSao = rdbtn_3s.getText();
					} else if (sao == 4) {
						soSao = rdbtn_4s.getText();
					} else if (sao == 5) {
						soSao = rdbtn_5s.getText();
					}
					// lấy cmt
					String cmt = textArea_vietCmt.getText();
					// Hiển thị lên ô đọc cmt
					textArea_CMT.append(tenTK + ": " + cmt + " -" + "Đánh giá: " + soSao + "\n");
					this.textArea_vietCmt.setText("");

					try {
						String url = "jdbc:mysql://localhost:3306/comment_khachhang";
						Connection conn = DriverManager.getConnection(url, "root", "2010");
						String sql = "UPDATE comment_khachhang SET CMT = ? WHERE tenSP = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, textArea_CMT.getText() + "");
						pstmt.setString(2, textArea_ten.getText().trim());
						int num = pstmt.executeUpdate();
						if (num > 0) {
							System.out.println("cap nhat thanh cong");
						} else {
							System.out.println("cap nhat that bai");
						}
					} catch (Exception e9) {
						e9.printStackTrace();

					}

				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Sản Phẩm Bạn Cần Đánh Giá.", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}

		});

		btnSend.setBounds(647, 361, 63, 62);
		JButton btnChat = new JButton("Chat");
		btnChat.addActionListener(e -> {
			this.setContentPane(chat);
			this.validate();
			txt_thongtin_chat.setText("-Chào Ngày Mới" + "\n" + "-Có Việc Gì Thì Liên Hệ" + "\n"
					+ "Anh Chủ Đẹp Trai Nha" + "\n" + "SĐT:0832235031" + "\n" + "FB: Nguyễn Đình Lân");
			HienThiTinNhan();
			lebel_chuchayngang
					.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(View.class.getResource("war.png"))));
			lebel_chuchayngang.setText("Client 0 để nhắn với Quản Lý và Client 1 để nhắn với Nhân Viên");
			timerr.start();
			// setUpSocket();
		});
		btnChat.setBounds(26, 430, 89, 47);
		recommend_khachhang.add(btnChat);
		;
		recommend_khachhang.add(btnSend);

		JLabel lbl_cmt = new JLabel("Comment");
		lbl_cmt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_cmt.setBounds(274, 229, 63, 47);
		recommend_khachhang.add(lbl_cmt);
		JLabel lbl_user = new JLabel("User:");
		lbl_user.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_user.setBounds(262, 360, 42, 23);
		recommend_khachhang.add(lbl_user);
		Font font = new Font("Arial", Font.PLAIN, 14);
		// Font fontt = new Font("Bold", Font.PLAIN, 14);
		// ten tk
		textArea_tentk = new JTextArea();
		textArea_tentk.setFont(new Font("Tahoma", Font.BOLD, 14));
		textArea_tentk.setEditable(false);
		textArea_tentk.setBackground(SystemColor.inactiveCaption);
		textArea_tentk.setBounds(303, 363, 76, 23);
		recommend_khachhang.add(textArea_tentk);
//		button đọc truyện
		JButton btn_doctruyen = new JButton("Đọc Truyện");
		btn_doctruyen.addActionListener(ac);
		btn_doctruyen.setBounds(136, 430, 116, 47);
		recommend_khachhang.add(btn_doctruyen);

		rdbtn_1s = new JRadioButton("1 sao");
		rdbtn_1s.addActionListener(ac);
		rdbtn_1s.setBackground(SystemColor.inactiveCaption);
		rdbtn_1s.setBounds(389, 207, 57, 23);
		recommend_khachhang.add(rdbtn_1s);

		rdbtn_2s = new JRadioButton("2 sao");
		rdbtn_2s.addActionListener(ac);
		rdbtn_2s.setBackground(SystemColor.inactiveCaption);
		rdbtn_2s.setBounds(448, 207, 57, 23);
		recommend_khachhang.add(rdbtn_2s);

		rdbtn_3s = new JRadioButton("3 sao");
		rdbtn_3s.addActionListener(ac);
		rdbtn_3s.setBackground(SystemColor.inactiveCaption);
		rdbtn_3s.setBounds(507, 207, 57, 23);
		recommend_khachhang.add(rdbtn_3s);

		rdbtn_4s = new JRadioButton("4 sao");
		rdbtn_4s.addActionListener(ac);
		rdbtn_4s.setBackground(SystemColor.inactiveCaption);
		rdbtn_4s.setBounds(563, 207, 57, 23);
		recommend_khachhang.add(rdbtn_4s);

		rdbtn_5s = new JRadioButton("5 sao");
		rdbtn_5s.addActionListener(ac);
		rdbtn_5s.setBackground(SystemColor.inactiveCaption);
		rdbtn_5s.setBounds(619, 207, 57, 23);
		recommend_khachhang.add(rdbtn_5s);

		ButtonGroup danhgias = new ButtonGroup();
		danhgias.add(rdbtn_1s);
		danhgias.add(rdbtn_2s);
		danhgias.add(rdbtn_3s);
		danhgias.add(rdbtn_4s);
		danhgias.add(rdbtn_5s);

		JLabel lbl_danhgia = new JLabel("Product Rating");
		lbl_danhgia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_danhgia.setBounds(274, 205, 99, 29);
		recommend_khachhang.add(lbl_danhgia);

		textArea_gia = new JTextArea();
		textArea_gia.setFont(font);
		textArea_gia.setEditable(false);
		textArea_gia.setBounds(451, 178, 150, 22);
		textArea_gia.setBackground(SystemColor.inactiveCaption);
		recommend_khachhang.add(textArea_gia);

		textArea_ten = new JTextArea();

		textArea_ten.setFont(font);
		textArea_ten.setAlignmentX(Component.CENTER_ALIGNMENT);
		textArea_ten.setEditable(false);
		textArea_ten.setBounds(408, 19, 225, 22);
		textArea_ten.setBackground(SystemColor.inactiveCaption);
		recommend_khachhang.add(textArea_ten);

// recommend cua admin
		rcm_admin = new JPanel();
		rcm_admin.setLayout(null);
		rcm_admin.setBackground(SystemColor.inactiveCaption);
		JList<String> list_admin = new JList<>(model_admin);
		list_admin.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String tenSP = list_admin.getSelectedValue();
				if (tenSP != null) {
					// hien thi ten sp
					textField_tensp_rcmt_admin.setText("          " + tenSP);
					// hien thi anh
					try {
						String url = "jdbc:mysql://localhost:3306/sanpham";
						Connection conn = DriverManager.getConnection(url, "root", "2010");
						String sql = "SELECT img FROM sanpham WHERE TenSanPham = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tenSP);
						ResultSet rs = pstmt.executeQuery();
						if (rs.next()) {
							String pathh = rs.getString("img");
							ImageIcon imgg = new ImageIcon(pathh);
							label_anh_admin.setIcon(imgg);
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						String url = "jdbc:mysql://localhost:3306/sanpham";
						Connection conn = DriverManager.getConnection(url, "root", "2010");
						String sql = "SELECT GiaSanPham FROM sanpham WHERE TenSanPham = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tenSP);
						ResultSet rs = pstmt.executeQuery();
						if (rs.next()) {
							String gia = rs.getString("GiaSanPham" + "");
							gia_rcm_admin.setText("          " + gia + "vnđ");
						}

					} catch (Exception e3) {
						e3.printStackTrace();
					}
					try {
						String url = "jdbc:mysql://localhost:3306/comment_khachhang";
						Connection conn = DriverManager.getConnection(url, "root", "2010");
						String sql = "SELECT CMT FROM comment_khachhang WHERE tenSP = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tenSP);
						ResultSet rs = pstmt.executeQuery();
						if (rs.next()) {
							String cmt = rs.getString("CMT");
							textArea_CMT_rcm_admin.setText(cmt);
						}
					} catch (Exception e4) {
						e4.printStackTrace();
					}
				}
			}

		});

		JScrollPane scrollPane_rcm_admin = new JScrollPane(list_admin);
		scrollPane_rcm_admin.setBounds(5, 5, 259, 423);
		scrollPane_rcm_admin.setPreferredSize(new Dimension(259, 361));
		rcm_admin.add(scrollPane_rcm_admin);

		textField_tensp_rcmt_admin = new JTextArea();
		textField_tensp_rcmt_admin.setEditable(false);
		textField_tensp_rcmt_admin.setFont(font);
		textField_tensp_rcmt_admin.setBounds(417, 5, 186, 29);
		textField_tensp_rcmt_admin.setBackground(SystemColor.inactiveCaption);
		rcm_admin.add(textField_tensp_rcmt_admin);
		textField_tensp_rcmt_admin.setColumns(10);

		label_anh_admin = new JLabel();
		label_anh_admin.setBounds(368, 40, 291, 159);
		label_anh_admin.setBackground(SystemColor.inactiveCaption);
		rcm_admin.add(label_anh_admin);

		JScrollPane scrollPane_CMT_rcm_admin = new JScrollPane();
		scrollPane_CMT_rcm_admin.setBounds(345, 260, 348, 163);
		rcm_admin.add(scrollPane_CMT_rcm_admin);

		textArea_CMT_rcm_admin = new JTextArea();
		textArea_CMT_rcm_admin.setEditable(false);
		scrollPane_CMT_rcm_admin.setViewportView(textArea_CMT_rcm_admin);
		textArea_CMT_rcm_admin.setBackground(SystemColor.inactiveCaption);
		;

		JLabel label_cmt_admin = new JLabel("Comment");
		label_cmt_admin.setBounds(274, 273, 63, 47);
		label_cmt_admin.setFont(new Font("Tahoma", Font.BOLD, 13));
		rcm_admin.add(label_cmt_admin);

		gia_rcm_admin = new JTextArea();
		gia_rcm_admin.setEditable(false);
		gia_rcm_admin.setFont(font);
		gia_rcm_admin.setBackground(SystemColor.inactiveCaption);
		gia_rcm_admin.setBounds(417, 200, 198, 22);
		rcm_admin.add(gia_rcm_admin);
		JButton btn_quaylai_rcm_admin = new JButton("Quay lại");
		btn_quaylai_rcm_admin.addActionListener(ac);
		btn_quaylai_rcm_admin.setBounds(470, 5, 87, 23);
		rcm_admin.add(btn_quaylai_rcm_admin);
// tao tai khoan nhan vien
		taotaikhoanNV = new JPanel();
		taotaikhoanNV.setLayout(null);
		taotaikhoanNV.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel label_taoTKnhanvien = new JLabel("Cổng Tạo Tài Khoản Cho Nhân Viên");
		label_taoTKnhanvien.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_taoTKnhanvien.setBounds(15, 1, 309, 35);
		taotaikhoanNV.add(label_taoTKnhanvien);
		JLabel label_taikhoanNV = new JLabel("Tài Khoản:");
		label_taikhoanNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_taikhoanNV.setBounds(10, 47, 71, 35);
		taotaikhoanNV.add(label_taikhoanNV);

		JLabel label_emaiNV = new JLabel("Email:");
		label_emaiNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_emaiNV.setBounds(10, 111, 71, 14);
		taotaikhoanNV.add(label_emaiNV);

		JLabel lblNewLabel_1_2 = new JLabel("Mật Khẩu:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(10, 161, 71, 14);
		taotaikhoanNV.add(lblNewLabel_1_2);

		textField_taikhoanNV = new JTextField();
		textField_taikhoanNV.setBounds(115, 55, 148, 20);
		taotaikhoanNV.add(textField_taikhoanNV);
		textField_taikhoanNV.setColumns(10);

		textField_emailNV = new JTextField();
		textField_emailNV.setColumns(10);
		textField_emailNV.setBounds(115, 109, 148, 20);
		taotaikhoanNV.add(textField_emailNV);

		textField_matkhauNV = new JTextField();
		textField_matkhauNV.setColumns(10);
		textField_matkhauNV.setBounds(115, 159, 148, 20);
		taotaikhoanNV.add(textField_matkhauNV);
		JButton btn_quaytrolai_admin_rcm = new JButton("Quay Về");
		btn_quaytrolai_admin_rcm.addActionListener(ac);
		btn_quaytrolai_admin_rcm.setBounds(290, 190, 25, 23);
		taotaikhoanNV.add(btn_quaytrolai_admin_rcm);

		JButton btn_xacnhanTaoTKNV = new JButton("Tạo");
		btn_xacnhanTaoTKNV.addActionListener(ac);
		btn_xacnhanTaoTKNV.setBounds(115, 190, 78, 23);
		taotaikhoanNV.add(btn_xacnhanTaoTKNV);
// chat 
		chat = new JPanel();
		chat.setLayout(null);
		chat.setLayout(null);
		jComboBox1 = new JComboBox();
		jComboBox1.addItem("Gửi tất cả");

		txt_thongtin_chat = new JTextArea();
		txt_thongtin_chat.setEditable(false);
		txt_thongtin_chat.setBounds(570, 3, 138, 111);
		chat.add(txt_thongtin_chat);

		JButton btn_quaylai_chat = new JButton("Quay Lại");
		btn_quaylai_chat.addActionListener(e -> {
			if (ten.equals("admin")) {
				this.setContentPane(this.panel_admin);
				this.validate();
			} else if (ten.equals("staff")) {
				this.setContentPane(this.thanhtoan);
				this.validate();
			} else {
				this.setContentPane(this.recommend_khachhang);
				this.validate();
			}
		});
		btn_quaylai_chat.setBounds(0, 0, 96, 31);
		chat.add(btn_quaylai_chat);

		lebel_chuchayngang = new JLabel("");
		lebel_chuchayngang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lebel_chuchayngang.setBounds(106, 8, 444, 23);
		chat.add(lebel_chuchayngang);

		timerr = new Timer(35, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cập nhật vị trí của JLabel
				x += 5;
				if (x > chat.getWidth()) {
					x = -lebel_chuchayngang.getWidth();
				}
				lebel_chuchayngang.setLocation(x, lebel_chuchayngang.getLocation().y);
			}
		});

		JLabel jLabel3 = new JLabel("New label");
		jLabel3.setFont(new Font("Tahoma", Font.BOLD, 17));
		jLabel3.setBounds(161, 37, 209, 20);
		chat.add(jLabel3);

		jTextArea1 = new JTextArea();
		jTextArea1.setEditable(false);
		jTextArea1.setBounds(161, 68, 389, 213);
		chat.add(jTextArea1);

		JLabel lblNewLabe_1 = new JLabel("Chọn Người Nhắn:");
		lblNewLabe_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabe_1.setBounds(161, 292, 137, 22);
		chat.add(lblNewLabe_1);

		jComboBox1.addActionListener(e -> {
			if (jComboBox1.getSelectedIndex() == 0) {
				jLabel3.setText("Global");
			} else {
				jLabel3.setText("Đang nhắn với " + jComboBox1.getSelectedItem());
			}
		});
		jComboBox1.setBounds(161, 325, 389, 22);
		chat.add(jComboBox1);

		JLabel lblNewLabel_1_1 = new JLabel("Nhập Tin Nhắn:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(161, 358, 137, 22);
		chat.add(lblNewLabel_1_1);

		jTextField1 = new JTextField();
		jTextField1.setBounds(161, 391, 306, 43);
		chat.add(jTextField1);
		jTextField1.setColumns(10);

		JButton btn_send = new JButton("Gửi");
		btn_send.addActionListener(e -> {
			String messageContent = jTextField1.getText();
			if (messageContent.isEmpty()) {
				JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tin nhắn");
				return;
			}
			if (jComboBox1.getSelectedIndex() == 0) {
				try {
					System.out.println(ten);
					write("send-to-global" + "," + messageContent + "," + ten);
					jTextArea1.setText(jTextArea1.getText() + "\n" + "Bạn: " + messageContent + "\n");
					jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
					//UpdateTinNhan();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
				}
			} else {
				try {
					String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
					write("send-to-person" + "," + messageContent + "," + this.id + "," + parner[1]);
					jTextArea1.setText(jTextArea1.getText() + "\n" + "Bạn (tới Client " + parner[1] + "): "
							+ messageContent + "\n");
					jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
					UpdateTinNhan();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
				}
			}
			jTextField1.setText("");
		});
		btn_send.setBounds(477, 389, 73, 46);
		chat.add(btn_send);
// đọc truyện
		doctruyen = new JPanel();
		doctruyen.setLayout(null);
		textPane = new JTextPane();
		textPane.setContentType("text/html");

		JScrollPane scrollPane_doctruyen = new JScrollPane(textPane);
		scrollPane_doctruyen.setBounds(153, 0, 735, 479);
		doctruyen.add(scrollPane_doctruyen);

		JComboBox comboBox_trangquynh = new JComboBox();
		comboBox_trangquynh.addActionListener(ac);
		comboBox_trangquynh.setBounds(0, 0, 151, 31);
		doctruyen.add(comboBox_trangquynh);
		comboBox_trangquynh.addItem("Trạng Quỷnh");
		comboBox_trangquynh.addItem("Tập 1:Sao sáng xứ Thanh");
		comboBox_trangquynh.addItem("Tập 2:Đất nứt con bọ hung");
		comboBox_trangquynh.addItem("Tập 3:Cúng Thành Hoàng");
		comboBox_trangquynh.addItem("Tập 4:Miệng kẻ sang");
		comboBox_trangquynh.addItem("Tập 5:Ghẹo cô hàng nước");
		comboBox_trangquynh.addItem("Tập 6:Đệ nhất danh họa");

		JComboBox comboBox_cauberong = new JComboBox();
		comboBox_cauberong.setBounds(0, 42, 151, 31);
		doctruyen.add(comboBox_cauberong);
		comboBox_cauberong.addItem("Cậu Bé Rồng");

		btn_qlrcm = new JButton("Quay về trang recommend");
		btn_qlrcm.addActionListener(ac);
		btn_qlrcm.setBounds(10, 438, 89, 41);
		doctruyen.add(btn_qlrcm);
		setUpSocket();
	}

// Quản Lý Sản Phẩm	
	public void deleteform_qlsp() {
		textField_tenSP.setText("");
		textField_giaSP.setText("");
		textField_maSP.setText("");
		textField_ghichuSP.setText("");
		txt_pathFile.setText("");
	}

	public void insertandupdate_qlsp(SanPham sp) {
		DefaultTableModel model_table = (DefaultTableModel) table_cacloaidouong.getModel();
		if (!this.m.kiemtraSPdatontaichua(sp)) {
			this.m.themSP(sp);
			try {
				String url = "jdbc:mysql://localhost:3306/sanpham";
				Connection conn = DriverManager.getConnection(url, "root", "2010");
				String sql = "INSERT INTO sanpham (MaSanPham, TenSanPham, GiaSanPham,TrangThai, GhiChu, img) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql); // là một interface để thực thi câu lệnh
				pstmt.setInt(1, sp.getMaSP());
				pstmt.setString(2, sp.getTenSP());
				pstmt.setInt(3, sp.getGiaSP());
				pstmt.setBoolean(4, sp.isTrangThai());
				pstmt.setString(5, sp.getGhichu());
				pstmt.setString(6, sp.getImage());
				int num = pstmt.executeUpdate();
				if (num > 0) {
					this.coppyProduct();
				} else {
					System.out.println("Thêm sản phẩm thất bại!");
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Mã Sản Phẩm Đã Tồn Tại");
			}

		} else {
			this.m.chinhsuaSP(sp);
			int soluongdong = model_table.getRowCount();
			for (int i = 0; i < soluongdong; i++) {
				String maSP = model_table.getValueAt(i, 0) + "";
				if (maSP.equals(sp.getMaSP() + "")) {
					Connection conn = null;
					try {
						String url = "jdbc:mysql://localhost:3306/sanpham";
						conn = DriverManager.getConnection(url, "root", "2010");
						String sql = "UPDATE sanpham SET TenSanPham = ?, GiaSanPham = ?, TrangThai = ?, GhiChu = ?, img = ? WHERE MaSanPham = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, sp.getTenSP());
						pstmt.setInt(2, sp.getGiaSP());
						pstmt.setBoolean(3, sp.isTrangThai());
						pstmt.setString(4, sp.getGhichu());
						pstmt.setString(5, sp.getImage());
						pstmt.setInt(6, sp.getMaSP());
						pstmt.executeUpdate();

					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}

		this.updatetable_qlsp();
	}

	public void updatetable_qlsp() {
		DefaultTableModel model_table = (DefaultTableModel) table_cacloaidouong.getModel();
		model_table.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/sanpham";
			conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "SELECT * FROM sanpham";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // thực thi câu lệnh và lưu kq vào rs

			while (rs.next()) { // duyệt qua tất cả các hàng nếu có trả về true nếu không trả về false
				int maSP = rs.getInt("MaSanPham");
				String tenSP = rs.getString("TenSanPham");
				int giaSP = rs.getInt("GiaSanPham");
				boolean trangthai = rs.getBoolean("TrangThai");
				String ghichu = rs.getString("GhiChu");

				model_table.addRow(
						new Object[] { maSP + "", tenSP, giaSP, trangthai ? "Hoạt Động" : "Không Hoạt Động", ghichu });
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void product_repair_qlsp() {
		SanPham sp = productisselected_qlsp();
		textField_tenSP.setText(sp.getTenSP());
		textField_giaSP.setText(sp.getGiaSP() + "");
		textField_maSP.setText(sp.getMaSP() + "");
		textField_ghichuSP.setText(sp.getGhichu());
		if (sp.isTrangThai()) {
			rdn_hd.setSelected(true);
		} else {
			rdn_khonghd.setSelected(true);
		}

	}

	public SanPham productisselected_qlsp() {
		DefaultTableModel model_table = (DefaultTableModel) table_cacloaidouong.getModel();
		int i_row = table_cacloaidouong.getSelectedRow();
		int maSP = Integer.valueOf(model_table.getValueAt(i_row, 0) + "");
		String tenSP = model_table.getValueAt(i_row, 1) + "";

		int giaSP = Integer.valueOf(model_table.getValueAt(i_row, 2) + "");
		String textGioiTinh = model_table.getValueAt(i_row, 3) + "";
		boolean trangthai = textGioiTinh.equals("Hoạt Động");
		String ghichu = model_table.getValueAt(i_row, 4) + "";

		// String img = model_table.getValueAt(i_row, 5) + "";
		SanPham sp = new SanPham(maSP, tenSP, giaSP, trangthai, ghichu, "");
		return sp;
	}

	public void delete_qlsp() {
		int luachon = JOptionPane.showConfirmDialog(this, "Bạn có Chắc Chắn Muốn Xóa Dòng Đã Chọn");
		SanPham sp = productisselected_qlsp();
		if (luachon == JOptionPane.YES_OPTION) {
			this.m.xoaSP(sp);
		}
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/sanpham";
			conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "DELETE FROM sanpham WHERE MaSanPham = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sp.getMaSP());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		this.updatetable_qlsp();
	}

// Quản Lý Nhân Viên
	public void xoaform_qlnv() {
		textField_tenNV.setText("");
		textField_tuoiNV.setText("");
		textField_ngaysinhNV.setText("");
		textField_cccdNV.setText("");
		textField_sdtNV.setText("");
		textField_ngaylamNV.setText("");
	}

	public void themhoaccapnhatNhanVien(NhanVien nv) {
		String tennv = nv.getTenNV();
		String ngaysinh = nv.getNgaySinh();
		String tuoi = nv.getTuoi() + "";
		String cccd = nv.getCccd();
		String sdt = nv.getSdt();
		String ngaylam = nv.getNgayLam();
		try {
			write("kiemtranhanvientontaihaychua" + "," + tennv + "," + ngaysinh + "," + tuoi + "," + cccd + "," + sdt
					+ "," + ngaylam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			DefaultTableModel model_table = (DefaultTableModel) table_qlnv.getModel();
			model_table.setRowCount(0);
			write("staff");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void capnhatbang_qlnv() {
		DefaultTableModel model_table = (DefaultTableModel) table_qlnv.getModel();
		model_table.setRowCount(0);
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

				model_table.addRow(new Object[] { tenNV, ngaysinh, tuoi, cccd, sdt, ngaylam });
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void suaNV() {
		NhanVien nv = getNVdangchon();
		this.textField_tenNV.setText(nv.getTenNV());
		this.textField_ngaysinhNV.setText(nv.getNgaySinh() + "");
		this.textField_tuoiNV.setText(nv.getTuoi() + "");
		this.textField_cccdNV.setText(nv.getCccd());
		this.textField_sdtNV.setText(nv.getSdt());
		this.textField_ngaylamNV.setText(nv.getNgayLam() + "");

	}

	public NhanVien getNVdangchon() {
		DefaultTableModel model_table = (DefaultTableModel) table_qlnv.getModel();
		int i_row = table_qlnv.getSelectedRow();
		String tenNV = model_table.getValueAt(i_row, 0) + "";
		String ngaysinh = model_table.getValueAt(i_row, 1) + "";
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		format.setLenient(false);
		Date date_ngaysinh = null;
		try {
			date_ngaysinh = format.parse(ngaysinh);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int tuoi = Integer.valueOf(model_table.getValueAt(i_row, 2) + "");
		String cccd = model_table.getValueAt(i_row, 3) + "";
		String sdt = model_table.getValueAt(i_row, 4) + "";
		String ngaylam = model_table.getValueAt(i_row, 5) + "";
		SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
		Date date_ngaylam = null;
		try {
			date_ngaylam = form.parse(ngaylam);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		NhanVien nv = new NhanVien(tenNV, date_ngaysinh, tuoi, cccd, sdt, date_ngaylam);
		return nv;
	}

	public void xoaNV() {
		int luachon = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Nhân Viên Này?");
		NhanVien nv = getNVdangchon();
		if (luachon == JOptionPane.YES_OPTION) {
			try {
				write("delete" + "," + nv.getCccd());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			DefaultTableModel model_table = (DefaultTableModel) table_qlnv.getModel();
			model_table.setRowCount(0);
			write("staff");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// cổng thanh toán
	int tongtien = 0;

	public void sanpham_congthanhtoan() {
		this.khongchinhsua1();
		this.khongchinhsua2();
		DefaultTableModel model_table = (DefaultTableModel) table_sanpham_thanhtoan.getModel();
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

				model_table.addRow(
						new Object[] { maSP + "", tenSP, giaSP, trangthai ? "Hoạt Động" : "Không Hoạt Động", ghichu });
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void chon_douong() {
		String sl = JOptionPane.showInputDialog(this, "Nhập số lượng:");
		int soluong = Integer.valueOf(sl);
		DefaultTableModel model_table = (DefaultTableModel) table_sanpham_thanhtoan.getModel();
		int i_row = table_sanpham_thanhtoan.getSelectedRow();

		String tensp = model_table.getValueAt(i_row, 1) + "";
		int giabang = Integer.valueOf(model_table.getValueAt(i_row, 2) + "");
		int giachinh = giabang * soluong;

		DefaultTableModel model_table_bangtinhtoan = (DefaultTableModel) table_thanhtoan_tinhtoan.getModel();
		model_table_bangtinhtoan.addRow(new Object[] { tensp + "", sl, giachinh + "" });
	}

	public void reset_thanhtoan() {
		this.textField_tkdtt.setText("");
		this.textField_tltt.setText("");
		this.textArea_hdtt.setText("");
		tongtien = 0;
		DefaultTableModel model_table = (DefaultTableModel) table_thanhtoan_tinhtoan.getModel();
		model_table.setRowCount(0);
	}

	public void taohoadon() {
		String ngay = textField_ngaytt.getText() + "";
		textArea_hdtt.append("                  Bom Coffee\n");
		textArea_hdtt.append("Ngày Mua: " + ngay + "\n");
		textArea_hdtt.append("Tên Nước Uống: ");

		DefaultTableModel model_table = (DefaultTableModel) table_thanhtoan_tinhtoan.getModel();
		int soluongdong = model_table.getRowCount();
		for (int i = 0; i < soluongdong; i++) {
			String tensp = model_table.getValueAt(i, 0) + "";
			String sl = model_table.getValueAt(i, 1) + "";
			tongtien = tongtien + Integer.valueOf(model_table.getValueAt(i, 2) + "");
			textArea_hdtt.append(tensp + " x" + sl + "\n" + "                               ");
		}
		textArea_hdtt.append("\n");
		textArea_hdtt.append("Tổng Tiền: " + tongtien);
//		Connection conn = null;
//		try {
//			String url = "jdbc:mysql://localhost:3306/hoadon";
//			conn = DriverManager.getConnection(url, "root", "2010");
//			String sql = "INSERT INTO hoadon (TongTien, NgayTinhHoaDon) VALUES (?,?)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, tongtien);
//			pstmt.setString(2, ngay);
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
		try {
			write("tongtien" + "," + tongtien + "" + "," + ngay);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int luachon = JOptionPane.showConfirmDialog(this, "Bạn Muốn In Hóa Đơn Không?");
		if (luachon == JOptionPane.YES_OPTION) {

			JFileChooser fc = new JFileChooser();

			int returnval = fc.showSaveDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fc.getSelectedFile();
					PrintWriter pw = new PrintWriter(file.getAbsolutePath(), "UTF-8");
					String data = this.textArea_hdtt.getText();
					pw.print(data);
					pw.flush();
					pw.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	public void tinhtientralai() {

		int tienkhachdua = Integer.valueOf(textField_tkdtt.getText() + "");
		int thoilai = tienkhachdua - tongtien;
		if (thoilai < 0) {
			JOptionPane.showMessageDialog(this, "Khách Hàng Đưa Thiếu Tiền");
		}
		textField_tltt.setText(thoilai + "");
	}

// cổng thống kê

	public void themchienluoc() {
		this.textArea_ghichienluoc.setText("");
	}

	public void luuchienluoc() {
		textArea_luuchienluoc.append("                    " + this.textField_time_thongke.getText() + "" + "\n");
		textArea_luuchienluoc.append("\n");
		textArea_luuchienluoc.append(textArea_ghichienluoc.getText() + "\n");

		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/chienluocc";
			conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "UPDATE chienluoc SET cacchienluoc = ? WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, textArea_luuchienluoc.getText());
			pstmt.setInt(2, 1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void hiencacchienluoc() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/chienluocc";
			conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "SELECT cacchienluoc FROM chienluoc WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				textArea_luuchienluoc.setText(rs.getString("cacchienluoc"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void bangthongke() {
		this.khongchinhsua();
		DefaultTableModel model_table = (DefaultTableModel) table_thongke.getModel();
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

				int rowIndex = -1;
				int soluongdong = model_table.getRowCount();
				for (int i = 0; i < soluongdong; i++) {
					if (model_table.getValueAt(i, 0).equals(ngayBan)) {
						rowIndex = i;
						break;
					}
				}
				if (rowIndex >= 0) {
					model_table.setValueAt(tongTien, rowIndex, 1);
				} else {
					model_table.addRow(new Object[] { ngayBan, tongTien });
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void bangthongke_nv() {
		// this.khongchinhsua();
		DefaultTableModel model_table = (DefaultTableModel) table_thongke_nv.getModel();
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

				int rowIndex = -1;
				int soluongdong = model_table.getRowCount();
				for (int i = 0; i < soluongdong; i++) {
					if (model_table.getValueAt(i, 0).equals(ngayBan)) {
						rowIndex = i;
						break;
					}
				}
				if (rowIndex >= 0) {
					model_table.setValueAt(tongTien, rowIndex, 1);
				} else {
					model_table.addRow(new Object[] { ngayBan, tongTien });
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void khongchinhsua() {
		DefaultTableModel model_table = new DefaultTableModel(new String[] { "Ngày Bán", "Tổng Tiền" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_thongke.setModel(model_table);
	}

	public void khongchinhsua1() {
		DefaultTableModel model_table = new DefaultTableModel(
				new String[] { "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Sản Phẩm", "Trạng Thái", "Ghi Chú" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_sanpham_thanhtoan.setModel(model_table);
	}

	public void khongchinhsua2() {
		DefaultTableModel model_table = new DefaultTableModel(new String[] { "Tên", "SL", "Giá" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_thanhtoan_tinhtoan.setModel(model_table);
	}

	public void khongchinhsua3() {
		DefaultTableModel model_table = new DefaultTableModel(
				new String[] { "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Sản Phẩm", "Trạng Thái", "Ghi Chú" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_cacloaidouong.setModel(model_table);
	}

	public void khongchinhsua4() {
		DefaultTableModel model_table = new DefaultTableModel(
				new String[] { "Tên NV", "Ngày Sinh", "Tuổi", "CCCD", "SĐT", "Ngày Làm" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_qlnv.setModel(model_table);
	}

// đăng ký tk

	public void dangkytaikhoan() {
		String tenKH = textField_tenKH_dangky.getText();
		String tendangnhap = textField_usernameDK.getText();
		String matkhau = textField_passDK.getText();
		String sdt = textField_sdtDK.getText();

		if (tenKH.isEmpty() || tendangnhap.isEmpty() || matkhau.isEmpty() || sdt.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui Lòng Điền Đầy Đủ Các Ô");
			return;
		}
		String hash = BCrypt.hashpw(matkhau, BCrypt.gensalt(12));
		try {
			String url = "jdbc:mysql://localhost:3306/taikhoan";
			Connection conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "INSERT INTO taikhoan (tenKH,taikhoan,matkhau,sdt) VALUES (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tenKH);
			pstmt.setString(2, tendangnhap);
			pstmt.setString(3, hash);
			pstmt.setString(4, sdt);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				this.setContentPane(batdau);
				this.validate();
				this.textField_tenKH_dangky.setText("");
				textField_usernameDK.setText("");
				textField_passDK.setText("");
				textField_sdtDK.setText("");
				coppytenKH();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void capnhatdulieu() {
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
				SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
				Date date1 = null;
				try {
					date1 = form.parse(ngaysinh);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				int tuoi = rs.getInt("tuoi");
				String cccd = rs.getString("cccd");
				String sdt = rs.getString("sdt");
				String ngaylam = rs.getString("ngaylam");
				Date date2 = null;
				try {
					date2 = form.parse(ngaylam);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				NhanVien nv = new NhanVien(tenNV, date1, tuoi, cccd, sdt, date2);
				this.m.dsnv.add(nv);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void capnhatdulieu_sanpham() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/sanpham";
			conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "SELECT * FROM sanpham";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int maSP = Integer.valueOf(rs.getInt("MaSanPham"));
				String tenSP = rs.getString("TenSanPham");
				int giaSP = Integer.valueOf(rs.getInt("GiaSanPham"));
				boolean trangthai = rs.getBoolean("TrangThai");
				String ghichuSP = rs.getString("GhiChu");
				String img = rs.getString("img");
				SanPham sp = new SanPham(maSP, tenSP, giaSP, trangthai, ghichuSP, img);
				this.m.dssp.add(sp);
			}
		} catch (SQLException e) {

		}
	}

	public boolean endsWithGmail(String email) {
		return email.endsWith("@gmail.com");
	}

// Kiểm Tra Đăng Nhập
	public void kiemtradangnhap() {
		boolean check = false;
		String tk = this.textField_username.getText();
		String mk = this.textField_password.getText();
		if (tk.isEmpty() || mk.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tên người dùng và mật khẩu không được để trống");
			return;
		}
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
					this.setContentPane(this.panel_admin);
					this.validate();
					ten = "manager";
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
				String sql = "SELECT * FROM taikhoannhanvien WHERE tendangnhap=? AND matkhau=?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, tk);
				stmt.setString(2, mk);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					this.setContentPane(this.thanhtoan);
					this.validate();
					check = true;
					this.sanpham_congthanhtoan();
					ten = "staff";
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
						this.setContentPane(this.recommend_khachhang);
						this.validate();
						this.themsp_jlist();
						this.textArea_tentk.setText(" " + tk);
						this.coppyProduct();
						ten = tk;
					}
				} else {
					JOptionPane.showMessageDialog(this, "Tài Khoản Hoặc Mật Khẩu KHông Chính Xác");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void images() {
		String pathFile = "";
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			pathFile = file.getAbsolutePath().toString();
			// pathFile = file.getAbsolutePath().replace("//","--");
			this.txt_pathFile.setText(pathFile);
			BufferedImage b;
			try {
				b = ImageIO.read(file);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void themsp_jlist() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sanpham", "root", "2010");
			String sql = "SELECT TenSanPham FROM sanpham";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String tenSP = rs.getString("TenSanPham");
				model.addElement(tenSP);
				model_admin.addElement(tenSP);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void themtenKH() {
		try {
			String url = "jdbc:mysql://localhost/taikhoan";
			Connection conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "SELECT taikhoan FROM taikhoan";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String tenKH = rs.getString("taikhoan");
				model_chat_admin.addElement(tenKH);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void coppyProduct() {
		try {
			// Đường dẫn của trang nguồn
			String url_sanpham = "jdbc:mysql://localhost:3306/sanpham";
			Connection conn_sanpham = DriverManager.getConnection(url_sanpham, "root", "2010");
			// đường dẫn của trang đích
			String url_cmt = "jdbc:mysql://localhost:3306/comment_khachhang";
			Connection conn_cmt = DriverManager.getConnection(url_cmt, "root", "2010");
			// thực hiện các câu lệnh
			String selectQuery = "SELECT TenSanPham FROM sanpham";
			String insertQuery = "INSERT INTO comment_khachhang (tenSP,CMT) VALUES (?,?) ON DUPLICATE KEY UPDATE tenSP = tenSP";
			try {
				// trang nguồn
				PreparedStatement pstmt_sanpham = conn_sanpham.prepareStatement(selectQuery);
				ResultSet rs_sanpham = pstmt_sanpham.executeQuery();
				// trang đích
				PreparedStatement pstmt_cmt = conn_cmt.prepareStatement(insertQuery);
				// duyệt tên từ trang nguồn
				while (rs_sanpham.next()) {
					String tenSanPham = rs_sanpham.getString("TenSanPham");
					pstmt_cmt.setString(1, tenSanPham);
					pstmt_cmt.setString(2, " ");
					pstmt_cmt.executeUpdate();
					// System.out.println("Tên sản phẩm đã được sao chép thành công!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void coppytenKH() {
		try {
			// trang đầu
			String url_trangdau = "jdbc:mysql://localhost:3306/taikhoan";
			Connection conn_trangdau = DriverManager.getConnection(url_trangdau, "root", "2010");
			// trang 2
			String url_trangdich = "jdbc:mysql://localhost:3306/tinnhankhachhang";
			Connection conn_trangdich = DriverManager.getConnection(url_trangdich, "root", "2010");
			// trang 3
			String url_trangadmin = "jdbc:mysql://localhost:3306/tinnhanadmin";
			Connection conn_trangadmin = DriverManager.getConnection(url_trangadmin, "root", "2010");
			// cau lenh
			String select = "SELECT taikhoan FROM taikhoan";
			String insert = "INSERT INTO tinnhankhachhang (taikhoan,tinnhanvoiNV,tinnhanvoiADMIN) VALUES (?,?,?) ON DUPLICATE KEY UPDATE taikhoan = taikhoan";
			String insert2 = "INSERT INTO tinnhanadmin (taikhoan,tinnhan) VALUES (?,?) ON DUPLICATE KEY UPDATE taikhoan = taikhoan";
			try {
				// trang đầu
				PreparedStatement pstmt_select = conn_trangdau.prepareStatement(select);
				ResultSet rs = pstmt_select.executeQuery();
				// trang 2
				PreparedStatement pstmt_insert = conn_trangdich.prepareStatement(insert);
				// trang 3
				PreparedStatement pstmt_trang3 = conn_trangadmin.prepareStatement(insert2);
				while (rs.next()) {
					String tk = rs.getString("taikhoan");
					pstmt_insert.setString(1, tk);
					pstmt_insert.setString(2, "");
					pstmt_insert.setString(3, "");
					pstmt_trang3.setString(1, tk);
					pstmt_trang3.setString(2, "");
					pstmt_insert.executeUpdate();
					pstmt_trang3.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void taotkNV() {
		String tentk = textField_taikhoanNV.getText();
		String email = textField_emailNV.getText();
		String matkhau = textField_matkhauNV.getText();
		String hash = BCrypt.hashpw(matkhau, BCrypt.gensalt(12));
		if (tentk.isEmpty() || email.isEmpty() || matkhau.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui Lòng Điền Đầy Đủ Các Ô");
		}
		if (endsWithGmail(email)) {
			try {
				write("dangkytaikhoannhanvien" + "," + tentk + "," + email + "," + hash);

			} catch (Exception e) {
				e.printStackTrace();
			}
//			try {
//				String url = "jdbc:mysql://localhost:3306/taikhoannhanvien";
//				Connection conn = DriverManager.getConnection(url, "root", "2010");
//				String sql = "INSERT INTO taikhoannhanvien (tendangnhap, matkhau, email) VALUES (?,?,?)";
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, tentk);
//				pstmt.setString(2, hash);
//				pstmt.setString(3, email);
//				pstmt.executeUpdate();
//				JOptionPane.showMessageDialog(this, "Đăng Ký Thành Công");
//				this.setContentPane(panel_admin);
//				this.setBounds(100, 100, 724, 518);
//				this.setLocationRelativeTo(null);
//				textField_taikhoanNV.setText("");
//				textField_emailNV.setText("");
//				textField_matkhauNV.setText("");
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "Tên Tài Khoản Đã Tồn Tại");
//			}
		} else {
			JOptionPane.showMessageDialog(this, "Định Dạng Email Không Đúng");
		}

	}

	private void setUpSocket() {
		try {
			thread = new Thread() {
				@Override
				public void run() {
					try {
						socketOfClient = new Socket("localhost", 7777);
						os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
						is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
						String message;
						while (true) {
							message = is.readLine();
							if (message == null) {
								break;
							}
							String[] messageSplit = message.split(",");
							if (messageSplit[0].equals("get-id")) {
								setID(Integer.parseInt(messageSplit[1]));
							}
							if (messageSplit[0].equals("update-online-list")) {
								onlineList = new ArrayList<>();
								String[] onlineSplit = messageSplit[1].split("-");
								for (int i = 0; i < onlineSplit.length; i++) {
									onlineList.add(onlineSplit[i]);
								}
								updateCombobox();
							}
							if (messageSplit[0].equals("global-message")) {
								jTextArea1.setText(jTextArea1.getText() + "\n" + messageSplit[1] + "\n");
								jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
							}
							if (messageSplit[0].equals("done1")) {
								setContentPane(panel_admin);
								ten = "admin";
								setTitle(messageSplit[1]);
							}
							if (messageSplit[0].equals("done2")) {
								nhanvien();
								setTitle(messageSplit[1]);
								ten = messageSplit[1];
							}
							if (messageSplit[0].equals("done3")) {
								khachhang();
							}
							if (messageSplit[0].equals("matkhaukhongchinhxac")) {
								JOptionPane.showMessageDialog(taotaikhoanNV, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác");
							}
							if (messageSplit[0].equals("product")) {
								try {
									model.addElement(messageSplit[1]);
									// model_admin.addElement(messageSplit[1]);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							if (messageSplit[0].equals("selected")) {
								ImageIcon imageIcon = new ImageIcon(messageSplit[1]);
								lbl_anh.setIcon(imageIcon);
								textArea_gia.setText("          " + messageSplit[2] + "vnđ");
								textArea_CMT.setText(messageSplit[3]);
							}
							if (messageSplit[0].equals("productStaff")) {
								DefaultTableModel model_table_admin = (DefaultTableModel) table_cacloaidouong.getModel();
								DefaultTableModel model_table = (DefaultTableModel) table_sanpham_thanhtoan.getModel();
								boolean trangthai;
								if (messageSplit[4].equals("0")) {
									trangthai = false;
								} else {
									trangthai = true;
								}
								model_table
										.addRow(new Object[] { messageSplit[1] + "", messageSplit[2], messageSplit[3],
												trangthai ? "Hoạt Động" : "Không Hoạt Động", messageSplit[5] });
								model_table_admin
										.addRow(new Object[] { messageSplit[1] + "", messageSplit[2], messageSplit[3],
												trangthai ? "Hoạt Động" : "Không Hoạt Động", messageSplit[5] });
							}
							if (messageSplit[0].equals("thongke")) {
								try {
									DefaultTableModel model_table = (DefaultTableModel) table_thongke.getModel();
									DefaultTableModel model_table_tk = (DefaultTableModel) table_thongke_nv.getModel();
									int rowIndex = -1;
									int soluongdong = model_table_tk.getRowCount();
									for (int i = 0; i < soluongdong; i++) {
										if (model_table_tk.getValueAt(i, 0).equals(messageSplit[2])) {
											rowIndex = i;
											break;
										}
									}
									if (rowIndex >= 0) {
										model_table.setValueAt(messageSplit[1], rowIndex, 1);
										model_table_tk.setValueAt(messageSplit[1], rowIndex, 1);
									} else {
										model_table.addRow(new Object[] { messageSplit[2], messageSplit[1] });
										model_table_tk.addRow(new Object[] { messageSplit[2], messageSplit[1] });
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							if (messageSplit[0].equals("staff")) {
								DefaultTableModel model_table = (DefaultTableModel) table_qlnv.getModel();
								model_table.addRow(new Object[] { messageSplit[1], messageSplit[2], messageSplit[3],
										messageSplit[4], messageSplit[5], messageSplit[6] });
							}
							if (messageSplit[0].equals("chienluoc")) {
								textArea_luuchienluoc.setText(messageSplit[1]);
							}
							if (messageSplit[0].equals("dangkytaikhoannhanvien")) {
								String check = messageSplit[1].toString();
								if (check.equals("true")) {
									JOptionPane.showMessageDialog(taotaikhoanNV, "Đăng Ký Thành Công");
									setContentPane(panel_admin);
									validate();
									setBounds(100, 100, 724, 518);
									setLocationRelativeTo(null);
								} else {
									JOptionPane.showMessageDialog(taotaikhoanNV, "Tên Tài Khoản Đã Tồn Tại");
								}
							}
						}
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateCombobox() {
		jComboBox1.removeAllItems();
		jComboBox1.addItem("Gửi tất cả");
		String idString = "" + this.id;
		for (String e : onlineList) {
			if (!e.equals(idString)) {
				jComboBox1.addItem("Client " + e);
			}
		}

	}

	private void setID(int id) {
		this.id = id;
	}

	public void HienThiTinNhan() {
		try {
			String url = "jdbc:mysql://localhost:3306/tinnhanadmin";
			Connection conn = DriverManager.getConnection(url, "root", "2010");

			String sql = "SELECT tinnhan FROM tinnhanadmin WHERE taikhoan = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ten);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String tinnhan = rs.getString("tinnhan");
				jTextArea1.setText(tinnhan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UpdateTinNhan() {
		try {
			String url = "jdbc:mysql://localhost:3306/tinnhanadmin";
			Connection conn = DriverManager.getConnection(url, "root", "2010");
			String sql = "UPDATE tinnhanadmin SET tinnhan = ? WHERE taikhoan = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jTextArea1.getText());
			pstmt.setString(2, ten);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	 đọc truyện
	public void tap1() {
		System.out.println("Đang gọi phương thức tap1");
		try {
			String url = "https://trangquynh-trangquynh.blogspot.com/2016/07/trang-quynh-tap-1-sao-sang-xu-thanh.html";
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("img");
			for (Element link : links) {
				System.out.println("ok");
				URL imageUrl = new URL(link.attr("src"));
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				textPane.insertIcon(imageIcon);
				textPane.getDocument().insertString(textPane.getDocument().getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra");
		}
	}

	public void tap2() {
		textPane.setText("");
		try {
			String url = "https://trangquynh-trangquynh.blogspot.com/2016/07/trang-quynh-tap-2-at-nut-con-bo-hung.html";
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("img");
			for (Element link : links) {
				System.out.println("ok");
				URL imageUrl = new URL(link.attr("src"));
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				textPane.insertIcon(imageIcon);
				textPane.getDocument().insertString(textPane.getDocument().getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra");
		}
	}

	public void tap3() {
		try {
			String url = "https://trangquynh-trangquynh.blogspot.com/2016/07/trang-quynh-tap-3-cung-thanh-hoang.html";
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("img");
			for (Element link : links) {
				System.out.println("ok");
				URL imageUrl = new URL(link.attr("src"));
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				textPane.insertIcon(imageIcon);
				textPane.getDocument().insertString(textPane.getDocument().getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra");
		}
	}

	public void tap4() {
		try {
			String url = "https://trangquynh-trangquynh.blogspot.com/2016/07/trang-quynh-tap-4-mieng-ke-sang.html";
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("img");
			for (Element link : links) {
				System.out.println("ok");
				URL imageUrl = new URL(link.attr("src"));
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				textPane.insertIcon(imageIcon);
				textPane.getDocument().insertString(textPane.getDocument().getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra");
		}
	}

	public void tap5() {
		try {
			String url = "https://trangquynh-trangquynh.blogspot.com/2016/07/trang-quynh-tap-5-gheo-co-hang-nuoc.html";
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("img");
			for (Element link : links) {
				System.out.println("ok");
				URL imageUrl = new URL(link.attr("src"));
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				textPane.insertIcon(imageIcon);
				textPane.getDocument().insertString(textPane.getDocument().getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra");
		}
	}

	public void tap6() {
		try {
			String url = "http://trangquynh-trangquynh.blogspot.com/2016/07/trang-quynh-tap-6-e-nhat-danh-hoa.html";
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("img");
			for (Element link : links) {
				System.out.println("ok");
				URL imageUrl = new URL(link.attr("src"));
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				textPane.insertIcon(imageIcon);
				textPane.getDocument().insertString(textPane.getDocument().getLength(), "\n", null);
			}

		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra");
		}
	}

	public void khachhang() {

//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sanpham", "root", "2010");
//			String sql = "SELECT TenSanPham FROM sanpham";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				String tenSP = rs.getString("TenSanPham");
//				model.addElement(tenSP);
//				model_admin.addElement(tenSP);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String tk = this.textField_username.getText();
		String mk = this.textField_password.getText();
		this.setContentPane(this.recommend_khachhang);
		this.validate();
		this.textArea_tentk.setText(" " + tk);
		// this.coppyProduct();
		ten = tk;
		try {
			write("update");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void nhanvien() {
		this.setContentPane(this.thanhtoan);
		this.validate();
		// this.sanpham_congthanhtoan();
		//ten = "staff";
		try {
			write("productStaff");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(String message) throws IOException {
		os.write(message);
		os.newLine();
		os.flush();
	}

	public void xuatXML() {
		StringWriter writer = new StringWriter();
		DefaultTableModel model_table = (DefaultTableModel) table_qlnv.getModel();
		int soluongdong = model_table.getRowCount();
		for (int i = 0; i < soluongdong; i++) {
			String tenNV = model_table.getValueAt(i, 0) + "";
			String ngaySinh = model_table.getValueAt(i, 1) + "";
			String tuoi = model_table.getValueAt(i, 2) + "";
			String cccd = model_table.getValueAt(i, 3) + "";
			String dienthoai = model_table.getValueAt(i, 4) + "";
			String ngaylam = model_table.getValueAt(i, 5) + "";

			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				org.w3c.dom.Document doc = db.newDocument();

				org.w3c.dom.Element NhanVien = doc.createElement("nhanvien");
				org.w3c.dom.Element thongtin = doc.createElement("thongtin");

				org.w3c.dom.Element TenNV = doc.createElement("hoten");
				TenNV.setTextContent(tenNV);

				org.w3c.dom.Element NgaySinh = doc.createElement("ngaysinh");
				NgaySinh.setTextContent(ngaySinh);

				org.w3c.dom.Element Tuoi = doc.createElement("tuoi");
				Tuoi.setTextContent(tuoi);

				org.w3c.dom.Element CCCD = doc.createElement("cccd");
				CCCD.setTextContent(cccd);

				org.w3c.dom.Element SDT = doc.createElement("dienthoai");
				SDT.setTextContent(dienthoai);

				org.w3c.dom.Element ngayLam = doc.createElement("ngaylam");
				ngayLam.setTextContent(ngaylam);

				int stt = i + 1;
				String STT = stt + "";

				thongtin.setAttribute("stt", STT);
				thongtin.appendChild(TenNV);
				thongtin.appendChild(NgaySinh);
				thongtin.appendChild(Tuoi);
				thongtin.appendChild(CCCD);
				thongtin.appendChild(SDT);
				thongtin.appendChild(ngayLam);

				NhanVien.appendChild(thongtin);

				doc.appendChild(NhanVien);

				try {
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();// Tạo một Transformer để biến đổi DOM
																					// thành dạng XML.
					transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // khi biến đổi nội dung DOM thành XML, nó
																				// sẽ được in định dạng với các khoảng
																				// trắng để dễ đọc hơn.
					transformer.transform(new DOMSource(doc), new StreamResult(writer));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		String xmlString = writer.toString();
		System.out.println(xmlString);
		int luachon = JOptionPane.showConfirmDialog(this, "Bạn Muốn In Thông Tin Ra Không?");
		if (luachon == JOptionPane.YES_OPTION) {
			JFileChooser fc = new JFileChooser();
			int returnval = fc.showSaveDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fc.getSelectedFile();
					PrintWriter pw = new PrintWriter(file.getAbsolutePath(), "UTF-8");
					pw.print(xmlString);
					pw.flush();
					pw.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
