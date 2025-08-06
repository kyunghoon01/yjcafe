package yjcafe.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import yjcafe.Page;
import yjcafe.YJCafe;
import yjcafe.model.BookingVO;
import yjcafe.model.CoffeeVO;
import yjcafe.model.UserVO;

import yjcafe.model.MenuVO;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateReservation extends Page {
	static class Param {
		public int r_number;
		public int h_number;
	}

	private static final long serialVersionUID = -1182873622661729850L;

	private static final int PANEL_WIDTH = 550;

	private ArrayList<BookingVO> bookings = new ArrayList<>();
	private UserVO user = new UserVO();
	private CoffeeVO coffee = new CoffeeVO();
	private MenuVO menu = new MenuVO();

	private String u_id = null;
	private int r_number = 0;
	private int h_number = 0;

	
	
	private String min_date = null;
	private String max_date = null;

	private MyLabel date_label = new MyLabel("", 12);
	private MyLabel days_label = new MyLabel("", 14);
	private MyLabel cost_label = new MyLabel("", 14);

	private CenterPanel center = new CenterPanel();
	private BottomPanel bottom = new BottomPanel();

	public DateReservation() {
		super("예약 시간 지정", "Date Reservation");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(center);
		add(Box.createVerticalStrut(10));
		add(bottom);
	}

	@Override
	public void onMount(Object param) {
		Param params = castParam(param);
		String u_id = YJCafe.getUser().getU_id();
		
		
		if (u_id == null || params.r_number == 0) {
			System.out.println("입력 정보가 올바르지 않습니다.");
			return;
		}
		this.u_id = u_id;
		r_number = params.r_number;
		h_number = params.h_number;
		System.out.println(r_number);
		makeConnection();
		try {
			center.load();
			bottom.load();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void makeConnection() {
		try {
			Connection conn = YJCafe.getConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM 회원 WHERE u_id='" + u_id + "'");
			if (rs.next()) {
				 user.setU_name(rs.getString("u_name"));
	               user.setU_id(rs.getString("u_id"));
	               user.setU_password(rs.getString("u_password"));
	               user.setU_phone_number(rs.getString("u_phone_number"));
	               user.setU_birthday(rs.getDate("u_birthday"));
	               user.setU_email(rs.getString("u_email"));
	               user.setU_cash(rs.getInt("u_cash"));
	               user.setU_enroll_date(rs.getDate("u_enroll_date"));
	               user.setU_profile_image(rs.getString("u_profile_image"));
	               user.setU_department(rs.getString("u_department"));
				System.out.println("회원 연결 성공");
			} else
				System.out.println("회원이 존재하지 않습니다.");

			

			rs = stmt.executeQuery("SELECT * FROM 메뉴 WHERE m_number=" + r_number);
			if (rs.next()) {
				menu.m_number= rs.getInt("m_number");
				menu.m_name=rs.getString("m_name");
				menu.m_description=rs.getString("m_description");
				//coffee.description = rs.getString("m_description");
				//coffee.coffee_number = rs.getInt("r_coffee_number");
				//coffee.area_size = rs.getInt("r_area_size");
				//coffee.max_personnel = rs.getInt("r_max_personnel");
				//coffee.demand_rate = rs.getDouble("r_demand_rate");
				menu.m_price=rs.getInt("m_price");
				menu.m_total_price=rs.getInt("m_total_price");
				//coffee.cost = rs.getInt("m_price");
				//coffee.total_cost = rs.getInt("r_total_cost");
				menu.k_number=rs.getInt("k_number");
				//coffee.h_number = rs.getInt("k_number");
				System.out.println("메뉴 연결 성공");
			} else
				System.out.println("메뉴가 존재하지 않습니다.");
		} catch (SQLException err) {
//			System.out.println("연결에 실패하였습니다.");
			YJCafe.showAlert("데이터베이스 처리에 실패했습니다.");
		}
	}

	private class CenterPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private JComboBox<String> hours;
	    private JComboBox<String> minutes;
	    
	    private JButton selectButton;
	    private JButton deselectButton;

		public CenterPanel() {
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setPreferredSize(new Dimension(PANEL_WIDTH, 400));
			setLayout(new BorderLayout());
		}

	

		 private void load() throws SQLException {
		        removeAll();

		        // Initialize the combo boxes with values for hours and minutes
		        String[] hourValues = new String[15];
		        for (int i = 0; i < 15; i++) {
		            hourValues[i] = String.format("%02d", i+8); // 2-digit format
		        }
		        hours = new JComboBox<>(hourValues);

		        String[] minuteValues = new String[60];
		        for (int i = 0; i < 60; i++) {
		            minuteValues[i] = String.format("%02d", i); // 2-digit format
		        }
		        minutes = new JComboBox<>(minuteValues);

		        // Creating a panel for the time selection components
		        JPanel timeSelectionPanel = new JPanel();
		       
		        timeSelectionPanel.add(hours);
		        timeSelectionPanel.add(new JLabel("시:"));
		       
		        timeSelectionPanel.add(minutes);
		        timeSelectionPanel.add(new JLabel("분:"));

		        // Add the time selection panel to the CenterPanel
		        add(timeSelectionPanel, BorderLayout.CENTER);
		        
		        selectButton = new JButton("Select");
		        deselectButton = new JButton("Deselect");
		        
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setLayout(new FlowLayout()); // Using FlowLayout to place buttons next to each other
		        buttonPanel.add(selectButton);
		        buttonPanel.add(deselectButton);

		        // Add action listeners to the buttons
		        selectButton.addActionListener(e -> selectTime());
		        deselectButton.addActionListener(e -> deselectTime());

		        // Add the buttons to the panel
		        add(timeSelectionPanel, BorderLayout.CENTER);
		        add(buttonPanel, BorderLayout.SOUTH); 
		    }

		    // Method to get the selected time
		  private void selectTime() {
		        String selectedTime = getSelectedTime();
		        bottom.setSelectedTime(selectedTime);
		    }

		    private void deselectTime() {
		        bottom.clearSelectedTime();
		    }

		    private String getSelectedTime() {
		        return hours.getSelectedItem() + ":" + minutes.getSelectedItem();
		    }
			    
		}



	private class BottomPanel extends JPanel {
		private static final long serialVersionUID = -6378189534685653400L;

		private MyLabel selectedTimeLabel;
		 private MyLabel reservationDateLabel;
		    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		public BottomPanel() {
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "선택 정보"));
			setPreferredSize(new Dimension(PANEL_WIDTH, 255));
			setLayout(new BorderLayout());
		}
	
		public void setSelectedTime(String time) {
		    selectedTimeLabel.setText(time); // Set only the time
		}

	    public void clearSelectedTime() {
	        selectedTimeLabel.setText("예약 시간: None");
	        // ... additional handling ...
	    }
		private void load() throws Exception {
			removeAll();
			Connection conn = YJCafe.getConnection();
			{
				JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
				{
					ImagePanel panel = new ImagePanel();
					//panel.setBackground(Color.WHITE);
					panel.setPreferredSize(new Dimension(100, 100));
					panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					ResultSet rs = conn.createStatement()
							.executeQuery("SELECT m_image FROM 메뉴_이미지 WHERE m_number=" + r_number);
					if (rs.next()) {
						panel.image = ImageIO.read(new URL(rs.getString("m_image"))).getScaledInstance(100, 100,
								BufferedImage.SCALE_DEFAULT);
						panel.repaint();
					}

					container.add(panel);
				}
				{
					JPanel panel = new JPanel(new GridLayout(3, 1, 0, 12));
					panel.add(new MyLabel(menu.m_name));
					panel.add(new MyLabel(menu.m_description));
					panel.add(new MyLabel("가격 " +menu.m_price));
					container.add(panel);
				}
				add(container, BorderLayout.NORTH);
			}
			JTextField capField = new JTextField(4);
			{
				JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT, 28, 0));
				{
					JPanel innerContainer = new JPanel(new BorderLayout(28, 0));
					innerContainer.setPreferredSize(new Dimension(370, 100));
					final int SIZE = 14;
					{
						JPanel panel = new JPanel(new GridLayout(4, 1, 0, 8));
						panel.add(new MyLabel("수량", SIZE, true));
						LocalDate currentDate = LocalDate.now();
				        reservationDateLabel = new MyLabel("예약 날짜: " + currentDate.format(DATE_FORMATTER), SIZE, true);
				        panel.add(reservationDateLabel);
						 selectedTimeLabel = new MyLabel("예약 시간: None", SIZE, true);
					        panel.add(selectedTimeLabel);

					        panel.add(new MyLabel("총 비용", SIZE, true));
					        innerContainer.add(panel, BorderLayout.WEST);
						innerContainer.add(panel, BorderLayout.WEST);
					}
					{
						JPanel panel = new JPanel(new GridLayout(4, 1, 0, 8));
						JPanel textPanels[] = { new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)),
								new JPanel(new BorderLayout(5, 0)) };
						capField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						capField.setHorizontalAlignment(JTextField.CENTER);
						MyLabel alert = new MyLabel("", SIZE);
						alert.setForeground(Color.RED);
						capField.addActionListener(e -> {
							JTextField tmp = (JTextField) e.getSource();
				            try {
				            	
				                int quantity = Integer.parseInt(tmp.getText());
				                if (quantity <= 0) {
				                    throw new NumberFormatException();
				                }
				                // Calculate the total cost
				                int totalCost = menu.m_total_price * quantity;
				                cost_label.setText(String.valueOf(totalCost));
				            } catch (NumberFormatException ex) {
				                YJCafe.showAlert("올바른 수량을 입력하세요.");
				                tmp.setText("");
				            }
				        });
						textPanels[1].add(capField, BorderLayout.WEST);
						textPanels[1].add(new MyLabel("개", SIZE), BorderLayout.CENTER);
						textPanels[1].add(alert, BorderLayout.EAST);
						textPanels[0].add(textPanels[1]);
						panel.add(textPanels[0]);
						panel.add(date_label);
						panel.add(days_label);
						panel.add(cost_label);
						innerContainer.add(panel, BorderLayout.CENTER);
					}
					JPanel outerContainer = new JPanel(new BorderLayout());
					outerContainer.add(innerContainer, BorderLayout.CENTER);
					{
						JPanel panel = new JPanel(new BorderLayout());
						JButton button = new JButton("결제하기");
						button.setFont(new Font("굴림", Font.BOLD, 20));
						button.setPreferredSize(new Dimension(130, 50));
						panel.add(button, BorderLayout.SOUTH);
						outerContainer.add(panel, BorderLayout.EAST);
						button.addActionListener((e) -> {
			 				String cost = cost_label.getText();
			 				 String time = selectedTimeLabel.getText(); 
							String caps = capField.getText();
						
							if (caps.trim().equals("")) {
								YJCafe.showAlert("수량을 입력해주세요.");
								return;
							}
							 if (time.equals("예약 시간: None")) {
							        YJCafe.showAlert("시간을 설정하세요.");
							        return;
							    }
							
							try {
								int b_guest_quantity = Integer.parseInt(caps);
								int b_cost = Integer.parseInt(cost);
								  String[] parts = time.split(":");
							        int hours = Integer.parseInt(parts[0]);
							        int minutes = Integer.parseInt(parts[1]);
							        int b_days = hours * 60 + minutes; 
							        
								 String b_date = reservationDateLabel.getText().substring("예약 날짜: ".length());
								PayInfo.Param param = new PayInfo.Param();
								param.h_number = h_number;
								param.r_number = r_number;
								param.b_cost = b_cost;
								param.b_days = b_days;
								param.b_date = b_date;
								
								param.hours=hours;
								param.minutes=minutes;
								
								param.b_guest_quantity = b_guest_quantity;

								YJCafe.route("payinfo", param);
							} catch (Exception ex) {
								ex.printStackTrace();
								YJCafe.showAlert("유효하지 않은 값이 있습니다.");
							}
						});
					}
					container.add(outerContainer);
				}
				add(container, BorderLayout.CENTER);
			}
		}
	}

	private class MyLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public MyLabel(String text) {
			this(text, 15, false);
		}

		public MyLabel(String text, int size) {
			this(text, size, false);
		}

		public MyLabel(String text, int size, boolean mode) {
			super(text);
			setFont(new Font("굴림", mode ? Font.BOLD : Font.PLAIN, size));
			if (mode)
				setHorizontalAlignment(JLabel.CENTER);
		}
	}

	private class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		Image image;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(image, 0, 0, null);
		}
	}
}
