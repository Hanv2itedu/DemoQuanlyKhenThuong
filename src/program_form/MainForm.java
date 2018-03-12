package program_form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.tk.Toolkit;
import com.toedter.calendar.JDateChooser;

import controller.DBUtil;
import controller.ExcelExporter;
import controller.FileTypeFilter;
import model.DanhHieu;
import model.FilterSearch;
import model.HinhThucKhen;
import model.KTCaNhan;
import model.KTTapThe;
import model.Login;
import model.NhanVien;
import model.QuyetDinhKT;
import model.TapThe;
import program_form.MainForm;
import projectDao.DonViDao;
import projectDao.NhanVienDao;
import service.DanhHieuService;
import service.DonViService;
import service.HTKhenThuongService;
import service.KhenThuongCNService;
import service.KhenThuongTTService;
import service.NhanVienService;
import service.QDKTService;

public class MainForm {
	static String orderByStr;
	static String orderStatus;
	public JFrame frameMain;
	private JInternalFrame internalFrame;
	private JInternalFrame internalFrame_2; // quan ly gv frame
	private JInternalFrame internalFrame_3; // quan ly danh hieu thi dua
	private JInternalFrame internalFrame_4; // quanly hinh thuc khen thuong
	private JInternalFrame internalFrame_5; // qdkt 
	private JInternalFrame internalFrame_6; // Khen thuonwgr ca nhan
	private JInternalFrame internalFrame_7; // Khen thuonwgr tap the
	DonViService donViService = new DonViService();
	NhanVienDao nhanVienDao = new NhanVienDao();

	/*
	 * Connect to mysql ; and set Result
	 */
	private Connection conn;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
	/*
	 * Task DonVi
	 */
	private JComboBox donViComboBox;
	private JTable jTableDonVi;
	private JTextField kyHieuTxt;
	private JTextField txtTenDonVi;
	private JTextField txtIdDonVi;
	private JButton btnDonVi;
	JComboBox donViComboBox_2;
	private int idTTCha =100;

	/*
	 * Task Nhan Vien
	 */
	private JButton btnNhanVien;
	private NhanVien nhanVienForm;
	private JTable jTableNhanVien;
	private JComboBox jComboChonDonVi;
	private JComboBox jComboChonDonViCon;
	private NhanVienService nhanVienService = new NhanVienService();
	private List<TapThe> listTT = donViService.getAllTapThe(100); 
	private int idTT = 0;

	JTextField txtId;
	JTextField txtTen;
	JTextField txtSdt;
	JTextField txtMail;
	JTextField txtChucVu;

	JRadioButton radioButtonNam;
	JRadioButton radioButtonNu;
	JDateChooser txtNgaySinh;
	private JComboBox jComboChonDonVi1;
	private JComboBox jComboChonDonViCon1;
	private final ButtonGroup buttonGrGT = new ButtonGroup();

	// danh hieu
	private DanhHieu danhHieuForm;
	JTable jTableDanhHieu;
	JTextField txtTenDanhHieu;
	JComboBox jcomboChonLoaiDanhHieu;
	private JComboBox jcomboChonLoaiDanhHieu1;
	private JButton btnDanhHieu;
	private DanhHieuService danhHieuService = new DanhHieuService();
	String filter = "all";
	
	// Hình thức khen thưởng
	
	private HinhThucKhen ktForm;
	JTable jTableHTKhen;
	JTextField txtTenHTKhen;
	private JButton btnHTKhen;
	private HTKhenThuongService hTKTService = new HTKhenThuongService();
	
	// quyết đinh khen thươngr
	
	private QuyetDinhKT qdkt;
	JTable jTableQDKT;
	JTextField txtMaSoKT;
	JTextField txtTenQD;
	private QuyetDinhKT qdForm;
//	JTextField txtRefer;
	JFileChooser fileChooseRefer;
	JLabel resultUpLoadFile;
	JDateChooser txtNgayQD;
	private JButton btnQDKhen;
	private QDKTService qDKTService = new QDKTService();
	
	// khen thuong ca nhan
	int idTTKT = 0;
	KhenThuongCNService ktcnService = new KhenThuongCNService();
	private KTCaNhan ktCNForm;
	JTable jTableKTCN;
	JButton btnKTCN;
	JTextField txtSearchTen;
	JComboBox jComboChonDonViInKTCN;
	JComboBox jComboChonDonViConInKTCN;
	JComboBox searchTenNhanVienComboBox;
	JTextField txtKTCNInputTen;
	JComboBox inputTenNhanVienComboBox;
	JComboBox jComboChonDanhHieu;
	JComboBox jCOmboChonHinhThuc;
	JComboBox jComboChonQuyetDinh;
	JDateChooser txtNgayKT;
	List<String> listHTKhenTen = hTKTService.getAllTenHT();
	List<String> listDanhHieuTen;
	List<String> listQDTen = qDKTService.getAllNameQD();
	
	// panel khen thuong tap the
	int idKTTTCha = 0;
	int idKTTTCon = 0;
	KhenThuongTTService ktttService = new KhenThuongTTService();
	private KTTapThe ktTTForm;
	JTable jTableKTTT;
	JButton btnKTTT;
	JComboBox jComboChonDonViInKTTT;
	JComboBox jComboChonDonViConInKTTT;
	JComboBox searchTenTTComboBox;
	JTextField txtKTTTInputTenTT;
	JComboBox jComboInputTenTapThe;
	JComboBox jComboChonDanhHieuInTT;
	JComboBox jCOmboChonHinhThucInTT;
	JComboBox jComboChonQuyetDinhInTT;
	JDateChooser txtNgayKTTT;
	JTextField txtKTTTId;
	FilterSearch filterSearchOb;
	
	
	private JPanel timePanel; // khung chứa thời gian

	// Dong ho va lich ngay
	private JLabel timeSystem;
	private JLabel calendarBD;

	// Dieu khien tab
	public JTabbedPane tabbedPane;
	public JPanel quanLy;
	public JPanel thongKe;
	public JPanel uuTu;
	//tab thong ke
	ButtonGroup buttonGroupDhHt;
	JComboBox jComboTheoDh;
	JComboBox jComboTheoHT;
	JComboBox jComboTheoDV;
	JComboBox jComboTheoDVCon;
	JTextField txtTheoTenQD;
	JComboBox jComboTheoQD;
	JTextField txtNamKt;
	JButton btnChon;
	JComboBox jComboCNorTT;
	JTable jTableThongKe;
	JCheckBox chckbxTheoHinhThuc;
	JCheckBox chckbxTheoDanhHieu;
	JCheckBox chckbxTheoNam;
	JComboBox jComboFilterOrder;
	// tab thong ke nhan vien 2-3 nam lien tiep
	JComboBox jComboUTCNorTT;
	JComboBox jComboChonSoNam;
	JTable jTableThongKeSoNamLT;
	String[] filters = null;
	String[] filters1 = {"Chon", "Ten Don Vi", "Ngay Khen Thuong"};
	String[] filters2 = {"Chon", "Ten Nhan Vien","Ten Don Vi", "Ngay Khen Thuong"};
	// login :
	private String admin_username, pass;

	public static void main(String arg[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frameMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		initMainForm();
		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something Wrong??");
		}
		// donViDao.showTableTruong(jTableDonVi);
		clock();
	}

	private void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while (true) {
						Calendar cal = new GregorianCalendar();
						int second = cal.get(Calendar.SECOND);
						int minute = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR_OF_DAY);
						String thu;
						int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
						if (dayOfWeek == 1) {
							thu = "Chủ nhật";
						} else {
							thu = "Thứ " + Integer.toString(dayOfWeek);
						}
						int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH);
						int year = cal.get(Calendar.YEAR);

						timeSystem.setText(hour + ":" + minute + ":" + second);
//						timeSystemBD.setText(hour + ":" + minute + ":" + second);
//						timeSystemTK.setText(hour + ":" + minute + ":" + second);
						calendarBD.setText(thu + " ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);
//						calendarTK.setText(
//								thu + dayOfWeek + " ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);
						sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();

	}

	private void initMainForm() {
		frameMain = new JFrame();
		frameMain.setResizable(false);
		frameMain.setVisible(true);
		frameMain.setTitle("Phần Mềm Quản Lý Danh Hiệu Thi Đua Đại Học Đà Nẵng");
		frameMain.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("icon/DU_logo.jpg"));
		frameMain.setBounds(80, 0, 1191, 715);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setBounds(0, 0, 1189, 694);
		frameMain.getContentPane().add(tabbedPane);

		quanLy = new JPanel();
		tabbedPane.addTab("Quản Lý", new ImageIcon("icon/Control-Panel-icon (1).png"), quanLy, null);
		quanLy.setLayout(null);

		timePanel = new JPanel(); // khung thoi gian
		timePanel.setBackground(new Color(204, 204, 255));
		timePanel.setBounds(10, 11, 216, 106);
		timePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		quanLy.add(timePanel);

		timeSystem = new JLabel();
		timeSystem.setForeground(new Color(128, 0, 0));
		timeSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		timePanel.add(timeSystem);
		
		calendarBD = new JLabel();
		calendarBD.setForeground(new Color(128, 0, 0));
		calendarBD.setFont(new Font("Tahoma", Font.BOLD, 11));
		timePanel.add(calendarBD);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(204, 204, 255));
		menuPanel.setBounds(10, 128, 216, 517);
		menuPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		quanLy.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel bannerPanel = new JPanel();
		bannerPanel.setBounds(236, 11, 938, 106);
		bannerPanel.setBackground(new Color(204, 204, 255));
		bannerPanel.setLayout(null);
		quanLy.add(bannerPanel);
		
		JLabel bannelLabel = new JLabel(new ImageIcon("icon/logo_du.jpg"));
		bannelLabel.setBounds(0, 0, 158, 106);
		bannerPanel.add(bannelLabel);
		
		JLabel bannelLabel2 =new JLabel("BAN PHÁP CHẾ VÀ THI ĐUA ĐẠI HỌC ĐÀ NẴNG");
		bannelLabel2.setBounds(159,0, 781,106);
		bannelLabel2.setFont(new Font("Tohama", Font.BOLD, 32));
		bannerPanel.add(bannelLabel2);
		
		JPanel workLocationPanel = new JPanel();
		workLocationPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		workLocationPanel.setBounds(236, 128, 938, 517);
		quanLy.add(workLocationPanel);
		workLocationPanel.setLayout(null);

		frameQuanLyTapThe(workLocationPanel);
		frameQuanLyNhanVien(workLocationPanel);
		frameQuanLyDanhHieu(workLocationPanel);
		frameQuanLyHTKhen(workLocationPanel); 
		frameQuanLyQDKhen(workLocationPanel);
		frameQuanLyKTCN(workLocationPanel);
		frameQuanLyKTTT(workLocationPanel);
		btnDonVi = new JButton("Quản Lý Đơn Vị");
		btnDonVi.setBackground(UIManager.getColor("Button.darkShadow"));
		btnDonVi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				orderByStr = "id";
				orderStatus = "ASC";
				internalFrame.setVisible(true);
				internalFrame_2.setVisible(false);
				internalFrame_3.setVisible(false);
				internalFrame_4.setVisible(false);				
				internalFrame_5.setVisible(false);
				internalFrame_6.setVisible(false);
				internalFrame_7.setVisible(false);
				donViService.showTableTruong(jTableDonVi, false, 100 , orderByStr, orderStatus);
			}
		});
		// btn
		btnDonVi.setBounds(10, 10, 196, 40);
		menuPanel.add(btnDonVi);

		// nhan vien button
		btnNhanVien = new JButton("Quản Lý Nhân Viên ");
		btnNhanVien.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				orderByStr = "nv.id";
				orderStatus = "ASC";
				listTT = donViService.getAllTapThe(100);
				internalFrame_2.setVisible(true);
				internalFrame.setVisible(false);
				internalFrame_3.setVisible(false);
				 internalFrame_4.setVisible(false);
				 internalFrame_5.setVisible(false);
				 internalFrame_6.setVisible(false);
				 internalFrame_7.setVisible(false);
				nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, orderByStr, orderStatus, 0);
			}
		});
		btnNhanVien.setBounds(10, 80, 196, 40);
		menuPanel.add(btnNhanVien);

		// danh Hieu button
		btnDanhHieu = new JButton("Quản Lý Danh Hiệu ");
		btnDanhHieu.setBackground(UIManager.getColor("Button.darkShadow"));
		btnDanhHieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				internalFrame_3.setVisible(true);
				internalFrame.setVisible(false);
				internalFrame_2.setVisible(false);
				internalFrame_4.setVisible(false);
				internalFrame_5.setVisible(false);
				internalFrame_6.setVisible(false);
				internalFrame_7.setVisible(false);
				danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);
				
			}
		});
		btnDanhHieu.setBounds(10, 150, 196, 43);
		menuPanel.add(btnDanhHieu);
		
		// qd khen thuong
		btnQDKhen = new JButton("Quản Lý Quyết Định ");
		btnQDKhen.setBackground(UIManager.getColor("Button.darkShadow"));
		btnQDKhen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				internalFrame_4.setVisible(false);
				internalFrame.setVisible(false);
				internalFrame_2.setVisible(false);
				internalFrame_3.setVisible(false);
				internalFrame_5.setVisible(true);
				internalFrame_6.setVisible(false);
				internalFrame_7.setVisible(false);
				qDKTService.showAllQD(jTableQDKT);
			}
		});
		btnQDKhen.setBounds(10, 290, 196, 43);
		menuPanel.add(btnQDKhen);
		
		btnHTKhen = new JButton("Quản Lý Hình Thức Khen Thưởng ");
		btnHTKhen.setBackground(UIManager.getColor("Button.darkShadow"));
		btnHTKhen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				internalFrame_4.setVisible(true);
				internalFrame.setVisible(false);
				internalFrame_2.setVisible(false);
				internalFrame_3.setVisible(false);
				internalFrame_5.setVisible(false);
				internalFrame_6.setVisible(false);
				internalFrame_7.setVisible(false);
				hTKTService.showAllHTKT(jTableHTKhen);
			}
		});
		btnHTKhen.setBounds(10, 220, 196, 43);
		menuPanel.add(btnHTKhen);
		
		btnKTCN = new JButton("Quản Lý Khen Thưởng Cá Nhân ");
		btnKTCN.setBackground(UIManager.getColor("Button.darkShadow"));
		btnKTCN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				internalFrame_4.setVisible(false);
				internalFrame.setVisible(false);
				internalFrame_2.setVisible(false);
				internalFrame_3.setVisible(false);
				internalFrame_5.setVisible(false);
				internalFrame_6.setVisible(true);
				internalFrame_7.setVisible(false);
				ktcnService.showAllKTCN(jTableKTCN);
			}
		});
		btnKTCN.setBounds(10, 360, 196, 43);
		menuPanel.add(btnKTCN);
		
		btnKTTT = new JButton("Quản Lý Khen Thưởng Tập Thể ");
		btnKTTT.setBackground(UIManager.getColor("Button.darkShadow"));
		btnKTTT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				idKTTTCha = 0;
				internalFrame_4.setVisible(false);
				internalFrame.setVisible(false);
				internalFrame_2.setVisible(false);
				internalFrame_3.setVisible(false);
				internalFrame_5.setVisible(false);
				internalFrame_6.setVisible(false);
				internalFrame_7.setVisible(true);
				ktttService.showAllKTTTByDV(jTableKTTT, idKTTTCha);
			}
		});
		btnKTTT.setBounds(10, 430, 196, 43);
		menuPanel.add(btnKTTT);
		
		// TAB PANNED FOR SHOW INFO;
		thongKe = new JPanel();
		tabbedPane.addTab("Thống Kê", null, thongKe, null);
		thongKe.setLayout(null);
		frameThongKe(thongKe);
		
		// TAB SHOW SO NAM LIEN TIEP
		uuTu = new JPanel();
		tabbedPane.addTab("Cá nhân/Tập Thể ưu tú", null, uuTu, null);
		uuTu.setLayout(null);
		frameUuTu(uuTu);
	}
	private void frameUuTu(JPanel panelThongKe) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 125, 1184, 440);
		panelThongKe.add(scrollPane);
		jTableThongKeSoNamLT = new JTable();
		scrollPane.setViewportView(jTableThongKeSoNamLT); 
		
		JPanel panelBanner = new JPanel();
		panelBanner.setBackground(new Color(204, 204, 255));
		panelBanner.setBounds(0, 0, 1184, 122);
		panelThongKe.add(panelBanner);
		panelBanner.setLayout(null);
		
			JLabel labelOfBanner = new JLabel("Thống Kê Cá Nhân/Tập Thể Ưu Tú");
			labelOfBanner.setBounds(330, 10, 410 , 45);
			labelOfBanner.setFont(new Font("Tahoma", Font.BOLD, 20));
			panelBanner.add(labelOfBanner);
			
			JPanel panelFiltergr = new JPanel();
			panelFiltergr.setBounds(0, 60, 1184, 62);
			panelFiltergr.setBackground(new Color(204, 204, 255));
			panelBanner.add(panelFiltergr);
			panelFiltergr.setLayout(null);
				
				jComboUTCNorTT = new JComboBox<>();
				jComboUTCNorTT.setBounds(210, 5, 150, 24);
				jComboUTCNorTT.addItem("ca nhan");
				jComboUTCNorTT.addItem("tap the");
				panelFiltergr.add(jComboUTCNorTT);
				
				JLabel labelChonSoNam = new JLabel("Chọn Số Năm Liên Tếp");
				labelChonSoNam.setBounds(480, 5, 150, 24);
				panelFiltergr.add(labelChonSoNam);
				
				jComboChonSoNam = new JComboBox<>();
				jComboChonSoNam.setBounds(680, 5, 100, 24);
				jComboChonSoNam.addItem("2");
				jComboChonSoNam.addItem("3");
				panelFiltergr.add(jComboChonSoNam);
				
				JButton btnChonUT = new JButton("Chọn");
				btnChonUT.setBounds(860, 5, 80, 50);
				btnChonUT.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int soNam = Integer.parseInt(jComboChonSoNam.getSelectedItem().toString());
						if(jComboUTCNorTT.getSelectedItem().toString().equals("ca nhan")){
							ktcnService.showAllThongKeCNUT(jTableThongKeSoNamLT, soNam);
						}else{
							ktttService.showAllThongKeTTUT(jTableThongKeSoNamLT, soNam);
						}
					}
				});
				panelFiltergr.add(btnChonUT);
				
				
				JPanel panelOrderBy = new JPanel();
				panelOrderBy.setBounds(0, 570, 1184, 80);
				panelOrderBy.setBackground(new Color(204, 204, 255));
				panelThongKe.add(panelOrderBy);				
					
					
					JButton exporterToExel = new JButton("Exporter excel");
					exporterToExel.setSize(80, 40);
					exporterToExel.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setDialogTitle("Xuất ra file Excel");
							fc.setFileFilter(new FileTypeFilter(".xlsx", "excel work book"));
							int result = fc.showSaveDialog(null);
							if(result == JFileChooser.APPROVE_OPTION){
								File file = fc.getSelectedFile();
								try{
									ExcelExporter exporter = new ExcelExporter();
									exporter.exporterTable(jTableThongKeSoNamLT, file);
								}catch(IOException ioEx){
									
								}
								
							}
							
							
						}
					});
					panelOrderBy.add(exporterToExel);
					
					JButton btnprint = new JButton("Print");
					btnprint.setIcon(new ImageIcon("icon/Print-icon.png"));
					btnprint.setSize(80,40);
					btnprint.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							controller.Print print = new controller.Print();
							String namePaper = "Danh Sách ";
							namePaper += (jComboUTCNorTT.getSelectedItem().toString().equals("ca nhan")) ? "Cá Nhân" : "Tập Thể";
							namePaper += " Ưu Tú Trong " + (jComboChonSoNam.getSelectedItem().toString()) + " Năm Liền";
							
							print.Print(jTableThongKeSoNamLT, "namePaper");						}
					});
					panelOrderBy.add(btnprint);
	}

	private void frameThongKe(JPanel panelThongKe) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 125, 1184, 440);
		panelThongKe.add(scrollPane);
		jTableThongKe = new JTable();
		scrollPane.setViewportView(jTableThongKe); 
		
		
			
		JPanel panelBanner = new JPanel();
		panelBanner.setBackground(new Color(204, 204, 255));
		panelBanner.setBounds(0, 0, 1184, 122);
		panelThongKe.add(panelBanner);
		panelBanner.setLayout(null);
		
			JLabel labelOfBanner = new JLabel("Thống Kê");
			labelOfBanner.setBounds(530, 10, 100 , 45);
			labelOfBanner.setFont(new Font("Tahoma", Font.BOLD, 20));
			panelBanner.add(labelOfBanner);
			
			JPanel panelFiltergr = new JPanel();
			panelFiltergr.setBounds(0, 60, 1184, 62);
			panelFiltergr.setBackground(new Color(204, 204, 255));
			panelBanner.add(panelFiltergr);
			panelFiltergr.setLayout(null);
				
				jComboCNorTT = new JComboBox<>();
				jComboCNorTT.setBounds(10, 5, 100, 24);
				jComboCNorTT.addItem("ca nhan");
				jComboCNorTT.addItem("tap the");
				jComboCNorTT.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							listDanhHieuTen = danhHieuService.getAllNameDHByIsCaNhan(jComboCNorTT.getSelectedItem().toString());
							initListTenNvConComboBox(jComboTheoDh, listDanhHieuTen);
							if(jComboCNorTT.getSelectedItem().toString().equals("ca nhan")){
								filters = filters2;
							}else{
								filters = filters1;
							}
							jComboFilterOrder.removeAllItems();
							for(int i = 0; i< filters.length ; i++)
								jComboFilterOrder.addItem(filters[i]);
						}
					}
				});
				panelFiltergr.add(jComboCNorTT);
		
				buttonGroupDhHt = new ButtonGroup();
				
				jComboTheoDV = new JComboBox<>();
				jComboTheoDV.setBounds(120, 5, 169, 24);
				initChonDVConComboBox(jComboTheoDV, listTT);
				jComboTheoDV.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent itemEvent) {
						if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
							if (!jComboTheoDV.getSelectedItem().toString().equals("Chọn Đơn Vị")) {
								String nameTTCha = jComboTheoDV.getSelectedItem().toString();
								 idKTTTCha = donViService.convertTenDVConToId(nameTTCha, listTT);
								if(idKTTTCha >0){
									List<TapThe> listTTCha = donViService.getAllTapThe(idKTTTCha);
									initChonDVConComboBox(jComboTheoDVCon, listTTCha);
									jComboTheoDVCon.setEnabled(true);
									panelFiltergr.add(jComboTheoDVCon);
								}else{
									JOptionPane.showMessageDialog(null, "Lỗi");
								}
								
							} else {
								jComboTheoDVCon.setSelectedIndex(0);
								jComboTheoDVCon.setEnabled(false);
							}
						}

					}
				});
				panelFiltergr.add(jComboTheoDV);

				jComboTheoDVCon = new JComboBox<>();
				jComboTheoDVCon.setBounds(120, 32, 169, 24);
				
				chckbxTheoDanhHieu = new JCheckBox("Theo danh hiệu");
				buttonGroupDhHt.add(chckbxTheoDanhHieu);
				chckbxTheoDanhHieu.setBackground(new Color(204, 204, 255));
				chckbxTheoDanhHieu.setFont(new Font("Tahoma", Font.BOLD, 13));
				chckbxTheoDanhHieu.setBounds(300, 5, 169, 24);
				chckbxTheoDanhHieu.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxTheoDanhHieu.isSelected())
							jComboTheoDh.setEnabled(true);
							jComboTheoDh.showPopup();
							jComboTheoHT.setEnabled(false);
					}
				});
				panelFiltergr.add(chckbxTheoDanhHieu);
				
				listDanhHieuTen = danhHieuService.getAllNameDHByIsCaNhan(jComboCNorTT.getSelectedItem().toString());
				
				jComboTheoDh = new JComboBox();
				jComboTheoDh.setBounds(300, 32, 169, 24);
				initListTenNvConComboBox(jComboTheoDh, listDanhHieuTen);
				panelFiltergr.add(jComboTheoDh);
				
				chckbxTheoHinhThuc = new JCheckBox("Theo Hình Thức");
				buttonGroupDhHt.add(chckbxTheoHinhThuc);
				chckbxTheoHinhThuc.setBackground(new Color(204, 204, 255));
				chckbxTheoHinhThuc.setFont(new Font("Tahoma", Font.BOLD, 13));
				chckbxTheoHinhThuc.setBounds(480, 5, 169, 24);
				chckbxTheoHinhThuc.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxTheoHinhThuc.isSelected())
							jComboTheoHT.setEnabled(true);
							jComboTheoHT.showPopup();	
							jComboTheoDh.setEnabled(false);
					}
				});
				panelFiltergr.add(chckbxTheoHinhThuc);
				
				jComboTheoHT = new JComboBox();
				jComboTheoHT.setBounds(480, 32, 169, 24);
				initListTenNvConComboBox(jComboTheoHT, listHTKhenTen);
				panelFiltergr.add(jComboTheoHT);
				
				txtTheoTenQD = new JTextField();
				txtTheoTenQD.setBounds(660, 5, 169, 24);
				txtTheoTenQD.setText("Nhập tên hoặc ma so QDKT");
				txtTheoTenQD.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(txtTheoTenQD.getText() !=""){
							List<String> listTenQD = qDKTService.getAllNameLikeNameOrMSKT(txtTheoTenQD.getText());
							initListTenNvConComboBox(jComboTheoQD, listTenQD);
							jComboTheoQD.showPopup();
						}
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				txtTheoTenQD.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						txtTheoTenQD.setText("");
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						txtTheoTenQD.setText("");
						
					}
				});
				panelFiltergr.add(txtTheoTenQD);
				
				jComboTheoQD = new JComboBox<>();
				jComboTheoQD.setBounds(660, 32, 169, 24);
				jComboTheoQD.addItemListener(new ItemListener() {
									
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							txtTheoTenQD.setText(jComboTheoQD.getSelectedItem().toString());
						}
						if(e.getStateChange() == ItemEvent.SELECTED && jComboTheoQD.getSelectedIndex() ==0)
							txtTheoTenQD.setText(null);
					}
				});
				panelFiltergr.add(jComboTheoQD);
				
				chckbxTheoNam = new JCheckBox("Theo Năm");
				chckbxTheoNam.setBackground(new Color(204, 204, 255));
				chckbxTheoNam.setFont(new Font("Tahoma", Font.BOLD, 13));
				chckbxTheoNam.setBounds(840, 5, 169, 24);
				chckbxTheoNam.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxTheoNam.isSelected()){
							txtNamKt.setEnabled(true);
							txtNamKt.setFocusable(true);
						}else{
							txtNamKt.setEnabled(false);
						}
					}
				});
				panelFiltergr.add(chckbxTheoNam);
				
				txtNamKt = new JTextField();
				txtNamKt.setBounds(840, 32, 169, 24);
				txtNamKt.setEnabled(false);
				panelFiltergr.add(txtNamKt);
				
				btnChon = new JButton("Chọn");
				btnChon.setBounds(1020, 15, 80, 50);
				btnChon.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						filterSearchOb = getThongKeKTCNForm();
						if(jComboCNorTT.getSelectedItem().toString().equals("ca nhan")){
							ktcnService.showAllThongKeByFilters(jTableThongKe, filterSearchOb);
						}else{
							ktttService.showAllThongKeByFilters(jTableThongKe, filterSearchOb);
						}
					}
				});
				panelFiltergr.add(btnChon);
				
				JPanel panelOrderBy = new JPanel();
				panelOrderBy.setBounds(0, 570, 1184, 80);
				panelOrderBy.setBackground(new Color(204, 204, 255));
				panelThongKe.add(panelOrderBy);
					
					JLabel labelFilters = new JLabel("Chọn Cột Cần Sắp Xếp :");
					labelFilters.setSize(150, 24);
					panelOrderBy.add(labelFilters);
					
					
					
					if(jComboCNorTT.getSelectedItem().toString().equals("ca nhan")){
						filters = filters2;
					}else{
						filters = filters1;
					}
					
					
					jComboFilterOrder = new JComboBox<>();
					for(int i = 0; i< filters.length ; i++)
						jComboFilterOrder.addItem(filters[i]);
					jComboFilterOrder.setSize(100, 24);
					panelOrderBy.add(jComboFilterOrder);
					
					JButton btnA_Z = new JButton("A-Z");
					btnA_Z.setSize(60, 40);
					btnA_Z.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							filterSearchOb = getThongKeKTCNForm();
							filterSearchOb.setOrderStatus(" ASC");
							int orderBy = jComboFilterOrder.getSelectedIndex();
							if(orderBy == 0) JOptionPane.showMessageDialog(null, "Bạn cần chọn 1 trường để sắp xếp");
							if(jComboCNorTT.getSelectedItem().toString().equals("ca nhan")){
								switch(orderBy){
									case 1:
										filterSearchOb.setOrderBy("nv.ten");
										break;
									case 2:
										filterSearchOb.setOrderBy("tt.ten_tap_the");
										break;
									case 3: 
										filterSearchOb.setOrderBy("ktcn.ngay_kt");
										break;
									default:
										filterSearchOb.setOrderBy(null);
								}
								ktcnService.showAllThongKeByFilters(jTableThongKe, filterSearchOb);
							}else{
								switch(orderBy){								
									case 1:
										filterSearchOb.setOrderBy("tt.ten_tap_the");
										break;
									case 2: 
										filterSearchOb.setOrderBy("ktcn.ngay_kt");
										break;
									default:
										filterSearchOb.setOrderBy(null);
								}
								ktttService.showAllThongKeByFilters(jTableThongKe, filterSearchOb);
							}
						}
					});
					panelOrderBy.add(btnA_Z);
					
					JButton btnZ_A = new JButton("Z_A");
					btnZ_A.setSize(60, 40);
					btnZ_A.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							filterSearchOb = getThongKeKTCNForm();
							filterSearchOb.setOrderStatus(" DESC");
							int orderBy = jComboFilterOrder.getSelectedIndex();
							if(orderBy == 0) JOptionPane.showMessageDialog(null, "Bạn cần chọn 1 trường để sắp xếp");
							if(jComboCNorTT.getSelectedItem().toString().equals("ca nhan")){
								switch(orderBy){
									case 1:
										filterSearchOb.setOrderBy("nv.ten");
										break;
									case 2:
										filterSearchOb.setOrderBy("tt.ten_tap_the");
										break;
									case 3: 
										filterSearchOb.setOrderBy("ktcn.ngay_kt");
										break;
									default:
										filterSearchOb.setOrderBy(null);
								}
								ktcnService.showAllThongKeByFilters(jTableThongKe, filterSearchOb);
							}else{
								switch(orderBy){								
									case 1:
										filterSearchOb.setOrderBy("tt.ten_tap_the");
										break;
									case 2: 
										filterSearchOb.setOrderBy("ktcn.ngay_khen_thuong");
										break;
									default:
										filterSearchOb.setOrderBy(null);
								}
								ktttService.showAllThongKeByFilters(jTableThongKe, filterSearchOb);
							}
						}
					});
					panelOrderBy.add(btnZ_A);
					
					JButton exporterToExel = new JButton("Exporter excel");
					exporterToExel.setSize(80, 40);
					exporterToExel.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setDialogTitle("Xuất ra file Excel");
							fc.setFileFilter(new FileTypeFilter(".xlsx", "excel work book"));
							int result = fc.showSaveDialog(null);
							if(result == JFileChooser.APPROVE_OPTION){
								File file = fc.getSelectedFile();
								try{
									ExcelExporter exporter = new ExcelExporter();
									exporter.exporterTable(jTableThongKe, file);
								}catch(IOException ioEx){
									
								}
								
							}
							
							
						}
					});
					panelOrderBy.add(exporterToExel);
					
					JButton btnprint = new JButton("Print");
					btnprint.setIcon(new ImageIcon("icon/Print-icon.png"));
					btnprint.setSize(80,40);
					btnprint.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							controller.Print print = new controller.Print();
							print.Print(jTableThongKe, "Danh Sách ");						}
					});
					panelOrderBy.add(btnprint);
				
		// table 
		
				
	}

	private void frameQuanLyKTTT(JPanel workLocationPanel) {
		listDanhHieuTen = danhHieuService.getAllNameDHByIsCaNhan("tap the");
		internalFrame_7 = new JInternalFrame("Quản Lý Khen Thuỏng Tập Thể");
		internalFrame_7.setClosable(true);
		internalFrame_7.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame_7.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame_7);
		internalFrame_7.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 0, 934, 35);
		internalFrame_7.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Khen Thuỏng Tập Thể");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		JPanel jPanelNhapDV = new JPanel();
		jPanelNhapDV.setBounds(0, 36, 934, 30);
		jPanelNhapDV.setLayout(null);
		internalFrame_7.getContentPane().add(jPanelNhapDV);

		JLabel jLabelChonDonVi = new JLabel("Chọn Đơn Vị :");
		jLabelChonDonVi.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelChonDonVi.setBounds(0, 0, 180, 20);
		jPanelNhapDV.add(jLabelChonDonVi);

		jComboChonDonViInKTTT = new JComboBox<>();
		jComboChonDonViInKTTT.setBounds(200, 0, 160, 20);
		initChonDVConComboBox(jComboChonDonViInKTTT, listTT);
		jComboChonDonViInKTTT.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (!jComboChonDonViInKTTT.getSelectedItem().toString().equals("Chọn Đơn Vị")) {
						String nameTTCha = jComboChonDonViInKTTT.getSelectedItem().toString();
						 idKTTTCha = donViService.convertTenDVConToId(nameTTCha, listTT);
						if(idKTTTCha >0){
							List<TapThe> listTTCha = donViService.getAllTapThe(idKTTTCha);
							initChonDVConComboBox(jComboChonDonViConInKTTT, listTTCha);
							jComboChonDonViConInKTTT.setEnabled(true);
							jPanelNhapDV.add(jComboChonDonViConInKTTT);
						}else{
							JOptionPane.showMessageDialog(null, "Lỗi");
						}
						
					} else {
						jComboChonDonViConInKTTT.setSelectedIndex(0);
						jComboChonDonViConInKTTT.setEnabled(false);
					}
				}

			}
		});
		jPanelNhapDV.add(jComboChonDonViInKTTT);

		jComboChonDonViConInKTTT = new JComboBox<>();
		jComboChonDonViConInKTTT.setBounds(380, 0, 160, 20);

		JButton jButtonChonDv = new JButton("Chọn");
		jButtonChonDv.setBounds(560, 0, 60, 20);
		jButtonChonDv.setIcon(new ImageIcon("icon/Search-icon (7).png"));
		jButtonChonDv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
				if (jComboChonDonViConInKTTT.getSelectedIndex() !=0) {
					List<TapThe> listTTCha = donViService.getAllTapThe(idKTTTCha);
					idKTTTCon = donViService.convertTenDVConToId(jComboChonDonViConInKTTT.getSelectedItem().toString(), listTTCha);
					ktttService.showAllKTTTByDV(jTableKTTT, idKTTTCon);
				} else {
					if (jComboChonDonViInKTTT.getSelectedIndex() != 0) {
						ktttService.showAllKTTTByDV(jTableKTTT, idKTTTCha);
					} else{
						ktttService.showAllKTTTByDV(jTableKTTT, 0);
					}
				}
				

			}
		});
		jPanelNhapDV.add(jButtonChonDv);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 914, 173);
		internalFrame_7.getContentPane().add(scrollPane);
		// creat table;
		jTableKTTT = new JTable();
		jTableKTTT.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = jTableKTTT.getSelectedRow();
					txtKTTTId.setText(jTableKTTT.getValueAt(row, 1).toString());
					List<String> inputTen = donViService.getAllNameLikeName(jTableKTTT.getValueAt(row, 2).toString());
					txtKTTTInputTenTT.setText(inputTen.get(0));
					if(jTableKTTT.getValueAt(row, 4).equals(""))
						jCOmboChonHinhThucInTT.setSelectedIndex(0);
					else
						jCOmboChonHinhThucInTT.setSelectedItem(jTableKTTT.getValueAt(row, 4));
					if(jTableKTTT.getValueAt(row, 3).equals(""))
						jComboChonDanhHieuInTT.setSelectedIndex(0);
					else
						jComboChonDanhHieuInTT.setSelectedItem(jTableKTTT.getValueAt(row, 3));
					jComboChonQuyetDinhInTT.setSelectedItem(jTableKTTT.getValueAt(row, 6));;
					txtNgayKTTT.setDate((Date) jTableKTTT.getValueAt(row, 5));
				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		scrollPane.setViewportView(jTableKTTT);
////		
////		// panelMenu edit
////
				JPanel crudPanel = new JPanel();
				crudPanel.setBackground(new Color(204, 204, 255));
				crudPanel.setBounds(730, 257, 194, 220);
				internalFrame_7.getContentPane().add(crudPanel);
				crudPanel.setLayout(null);
				JButton btnThem = new JButton("Thêm");
				btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
				btnThem.setBounds(33, 24, 116, 30);
				btnThem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						ktTTForm = getKTTTForm();
						if(ktTTForm!= null){
							ktttService.addAKTTT(ktTTForm);
							ktttService.showAllKTTTByDV(jTableKTTT, idKTTTCon);;
						}

					}

				});
				crudPanel.add(btnThem);

				JButton btnSua = new JButton("Sửa");
				btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
				btnSua.setBounds(33, 73, 116, 30);
				btnSua.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableKTTT);
						String idKhenThuongTT = "";
						if(rowCount.size() == 1){
							if (jTableKTTT.getValueAt(rowCount.get(0), 0).toString() == "true"){
								idKhenThuongTT =  jTableKTTT.getValueAt(rowCount.get(0), 1).toString();
								ktTTForm = getKTTTForm();
								if (ktTTForm != null && idKhenThuongTT !="") {
									ktttService.updateAKTTT(ktTTForm, idKhenThuongTT);
									ktttService.showAllKTTTByDV(jTableKTTT, idKTTTCon);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Bạn Chưa chọn khen thưởng nào cần sửa");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Nên click chỉ một khen thưởng cần sửa!");
						}		
						
					}
				});
				crudPanel.add(btnSua);

				JButton btnXoa = new JButton("Xóa");
				btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
				btnXoa.setBounds(33, 123, 116, 30);
				btnXoa.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableKTTT);
						if(rowCount.size() ==1){
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Bạn có muốn xóa  : " + rowCount.size() + " khen thưởng không?",
									"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, options[1]);
							if(n == 0){								
									String ktDel =  jTableKTTT.getValueAt(rowCount.get(0), 1).toString();
									if(ktDel != "")
										ktttService.deleteAKTTT(ktDel);
									ktttService.showAllKTTTByDV(jTableKTTT, idKTTTCon);
							}
							
						}else if(rowCount.size() == 0){
							JOptionPane.showMessageDialog(null, "Bạn Chưa Click Chọn Danh Hiệu Cần Xóa");
						} else
							JOptionPane.showMessageDialog(null, "Bạn đã chọn hơn 1 dòng! bạn nên click chỉ 1 dòng cần xóa!");

					}
				});
				crudPanel.add(btnXoa);

				JButton btnReset = new JButton("Reset");
				btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
				btnReset.setBounds(33, 173, 116, 30);
				btnReset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						txtKTTTInputTenTT.setText("");
						txtNgayKTTT.setDate(null);
						jComboChonDanhHieuInTT.setSelectedIndex(0);
						jCOmboChonHinhThucInTT.setSelectedIndex(0);
						jComboChonQuyetDinhInTT.setSelectedIndex(0);
					
					}
				});
				crudPanel.add(btnReset);

////
////				// input information panel
////
				JPanel inputInforPanel = new JPanel();
				inputInforPanel.setBackground(new Color(204, 204, 255));
				inputInforPanel.setBounds(10, 289, 706, 188);
				internalFrame_7.getContentPane().add(inputInforPanel);
				inputInforPanel.setLayout(null);
				
				JLabel lblTnLp1 = new JLabel("Mã Số Khen Thưởng:");
				lblTnLp1.setForeground(new Color(0, 0, 128));
				lblTnLp1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp1.setBounds(20, 10, 150, 20);
				inputInforPanel.add(lblTnLp1);
				
				txtKTTTId =new JTextField();
				txtKTTTId.setBounds(150, 10, 185, 24);
				inputInforPanel.add(txtKTTTId);
				
				JLabel lblTnLp = new JLabel("Tên Tập Thể:");
				lblTnLp.setForeground(new Color(0, 0, 128));
				lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp.setBounds(20, 50, 150, 20);
				inputInforPanel.add(lblTnLp);
				
				txtKTTTInputTenTT = new JTextField();
				txtKTTTInputTenTT.setBounds(150, 50, 185, 24);
				txtKTTTInputTenTT.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(txtKTTTInputTenTT.getText() !=""){
							List<String> listTenDonVi = donViService.getAllNameLikeName(txtKTTTInputTenTT.getText());
							initListTenNvConComboBox(jComboInputTenTapThe, listTenDonVi);
							jComboInputTenTapThe.showPopup();
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						
					}
					
				});
				inputInforPanel.add(txtKTTTInputTenTT);
				
				jComboInputTenTapThe = new JComboBox();				
				jComboInputTenTapThe.setBounds(360, 50, 300, 24);
				jComboInputTenTapThe.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							txtKTTTInputTenTT.setText(jComboInputTenTapThe.getSelectedItem().toString());
						}
							
						
					}
				});
				inputInforPanel.add(jComboInputTenTapThe);

				JLabel lblTnLp2 = new JLabel("Hinh Thuc :");
				lblTnLp2.setForeground(new Color(0, 0, 128));
				lblTnLp2.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp2.setBounds(20, 90, 150, 20);
				inputInforPanel.add(lblTnLp2);
				
				jCOmboChonHinhThucInTT = new JComboBox<>();
				jCOmboChonHinhThucInTT.setBounds(150, 90, 185, 24);
				initListTenNvConComboBox(jCOmboChonHinhThucInTT, listHTKhenTen);
				inputInforPanel.add(jCOmboChonHinhThucInTT);
				
				JLabel lblTnLp3 = new JLabel("Danh Hiệu :");
				lblTnLp3.setForeground(new Color(0, 0, 128));
				lblTnLp3.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp3.setBounds(20, 130, 150, 20);
				inputInforPanel.add(lblTnLp3);
				
				jComboChonDanhHieuInTT = new JComboBox<>();
				jComboChonDanhHieuInTT.setBounds(150, 130, 185, 24);
				initListTenNvConComboBox(jComboChonDanhHieuInTT, listDanhHieuTen);
				inputInforPanel.add(jComboChonDanhHieuInTT);
				
				JLabel lblNgaySinh = new JLabel("Ngày Khen Thưởng:");
				lblNgaySinh.setForeground(new Color(0, 0, 128));
				lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNgaySinh.setBounds(360, 90, 185, 24);
				inputInforPanel.add(lblNgaySinh);

				txtNgayKTTT = new JDateChooser();
				txtNgayKTTT.setBounds(490, 90, 185, 24);
				txtNgayKTTT.setDateFormatString("yyyy-MM-dd");
				inputInforPanel.add(txtNgayKTTT);
				
				JLabel upLoadFile = new JLabel("Quyết Định");
				upLoadFile.setForeground(new Color(0, 0, 128));
				upLoadFile.setFont(new Font("Tahoma", Font.BOLD, 13));
				upLoadFile.setBounds(360, 130, 185, 24);
				inputInforPanel.add(upLoadFile);
				
				jComboChonQuyetDinhInTT = new JComboBox<>();
				jComboChonQuyetDinhInTT.setBounds(490, 130, 185, 24);
				initListTenNvConComboBox(jComboChonQuyetDinhInTT, listQDTen);
				inputInforPanel.add(jComboChonQuyetDinhInTT);
		
	}

	private void frameQuanLyKTCN(JPanel workLocationPanel) {
		
		
		listDanhHieuTen = danhHieuService.getAllNameDHByIsCaNhan("ca nhan");
		internalFrame_6 = new JInternalFrame("Quản Lý Khen Thuỏng Cá Nhân");
		internalFrame_6.setClosable(true);
		internalFrame_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame_6.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame_6);
		internalFrame_6.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 0, 934, 35);
		internalFrame_6.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Khen Thuỏng Cá Nhân");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		JPanel jPanelNhapDV = new JPanel();
		jPanelNhapDV.setBounds(0, 36, 934, 30);
		jPanelNhapDV.setLayout(null);
		internalFrame_6.getContentPane().add(jPanelNhapDV);

		JLabel jLabelChonDonVi = new JLabel("Chọn Đơn Vị :");
		jLabelChonDonVi.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelChonDonVi.setBounds(0, 0, 180, 20);
		jPanelNhapDV.add(jLabelChonDonVi);

		jComboChonDonViInKTCN = new JComboBox<>();
		jComboChonDonViInKTCN.setBounds(200, 0, 160, 20);
		initChonDVConComboBox(jComboChonDonViInKTCN, listTT);
		jComboChonDonViInKTCN.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (!jComboChonDonViInKTCN.getSelectedItem().toString().equals("Chọn Đơn Vị")) {
						String nameTTCha = jComboChonDonViInKTCN.getSelectedItem().toString();
						 idTTCha = donViService.convertTenDVConToId(nameTTCha, listTT);
						if(idTTCha >0){
							List<TapThe> listTTCha = donViService.getAllTapThe(idTTCha);
							initChonDVConComboBox(jComboChonDonViConInKTCN, listTTCha);
							jComboChonDonViConInKTCN.setEnabled(true);
							jPanelNhapDV.add(jComboChonDonViConInKTCN);
						}else{
							JOptionPane.showMessageDialog(null, "Lỗi");
						}
						
					} else {
						jComboChonDonViConInKTCN.setSelectedIndex(0);
						jComboChonDonViConInKTCN.setEnabled(false);
					}
				}

			}
		});
		jPanelNhapDV.add(jComboChonDonViInKTCN);

		jComboChonDonViConInKTCN = new JComboBox<>();
		jComboChonDonViConInKTCN.setBounds(380, 0, 160, 20);

		JButton jButtonChonDv = new JButton("Chọn");
		jButtonChonDv.setBounds(560, 0, 60, 20);
		jButtonChonDv.setIcon(new ImageIcon("icon/Search-icon (7).png"));
		jButtonChonDv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
				if (jComboChonDonViConInKTCN.getSelectedIndex() != 0) {
					List<TapThe> listTTCha = donViService.getAllTapThe(idTTCha);
					idTTKT = donViService.convertTenDVConToId(jComboChonDonViConInKTCN.getSelectedItem().toString(), listTTCha);
					ktcnService.showAllKTCNByDV(jTableKTCN, idTTKT);
				} else {
					if (jComboChonDonViInKTCN.getSelectedIndex() != 0) {
						idTTKT = donViService.convertTenDVConToId(jComboChonDonViInKTCN.getSelectedItem().toString(), listTT);
						ktcnService.showAllKTCNByIdTTCha(jTableKTCN, idTTKT);
					} else{
						ktcnService.showAllKTCNByDV(jTableKTCN, idTTKT);
					}
				}
				

			}
		});
		jPanelNhapDV.add(jButtonChonDv);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 914, 173);
		internalFrame_6.getContentPane().add(scrollPane);
		// creat table;
		jTableKTCN = new JTable();
		jTableKTCN.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = jTableKTCN.getSelectedRow();
					txtKTCNInputTen.setText(jTableKTCN.getValueAt(row, 2).toString());
					if(jTableKTCN.getValueAt(row, 4).equals(""))
						jCOmboChonHinhThuc.setSelectedIndex(0);
					else
						jCOmboChonHinhThuc.setSelectedItem(jTableKTCN.getValueAt(row, 4));
					if(jTableKTCN.getValueAt(row, 3).equals(""))
						jComboChonDanhHieu.setSelectedIndex(0);
					else
						jComboChonDanhHieu.setSelectedItem(jTableKTCN.getValueAt(row, 3));
					jComboChonQuyetDinh.setSelectedItem(jTableKTCN.getValueAt(row, 6));;
					txtNgayKT.setDate((Date) jTableKTCN.getValueAt(row, 5));
				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		scrollPane.setViewportView(jTableKTCN);
//		
//		// panelMenu edit
//
				JPanel crudPanel = new JPanel();
				crudPanel.setBackground(new Color(204, 204, 255));
				crudPanel.setBounds(730, 257, 194, 220);
				internalFrame_6.getContentPane().add(crudPanel);
				crudPanel.setLayout(null);
				JButton btnThem = new JButton("Thêm");
				btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
				btnThem.setBounds(33, 24, 116, 30);
				btnThem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						ktCNForm = getKTCNForm();
						if(ktCNForm!= null){
							ktcnService.addAKTCN(ktCNForm);
							ktcnService.showAllKTCNByDV(jTableKTCN, 0);
						}

					}

				});
				crudPanel.add(btnThem);

				JButton btnSua = new JButton("Sửa");
				btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
				btnSua.setBounds(33, 73, 116, 30);
				btnSua.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableKTCN);
						int idKTCN = 0;
						if(rowCount.size() == 1){
							if (jTableKTCN.getValueAt(rowCount.get(0), 0).toString() == "true"){
								idKTCN = (int) jTableKTCN.getValueAt(rowCount.get(0), 1);
								ktCNForm = getKTCNForm();
								if (ktCNForm != null && idKTCN > 0) {
									ktcnService.updateAKTCN(ktCNForm, idKTCN);
									ktcnService.showAllKTCNByDV(jTableKTCN, 0);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Bạn Chưa chọn khen thưởng nào cần sửa");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Nên click chỉ một khen thưởng cần sửa!");
						}		
						
					}
				});
				crudPanel.add(btnSua);

				JButton btnXoa = new JButton("Xóa");
				btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
				btnXoa.setBounds(33, 123, 116, 30);
				btnXoa.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableKTCN);
						if(rowCount.size() ==1){
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Bạn có muốn xóa  : " + rowCount.size() + " khen thưởng không?",
									"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, options[1]);
							if(n == 0){								
									int ktDel = (int) jTableKTCN.getValueAt(rowCount.get(0), 1);
									if(ktDel !=0)
										ktcnService.deleteAKTCN(ktDel);
									ktcnService.showAllKTCN(jTableKTCN);
							}
							
						}else if(rowCount.size() == 0){
							JOptionPane.showMessageDialog(null, "Bạn Chưa Click Chọn Danh Hiệu Cần Xóa");
						} else
							JOptionPane.showMessageDialog(null, "Bạn đã chọn hơn 1 dòng! bạn nên click chỉ 1 dòng cần xóa!");

					}
				});
				crudPanel.add(btnXoa);

				JButton btnReset = new JButton("Reset");
				btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
				btnReset.setBounds(33, 173, 116, 30);
				btnReset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						txtKTCNInputTen.setText("");
						txtNgayKT.setDate(null);
						jComboChonDanhHieu.setSelectedIndex(0);
						jCOmboChonHinhThuc.setSelectedIndex(0);
						jComboChonQuyetDinh.setSelectedIndex(0);
					
					}
				});
				crudPanel.add(btnReset);
				
				// menu bar
				JLabel lblSort = new JLabel("Tìm theo tên nhân viên :");
				lblSort.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblSort.setBounds(10, 256, 185, 24);
				internalFrame_6.getContentPane().add(lblSort);
				
				txtSearchTen = new JTextField();
				txtSearchTen.setBounds(180, 256, 185, 24);
				txtSearchTen.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(txtSearchTen.getText() !=""){
							List<String> listTenNV = nhanVienService.getAllTenNhanViensLikeName(txtSearchTen.getText());
							initListTenNvConComboBox(searchTenNhanVienComboBox, listTenNV);
							searchTenNhanVienComboBox.showPopup();
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						
					}
					
				});
				internalFrame_6.getContentPane().add(txtSearchTen);
				
				searchTenNhanVienComboBox = new JComboBox();				
				searchTenNhanVienComboBox.setBounds(320, 256, 300, 24);
				searchTenNhanVienComboBox.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							String[] nameFields = searchTenNhanVienComboBox.getSelectedItem().toString().split("/");
							txtSearchTen.setText(nameFields[0]);
						}
							
						
					}
				});
				internalFrame_6.getContentPane().add(searchTenNhanVienComboBox);

				JButton btnOk = new JButton("Chọn");
				btnOk.setIcon(new ImageIcon("icon/Search-icon (3).png"));
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
						ktcnService.showAllKTCNByTenNhanVien(jTableKTCN, txtSearchTen.getText());
					}
				});
				btnOk.setBounds(623, 256, 83, 24);
				internalFrame_6.getContentPane().add(btnOk);
//
//				// input information panel
//
				JPanel inputInforPanel = new JPanel();
				inputInforPanel.setBackground(new Color(204, 204, 255));
				inputInforPanel.setBounds(10, 289, 706, 188);
				internalFrame_6.getContentPane().add(inputInforPanel);
				inputInforPanel.setLayout(null);
				
				JLabel lblTnLp = new JLabel("Tên Nhân Viên:");
				lblTnLp.setForeground(new Color(0, 0, 128));
				lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp.setBounds(20, 10, 150, 20);
				inputInforPanel.add(lblTnLp);
				
				txtKTCNInputTen = new JTextField();
				txtKTCNInputTen.setBounds(150, 10, 185, 24);
				txtKTCNInputTen.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(txtKTCNInputTen.getText() !=""){
							List<String> listTenNV = nhanVienService.getAllTenNhanViensLikeName(txtKTCNInputTen.getText());
							initListTenNvConComboBox(inputTenNhanVienComboBox, listTenNV);
							inputTenNhanVienComboBox.showPopup();
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						
					}
					
				});
				inputInforPanel.add(txtKTCNInputTen);
				
				inputTenNhanVienComboBox = new JComboBox();				
				inputTenNhanVienComboBox.setBounds(360, 10, 300, 24);
				inputTenNhanVienComboBox.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							String[] infoOfNhanVien = inputTenNhanVienComboBox.getSelectedItem().toString().split("/");							
							idTTKT = donViService.convertTenTTToId(infoOfNhanVien[1], idTTCha);
							txtKTCNInputTen.setText(infoOfNhanVien[0]);
						}
							
						
					}
				});
				inputInforPanel.add(inputTenNhanVienComboBox);

				JLabel lblTnLp2 = new JLabel("Hinh Thuc :");
				lblTnLp2.setForeground(new Color(0, 0, 128));
				lblTnLp2.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp2.setBounds(20, 50, 150, 20);
				inputInforPanel.add(lblTnLp2);
				
				jCOmboChonHinhThuc = new JComboBox<>();
				jCOmboChonHinhThuc.setBounds(150, 50, 185, 24);
				initListTenNvConComboBox(jCOmboChonHinhThuc, listHTKhenTen);
				inputInforPanel.add(jCOmboChonHinhThuc);
				
				JLabel lblTnLp3 = new JLabel("Danh Hiệu :");
				lblTnLp3.setForeground(new Color(0, 0, 128));
				lblTnLp3.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp3.setBounds(20, 90, 150, 20);
				inputInforPanel.add(lblTnLp3);
				
				jComboChonDanhHieu = new JComboBox<>();
				jComboChonDanhHieu.setBounds(150, 90, 185, 24);
				initListTenNvConComboBox(jComboChonDanhHieu, listDanhHieuTen);
				inputInforPanel.add(jComboChonDanhHieu);
				
				JLabel lblNgaySinh = new JLabel("Ngày Khen Thưởng:");
				lblNgaySinh.setForeground(new Color(0, 0, 128));
				lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNgaySinh.setBounds(360, 50, 185, 24);
				inputInforPanel.add(lblNgaySinh);

				txtNgayKT = new JDateChooser();
				txtNgayKT.setBounds(490, 50, 185, 24);
				txtNgayKT.setDateFormatString("yyyy-MM-dd");
				inputInforPanel.add(txtNgayKT);
				
				JLabel upLoadFile = new JLabel("Quyết Định");
				upLoadFile.setForeground(new Color(0, 0, 128));
				upLoadFile.setFont(new Font("Tahoma", Font.BOLD, 13));
				upLoadFile.setBounds(360, 90, 185, 24);
				inputInforPanel.add(upLoadFile);
				
				jComboChonQuyetDinh = new JComboBox<>();
				jComboChonQuyetDinh.setBounds(490, 90, 185, 24);
				initListTenNvConComboBox(jComboChonQuyetDinh, listQDTen);
				inputInforPanel.add(jComboChonQuyetDinh);
				
				
		
	}

	private void frameQuanLyQDKhen(JPanel workLocationPanel) {
		internalFrame_5 = new JInternalFrame("Quản Lý Quyết Định Khen Thuỏng");
		internalFrame_5.setClosable(true);
		internalFrame_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame_5.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame_5);
		internalFrame_5.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 0, 934, 35);
		internalFrame_5.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Quyết Định Khen Thưởng");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 914, 173);
		internalFrame_5.getContentPane().add(scrollPane);
		// creat table;
		jTableQDKT = new JTable();
		jTableQDKT.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = jTableQDKT.getSelectedRow();
					txtMaSoKT.setText(jTableQDKT.getValueAt(row, 1).toString());
					txtTenQD.setText(jTableQDKT.getValueAt(row, 2).toString());
					txtNgayQD.setDate((Date) jTableQDKT.getValueAt(row, 4));
				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		scrollPane.setViewportView(jTableQDKT);
//		
//		// panelMenu edit
//
				JPanel crudPanel = new JPanel();
				crudPanel.setBackground(new Color(204, 204, 255));
				crudPanel.setBounds(730, 257, 194, 220);
				internalFrame_5.getContentPane().add(crudPanel);
				crudPanel.setLayout(null);
				JButton btnThem = new JButton("Thêm");
				btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
				btnThem.setBounds(33, 24, 116, 30);
				btnThem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						qdForm = getQDFrom();
						 if(qDKTService.findOneQDByMaQD(txtMaSoKT.getText().toString())!=null){
							JOptionPane.showMessageDialog(null, "Đã Có Sẵn Quyết Định Có Mã Số "+txtMaSoKT.getText().toString() +"!");
											
						 }else if (qdForm != null) {
							qDKTService.addAQD(qdForm);
							qDKTService.showAllQD(jTableQDKT);
						}
					}

				});
				crudPanel.add(btnThem);

				JButton btnSua = new JButton("Sửa");
				btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
				btnSua.setBounds(33, 73, 116, 30);
				btnSua.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableQDKT);
						String maSoQD = "";
						if(rowCount.size() == 1){
							if (jTableQDKT.getValueAt(rowCount.get(0), 0).toString() == "true"){
								maSoQD = jTableQDKT.getValueAt(rowCount.get(0), 1).toString();
								qdForm = getQDFrom();
								if (qdForm != null && maSoQD.length() > 0) {
									qDKTService.updateAQD(qdForm,maSoQD);
									qDKTService.showAllQD(jTableQDKT);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Bạn Chưa chọn quyết định nào cần sửa");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Nên click chỉ một quyết định cần sửa!");
						}
						
						

					}
				});
				crudPanel.add(btnSua);

				JButton btnXoa = new JButton("Xóa");
				btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
				btnXoa.setBounds(33, 123, 116, 30);
				btnXoa.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableQDKT);
						if(rowCount.size() ==1){
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Bạn có muốn xóa  : " + rowCount.size() + " quyết định khen thưởng không?",
									"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, options[1]);
							if(n == 0){								
									String qdDel = jTableQDKT.getValueAt(rowCount.get(0), 1).toString();
									qDKTService.deleteAQD(qdDel);
									qDKTService.showAllQD(jTableQDKT);
							}
							
						}else if(rowCount.size() == 0){
							JOptionPane.showMessageDialog(null, "Bạn Chưa Click Chọn Danh Hiệu Cần Xóa");
						} else
							JOptionPane.showMessageDialog(null, "Bạn đã chọn hơn 1 dòng! bạn nên click chỉ 1 dòng cần xóa!");

					}
				});
				crudPanel.add(btnXoa);

				JButton btnReset = new JButton("Reset");
				btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
				btnReset.setBounds(33, 173, 116, 30);
				btnReset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						

					}
				});
				crudPanel.add(btnReset);
//
//				// input information panel
//
				JPanel inputInforPanel = new JPanel();
				inputInforPanel.setBackground(new Color(204, 204, 255));
				inputInforPanel.setBounds(10, 289, 706, 188);
				internalFrame_5.getContentPane().add(inputInforPanel);
				inputInforPanel.setLayout(null);
				
				JLabel lblTnLp = new JLabel("Mã Số QĐ :");
				lblTnLp.setForeground(new Color(0, 0, 128));
				lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp.setBounds(20, 70, 188, 20);
				inputInforPanel.add(lblTnLp);

				txtMaSoKT = new JTextField();
				txtMaSoKT.setBackground(SystemColor.textHighlightText);
				txtMaSoKT.setBounds(140, 70, 150, 20);
				inputInforPanel.add(txtMaSoKT);
				
				JLabel lblTnLp2 = new JLabel("Tên Quyết Định :");
				lblTnLp2.setForeground(new Color(0, 0, 128));
				lblTnLp2.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp2.setBounds(20, 120, 188, 20);
				inputInforPanel.add(lblTnLp2);
				
				txtTenQD = new JTextField();
				txtTenQD.setBackground(SystemColor.textHighlightText);
				txtTenQD.setBounds(140, 120, 150, 20);
				inputInforPanel.add(txtTenQD);
				
				JLabel lblNgaySinh = new JLabel("Ngày Ra Quyết Định:");
				lblNgaySinh.setForeground(new Color(0, 0, 128));
				lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNgaySinh.setBounds(300, 70, 188, 20);
				inputInforPanel.add(lblNgaySinh);

				txtNgayQD = new JDateChooser();
				txtNgayQD.setBounds(450, 70, 188, 20);
				txtNgayQD.setDateFormatString("yyyy-MM-dd");
				inputInforPanel.add(txtNgayQD);
				
				JLabel upLoadFile = new JLabel("Thêm Văn Bản(nếu có):");
				upLoadFile.setForeground(new Color(0, 0, 128));
				upLoadFile.setFont(new Font("Tahoma", Font.BOLD, 13));
				upLoadFile.setBounds(300, 120, 188, 20);
				inputInforPanel.add(upLoadFile);
				
				resultUpLoadFile = new JLabel("");
				resultUpLoadFile.setBounds(530, 120,80,20);
				resultUpLoadFile.setFont(new Font("Tahoma", Font.BOLD, 13));
				inputInforPanel.add(resultUpLoadFile);
				
				fileChooseRefer = new JFileChooser();
				JButton showFileDialogButton = new JButton("Open File");
				showFileDialogButton.setBounds(450, 120,80,20);
			    showFileDialogButton.addActionListener(new ActionListener() {
			         @Override
			         public void actionPerformed(ActionEvent e) {
			            int returnVal = fileChooseRefer.showOpenDialog(frameMain);
			            if (returnVal == JFileChooser.APPROVE_OPTION) {
			               File file = fileChooseRefer.getSelectedFile();			               
			               resultUpLoadFile.setText(file.getAbsolutePath());
			            }
			            else{
			            	resultUpLoadFile.setText(null);           
			            }      
			         }
			      });
			    inputInforPanel.add(showFileDialogButton);

		
	}

	public void frameQuanLyDanhHieu(JPanel workLocationPanel) {
		internalFrame_3 = new JInternalFrame("Quản Lý Danh Hiệu");
		internalFrame_3.setClosable(true);
		internalFrame_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame_3.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame_3);
		internalFrame_3.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 0, 934, 35);
		internalFrame_3.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Danh Sách Danh Hiệu");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		// Panel Chon Loại Danh Hieu

		JPanel jPanelNhapDV = new JPanel();
		jPanelNhapDV.setBounds(0, 36, 934, 30);
		jPanelNhapDV.setLayout(null);
		internalFrame_3.getContentPane().add(jPanelNhapDV);

		JLabel jLabelChonDonVi = new JLabel("Chọn Danh Hiệu :");
		jLabelChonDonVi.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelChonDonVi.setBounds(0, 0, 180, 20);
		jPanelNhapDV.add(jLabelChonDonVi);
		
		jcomboChonLoaiDanhHieu = new JComboBox<>();
		jcomboChonLoaiDanhHieu.setBounds(200, 0, 160, 20);
		initDanhHieuComboBox(jcomboChonLoaiDanhHieu);
		jPanelNhapDV.add(jcomboChonLoaiDanhHieu);
		
		JButton jButtonChonDv = new JButton("Chọn");
		jButtonChonDv.setBounds(560, 0, 60, 20);
		jButtonChonDv.setIcon(new ImageIcon("icon/Search-icon (7).png"));
		jButtonChonDv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(jcomboChonLoaiDanhHieu.getSelectedIndex()){
					case 0 : {	
						filter = "all";
						danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);
						break;
					}
					case 1: {
						filter = "ca nhan";
						danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);
						break;
					}
					case 2:{
						filter = "tap the";
						danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);
						break;
					}
				}
				
			}
		});
		jPanelNhapDV.add(jButtonChonDv);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 914, 173);
		internalFrame_3.getContentPane().add(scrollPane);

		// creat table;
		jTableDanhHieu = new JTable();
		scrollPane.setViewportView(jTableDanhHieu);
		
		// panelMenu edit

				JPanel crudPanel = new JPanel();
				crudPanel.setBackground(new Color(204, 204, 255));
				crudPanel.setBounds(730, 257, 194, 220);
				internalFrame_3.getContentPane().add(crudPanel);
				crudPanel.setLayout(null);
				JButton btnThem = new JButton("Thêm");
				btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
				btnThem.setBounds(33, 24, 116, 30);
				btnThem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						danhHieuForm = getDanhHieuForm();
						if (danhHieuForm != null) {
							filter = (jcomboChonLoaiDanhHieu1.getSelectedIndex() == 1) ? "ca nhan" : "tap the";
							danhHieuService.addADanhHieu(danhHieuForm);
							danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);
						}
					}

				});
				crudPanel.add(btnThem);

				JButton btnSua = new JButton("Sửa");
				btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
				btnSua.setBounds(33, 73, 116, 30);
				btnSua.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableDanhHieu);
						String danhHieuName = "";
						if(rowCount.size() == 1){
							if (jTableDanhHieu.getValueAt(rowCount.get(0), 0).toString() == "true"){
								danhHieuName = jTableDanhHieu.getValueAt(rowCount.get(0), 2).toString();
								danhHieuForm = getDanhHieuForm();
								if (danhHieuForm != null && danhHieuName.length() > 0) {
									danhHieuService.updateANhanVien(danhHieuForm, danhHieuName);
									danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Bạn Chưa chọn nhân viên cần sửa");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Nên click chỉ một danh hiệu cần sửa!");
						}
						
						

					}
				});
				crudPanel.add(btnSua);

				JButton btnXoa = new JButton("Xóa");
				btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
				btnXoa.setBounds(33, 123, 116, 30);
				btnXoa.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableDanhHieu);
						System.out.println("rowCount.toString() :"+rowCount.toString());
						if(rowCount.size() ==1){
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Bạn có muốn xóa  : " + rowCount.size() + " danh hiệu không?",
									"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, options[1]);
							if(n == 0){								
									String danhHieuDelete = jTableDanhHieu.getValueAt(rowCount.get(0), 2).toString();
									danhHieuService.deleteADanhHieu(danhHieuDelete);
									danhHieuService.showAllDanhHieu(jTableDanhHieu, filter);								
							}
							
						}else if(rowCount.size() == 0){
							JOptionPane.showMessageDialog(null, "Bạn Chưa Click Chọn Danh Hiệu Cần Xóa");
						} else
							JOptionPane.showMessageDialog(null, "Bạn đã chọn hơn 1 dòng! bạn nên click chỉ 1 dòng cần xóa!");

					}
				});
				crudPanel.add(btnXoa);

				JButton btnReset = new JButton("Reset");
				btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
				btnReset.setBounds(33, 173, 116, 30);
				btnReset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						

					}
				});
				crudPanel.add(btnReset);

				// input information panel

				JPanel inputInforPanel = new JPanel();
				inputInforPanel.setBackground(new Color(204, 204, 255));
				;
				inputInforPanel.setBounds(10, 289, 706, 188);
				internalFrame_3.getContentPane().add(inputInforPanel);
				inputInforPanel.setLayout(null);
				
				JLabel lblTnLp = new JLabel("Tên Danh Hieu:");
				lblTnLp.setForeground(new Color(0, 0, 128));
				lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp.setBounds(20, 70, 188, 20);
				inputInforPanel.add(lblTnLp);

				txtTenDanhHieu = new JTextField();
				txtTenDanhHieu.setBackground(SystemColor.textHighlightText);
				txtTenDanhHieu.setBounds(160, 70, 150, 20);
				inputInforPanel.add(txtTenDanhHieu);
				
				JLabel lblDhLp = new JLabel("Chọn loại :");
				lblDhLp.setForeground(new Color(0, 0, 128));
				lblDhLp.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDhLp.setBounds(320, 70, 150, 20);
				inputInforPanel.add(lblDhLp);
				
				jcomboChonLoaiDanhHieu1 = new JComboBox<>();
				initDanhHieuComboBox(jcomboChonLoaiDanhHieu1);
				jcomboChonLoaiDanhHieu1.setBounds(420, 70, 150, 20);
				inputInforPanel.add(jcomboChonLoaiDanhHieu1);


	}


	public void frameQuanLyHTKhen(JPanel workLocationPanel) {
		internalFrame_4 = new JInternalFrame("Quản Lý Hình Thức Khen Thuỏng");
		internalFrame_4.setClosable(true);
		internalFrame_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame_4.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame_4);
		internalFrame_4.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 0, 934, 35);
		internalFrame_4.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Hình Thức Khen Thưởng");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 914, 173);
		internalFrame_4.getContentPane().add(scrollPane);
		// creat table;
		jTableHTKhen = new JTable();
		scrollPane.setViewportView(jTableHTKhen);
//		
//		// panelMenu edit
//
				JPanel crudPanel = new JPanel();
				crudPanel.setBackground(new Color(204, 204, 255));
				crudPanel.setBounds(730, 257, 194, 220);
				internalFrame_4.getContentPane().add(crudPanel);
				crudPanel.setLayout(null);
				JButton btnThem = new JButton("Thêm");
				btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
				btnThem.setBounds(33, 24, 116, 30);
				btnThem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						ktForm = getHTForm();
						if (ktForm != null) {
							hTKTService.addAHTKT(ktForm);
							hTKTService.showAllHTKT(jTableHTKhen);
						}
					}

				});
				crudPanel.add(btnThem);

				JButton btnSua = new JButton("Sửa");
				btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
				btnSua.setBounds(33, 73, 116, 30);
				btnSua.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableHTKhen);
						String hTKName = "";
						if(rowCount.size() == 1){
							if (jTableHTKhen.getValueAt(rowCount.get(0), 0).toString() == "true"){
								hTKName = jTableHTKhen.getValueAt(rowCount.get(0), 2).toString();
								ktForm = getHTForm();
								if (ktForm != null && hTKName.length() > 0) {
									hTKTService.updateAHTKT(ktForm, hTKName);
									hTKTService.showAllHTKT(jTableHTKhen);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Bạn Chưa chọn Hình thức cần sửa");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Nên click chỉ một Hình thức cần sửa!");
						}
						
						

					}
				});
				crudPanel.add(btnSua);

				JButton btnXoa = new JButton("Xóa");
				btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
				btnXoa.setBounds(33, 123, 116, 30);
				btnXoa.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<Integer> rowCount = rowsSelectCount(jTableHTKhen);
						if(rowCount.size() ==1){
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Bạn có muốn xóa  : " + rowCount.size() + " hình thức khen thưởng không?",
									"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, options[1]);
							if(n == 0){								
									String danhHieuDelete = jTableHTKhen.getValueAt(rowCount.get(0), 2).toString();
									hTKTService.deleteAHT(danhHieuDelete);
									hTKTService.showAllHTKT(jTableHTKhen);								
							}
							
						}else if(rowCount.size() == 0){
							JOptionPane.showMessageDialog(null, "Bạn Chưa Click Chọn Danh Hiệu Cần Xóa");
						} else
							JOptionPane.showMessageDialog(null, "Bạn đã chọn hơn 1 dòng! bạn nên click chỉ 1 dòng cần xóa!");

					}
				});
				crudPanel.add(btnXoa);

				JButton btnReset = new JButton("Reset");
				btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
				btnReset.setBounds(33, 173, 116, 30);
				btnReset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						

					}
				});
				crudPanel.add(btnReset);
//
//				// input information panel
//
				JPanel inputInforPanel = new JPanel();
				inputInforPanel.setBackground(new Color(204, 204, 255));
				inputInforPanel.setBounds(10, 289, 706, 188);
				internalFrame_4.getContentPane().add(inputInforPanel);
				inputInforPanel.setLayout(null);
				
				JLabel lblTnLp = new JLabel("Tên Hình Thức Khen Thưởng:");
				lblTnLp.setForeground(new Color(0, 0, 128));
				lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTnLp.setBounds(20, 70, 188, 20);
				inputInforPanel.add(lblTnLp);

				txtTenHTKhen = new JTextField();
				txtTenHTKhen.setBackground(SystemColor.textHighlightText);
				txtTenHTKhen.setBounds(200, 70, 150, 20);
				inputInforPanel.add(txtTenHTKhen);
				
				


	}

	public void frameQuanLyTapThe(JPanel workLocationPanel) {
		internalFrame = new JInternalFrame("Quản Lý Danh Sách Tập Thể");
		internalFrame.setClosable(true);
		internalFrame.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 29, 934, 35);
		internalFrame.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Danh Sách Tập Thể/Đơn Vị");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 914, 173);
		internalFrame.getContentPane().add(scrollPane);

		// creat table;
		jTableDonVi = new JTable();
		// when mouse click
		jTableDonVi.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = jTableDonVi.getSelectedRow();
					kyHieuTxt.setText(jTableDonVi.getValueAt(row, 2).toString());
					txtTenDonVi.setText(jTableDonVi.getValueAt(row, 3).toString());
					donViComboBox_2.setSelectedItem(jTableDonVi.getValueAt(row, 4).toString());
					System.out.println(jTableDonVi.getValueAt(row, 4).toString());
				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// todo in here ...

		scrollPane.setViewportView(jTableDonVi);

		// panelMenu edit

		// crud button group
		JPanel crudPanel = new JPanel();
		crudPanel.setBackground(new Color(204, 204, 255));
		crudPanel.setBounds(730, 257, 194, 220);
		internalFrame.getContentPane().add(crudPanel);
		crudPanel.setLayout(null);
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TapThe tapTheOb = new TapThe();
				tapTheOb = getTTForm();
				if(donViService.findOneByName(tapTheOb.getKyHieuTT()) == null){
					donViService.addADonVi(tapTheOb);
					donViService.showTableTruong(jTableDonVi, false, idTTCha, orderByStr, orderStatus);
					listTT = donViService.getAllTapThe(100);
					updateDVButtonListner();
				}else{
					JOptionPane.showMessageDialog(null, "Đã tồn tại một đơn vị có chung ký hiệu!");
				}
				
					
			}
		});
		btnThem.setBounds(33, 24, 116, 30);
		crudPanel.add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
		btnSua.setBounds(33, 73, 116, 30);
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Integer> rowCount = rowsSelectCount(jTableDonVi);
				String kyHieuTT = "";
				if(rowCount.size() == 1){
					if (jTableDonVi.getValueAt(rowCount.get(0), 0).toString() == "true"){
						kyHieuTT = jTableDonVi.getValueAt(rowCount.get(0), 2).toString();
						TapThe tt = new TapThe();
						tt = getTTForm();
						if (tt != null && kyHieuTT.length() > 0) {
							donViService.updateATT(tt,kyHieuTT);
							donViService.showTableTruong(jTableDonVi, false, idTTCha, orderByStr, orderStatus);
							jComboChonDonViCon.setEnabled(true);
							listTT = donViService.getAllTapThe(100);
							updateDVButtonListner();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Bạn Chưa chọn Hình thức cần sửa");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Nên click chỉ một Hình thức cần sửa!");
				}
				
			}
		});
		crudPanel.add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
		btnXoa.setBounds(33, 123, 116, 30);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Integer> rowCount = rowsSelectCount(jTableDonVi);
				if(rowCount.size() ==1){
					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(null,
							"Bạn có muốn xóa  : " + rowCount.size() + " Tập Thể khen thưởng không?",
							"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, options[1]);
					if(n == 0){								
							String kyHieuTTDelete = jTableDonVi.getValueAt(rowCount.get(0), 2).toString();
							donViService.deleteATT(kyHieuTTDelete);	
							donViService.showTableTruong(jTableDonVi, false, idTTCha, orderByStr, orderStatus);
							listTT = donViService.getAllTapThe(100);
							updateDVButtonListner();
					}
					
				}else if(rowCount.size() == 0){
					JOptionPane.showMessageDialog(null, "Bạn Chưa Click Chọn Danh Hiệu Cần Xóa");
				} else
					JOptionPane.showMessageDialog(null, "Bạn đã chọn hơn 1 dòng! bạn nên click chỉ 1 dòng cần xóa!");

			}
		});
		crudPanel.add(btnXoa);

		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
		btnReset.setBounds(33, 173, 116, 30);
		crudPanel.add(btnReset);

		// input information panel

		JPanel inputInforPanel = new JPanel();
		inputInforPanel.setBackground(new Color(204, 204, 255));
		;
		inputInforPanel.setBounds(10, 289, 706, 188);
		internalFrame.getContentPane().add(inputInforPanel);
		inputInforPanel.setLayout(null);

		JLabel dvChaJLable = new JLabel("Trực Thuộc Đơn Vị");
		dvChaJLable.setForeground(new Color(0, 0, 128));
		dvChaJLable.setFont(new Font("Tahoma", Font.BOLD, 13));
		dvChaJLable.setBounds(430, 25, 188, 20);
		inputInforPanel.add(dvChaJLable);

		donViComboBox_2 = new JComboBox();
		initChonDVConComboBox(donViComboBox_2, listTT);
		donViComboBox_2.setBounds(430, 70, 188, 20);
		inputInforPanel.add(donViComboBox_2);

		JLabel lblMLp_1 = new JLabel("Ký Hiệu :");
		lblMLp_1.setForeground(new Color(0, 0, 128));
		lblMLp_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMLp_1.setBounds(20, 25, 188, 20);
		inputInforPanel.add(lblMLp_1);

		kyHieuTxt = new JTextField();
		kyHieuTxt.setBackground(SystemColor.textHighlightText);
		kyHieuTxt.setBounds(160, 25, 188, 20);
		inputInforPanel.add(kyHieuTxt);

		JLabel lblTnLp = new JLabel("Tên Đơn Vị:");
		lblTnLp.setForeground(new Color(0, 0, 128));
		lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnLp.setBounds(20, 70, 188, 20);
		inputInforPanel.add(lblTnLp);

		txtTenDonVi = new JTextField();
		txtTenDonVi.setBackground(SystemColor.textHighlightText);
		txtTenDonVi.setBounds(160, 70, 188, 20);
		inputInforPanel.add(txtTenDonVi);

		// menu bar
		JLabel lblSort = new JLabel("Xem DS Theo Đơn Vị :");
		lblSort.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSort.setBounds(273, 256, 185, 14);
		internalFrame.getContentPane().add(lblSort);

		donViComboBox = new JComboBox();
		initChonDVConComboBox(donViComboBox, listTT);
		donViComboBox.setBounds(455, 256, 140, 20);
		internalFrame.getContentPane().add(donViComboBox);

		JButton btnOk = new JButton("Chọn");
		btnOk.setIcon(new ImageIcon("icon/Search-icon (3).png"));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderByStr = "id";
				orderStatus = "ASC";
				String nameTT = donViComboBox.getSelectedItem().toString();
				idTTCha =(nameTT == "Chọn Đơn Vị") ? 100 : donViService.convertTenDVConToId(nameTT, listTT);
				donViService.showTableTruong(jTableDonVi, false, idTTCha, orderByStr, orderStatus );

			}
		});
		btnOk.setBounds(623, 252, 83, 23);
		internalFrame.getContentPane().add(btnOk);

		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.setIcon(new ImageIcon("icon/Select-icon.png"));
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				donViService.showTableTruong(jTableDonVi, true, idTTCha, orderByStr, orderStatus );
			}
		});
		btnSelectAll.setBounds(20, 253, 112, 23);
		internalFrame.getContentPane().add(btnSelectAll);

		JButton btnUnselect = new JButton("Unselect");
		btnUnselect.setIcon(new ImageIcon("icon/close-icon.png"));
		btnUnselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				donViService.showTableTruong(jTableDonVi, false, idTTCha, orderByStr, orderStatus );
			}
		});
		btnUnselect.setBounds(142, 253, 110, 23);
		internalFrame.getContentPane().add(btnUnselect);

	}

	public void frameQuanLyNhanVien(JPanel workLocationPanel) {
		
		internalFrame_2 = new JInternalFrame("Quản Lý Nhân Viên");
		internalFrame_2.setClosable(true);
		internalFrame_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		internalFrame_2.setBounds(0, 0, 938, 517);

		workLocationPanel.add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		JPanel nhapTTPanel = new JPanel();
		nhapTTPanel.setBackground(SystemColor.activeCaption);
		nhapTTPanel.setBounds(0, 0, 934, 35);
		internalFrame_2.getContentPane().add(nhapTTPanel);

		JLabel nhapTTLabel = new JLabel("Quản Lý Danh Sách Nhân Viên");
		nhapTTLabel.setForeground(new Color(0, 0, 0));
		nhapTTLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nhapTTPanel.add(nhapTTLabel);

		// Panel Chon DonVi

		JPanel jPanelNhapDV = new JPanel();
		jPanelNhapDV.setBounds(0, 36, 934, 30);
		jPanelNhapDV.setLayout(null);
		internalFrame_2.getContentPane().add(jPanelNhapDV);

		JLabel jLabelChonDonVi = new JLabel("Chọn Đơn Vị :");
		jLabelChonDonVi.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelChonDonVi.setBounds(0, 0, 180, 20);
		jPanelNhapDV.add(jLabelChonDonVi);

		jComboChonDonVi = new JComboBox<>();
		jComboChonDonVi.setBounds(200, 0, 160, 20);
		initChonDVConComboBox(jComboChonDonVi, listTT);
		jComboChonDonVi.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (!jComboChonDonVi.getSelectedItem().toString().equals("Chọn Đơn Vị")) {
						String nameTTCha = jComboChonDonVi.getSelectedItem().toString();
						 idTTCha = donViService.convertTenDVConToId(nameTTCha, listTT);
						if(idTTCha >0){
							List<TapThe> listTTCha = donViService.getAllTapThe(idTTCha);
							initChonDVConComboBox(jComboChonDonViCon, listTTCha);
							jComboChonDonViCon.setEnabled(true);
							jPanelNhapDV.add(jComboChonDonViCon);
						}else{
							JOptionPane.showMessageDialog(null, "Lỗi");
						}
						
					} else {
						jComboChonDonViCon.setSelectedIndex(0);
						jComboChonDonViCon.setEnabled(false);
					}
				}

			}
		});
		jPanelNhapDV.add(jComboChonDonVi);

		jComboChonDonViCon = new JComboBox<>();
		jComboChonDonViCon.setBounds(380, 0, 160, 20);

		JButton jButtonChonDv = new JButton("Chọn");
		jButtonChonDv.setBounds(560, 0, 60, 20);
		jButtonChonDv.setIcon(new ImageIcon("icon/Search-icon (7).png"));
		jButtonChonDv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jComboChonDonViCon.getSelectedIndex() != 0) {
					List<TapThe> listTTCha = donViService.getAllTapThe(idTTCha);
					idTT = donViService.convertTenDVConToId(jComboChonDonViCon.getSelectedItem().toString(), listTTCha);
					nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, "nv.ten", "ASC", idTT);
				} else {
					if (jComboChonDonVi.getSelectedIndex() != 0) {
						idTT = donViService.convertTenDVConToId(jComboChonDonVi.getSelectedItem().toString(), listTT);
						nhanVienService.showAllNVTheoTruong(jTableNhanVien, false, "nv.ten", "ASC", idTT);
					} else {
						JOptionPane.showMessageDialog(null, "Bạn Cần Phải Chọn Đơn Vị");
					}
				}
				

			}
		});
		jPanelNhapDV.add(jButtonChonDv);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 914, 173);
		internalFrame_2.getContentPane().add(scrollPane);

		// creat table;
		jTableNhanVien = new JTable();
		// when mouse click
		jTableNhanVien.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = jTableNhanVien.getSelectedRow();
					txtId.setText(jTableNhanVien.getValueAt(row, 1).toString());
					txtTen.setText(jTableNhanVien.getValueAt(row, 2).toString());
					txtChucVu.setText(jTableNhanVien.getValueAt(row, 3).toString());

					txtMail.setText(jTableNhanVien.getValueAt(row, 5).toString());
					txtSdt.setText(jTableNhanVien.getValueAt(row, 6).toString());
					if (jTableNhanVien.getValueAt(row, 7).equals("nam"))
						radioButtonNam.setSelected(true);
					else
						radioButtonNu.setSelected(true);
					String birthday = jTableNhanVien.getValueAt(row, 4).toString();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = df.parse(birthday);
					txtNgaySinh.setDate(startDate);

				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// todo in here ...

		scrollPane.setViewportView(jTableNhanVien);

		// panelMenu edit

		JPanel crudPanel = new JPanel();
		crudPanel.setBackground(new Color(204, 204, 255));
		crudPanel.setBounds(730, 257, 194, 220);
		internalFrame_2.getContentPane().add(crudPanel);
		crudPanel.setLayout(null);
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(UIManager.getColor("Button.darkShadow"));
		btnThem.setBounds(33, 24, 116, 30);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				nhanVienForm = getNhanVienForm();
				if (nhanVienForm != null) {
					nhanVienService.addANhanVien(nhanVienForm);
					nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, "nv.ten", "ASC", idTT);
				}
			}

		});
		crudPanel.add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(UIManager.getColor("Button.darkShadow"));
		btnSua.setBounds(33, 73, 116, 30);
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = jTableNhanVien.getSelectedRow();
				String idNeedToUpdate = "";
				if (jTableNhanVien.getValueAt(row, 0).toString().equals("true"))
					idNeedToUpdate = jTableNhanVien.getValueAt(row, 1).toString();
				else {
					JOptionPane.showMessageDialog(null, "Bạn Chưa chọn nhân viên cần sửa");
				}
				nhanVienForm = getNhanVienForm();
				if (nhanVienForm != null && idNeedToUpdate.length() > 0) {
					nhanVienService.updateANhanVien(nhanVienForm, idNeedToUpdate);
					nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, "nv.ten", "ASC", idTT);
				}

			}
		});
		crudPanel.add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(UIManager.getColor("Button.darkShadow"));
		btnXoa.setBounds(33, 123, 116, 30);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = jTableNhanVien.getSelectedRow();
				String idNeedToDel = "";
				if (row != -1 && jTableNhanVien.getValueAt(row, 0).toString().equals("true"))
					idNeedToDel = jTableNhanVien.getValueAt(row, 1).toString();
				else {
					JOptionPane.showMessageDialog(null, "Bạn Chưa chọn nhân viên cần xóa");
				}
				if (idNeedToDel.length() > 0) {
					List<KTCaNhan> ktByName = ktcnService.listAllKTCNByTenCaNhan(jTableNhanVien.getValueAt(row, 2).toString());
					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(null,
							"Bạn có muốn xóa nhân viên : " + idNeedToDel + jTableNhanVien.getValueAt(row, 2).toString() 
							+" Đã đạt dược"+ktByName.size()+" khen thưởng.",
							"Confirm to Delete?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, options[1]);
					if (n == 0) { // Confirm Delete = Yes
						nhanVienService.deleteANhanVien(idNeedToDel);
						nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, "nv.ten", "ASC", idTT);
					}
				}

			}
		});
		crudPanel.add(btnXoa);

		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(UIManager.getColor("Button.darkShadow"));
		btnReset.setBounds(33, 173, 116, 30);
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtId.setText(null);
				txtTen.setText(null);
				txtSdt.setText(null);
				txtMail.setText(null);
				txtChucVu.setText(null);
				buttonGrGT.clearSelection();
				txtNgaySinh.setDate(null);
				jComboChonDonVi1.setSelectedIndex(0);
				if (jComboChonDonViCon1.isDisplayable())
					jComboChonDonViCon1.setSelectedIndex(0);

			}
		});
		crudPanel.add(btnReset);

		// input information panel

		JPanel inputInforPanel = new JPanel();
		inputInforPanel.setBackground(new Color(204, 204, 255));
		;
		inputInforPanel.setBounds(10, 289, 706, 188);
		internalFrame_2.getContentPane().add(inputInforPanel);
		inputInforPanel.setLayout(null);

		JLabel lblMLp_1 = new JLabel("ID :");
		lblMLp_1.setForeground(new Color(0, 0, 128));
		lblMLp_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMLp_1.setBounds(20, 25, 188, 20);
		inputInforPanel.add(lblMLp_1);

		txtId = new JTextField();
		txtId.setBackground(SystemColor.textHighlightText);
		txtId.setBounds(60, 25, 188, 20);
		inputInforPanel.add(txtId);

		JLabel lblTnLp = new JLabel("Tên:");
		lblTnLp.setForeground(new Color(0, 0, 128));
		lblTnLp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnLp.setBounds(20, 70, 188, 20);
		inputInforPanel.add(lblTnLp);

		txtTen = new JTextField();
		txtTen.setBackground(SystemColor.textHighlightText);
		txtTen.setBounds(60, 70, 188, 20);
		inputInforPanel.add(txtTen);

		JLabel lblSdt = new JLabel("SDT:");
		lblSdt.setForeground(new Color(0, 0, 128));
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSdt.setBounds(20, 115, 188, 20);
		inputInforPanel.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setBackground(SystemColor.textHighlightText);
		txtSdt.setBounds(60, 115, 188, 20);
		inputInforPanel.add(txtSdt);

		JLabel lblMail = new JLabel("Mail:");
		lblMail.setForeground(new Color(0, 0, 128));
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMail.setBounds(20, 160, 188, 20);
		inputInforPanel.add(lblMail);

		txtMail = new JTextField();
		txtMail.setBackground(SystemColor.textHighlightText);
		txtMail.setBounds(60, 160, 188, 20);
		txtMail.setText("@gmail.com");
		inputInforPanel.add(txtMail);

		JLabel lblCV = new JLabel("Chức Vụ:");
		lblCV.setForeground(new Color(0, 0, 128));
		lblCV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCV.setBounds(300, 25, 188, 20);
		inputInforPanel.add(lblCV);

		txtChucVu = new JTextField();
		txtChucVu.setBackground(SystemColor.textHighlightText);
		txtChucVu.setBounds(380, 25, 188, 20);
		inputInforPanel.add(txtChucVu);

		JLabel lblGT = new JLabel("Giới tính:");
		lblGT.setForeground(new Color(0, 0, 128));
		lblGT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGT.setBounds(300, 70, 188, 20);
		inputInforPanel.add(lblGT);

		radioButtonNam = new JRadioButton("Nam");
		buttonGrGT.add(radioButtonNam);
		radioButtonNam.setBounds(380, 70, 100, 20);
		inputInforPanel.add(radioButtonNam);

		radioButtonNu = new JRadioButton("Nữ");
		buttonGrGT.add(radioButtonNu);
		radioButtonNu.setBounds(480, 70, 86, 20);
		inputInforPanel.add(radioButtonNu);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
		lblNgaySinh.setForeground(new Color(0, 0, 128));
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgaySinh.setBounds(300, 115, 188, 20);
		inputInforPanel.add(lblNgaySinh);

		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setBounds(380, 115, 188, 20);
		txtNgaySinh.setDateFormatString("yyyy-MM-dd");
		inputInforPanel.add(txtNgaySinh);

		JLabel lblDonVi = new JLabel("Đơn Vị:");
		lblDonVi.setForeground(new Color(0, 0, 128));
		lblDonVi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDonVi.setBounds(300, 160, 188, 20);
		inputInforPanel.add(lblDonVi);

		jComboChonDonVi1 = new JComboBox<>();
		jComboChonDonVi1.setBounds(380, 160, 120, 20);
		List<TapThe> listTTCha = donViService.getAllTapThe(idTTCha);
		initChonDVConComboBox(jComboChonDonVi1, listTTCha);
		jComboChonDonVi1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (!jComboChonDonVi1.getSelectedItem().toString().equals("Chọn Đơn Vị")) {
						String nameTTCha = jComboChonDonVi1.getSelectedItem().toString();
						idTTCha = donViService.convertTenDVConToId(nameTTCha, listTT);
						if(idTTCha >0){
							List<TapThe> listTTCha = donViService.getAllTapThe(idTTCha);
							initChonDVConComboBox(jComboChonDonViCon1, listTTCha);
							jComboChonDonViCon1.setEnabled(true);
							inputInforPanel.add(jComboChonDonViCon1);
						}else{
							JOptionPane.showMessageDialog(null, "Lỗi");
						}
						
					} else {
						jComboChonDonViCon1.setSelectedIndex(0);
						jComboChonDonViCon1.setEnabled(false);
					}
				}				

			}
		});
		inputInforPanel.add(jComboChonDonVi1);

		jComboChonDonViCon1 = new JComboBox<>();
		jComboChonDonViCon1.setBounds(520, 160, 120, 20);

		// menu bar
		JLabel lblSort = new JLabel("Sắp Xếp");
		lblSort.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSort.setBounds(273, 256, 65, 14);
		internalFrame_2.getContentPane().add(lblSort);

		JButton btnOk = new JButton("A-Z");
		btnOk.setIcon(new ImageIcon("icon/sort-ascending-icon.png"));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderByStr = "id";
				orderStatus = "ASC";
				nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, orderByStr, orderStatus, idTT);
			}
		});
		btnOk.setBounds(493, 252, 95, 23);
		internalFrame_2.getContentPane().add(btnOk);

		JButton btnZa = new JButton("Z-A");
		btnZa.setIcon(new ImageIcon("icon/sort-descending-icon.png"));
		btnZa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderByStr = "id";
				orderStatus = "DESC";
				nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, orderByStr, orderStatus, idTT);
			}
		});
		btnZa.setBounds(618, 253, 95, 23);
		internalFrame_2.getContentPane().add(btnZa);

		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.setIcon(new ImageIcon("/icon/Select-icon.png"));
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, true, "nv.ten", "ASC", idTT);
			}
		});
		btnSelectAll.setBounds(20, 253, 112, 23);
		internalFrame_2.getContentPane().add(btnSelectAll);

		JButton btnUnselect = new JButton("Unselect");
		btnUnselect.setIcon(new ImageIcon("icon/close-icon.png"));
		btnUnselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nhanVienService.showAllDonViTheoTenTTCha(jTableNhanVien, false, "nv.ten", "ASC", idTT);
			}
		});
		btnUnselect.setBounds(142, 253, 110, 23);
		internalFrame_2.getContentPane().add(btnUnselect);

	}

	private void initListTenNvConComboBox(JComboBox jComboBox, List<String> listTenNV) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement("Chọn");
		for (int i = 0; i < listTenNV.size(); i++)
			model.addElement(listTenNV.get(i));
		jComboBox.setModel(model);
		
	}	
	private void initDanhHieuComboBox(JComboBox danhHieuCombobox) {
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement("Chọn loại danh hiệu");
		model.addElement("Danh Hiệu Cá Nhân");
		model.addElement("Danh Hiệu Tập Thể");
		danhHieuCombobox.setModel(model);
	}
	private void initChonDVConComboBox(JComboBox donViComboBox, List<TapThe> listTT) {
		List<String> listName = donViService.getAllNameOfDVTheoCha(listTT);
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addElement("Chọn Đơn Vị");
		for (int i = 0; i < listName.size(); i++)
			model.addElement(listName.get(i));
		donViComboBox.setModel(model);
	}
	private NhanVien getNhanVienForm() {

		NhanVien nhanVien = new NhanVien();
		if (txtId.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Mã nhân viên không đươc để trống !");
			return null;
		} else if (txtTen.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không đươc để trống !");
			return null;
		} else if (jComboChonDonVi1.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Chọn đơn vị không đươc để trống !");
			return null;
		} else if (jComboChonDonViCon1.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Chọn thêm đơn vị phụ thuộc !");
			return null;
		} else if (txtMail.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Mail nhân viên không đươc để trống !");
			return null;
		} else if (txtSdt.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Sdt nhân viên không đươc để trống !");
			return null;
		} else if (txtChucVu.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Chức vụ không đươc để trống !");
			return null;
		} else if (txtNgaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Ngày sinh nhân viên không đươc để trống !");
			return null;
		} else if (radioButtonNam.isSelected() == false && radioButtonNu.isSelected() == false) {
			JOptionPane.showMessageDialog(null, "Giới tính vui lòng chọn Nam hoặc Nữ !");
			return null;
		} else {
			Date birthday = txtNgaySinh.getDate();

			nhanVien.setId(txtId.getText());
			nhanVien.setTenNhanVien(txtTen.getText());			
			int idTTCon = donViService.convertTenTTToId(jComboChonDonViCon1.getSelectedItem().toString(), idTTCha);
			nhanVien.setIdTT(idTTCon);

		}
		return nhanVien;
	}
	private List<Integer> rowsSelectCount(JTable jTable){
		List<Integer> results = new ArrayList<>();
		for(int i= 0; i < jTable.getRowCount(); i++){
			boolean check =  Boolean.valueOf(jTable.getValueAt(i, 0).toString());
			if(check == true)
				results.add(i);
		}
		return results;
	}
	private DanhHieu getDanhHieuForm(){
		DanhHieu danhHieu = new DanhHieu();
		
		if(txtTenDanhHieu.getText().toString().length() == 0 ){
			JOptionPane.showMessageDialog(null, "Tên Danh Hiệu Không Được Bỏ Trống!");
			return null;
		} else if(jcomboChonLoaiDanhHieu1.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(null, "Bạn Chưa Chọn Loại Danh Hiệu Cần Nhập!");
			return null;
		} else if(danhHieuService.findOneDanhHieuByName(txtTenDanhHieu.getText().toString())!=null){
				JOptionPane.showMessageDialog(null, "Đã Có Sẵn Danh Hiệu!");
				return null;				
		} else{
				danhHieu.setName(txtTenDanhHieu.getText().toString());
				if(jcomboChonLoaiDanhHieu1.getSelectedIndex() == 1){
					danhHieu.setIsCaNhan((byte) 1);
				}else{
					danhHieu.setIsCaNhan((byte) 0);
				}
				return danhHieu;
			}
			
		}
	private HinhThucKhen getHTForm(){
		HinhThucKhen hinhThucKhen = new HinhThucKhen();
		
		if(txtTenHTKhen.getText().toString().length() == 0 ){
			JOptionPane.showMessageDialog(null, "Tên Hình thức Không Được Bỏ Trống!");
			return null;
		}else if(hTKTService.findOneHTByName(txtTenHTKhen.getText().toString())!=null){
				JOptionPane.showMessageDialog(null, "Đã Có Sẵn 1 Hình thức!");
				return null;				
		} else{
			hinhThucKhen.setNameHT(txtTenHTKhen.getText().toString());
				
			return hinhThucKhen;
		}
			
	}
	private QuyetDinhKT getQDFrom(){
		QuyetDinhKT qdkt = new QuyetDinhKT();
		
		if(txtMaSoKT.getText().toString().length() == 0 ){
			JOptionPane.showMessageDialog(null, "Mã Quyết Định Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else if(txtTenQD.getText().toString().length() == 0){
			JOptionPane.showMessageDialog(null, "Tên Quyết Định Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else if(txtNgayQD.getDate() == null){
			JOptionPane.showMessageDialog(null, "Ngày Ra Quyết Định Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else{
			qdkt.setMsQD(txtMaSoKT.getText().toString());
			qdkt.setNameQD(txtTenQD.getText().toString());
			qdkt.setNgayQD(txtNgayQD.getDate());
			qdkt.setReferLink(resultUpLoadFile.getText());
			return qdkt;
			
		}
		
		
	}
	private KTCaNhan getKTCNForm(){
		
		if(txtKTCNInputTen.getText().toString().length() == 0 ){
			JOptionPane.showMessageDialog(null, "Tên Nhân Viên Không Được Bỏ Trống!");
			return null;
		}else if(txtNgayKT.getDate() == null){
			JOptionPane.showMessageDialog(null, "Ngày Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else if(jComboChonDanhHieu.getSelectedItem().toString() == "Chọn" && jCOmboChonHinhThuc.getSelectedItem().toString() == "Chọn"){
			JOptionPane.showMessageDialog(null, "Chọn Ít Nhất 1 trong Danh Hiệu && Hình Thức!");
			return null;
		}else if(jComboChonQuyetDinh.getSelectedItem().toString() == "Chọn"){
			JOptionPane.showMessageDialog(null, "Quyết Định Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else{
			KTCaNhan kTCaNhan = new KTCaNhan();
			kTCaNhan.setIdCaNhan(nhanVienService.convertTenToId(txtKTCNInputTen.getText()));
			int idDH = danhHieuService.convertTenToId(jComboChonDanhHieu.getSelectedItem().toString(), "ca nhan");
			int idHT = hTKTService.convertTenToId(jCOmboChonHinhThuc.getSelectedItem().toString());
			String mskt= qDKTService.convertTenToId(jComboChonQuyetDinh.getSelectedItem().toString()); 
			if(mskt == null){
				JOptionPane.showMessageDialog(null, "Không tồn tại quyết định");
				return null;
			}else{
				kTCaNhan.setIdDanhHieu(idDH);
				kTCaNhan.setIdHinhThuc(idHT);
				kTCaNhan.setIdQD(mskt);
				kTCaNhan.setNgayKT(txtNgayKT.getDate());
			}			
			return kTCaNhan;
		}
		
		
	}	
	private KTTapThe getKTTTForm(){
		if(txtKTTTId.getText().toString().length() == 0 ){
			JOptionPane.showMessageDialog(null, "Mã Số Khen Thuỏng Không Được Bỏ Trống!");
			return null;
		}else if(txtKTTTInputTenTT.getText().toString().length() == 0 ){
			JOptionPane.showMessageDialog(null, "Tên đơn vị Không Được Bỏ Trống!");
			return null;
		}else if(txtNgayKTTT.getDate() == null){
			JOptionPane.showMessageDialog(null, "Ngày Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else if(jComboChonDanhHieuInTT.getSelectedItem().toString() == "Chọn" && jCOmboChonHinhThucInTT.getSelectedItem().toString() == "Chọn"){
			JOptionPane.showMessageDialog(null, "Chọn Ít Nhất 1 trong Danh Hiệu && Hình Thức!");
			return null;
		}else if(jComboChonQuyetDinhInTT.getSelectedItem().toString() == "Chọn"){
			JOptionPane.showMessageDialog(null, "Quyết Định Khen Thưởng Không Được Bỏ Trống!");
			return null;
		}else{
			KTTapThe kttt = new KTTapThe();
			String[] infoOfTT = txtKTTTInputTenTT.getText().toString().split("/");			
			if(infoOfTT[1].equals("Đại học Đà Nẵng"))
				idKTTTCon = donViService.convertTenTTToId(infoOfTT[0], 100);
			else{
				idKTTTCha = donViService.convertTenTTToId(infoOfTT[1], 100);
				idKTTTCon = donViService.convertTenTTToId(infoOfTT[0], idKTTTCha);
			}
			int idDH = danhHieuService.convertTenToId(jComboChonDanhHieuInTT.getSelectedItem().toString(), "tap the");
			int idHT = hTKTService.convertTenToId(jCOmboChonHinhThucInTT.getSelectedItem().toString());
			String mskt= qDKTService.convertTenToId(jComboChonQuyetDinhInTT.getSelectedItem().toString()); 
			if(mskt == null){
				JOptionPane.showMessageDialog(null, "Không tồn tại quyết định");
				return null;
			}else{
				kttt.setId(txtKTTTId.getText().toString());
				kttt.setIdTapThe(idKTTTCon);
				kttt.setIdDanhHieu(idDH);
				kttt.setIdHinhThuc(idHT);
				kttt.setIdQD(mskt);
				kttt.setNgayKT(txtNgayKTTT.getDate());
			}			
			return kttt;
		}
		
		
	}	
	private TapThe getTTForm(){
		TapThe tapTheOb = new TapThe();
		if (kyHieuTxt.getText().toString().length() == 0) {
			JOptionPane.showMessageDialog(null, "Ký hiệu đơn vị không được bỏ trống");
		} else if (txtTenDonVi.getText().toString().length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên đơn vị không được bỏ trống");
		} else{					
			tapTheOb.setKyHieuTT(kyHieuTxt.getText().toString());
			tapTheOb.setTenTT(txtTenDonVi.getText().toString());
			String nameTT = donViComboBox_2.getSelectedItem().toString();
			idTTCha = (nameTT == "Chọn Đơn Vị") ? 100 : donViService.convertTenDVConToId(nameTT, listTT);
			tapTheOb.setIdTTCha(idTTCha);
			
		}	
		return tapTheOb;
	}
	private FilterSearch getThongKeKTCNForm(){
		int idCha = 0, idCon =0;
		int idDH = 0, idHT = 0, nam = 0;
		String[] txtQD = txtTheoTenQD.getText().split("/");
		FilterSearch filterOb = new FilterSearch();

		if (jComboTheoDVCon.getSelectedIndex() !=0 && jComboTheoDVCon.getSelectedIndex() != -1) {
			idCha = donViService.convertTenTTToId(jComboTheoDV.getSelectedItem().toString(), 100);
			idCon = donViService.convertTenTTToId(jComboTheoDVCon.getSelectedItem().toString(), idCha);
			filterOb.setIdDV(idCon);
		} else{
			if (jComboTheoDV.getSelectedIndex() !=0){
				idCha = donViService.convertTenTTToId(jComboTheoDV.getSelectedItem().toString(), 100);
				filterOb.setIdDV(idCha);
			}else{
				filterOb.setIdDV(0);
			}
		}
		if(chckbxTheoDanhHieu.isSelected()&&jComboTheoDh.getSelectedIndex() !=0){
			idDH = danhHieuService.convertTenToId(jComboTheoDh.getSelectedItem().toString(), jComboCNorTT.getSelectedItem().toString());
			filterOb.setIdDanhHieu(idDH);
		}else
			filterOb.setIdDanhHieu(idDH);
		if(chckbxTheoHinhThuc.isSelected()&&jComboTheoHT.getSelectedIndex() !=0){
			idHT = hTKTService.convertTenToId(jComboTheoHT.getSelectedItem().toString());
			filterOb.setIdHinhThuc(idHT);
		}else
			filterOb.setIdHinhThuc(idHT);
		if(!txtTheoTenQD.getText().toString().equals("Nhập tên hoặc ma so QDKT") && (txtTheoTenQD.getText().toString().length()>0))
			filterOb.setIdQD(txtQD[1]);
		if(chckbxTheoNam.isSelected()){
			if(txtNamKt.getText().toString().length() > 0){
				try{
					nam = Integer.parseInt(txtNamKt.getText());
					filterOb.setNam(nam);
				}catch(NumberFormatException ne){
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đúng năm là số !");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập năm khen thuong!");
				filterOb.setNam(0);
			}
				
		}
		return filterOb;

	}
	private void updateDVButtonListner(){
		initChonDVConComboBox(donViComboBox_2, listTT);
		initChonDVConComboBox(donViComboBox, listTT);
		initChonDVConComboBox(jComboChonDonVi, listTT);
		initChonDVConComboBox(jComboChonDonVi1, listTT);
		initChonDVConComboBox(jComboChonDonViInKTCN, listTT);
		initChonDVConComboBox(jComboChonDonViInKTTT, listTT);
		initChonDVConComboBox(jComboTheoDV, listTT);
		jComboChonDonViCon.setEnabled(false);
		jComboChonDonViCon1.setEnabled(false);
		jComboChonDonViConInKTCN.setEnabled(false);
		jComboChonDonViConInKTTT.setEnabled(false);
		jComboTheoDVCon.setEnabled(false);
	}
}
